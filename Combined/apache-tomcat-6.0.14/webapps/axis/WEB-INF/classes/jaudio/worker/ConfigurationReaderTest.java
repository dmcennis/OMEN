package jaudio.worker;

import junit.framework.TestCase;

public class ConfigurationReaderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'jaudio.worker.ConfigurationReader.init(String)'
	 */
	public void testInit() {
		ConfigurationReader test = new ConfigurationReader();
		test.init();
		assertEquals("http://miles.local:8080/axis/services/",test.libraryAddress);
	}

}
