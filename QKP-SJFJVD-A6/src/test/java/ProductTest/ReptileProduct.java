package ProductTest;

import java.io.File;
import java.io.IOException;

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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReptileProduct {
	WebDriver driver;
	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeSuite
	public void suiteSetup() {
		// Create the SparkReport
		spark = new ExtentSparkReporter("./AdvanceReports/ReptileProductReport.html");

		// Configure the sparkReport Information
		spark.config().setDocumentTitle("Functionality Testing for Petstore");
		spark.config().setReportName("FunctionalitySuite||ReptileTest");
		spark.config().setTheme(Theme.STANDARD);

		// Create the extent report
		report = new ExtentReports();

		// Attach the Spark Report and ExtentReport
		report.attachReporter(spark);

		// Configure the System Information in ExtentReport
		report.setSystemInfo("DeviceName:", "Harry");
		report.setSystemInfo("OpeningSystem:", "WINDOWS 11");
		report.setSystemInfo("Browser:", "Chrome");
		report.setSystemInfo("BrowserVersion:", "chrome-128.0.6613.85");
	}
	
	@AfterSuite
	public void suiteTerminateSetup() {
		// Flush the Report Information
		report.flush();
	}

	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup(String browser) {

		Reporter.log("Browser Launched Successfully", true);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// Step:1.1: Browser Launched- chrome
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// Step:1.1 Browser Launched- Firefox
			driver = new FirefoxDriver();

		} else if (browser.equals("edge")) {

			WebDriverManager.edgedriver().setup();
			// Step:1.1: Browser Launched-Edge
			driver = new EdgeDriver();
		} else {
			Reporter.log("You Entered Invalid Browser", true);
			Reporter.log("So, I will Run My Default Browser-Chrome");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

	}

	@BeforeMethod
	public void signIn() {

		Reporter.log("SignIn Execution Started", true);
		// Step: 1.2 : Navigating to the Application
		driver.get("https://petstore.octoperf.com/");
		
		driver.findElement(By.xpath("//a[contains(@href, 'action')]")).click();

		// Step: 1.2 : Click on Sign In
		driver.findElement(By.xpath("//a[contains(text(), 'Sign')]")).click();

		// Step:2.0 : Enter "User ID" Text Field
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("RagiSumanjali");

		// Step:3.0 : Clear Password Field
		driver.findElement(By.xpath("//input[@name='password']")).clear();

		// Step:3.1 : Enter Valid "Password" in Password Text Field
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Suma@1234");

		// Step:4.0: Click on Sign In
		driver.findElement(By.xpath("//input[@name='signon']")).click();

		Reporter.log("Sign In done Succesfully", true);

	}
	

	@AfterMethod
	public void signOut() {

		// Step: Click on Sign Out
		driver.findElement(By.xpath("//a[contains(@href,'signoff')]")).click();
		Reporter.log("SignOut Done Successfully", true);

	}

	@AfterClass
	public void terminateBrowser() {
		// Step : Terminating Browser
		driver.quit();
		Reporter.log("Broser Terminated Successfully", true);
	}

	@Test
	public void addProduct() {
		
		Reporter.log("Adding Product Started Successfully", true);

		// Step:5.0: Click on Dog Product
		driver.findElement(By.xpath("//img[contains(@src,'reptiles_icon')]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Step:5.1 Click on a Product Code-6th Product
		driver.findElement(By.xpath("//a[contains(text(),'RP-SN-01')]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step:6: Click on Add to Cart
		driver.findElement(By.xpath("//a[contains(text(),'Add to Cart')]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step:7: Change Quantity
		WebElement quantity = driver.findElement(By.name("EST-11"));
		quantity.clear();
		quantity.sendKeys("1");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step8: Update Cart
		driver.findElement(By.name("updateCartQuantities")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step:9 Click on Proceed to checkOut
		driver.findElement(By.xpath("//a[contains(text(),'Proceed to Checkout')]")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step:10 Click on Continue
		driver.findElement(By.name("newOrder")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step:11 CLicking on Confirm
		driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Step:12 Scrolling Web Page
		//WebElement end = driver.findElement(By.xpath("//th[@colspan='5']"));

		//JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//js.executeScript("window.scrollTo(0,100)");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./TakeScreenshot/DogProduct.png");
		try {
			FileHandler.copy(temp, perm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(driver.getTitle());
		
		Reporter.log("Adding Product Finished Successfully", true);

	}

	@Test
	
	public void removeProduct() {

		// Step:5.0: Click on Dog Product
		driver.findElement(By.xpath("//img[contains(@src,'reptiles_icon')]")).click();

		// Step:5.1 Click on a Product Code-6th Product
		driver.findElement(By.xpath("//a[contains(text(),'RP-SN-01')]")).click();

		// Step:6: Click on Add to Cart
		driver.findElement(By.xpath("//a[contains(text(),'Add to Cart')]")).click();

		// Step:7: Change Quantity
		WebElement quantity = driver.findElement(By.name("EST-11"));
		quantity.clear();
		quantity.sendKeys("2");
		
		//Step8: Update the Cart
		driver.findElement(By.name("updateCartQuantities")).click();
		
		//Step9: Clicking on Remove
		driver.findElement(By.xpath("//a[contains(text(),'Remove')]")).click();
		
		Reporter.log("Add Product Removed Successfully", true);
		

	}
}
