/**
 * MusicSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public class MusicSoapBindingSkeleton implements jaudio.library.web.Music, org.apache.axis.wsdl.Skeleton {
    private jaudio.library.web.Music impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getMusic", _params, new javax.xml.namespace.QName("", "getMusicReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:LibraryGetFile", "getMusic"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getMusic") == null) {
            _myOperations.put("getMusic", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getMusic")).add(_oper);
    }

    public MusicSoapBindingSkeleton() {
        this.impl = new jaudio.library.web.MusicSoapBindingImpl();
    }

    public MusicSoapBindingSkeleton(jaudio.library.web.Music impl) {
        this.impl = impl;
    }
    public javax.activation.DataHandler getMusic(int in0) throws java.rmi.RemoteException
    {
        javax.activation.DataHandler ret = impl.getMusic(in0);
        return ret;
    }

}
