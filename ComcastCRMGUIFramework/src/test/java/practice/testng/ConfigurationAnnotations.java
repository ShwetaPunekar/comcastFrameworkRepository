package practice.testng;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConfigurationAnnotations
{
	//case 1
	//	@BeforeMethod
	//	public void configMethod()
	//	{
	//		System.out.println("Execute case 1");
	//	}

	//case 2
	//	@BeforeMethod
	//	public void configMethod()
	//	{
	//		System.out.println("Execute case 2");
	//	}
	//	@Test
	//	public void createContactTest()
	//	{
	//		System.out.println("Execute CC");
	//	}

//	//case 3
//	@BeforeMethod
//	public void cat()
//	{
//		System.out.println("Execute method 1");
//	}
////	@BeforeMethod
////	public void ball()
////	{
////		System.out.println("Execute method 2");
////	}
////	@BeforeMethod
////	public void apple()
////	{
////		System.out.println("Execute method 3");
////	}
//	
//	@Test
//	public void createContactTest()
//	{
//		System.out.println("Execute test 1");
//	}
//	
//	@Test
//	public void createContactWithPhoneNumber()
//	{
//		System.out.println("Execute test 2");
//	}
	
	//case 4
//	@BeforeMethod
//	public void configBeforeM()
//	{
//		System.out.println("Execute before method");
//	}
//	
//	@AfterMethod
//	public void configAfterM()
//	{
//		System.out.println("Execute after method");
//	}
//	
//	
//	@Test
//	public void createContactTest()
//	{
//		System.out.println("Execute test 1");
//	}
//	
//	@Test
//	public void createContactWithPhoneNumber()
//	{
//		System.out.println("Execute test 2");
//	}
	//==================================================================================================
	
	
	//Before class and After class
	//case 1:
	
	
//	@BeforeClass
//	public void configBC()
//	{
//		System.out.println("Execute Before class");
//	}
//	
//	@BeforeMethod
//	public void configBM()
//	{
//		System.out.println("Execute Before method");
//	}
//	
//	@Test
//	public void createTest()
//	{
//		System.out.println("Execute Test 1");
//	}
	
	
	
	//case 2:
//	@BeforeClass
//	public void configBC()
//	{
//		System.out.println("Execute Before class");
//	}
//	
//	@BeforeMethod
//	public void configBM()
//	{
//		System.out.println("Execute Before method");
//	}
//	
//	@Test
//	public void createTest()
//	{
//		System.out.println("Execute Test 1");
//	}
//	
//	@Test
//	public void createTestData()
//	{
//		System.out.println("Execute Test 2");
//	}
	
	
	//case 3:
//	@BeforeClass
//	public void configBC()
//	{
//		System.out.println("Execute Before class");
//	}
//	
//	@BeforeMethod
//	public void configBM()
//	{
//		System.out.println("Execute Before method");
//	}
//	
//	@Test
//	public void createTest()
//	{
//		System.out.println("Execute Test 1");
//	}
//	
//	@Test
//	public void createTestData()
//	{
//		System.out.println("Execute Test 2");
//	}
//	
//	@AfterMethod
//	public void configAm()
//	{
//		System.out.println("Execute after method");
//	}
//	
//	@AfterClass
//	public void configAC()
//	{
//		System.out.println("Execute after class");
//	}
	
//======================================================================================
	
	//case 1:
	
	@BeforeSuite
	public void configBS()
	{
		System.out.println("Execute before suite");
	}
	
	
	@BeforeClass
	public void configBC()
	{
		System.out.println("Execute Before class");
	}
	
	@BeforeMethod
	public void configBM()
	{
		System.out.println("Execute Before method");
	}
	
	@Test
	public void createTest()
	{
		System.out.println("Execute Test 1");
	}
	
	@Test
	public void createTestData()
	{
		System.out.println("Execute Test 2");
	}
	
	@AfterMethod
	public void configAm()
	{
		System.out.println("Execute after method");
	}
	
	@AfterClass
	public void configAC()
	{
		System.out.println("Execute after class");
	}
	
	
	@AfterSuite
	public void configAS()
	{
		System.out.println("Execute After suite");
	}
	
	
	
	
	
}
