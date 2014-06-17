package jaudio.library;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import jAudioFeatureExtractor.ACE.DataTypes.Batch;
import jAudioFeatureExtractor.DataTypes.RecordingInfo;

public class XMLBatch {
	
	public static Element BatchString2Element(String settings) throws ParserConfigurationException, SAXException, IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(settings.getBytes());
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document d = db.parse(bais);
		NodeList nl = d.getChildNodes();
		Element e=null;
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			if(n.getNodeType()==Node.ELEMENT_NODE){
				NodeList nl2 = n.getChildNodes();
				for(int j=0;j<nl2.getLength();++j){
					Node n2 = nl2.item(j);
					if(n2.getNodeType()==Node.ELEMENT_NODE){
						e = (Element)n2;
						break;
					}
				}
				break;
			}
		}
		return e;
	}
	
	public static Element String2Element(String settings) throws ParserConfigurationException, SAXException, IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(settings.getBytes());
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document d = db.parse(bais);
		Element e=d.getDocumentElement();
		return e;
	}

	public static Batch XML2Batch(Element e) throws Exception{
		Batch ret = new Batch();
		if(!e.getNodeName().equals("batch")){
			throw new Exception("Element should be a 'batch' but is a '"+e.getLocalName()+"'");
		}
		String name = e.getAttribute("ID");
		if(name.equals("")){
			throw new Exception("Batch should have a name - none present");
		}
		ret.setName(name);
		
		String destination = null;
		NodeList nl = e.getChildNodes();
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			if(n.getNodeName()!=null){
				if(n.getNodeName().equals("fileSet")){
					parseFileSet(ret,(Element)n);
				}else if(n.getNodeName().equals("settings")){
					parseSettings(ret,(Element)n);
				}else if(n.getNodeName().equals("destination")){
					if(destination==null){
						destination = parseData((Element)n,"destinationFK");
						ret.setDestinationFK(destination);
					}else{
						destination = parseData((Element)n,"destinationFV");
						ret.setDestinationFV(destination);
					}
				}
			}
		}
		return ret;
	}
	
	public static String XML2Settings(Element e) throws Exception{
		String ret = "";
		if(!e.getNodeName().equals("batch")){
			throw new Exception("Element should be a 'batch' but is a '"+e.getLocalName()+"'");
		}
		String name = e.getAttribute("ID");
		if(name.equals("")){
			throw new Exception("Batch should have a name - none present");
		}
		
		String destination = null;
		NodeList nl = e.getChildNodes();
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			if(n.getNodeName()!=null){
				if(n.getNodeName().equals("settings")){
					ret = XMLUtils.ElementToString((Element)n);
				}
			}
		}
		return ret;
	}
	
	
	protected static void parseFileSet(Batch ret,Element e) throws Exception{
		NodeList nl = e.getChildNodes();
		Vector<String> str = new Vector<String>();
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			if((n.getNodeName()!=null)&&(n.getNodeName().equals("file"))){
				str.add(parseData((Element)n,"file"));
			}
		}
		File[] f= new File[str.size()];
		for(int i=0;i<f.length;++i){
			f[i] = new File(str.get(i));
		}
		RecordingInfo[] ri = new RecordingInfo[f.length];
		for(int i=0;i<f.length;++i){
//			if(f[i].exists()){
//				System.out.println(f[i].getName()+" Exists");
//				ri[i] = new RecordingInfo(f[i].getName(),f[i].getAbsolutePath(),null,false);
//			}else{
				ri[i] = new RecordingInfo(f[i].getName(),f[i].getName(),null,false);
//			}
		}
		ret.setRecording(ri);
	}
	
	protected static void parseSettings(Batch ret, Element e) throws Exception{
		HashMap<String,Boolean> active = new HashMap<String,Boolean>();
		HashMap<String,String[]> attributes = new HashMap<String,String[]>();
		LinkedList<String> aggName = new LinkedList<String>();
		LinkedList<String[]>aggFeature = new LinkedList<String[]>();
		LinkedList<String[]>aggParam = new LinkedList<String[]>();
		
		NodeList nl = e.getChildNodes();
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			String name = n.getNodeName();
			if((name!=null)&&(name.equals("windowSize"))){
				String s = parseData((Element)n,"windowSize");
				try {
					ret.setWindowSize(Integer.parseInt(s));
					
				} catch (NumberFormatException e1) {
					throw new Exception("windowSize must be an Integer but was "+s);
				}
			}else if((name!=null)&&(name.equals("windowOverlap"))){
				String s = parseData((Element)n,"windowOverlap");
				try {
					ret.setWindowOverlap(Double.parseDouble(s));
				} catch (NumberFormatException e1) {
					throw new Exception("windowOverlap must be a double but was "+s);
				}
			}else if((name!=null)&&(name.equals("samplingRate"))){
				String s = parseData((Element)n,"samplingRate");
				try {
					ret.setSamplingRate(Double.parseDouble(s));
				} catch (NumberFormatException e1) {
					throw new Exception("samplingRate must be a double but was "+s);
				}
			}else if((name!=null)&&(name.equals("normalise"))){
				String s = parseData((Element)n,"normalise");
				ret.setNormalise(Boolean.parseBoolean(s));
			}else if((name!=null)&&(name.equals("perWindowStats"))){
				String s = parseData((Element)n,"perWindowStats");
				ret.setPerWindow(Boolean.parseBoolean(s));				
			}else if((name!=null)&&(name.equals("overallStats"))){
				String s = parseData((Element)n,"overallStats");
				ret.setOverall(Boolean.parseBoolean(s));				
			}else if((name!=null)&&(name.equals("outputType"))){
				String s = parseData((Element)n,"outputType");
				if(s.equals("ACE")){
					ret.setOutputType(0);
				}else{
					ret.setOutputType(1);
				}
			}else if((name!=null)&&(name.equals("feature"))){
				parseFeature(active,attributes,(Element)n);
			}else if((name!=null)&&(name.equals("aggregator"))){
				parseAggregator(aggName,aggFeature,aggParam,(Element)n);
			}
		} // for i<nl.length
		ret.setFeatures(active,attributes);
		ret.setAggregators(aggName.toArray(new String[]{}),aggFeature.toArray(new String[][]{}),aggParam.toArray(new String[][]{}));
	}
	
	protected static String parseData(Element e,String name) throws Exception{
		String ret="";
		NodeList nl = e.getChildNodes();
		if(nl.getLength()==0){
			throw new Exception(name+" must have at least 1 child");
		}else if(nl.getLength() > 1){
			throw new Exception(name+"must have exactly 1 text node child - "+nl.getLength()+" children found");
		}
		Node n = nl.item(0);
		if(n.getNodeType() != Node.TEXT_NODE){
			throw new Exception(name+"s only child must be a text node");
		}
		Text t = (Text)n;
		ret = t.getData();
		return ret;
	}
	protected static void parseFeature(HashMap<String,Boolean>active,HashMap<String,String[]>attributes, Element e) throws Exception{
		String key = null;
		boolean on = false;
		Vector<String> att = new Vector<String>();
		NodeList nl = e.getChildNodes();
		if(nl.getLength()<2){
			throw new Exception("Features have at least 2 chuldren, only "+nl.getLength() + " found");
		}
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			String name = n.getNodeName();
			if((name != null)&&(name.equals("name"))){
				key = parseData((Element)n,"name");
			}else if((name != null)&&(name.equals("active"))){
				on = Boolean.parseBoolean(parseData((Element)n,"active"));
			}else if((name!=null)&&(name.equals("attribute"))){
				att.add(parseData((Element)n,"attribute"));
			}
		}
		if(key == null){
			throw new Exception("Every feature must have a name");
		}
		active.put(key,on);
		attributes.put(key,att.toArray(new String[]{}));
	}
	
	protected static void parseAggregator(LinkedList<String> name, LinkedList<String[]> feature, LinkedList<String[]>param, Element e) throws Exception{
		LinkedList<String>tmpFeature = new LinkedList<String>();
		LinkedList<String>tmpParam = new LinkedList<String>();
		NodeList nl = e.getChildNodes();
		for(int i=0;i<nl.getLength();++i){
			Node n = nl.item(i);
			String nodeName = n.getNodeName();
			if("aggregatorName".equals(nodeName)){
				name.add(parseData((Element)n,"aggregatorName"));
			}else if("aggregatorFeature".equals(nodeName)){
				tmpFeature.add(parseData((Element)n,"aggregatorFeature"));
			} else if("aggregatorAttribute".equals(nodeName)){
				tmpParam.add(parseData((Element)n,"aggregatorAttribute"));
			}
		}
		feature.add(tmpFeature.toArray(new String[]{}));
		param.add(tmpParam.toArray(new String[]{}));
	}
	
	public static Element Batch2XML(Batch b) throws Exception{
		Element ret=null;
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc            = builder.newDocument();
		ret = doc.createElement("batch");
		ret.setAttribute("ID",b.getName());
		ret.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t"));
		
		Element fileSet = doc.createElement("fileSet");
		Element file = null;
		RecordingInfo[] ri = b.getRecording();
		for(int i=0;i<ri.length;++i){
			fileSet.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
			file = doc.createElement("file");
			file.appendChild(doc.createTextNode(ri[i].file_path));
			fileSet.appendChild(file);
		}
		fileSet.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t"));
		ret.appendChild(fileSet);
		
		ret.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t"));
		
		Element settings = doc.createElement("settings");

		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
		Element property = doc.createElement("windowSize");
		property.appendChild(doc.createTextNode(Integer.toString(b.getWindowSize())));
		settings.appendChild(property);
		
		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
		property = doc.createElement("windowOverlap");
		property.appendChild(doc.createTextNode(Double.toString(b.getWindowOverlap())));
		settings.appendChild(property);
		
		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
		property = doc.createElement("samplingRate");
		property.appendChild(doc.createTextNode(Double.toString(b.getSamplingRate())));
		settings.appendChild(property);
		
		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
		property = doc.createElement("normalise");
		property.appendChild(doc.createTextNode(Boolean.toString(b.isNormalise())));
		settings.appendChild(property);
		
		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
		property = doc.createElement("perWindowStats");
		property.appendChild(doc.createTextNode(Boolean.toString(b.isPerWindow())));
		settings.appendChild(property);

		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
		property = doc.createElement("overallStats");
		property.appendChild(doc.createTextNode(Boolean.toString(b.isOverall())));
		settings.appendChild(property);

		HashMap<String,Boolean> activeM=b.getActivated();
		HashMap<String,String[]>attributesM=b.getAttributes();
		Set<Map.Entry<String,Boolean>> s = activeM.entrySet();
		for(Iterator<Map.Entry<String,Boolean>> i=s.iterator();i.hasNext();){
			Map.Entry<String,Boolean> m = i.next();
			settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
			Element feature = doc.createElement("feature");
			feature.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t\t"));

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(m.getKey()));
			feature.appendChild(name);		

			feature.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t\t"));			
			Element active = doc.createElement("active");
			active.appendChild(doc.createTextNode(m.getValue().toString()));
			feature.appendChild(active);
			
			String[] attr = attributesM.get(m.getKey());
			for(int j=0;j<attr.length;++j){
				feature.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t\t"));			
				Element attribute = doc.createElement("attribute");
				attribute.appendChild(doc.createTextNode(attr[j]));
				feature.appendChild(attribute);
			}
			feature.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
			settings.appendChild(feature);
		}
		
		// output aggregator entries
		String[] aggName = b.getAggregatorNames();
		String[][] aggFeat = b.getAggregatorFeatures();
		String[][] aggParam = b.getAggregatorParameters();
		for(int i=0;i<aggName.length;++i){
			settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
			Element aggregator = doc.createElement("aggregator");
			aggregator.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t\t"));

			Element name = doc.createElement("aggregatorName");
			name.appendChild(doc.createTextNode(aggName[i]));
			aggregator.appendChild(name);
			
			for(int j=0;j<aggFeat[i].length;++j){
				aggregator.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t\t"));
				Element feature = doc.createElement("aggregatorFeature");
				feature.appendChild(doc.createTextNode(aggFeat[i][j]));
				aggregator.appendChild(feature);
			}
			
			for(int j=0;j<aggParam[i].length;++j){
				aggregator.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t\t"));
				Element parameter = doc.createElement("aggregatorAttribute");
				parameter.appendChild(doc.createTextNode(aggParam[i][j]));
				aggregator.appendChild(parameter);
			}
			aggregator.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t\t"));
			settings.appendChild(aggregator);
			
		}

		settings.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t"));
		ret.appendChild(settings);
		
		Element destination;
		if(b.getDestinationFK()!= null){
			ret.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t"));
			destination = doc.createElement("destination");
			destination.appendChild(doc.createTextNode(b.getDestinationFK()));
			ret.appendChild(destination);
		}
		ret.appendChild(doc.createTextNode(System.getProperty("line.separator")+"\t"));
		destination = doc.createElement("destination");
		destination.appendChild(doc.createTextNode(b.getDestinationFV()));
		ret.appendChild(destination);
		
		ret.appendChild(doc.createTextNode(System.getProperty("line.separator")));
		return ret;
	}
	
	public static void main(String[] args){
		System.out.println("Testing XML->Batch");
		Batch b = new Batch();
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document d = db.parse(new File(args[0]));
			NodeList nl = d.getChildNodes();
			Element e=null;
			for(int i=0;i<nl.getLength();++i){
				Node n = nl.item(i);
				if(n.getNodeType()==Node.ELEMENT_NODE){
					NodeList nl2 = n.getChildNodes();
					for(int j=0;j<nl2.getLength();++j){
						Node n2 = nl2.item(j);
						if(n2.getNodeType()==Node.ELEMENT_NODE){
							e = (Element)n2;
							break;
						}
					}
					break;
				}
			}
			b = XML2Batch(e);
			System.out.println("Name:    "+b.getName());
			System.out.println("winSize: "+b.getWindowSize());
			System.out.println("winOver: "+b.getWindowOverlap());
			System.out.println("sampleR: "+b.getSamplingRate());
			System.out.println("norm:    "+b.isNormalise());
			System.out.println("overall: "+b.isOverall());
			System.out.println("perWind: "+b.isPerWindow());
			System.out.println("outputT: "+b.getOutputType());
			System.out.println("FK     : "+b.getDestinationFK());
			System.out.println("FV     : "+b.getDestinationFV());
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------");
		System.out.println("Testing Batch->XML");
		try {
			Element e = Batch2XML(b);
			System.out.println(XMLUtils.ElementToString(e));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

//	public static Element findBatch(Node d) throws Exception{
//		if((d.getLocalName()!= null)&&(d.getNodeName().equals("batch"))){
//			return (Element)d;
//		}else{
//			NodeList nl = d.getChildNodes();
//			for(int i=0;i<nl.getLength();++i){
//				Element e = findBatch(nl.item(i));
//				if(e != null){
//					return e;
//				}
//			}
//			return null;
//		}
//	}
}
