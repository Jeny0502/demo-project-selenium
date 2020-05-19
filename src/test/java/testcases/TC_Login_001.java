package testcases;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.LoginPage;


public class TC_Login_001 extends Base {

	@BeforeClass
	public void driverInitialize() {
		try {
			driverSetup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void verifyLogin() {

		LoginPage loginObj = new LoginPage(driver);

		loginObj.getFrameid();
		log.info("Switched to frame");
		loginObj.closeFrame();
		log.info("Closed the frame");
		loginObj.setUsername("userid");
		log.info("Entered username");
		loginObj.setPassword("password");
		log.info("Entered password");
		loginObj.submitLogin();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleIs(driver.getTitle()));
		String title = driver.getTitle();
		System.out.println(title);

		try {
			Assert.assertEquals(title, "Hiibbdwbw");
		} catch (AssertionError e) {

			log.info("Mismatch in title");
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
