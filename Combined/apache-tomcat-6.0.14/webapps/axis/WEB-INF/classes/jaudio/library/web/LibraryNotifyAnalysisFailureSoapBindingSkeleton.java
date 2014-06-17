/**
 * LibraryNotifyAnalysisFailureSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public class LibraryNotifyAnalysisFailureSoapBindingSkeleton implements jaudio.library.web.LibraryNotifyAnalysisFailure, org.apache.axis.wsdl.Skeleton {
    private jaudio.library.web.LibraryNotifyAnalysisFailure impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:LibraryNotifyAnalysisFailure", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:LibraryNotifyAnalysisFailure", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("notifyAnalysisFailure", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("urn:LibraryNotifyAnalysisFailure", "notifyAnalysisFailure"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("notifyAnalysisFailure") == null) {
            _myOperations.put("notifyAnalysisFailure", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("notifyAnalysisFailure")).add(_oper);
    }

    public LibraryNotifyAnalysisFailureSoapBindingSkeleton() {
        this.impl = new jaudio.library.web.LibraryNotifyAnalysisFailureSoapBindingImpl();
    }

    public LibraryNotifyAnalysisFailureSoapBindingSkeleton(jaudio.library.web.LibraryNotifyAnalysisFailure impl) {
        this.impl = impl;
    }
    public void notifyAnalysisFailure(int in0, int in1) throws java.rmi.RemoteException
    {
        impl.notifyAnalysisFailure(in0, in1);
    }

}
