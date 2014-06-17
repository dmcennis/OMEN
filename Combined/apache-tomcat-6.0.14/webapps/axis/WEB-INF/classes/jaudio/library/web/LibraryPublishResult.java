package jaudio.library.web;

/*
 * Copyright 2002-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jaudio.library.ConfigurationReader;
import jaudio.library.EscapeCharacters;
import jaudio.library.QueueManager;
import jaudio.library.Result;
import jaudio.library.XMLBatch;
import jAudioFeatureExtractor.ACE.DataTypes.Batch;
import jAudioFeatureExtractor.DataTypes.RecordingInfo;

import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPBodyElement;

/**
 * Simple message-style service sample.
 */
public class LibraryPublishResult {
	/**
	 * Service method, which simply echoes back any XML it receives.
	 * 
	 * @param elems
	 *            an array of DOM Elements, one for each SOAP body element
	 * @return an array of DOM Elements to be sent in the response body
	 */
	public Element[] execute(Element[] elems) {
		Element[] ret = new SOAPBodyElement[1];

		int task = -1;
		NodeList nl = elems[0].getChildNodes();
		for (int i = 0; i < nl.getLength(); ++i) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.TEXT_NODE) {
				task = Integer.parseInt(((Text) n).getData());
			}
		}

		String file = "";
		nl = elems[1].getChildNodes();
		for (int i = 0; i < nl.getLength(); ++i) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.TEXT_NODE) {
				file = ((Text) n).getData();
			}
		}

		String type = "";
		nl = elems[2].getChildNodes();
		for (int i = 0; i < nl.getLength(); ++i) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.TEXT_NODE) {
				type = ((Text) n).getData();
			}
		}

		// Currently assuming that this data has not been returned before. If it
		// has, this will overwrite the previous value. Also will try to remove
		// non-existant entry for outstanding calculations in the database
		//
		// Load these results in database
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			ConfigurationReader config = ConfigurationReader
					.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();
			// All results in, so publish results to master server

			// First, get the library name from the database
			rs = stat.executeQuery("SELECT name FROM libraryName");
			String libraryName = "";
			if (rs.next()) {
				libraryName = rs.getString(1);
			} else {
				throw new Exception("This library has no name");
			}

			// Second, get the location of the master server
			rs = stat.executeQuery("SELECT url FROM masterURL");
			String masterURL = "";
			if (rs.next()) {
				masterURL = rs.getString(1);
			} else {
				throw new Exception("Missing location of portal system");
			}

			// Create the arguments for the webservice call
			SOAPBodyElement[] args = null;
			if ("ACE".equals(type)) {
				args = new SOAPBodyElement[7];
				
//				String results = XMLUtils.ElementToString(elems[3]);
//				ByteArrayInputStream feature = new ByteArrayInputStream(results
//						.getBytes());
//				DocumentBuilder db = DocumentBuilderFactory.newInstance()
//						.newDocumentBuilder();
//				Document d = db.parse(feature);
//				feature.close();
				args[5] = new SOAPBodyElement(elems[3]);

//				results = XMLUtils.ElementToString(elems[4]);
//				feature = new ByteArrayInputStream(results.getBytes());
//				d = db.parse(feature);
//				feature.close();
//				feature = null;
				args[6] = new SOAPBodyElement(elems[4]);
			} else {
				args = new SOAPBodyElement[6];
//				String result = XMLUtils.ElementToString(elems[3]);
				args[5] = new SOAPBodyElement(elems[3]);
			}

			// get the name of this batch
			rs = stat.executeQuery("SELECT user, name FROM tasks WHERE id="
					+ task);
			if (rs.next()) {
				String user = rs.getString(1);
				String name = rs.getString(2);
				// System.out.println("'"+name+"'");

				// now finish constructing the response
				args[0] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"user", user));
				args[1] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"name", name));
				args[2] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"type", type)); // type
				args[3] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"library", libraryName));
				args[4] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"file", file)); // file

				// Now send the data
				Service service = new Service();
				Call call = (Call) service.createCall();
				System.out.println(masterURL + "PublishResult");
				call.setTargetEndpointAddress(new java.net.URL(masterURL
						+ "PublishResult"));
				 call.setOperationName("publishResult");
				Vector response = (Vector) call.invoke(args);
				if ((response!= null)&&(response.size() > 0)) {
					Element e = ((SOAPBodyElement) response.get(0)).getAsDOM();
					System.out.println(XMLUtils.ElementToString(e));
				}
				
				stat.executeUpdate("DELETE FROM outstanding_calculations WHERE task_id="+task+" AND file_id="+file);
				
				QueueManager queue = QueueManager.getManager();
				queue.update();

			} else {
				System.out
						.println("INTERNAL ERROR: Can't find name or user of this task");
			}
		} catch (ClassNotFoundException e) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "error",
					"INTERNAL ERROR: database driver missing"));
			e.printStackTrace();
		} catch (SQLException e) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "error",
					"INTERNAL ERROR: SQL Error encountered " + e.getMessage()));
			try {
				stat.execute("UNLOCK TABLES");
			} catch (Exception e1) {
			}
			e.printStackTrace();
		} catch (Exception e) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "error",
					"INTERNAL ERROR: unknown type" + e.getMessage()));
			try {
				stat.execute("UNLOCK TABLES");
			} catch (Exception e1) {
			}
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
