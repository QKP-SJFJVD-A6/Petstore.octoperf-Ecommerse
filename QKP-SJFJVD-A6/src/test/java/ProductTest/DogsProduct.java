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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DogsProduct {

	WebDriver driver;
	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;

	@BeforeSuite
	public void suiteSetup() {
		// Create the SparkReport
		spark = new ExtentSparkReporter("./AdvanceReports/DogsProduct.html");

		// Configure the sparkReport Information
		spark.config().setDocumentTitle("Functionality Testing for Petstore");
		spark.config().setReportName("FunctionalitySuite||DogsProduct");
		spark.config().setTheme(Theme.STANDARD);

		// Create the extent report
		report = new ExtentReports();

		// Attach the Spark Report and ExtentReport
		report.attachReporter(spark);

		// Configure the System Information in ExtentReport
		report.setSystemInfo("DeviceName:", "Revathi");
		report.setSystemInfo("OperatingSystem:", "WINDOWS 11");
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
		test.log(Status.INFO, "Browser launched succesfully");

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

		// Create the Test Information
		test = report.createTest("signin");
		test.log(Status.INFO, "Sign In Execution Started Succesful");
		// Step: 1.2 : Navigating to the Application
		driver.get("https://petstore.octoperf.com/");

		driver.findElement(By.xpath("//a[contains(@href, 'action')]")).click();

		// Step: 1.2 : Click on Sign In
		driver.findElement(By.xpath("//a[contains(text(), 'Sign')]")).click();

		// Step:2.0 : Enter "User ID" Text Field
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Revathi@1995");

		// Step:3.0 : Clear Password Field
		driver.findElement(By.xpath("//input[@name='password']")).clear();

		// Step:3.1 : Enter Valid "Password" in Password Text Field
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("vishu@2017");

		// Step:4.0: Click on Sign In
		driver.findElement(By.xpath("//input[@name='signon']")).click();

		test.log(Status.INFO, "Sign In Done Succesful");

	}

	@AfterMethod
	public void signOut() {

		// Create the Test Information
		test = report.createTest("signout");
		test.log(Status.INFO, "Sign out Execution Started Succesful");
		// Step: Click on Sign Out
		driver.findElement(By.xpath("//a[contains(@href,'signoff')]")).click();
		test.log(Status.INFO, "Browser Terminated Succesful");

	}

	@AfterClass
	public void terminateBrowser() {
		// Step : Terminating Browser
		driver.quit();
		Reporter.log("Broser Terminated Successfully", true);
	}

	@Test
	public void addProduct() throws IOException, InterruptedException {

		// Create the Test Information
		test = report.createTest("addProduct");
		// Step:5.0: Click on Dog Product
		driver.findElement(By.xpath("(//area[@shape='RECT'])[3]")).click();

		Thread.sleep(1000);
		test.log(Status.INFO, "Click on Dogs Succesful");
		// Step:5.1 Click on a Product Code-6th Product
		driver.findElement(By.xpath("//a[contains(@href,'K9-CW-01')]")).click();
		test.log(Status.INFO, "Click on Chihuahua ID Succesful");
		Thread.sleep(1000);
		// Step:6: Click on Add to Cart
		driver.findElement(By.xpath("(//a[contains(@href,'EST-26')])[2]")).click();
		test.log(Status.INFO, "Click on Add to Cart succesful");
		Thread.sleep(1000);
		// Step:7: Change Quantity
		WebElement quantity = driver.findElement(By.name("EST-26"));
		quantity.clear();
		quantity.sendKeys("1");
		test.log(Status.INFO, "Quantity changed to 1 succesful");
		Thread.sleep(1000);
		// Step8: Update Cart
		driver.findElement(By.name("updateCartQuantities")).click();
		test.log(Status.INFO, "Click on Checkout succesful");
		Thread.sleep(1000);
		// Step:9 Click on Proceed to checkOut
		driver.findElement(By.xpath("(//a[@class='Button'])[2]")).click();
		test.log(Status.INFO, "Click on continue succesful");
		Thread.sleep(1000);
		// Step:10 Click on Continue
		driver.findElement(By.name("newOrder")).click();
		test.log(Status.INFO, "Dog Product Chihuahua-Added Succesful");

		Thread.sleep(1000);
		// Step:11 CLicking on Confirm
		driver.findElement(By.xpath("//a[@class='Button']")).click();

		Thread.sleep(1000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./Screenshots/DogProduct.png");
		FileHandler.copy(temp, perm);
		test.log(Status.INFO, "Screenshot taken succesfully");

	}

	@Test(dependsOnMethods = "addProduct")

	public void removeProduct() {

		// Create the Test Information
		test = report.createTest("removeProduct");
		// Step:5.0: Click on Dog Product
		driver.findElement(By.xpath("//area[@alt='Dogs']")).click();

		// Step:5.1 Click on a Product Code-6th Product
		driver.findElement(By.xpath("//a[contains(@href,'K9-CW-01')]")).click();
		test.log(Status.INFO, "Click on Dog Product Succesful");
		// Step:6: Click on Add to Cart
		driver.findElement(By.xpath("(//a[contains(@href,'EST-26')])[2]")).click();
		test.log(Status.INFO, "Click on Add to Cart succesful");
		// Step:7: Change Quantity
		WebElement quantity = driver.findElement(By.name("EST-26"));
		quantity.clear();
		quantity.sendKeys("2");

		// Step8: Update the Cart
		driver.findElement(By.name("updateCartQuantities")).click();

		// Step9: Clicking on Remove
		driver.findElement(By.xpath("(//a[@class='Button'])[1]")).click();
		test.log(Status.INFO, "Dogs Product Test-Chihuahua-Removed Succesful");

	}
}