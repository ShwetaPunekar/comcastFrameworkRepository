package FrameWork;
/**
 * test class for contact module
 * @author shwet
 *
 */

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;

import testng.com.crm.generic.baseclassutility.BaseClass;

public class CodingStandards extends BaseClass
{
	//scenarios
	/**
	 *  Scenario : Login()==> NavigateContact==>CreateContact==>Verify
	 */
	
	
	@Test
	public void searchContact()
	{	//Instead of using // this use /*............*/ to write command line
		/* Step-1 : Login to app*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "admin", "admin");
	
	}
}
