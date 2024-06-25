package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author shwet
 * contains Login page element & business lib like login()
 */
public class LoginPage
{								//Rule-1 Create a seperate java class
								//Rule-2 Object creation
	
//	@FindBy(name="user_name")
//	WebElement usernameEdt;
//	
//	@FindBy(name="user_password")
//	WebElement passwordEdt;
//	
//	@FindBy(id="submitButton")
//	WebElement submitBtn;
	
								//Rule-3: Object Initialization
	
//	@FindBy(name="user_name")
//	public WebElement usernameEdt;
//	
//	@FindBy(name="user_password")
//	public WebElement passwordEdt;
//	
//	@FindBy(id="submitButton")
//	public WebElement submitBtn;
	
								//Rule-4: Object Encapsulation
	
	//*here provide one constractor and inside this contractor do the initialization
	//have to pass driver object
	
	//this driver object is local so if we want to access implicitly wait it won't work so create as globle
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		//LoginPage.class instead of this use current object refrence
		PageFactory.initElements(driver, this);
		//#
		
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement submitBtn;

	
	//getter n setter methods
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
									//Object utilization or provide action
	public void loginToApp(String username, String password)
	{
		driver.manage().window().maximize();
		//this is business library this is specific to business Or app
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		submitBtn.click();
	}
	
	/**
	 * Login application based on username, password, url argument
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password)
	{
		driver.manage().window().maximize();
		//this is business library this is specific to business Or app
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		submitBtn.click();
	}
	
}
