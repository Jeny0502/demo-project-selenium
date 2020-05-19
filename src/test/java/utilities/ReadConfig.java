package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ReadConfig {

	public FileInputStream file;
	public Properties prop;

	public String getConfigProperties(String propertyName) throws IOException {
		file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\Config.properties");
		prop = new Properties();
		prop.load(file);

		return prop.getProperty(propertyName);
	}
	
	
}
