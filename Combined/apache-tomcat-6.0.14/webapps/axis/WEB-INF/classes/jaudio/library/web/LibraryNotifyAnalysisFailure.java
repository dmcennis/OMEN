/**
 * LibraryNotifyAnalysisFailure.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public interface LibraryNotifyAnalysisFailure extends java.rmi.Remote {
	public static final int EXPLICIT_CANCEL = 0;
	
	public static final int EXCEPTION_IN_EXECUTION = 1;
	
	public void notifyAnalysisFailure(int in0, int in1) throws java.rmi.RemoteException;
}
