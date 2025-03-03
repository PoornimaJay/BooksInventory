package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BooksInventory_All_Positive_Workflows {
	
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	
	@Parameters({"URL","name","pass"})
	@BeforeClass
	public void Login(String urlname, String username, String password) {

		driver.get(urlname);
		driver.manage().window().maximize();
	    
	    //Enter Login credentials and click on Login button
	    driver.findElement(By.id("username")).sendKeys(username);
	    driver.findElement(By.id("password")).sendKeys(password);
	    driver.findElement(By.id("login-button")).click();
		
	}
	
	
	@Test(priority=1)
	//Verify user is able to view records
	public void ViewRecords() {		
		
		if (driver.findElement(By.cssSelector("h3[class='text-lg font-semibold total-book-title']")).getText().contains("Total Book Titles: 3"))
		{
			System.out.println("****User is able to view all records****");
			Assert.assertTrue(true);
		}
		
		else
		{
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(priority=2)
	//Verify user is able to view Welcome and Logout button after login
	public void ConfirmWelcomeMessageAndLogoutButton() {
		
	    if (driver.findElement(By.cssSelector("div[class='flex justify-between p-4 bg-gray-200']")).getText().contains("Welcome, Admin!"))
		{
			System.out.println("User is logged in and Welcome message is displayed");
			Assert.assertTrue(true);
		}
		
		else
		{
			Assert.assertTrue(false);
		}

	    if (driver.findElement(By.cssSelector("div[class='flex justify-between p-4 bg-gray-200']")).getText().contains("Log Out"))
		{
			System.out.println("Logout button is also displayed");
			Assert.assertTrue(true);
		}
		
		else
		{
			Assert.assertTrue(false);
		}

		
	}
	
	@Test(priority=3)
	//Verify user is able to add record
	public void AddBook() {		
		
		System.out.println("****Adding book 4****");
		driver.findElement(By.cssSelector("button[class='mb-4 py-2 px-4 bg-purple-600 text-white font-semibold rounded hover:bg-yellow-700 add-book-button']"))
		.click();
		driver.findElement(By.id("title")).sendKeys("The Hope");
		driver.findElement(By.id("author")).sendKeys("Sara Williams");
		driver.findElement(By.id("isbn")).sendKeys("9780394800000");
		driver.findElement(By.id("publicationDate")).sendKeys("1999-12-20");
		driver.findElement(By.id("price")).sendKeys("£5.00");
		
		WebElement staticDropdown = driver.findElement(By.id("genre"));	
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText("Non-Fiction");
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		//Assertion to confirm if newly added book is displayed
		if (driver.getPageSource().contains("The Hope"))
		{
			System.out.println("Book 4 added successfully");
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		
		

	}
	
	@Test(priority=4)
	//Verify user is able to Edit records
	public void EditBook() {		
		System.out.println("****Editing book 4****");
		driver.findElement(By.id("edit-book-4")).click();
		driver.findElement(By.id("edit-price")).sendKeys("£6.00");		
		driver.findElement(By.id("save-changes")).click();
		
		//Assertion to confirm if edited book is displayed
		if (driver.getPageSource().contains("The Hope"))
		{
			System.out.println("Changes to book 4 added successfully");
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(priority=5)
	//Verify user is able to Delete records
	public void DeleteBook() {	
		System.out.println("****Deleting book 4****");
		driver.findElement(By.id("delete-book-4")).click();
		
		//Assertion to confirm if deleted book is no more displayed
		if (driver.getPageSource().contains("The Hope"))
		{
			Assert.assertTrue(false);
			System.out.println("Book 4 deleted successfully");
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@AfterSuite
	public void Logout() {
		driver.findElement(By.cssSelector("button[class='bg-red-500 text-white py-1 px-3 rounded logout-button']")).click();
		System.out.println("User logged out successfully");
		driver.quit();
				
				}
		
	
}
	

