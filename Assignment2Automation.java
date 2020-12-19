package Assignment2Package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import june19SeleniumLocators.Utility;

public class Assignment2Automation {

	public static void main(String[] args) {
		


		System.setProperty("webdriver.chrome.driver", "/Users/farogaturakova/Documents/SeleniumFiles/drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1. Go to Cargurus.com (Carfax.com is not letting me using Automation tasks)
		driver.get("https://www.cargurus.com/");
		
		
		//2. Click on Find a Used Car
		driver.findElement(By.linkText("Used Cars")).click();
		
		
		//3. Verify the page title contains the word “Used Cars”
		Utility.assertContains("Used Cars", driver.getTitle());
		
		
		//4. Choose “Tesla” for  Make.
		Select s = new Select(driver.findElement(By.id("Select Make")));
		s.selectByValue("m112");
		
		//5. Verify that the Select Model dropdown box contains 3 current Tesla models - (Model 3, Model S, Model X). 
		String expectedTeslaModels1 = "Model 3";
		String expectedTeslaModels2 = "Model S";
		String expectedTeslaModels3 = "Model X";
		
		if(driver.getPageSource().contains(expectedTeslaModels1)) {
			System.out.println("Model 3 is in Tesla Models");
		} else 
			System.out.println("FAILED");
		
		if(driver.getPageSource().contains(expectedTeslaModels2)) {
			System.out.println("Model S is in Tesla Models");
		} else 
			System.out.println("FAILED");
		
		if(driver.getPageSource().contains(expectedTeslaModels3)) {
			System.out.println("Model X is in Tesla Models");
		} else 
			System.out.println("FAILED");
		
		
		//6. Choose “Model S” for Model.
		new Select(driver.findElement(By.id("Select Model"))).selectByValue("d2039");
		
		//7. Enter the zipcode as 22182 and click Next
		driver.findElement(By.id("postal-code-input")).sendKeys("22182");
		
		////Enter the Radius within 10 miles
		new Select(driver.findElement(By.id("radius"))).selectByValue("10");
		
		driver.findElement(By.xpath("//*[@id=\"search-form\"]/form/button")).click();
		
		
		//Verify the expected Greeting
		String expectedGreeting = "Used Tesla Model S for Sale in Vienna, VA - CarGurus";
		System.out.println(driver.getPageSource().contains(expectedGreeting));
		
		//14. Verify that each result contains String “Tesla Model S”.
		String expectedResult = "Tesla Model S";
		String actualResult = driver.findElement(By.xpath("//*[@id=\"cargurus-listing-search\"]/div[1]/div/div[2]/div[2]/div[1]/div/div")).getText();
		
		Utility.assertEquals(expectedResult, actualResult);
		
		
		//15. Get the price of each result and save them into a list in the order of their appearance
		//I could not print all prices, it is only printing one car's price 
		
		//System.out.println(driver.findElement(By.className("_8E6guA")).getText());
		
		
		WebElement result = driver.findElement(By.cssSelector("div[class='_2KVYZH'] span"));
		
		System.out.println(result.getText());
		
		
		
		
		//16. Choose “Price - High to Low” option from Sort menu
		
		new Select(driver.findElement(By.cssSelector("div[class='_4X6b2V'] select"))).selectByValue("PRICE_DESC");
		
		
		//17. Verify that the results are displayed from high to low price. 
		
		String expectedResult2 = "Highest price first";
		System.out.println(driver.getPageSource().contains(expectedResult2));
		
		
		
		//18. Choose “Mileage - Low to High” option from Sort menu
		
		new Select(driver.findElement(By.cssSelector("div[class='_4X6b2V'] select"))).selectByValue("MILEAGE_ASC");
				
		
		//19. Verify that the results are displayed from low to high mileage.  
				
		String expectedResult3 = "Lowest mileage first";
		System.out.println(driver.getPageSource().contains(expectedResult3));
		
		
		
		
		//20. Choose “Year - New to Old” option from Sort menu
		
		new Select(driver.findElement(By.cssSelector("div[class='_4X6b2V'] select"))).selectByValue("DAYS_ON_MARKET_ASC");
						
		
		//21. Verify that the results are displayed from new to old year.   
						
		String expectedResult4 = "Newest listings first";
		System.out.println(driver.getPageSource().contains(expectedResult4));
		
		
		

	}

}
