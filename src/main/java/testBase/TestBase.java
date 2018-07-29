package testBase;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {
	public static final int SUCCESS_RESPONSE_CODE_201=200;
	
	public static Properties prop;
	
	public TestBase() throws IOException {
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
		prop.load(fis);
		
	}
	
//	public static void main(String[] args) throws IOException {
//	TestBase tO=new TestBase();	
//	System.out.println(prop.getProperty("url")+prop.getProperty("apiUrl"));
//	}

}
