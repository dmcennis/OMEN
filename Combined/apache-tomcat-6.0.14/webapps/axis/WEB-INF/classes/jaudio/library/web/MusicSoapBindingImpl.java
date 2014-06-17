/**
 * MusicSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

import jaudio.library.ConfigurationReader;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class MusicSoapBindingImpl implements jaudio.library.web.Music {
	public javax.activation.DataHandler getMusic(int in0)
			throws java.rmi.RemoteException {

		DataHandler dh = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stat = null;

		try {
			ConfigurationReader config = ConfigurationReader.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager
					.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();
			rs = stat.executeQuery("SELECT path from file where id=" + in0);
			if (rs.next()) {
				String path = rs.getString(1);
				File file = new File(path);
				dh = new DataHandler(new FileDataSource(file));
			} else {
				dh = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			dh = null;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver failed to load");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Failed to find configuration file");
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
		return dh;
	}

}
