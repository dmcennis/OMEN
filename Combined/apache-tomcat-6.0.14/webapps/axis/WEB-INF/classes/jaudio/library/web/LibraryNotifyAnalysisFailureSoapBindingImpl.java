/**
 * LibraryNotifyAnalysisFailureSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

import jaudio.library.ConfigurationReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryNotifyAnalysisFailureSoapBindingImpl implements jaudio.library.web.LibraryNotifyAnalysisFailure{
    public void notifyAnalysisFailure(int in0, int in1) throws java.rmi.RemoteException {
    		Connection conn = null;
    		PreparedStatement stat = null;
    		PreparedStatement insert = null;
    		ResultSet rs = null;
    		ResultSet max = null;
    		
    		if(in1 == LibraryNotifyAnalysisFailure.EXCEPTION_IN_EXECUTION){
    			System.out.println("Worker "+in0+" experienced an exception while executing analysis");
    		}else if(in1 == LibraryNotifyAnalysisFailure.EXPLICIT_CANCEL){
    			System.out.println("Worker "+in0+" was explicitly terminated by the Library Node");
    		}
    		
    		try {
				ConfigurationReader config = ConfigurationReader.getConfigurationReader();
				Class.forName(config.getDatabaseDriver());
				conn = DriverManager.getConnection(config.getDatabaseURL());
				stat = conn.prepareStatement("SELECT task_id, file_id FROM outstanding_calculations WHERE worker_id=?");
				stat.setInt(1,in0);
				rs = stat.executeQuery();
				try {
					insert = conn.prepareStatement("LOCK TABLES queue WRITE");
					insert.execute();
					int position = 0;
					max = insert.executeQuery("SELECT MAX(position) FROM queue");
					if(max.next()){
						position = max.getInt(1)+1;
					}
					insert = conn.prepareStatement("INSERT INTO queue (tid,fid,position) VALUES (?,?,?)");
					while(rs.next()){
						insert.setInt(1,rs.getInt(1));
						insert.setInt(2,rs.getInt(2));
						insert.setInt(3,position);
						insert.executeUpdate();
						position++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					insert.execute("UNLOCK TABLES");
				}
				stat = conn.prepareStatement("DELETE FROM outstanding_calculations WHERE worker_id=?");
				stat.setInt(1,in0);
				stat.executeUpdate();
			} catch (ClassNotFoundException e) {
				System.out.println("Database Drivers could not be found");
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs != null){
					try {
						rs.close();
					} catch (SQLException e) {}
				}
				if(max != null){
					try {
						max.close();
					} catch (SQLException e) {}
				}
				if(stat != null){
					try {
						stat.close();
					} catch (SQLException e) {}
				}
				if(insert != null){
					try {
						insert.close();
					} catch (SQLException e) {}
				}
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
    }

}
