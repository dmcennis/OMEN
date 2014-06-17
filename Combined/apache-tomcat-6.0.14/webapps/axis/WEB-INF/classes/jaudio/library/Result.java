package jaudio.library;

import java.io.ByteArrayInputStream;
import java.io.LineNumberReader;
import java.io.Serializable;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Result implements Serializable {

	static final String ACE_KEY_HEADER= "<?xml version=\"1.0\"?>"+System.getProperty("line.separator")+
"<!DOCTYPE feature_key_file ["+System.getProperty("line.separator")+
   "\t<!ELEMENT feature_key_file (comments, feature+)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT comments (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT feature (name, description?, is_sequential, parallel_dimensions)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT name (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT description (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT is_sequential (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT parallel_dimensions (#PCDATA)>"+System.getProperty("line.separator")+
"]>"+System.getProperty("line.separator")+System.getProperty("line.separator");
	
	static final String ACE_VALUE_HEADER="<?xml version=\"1.0\"?>"+System.getProperty("line.separator")+
		"<!DOCTYPE feature_vector_file ["+System.getProperty("line.separator")+
   "\t<!ELEMENT feature_vector_file (comments, data_set+)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT comments (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT data_set (data_set_id, section*, feature*)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT data_set_id (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT section (feature+)>"+System.getProperty("line.separator")+
   "\t<!ATTLIST section start CDATA \"\""+System.getProperty("line.separator")+
                     "\t\tstop CDATA \"\">"+System.getProperty("line.separator")+
   "\t<!ELEMENT feature (name, v+)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT name (#PCDATA)>"+System.getProperty("line.separator")+
   "\t<!ELEMENT v (#PCDATA)>"+System.getProperty("line.separator")+
   "]>"+System.getProperty("line.separator")+System.getProperty("line.separator")+
   "<feature_vector_file>"+System.getProperty("line.separator")+
   "\t<comments></comments>"+System.getProperty("line.separator");

	
	static final String ACE_VALUE_FOOT = System.getProperty("line.separator")+"</feature_vector_file>";
	
	static final long serialVersionUID = 1;
	
	private int id;
	
	private String featureDefinition;
	
	private String featureValues;
	
	private String type;
	
	private String name;

	public String getFeatureDefinition() {
		return featureDefinition;
	}

	public void setFeatureDefinition(String featureDefinition) {
		this.featureDefinition = featureDefinition;
	}

	public String getFeatureValues() {
		return featureValues;
	}

	public void setFeatureValues(String featureValues) {
		this.featureValues = featureValues;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String s){
		type = s;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String s){
		name = s;
	}
	
	public void combineACEResults (String[] key,String[]value)throws Exception{
		if((key==null)||(value==null)){
			System.out.println("INTERNAL ERROR: Value or key is null");
		}else if((key.length==0)||(value.length==0)){
			System.out.println("INTERNAL ERROR: value or key has 0 length:Key("+key.length+") Value("+value.length+")");
		}else if(key.length!=value.length){
			System.out.println("INTERNAL ERROR: key and value lengths do not match: Key("+key.length+") Value("+value.length+")");
		}else{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			featureDefinition = ACE_KEY_HEADER + key[0];
			StringBuffer featureValueSB = new StringBuffer( ACE_VALUE_HEADER);
			for(int i=0;i<value.length;++i){
				ByteArrayInputStream bais = new ByteArrayInputStream(value[i].getBytes());
				Document d = db.parse(bais);
				Element root = d.getDocumentElement();
				NodeList nl = root.getChildNodes();
				for(int j=0;j<nl.getLength();++j){
					Node n = nl.item(j);
					if(n.getNodeName().equals("data_set")){
						featureValueSB.append(XMLUtils.ElementToString((Element)n));
					}
				}
			}
			featureValueSB.append(ACE_VALUE_FOOT);
			featureValues = featureValueSB.toString();
		}
	}
	
	public void combineARFFResults (String[] value)throws Exception{
		if(value.length==0){
			System.out.println("INTERNAL ERROR: no values were passed to combineARFFResults");
		}else{
			StringBuffer header = new StringBuffer();
			LineNumberReader lnr = new LineNumberReader(new StringReader(value[0]));
			String data = lnr.readLine();
			Pattern p = Pattern.compile("[@#].*");
			Matcher m = p.matcher(data);
			Pattern p2 = Pattern.compile("^$");
			Matcher m2 = p2.matcher(data);
			while(m.matches()||m2.matches()){
//				System.out.println(data);
				header.append(data).append(System.getProperty("line.separator"));
				data = lnr.readLine();
				m = p.matcher(data);
				m2 = p2.matcher(data);
			}
			lnr.close();
			for(int i=0;i<value.length;++i){
				lnr = new LineNumberReader(new StringReader(value[i]));
				data = lnr.readLine();
				m = p.matcher(data);
				m2 = p2.matcher(data);
				while(m.matches()||m2.matches()){
					data=lnr.readLine();
					m=p.matcher(data);
					m2=p2.matcher(data);
				}
				while(lnr.ready() && (data!=null)){
					if(data!=null){
//						System.out.println(data);
						header.append(data).append(System.getProperty("line.separator"));
					}
					data=lnr.readLine();
					if(data!=null){
						m = p.matcher(data);
					}
				}
			}
			featureValues = header.toString();
		}
	}
	
	public static void main(String[] args){
		try {
			ConfigurationReader config = ConfigurationReader.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			Result r = new Result();
			Connection conn = DriverManager.getConnection(config.getDatabaseURL());
			Statement stat = conn.createStatement();
			ResultSet rs  =null;
			Vector<String> fv = new Vector<String>();
			rs = stat.executeQuery("SELECT feature_values FROM result_piece WHERE user='mcennis' AND name='arffTest'");
			while(rs.next()){
				fv.add(rs.getString(1));
			}
			r.setFeatureDefinition(null);
			r.combineARFFResults(fv.toArray(new String[]{}));
			System.out.print("'");
			System.out.print(r.featureValues);
			System.out.println("'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
