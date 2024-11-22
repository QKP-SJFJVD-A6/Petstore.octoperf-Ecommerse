package ProductTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class BirdsProduct {
	
	WebDriver driver;
	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeSuite
	public void suiteSetup() {
		// Create the SparkReport
		spark = new ExtentSparkReporter("./AdvanceReports/report.html");

		// Configure the sparkReport Information
		spark.config().setDocumentTitle("Functionality Testing for Petstore");
		spark.config().setReportName("FunctionalitySuite||BirdsTest");
		spark.config().setTheme(Theme.STANDARD);

		// Create the extent report
		report = new ExtentReports();

		// Attach the Spark Report and ExtentReport
		report.attachReporter(spark);

		// Configure the System Information in ExtentReport
		report.setSystemInfo("DeviceName:", "prashanth chinthalapati");
		report.setSystemInfo("OperatingSystem:", "WINDOWS 10");
		report.setSystemInfo("Browser:", "Chrome");
		report.setSystemInfo("BrowserVersion:", "chrome-130.0.6723.92");

	}

	@AfterSuite
	public void suiteTerminateSetup() {
		// Flush the Report Information
		report.flush();
	}

	
	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup(String browser) {
		test = report.createTest("browserSetup");
		test.log(Status.INFO,"Browser launched succesfully");
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//Step1.1:Launch the browser-chrome
			driver=new ChromeDriver();
		}
		
		else if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			//Step1.1:Launch the browser-chrome
			driver=new EdgeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//Step1.1:Launch the browser-chrome
			driver=new FirefoxDriver();
		}
		else
		{
			Reporter.log("Run the default Browser",true);
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

		}
		
		//Maximize the Browser
		driver.manage().window().maximize();
	}
	
		@BeforeMethod
		public void signin()
		{
			// Create the Test Information
			test = report.createTest("signin");
			test.log(Status.INFO,"Sign In Execution Started Succesful");
			//Step 1.2:Navigate to application
			driver.get("https://petstore.octoperf.com/actions/Catalog.action");
			//Step 5:Click on "Sign in" link
			driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
			//Step 5.1:Enter the User ID
			driver.findElement(By.name("username")).sendKeys("Tom70");
			//Step 5.2:Enter the Password
			driver.findElement(By.xpath("//input[@name='password']")).clear();
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Tom123");
			//Step 5.3:Click on "Login" button
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			
			test.log(Status.INFO,"Sign In Done Succesful");
		}
		
		@AfterMethod
		public void signout()
		{
			// Create the Test Information
			test = report.createTest("signout");
			test.log(Status.INFO,"Sign out Execution Started Succesful");
			//Click on "Signout" Button
			driver.findElement(By.linkText("Sign Out")).click();
			test.log(Status.INFO,"Sign In Done Succesful");

		}
		
		@AfterClass
		public void browserTerminate() {
			// Create the Test Information
			test = report.createTest("browserTerminate");
			driver.quit();
			test.log(Status.INFO,"Browser Terminated Succesful");
		}
	
	@Test
	public void addFirstProduct()
	{
		// Create the Test Information
		test = report.createTest("addFirstProduct");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//img[@src='../images/birds_icon.gif']")).click();
		test.log(Status.INFO,"Click on Birds Succesful");
		driver.findElement(By.linkText("AV-CB-01")).click();
		test.log(Status.INFO,"Click on Adult Male Amazon Parrot Succesful");
		driver.findElement(By.xpath("(//a[text()='Add to Cart'])[1]")).click();
		test.log(Status.INFO,"Click on Add to Cart succesful");
		driver.findElement(By.cssSelector("input[name='EST-18']")).clear();
		driver.findElement(By.cssSelector("input[name='EST-18']")).sendKeys("2");
		test.log(Status.INFO,"Quantity changed to 2 succesful");
		driver.findElement(By.partialLinkText("Checkout")).click();
		test.log(Status.INFO,"Click on Checkout succesful");
		driver.findElement(By.name("newOrder")).click();
		test.log(Status.INFO,"Click on continue succesful");
		driver.findElement(By.linkText("Confirm")).click();
		test.log(Status.INFO,"Bird Product Test-Amazon Parrot-Added Succesful");
	}
	
	@Test(dependsOnMethods = "addFirstProduct")
	public void removeFirstProduct()
	{
		// Create the Test Information
		test = report.createTest("removeFirstProduct");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//img[@src='../images/birds_icon.gif']")).click();
		test.log(Status.INFO,"Click on Birds Succesful");
		driver.findElement(By.linkText("AV-CB-01")).click();
		test.log(Status.INFO,"Click on Adult Male Amazon Parrot Succesful");
		driver.findElement(By.xpath("(//a[text()='Add to Cart'])[1]")).click();
		test.log(Status.INFO,"Click on Add to Cart succesful");
		driver.findElement(By.cssSelector("input[name='EST-18']")).clear();
		driver.findElement(By.cssSelector("input[name='EST-18']")).sendKeys("2");
		driver.findElement(By.xpath("//a[text()='Remove']"));
		test.log(Status.INFO,"Bird Product Test-Amazon Parrot-Removed Succesful");
	}
	
	

}
