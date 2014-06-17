/**
 * ApplySettingsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

public class ApplySettingsServiceLocator extends org.apache.axis.client.Service implements jaudio.worker.web.ApplySettingsService {

    public ApplySettingsServiceLocator() {
    }


    public ApplySettingsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ApplySettingsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ApplySettings
    private java.lang.String ApplySettings_address = "http://127.0.0.1:8080/axis/services/ApplySettings";

    public java.lang.String getApplySettingsAddress() {
        return ApplySettings_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ApplySettingsWSDDServiceName = "ApplySettings";

    public java.lang.String getApplySettingsWSDDServiceName() {
        return ApplySettingsWSDDServiceName;
    }

    public void setApplySettingsWSDDServiceName(java.lang.String name) {
        ApplySettingsWSDDServiceName = name;
    }

    public jaudio.worker.web.ApplySettings getApplySettings() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ApplySettings_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getApplySettings(endpoint);
    }

    public jaudio.worker.web.ApplySettings getApplySettings(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            jaudio.worker.web.ApplySettingsSoapBindingStub _stub = new jaudio.worker.web.ApplySettingsSoapBindingStub(portAddress, this);
            _stub.setPortName(getApplySettingsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setApplySettingsEndpointAddress(java.lang.String address) {
        ApplySettings_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (jaudio.worker.web.ApplySettings.class.isAssignableFrom(serviceEndpointInterface)) {
                jaudio.worker.web.ApplySettingsSoapBindingStub _stub = new jaudio.worker.web.ApplySettingsSoapBindingStub(new java.net.URL(ApplySettings_address), this);
                _stub.setPortName(getApplySettingsWSDDServiceName());
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
        if ("ApplySettings".equals(inputPortName)) {
            return getApplySettings();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ApplySettings", "ApplySettingsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ApplySettings", "ApplySettings"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ApplySettings".equals(portName)) {
            setApplySettingsEndpointAddress(address);
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
