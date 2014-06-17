/**
 * FileChangeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.web;

public interface FileChangeService extends javax.xml.rpc.Service {
    public java.lang.String getFileChangeAddress();

    public jaudio.web.FileChange getFileChange() throws javax.xml.rpc.ServiceException;

    public jaudio.web.FileChange getFileChange(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
