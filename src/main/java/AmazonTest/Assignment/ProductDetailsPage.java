package AmazonTest.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmazonTest.Components.Components;

public class ProductDetailsPage extends Components {
	
	
	
WebDriver driver;
String text;
	
	public ProductDetailsPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".a-price.aok-align-center.reinventPricePriceToPayMargin.priceToPay .a-price-whole")
	WebElement price;
	
	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	WebElement addToCartButton;
	
	@FindBy(xpath = "(//div[@class='a-box-inner a-alert-container']/h4)[5]")
	WebElement addToCartText;
	
	public String storePrice() throws InterruptedException
	{
		Thread.sleep(9000);
		return price.getText();
	}

	public String addToCart() throws InterruptedException {
       // waitForWebElementToBeClicable(addToCartButton);
        waitForWebElementToAppear(addToCartButton);
		addToCartButton.click();
		waitForWebElementToAppear(addToCartText);
		text = addToCartText.getText();
		return text;
		
	}
	
	public String getText()
	{
		return text;
	}

}
