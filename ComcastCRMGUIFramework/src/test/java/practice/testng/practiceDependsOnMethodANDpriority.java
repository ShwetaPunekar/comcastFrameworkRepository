package practice.testng;

import org.testng.annotations.Test;

public class practiceDependsOnMethodANDpriority
{
	@Test(priority = 0)
	public void isCheck()
	{
		System.out.println("Apple");
	}
	
	@Test(priority= 1, dependsOnMethods = "isCheck")
	public void isChecking()
	{
		System.out.println("Banana");
	}
	
	@Test(priority = 2, dependsOnMethods ="random" )
	public void as()
	{
		System.out.println("Cat");
	}
	
	@Test(priority = 3, dependsOnMethods = "isCheck")
	public void zas()
	{
		System.out.println("Dog");
	}
	
	@Test(priority = 0, dependsOnMethods =  "isCheck" )
	public void isChecked()
	{
		System.out.println("Elephant");
	}
	
	@Test(priority = 1)
	public void random()
	{
		System.out.println("Fan");
	}

}
