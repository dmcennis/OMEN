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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jaudio.library.BatchThread;
import jaudio.library.ConfigurationReader;
import jaudio.library.EscapeCharacters;
import jaudio.library.QueueManager;
import jaudio.library.XMLBatch;
import jAudioFeatureExtractor.ACE.DataTypes.Batch;
import jAudioFeatureExtractor.DataTypes.RecordingInfo;

import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.apache.axis.message.SOAPBodyElement;

/**
 * Simple message-style service sample.
 */
public class ExecuteBatch {
	/**
	 * Service method, which simply echoes back any XML it receives.
	 * 
	 * @param elems
	 *            an array of DOM Elements, one for each SOAP body element
	 * @return an array of DOM Elements to be sent in the response body
	 */
	public Element[] execute(Element[] elems) {
		SOAPBodyElement[] ret = new SOAPBodyElement[1];
		try {
			Batch b = XMLBatch.XML2Batch(elems[0]);
			String user = "";
			NodeList nl = elems[1].getChildNodes();
			for (int i = 0; i < nl.getLength(); ++i) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.TEXT_NODE) {
					user = ((Text) n).getData();
				}
			}

			RecordingInfo[] ri = b.getRecording();
			String[] file_id = new String[ri.length];
			for (int i = 0; i < file_id.length; ++i) {
				file_id[i] = ri[i].file_path;
			}
			String settings = XMLBatch.XML2Settings(elems[0]);

			Connection conn = null;
			Statement stat = null;
			PreparedStatement insert = null;
			ResultSet rs = null;

			try {
				ConfigurationReader config = ConfigurationReader
						.getConfigurationReader();
				Class.forName(config.getDatabaseDriver());
				conn = DriverManager.getConnection(config.getDatabaseURL());
				stat = conn.createStatement();
				stat.execute("LOCK TABLES tasks WRITE");
				rs = stat.executeQuery("SELECT MAX(id) FROM tasks");
				int taskID = 0;
				if (rs.next()) {
					taskID = rs.getInt(1) + 1;
				}
				insert = conn
						.prepareStatement("INSERT INTO tasks VALUES (?,?,?,?,?)");
				insert.setInt(1, taskID);
				insert.setString(2, user);
				insert.setString(3, settings);
				insert.setInt(4, file_id.length);
				insert.setString(5, b.getName());
				insert.executeUpdate();
				stat.execute("UNLOCK TABLES");

				QueueManager qm = QueueManager.getManager();
				if (qm.addFileRequests(file_id, taskID) == 0) {
					qm.update();
					ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("",
							"OK", "Operations completed sucessfuly"));
				} else {
					ret[0] = new SOAPBodyElement(XMLUtils
							.StringToElement("", "ERROR",
									"adding files to queue threw an exception"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("",
						"ERROR", e.getMessage()));
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
				if (insert != null){
					insert.close();
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}

		} catch (Exception e) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "ERROR",
					e.getMessage()));
			e.printStackTrace();
		}
		return ret;
	}

}
