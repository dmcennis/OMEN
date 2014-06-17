/**
 * NewFeatureService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

public interface NewFeatureService extends javax.xml.rpc.Service {
    public java.lang.String getWorkerNewFeatureAddress();

    public jaudio.worker.web.NewFeature getWorkerNewFeature() throws javax.xml.rpc.ServiceException;

    public jaudio.worker.web.NewFeature getWorkerNewFeature(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
