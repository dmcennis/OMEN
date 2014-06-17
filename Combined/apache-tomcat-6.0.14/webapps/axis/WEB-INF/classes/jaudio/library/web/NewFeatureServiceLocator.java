/**
 * NewFeatureServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public class NewFeatureServiceLocator extends org.apache.axis.client.Service implements jaudio.library.web.NewFeatureService {

    public NewFeatureServiceLocator() {
    }


    public NewFeatureServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NewFeatureServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LibraryNewFeature
    private java.lang.String LibraryNewFeature_address = "http://127.0.0.1:8080/axis/services/LibraryNewFeature";

    public java.lang.String getLibraryNewFeatureAddress() {
        return LibraryNewFeature_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LibraryNewFeatureWSDDServiceName = "LibraryNewFeature";

    public java.lang.String getLibraryNewFeatureWSDDServiceName() {
        return LibraryNewFeatureWSDDServiceName;
    }

    public void setLibraryNewFeatureWSDDServiceName(java.lang.String name) {
        LibraryNewFeatureWSDDServiceName = name;
    }

    public jaudio.library.web.NewFeature getLibraryNewFeature() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LibraryNewFeature_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLibraryNewFeature(endpoint);
    }

    public jaudio.library.web.NewFeature getLibraryNewFeature(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            jaudio.library.web.LibraryNewFeatureSoapBindingStub _stub = new jaudio.library.web.LibraryNewFeatureSoapBindingStub(portAddress, this);
            _stub.setPortName(getLibraryNewFeatureWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLibraryNewFeatureEndpointAddress(java.lang.String address) {
        LibraryNewFeature_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (jaudio.library.web.NewFeature.class.isAssignableFrom(serviceEndpointInterface)) {
                jaudio.library.web.LibraryNewFeatureSoapBindingStub _stub = new jaudio.library.web.LibraryNewFeatureSoapBindingStub(new java.net.URL(LibraryNewFeature_address), this);
                _stub.setPortName(getLibraryNewFeatureWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("LibraryNewFeature".equals(inputPortName)) {
            return getLibraryNewFeature();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:LibraryNewFeature", "NewFeatureService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:LibraryNewFeature", "LibraryNewFeature"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LibraryNewFeature".equals(portName)) {
            setLibraryNewFeatureEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
