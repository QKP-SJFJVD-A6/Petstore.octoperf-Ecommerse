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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Birdsproduct {

	WebDriver driver;

	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup(String browser) {
		// String browser = "chrome";
		Reporter.log("Browser Launched Succesfully", true);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// Step 1.1: Launch the browser - chrome
			driver = new ChromeDriver();
		}

		else if (browser.equals("edge")) {
			// Step 1.1: Launch the browser - Edge
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		else if (browser.equals("firefox")) {
			// Step 1.1: Launch the browser - Firefox
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else {
			Reporter.log("You Stupid Fellow Enter Valid Browser Name !!!!!!!!!!!!!!!!!!", true);
			Reporter.log("So I Will Run My Default Browser", true);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		// Maximize the Browser
		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void signIn() {

		Reporter.log("Sign In Execution Started Successful", true);
		// Step 1.2 : Navigate to application
		driver.get("https://petstore.octoperf.com/actions/Catalog.action");

		// Step 5 : Click on "Sign in" link
		driver.findElement(By.linkText("Sign In")).click();

		// Step 5.1 : Enter the User ID
		driver.findElement(By.name("username")).sendKeys("Tom70");
		// Step 5.2 : Enter the Password
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Tom123");

		// Step 5.3 : Click on "Login" button
		driver.findElement(By.name("signon")).click();

		Reporter.log("Sign In Done Succesful", true);

	}

	@AfterMethod
	public void signOut() {
		Reporter.log("Sign out  Stared Successful", true);
		// Click On "SignOut" Button
		driver.findElement(By.linkText("Sign Out")).click();

		Reporter.log("Sign Out Done Succesful", true);

	}

	@AfterClass
	public void browserTerminate() {
		driver.quit();
		Reporter.log("Browser Terminated Succesful", true);

	}

	@Test
	public void addFirstProduct() {
		// Wait Statement
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Bird Product Test-Amazon Parrot- Added Succesful", true);

	}

	@Test(dependsOnMethods = "addFirstProduct")
	public void removeFirstProduct() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Bird Product Test-Amazon Parrot- Removed Succesful", true);

	}

}
