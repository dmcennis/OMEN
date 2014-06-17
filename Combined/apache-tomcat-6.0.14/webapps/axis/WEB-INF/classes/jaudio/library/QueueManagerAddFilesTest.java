package jaudio.library;

import junit.framework.TestCase;

public class QueueManagerAddFilesTest extends TestCase {

	/*
	 * Test method for 'jaudio.library.QueueManager.addFileRequests(String[], int)'
	 */
	public void testAddFileRequests() throws Exception{
		String fileid[] = new String[]{"0","1","2","3"};
		int taskid = 0;
		QueueManager qm = QueueManager.getManager();
		qm.addFileRequests(fileid,taskid);
	}

}
