package jaudio.library;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jAudioFeatureExtractor.DataModel;
import jAudioFeatureExtractor.Updater;
import jAudioFeatureExtractor.ACE.DataTypes.Batch;

public class BatchThread implements Runnable, Updater, Serializable {

	static final long serialVersionUID = 1;
	
	private Batch b;

	private boolean done = false;

	private boolean bad = false;

	private int fileMax = 0;

	private int filePos = 0;

	private int overallMax = 1;

	private int overallPos = 1;

	private String user;

	private String name;
	
	private String library;
	
	private String featureLocation;

	public String getFeatureLocation() {
		return featureLocation;
	}

	public void setFeatureLocation(String featureLocation) {
		this.featureLocation = featureLocation;
	}

	public void run() {
		try {
			// set up for executing batch
			DataModel dm = new DataModel(featureLocation,null);
			ByteArrayOutputStream keys = new ByteArrayOutputStream();
			ByteArrayOutputStream values = new ByteArrayOutputStream();
			dm.featureKey = keys;
			dm.featureValue = values;
			b.setDataModel(dm);
			// System.out.println("Starting thread");
			String type;
			if (b.getOutputType() == 0) {
				type = "ACE";
			} else {
				type = "ARFF";
			}
			
			//execute batch
			b.execute();
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(
					"http://localhost:8080/axis/services/PublishResult"));

			
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		
			SOAPBodyElement[] input = null;
			if(type.equals("ACE")){
				ByteArrayInputStream key = new ByteArrayInputStream(
						((ByteArrayOutputStream) dm.featureKey).toByteArray());
				ByteArrayInputStream value = new ByteArrayInputStream(
						((ByteArrayOutputStream) dm.featureValue).toByteArray());
				input = new SOAPBodyElement[6];
//				System.out.println("Feature Key");
//				System.out.println(((ByteArrayOutputStream)dm.featureKey).toString());
//				System.out.println("Feature Value");
//				System.out.println(((ByteArrayOutputStream)dm.featureValue).toString());
				Document keyDoc = db.parse(key);
				Document valueDoc = db.parse(value);
				input[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "user",
						user));
				input[1] = new SOAPBodyElement(XMLUtils.StringToElement("","name",name));
				input[2] = new SOAPBodyElement(XMLUtils.StringToElement("","type","ACE"));
				input[3] = new SOAPBodyElement(XMLUtils.StringToElement("","library",library));
				input[4] = new SOAPBodyElement(keyDoc.getDocumentElement());
				
				input[5] = new SOAPBodyElement(valueDoc.getDocumentElement());
				
			}else{
				input = new SOAPBodyElement[5];
				input[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "user",
						user));
				input[1] = new SOAPBodyElement(XMLUtils.StringToElement("","name",name));
				input[2] = new SOAPBodyElement(XMLUtils.StringToElement("","type","ARFF"));
				input[3] = new SOAPBodyElement(XMLUtils.StringToElement("","library",library));
				input[4] = new SOAPBodyElement(XMLUtils.StringToElement("","data",dm.featureValue.toString()));
			}
			
			Vector elems = (Vector) call.invoke(input);
			SOAPBodyElement elem = null;
			for (int i = 0; i < elems.size(); ++i) {
				elem = (SOAPBodyElement) elems.get(i);
				System.out.println(XMLUtils.ElementToString(elem.getAsDOM()));
			}

			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
			bad = true;
		}
		done = true;
	}

	public void setBatch(Batch b) {
		this.b = b;
	}

//	public void setDataModel(DataModel d) {
//		dm = d;
//	}

	public synchronized boolean isDone() {
		return done;
	}

	public synchronized boolean isBad() {
		return bad;
	}

	public void announceUpdate(int arg0, int arg1) {
		filePos = arg1;
		overallPos = arg0;

	}

	public void announceUpdate(int arg0) {
		filePos = arg0;
	}

	public void setNumberOfFiles(int arg0) {
		overallMax = arg0;

	}

	public void setFileLength(int arg0) {
		fileMax = arg0;
	}

	public void setRunning() {
		Thread t = new Thread(this);
		t.start();
	}

	public int getFilePos() {
		return filePos;
	}

	public int getFileMax() {
		return fileMax;
	}

	public int getOverallPos() {
		return overallPos;

	}

	public int getOverallMax() {
		return overallMax;
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public void setUser(String s) {
		user = s;
	}

	public String getUser() {
		return user;
	}
	
	public void setLibrary(String s){
		library = s;
	}
	
	public String getLibrary(){
		return library;
	}

	protected static Element[] findDataSet(Node e) {
		Vector<Element> elem = new Vector<Element>();
		NodeList nl = e.getChildNodes();

		for (int i = 0; i < nl.getLength(); ++i) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl2 = n.getChildNodes();
				for (int j = 0; j < nl2.getLength(); ++j) {
					Node n2 = nl2.item(j);
					if ((n2.getNodeType() == Node.ELEMENT_NODE)
							&& (n2.getNodeName().equals("data_set"))) {
						elem.add((Element) n2);
					}
				}
			}
		}
		System.out.println(elem.size());
		return elem.toArray(new Element[] {});
	}

}
