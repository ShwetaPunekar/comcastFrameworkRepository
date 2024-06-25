package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class organizationInfoPage 
{
	WebDriver driver;
	public organizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industryName;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement phoneNumber;
	

	
	public WebElement getHeaderMsg()
	{
		return headerMsg;
	}
	
	public WebElement getIndustryName()
	{
		return industryName;
	}
	
	public WebElement getPhoneNumber()
	{
		return phoneNumber;
	}
	
	
	
}
