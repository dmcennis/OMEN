/**
 * FileChangeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.web;

public class FileChangeServiceLocator extends org.apache.axis.client.Service implements jaudio.web.FileChangeService {

    public FileChangeServiceLocator() {
    }


    public FileChangeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FileChangeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FileChange
    private java.lang.String FileChange_address = "http://127.0.0.1:8080/axis/services/FileChange";

    public java.lang.String getFileChangeAddress() {
        return FileChange_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FileChangeWSDDServiceName = "FileChange";

    public java.lang.String getFileChangeWSDDServiceName() {
        return FileChangeWSDDServiceName;
    }

    public void setFileChangeWSDDServiceName(java.lang.String name) {
        FileChangeWSDDServiceName = name;
    }

    public jaudio.web.FileChange getFileChange() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FileChange_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFileChange(endpoint);
    }

    public jaudio.web.FileChange getFileChange(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            jaudio.web.FileChangeSoapBindingStub _stub = new jaudio.web.FileChangeSoapBindingStub(portAddress, this);
            _stub.setPortName(getFileChangeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFileChangeEndpointAddress(java.lang.String address) {
        FileChange_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (jaudio.web.FileChange.class.isAssignableFrom(serviceEndpointInterface)) {
                jaudio.web.FileChangeSoapBindingStub _stub = new jaudio.web.FileChangeSoapBindingStub(new java.net.URL(FileChange_address), this);
                _stub.setPortName(getFileChangeWSDDServiceName());
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
        if ("FileChange".equals(inputPortName)) {
            return getFileChange();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:FileChange", "FileChangeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:FileChange", "FileChange"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FileChange".equals(portName)) {
            setFileChangeEndpointAddress(address);
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
