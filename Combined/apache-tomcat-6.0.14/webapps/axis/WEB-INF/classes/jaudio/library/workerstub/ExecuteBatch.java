package jaudio.library.workerstub;

import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Element;

public class ExecuteBatch {
	public Element[] execute(Element[] elems) {
		
		SOAPBodyElement[] ret = new SOAPBodyElement[1];
		ret[0] = new SOAPBodyElement(XMLUtils.StringToElement("", "OK",
		"Batch process started"));
		return ret;
	}
}
