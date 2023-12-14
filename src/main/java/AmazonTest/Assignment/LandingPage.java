package AmazonTest.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmazonTest.Components.Components;


public class LandingPage extends Components {
	
WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css = "#twotabsearchtextbox")
	WebElement searchBox;
	//driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("iphone",Keys.ENTER);
	
	
	public ProductCatalogue enterSearchString(String searchData)
	{
		searchBox.sendKeys(searchData,Keys.ENTER);
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	
	
	
	
	
	
	public void goTo() {

		driver.get("https://www.amazon.in/");
	}

}
