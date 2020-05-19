package pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ReadConfig;


public class LoginPage {

	WebDriver driver;
	ReadConfig read=new ReadConfig();
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="flow_close_btn_iframe")
	WebElement frameId;
	
	@FindBy(xpath = "//*[@id='closeBtn']")
	WebElement closeButton;
	
	@FindBy(xpath = "//*[@name=\"uid\"]")
	WebElement userId;
	
	@FindBy(xpath = "//*[@name=\"password\"]")
	WebElement password;
	
	@FindBy(xpath = "//*[@name=\"btnLogin\"]")
	WebElement login;

	
	public void getFrameid() {
		driver.switchTo().frame(frameId);
	}
	
	public void closeFrame() {
		closeButton.click();
		driver.switchTo().defaultContent();
	}
	
	public void setUsername(String userid) {
		try {
			userId.sendKeys(read.getConfigProperties(userid));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void setPassword(String pass) {
		try {
			password.sendKeys(read.getConfigProperties(pass));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void submitLogin() {
		login.click();
	}


	
}
