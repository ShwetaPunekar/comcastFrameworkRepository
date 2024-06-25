package com.comcast.crm.objectrepositoryutility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchingWindow 
{
	WebDriver driver;
	public SwitchingWindow(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement searchTxtFiled;
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	
	public WebElement getSearchTextF()
	{
		return searchTxtFiled;
	}
	public WebElement getSearchBtn()
	{
		return searchBtn;
	}
	
	
	//=============
//	public void dynamicEle(String orgName)
//	{
//		String xp="//a[text()='"+orgName+"']";
//		
//		
//	}
}
