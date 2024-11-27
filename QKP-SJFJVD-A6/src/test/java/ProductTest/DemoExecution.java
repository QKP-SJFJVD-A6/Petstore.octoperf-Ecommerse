package ProductTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericLibrary.BaseClass;

//@Listeners(GenericLibrary.Listeners.class)
public class DemoExecution extends BaseClass {

	@Test
	public void demoMethodExecution() {
		// Make The TestScript Explicitly Fail
		// Assert.fail();
		Assert.assertEquals(false, true);
		Reporter.log("demoMethodExecution", true);

	}

}
