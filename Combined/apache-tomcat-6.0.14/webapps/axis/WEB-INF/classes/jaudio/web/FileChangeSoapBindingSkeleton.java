/**
 * FileChangeSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.web;

public class FileChangeSoapBindingSkeleton implements jaudio.web.FileChange, org.apache.axis.wsdl.Skeleton {
    private jaudio.web.FileChange impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://jaudio", "FileRecord"), jaudio.FileRecord[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("loadFileRecord", _params, new javax.xml.namespace.QName("urn:FileChange", "loadFileRecordReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:FileChange", "loadFileRecord"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("loadFileRecord") == null) {
            _myOperations.put("loadFileRecord", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("loadFileRecord")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://jaudio", "FileRecord"), jaudio.FileRecord[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("changeFileRecord", _params, new javax.xml.namespace.QName("urn:FileChange", "changeFileRecordReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:FileChange", "changeFileRecord"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("changeFileRecord") == null) {
            _myOperations.put("changeFileRecord", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("changeFileRecord")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteFileRecord", _params, new javax.xml.namespace.QName("urn:FileChange", "deleteFileRecordReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:FileChange", "deleteFileRecord"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteFileRecord") == null) {
            _myOperations.put("deleteFileRecord", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteFileRecord")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:FileChange", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listFileRecord", _params, new javax.xml.namespace.QName("urn:FileChange", "listFileRecordReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://jaudio", "FileRecord"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:FileChange", "listFileRecord"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listFileRecord") == null) {
            _myOperations.put("listFileRecord", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listFileRecord")).add(_oper);
    }

    public FileChangeSoapBindingSkeleton() {
        this.impl = new jaudio.web.FileChangeSoapBindingImpl();
    }

    public FileChangeSoapBindingSkeleton(jaudio.web.FileChange impl) {
        this.impl = impl;
    }
    public int[] loadFileRecord(jaudio.FileRecord[] in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        int[] ret = impl.loadFileRecord(in0, in1);
        return ret;
    }

    public int changeFileRecord(jaudio.FileRecord[] in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        int ret = impl.changeFileRecord(in0, in1);
        return ret;
    }

    public int deleteFileRecord(int[] in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        int ret = impl.deleteFileRecord(in0, in1);
        return ret;
    }

    public jaudio.FileRecord[] listFileRecord(java.lang.String in0) throws java.rmi.RemoteException
    {
        jaudio.FileRecord[] ret = impl.listFileRecord(in0);
        return ret;
    }

}
