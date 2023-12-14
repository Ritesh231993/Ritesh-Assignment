package AmazonTest.Assignment;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AmazonTest.Components.Components;

public class ProductCatalogue extends Components {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "span[class='a-size-medium a-color-base a-text-normal']")
	List<WebElement> iPhonesList;
	By iPhonesListBy = By.cssSelector("span[class='a-size-medium a-color-base a-text-normal']");

	@FindBy(xpath = "//li[@id='p_89/Samsung']//i[@class='a-icon a-icon-checkbox']")
	WebElement checkBox;

	@FindBy(css = ".a-size-mini.a-spacing-none.a-color-base.s-line-clamp-4")
	List<WebElement> filteredList;
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(iPhonesListBy);
		return iPhonesList;
	}

	public WebElement selectProduct(String productName, List<WebElement> productList) throws InterruptedException {

		for (WebElement phoneEle : productList) {
			if (phoneEle.getText().equalsIgnoreCase(productName)
					|| phoneEle.getText().equalsIgnoreCase("Apple " + productName)) {
				return phoneEle;
			}
		}
		return null;

	}

	public ProductDetailsPage clickOnProduct(WebElement selectedProductWebElement, String pageTitle)
			throws InterruptedException {

		selectedProductWebElement.click();
		Thread.sleep(9000);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {

			String windowHandle = it.next();
			driver.switchTo().window(windowHandle);
			if (driver.getTitle().equalsIgnoreCase(pageTitle)) {
				System.out.println(driver.getTitle());
				break;
			}
		}

		return new ProductDetailsPage(driver);
	}

	public void setFilter() {

		checkBox.click();
	}

	public List<WebElement> getFilteredList() {

		
		return filteredList;
	}

	public void validateList(List<WebElement> filteredList2) {
		
		for (WebElement productTitle : filteredList) {
			String titleText = productTitle.getText();
			Assert.assertTrue(titleText.contains("Samsung"), "Product title does not contain 'Samsung': " + titleText);
		}
	}

}
