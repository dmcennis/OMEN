/**
 * NewFeatureService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public interface NewFeatureService extends javax.xml.rpc.Service {
    public java.lang.String getLibraryNewFeatureAddress();

    public jaudio.library.web.NewFeature getLibraryNewFeature() throws javax.xml.rpc.ServiceException;

    public jaudio.library.web.NewFeature getLibraryNewFeature(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
