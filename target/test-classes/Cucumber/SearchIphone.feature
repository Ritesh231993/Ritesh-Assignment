Feature: Add iphone to cart 



	@RetryAddToCart
Scenario: Search iphone and add to cart 

	Given Search with the product "iphone" 
	And Select product "iPhone 15 (128 GB) - Black" and Go to product details page "Apple iPhone 15 (128 GB) - Black : Amazon.in: Electronics" 
	When Store price in a variable and validate "76,990" 
	And Click on Add to cart 
	Then Added to Cart message is displayed 
	
	
	
	@phone
Scenario: Search phone and apply filter 

	Given Search for "phone" 
	When Applied filters 
	Then validate the filtered list 
	
  