package testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import utilities.ReadConfig;

public class Base {

	public static WebDriver driver;
	public DesiredCapabilities capabilities;
	public ReadConfig readconfig;
	public FirefoxProfile profile;

	Logger log = LoggerFactory.getLogger("DEMO_PROJECT");

	
	public void driverSetup() throws IOException {

		PropertyConfigurator.configure("log4j.properties");

		readconfig = new ReadConfig();
		String browserName = readconfig.getConfigProperties("browser");
		String url = readconfig.getConfigProperties("url");

		capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

		if (browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");

			driver = new ChromeDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			log.debug("Going to load project url");
			driver.get(url);
			driver.manage().window().maximize();
			log.info("Landed successfully in Guru Demo Project Page");

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			driver = new FirefoxDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			log.debug("Going to load project url");
			driver.get(url);
			driver.manage().window().maximize();
			log.info("Landed successfully in Guru Demo Project Page");
		}

	}

	public void takeScreenshot(String testName) throws IOException {
		String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
		String name = testName+time;
		String pathName=System.getProperty("user.dir") + "\\Screenshots\\" + name+".png";
		System.out.println(pathName);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target=new File(pathName);
		FileUtils.copyFile(src, target);
		
		

	}
	

}
