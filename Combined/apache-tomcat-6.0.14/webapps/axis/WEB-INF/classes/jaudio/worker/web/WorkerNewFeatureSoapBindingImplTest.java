package jaudio.worker.web;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import junit.framework.TestCase;

public class WorkerNewFeatureSoapBindingImplTest extends TestCase {

	WorkerNewFeatureSoapBindingImpl test;
	
	protected void setUp() throws Exception {
		super.setUp();
		test = new WorkerNewFeatureSoapBindingImpl();
	}

	public void testbuildFileName() throws Exception{
		String base = "base.test.case";
		File ret;
		ret = test.buildFileName(base);
		assertEquals("/Network/Servers/borges.mt.lan/Volumes/home/mcennis/Documents/workspace/WorkerService/plugin/base/test/case.class",ret.getPath());
	}
	
	public void testAddFeature() throws Exception{
		DataHandler dh = new DataHandler(new FileDataSource(new File("testFeature.class")));
		String name = "testFeature";
		assertEquals(0,test.putNewFeature(dh,name,0));
		File plugin = new File("plugin/testFeature.class");
		assertTrue(plugin.exists());
//		plugin.delete();
	}
	
	public void testAddAggregator() throws Exception{
		DataHandler dh = new DataHandler(new FileDataSource(new File("testFeature.class")));
		String name = "my.testAggregator";
		assertEquals(0,test.putNewFeature(dh,name,1));
		File plugin = new File("plugin/my/testAggregator.class");
		assertTrue(plugin.exists());
//		plugin.delete();
		
	}
	
	public void testServiceNewFeature() throws Exception{
		NewFeatureServiceLocator nfsl = new NewFeatureServiceLocator();
		nfsl.setWorkerNewFeatureEndpointAddress("http://127.0.0.1:8080/axis/services/WorkerNewFeature");
		NewFeature nf = nfsl.getWorkerNewFeature();
		DataHandler dh = new DataHandler(new FileDataSource(new File("testFeature.class")));
		String name = "testFeature";
		assertEquals(0,nf.putNewFeature(dh,name,0));
	}
}
