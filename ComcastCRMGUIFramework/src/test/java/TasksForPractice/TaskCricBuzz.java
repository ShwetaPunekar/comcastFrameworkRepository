package TasksForPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Driver;

public class TaskCricBuzz {

	public static void main(String[] args) throws SQLException 
	{
		Connection con=null;
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		try {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskcricbuzz", "root", "root");

		Statement stat = con.createStatement();


		driver.get("https://www.cricbuzz.com/live-cricket-scorecard/87528/afg-vs-uga-5th-match-group-c-icc-mens-t20-world-cup-2024");
		
		// Runs
		List<WebElement> Runs = driver.findElements
				(By.xpath("//span[text()='Afghanistan Innings']/ancestor::div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr']/descendant::div[contains(@class,'cb-col cb-col-8 text-right text-bold') and not(contains(.,'R'))]"));



		Iterator<WebElement> i = Runs.iterator();
		for (WebElement Run : Runs) 
		{
			String text = Run.getText();
			int n=Integer.parseInt(text);
			if(n<=30)
			{
				String name="//span[contains(text(),'Afghanistan Innings')]/ancestor::div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr']/descendant::div[.='"+text+"' and contains(@class,'cb-col cb-col-8 text-right text-bold')]/preceding-sibling::div[@class='cb-col cb-col-25 ']";
				String strikeRate="//span[contains(text(),'Afghanistan Innings')]/ancestor::div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr']/descendant::div[.='"+text+"' and contains(@class,'cb-col cb-col-8 text-right text-bold')]/following-sibling::div[@style='padding-right:10px;']/following-sibling::div[@class='cb-col cb-col-8 text-right']";
				String Names = driver.findElement(By.xpath(name)).getText();
				String SR = driver.findElement(By.xpath(strikeRate)).getText();
//								System.out.println(Names);
//								System.out.println(text);
//								System.out.println(SR);

				String Query= "insert into task values('"+Names+"' , '"+text+"' , '"+SR+"')";
				int set = stat.executeUpdate(Query);	
			}
		}
		String Query1 = " select * from task where text > 10";
		ResultSet Data = stat.executeQuery(Query1);
		while(Data.next())
		{
			
			System.out.println( Data.getString(1) + " "+Data.getString(2) + " "+
					Data.getString(3)  );
		}
	
		
		}
		catch(Exception e) {}
		finally {
		con.close();
		System.out.println("====Connection closed====");
		driver.quit();
		}
		
	}

}
