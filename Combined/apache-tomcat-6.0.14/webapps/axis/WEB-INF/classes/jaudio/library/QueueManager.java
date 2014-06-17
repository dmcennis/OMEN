package jaudio.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Element;

public class QueueManager {

	static QueueManager singleton = null;

	static UpdateThread updater;
	
	/**
	 * @author mcennis
	 *
	 */
	public class UpdateThread extends Thread {
		QueueManager manager;
		boolean done = false;
		@Override
		public void run() {
			
			manager = QueueManager.getManager();
			while(!done){
				manager.update();
				Thread.yield();
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					done = true;
				}
			}
		}

	}

	private QueueManager() {
		updater = new UpdateThread();
		updater.setPriority(1);
		updater.setDaemon(true);
		updater.start();
	}

	static public QueueManager getManager() {
		if (singleton == null) {
			singleton = new QueueManager();
		}
		return singleton;
	}

	public synchronized int addFileRequests(String[] fileId, int taskId) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			ConfigurationReader config = ConfigurationReader
					.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());

			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();
			stat.execute("LOCK TABLES queue WRITE");
			rs = stat.executeQuery("SELECT MAX(position) FROM queue");
			int position = 0;
			if (rs.next()) {
				position = rs.getInt(1) + 1;
			}
			for (int i = 0; i < fileId.length; ++i) {
				stat
						.executeUpdate("INSERT INTO queue (tid,fid,position) VALUES ("
								+ taskId
								+ ",'"
								+ fileId[i]
								+ "',"
								+ position
								+ ")");
				position++;
			}
			stat.execute("UNLOCK TABLES");
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized void update() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			System.out.println("Executing Update");
			ConfigurationReader config = ConfigurationReader
					.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();

			rs = stat.executeQuery("SELECT type,day,hour,minute FROM time");
			Vector<Time> timeList = new Vector<Time>();
			while (rs.next()) {
				Time t = new Time();
				if (rs.getInt(1) == 0) {
					t.setType(true);
				} else {
					t.setType(false);
				}
				t.setDay(rs.getInt(2));
				t.setHour(rs.getInt(3));
				t.setMinute(rs.getInt(4));
				timeList.add(t);
			}

			boolean go = true;
			if (timeList.size() > 0) {
				go = Time.isOn(timeList.toArray(new Time[] {}));
			}
			if (go) {
				rs = stat
						.executeQuery("SELECT tid,fid FROM queue ORDER BY position ASC");
				LinkedList<Integer> tid = new LinkedList<Integer>();
				LinkedList<Integer> fid = new LinkedList<Integer>();
				while (rs.next()) {
					tid.add(rs.getInt(1));
					fid.add(rs.getInt(2));
				}
				int[][] taskAndFile = new int[2][tid.size()];
				Integer[] tidI = tid.toArray(new Integer[] {});
				Integer[] fidI = fid.toArray(new Integer[] {});
				for (int i = 0; i < taskAndFile[0].length; ++i) {
					taskAndFile[0][i] = tidI[i].intValue();
					taskAndFile[1][i] = fidI[i].intValue();
				}
				if (taskAndFile[0].length > 0) {
					rs = stat
							.executeQuery("SELECT worker.id FROM worker WHERE worker.id NOT IN (SELECT worker.id FROM worker,outstanding_calculations WHERE worker.id=outstanding_calculations.worker_id)");
					LinkedList<Integer> workerList = new LinkedList<Integer>();
					while (rs.next()) {
						workerList.add(rs.getInt(1));
					}
					int currentTask = 0;
					Iterator<Integer> workerCurser = workerList.listIterator();
					while ((currentTask < taskAndFile[0].length)
							&& (workerCurser.hasNext())) {
						int worker = workerCurser.next();
						int ret = startWorker(taskAndFile[0][currentTask],
								taskAndFile[1][currentTask], worker, conn,
								stat, rs);
						if (ret == 0) {
							++currentTask;
						}
					}
				} else {
					// no outstanding tasks - deliberate no-op.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}
	}

	int startWorker(int task, int file, int worker, Connection conn,
			Statement stat, ResultSet rs) throws Exception {

		rs = stat.executeQuery("SELECT location from worker where ID = "
				+ worker);
		if (rs.next()) {
			String location = rs.getString(1);
			rs.close();
			rs = stat.executeQuery("SELECT settings from tasks where id="
					+ task);
			if (rs.next()) {
				String settings = rs.getString(1);
				rs.close();
				settings = buildBatch(settings, file);
				System.out.println("Work sent to worker " + location + ".");
				SOAPBodyElement[] args = new SOAPBodyElement[2];
				args[0] = new SOAPBodyElement(XMLBatch.String2Element(settings));
				args[1] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"task", Integer.toString(task)));
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(new java.net.URL(location
						+ "WorkerExecuteBatch"));
				Vector ret = (Vector) call.invoke(args);
				if (ret.size() == 1) {
					org.w3c.dom.Element response = ((SOAPBodyElement) ret
							.get(0)).getAsDOM();
					if ("OK".equals(response.getNodeName())) {
						// stat.execute("LOCK TABLES queue WRITE");
						stat.executeUpdate("DELETE FROM queue WHERE tid="
								+ task + " AND fid=" + file);
						// stat.execute("UNLOCK TABLES");
						stat
								.executeUpdate("INSERT INTO outstanding_calculations VALUES ("
										+ worker
										+ ","
										+ task
										+ ","
										+ file
										+ ")");
						return 0;
					} else {
						System.out.println("Attempt to send work to worker "
								+ location + " failed");
						System.out.println(XMLUtils.ElementToString(response));
						return 2;
					}
				} else {
					System.out
							.println("INTERNAL ERROR: no response from worker "
									+ location);
					return 3;
				}
			} else {
				System.out.println("INTERNAL ERROR: Settings don't exist for this task");
				return 4;
			}
		} else {
			System.out.println("INTERNAL ERROR: Worker id "+worker+"  does not correspond to a valid worker");
			return 1;
		}

	}

	String buildBatch(String settings, int file) {
		String ret = "<batch ID=\"Basic\">"
				+ System.getProperty("line.separator") + "		<fileSet>"
				+ System.getProperty("line.separator") + "			<file>" + file
				+ "</file>" + System.getProperty("line.separator")
				+ "		</fileSet>" + System.getProperty("line.separator")
				+ settings + System.getProperty("line.separator")
				+ "<destination>feature_definitions_1.xml</destination>"
				+ System.getProperty("line.separator")
				+ "<destination>feature_values_1.xml</destination>"
				+ System.getProperty("line.separator") + "</batch>"
				+ System.getProperty("line.separator");
		return ret;
	}
	
	public void stopUpdate(){
		updater.done = true;
	}
	
	public void restartUpdate(){
		updater = new UpdateThread();
		updater.setPriority(1);
		updater.setDaemon(true);
		updater.start();
	}

}
