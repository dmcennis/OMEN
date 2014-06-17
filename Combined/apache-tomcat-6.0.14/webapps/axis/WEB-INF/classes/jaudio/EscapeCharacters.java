package jaudio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EscapeCharacters {
    static public void EscapeCharacters(String[] name){
		Pattern p = Pattern.compile("([^']*)['](.*)");
		for(int i=0;i<name.length;++i){
			StringBuffer result = new StringBuffer();
			String remain = name[i];
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
			name[i] = result.toString();
		}
}

}
