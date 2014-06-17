/**
 * ResetSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.worker.web;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import jaudio.library.web.LibraryNotifyAnalysisFailure;
import jaudio.library.web.LibraryNotifyAnalysisFailureService;
import jaudio.library.web.LibraryNotifyAnalysisFailureServiceLocator;
import jaudio.worker.BatchThread;
import jaudio.worker.ConfigurationReader;
import jaudio.worker.MusicRepository;

public class ResetSoapBindingImpl implements jaudio.worker.web.Reset{
    public void cancelAnalysis() throws java.rmi.RemoteException {
   		BatchThread.alterExecutionList(BatchThread.CLEAR,null);
		try {
			ConfigurationReader config = new ConfigurationReader();
			config.init();
			LibraryNotifyAnalysisFailureServiceLocator lnafsl = new LibraryNotifyAnalysisFailureServiceLocator();
			lnafsl.setLibraryNotifyAnalysisFailureEndpointAddress(config.getLibraryAddress()+"LibraryNotifyAnalysisFailure");
			LibraryNotifyAnalysisFailure lnaf = lnafsl.getLibraryNotifyAnalysisFailure();
			lnaf.notifyAnalysisFailure(config.getId(),LibraryNotifyAnalysisFailure.EXPLICIT_CANCEL);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage());
		}
  	    }

    public void reset() throws java.rmi.RemoteException {
    		MusicRepository music = MusicRepository.get();
    		music.clearCache();
       		BatchThread.alterExecutionList(BatchThread.CLEAR,null);
       	    		
    		try {
				ConfigurationReader config = new ConfigurationReader();
				config.init();
				LibraryNotifyAnalysisFailureServiceLocator lnafsl = new LibraryNotifyAnalysisFailureServiceLocator();
				lnafsl.setLibraryNotifyAnalysisFailureEndpointAddress(config.getLibraryAddress()+"LibraryNotifyAnalysisFailure");
				LibraryNotifyAnalysisFailure lnaf = lnafsl.getLibraryNotifyAnalysisFailure();
				lnaf.notifyAnalysisFailure(config.getId(),LibraryNotifyAnalysisFailure.EXPLICIT_CANCEL);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
    }

}
