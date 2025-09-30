package hrm.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigpropertiesReader {

	private Properties prop;
	
	public Properties init_Properties() throws Throwable {
		
		prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(".//config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
}
