package jaudio.web;

import jaudio.ConfigurationReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 
 * @author Daniel McEnnis
 *
 */
public class PublishResult {

	public Element[] publishResult(Element[] args) {
		System.out.println("Starting publish");
		SOAPBodyElement[] ret = new SOAPBodyElement[1];
		System.out.println("Starting PublishResult");
		if (args.length < 5) {
			ret = new SOAPBodyElement[1];
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "error",
					"Must have at least 4 elements"));
			System.out.println("ERROR: Only " + args.length
					+ " elements reported.  At least 4 needed");
			return ret;
		}
		String user = "";
		if (args[0].getNodeName().equals("user")) {
			NodeList nl = args[0].getChildNodes();
			for (int i = 0; i < nl.getLength(); ++i) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.TEXT_NODE) {
					user = ((Text) n).getData();
				}
			}
		}else{
			ret = new SOAPBodyElement[1];
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","1st argument must be a username"));
			System.out.println("ERROR: 1st argument must be the username");
			return ret;
		}
		
		String name = "";
		if (args[1].getNodeName().equals("name")) {
			NodeList nl = args[1].getChildNodes();
			for (int i = 0; i < nl.getLength(); ++i) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.TEXT_NODE) {
					name = ((Text) n).getData();
				}
			}
		}else{
			ret = new SOAPBodyElement[1];
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","2nd argument must be the name of batch"));
			System.out.println("ERROR: 2nd argument must be the name of the batch");
			return ret;
		}
		
		String type = "";
		if (args[2].getNodeName().equals("type")) {
			NodeList nl = args[2].getChildNodes();
			for (int i = 0; i < nl.getLength(); ++i) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.TEXT_NODE) {
					type = ((Text) n).getData();
				}
			}
		}else{
			ret = new SOAPBodyElement[1];
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","3rd argument must be output type"));
			System.out.println("ERROR: 3rd argument must be the output type");
			return ret;
		}
		
		String library = "";
		if (args[3].getNodeName().equals("library")){
			NodeList nl = args[3].getChildNodes();
			for (int i = 0; i < nl.getLength(); ++i) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.TEXT_NODE) {
					library = ((Text) n).getData();
				}
			}
		}else{
			ret = new SOAPBodyElement[1];
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","4th argument must be library type"));
			System.out.println("ERROR: 4th argument must be the library type");
			return ret;
		}
		
		int file = -1;
		if (args[4].getNodeName().equals("file")){
			NodeList nl = args[4].getChildNodes();
			for (int i = 0; i < nl.getLength(); ++i) {
				Node n = nl.item(i);
				if (n.getNodeType() == Node.TEXT_NODE) {
					try {
						file = Integer.parseInt(((Text) n).getData());
					} catch (NumberFormatException e) {
						ret = new SOAPBodyElement[1];
						ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","5th argument must be an integer :'"+((Text) n).getData()+"'"));
						System.out.println("ERROR: 5th argument must be an integer (file id):'"+((Text) n).getData()+"'");
						return ret;
					}
				}
			}
		}else{
			ret = new SOAPBodyElement[1];
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","5th argument must be the file id"));
			System.out.println("ERROR: 5th argument must be the file id");
			return ret;
		}
		
		
		System.out.println("Processing Library request - "+library);
		InputStreamReader key = null;
		int keyLength = -1;
		InputStreamReader dataSet = null;
		int dataLength = -1;
		int argPlace = 5;
		if(type.equals("ACE")){
			System.out.println("Publishing ACE");
			key = new InputStreamReader(new ByteArrayInputStream(XMLUtils.ElementToString(args[argPlace]).getBytes()));
			keyLength = XMLUtils.ElementToString(args[argPlace]).length();
//			Pattern p = Pattern.compile("\'");
//			Matcher m = p.matcher(key);
//			StringBuffer sb = new StringBuffer();
//			while(m.find()){
//				m.appendReplacement(sb,"`");
//			}
//			m.appendTail(sb);
//			key = sb.toString();
			argPlace++;
			dataSet = new InputStreamReader(new ByteArrayInputStream(XMLUtils.ElementToString(args[argPlace]).getBytes()));
			dataLength = XMLUtils.ElementToString(args[argPlace]).length();
		}else{
			System.out.println("Publishing ARFF");
			Node n = args[argPlace].getFirstChild();
			dataSet = new InputStreamReader(new ByteArrayInputStream(((Text)n).getData().getBytes()));
			dataLength = XMLUtils.ElementToString(args[argPlace]).length();
		}
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement insert = null;
		ResultSet rs = null;
		
		try {
			ConfigurationReader config = ConfigurationReader.getConfigurationReader();
			Class.forName(config.getDatabaseDriver());
			conn = DriverManager.getConnection(config.getDatabaseURL());
			stat = conn.createStatement();

			rs = stat.executeQuery("SELECT id FROM analysis_request WHERE name=\""+name+"\" AND rid=\""+user+"\"");
			if(rs.next()){
				int analysis_request_id = rs.getInt(1);
				insert = conn.prepareStatement("INSERT INTO result (file_id,analysis_request_id,feature_key,feature_values) VALUES (?,?,?,?)");
				insert.clearParameters();
				insert.setInt(1,file);
				insert.setInt(2,analysis_request_id);
				if(type.equals("ACE")){
					insert.setCharacterStream(3,key,keyLength);
				}else{
					insert.setNull(3,Types.LONGVARCHAR,"TEXT");
				}
				insert.setCharacterStream(4,dataSet,dataLength);
				insert.executeUpdate();
				ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","OK","All operations completed succesfully"));
				System.out.println("PublishResult completed successfully");
			}else{
				ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","no record of this query in db for name("+name+") and user("+user+")"));
				System.out.println("query table does not include this query - aborting publishing of results");
			}
			
			
		} catch (SQLException e1) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","SQLException:" +e1.getMessage()));
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error","MySQL Driver not found"));
			e.printStackTrace();
		} catch (Exception e){
			ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("","error",e.getMessage()));
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {}
				rs = null;
			}
			if(stat != null){
				try {
					stat.close();
				} catch (SQLException e) {}
				stat = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {}
				conn = null;
			}
		}

		return ret;
	}
	
}
