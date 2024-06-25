package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import testng.com.crm.generic.baseclassutility.BaseClass;

public class RetryActivateSim extends BaseClass
{
	//instead of using retry annotation will use retry key
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListener.class)
	public void ActivateSim()
	{
		System.out.println("Execute CreateInvoice");
		//verify
//		String ActTitle = driver.getTitle();
		//compare with null
		Assert.assertEquals("", "Login");
		
		//take screenshot in run time
		
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
