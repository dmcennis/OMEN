package jaudio.web;

import jaudio.FileRecord;
import junit.framework.TestCase;

public class FileChangeSoapBindingImplTest extends TestCase {

	FileRecord[] test;

	FileChangeSoapBindingImpl fcsbi;

	int result;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(FileChangeSoapBindingImplTest.class);
	}

	public void setUp() {
		test = new FileRecord[] { new FileRecord() };
		test[0]
				.setGenre("jazz");
		test[0].setTrack("Linus & Lucy");
		test[0].setAlbum("Joe Cool's Blues");
		test[0].setArtist("Wynton Marsalis");
		test[0].setType("stereo mp3 160kbps");
		fcsbi = new FileChangeSoapBindingImpl();
	}

	/*
	 * Test method for
	 * 'jaudio.web.FileChangeSoapBindingImpl.loadFileRecord(FileRecord[],
	 * String)'
	 */
	public void testLoadFileRecord() throws Exception {
		int[] ret = fcsbi.loadFileRecord(test, "mcennis' iTunes");
		assertTrue(ret != null);
		result = ret[0];
		test[0].setFileId(result);
		assertTrue(ret[0] > 0);
		System.out.println("File ID: " + ret[0]);
		
		// Start test for deleting file;
		int ret1 = fcsbi.deleteFileRecord(new int[]{ret[0]},"mcennis' iTunes");
		assertEquals(0,ret1);
	}

//	public void testChangeFileRecordSimple() throws Exception {
//		FileRecord[] test1 = new FileRecord[] { new FileRecord() };
//		test1[0]
//				.setPath("/Network/Servers/borges.mt.lan/Volumes/home/mcennis/Music/iTunes/iTunes Music/Sarah McLachlan/Afterglow/10 Dirty Little Secret.mp3");
//		test1[0].setTrack("Dirty Little Secret");
//		test1[0].setAlbum("Afterglow");
//		test1[0].setArtist("Sarah McLachlan");
//		test1[0].setType("stereo mp3 160kpbs");
//		test1[0].setFileId(115);
//		int ret = fcsbi.changeFileRecord(test1, "mcennis' iTunes");
//		assertEquals(0, ret);
//	}

	public void testLoadFileRecordRemote() throws Exception {
		FileChangeService broker = new FileChangeServiceLocator();
		FileChange proxy = broker.getFileChange();

		int[] ret = proxy.loadFileRecord(test, "mcennis' iTunes");
		assertTrue(ret != null);
		result = ret[0];
		test[0].setFileId(result);
		assertTrue(ret[0] > 0);
		System.out.println("File ID: " + ret[0]);
		
		
		// Start test for deleting file;
		int ret1 = proxy.deleteFileRecord(new int[]{ret[0]},"mcennis' iTunes");
		assertEquals(0,ret1);
	}

//	public void testChangeFileRecordRemote() throws Exception {
//		FileChangeService broker = new FileChangeServiceLocator();
//		FileChange proxy = broker.getFileChange();
//		FileRecord[] test1 = new FileRecord[] { new FileRecord() };
//		test1[0]
//				.setPath("/Network/Servers/borges.mt.lan/Volumes/home/mcennis/Music/iTunes/iTunes Music/Sarah McLachlan/Afterglow/10 Dirty Little Secret.mp3");
//		test1[0].setTrack("Dirty Little Secret");
//		test1[0].setAlbum("Afterglow");
//		test1[0].setArtist("Sarah McLachlan");
//		test1[0].setType("stereo mp3 160kpbs");
//		test1[0].setFileId(115);
//		int ret = proxy.changeFileRecord(test1, "DDMAL");
//		assertEquals(0, ret);
//	}


}
