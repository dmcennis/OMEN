package jaudio;

import java.io.File;
import java.io.IOException;

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
	
	String databaseDriver;
	
	String databaseURL;
	
	private ConfigurationReader() throws Exception{
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			String location = "";
			if(java.lang.System.getenv("CATALINA_HOME")!=null){
				location = java.lang.System.getenv("CATALINA_HOME")+"/webapps/axis/WEB-INF/";
			}
			location += "portalconfig.xml";
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
