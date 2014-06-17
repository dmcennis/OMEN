package jaudio.worker.web;

import java.io.File;
import java.net.URL;
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

import junit.framework.TestCase;

public class WorkerExecuteBatchTest extends TestCase {

	WorkerExecuteBatch web;
	SOAPBodyElement[] batchFileElement;
	
	protected void setUp() throws Exception {
		super.setUp();
		web = new WorkerExecuteBatch();
		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document d = db.parse(new File("BatchFile.xml"));
		NodeList nl = d.getChildNodes();
		Element e = null;
		for (int i = 0; i < nl.getLength(); ++i) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl2 = n.getChildNodes();
				for (int j = 0; j < nl2.getLength(); ++j) {
					Node n2 = nl2.item(j);
					if (n2.getNodeType() == Node.ELEMENT_NODE) {
						e = (Element) n2;
						break;
					}
				}
				break;
			}
		}
		batchFileElement = new SOAPBodyElement[] {
				new SOAPBodyElement(e),
				new SOAPBodyElement(XMLUtils.StringToElement("", "task", "0")) };
	}

	/*
	 * Test method for 'jaudio.worker.web.WorkerExecuteBatch.execute(Element[])'
	 */
	public void testExecuteLocal() throws Exception {
		Element[] ret = web.execute(batchFileElement);
		assertTrue(ret != null);
		assertTrue(ret.length == 1);
		assertEquals("OK", ret[0].getLocalName());
	}
	
	public void testExecuteRemote() throws Exception {
		Service service = new Service();
		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress( new URL("http://127.0.0.1:8080/axis/services/WorkerExecuteBatch") );
		Vector ret = (Vector)call.invoke(batchFileElement);
		assertTrue(ret != null);
		assertTrue(ret.size()==1);
		assertEquals("Test Failed: "+((SOAPBodyElement)(ret.get(0))).getAsString(),"OK",((SOAPBodyElement)(ret.get(0))).getLocalName());
	}

}
