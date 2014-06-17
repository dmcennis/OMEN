/**
 * 
 */
package jaudio.library.web;

import javax.activation.DataContentHandler;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import junit.framework.TestCase;

/**
 * @author mcennis
 *
 */
public class LibraryNewFeatureSoapBindingImplTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(LibraryNewFeatureSoapBindingImplTest.class);
	}

	/*
	 * Test method for 'jaudio.library.web.LibraryNewFeatureSoapBindingImpl.putNewFeature(DataHandler, String)'
	 */
	public void testPutNewFeature() throws Exception{
		LibraryNewFeatureSoapBindingImpl imp = new LibraryNewFeatureSoapBindingImpl();
		DataHandler dh = new DataHandler(new FileDataSource("libraryconfig.xml"));
		String name = "test.name.feature";
		assertEquals(0,imp.putNewFeature(dh,name,NewFeature.FEATURE));
	}
	
	public void testWebServicePutNewFeature() throws Exception{
		NewFeatureServiceLocator nfsl = new NewFeatureServiceLocator();
		NewFeature nf = nfsl.getLibraryNewFeature();
		DataHandler dh = new DataHandler(new FileDataSource("libraryconfig.xml"));
		String name = "test.name.feature2";
		assertEquals(0,nf.putNewFeature(dh,name,NewFeature.FEATURE));
	}
	
	public void testWebServicePutNewAggregator() throws Exception{
		NewFeatureServiceLocator nfsl = new NewFeatureServiceLocator();
		NewFeature nf = nfsl.getLibraryNewFeature();
		DataHandler dh = new DataHandler(new FileDataSource("libraryconfig.xml"));
		String name = "test.name.agg1";
		assertEquals(0,nf.putNewFeature(dh,name,NewFeature.AGGREGATOR));
	}

}
