package jaudio.web;

import jaudio.EscapeCharacters;
import junit.framework.TestCase;

public class TestEscapeCode extends TestCase {

	
	FileChangeSoapBindingImpl fcsbi = new FileChangeSoapBindingImpl();
	public void testFileChangeBasic(){
		 String[] test = new String[]{"This is a test"};
		 String[] check = new String[]{"This is a test"};
		 EscapeCharacters.EscapeCharacters(test);
		 assertEquals(check[0],test[0]);
	}
	
	public void testFileChangeSingle(){
		String[] test = new String[]{"This is a ' test"};
		String[] check = new String[]{"This is a \\' test"};
		EscapeCharacters.EscapeCharacters(test);
		assertEquals(check[0],test[0]);
	}
	
	public void testFileChangeDouble(){
		String[] test= new String[]{"This is a '' test"};
		String[] check = new String[]{"This is a \\'\\' test"};
		EscapeCharacters.EscapeCharacters(test);
		assertEquals(check[0],test[0]);
	}
	
	public void testFileChangeMultiple(){
		String[] test = new String[]{"This is a ' test","This is a ' test"};
		String[] check = new String[]{"This is a \\' test","This is a \\' test"};
		EscapeCharacters.EscapeCharacters(test);
		for(int i=0;i<test.length;++i){
			assertEquals("test "+i,check[i],test[i]);
		}
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestEscapeCode.class);
	}

}
