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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReptileProduct {
	WebDriver driver;

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
	public void addProduct() throws IOException, InterruptedException {
		
		Reporter.log("Adding Product Started Successfully", true);

		// Step:5.0: Click on Dog Product
		driver.findElement(By.xpath("//img[contains(@src,'reptiles_icon')]")).click();

		Thread.sleep(2000);
		
		// Step:5.1 Click on a Product Code-6th Product
		driver.findElement(By.xpath("//a[contains(text(),'RP-SN-01')]")).click();

		Thread.sleep(2000);
		// Step:6: Click on Add to Cart
		driver.findElement(By.xpath("//a[contains(text(),'Add to Cart')]")).click();

		Thread.sleep(2000);
		// Step:7: Change Quantity
		WebElement quantity = driver.findElement(By.name("EST-11"));
		quantity.clear();
		quantity.sendKeys("1");

		Thread.sleep(1000);
		// Step8: Update Cart
		driver.findElement(By.name("updateCartQuantities")).click();

		Thread.sleep(1000);
		// Step:9 Click on Proceed to checkOut
		driver.findElement(By.xpath("//a[contains(text(),'Proceed to Checkout')]")).click();

		Thread.sleep(1000);
		// Step:10 Click on Continue
		driver.findElement(By.name("newOrder")).click();

		Thread.sleep(1000);
		// Step:11 CLicking on Confirm
		driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click();

		Thread.sleep(1000);
		// Step:12 Scrolling Web Page
		//WebElement end = driver.findElement(By.xpath("//th[@colspan='5']"));

		//JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//js.executeScript("window.scrollTo(0,100)");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./TakeScreenshot/DogProduct.png");
		FileHandler.copy(temp, perm);
		
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
