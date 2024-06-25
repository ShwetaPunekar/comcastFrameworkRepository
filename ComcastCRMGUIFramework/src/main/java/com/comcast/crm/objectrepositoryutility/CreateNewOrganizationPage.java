package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage 
{
	//Obj Initialization
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	//Obj Identification
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(name="button")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(id="phone")
	private WebElement phoneNum;
	
	
	
	
	//Obj Encapsulation
	public WebElement getOrgNameEdit()
	{
		return orgNameEdit;
	}
	 
	public WebElement getSaveBtn()
	{
		return saveBtn;
	}
	
	public WebElement getIndustryName()
	{
		return industryDD;
	}
	
	public WebElement getPhoneNum()
	{
		return phoneNum;
	}
	
	
	
	
	// Obj Utilization
	public void createOrg(String orgName)
	{
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String Industry)
	{
		orgNameEdit.sendKeys(orgName);
		Select sel=new Select(industryDD);
		sel.selectByVisibleText(Industry);
		saveBtn.click();
	}
	
	public void enterPhoneNum(String orgName, String PhoneNumber)
	{
		orgNameEdit.sendKeys(orgName);
		phoneNum.sendKeys(PhoneNumber);
		saveBtn.click();
	}
}
