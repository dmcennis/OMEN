/**
 * ResetService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

public interface ResetService extends javax.xml.rpc.Service {
    public java.lang.String getResetAddress();

    public jaudio.worker.web.Reset getReset() throws javax.xml.rpc.ServiceException;

    public jaudio.worker.web.Reset getReset(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
