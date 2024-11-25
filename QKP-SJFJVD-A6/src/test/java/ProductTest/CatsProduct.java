package ProductTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CatsProduct {

	WebDriver driver;
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	
	@BeforeSuite
	public void suiteSetup()
	{
		//cREATE THE SPARKREPORT
		spark = new ExtentSparkReporter("./AdvanceReports/CatsProductReport.html");
		
		//Configure the SparkReport Information
		spark.config().setDocumentTitle("Regression Testing for Birds Product");
		spark.config().setReportName("Cat Product Test Report");
		spark.config().setTheme(Theme.STANDARD);
		
		//Create the Extent Report
		report = new ExtentReports();
		
		//Attach the SparkReport and ExtentReport
		report.attachReporter(spark);
		
		//Configure the system into in ExtentRport
		report.setSystemInfo("DeviceName :", "Durga");
		report.setSystemInfo("OperatingSystem:", "WINDOWSS 11");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("BrowserVersion: ", "chrome-128.0.661.85");
	}
	
	@AfterSuite
	public void suiteTerminateSetup()
	{
		//Flush the Report Information
		report.flush();
	}
	
	

	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup(String browser)
	{
		// Start the ExtentTest for this class
       test = report.createTest("Browser Setup");
        
		Reporter.log("browser launched successfully", true);
		
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//Step 1.1: Launch the browser - Chrome
			driver = new ChromeDriver();
		}
		else if(browser.equals("edge"))
		{
			//Step 1.1: Launch the browser -Edge
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browser.equals("firefox"))
		{
			//Step 1.1: Launch the browser -Firefox
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}
		
		//Maximize the Browser
		driver.manage().window().maximize();
		test.pass("Browser launched and maximized successfully.");
	}
	
	@BeforeMethod
	public void signIn()
	{
		
		test = report.createTest("Sign In");
		 
		Reporter.log("Sign In Execution Started Successfully", true);
		
		//Step 1.2: Navigate to application
		driver.get("https://petstore.octoperf.com/actions/Catalog.action");
		
		//Step 5: click on "Sign in" link
		driver.findElement(By.linkText("Sign In")).click();
		
		//Step 5.1: Enter the User ID
		driver.findElement(By.name("username")).sendKeys("DB@gmail.com");
		//Step 5.2: Enter the Password
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("DB!@#$%");
		
		//Step 5.3: Click on "Login" Button
		driver.findElement(By.name("signon")).click();
		
		Reporter.log("Sign In Done Successfully", true);
		test.pass("User signed in successfully.");
	}
	
	@AfterMethod
	public void signOut()
	{
		test = report.createTest("Sign Out");
		
		Reporter.log("Sign Out started Successfully", true);
		//Click on "SignOut" button
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		Reporter.log("Sign Out Done Successfully", true);
		test.pass("User signed out successfully.");
	}
	
	@AfterClass
	public void browserTerminate()
	{
		test = report.createTest("Browser Terminate");
	  
		driver.quit();
		Reporter.log("Browser Terminated Successfully", true);
		test.pass("Terminated Successfully.");
	}
	
	@Test
	public void addFirstProduct() throws IOException, InterruptedException
	{
		
		test = report.createTest("Add First Product to Cart");
		
		Reporter.log("----------------ADD PRODUCT----------------", true);
		 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Implicit wait set to 20 seconds.", true);
		
		driver.findElement(By.xpath("//div[@id='Content']//a[3]")).click();
		Reporter.log("Clicking on the cats link was successful.", true);
		
		driver.findElement(By.linkText("FL-DSH-01")).click();
		Reporter.log("Click the link to view the cat product ID successfully.", true);
		
		driver.findElement(By.linkText("Add to Cart")).click();
		Reporter.log("The cat product has been successfully placed to the cart.", true);
		
		driver.findElement(By.name("EST-14")).sendKeys("15");
		Reporter.log("Enter a specific cat product's quantity number successfully.", true);
		
		driver.findElement(By.name("updateCartQuantities")).click();
		Reporter.log("Successfully updated cart.", true);
		
		driver.findElement(By.linkText("Proceed to Checkout")).click();
		Reporter.log("Successfully Proceed to Checkout.", true);
		
		driver.findElement(By.name("newOrder")).click();
		
		driver.findElement(By.linkText("Confirm")).click();
		Reporter.log("Successfully Confirm the Order.", true);
		
		WebElement Confirm_Message = driver.findElement(By.xpath("//li[normalize-space()='Thank you, your order has been submitted.']"));
		
		if(Confirm_Message.isDisplayed())
		{
			Reporter.log("Thank you, your order has been submitted.", true);
			test.pass("Order was successfully submitted.");
		}
		else
		{
			Reporter.log("Thank you, your order has not been submitted.", true);
			 test.fail("Order submission failed.");
		}
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File temp = ts.getScreenshotAs(OutputType.FILE);
		
		File perm = new File("./Screenshots/CatOrderPaySlip.png");
		
		FileHandler.copy(temp, perm);
		
		Reporter.log("ScreenShot saved at:"+perm.getAbsolutePath(), true);
		test.addScreenCaptureFromPath(perm.getAbsolutePath());
			
	}
	
	
	
	@Test(dependsOnMethods = "addFirstProduct")
	public void removeProduct() throws InterruptedException
	{
		test = report.createTest("Removed Product in Cart");
		
		Reporter.log("----------------REMOVE PRODUCT----------------", true);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Implicit wait set to 20 seconds.", true);
		
		driver.findElement(By.xpath("//div[@id='Content']//a[3]")).click();
		Reporter.log("Clicking on the cats link was successful.", true);
		
		driver.findElement(By.linkText("FL-DSH-01")).click();
		Reporter.log("Click the link to view the cat product ID successfully.", true);
		
		driver.findElement(By.linkText("Add to Cart")).click();
		Reporter.log("The cat product has been successfully placed to the cart.", true);
		
		driver.findElement(By.linkText("Remove")).click();
		Reporter.log("Removed the product in the cart successfully", true);
		
		WebElement confirm_msg = driver.findElement(By.xpath("//b[normalize-space()='Your cart is empty.']"));
		
		if(confirm_msg.isDisplayed())
		{
			Reporter.log("Your cart is empty.", true);
			test.pass("Your cart is empty..");
		}
		else
		{
			Reporter.log("Your cart is not empty.", true);
			test.fail("Your cart is not empty.");
		}
		//Click on Update cart button
		
		test.log(Status.INFO,"Click on Update cart button");
		driver.findElement(By.name("updateCartQuantities")).click();
		Reporter.log("Successfully updated cart of Cats product", true);
		Thread.sleep(2000);
		
		//Read the cart details
				test.log(Status.INFO,"Read the cart details");
				WebElement empty = driver.findElement(By.xpath("//b[contains(text(), 'empty')]"));
				Reporter.log(empty.getText());
				test.log(Status.INFO,empty.getText());
				test.log(Status.INFO,"Read the cart details Succefully");
				Thread.sleep(2000);
	}
	
	
	
	
	
	
}
