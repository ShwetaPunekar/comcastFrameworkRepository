package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage 
{
	WebDriver driver;
	public OrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement CreateOrgBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchTxtF;
	@FindBy(id = "bas_searchfield")
	private WebElement searchDD;
	@FindBy(name = "submit")
	private WebElement searchBtn;
	
	
	
	
	
	public WebElement getCreateOrgBtn()
	{
		return CreateOrgBtn;
	}
	
	public WebElement getSearchTxtF()//static element
	{
		return searchTxtF;
	}
	public WebElement getSearchDD()//static element
	{
		return searchDD;
	}
	public WebElement getSearchBtn()
	{
		return searchBtn;
	}
	//business library is not required because its moving from one page to another page
}
