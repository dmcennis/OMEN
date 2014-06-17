/**
 * FileChange.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.web;

public interface FileChange extends java.rmi.Remote {
    public int[] loadFileRecord(jaudio.FileRecord[] in0, java.lang.String in1) throws java.rmi.RemoteException;
    public int changeFileRecord(jaudio.FileRecord[] in0, java.lang.String in1) throws java.rmi.RemoteException;
    public int deleteFileRecord(int[] in0, java.lang.String in1) throws java.rmi.RemoteException;
    public jaudio.FileRecord[] listFileRecord(java.lang.String in0) throws java.rmi.RemoteException;
}
