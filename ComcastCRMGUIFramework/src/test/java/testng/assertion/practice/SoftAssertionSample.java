package testng.assertion.practice;

import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionSample {

	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		SoftAssert assertObj=new SoftAssert();
		
		assertObj.assertEquals("Home", "Home999");
		
		System.out.println("Step-3");
		assertObj.assertEquals("Title", "Title");
		System.out.println("Step-4");
		//print exception its mandatory to print
		assertObj.assertAll();
		
		
		System.out.println(mtd.getName()+" Test End");
	}

	@Test
	public void verifyHomePageLogo(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");

		System.out.println("Step-1");
		System.out.println("Step-2");
		SoftAssert assertObj=new SoftAssert();
		assertObj.assertTrue(true);
		System.out.println("Step-3");
		System.out.println("Step-4");
		//print exception its mandatory to print
				assertObj.assertAll();
		System.out.println(mtd.getName()+" Test End");
	}
}
