/**
 * NewFeature.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public interface NewFeature extends java.rmi.Remote {
	
	public static final int FEATURE=0;
	
	public static final int AGGREGATOR=1;
	
    public int putNewFeature(javax.activation.DataHandler in0, java.lang.String in1, int in2) throws java.rmi.RemoteException;
}
