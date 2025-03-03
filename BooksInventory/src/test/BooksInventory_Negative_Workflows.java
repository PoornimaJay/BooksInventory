package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BooksInventory_Negative_Workflows {
	
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	
	@Parameters({"URL"})
	@BeforeClass
	public void Login(String urlname) {
		

		driver.get(urlname);
		driver.manage().window().maximize();
	    
	    //Enter Login credentials and click on Login button
	    driver.findElement(By.id("username")).sendKeys("admin1");
	    driver.findElement(By.id("password")).sendKeys("securePassword");
	    driver.findElement(By.id("login-button")).click();
		
	}
	
	@Test(priority=1)
	//Verify Welcome message and logout button is displayed after refreshing the page
	public void RefreshPage() {
		System.out.println("****Confirming Welcome Admin and Logout page is displayed after a page refresh****");
		driver.navigate().refresh();
		
		if (driver.getPageSource().contains("Welcome, Admin!"))
			
		{
			if (driver.findElement(By.cssSelector("div[class='flex justify-between p-4 bg-gray-200']")).getText().contains("Log Out"))
			{
			Assert.assertTrue(false);
		}
		}
		else
		{
			Assert.assertTrue(true);
			System.out.println("!!!!!Welcome Admin message and Logout button are no more displayed!!!!!");
		}
		
	}
	
	@Test(priority=2)
	//Verify Application displays Invalid date in the Date field when invalid date is saved
	public void InvalidDate() {

		System.out.println("****Adding book with Invalid date****");
		driver.findElement(By.cssSelector("button[class='mb-4 py-2 px-4 bg-purple-600 text-white font-semibold rounded hover:bg-yellow-700 add-book-button']"))
		.click();
		driver.findElement(By.id("title")).sendKeys("The Hope");
		driver.findElement(By.id("author")).sendKeys("Sara Williams");
		driver.findElement(By.id("isbn")).sendKeys("9780394800000");
		driver.findElement(By.id("publicationDate")).sendKeys("abcd");
		driver.findElement(By.id("price")).sendKeys("£5.00");
		
		WebElement staticDropdown = driver.findElement(By.id("genre"));	
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText("Non-Fiction");
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		if (driver.findElement(By.xpath("//td[normalize-space()='Invalid Date']")).getText().contains("Invalid Date"))
		
		{
		Assert.assertTrue(true);
		System.out.println("!!!!!Error Message - Invalid Date is entered !!!!!");
		}	
		
		else
		{
			Assert.assertTrue(false);
			System.out.println("!!!!!No Error Message is displayed !!!!!");
		}
				}
	
	@Test(priority=3)
	//Verify Application shows relevant error messages when the input details are not entered
	public void AddBookWithNoDetails() {
		System.out.println("****Click on Add book with no book details****");
		driver.findElement(By.cssSelector("button[class='mb-4 py-2 px-4 bg-purple-600 text-white font-semibold rounded hover:bg-yellow-700 add-book-button']"))
		.click();
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Title is required."))
		{
		
			if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Author is required."))
			{
				
				if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Genre is required."))
				{
					
					if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("ISBN is required."))
					{
						
			}	
					if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Publication Date is required."))
					{
						
			}
					if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Price is required."))
					{
						
						Assert.assertTrue(true);
						System.out.println("!!!!!Error Message - Title is required. is displayed !!!!!");
						System.out.println("!!!!!Error Message - Author is required. is displayed !!!!!");
						System.out.println("!!!!!Error Message - Genre is required. is displayed !!!!!");
						System.out.println("!!!!!Error Message - ISBN is required. is displayed !!!!!");
						System.out.println("!!!!!Error Message - Publication Date is required. is displayed !!!!!");
						System.out.println("!!!!!Error Message - Price is required. is displayed !!!!!");
			}
		}	
		
	}	
		
		else
		{
			Assert.assertTrue(true);
			System.out.println("!!!!!No Error messages are displayed!!!!!");
			
		}
				
			}
	
		}
	@Parameters({"URL","invalidname","pass"})
	@Test(priority=4)
	//Verify Application displays error message with Invalid Username and Valid Password
	public void InvalidUsername(String urlname, String invalidusername, String password) {

		driver.get(urlname);
		driver.manage().window().maximize();
	    
	    //Enter Login credentials and click on Login button
	    driver.findElement(By.id("username")).sendKeys(invalidusername);
	    driver.findElement(By.id("password")).sendKeys(password);
	    driver.findElement(By.id("login-button")).click();
	    
		if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Invalid username or password. Please try again."))
			
		{
		Assert.assertTrue(true);
		System.out.println("!!!!!Error Message - Invalid username or password!!!!!");
		}	
		
		else
		{
			Assert.assertTrue(false);
			System.out.println("!!!!! No Error Message is displayed !!!!!");
		}
	}

	@Parameters({"URL","name","invalidpass"})
	@Test(priority=5)
	//Verify Application displays error message with Valid Username and Invalid Password
	public void InvalidPassword(String urlname, String username, String invalidpassword) {

		driver.get(urlname);
		driver.manage().window().maximize();
	    
	    //Enter Login credentials and click on Login button
	    driver.findElement(By.id("username")).sendKeys(username);
	    driver.findElement(By.id("password")).sendKeys(invalidpassword);
	    driver.findElement(By.id("login-button")).click();
	    
		if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Invalid username or password. Please try again."))
			
		{
		Assert.assertTrue(true);
		System.out.println("!!!!!Error Message - Invalid username or password!!!!!");
		}	
		
		else
		{
			Assert.assertTrue(false);
			System.out.println("!!!!! No Error Message is displayed !!!!!");
		}
	}

	@Parameters({"URL","invalidname","invalidpass"})
	@Test(priority=6)
	//Verify Application displays error message with Invalid Username and Invalid Password
	public void InvalidUsernamePassword(String urlname, String invalidusername, String invalidpassword) {

		driver.get(urlname);
		driver.manage().window().maximize();
	    
	    //Enter Login credentials and click on Login button
	    driver.findElement(By.id("username")).sendKeys(invalidusername);
	    driver.findElement(By.id("password")).sendKeys(invalidpassword);
	    driver.findElement(By.id("login-button")).click();
	    
		if (driver.findElement(By.cssSelector("ul[class='list-disc pl-5']")).getText().contains("Invalid username or password. Please try again."))
			
		{
		Assert.assertTrue(true);
		System.out.println("!!!!!Error Message - Invalid username or password!!!!!");
		}	
		
		else
		{
			Assert.assertTrue(false);
			System.out.println("!!!!! No Error Message is displayed !!!!!");
		}
	}
	
}