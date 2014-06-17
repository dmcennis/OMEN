/**
 * MusicService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package jaudio.library.web;

public interface MusicService extends javax.xml.rpc.Service {
    public java.lang.String getMusicAddress();

    public jaudio.library.web.Music getMusic() throws javax.xml.rpc.ServiceException;

    public jaudio.library.web.Music getMusic(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
