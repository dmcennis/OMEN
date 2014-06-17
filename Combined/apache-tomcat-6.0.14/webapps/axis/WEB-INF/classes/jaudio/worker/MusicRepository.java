/**
 * 
 */
package jaudio.worker;

import jaudio.library.web.Music;
import jaudio.library.web.MusicServiceLocator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.TreeSet;

import javax.activation.DataHandler;

/**
 * @author mcennis
 *
 */
public class MusicRepository {

	static MusicRepository singleton = null;
	
	String repositoryLocation;
	
	long totalSize;
	
	Hashtable<String,File> number2file;
	
	Hashtable<Long,String> lastModified2number;
	
	Hashtable<String,Long> number2lastModified;
	
	TreeSet<Long> lastModified;
	
	ConfigurationReader config;
	
	private MusicRepository(){
		number2file = new Hashtable<String,File>();
		lastModified2number = new Hashtable<Long,String>();
		number2lastModified = new Hashtable<String,Long>();
		lastModified = new TreeSet<Long>();
		repositoryLocation = "";
		if(System.getenv("CATALINA_HOME")!=null){
			repositoryLocation = java.lang.System.getenv("CATALINA_HOME")
			+ File.separator + "webapps" + File.separator + "axis"
			+ File.separator + "WEB-INF" + File.separator;
		}
		repositoryLocation += "files";
		File files = new File(repositoryLocation);
		if(files.isDirectory()){
			File[] music = files.listFiles();
			totalSize=0;
			for(int i=0;i<music.length;++i){
				System.out.println("Adding file "+music[i].getName());
				String name = music[i].getName();
				long date = music[i].lastModified();
				number2file.put(name,music[i]);
				lastModified2number.put(date,name);
				number2lastModified.put(name,date);
				lastModified.add(date);
				totalSize += music[i].length();
			}
		}else{
			totalSize=-1;
		}
	}
	
	public static MusicRepository get(){
		if(singleton==null){
			singleton = new MusicRepository();
		}
		return singleton;
	}
	
	public File getFile(String name) throws Exception{
		
		if(!number2file.containsKey(name)){
			System.out.println("File not in repository - adding "+name);
			addFile(name);	
		}else{
			System.out.println("File "+name+" found - using cached copy");
			updateTime(name);
		}
		return number2file.get(name);
	}
	
	protected void addFile(String name) throws Exception{
		config = new ConfigurationReader();
		config.init();
		MusicServiceLocator locator = new MusicServiceLocator();
		locator.setMusicEndpointAddress(config.getLibraryAddress()+"Music");
		Music music = locator.getMusic();
		DataHandler dh = (DataHandler)music.getMusic(Integer.parseInt(name));
		
		if(dh == null){
			System.out.println("getMusic failed to return a file");
			throw new Exception("No music returned to analyze");
		}
		InputStream streamingMusicData = dh.getInputStream();
		File endpoint = new File(repositoryLocation+File.separator+name);
		OutputStream musicFile = new FileOutputStream(endpoint);
		byte[] buff = new byte[10240];
		while(streamingMusicData.available()>0){
			int read = streamingMusicData.read(buff);
			musicFile.write(buff,0,read);
		}
		musicFile.close();
		streamingMusicData.close();

		while(((totalSize+endpoint.length())>config.maxCacheSize)&&(lastModified.size()>0)){
			System.out.println("lastModified:"+lastModified.first());
			System.out.println("lastModified2number:"+lastModified2number.get(lastModified.first()));
			File old = number2file.get(lastModified2number.get(lastModified.first()));
			String number = lastModified2number.get(lastModified.first());
			totalSize -= old.length();
			number2file.remove(lastModified2number.get(lastModified.first()));
			lastModified2number.remove(lastModified.first());
			lastModified.remove(number2lastModified.get(number));
			number2lastModified.remove(number);
			old.delete();
		}
		
		number2file.put(name,endpoint);
		long time = endpoint.lastModified();
		lastModified2number.put(time,name);
		number2lastModified.put(name,time);
		lastModified.add(time);
		totalSize += endpoint.length();
	}
	
	protected void updateTime(String name){
		long oldTime = number2lastModified.get(name);
		long newTime = (new Date()).getTime();
		lastModified.remove(oldTime);
		lastModified.add(newTime);
		number2lastModified.put(name,newTime);
		lastModified2number.remove(oldTime);
		lastModified2number.put(newTime, name);
	}
	
	public void clearCache(){
		File cache = new File(repositoryLocation);
		File[] files = cache.listFiles();
		for(int i=0;i<files.length;++i){
			files[i].delete();
		}
		totalSize=0;
		number2file.clear();
		lastModified2number.clear();
		lastModified.clear();
	}
	
}
