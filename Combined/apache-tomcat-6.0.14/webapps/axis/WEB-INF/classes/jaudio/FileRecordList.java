package jaudio;

import java.io.Serializable;
import java.util.Vector;

public class FileRecordList implements Serializable {
	static final long serialVersionUID=1;
	
	private Vector<FileRecord> records;
	
	public FileRecordList(){
		records = new Vector<FileRecord>();
	}
	
	public FileRecord[] getRecords(){
		return records.toArray(new FileRecord[]{});
	}
	
	public FileRecord getRecords(int i){
		return records.get(i);
	}
	
	public void setRecords(FileRecord[] fr){
		records.clear();
		for(int i=0;i<fr.length;++i){
			records.add(fr[i]);
		}
	}
	
	public void setRecords(int i,FileRecord fr){
		records.set(i,fr);
	}
	public void setRecords(FileRecord fr,int i){
		records.set(i,fr);
	}
	
	
	public void addRecord(){
		FileRecord tmp = new FileRecord();
		tmp.setArrayPlace(records.size());
		records.add(tmp);
	}
	
	public void removeRecord(int i){
		records.remove(i);
	}
	
	static public void main(String[] args){
		FileRecordList frl = new FileRecordList();
		frl.addRecord();
		System.out.println("Size: "+frl.getRecords().length);
		(frl.getRecords(0)).setFileId(0);
		frl.addRecord();
		System.out.println(frl.getRecords(0).getGenre());
		System.out.println(frl.getRecords(0).getArrayPlace());
		System.out.println(frl.getRecords(1).getArrayPlace());
	}
	
}
