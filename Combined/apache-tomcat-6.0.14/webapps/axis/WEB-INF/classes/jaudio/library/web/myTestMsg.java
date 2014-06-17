package jaudio.library.web;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class myTestMsg {

	public static String doIt(String[] args) {
		String ret = null;
		
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new URL("http://localhost:8080/axis/services/ExecuteBatch") );
			SOAPBodyElement[] input = new SOAPBodyElement[2];
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
			input[0] = new SOAPBodyElement(e);
			input[1] = new SOAPBodyElement(XMLUtils.StringToElement("","user","mcennis"));
			Vector          elems = (Vector) call.invoke( input );
			SOAPBodyElement elem  = null ;
			elem = (SOAPBodyElement) elems.get(0);
			e    = elem.getAsDOM();
			ret = XMLUtils.ElementToString(e)+System.getProperty("line.separator");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ret = doIt(args);
		System.out.println(ret);

	}

}
