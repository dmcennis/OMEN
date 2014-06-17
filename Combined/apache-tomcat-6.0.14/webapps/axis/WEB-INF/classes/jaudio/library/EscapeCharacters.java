package jaudio.library;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EscapeCharacters {
    static public void EscapeCharacters(String[] name){
		Pattern p = Pattern.compile("([^']*)['](.*)");
		for(int i=0;i<name.length;++i){
			LineNumberReader source = new LineNumberReader(new StringReader(name[i]));
			String line=null;
			try {
				line = source.readLine();
			} catch (IOException e) {
				System.out.println("Crazy - a string produced an io error");
				e.printStackTrace();
			}
			StringBuffer result = new StringBuffer();
			while(line != null){
				String remain = line;
				Matcher m = p.matcher(remain);
				while(m.matches()){
					if(m.groupCount()!=2){
						System.out.println("Group Count should be 2 but is "+m.groupCount());
					}else{
						result.append(m.group(1));
						result.append("\\'");
						remain = m.group(2);
					}
					m = p.matcher(remain);
				}
				result.append(remain);
				result.append(System.getProperty("line.separator"));
				line = null;
				try {
					line = source.readLine();
				} catch (IOException e) {
					System.out.println("Crazy - a string produced an io error");
					e.printStackTrace();
				}
			}
			name[i] = result.toString();
		}
}

}
