package GenericLibrary;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		String testmethodname = result.getName();

		// 1.perform Typecasting
		TakesScreenshot tsobj = (TakesScreenshot) BaseClass.static_driver;

		// 2.Call the Screenshot Method
		// 3.Store it In temp File Path
		File temp = tsobj.getScreenshotAs(OutputType.FILE);

		// 4.Create the Permanenet File Path
		File permanenet = new File("./Screenshots/" + testmethodname + ".png");

		// 5.Copy The File From Temp To Permanent.
		try {
			FileHandler.copy(temp, permanenet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("We Will Give Teh Screeshot Implementataion");
	}

}
