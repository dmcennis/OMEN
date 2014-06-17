package jaudio.worker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class ConfigurationReader {

	static final String header = "<?xml version=\"1.0\"?>"
			+ System.getProperty("line.separator")
			+ "<!DOCTYPE configuration ["
			+ System.getProperty("line.separator")
			+ "   <!ELEMENT configuration (libraryURL,priority,maxCacheSize,onIdle,id)>"
			+ System.getProperty("line.separator")
			+ "   <!ELEMENT libraryURL (#PCDATA)>"
			+ System.getProperty("line.separator")
			+ "   <!ELEMENT priority (#PCDATA)>"
			+ System.getProperty("line.separator")
			+ "   <!ELEMENT maxCacheSize (#PCDATA)>"
			+ System.getProperty("line.separator")
			+ "   <!ELEMENT onIdle (#PCDATA)>"
			+ System.getProperty("line.separator")
			+ "   <!ELEMENT id (#PCDATA)>"
			+ System.getProperty("line.separator") + "]>"
			+ System.getProperty("line.separator")
			+ System.getProperty("line.separator");

	String libraryAddress;

	int maxCacheSize;

	boolean onIdle;

	int priority;
	
	int id;

	public ConfigurationReader() {
	}

	public void init() {
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			String location = "";
			if (java.lang.System.getenv("CATALINA_HOME") != null) {
				location = java.lang.System.getenv("CATALINA_HOME")
						+ File.separator + "webapps" + File.separator + "axis"
						+ File.separator + "WEB-INF" + File.separator;

			}
			location += "config.xml";
			Document d = db.parse(new File(location));
			Element configurationRoot = d.getDocumentElement();
			NodeList configurationRootChildren = configurationRoot
					.getChildNodes();
			for (int i = 0; i < configurationRootChildren.getLength(); ++i) {
				Node node = configurationRootChildren.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element child = (Element) node;
					if ("libraryURL".equals(child.getNodeName())) {
						libraryAddress = ((Text) child.getChildNodes().item(0))
								.getData();
					} else if ("priority".equals(child.getNodeName())) {
						priority = Integer.parseInt(((Text) child
								.getChildNodes().item(0)).getData());
					} else if ("maxCacheSize".equals(child.getNodeName())) {
						maxCacheSize = Integer.parseInt(((Text) child
								.getChildNodes().item(0)).getData());
					} else if ("onIdle".equals(child.getNodeName())) {
						onIdle = Boolean.parseBoolean(((Text) child
								.getChildNodes().item(0)).getData());
					} else if("id".equals(child.getNodeName())){
						id = Integer.parseInt(((Text) child
								.getChildNodes().item(0)).getData());
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public void setPriority(int p) {
		priority = p;
	}

	public String getLibraryAddress() {
		return libraryAddress;
	}

	public int getPriority() {
		return priority;
	}

	public int getMaxCacheSize() {
		return maxCacheSize;
	}

	public void setMaxCacheSize(int maxCacheSize) {
		this.maxCacheSize = maxCacheSize;
	}

	public boolean isOnIdle() {
		return onIdle;
	}

	public void setOnIdle(boolean onIdle) {
		this.onIdle = onIdle;
	}

	public void writeConfig() throws IOException {
		String location = "";
		if (java.lang.System.getenv("CATALINA_HOME") != null) {
			location = java.lang.System.getenv("CATALINA_HOME")
					+ "/webapps/axis/WEB-INF/";
		}
		location += "config.xml";
		File config = new File(location);
		config.renameTo(new File(location + (new Date()).getTime()));
		FileWriter out = new FileWriter(new File(location));
		out.write(header);
		out.write("<configuration>" + System.getProperty("line.separator"));
		out.write("\t<libraryURL>" + libraryAddress + "</libraryURL>"
				+ System.getProperty("line.separator"));
		out.write("\t<priority>" + Integer.toString(priority) + "</priority>"
				+ System.getProperty("line.separator"));
		out.write("\t<maxCacheSize>" + Integer.toString(maxCacheSize)
				+ "</maxCacheSize>" + System.getProperty("line.separator"));
		out.write("\t<onIdle>" + Boolean.toString(onIdle) + "</onIdle>"
				+ System.getProperty("line.separator"));
		out.write("\t<id>"+Integer.toString(id)+"</id>"+System.getProperty("line.separator"));
		out.write("</configuration>" + System.getProperty("line.separator"));
		out.close();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
