package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//looking at this automation Engg will get to know what kind of element it is
	//this element being used by multiple engg
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath = "//td[@valign=\"bottom\" and @onmouseover=\"fnDropDownUser(this,'ondemand_sub','~shwe@gmail.com`');\"]")
	private WebElement adminBtn;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutBtn;
	
	
	
	public WebElement getOrgLink()
	{
		return orgLink;
	}
	public WebElement getContactLink()
	{
		return contactLink;
	}
	
	public WebElement getMoreLink()
	{
		return moreLink;
	}
	
	public WebElement getCampaignsLink()
	{
		return campaignsLink;
	}
	
	public WebElement getAdminBtn()
	{
		return adminBtn;
	}
	public WebElement getsignOutBtn()
	{
		return signOutBtn;
	}
	
	//business methods
	public void navigateToCampagin()
	{
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignsLink.click();
	}
	
	public void logOut() 
	{
		Actions act1 = new Actions(driver);
		act1.moveToElement(adminBtn).perform();
		signOutBtn.click();
	}
}
