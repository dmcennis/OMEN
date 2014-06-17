/**
 * ApplySettingsSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

import jaudio.worker.ConfigurationReader;

import java.io.IOException;

public class ApplySettingsSoapBindingImpl implements jaudio.worker.web.ApplySettings{
    public int applySettings(int in0, int in1, boolean in2, int in3) throws java.rmi.RemoteException {
        int ret = 0;
        
        ConfigurationReader config = new ConfigurationReader();
        config.init();
        
        config.setPriority(in0);
        config.setMaxCacheSize(in1);
        config.setOnIdle(in2);
        config.setId(in3);
        try {
			config.writeConfig();
		} catch (IOException e) {
			throw new java.rmi.RemoteException("Error Writing Config File: "+e.getMessage());
		}
        
        return ret;    
        }

}
