package AmazonTest.StepDefination;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AmazonTest.Assignment.ProductCatalogue;
import AmazonTest.Assignment.ProductDetailsPage;
import AmazonTest.TestComponents.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationAddCart extends BaseTest {

	public static int MAX_RETRIES = 2;
	ProductCatalogue productCatalogue;
	ProductDetailsPage prodDetailsPage;

	/*
	 * @Given("I Navigated on Landing Page") public void
	 * i_navigated_on_landing_page() {
	 * 
	 * launchApplication(); }
	 */

	@Given("Search with the product {string}")
	public void search_with_the_product(String string) {

		productCatalogue = landingPage.enterSearchString("iphone");
	}

	@Given("Select product {string} and Go to product details page {string}")
	public void select_product_and_get_window_handle_of(String productName, String pageTitle)
			throws InterruptedException {
		List<WebElement> productList = productCatalogue.getProductList();
		WebElement selectedProductWebElement = productCatalogue.selectProduct(productName, productList);
		prodDetailsPage = productCatalogue.clickOnProduct(selectedProductWebElement, pageTitle);
	}

	@When("Store price in a variable and validate {string}")
	public void store_price_in_a_variable_and_validate(String price) throws InterruptedException {
		String prodPrice = prodDetailsPage.storePrice();
		System.out.println(prodPrice);
		Assert.assertEquals(prodPrice, price);
	}

	@When("Click on Add to cart")
	public void click_on_add_to_cart() throws InterruptedException {
		
		int retryCount = 0;
		 while (retryCount < MAX_RETRIES)
	            try {
	            	String text = prodDetailsPage.addToCart();
	                break;
	            } catch (Exception e) {
	                System.out.println("Retry attempt " + (retryCount + 1) + " failed: " + e.getMessage());


	                retryCount++;
	            }
		 }
		
	

	@Then("Added to Cart message is displayed")
	public void added_to_cart_message_is_displayed() {

		String text = prodDetailsPage.getText();
		Assert.assertEquals(text, "Added to Cart");
	}
	
	
	@Given("Search for {string}")
	public void search_for(String phone) {
		productCatalogue = landingPage.enterSearchString(phone);
	}

	@When("Applied filters")
	public void applied_filters() {
		productCatalogue.setFilter();
	}

	@Then("validate the filtered list")
	public void validate_the_filtered_list() {
		List<WebElement> filteredList = productCatalogue.getFilteredList();
		productCatalogue.validateList(filteredList);
	}
	
	 @Before
	    public void setUp() {
	        
		 launchApplication();
	    }
	
	 @After
	    public void tear() {
	       
		 tearDown();
		 
	        }
}
