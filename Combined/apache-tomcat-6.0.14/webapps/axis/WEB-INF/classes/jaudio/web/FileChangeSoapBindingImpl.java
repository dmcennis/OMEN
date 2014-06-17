/**
 * FileChangeSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.web;

import jaudio.ConfigurationReader;
import jaudio.ConfigurationReaderTest;
import jaudio.EscapeCharacters;
import jaudio.FileRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class FileChangeSoapBindingImpl implements jaudio.web.FileChange {
	public int[] loadFileRecord(jaudio.FileRecord[] in0, java.lang.String in1)
			throws java.rmi.RemoteException {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		String errorMessage = "";
		int[] ret = null;
		try {
			ConfigurationReader config = ConfigurationReader
					.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();
			ret = new int[in0.length];
			for (int i = 0; i < in0.length; ++i) {
				String[] data = new String[] { in0[i].getAlbum(),
						in0[i].getArtist(), in0[i].getTrack(),
						in0[i].getGenre(), in0[i].getType(), in1 };
				// for(int j=0;j<data.length;++j){
				// System.err.println(data[j]);
				// }
				EscapeCharacters.EscapeCharacters(data);
				int numberResults = stat.executeUpdate(
						"INSERT INTO file (album,artist,track,genre,type,library) VALUES ('"
								+ data[0] + "','" + data[1] + "','" + data[2]
								+ "','" + data[3] + "','" + data[4] + "','"
								+ data[5] + "')",
						Statement.RETURN_GENERATED_KEYS);

				rs = stat.getGeneratedKeys();
				if ((numberResults > 0) && (rs.next())) {
					ret[i] = rs.getInt(1);
				} else {
					ret[i] = -1;
				}
			}
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			errorMessage = "SQL Error: " + e.getMessage();
			ret = null;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver failed to load");
			e.printStackTrace();
			errorMessage = "JDBC Driver failed to load";
			ret = null;
		} catch (Exception e) {
			System.out.println("Problems with configuration file");
			e.printStackTrace();
			errorMessage = "Problems with configuration file";
			ret = null;
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
		if (ret != null) {
			return ret;
		} else {
			throw new java.rmi.RemoteException(errorMessage);
		}
	}

	public int changeFileRecord(jaudio.FileRecord[] in0, java.lang.String in1)
			throws java.rmi.RemoteException {

		Connection conn = null;
		PreparedStatement getID = null;
		PreparedStatement update = null;
		ResultSet rs = null;
		int retCount = 0;
		try {
			ConfigurationReader config = ConfigurationReader.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager
					.getConnection(config.getDatabaseURL());
			getID = conn
					.prepareStatement("SELECT id FROM file WHERE id = ? AND library = ?");
			update = conn
					.prepareStatement("UPDATE file SET album=?, artist=?,track=?,type=?,genre=? WHERE id=?");
			for (int i = 0; i < in0.length; ++i) {
				getID.clearParameters();
				getID.setInt(1, in0[i].getFileId());
				getID.setString(2, in1);
				rs = getID.executeQuery();
				if (rs.next()) {
					int id = rs.getInt(1);
					update.clearParameters();
					update.setString(1, in0[i].getAlbum());
					update.setString(2, in0[i].getArtist());
					update.setString(3, in0[i].getTrack());
					update.setString(4, in0[i].getType());
					update.setString(5,in0[i].getGenre());
					update.setInt(6, id);
					update.executeUpdate();
				} else {
					retCount++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (getID != null) {
				try {
					getID.close();
				} catch (SQLException e) {
				}
			}
			if (update != null) {
				try {
					update.close();
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

		return retCount;
	}

	public int deleteFileRecord(int[] in0, java.lang.String in1)
			throws java.rmi.RemoteException {
		int retCount = 0;
		Connection conn = null;
		PreparedStatement delete = null;
		ResultSet rs = null;
		try {
			ConfigurationReader config = ConfigurationReader
					.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			delete = conn
					.prepareStatement("DELETE FROM file WHERE id=? and library=?");
			for (int i = 0; i < in0.length; ++i) {
				delete.setInt(1, in0[i]);
				delete.setString(2, in1);
				int ret = delete.executeUpdate();
				if (ret != 1) {
					retCount++;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Problems: " + e.getMessage());
			e.printStackTrace();
			return -2;
		} catch (ClassNotFoundException e) {
			System.out.println("SQL Driver not found: " + e.getMessage());
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			System.out.println("Problems with the configuration file: "
					+ e.getMessage());
			e.printStackTrace();
			return -3;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (delete != null) {
				try {
					delete.close();
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
		return retCount;
	}

	public jaudio.FileRecord[] listFileRecord(java.lang.String in0)
			throws java.rmi.RemoteException {
		LinkedList<jaudio.FileRecord> list = new LinkedList<jaudio.FileRecord>();

		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			ConfigurationReader config = ConfigurationReader
					.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn
					.prepareStatement("SELECT album, artist, track, genre, type, id FROM file WHERE library=?");
			stat.setString(1, in0);
			rs = stat.executeQuery();
			while (rs.next()) {
				FileRecord fr = new FileRecord();
				fr.setAlbum(rs.getString(1));
				fr.setArtist(rs.getString(2));
				fr.setTrack(rs.getString(3));
				fr.setGenre(rs.getString(4));
				fr.setType(rs.getString(5));
				fr.setFileId(rs.getInt(6));
				list.add(fr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("Problems with the configuration file: "
					+ e.getMessage());
			e.printStackTrace();
			return null;
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

		return list.toArray(new FileRecord[] {});
	}

}
