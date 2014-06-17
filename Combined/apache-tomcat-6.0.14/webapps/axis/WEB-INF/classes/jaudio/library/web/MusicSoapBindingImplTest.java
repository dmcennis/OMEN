package jaudio.library.web;

import javax.activation.DataHandler;

import junit.framework.TestCase;

public class MusicSoapBindingImplTest extends TestCase {
	MusicSoapBindingImpl msbi = new MusicSoapBindingImpl();
	/*
	 * Test method for 'jaudio.library.web.MusicSoapBindingImpl.getMusic(int)'
	 */
	public void testGetMusic() throws Exception{
		DataHandler dh = msbi.getMusic(115);
		assertFalse(null==dh);
	}

}
