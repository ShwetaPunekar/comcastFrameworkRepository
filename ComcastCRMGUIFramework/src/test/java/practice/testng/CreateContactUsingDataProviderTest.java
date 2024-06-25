package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactUsingDataProviderTest 
{
//	@Test(dataProvider = "getData")
//	public void createContactTest(String FirstName, String LastName)
//	{
//		System.out.println("FirstName :"+FirstName+", LastName :"+LastName);
//	}
//
//	@DataProvider
//	public Object[][] getData()
//	{
//		Object[][] objArr=new Object[3][2];
//		objArr[0][0]="Deepak";
//		objArr[0][1]="HR";
//		
//		objArr[1][0]="Sam";
//		objArr[1][1]="HD";
//		
//		objArr[2][0]="Jhon";
//		objArr[2][1]="Smith";
//		
//		return objArr;
//	}
	
	@Test(dataProvider = "getDatas")
	public void createContactTest(String FirstName, String LastName, long phoneNumber)
	{
		System.out.println("FirstName :"+FirstName+", LastName :"+LastName+", PhoneNumber :"+phoneNumber);
	}

	
	
	//@DataProvider
	@DataProvider(name="getDatas")
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][3];
		objArr[0][0]="Deepak";
		objArr[0][1]="HR";
		objArr[0][2]=9876543211l;
		
		objArr[1][0]="Sam";
		objArr[1][1]="HD";
		objArr[1][2]=2345643232l;
		
		objArr[2][0]="Jhon";
		objArr[2][1]="Smith";
		objArr[2][2]=4567245662l;
		
		return objArr;
	}
}
