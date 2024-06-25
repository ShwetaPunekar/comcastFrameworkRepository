package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testng.com.crm.generic.baseclassutility.BaseClass;

//@Listeners(com.comcast.crm.listenerUtility.ListenerImplementation.class)
public class ListenerInvoiceTest1 extends BaseClass
{
	
	@Test
	public void CreateInvoice()
	{
		System.out.println("Execute CreateInvoice");
		//verify
		String ActTitle = driver.getTitle();
		Assert.assertEquals(ActTitle, "Login");
		
		//take screenshot in run time
		
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test
	public void CreateInvoiceWithContactTest()
	{
		System.out.println("Execute CreateInvoiceWithContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
