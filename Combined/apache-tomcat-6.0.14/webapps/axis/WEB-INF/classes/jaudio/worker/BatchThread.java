package jaudio.worker;

import jaudio.library.web.LibraryNotifyAnalysisFailure;
import jaudio.library.web.LibraryNotifyAnalysisFailureServiceLocator;
import jaudio.library.web.Music;
import jaudio.library.web.MusicServiceLocator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jAudioFeatureExtractor.Cancel;
import jAudioFeatureExtractor.DataModel;
import jAudioFeatureExtractor.ExplicitCancel;
import jAudioFeatureExtractor.Updater;
import jAudioFeatureExtractor.ACE.DataTypes.Batch;
import jAudioFeatureExtractor.DataTypes.RecordingInfo;

public class BatchThread implements Runnable, Updater, Serializable {

	static final long serialVersionUID = 1;
	
	public static final int ADD = 0;
	
	public static final int REMOVE = 1;
	
	public static final int CLEAR = 2;
	
	private static Vector<BatchThread> executionList = new Vector<BatchThread>();
	
	private Batch b;

	private boolean done = false;

	private boolean bad = false;

	private int fileMax = 0;

	private int filePos = 0;

	private int overallMax = 1;

	private int overallPos = 1;

	private int task;

	private int file;
	
	private String featureLocation;
	
	private int priority=5;
	
	private Cancel cancel = null;
	
	public BatchThread(){
		BatchThread.alterExecutionList(BatchThread.ADD,this);
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getFeatureLocation() {
		return featureLocation;
	}

	public void setFeatureLocation(String featureLocation) {
		this.featureLocation = featureLocation;
	}

	public void run() {
		try {
			ConfigurationReader config = new ConfigurationReader();
			config.init();
			String library = config.getLibraryAddress();
			MusicRepository repository = MusicRepository.get();
			File location = repository.getFile(Integer.toString(file));
			RecordingInfo[] recordingInfo = new RecordingInfo[1];
			recordingInfo[0] = new RecordingInfo(location.getAbsolutePath());
			b.setRecording(recordingInfo);

			// set up for executing batch
			System.out.println("Starting Execution of Batch");
			DataModel dm = new DataModel(featureLocation,null);
			ByteArrayOutputStream putKeys = new ByteArrayOutputStream();
			ByteArrayOutputStream putValues = new ByteArrayOutputStream();
			dm.featureKey = putKeys;
			dm.featureValue = putValues;
			cancel = dm.cancel_;
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


			// Create a set of elements for communication with library's publish result
			// Order: 
			// task id
			// file id
			// type
			// if ARFF: result Ñ if ACE: feature vector
			// if ACE: feature values
			
			SOAPBodyElement[] input = null;
			if(type=="ACE"){
				input = new SOAPBodyElement[5];
			}else{
				input = new SOAPBodyElement[4];
			}
			input[0] = new SOAPBodyElement(XMLUtils.StringToElement("","task",Integer.toString(task)));
			input[1] = new SOAPBodyElement(XMLUtils.StringToElement("","file",Integer.toString(file)));
			input[2] = new SOAPBodyElement(XMLUtils.StringToElement("","type",type));
			if(type.equals("ACE")){
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				ByteArrayInputStream keysBuffer = new ByteArrayInputStream(
						((ByteArrayOutputStream) dm.featureKey).toByteArray());
				ByteArrayInputStream valuesBuffer = new ByteArrayInputStream(
						((ByteArrayOutputStream) dm.featureValue).toByteArray());
				Document key = db.parse(keysBuffer);
				Document values = db.parse(valuesBuffer);
				input[3] = new SOAPBodyElement(key.getDocumentElement());
				input[4] = new SOAPBodyElement(values.getDocumentElement());
			}else{
				input[3] = new SOAPBodyElement(XMLUtils.StringToElement("","data",dm.featureValue.toString()));
			}
			
			// call publish result			
			Service service = new Service();
			Call call = (Call)service.createCall();
			System.out.println(library+"LibraryPublishResult");
			call.setTargetEndpointAddress(new java.net.URL(library+"LibraryPublishResult"));
			Vector ret = (Vector)call.invoke(input);

			
		} catch(ExplicitCancel e){
			System.out.println("Explicitly killed by library - cleanup taken care of elsewhere");
		}catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
			try {
				ConfigurationReader config = new ConfigurationReader();
				config.init();
				LibraryNotifyAnalysisFailureServiceLocator locator = new LibraryNotifyAnalysisFailureServiceLocator();
				locator.setLibraryNotifyAnalysisFailureEndpointAddress(config.getLibraryAddress()+"LibraryNotifyAnalysisFailure");
				LibraryNotifyAnalysisFailure failure = locator.getLibraryNotifyAnalysisFailure();
				failure.notifyAnalysisFailure(config.getId(),LibraryNotifyAnalysisFailure.EXCEPTION_IN_EXECUTION);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		done = true;
		BatchThread.alterExecutionList(BatchThread.REMOVE,this);
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
		t.setPriority(priority);
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

	public void setTask(int s) {
		task = s;
	}

	public int getTask() {
		return task;
	}

	public void setFile(int i) {
		file = i;
	}

	public int getFile() {
		return file;
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

	protected int addFile(DataHandler dh){
		int ret = 0;
		
		// determine how much space is used.
		
		
		return ret;
	}
	
	public void cancel(){
		if(cancel != null){
			System.out.println("Canceling Execution of Batch");
			cancel.setCancel(true);
		}
	}
	
	public static synchronized void alterExecutionList(int type,BatchThread arg){
		if(type==BatchThread.ADD){
			executionList.add(arg);
		}else if(type==BatchThread.REMOVE){
			executionList.remove(arg);
		}else if(type==BatchThread.CLEAR){
			for(int i=0;i<executionList.size();++i){
				executionList.get(i).cancel();
			}
		}
	}
	
}
