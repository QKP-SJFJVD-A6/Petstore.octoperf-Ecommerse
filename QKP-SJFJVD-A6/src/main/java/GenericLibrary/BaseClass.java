package GenericLibrary;

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
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver static_driver = null;
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;

	@DataProvider
	public Object[][] registerData() {
		Object[][] data = new Object[3][3];

		data[0][0] = 101;
		data[0][1] = "Harry";
		data[0][2] = "Trainer";

		data[1][0] = 102;
		data[1][1] = "Suma";
		data[1][2] = "Testing";

		data[2][0] = 103;
		data[2][1] = "Manasa";
		data[2][2] = "Devops";
		return data;
	}

	@BeforeSuite
	public void suiteSetup() {
		// Create the SparkReport
		spark = new ExtentSparkReporter("./AdvanceReports/BirdsProductReport.html");

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

	@BeforeClass
	public void browserSetup() throws InterruptedException {
		String browsername = "chrome";

		if (browsername.equals("chrome")) {
			// Launch Chrome Browser
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			static_driver = driver;
		}

		else if (browsername.equals("edge")) {
			// Launch Browser
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			static_driver = driver;
		}

		else if (browsername.equals("firefox")) {
			// Launch Chrome Browser
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			static_driver = driver;
		}

		else {
			Reporter.log("Run the default Browser", true);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			System.out.println("You Have Enter Invalid Browser Name!!!!!!!!!");
		}

		// Maximize the Browser
		driver.manage().window().maximize();
	}

	@BeforeMethod()
	public void signin() {
		// Create the Test Information
		test = report.createTest("signin");
		test.log(Status.INFO, "Sign In Execution Started Succesful");
		// Step 1.2:Navigate to application
		driver.get("https://petstore.octoperf.com/actions/Catalog.action");
		// Step 5:Click on "Sign in" link
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		// Step 5.1:Enter the User ID
		driver.findElement(By.name("username")).sendKeys("Prashanth2507");
		// Step 5.2:Enter the Password
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Prashanth@25");
		// Step 5.3:Click on "Login" button
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		test.log(Status.INFO, "Sign In Done Succesful");
	}

	@AfterMethod
	public void signout() {
		// Create the Test Information
		test = report.createTest("signout");
		test.log(Status.INFO, "Sign out Execution Started Succesful");
		// Click on "Signout" Button
		driver.findElement(By.linkText("Sign Out")).click();
		test.log(Status.INFO, "Sign In Done Succesful");

	}

	@AfterClass
	public void browserTerminate() {
		driver.quit();

	}

}
