/**
 * ApplySettingsSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

public class ApplySettingsSoapBindingSkeleton implements jaudio.worker.web.ApplySettings, org.apache.axis.wsdl.Skeleton {
    private jaudio.worker.web.ApplySettings impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ApplySettings", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ApplySettings", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ApplySettings", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ApplySettings", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("applySettings", _params, new javax.xml.namespace.QName("urn:ApplySettings", "applySettingsReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:ApplySettings", "applySettings"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("applySettings") == null) {
            _myOperations.put("applySettings", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("applySettings")).add(_oper);
    }

    public ApplySettingsSoapBindingSkeleton() {
        this.impl = new jaudio.worker.web.ApplySettingsSoapBindingImpl();
    }

    public ApplySettingsSoapBindingSkeleton(jaudio.worker.web.ApplySettings impl) {
        this.impl = impl;
    }
    public int applySettings(int in0, int in1, boolean in2, int in3) throws java.rmi.RemoteException
    {
        int ret = impl.applySettings(in0, in1, in2, in3);
        return ret;
    }

}
