package jaudio.library;

import junit.framework.TestCase;

public class ConfigurationReaderTest extends TestCase {

		public void testConstructor() throws Exception{
			ConfigurationReader config = ConfigurationReader.getConfigurationReader();
			assertEquals("com.mysql.jdbc.Driver",config.getDatabaseDriver());
			assertEquals("jdbc:mysql://127.0.0.1/library?user=mcennis&password=H311F1r3",config.getDatabaseURL());
		}
}
