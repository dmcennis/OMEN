/**
 * LibraryNewFeatureSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public class LibraryNewFeatureSoapBindingSkeleton implements jaudio.library.web.NewFeature, org.apache.axis.wsdl.Skeleton {
    private jaudio.library.web.NewFeature impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:LibraryNewFeature", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"), javax.activation.DataHandler.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:LibraryNewFeature", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:LibraryNewFeature", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("putNewFeature", _params, new javax.xml.namespace.QName("urn:LibraryNewFeature", "putNewFeatureReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:LibraryNewFeature", "putNewFeature"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("putNewFeature") == null) {
            _myOperations.put("putNewFeature", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("putNewFeature")).add(_oper);
    }

    public LibraryNewFeatureSoapBindingSkeleton() {
        this.impl = new jaudio.library.web.LibraryNewFeatureSoapBindingImpl();
    }

    public LibraryNewFeatureSoapBindingSkeleton(jaudio.library.web.NewFeature impl) {
        this.impl = impl;
    }
    public int putNewFeature(javax.activation.DataHandler in0, java.lang.String in1, int in2) throws java.rmi.RemoteException
    {
        int ret = impl.putNewFeature(in0, in1, in2);
        return ret;
    }

}
