/**
 * WorkerNewFeatureSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.utils.XMLUtils;
import org.apache.commons.codec.net.URLCodec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class WorkerNewFeatureSoapBindingImpl implements jaudio.worker.web.NewFeature{
	public static final String HEADER = "<?xml version=\"1.0\"?>"+System.getProperty("line.separator")+
	"<!DOCTYPE featureList ["+System.getProperty("line.separator")+
	"	<!ELEMENT featureList (pluginFolder?,feature+,aggregator*)>"+System.getProperty("line.separator")+
	"	<!ELEMENT pluginFolder (#PCDATA)>"+System.getProperty("line.separator")+
	"	<!ELEMENT feature (class , on?)>"+System.getProperty("line.separator")+
	"	<!ELEMENT class (#PCDATA) >"+System.getProperty("line.separator")+
	"	<!ELEMENT on EMPTY>"+System.getProperty("line.separator")+
	"	<!ELEMENT aggregator (#PCDATA)>"+System.getProperty("line.separator")+
	"]>"+System.getProperty("line.separator")+System.getProperty("line.separator");

   public int putNewFeature(javax.activation.DataHandler in0, java.lang.String in1, int in2) throws java.rmi.RemoteException {
       int ret = 0;
       
       if((in2 != 0)&&(in2 != 1)){
       		throw new java.rmi.RemoteException("Bad type - type must be either feature (0) or aggregator (1), but is "+in2);
       }
       
       try {
			File locationForFeature = buildFileName(in1);
			
			InputStream loadFeature = in0.getInputStream();
			OutputStream writeFeature = new FileOutputStream(locationForFeature);
			byte[] buffer = new byte[10240];
			int bytesRead = loadFeature.read(buffer);
			while(bytesRead > 0){
					writeFeature.write(buffer,0,bytesRead);
					bytesRead = loadFeature.read(buffer);
			}
			updateFeaturesFile(in1,in2);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -2;
		}catch (Exception e){
       		System.out.println(e.getMessage());
       		e.printStackTrace();
       		return -3;
       }
       
       return ret;
    }
   void updateFeaturesFile(String name,int type) throws Exception{
		String ret=HEADER;
	
		String base = System.getenv("CATALINA_HOME");
	if(base != null){
		base += File.separator;
		base += "webapps";
		base += File.separator;
		base += "axis";
		base += File.separator;
		base += "WEB-INF";
		base += File.separator;
	}else{
		base = "";
	}	
	base += "features.xml";
	
	DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	Document d = db.parse(new File(base));
	Element e = d.getDocumentElement();
	
	if(type == 1){
		e.appendChild(d.createTextNode("\t"));
		Element newEntry = d.createElement("aggregator");
		newEntry.appendChild(d.createTextNode(name));
		e.appendChild(newEntry);
		e.appendChild(d.createTextNode(System.getProperty("line.separator")));
	}else if(type == 0 ){
		Element newEntryClass = d.createElement("class");
		newEntryClass.appendChild(d.createTextNode(name));
		Element newEntry = d.createElement("feature");
		newEntry.appendChild(d.createTextNode(System.getProperty("line.separator")+"\t\t"));
		newEntry.appendChild(newEntryClass);
		newEntry.appendChild(d.createTextNode(System.getProperty("line.separator")+"\t"));
		NodeList nl = e.getChildNodes();
		int count = 1;
		Node n = nl.item(0);
		while((count < nl.getLength()) && (!n.getNodeName().equals("aggregator"))){
			n = nl.item(count++);
		}
		if(!n.getNodeName().equals("aggregator")){
			e.appendChild(d.createTextNode("\t"));
			e.appendChild(newEntry);
			e.appendChild(d.createTextNode(System.getProperty("line.separator")));
		}else{
			e.insertBefore(newEntry,n);
			e.insertBefore(d.createTextNode(System.getProperty("line.separator")+"\t"),n);
		}
	}
	
	ret += XMLUtils.ElementToString(e);
	
	File backup = new File(base);
	backup.renameTo(new File(base+(new Date()).getTime()));
	
	FileWriter output = new FileWriter(new File(base));
	output.write(ret);
	output.close();
}


File buildFileName(String name) throws Exception{
		
		URL url = new URL(getPluginFolderURL());
		String base = (new URLCodec()).decode(url.getPath());
		
		String original = name;
		Pattern p = Pattern.compile("([^.]*)\\.(.*)");
		StringBuffer sb = new StringBuffer();
		sb.append(base);
		Matcher m = p.matcher(name);
		
		while(m.matches()){
			sb.append(m.group(1));
			File dir = new File(sb.toString());
			dir.mkdir();
			sb.append(File.separator);
			original = m.group(2);
			m = p.matcher(original);
		}
		sb.append(original);
		sb.append(".class");
		base = sb.toString();
		
		return new File(base);
}


String getPluginFolderURL() throws Exception{
	String base = System.getenv("CATALINA_HOME");
	String ret = "";
	if(base != null){
		base += File.separator;
		base += "webapps";
		base += File.separator;
		base += "axis";
		base += File.separator;
		base += "WEB-INF";
		base += File.separator;
	}else{
		base = "";
	}	
	base += "features.xml";
	
	DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	Document doc = db.parse(base);
	Element root = doc.getDocumentElement();
	NodeList nl = root.getChildNodes();
	for(int i=0;i<nl.getLength();++i){
		Node n = nl.item(i);
		if("pluginFolder".equals(n.getNodeName())){
			ret = ((Text)(n.getFirstChild())).getData();
			break;
		}
	}
	if(ret.equals("")){
		throw new Exception("No plugin folder listed in features.xml file");
	}else{
		return ret;
	}
}
}
