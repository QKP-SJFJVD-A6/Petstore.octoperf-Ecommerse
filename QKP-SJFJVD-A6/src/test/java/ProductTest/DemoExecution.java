package ProductTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericLibrary.BaseClass;

//@Listeners(GenericLibrary.Listeners.class)
public class DemoExecution extends BaseClass {

	@Test(dataProvider = "registerData")
	public void demoMethodExecution(int id,String name,String dept) {

	
		System.out.println("-------------");
		System.out.println(id);
		System.out.println(name);
		System.out.println(dept);
		System.out.println("-------------");
		
		// Make The TestScript Explicitly Fail
		// Assert.fail();
		// Assert.assertEquals(false, true);
		Reporter.log("demoMethodExecution", true);

	}

}
