package com.comcast.hrm.ninja;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class projectCreatedByDB {

	public static void main(String[] args) throws IOException, SQLException, InterruptedException 
	{
		WebDriver driver = new ChromeDriver();

		FileUtility plib = new FileUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		DatabaseUtility dlib = new DatabaseUtility();
		ExcelUtility elib=new ExcelUtility();

		String query = "Insert into project(project_id,created_by,created_on,project_name,status,team_size)values('TY_PROJ_966','Pratyusha','2024-06-03','Vajra_145','OnGoing',08);";
		dlib.getDBConnection("jdbc:mysql://106.51.90.215:3333/Projects", "root@%", "root");
		System.out.println(dlib.excecuteNonSelectQuery(query));
		String query1 = "select * from project;";

		ResultSet res = dlib.executeSelectQuery(query1);
		while (res.next()) 
		{
			System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4) + "\t" + res.getString(5) + "\t" + res.getString(6) + "\t");
		}
		dlib.closeDBConnection();

		String PrjName = "Vajra_145";																																																						

		String URL = plib.getDataFromPropertiesFile("url");
		String USERNAME = plib.getDataFromPropertiesFile("username");
		String PASSWORD = plib.getDataFromPropertiesFile("password");

		driver.get(URL);

		wlib.waitForPageLoad(driver);
		//wlib.maximizeWindow(driver);

		driver.findElement(By.id("username")).sendKeys(USERNAME, Keys.TAB, PASSWORD, Keys.ENTER);

		driver.findElement(By.linkText("Projects")).click();
		WebElement ele = driver.findElement(
				By.xpath("//a[@class='btn btn-primary']/ancestor::div[@class='container-fluid']/descendant::select"));

		wlib.select(ele, "Search by Project Name");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Search by Project Name']")).sendKeys(PrjName);


		WebElement ActualPNameSearch =driver.findElement(By.xpath("//table/descendant::td[2]"));
		String atualData = ActualPNameSearch.getText();

		if (atualData.equals(PrjName)) 
		{


			List<WebElement> all = driver.findElements(By.xpath("//table/descendant::td"));
			for(WebElement ele2:all) 
			{

				System.out.println(ele2.getText());

				int i=1;
				if(all.size()<=6) 
				{
					elib.setDataIntoExcel("Mock", 0, i-1, ele2.getText());
					i++;

				}
			}

			System.out.println(PrjName + "  is created in db and Gui");
		} 
		else 
		{
			System.out.println(PrjName + " Not created");
		}

	}

}
