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

public class FishProduct {

	
WebDriver driver;
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	
	@BeforeSuite
	public void Suitesetup()
	{
		
		//Create the sparkreport
		 spark=new ExtentSparkReporter("./AdvanceReports/FishProductReport.html");
				
		//Configure the Sparkreport informtion
				
		spark.config().setDocumentTitle("Functional Testing for Petstore");
		spark.config().setReportName("FunctionalSuite||updateProduct||addProduct");
		spark.config().setTheme(Theme.STANDARD);
				
		//Create the Extent Report
				
		report=new ExtentReports();
				
		//Attach the Spark Report and ExtentReport
		report.attachReporter(spark);
				
		//Configure the System Information in Extent Report
		report.setSystemInfo("DeviceName;", "Charan");
		report.setSystemInfo("OperatingSystem", "Windows10");
		report.setSystemInfo("Browser", "chrome");
		report.setSystemInfo("BrowserVersion", "chrome-123.0.6312.106");
	
	}
	
	
	@AfterSuite
	public void suiteTerminateSetup()
	{
		
		//Flush the Report information
		report.flush();
	}
	
	
	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup(String browser)
	{
		test = report.createTest("browserSetup");
		test.log(Status.INFO,"Browser launched succesfully");
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//Step 1.1: Launch the browser-chrome
			driver=new ChromeDriver();
		}
		else if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			Reporter.log("enter the valid browsername");
		}
		
		//Maximize the Browser
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void signIn()
	{
		test = report.createTest("signin");
		test.log(Status.INFO,"Sign In Execution Started Succesful");
		//Step 1.2 Navigate to application
		driver.get("https://petstore.octoperf.com/actions/Catalog.action");
		test.log(Status.INFO,"Succesfully Navigated to the application");
		
		//Step 2 click on Sigin link
		test.log(Status.INFO,"Click on Sigin link");
		driver.findElement(By.xpath("//a[contains(text(), 'Sign In')]")).click();
		test.log(Status.INFO,"Click on Sigin link Succesfully");
		
		//Step 3.1 Enter the user name on user name text field
		test.log(Status.INFO,"Enter the user name on user name text field");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Charan00013");
		test.log(Status.INFO,"Enter the user name Enterted Succefully");
		
		//Step 3.2 Enter the password on password text field
		test.log(Status.INFO,"Clear the password on password text field");
		test.log(Status.INFO,"Enter the password on password text field");
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		test.log(Status.INFO,"Enter the password on password text field Succefully");
		
		test.log(Status.INFO,"Enter the password on password text field");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Ch@ran00013");
		test.log(Status.INFO,"Enter the password on password text field Succefully");
		
		//Step 3.3 Click on Login button
		test.log(Status.INFO,"Click on Login button");
		driver.findElement(By.xpath("//input[contains(@name, 'signon')]")).click();
		test.log(Status.INFO,"Click on Login button Succefully");

		test.log(Status.INFO,"Sigin done succefull");
	}
	
	@AfterMethod
	public void signOut()
	{
		test = report.createTest("signout");
		test.log(Status.INFO,"Sigin out started succefull");
		
		//Step 4 Click on Sign out button
		test.log(Status.INFO,"Click on Sign out button");
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		test.log(Status.INFO,"Click on Sign out button Succefull");
		
		test.log(Status.INFO,"Sigin out done succefull");
	}
	
	@AfterClass
	public void broserTerminate()
	{
		test = report.createTest("broserTerminate");
		driver.quit();
		test.log(Status.INFO,"Browser terminated succefully");
	}
	@Test 
	public void addProduct() throws InterruptedException, IOException
	{
		
		// Create the Test Information
		test = report.createTest("addProduct");
				
		//Click on Fish
		test.log(Status.INFO,"Click on Fish");
		driver.findElement(By.xpath("//img[contains(@src, 'fish_icon')]")).click();
		test.log(Status.INFO,"Click on Fish Succefully");
		
		//Click on Angelfish
		test.log(Status.INFO,"Click on Angelfish");
		driver.findElement(By.xpath("//a[contains(text(), 'FI-SW-01')]")).click();
		test.log(Status.INFO,"Click on Angelfish Succefully");
		
		//Click on Add to Cart
		test.log(Status.INFO,"Click on Add to Cart");
		driver.findElement(By.xpath("(//a[contains(text(), 'Add to Cart')])[1]")).click();
		test.log(Status.INFO,"Click on Add to Cart Succefully");
		//clear product Quantity
		test.log(Status.INFO,"Click on clear product Quantity");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		test.log(Status.INFO,"Click on clear product Quantity Succefully");
		
		//Add product Quantity
		test.log(Status.INFO,"Click on Add product Quantity");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("10");
		test.log(Status.INFO,"Click on Add product Quantity Succefully");
		
		//Click on Proceed to Checkout button
		test.log(Status.INFO,"Click on Proceed to Checkout button");
		driver.findElement(By.xpath("//a[contains(text(), 'Proceed')]")).click();
		test.log(Status.INFO,"Click on Proceed to Checkout button Succefully");
		
		//Click on Continue button
		test.log(Status.INFO,"Click on Continue button");
		driver.findElement(By.name("newOrder")).click();
		test.log(Status.INFO,"Click on Continue button Succefully");
		
		//click on Confirm button
		test.log(Status.INFO,"Click on Confirm button");
		driver.findElement(By.xpath("//a[contains(text(), 'Confirm')]")).click();
		test.log(Status.INFO,"Click on Confirm button Succefully");
		
		Thread.sleep(2000);
		//Read the order details
		test.log(Status.INFO,"Read the order details");
		WebElement tq = driver.findElement(By.xpath("//li[contains(text(), 'your order')]"));
		Reporter.log(tq.getText());
		test.log(Status.INFO,tq.getText());
		test.log(Status.INFO,"Read the order details Succefully");
		Thread.sleep(2000);
		
		WebElement addProductpage = driver.findElement(By.xpath("//html[contains(@xmlns, '1999')]"));
		File temp = addProductpage.getScreenshotAs(OutputType.FILE);
		File perm = new File("./Screenshots/AddFishProduct.png");
		FileHandler.copy(temp, perm);
		test.log(Status.INFO,"Screenshot taken succesfully");
		Thread.sleep(2000);
		
	}
	
	@Test
	public void updateProduct() throws InterruptedException, IOException
	{
		
		// Create the Test Information
		test = report.createTest("updateProduct");
				
		//Click on Fish
		test.log(Status.INFO,"Click on Fish");
		driver.findElement(By.xpath("//img[contains(@src, 'fish_icon')]")).click();
		test.log(Status.INFO,"Click on Fish Succefully");
				
		//Click on Angelfish
		test.log(Status.INFO,"Click on Angelfish");
		driver.findElement(By.xpath("//a[contains(text(), 'FI-SW-01')]")).click();
		test.log(Status.INFO,"Click on Angelfish Succefully");
				
		//Click on Add to Cart
		test.log(Status.INFO,"Click on Add to Cart");
		driver.findElement(By.xpath("(//a[contains(text(), 'Add to Cart')])[1]")).click();
		test.log(Status.INFO,"Click on Add to Cart Succefully");
				
		//clear product Quantity
		test.log(Status.INFO,"Click on clear product Quantity");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		test.log(Status.INFO,"Click on clear product Quantity Succefully");
				
		//Add product Quantity
		test.log(Status.INFO,"Click on Add product Quantity");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("20");
		test.log(Status.INFO,"Click on Add product Quantity Succefully");
				
		
		//Click on Update cart button
		test.log(Status.INFO,"Click on Update cart button");
		driver.findElement(By.name("updateCartQuantities")).click();
		test.log(Status.INFO,"Click on Update cart button Succefully");
		
		//Click on Proceed to Checkout button
		test.log(Status.INFO,"Click on Proceed to Checkout button");
		driver.findElement(By.xpath("//a[contains(text(), 'Proceed')]")).click();
		test.log(Status.INFO,"Click on Proceed to Checkout button Succefully");
				
		//Click on Continue button
		test.log(Status.INFO,"Click on Continue button");
		driver.findElement(By.name("newOrder")).click();
		test.log(Status.INFO,"Click on Continue button Succefully");
				
		//click on Confirm button
		test.log(Status.INFO,"Click on Confirm button");
		driver.findElement(By.xpath("//a[contains(text(), 'Confirm')]")).click();
		test.log(Status.INFO,"Click on Confirm button Succefully");
				
		Thread.sleep(2000);
		//Read the order details
		test.log(Status.INFO,"Read the order details");
		WebElement tq = driver.findElement(By.xpath("//li[contains(text(), 'your order')]"));
		Reporter.log(tq.getText());
		test.log(Status.INFO,tq.getText());
		test.log(Status.INFO,"Read the order details Succefully");
		Thread.sleep(2000);
				
		WebElement updateCartpage = driver.findElement(By.xpath("//html[contains(@xmlns, '1999')]"));
		File temp = updateCartpage.getScreenshotAs(OutputType.FILE);
		File perm = new File("./Screenshots/UpdateFishProduct.png");
		FileHandler.copy(temp, perm);
		test.log(Status.INFO,"Screenshot taken succesfully");
		Thread.sleep(2000);
	}
	
	@Test
	public void removeProduct() throws InterruptedException
	{
		
		// Create the Test Information
		test = report.createTest("RemoveProduct");
				
		//Click on Fish
		test.log(Status.INFO,"Click on Fish");
		driver.findElement(By.xpath("//img[contains(@src, 'fish_icon')]")).click();
		test.log(Status.INFO,"Click on Fish Succefully");
				
		//Click on Angelfish
		test.log(Status.INFO,"Click on Angelfish");
		driver.findElement(By.xpath("//a[contains(text(), 'FI-SW-01')]")).click();
		test.log(Status.INFO,"Click on Angelfish Succefully");
				
		//Click on Add to Cart
		test.log(Status.INFO,"Click on Add to Cart");
		driver.findElement(By.xpath("(//a[contains(text(), 'Add to Cart')])[1]")).click();
		test.log(Status.INFO,"Click on Add to Cart Succefully");
				
		//Click on remove button
		test.log(Status.INFO,"Click on Remove button");
		driver.findElement(By.xpath("//a[contains(text(), 'Remove')]")).click();
		test.log(Status.INFO,"Click on Remove button Succefully");
		
		//Click on Update cart button
		test.log(Status.INFO,"Click on Update cart button");
		driver.findElement(By.name("updateCartQuantities")).click();
		test.log(Status.INFO,"Click on Update cart button Succefully");
		
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
