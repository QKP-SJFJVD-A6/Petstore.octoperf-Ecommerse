package RegisterTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyRegister {
	
	

	@Test
	public void verifyRegisterValidInput() throws InterruptedException {

		//Step:1.1:Launch the Browser-Chrome
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Step 1.2 : Navigate to application
		driver.get("https://petstore.octoperf.com/actions/Catalog.action");

		// Step 2 : Click on "sign in" link on top right center of the homepage
		driver.findElement(By.linkText("Sign In")).click();

		// Step 3 : Click on "Register Now!" button
		driver.findElement(By.partialLinkText("Register Now!")).click();

		// Step 4 : Enter the registeration details
		// Step 4.1 : Enter the User ID
		driver.findElement(By.name("username")).sendKeys("Tom70");
		// Step 4.2 : Enter the New password
		driver.findElement(By.name("password")).sendKeys("Tom123");
		// Step 4.3 : Enter the Repeat password
		driver.findElement(By.name("repeatedPassword")).sendKeys("Tom123");

		// Step 4.4 : Enter the First Name
		driver.findElement(By.name("account.firstName")).sendKeys("Tom1");
		// Step 4.5 : Enter the Last Name
		driver.findElement(By.name("account.lastName")).sendKeys("Jerry");
		// Step 4.6 : Enter the Email
		driver.findElement(By.name("account.email")).sendKeys("tomjerry@gmail.com");
		// Step 4.7 : Enter the Phone
		driver.findElement(By.name("account.phone")).sendKeys("9876543212");
		// Step 4.8 : Enter the Address 1
		driver.findElement(By.name("account.address1")).sendKeys("abc");
		// Step 4.9 : Enter the Address 2
		driver.findElement(By.name("account.address2")).sendKeys("abc123");
		// Step 4.10 : Enter the City
		driver.findElement(By.name("account.city")).sendKeys("hyderabad");
		// Step 4.11 : Enter the State
		driver.findElement(By.name("account.state")).sendKeys("telangana");
		// Step 4.12 : Enter the Zip
		driver.findElement(By.name("account.zip")).sendKeys("560678");
		// Step 4.13 : Enter the Country
		driver.findElement(By.name("account.country")).sendKeys("india");
		// Step 4.14 : Click on "Enable MyList" checkbox
		driver.findElement(By.name("account.listOption")).click();
		// Step 4.15 : Click on "Enable MyBanner" checkbox
		driver.findElement(By.name("account.bannerOption")).click();

		// Step 4.16 : Click on "Save Account Information" button
		driver.findElement(By.name("newAccount")).click();

		Thread.sleep(3000);

		// Step 5 : Click on "Sign in" link
		driver.findElement(By.linkText("Sign In")).click();

		Reporter.log("Registeration completed", true);

	}

}
