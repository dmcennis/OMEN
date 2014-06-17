/**
 * LibraryNewFeatureSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

import jaudio.library.ConfigurationReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.rpc.ServiceException;

public class LibraryNewFeatureSoapBindingImpl implements jaudio.library.web.NewFeature{
    public int putNewFeature(javax.activation.DataHandler in0, java.lang.String in1, int in2) throws java.rmi.RemoteException {
    	
    	if((in2 != NewFeature.FEATURE)&&(in2 != NewFeature.AGGREGATOR)){
    		throw new java.rmi.RemoteException("Type must either be a feature or an aggregator.  Value "+in2+" sent.");
    	}
    	
    	ConfigurationReader config;
		try {
			config = ConfigurationReader.getConfigurationReader();
		} catch (Exception e1) {
			e1.printStackTrace();
			return -1;
		}

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int ret = 0;
		try {
			InputStream loadFeature = in0.getInputStream();
			java.io.File tmp = java.io.File.createTempFile("new","feature");
			OutputStream writeFeature = new FileOutputStream(tmp);
			byte[] buffer = new byte[10240];
			int bytesRead = loadFeature.read(buffer);
			while(bytesRead > 0){
					writeFeature.write(buffer,0,bytesRead);
					bytesRead = loadFeature.read(buffer);
			}
			loadFeature.close();
			writeFeature.close();
			DataHandler dh = new DataHandler(new FileDataSource(tmp));
			
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();
			rs = stat.executeQuery("SELECT location FROM worker");
			String location = "";
			while(rs.next()){
				location = rs.getString(1);
				jaudio.worker.web.NewFeatureServiceLocator source = new jaudio.worker.web.NewFeatureServiceLocator();
				source.setWorkerNewFeatureEndpointAddress(location+"WorkerNewFeature");
				System.out.println(location+"WorkerNewFeature");
				jaudio.worker.web.NewFeature dest= source.getWorkerNewFeature();
				dest.putNewFeature(dh,in1,in2);
			}
			tmp.delete();
		} catch (ClassNotFoundException e) {
			ret = -2;
			e.printStackTrace();
		} catch (SQLException e) {
			ret = -3;
			e.printStackTrace();
		} catch (ServiceException e) {
			ret = -4;
			e.printStackTrace();
		} catch (IOException e) {
			ret = -5;
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
		return ret;
    }

}
