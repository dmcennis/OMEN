/**
 * ResetSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

public class ResetSoapBindingSkeleton implements jaudio.worker.web.Reset, org.apache.axis.wsdl.Skeleton {
    private jaudio.worker.web.Reset impl;
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
        };
        _oper = new org.apache.axis.description.OperationDesc("cancelAnalysis", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("urn:Reset", "cancelAnalysis"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("cancelAnalysis") == null) {
            _myOperations.put("cancelAnalysis", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("cancelAnalysis")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("reset", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("urn:Reset", "reset"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("reset") == null) {
            _myOperations.put("reset", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("reset")).add(_oper);
    }

    public ResetSoapBindingSkeleton() {
        this.impl = new jaudio.worker.web.ResetSoapBindingImpl();
    }

    public ResetSoapBindingSkeleton(jaudio.worker.web.Reset impl) {
        this.impl = impl;
    }
    public void cancelAnalysis() throws java.rmi.RemoteException
    {
        impl.cancelAnalysis();
    }

    public void reset() throws java.rmi.RemoteException
    {
        impl.reset();
    }

}
