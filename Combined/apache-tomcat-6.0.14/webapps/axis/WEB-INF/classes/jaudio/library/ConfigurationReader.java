package jaudio.library;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class ConfigurationReader {

	static ConfigurationReader singleton=null;
	
	String masterNodeLocation;
	
	String databaseDriver;
	
	String databaseURL;
	
	String libraryName;
	
	private ConfigurationReader() throws Exception{
		try {
			
			// First read the configuration file to figure out how to access the database
			
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			String location = "";
			if(java.lang.System.getenv("CATALINA_HOME")!=null){
				location = java.lang.System.getenv("CATALINA_HOME")+"/webapps/axis/WEB-INF/";
			}
			location += "libraryconfig.xml";
			Document d = db.parse(new File(location));
			Element library = d.getDocumentElement();
			NodeList libraryChildren = library.getChildNodes();
			for(int i=0;i<libraryChildren.getLength();++i){
				Node node = libraryChildren.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE){
					Element child = (Element)node;
					if("database".equals(child.getNodeName())){
						processDatabase(child);
					}
				}
			}
			
			
			// Now access the database to get the rest of the configuration information
			
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			String message = "";
			boolean bad = false;
			
			try {
				Class.forName(databaseDriver);
				conn = DriverManager.getConnection(databaseURL);
				stat = conn.createStatement();

				rs = stat.executeQuery("SELECT url FROM masterURL");
				if(rs.next()){
					masterNodeLocation = rs.getString(1);
				}else{
					bad = true;
					message += "<p>INTERNAL ERROR: Can't find the portal's url";
				}
				
				rs = stat.executeQuery("SELECT name FROM libraryName");
				if(rs.next()){
					libraryName = rs.getString(1);
				}else{
					bad = true;
					message += "<p>INTERNAL ERROR: Can't find the name of this library";
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				bad = true;
				message += "<p>"+e.getMessage();
			} catch (SQLException e) {
				e.printStackTrace();
				bad = true;
				message += "<p>"+e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				bad = true;
				message += "<p>"+e.getMessage();
			}finally{
				if (rs != null){
					try {
						rs.close();
					} catch (SQLException e) {}
				}
				if(stat != null){
					try {
						stat.close();
					} catch (SQLException e) {}
				}
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
			if(bad){
				throw new Exception(message);
			}
			
			
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		private void processDatabase(Element database){
			NodeList databaseChildren = database.getChildNodes();
			for(int i=0;i<databaseChildren.getLength();++i){
				Node node = databaseChildren.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE){
					Element child = (Element)node;
					if("driverName".equals(child.getNodeName())){
						databaseDriver = ((Text)(child.getChildNodes().item(0))).getData();
					}
					if("URLName".equals(child.getNodeName())){
						databaseURL = ((Text)(child.getChildNodes().item(0))).getData();
					}
				}
			}
		}
		
		public String getLibraryName(){
			return libraryName;
		}
		
		public String getMasterURL(){
			return masterNodeLocation;
		}
		
		static public ConfigurationReader getConfigurationReader() throws Exception{
			if(singleton==null){
				singleton = new ConfigurationReader();
			}
			return singleton;
		}

		public String getDatabaseDriver() {
			return databaseDriver;
		}

		public String getDatabaseURL() {
			return databaseURL;
		}


		
	
}
