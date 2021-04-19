package stepdefinitions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitions {

	WebDriver driver;
	Scenario scenario;

	@Before
	public void setupDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://makemytrip.com");
		driver.manage().window().maximize();
		Assert.assertEquals("https://www.makemytrip.com/", driver.getCurrentUrl());
		

	}

	@Given("^I login on the MakeMyTrip and home page is displayed\\.$")
	public void i_login_on_the_MakeMyTrip_and_home_page_is_displayed() throws Exception {
		// driver.findElement(By.xpath("//input[@id='username']")).sendKeys("xyz@gmail.com");
		// driver.findElement(By.xpath("//button/span[text()='Continue']")).click() ;
		// driver.findElement(By.xpath("//input[@id='password']")).sendKeys("test");
		// driver.findElement(By.xpath("//button/span[text()='Login']")).click() ;
		String pageTitle=driver.getTitle();

		scenario.write("Login is successfull with the valid credentials" +pageTitle);
	}

	@When("^I select the Source as \"([^\"]*)\" and  Destination as \"([^\"]*)\" for \"([^\"]*)\"\\.$")
	public void i_select_the_Source_as_and_Destination_as_for(String sourceCity, String destinationCity,
			String tripType) throws Exception {

		
		driver.findElement(By.xpath(".//div[@data-cy='flightSW']//li[@data-cy='"+tripType +"']//span[text()='"+tripType +"'")).click();
		
		// *** To select the source city *****//
		driver.findElement(By.xpath(".//input[@id='fromCity']")).click();
		driver.findElement(By.xpath(".//div[@role='combobox']")).sendKeys(sourceCity);
		driver.findElement(By.xpath(".//div[@role='combobox']//ul//li//p[contains,'"+sourceCity +"'")).click();

		// *** To select the destination city *****//
		driver.findElement(By.xpath(".//input[@id='toCity']")).click();
		driver.findElement(By.xpath(".//div[@role='combobox']")).sendKeys(destinationCity);
		driver.findElement(By.xpath(".//div[@role='combobox']//ul//li//p[contains,'"+destinationCity+"' ")).click();

	}

	@When("^I select the departure date as today and arrival date as tomorrow\\.$")
	public void i_select_the_departure_date_as_today_and_arrival_date_as_tomorrow() throws Exception {
  //***Assuming the values will be auto populated when selected as round trip. Hence not validating this section
	scenario.write("Departure date and arrival date is as per the selection ");
	
	}

	@When("^I select the no\\.of passsengers as \"([^\"]*)\" and \"([^\"]*)\" and click on Apply button\\.$")
	public void i_select_the_no_of_passsengers_as_and_and_click_on_Apply_button(String adultCount, String childrenCount) throws Exception {

		//*** Selecting the no.of passengers by clicking on the span tag Travellers and class in the widget ***//
		
		driver.findElement(By.xpath(".//div[@data-cy='flightTraveller']//span[text()='Travellers & CLASS']")).click();
		driver.findElement(By.xpath(".//div[@class='travellers gbTravellers']//ul//li[@data-cy='adults-2']")).click();
		driver.findElement(By.xpath(".//div[@class='travellers gbTravellers']//ul//li[@data-cy='adults-2']")).click();
		driver.findElement(By.xpath("..//div[@class='makeFlex column childCounter']/ul[contains(@class,'guestCounter)']/li[@data-cy='children-1']  ")).click();
		driver.findElement(By.xpath(".//div[@class='travellers gbTravellers']/button[@data-cy='travellerApplyBtn']")).click();
	
	
	}

	@When("^I click on Search button and verify the flights list page is displayed\\.$")
	public void i_click_on_Search_button_and_verify_the_flights_list_page_is_displayed() throws Exception {

	driver.findElement(By.xpath(".//p[@data-cy='submit']//a[text()='Search']")).click();
	Assert.assertEquals("https://www.makemytrip.com/flight/search", driver.getTitle());
	
	}

	@When("^I click on non stop flights check box and sort  with lowest price\\.$")
	public void i_click_on_non_stop_flights_check_box_and_sort_with_lowest_price() throws Exception {
		
		driver.findElement(By.xpath(".//div[@class='filtersOuter']/div[1]//span[@class='filterName and title='Non Stop']")).click();
		
		//*** Sort the price for departure flights **//
		
		driver.findElement(By.xpath(".//div[@class='splitVw']/div[1]//div[@class='sortBySection']//div[@class='sortby-dom-sctn price_sorter  blackFont']//span/span[text()='Price']")).click();
		
		//***Sort the price for arrival Flights ***//
		driver.findElement(By.xpath(".//div[@class='splitVw']/div[2]//div[@class='sortBySection']//div[@class='sortby-dom-sctn price_sorter  blackFont']//span/span[text()='Price']")).click();


	}

	@When("^I select the flights with lowest price and click on Book Now button$")
	public void i_select_the_flights_with_lowest_price_and_click_on_Book_Now_button() throws Exception {

	driver.findElement(By.xpath(".//button[text()='Book Now']")).click();
	
	}

	@When("^I click on Continue in the next page$")
	public void i_click_on_Continue_in_the_next_page() throws Exception {

	  driver.findElement(By.xpath(".//button[text()='Continue']")).click();
	}

	@Then("^I verify the new tab is displayed with the Title as \"([^\"]*)\"\\.$")
	public void i_verify_the_new_tab_is_displayed_with_the_Title_as(String pageTitle) throws Exception {
		
		String currentPageTitle=driver.getWindowHandle();
		Set<String>tabDetails=driver.getWindowHandles();
		
		Iterator<String> itr=tabDetails.iterator();
		
		 while (itr.hasNext()) {
	            String ChildWindow = itr.next();
	                if (!currentPageTitle.equalsIgnoreCase(ChildWindow)) {
	                driver.switchTo().window(ChildWindow);
	               String actualTitle= driver.getTitle();
	       		Assert.assertEquals("https://www.makemytrip.com/flight/review/", actualTitle);

	                
	            }
		}
		
		
		
		
		

	}

}
