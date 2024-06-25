package practice.testng;

import org.testng.annotations.Test;

public class SampleTestTestNGBasicTopics
{
	
//	@Test(priority = 1)
//	public void createContactTest()
//	{
//		System.out.println("Execute test");
//	}
//	
//	@Test(priority = 2)
//	public void modifyContactTest()
//	{
//		System.out.println("Modify");
//	}
//	
//	@Test(priority = 3)
//	public void deleteContactTest()
//	{
//		System.out.println("Delete");
//	}
	
	
//	@Test
//	public void createContactTest()
//	{
//		System.out.println("Execute test");
//	}
//	
//	@Test
//	public void modifyContactTest()
//	{
//		System.out.println("Modify");
//	}
//	
//	@Test
//	public void deleteContactTest()
//	{
//		System.out.println("Delete");
//	}
//	
	
	
//	@Test
//	public void createContactTest()
//	{
//		System.out.println("Execute test ==>HDFC");
//	}
//	
//	@Test
//	public void modifyContactTest()
//	{
//		System.out.println("Execute test ==>HDFC");
//		System.out.println("Modify ==>HDFC->ICICI");
//	}
//	
//	@Test
//	public void deleteContactTest()
//	{
//		System.out.println("Execute test ==>HDFC");
//		System.out.println("Delete ==>ICICI");
//	}
	
//==============================================================================================//
	//dependsOnMethod
	
//	@Test
//	public void createContactTest()
//	{
//		System.out.println("Execute test ==>HDFC");
//	}
//	
//	@Test(dependsOnMethods = "createContactTest")
//	public void modifyContactTest()
//	{
//		//System.out.println("Execute test ==>HDFC");
//		System.out.println("Modify ==>HDFC->ICICI");	
//		
//	}
//	
//	@Test(dependsOnMethods = "modifyContactTest")
//	public void deleteContactTest()
//	{
//		//System.out.println("Execute test ==>HDFC");
//		System.out.println("Delete ==>ICICI");
//	}
//================================================================================================//
	
	//Invocation count
	
	@Test(invocationCount = 10)
	public void createdOrderTest()
	{
		System.out.println("Executed createOderTest==>123");
	}
	
	@Test(enabled = false)
	public void billingOnOrderTest()
	{
		System.out.println("Executed billingOrderTest==>123");
	}
	
	
	
}
