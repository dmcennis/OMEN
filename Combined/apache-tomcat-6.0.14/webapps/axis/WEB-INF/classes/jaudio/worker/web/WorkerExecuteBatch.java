package jaudio.worker.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;

import jaudio.library.web.Music;
import jaudio.library.web.MusicServiceLocator;
import jaudio.worker.BatchThread;
import jaudio.worker.ConfigurationReader;
import jaudio.worker.XMLBatch;
import jAudioFeatureExtractor.DataModel;
import jAudioFeatureExtractor.ACE.DataTypes.Batch;
import jAudioFeatureExtractor.DataTypes.RecordingInfo;

import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class WorkerExecuteBatch {
	public Element[] execute(Element[] elems) {
		SOAPBodyElement[] ret = new SOAPBodyElement[1];

		try {
			Batch b = jaudio.worker.XMLBatch.XML2Batch(elems[0]);
			int taskID=0;
			
			if (elems[1].getNodeName().equals("task")) {
				NodeList nl = elems[1].getChildNodes();
				for (int i = 0; i < nl.getLength(); ++i) {
					Node n = nl.item(i);
					if (n.getNodeType() == Node.TEXT_NODE) {
						taskID =  Integer.parseInt(((Text) n).getData());
					}
				}
			}else{
				ret = new SOAPBodyElement[1];
				ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","1st argument must be the task id"));
				System.out.println("ERROR: 1st argument must be the task id");
				return ret;
			}

			int fileID = Integer.parseInt((b.getRecording())[0].file_path);

			String featureLocation = "";
			if(java.lang.System.getenv("CATALINA_HOME")!=null){
				featureLocation = java.lang.System.getenv("CATALINA_HOME")+"/webapps/axis/WEB-INF/";
			}
			featureLocation += "features.xml";
			ConfigurationReader config = new ConfigurationReader();
			config.init();
			BatchThread bt = new BatchThread();
			bt.setBatch(b);
			bt.setFile(fileID);
			bt.setTask(taskID);
			bt.setBatch(b);
			bt.setFeatureLocation(featureLocation);
			bt.setPriority(config.getPriority());
			bt.setRunning();
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "OK",
					"Batch process started"));
		} catch (Exception e) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "ERROR",
					e.getMessage()));
			e.printStackTrace();
		}
		return ret;
	}
}
