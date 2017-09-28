package com.travels.pageclass;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.travels.genericLibrary.WebPage;

public class HomePage implements WebPage {
	static Logger logger;
	protected WebDriver _driver;
	protected WebDriverWait _driverWait;
	protected Properties prop;
	protected String url;
	protected String title;
	protected String logo;
	protected String hotelTab;
	protected String locOrHotelName;
	protected String checkinDate;
	protected String checkoutDate;
	protected String noOfAdults;
	protected String noOfChilds;
	protected String searchButton;
	public String appTitle;



	
	/**
	 * <p>This constructor method Loads the all page locators required for Application Home page from test.properties file
	 * @author Smruti
	 * @param timeout an int that is used to set the default timeout for the page
	 * 
	 */
	
	public HomePage(WebDriver driver, int timeout){
		this._driver = driver;
		logger =Logger.getLogger(this.getClass().getName());
		try{
			prop = new Properties();
			prop.load(HomePage.class.getClassLoader().getResourceAsStream("test.properties"));
	
			url = prop.getProperty("url");
			Assert.assertNotNull(url);
			/*title=prop.getProperty("home_title");
			Assert.assertNotNull(title);*/
			title=prop.getProperty("home_title");
			Assert.assertNotNull(title);
			logo = prop.getProperty("logo"); 
			Assert.assertNotNull(logo);
			hotelTab=prop.getProperty("hotel_tab");	
			Assert.assertNotNull(hotelTab);
			locOrHotelName = prop.getProperty("loc_or_hotel_name");
			Assert.assertNotNull(locOrHotelName);
			checkinDate = prop.getProperty("checkin_date");
			Assert.assertNotNull(checkinDate);
			checkoutDate = prop.getProperty("checkout_date");
			Assert.assertNotNull(checkoutDate);
			noOfAdults = prop.getProperty("no_of_adults");
			Assert.assertNotNull(noOfAdults);
			noOfChilds = prop.getProperty("no_of_childs"); 
			Assert.assertNotNull(noOfChilds);
			searchButton = prop.getProperty("search_btn"); 
			Assert.assertNotNull(searchButton);
			
			
			int pweight = Integer.parseInt( prop.getProperty("pageWeight"));
			_driver.manage().timeouts().implicitlyWait(pweight*timeout, TimeUnit.SECONDS);
			//_driverWait = new WebDriverWait(_driver,pweight*timeout,pweight);
			
			
		} catch (IOException e) {
    		logger.error("Could not find property file");
    		logger.error(e.getMessage());
          }	
		
	}
	
	/**
	 * This method returns the driver associated with Application Home Page 
	 * @author Smruti
	 * @return the Webdriver used to access Application Home page
	 */
	 public WebDriver getDriver() {
		 
		 return _driver;
	 }
	 

	/**
	 * <p>This method is used to navigate to the application
	 * @author Smruti
	 * @param driver a WebDriver Object
	 * 
	 */
public WebDriver openAppUrl() {
	logger.info("open the application URL");
	try {
		_driver.get(url);
		_driver.manage().window().maximize();
		logger.info("opened application URL");
	} catch (NoSuchElementException e) {
		logger.error("Not able to open application URL");
	}	
		return _driver;
		
	}
/**
 * This Method asserts the title of the Application Login page
 * @author Smruti
 * @return return "success" 
 *  
 */
public String getTitle() {
	logger.info("validate title of the application");
	try {
		 appTitle = _driver.getTitle();
		Assert.assertNotNull(appTitle);
		 logger.info("Page title is: "+appTitle);
	} catch (NoSuchElementException e) {
		logger.error("Not able to  get the title of page");
		return "FAIL";
	}	
	 return "PASS"; 	
}

/**
 * <p>Method to validate Pharmacy Locatory login page
 * @author Smruti
 * @return PASS if validation success else FAIL
 *  
 */
public String validatePage() {
		
	logger.info("Validate  if  home page is displayed ?  ");
	try{
		if(_driver.findElement(By.xpath(logo)).isDisplayed())
				logger.info("Home Page is displayed ");
		
	} catch (NoSuchElementException ne){
		logger.error("Home Page is not  displayed ...");
		return "FAIL";
		}
	
	 return "PASS";
}


/**
 * <p>This method is used to click on Hotels tab from home page
 * @author Smruti
 * 
 */
public String clickHotelsTab(){
	logger.info("Click on Hotels tab");
	try {
		_driver.findElement(By.xpath(hotelTab)).click();
		logger.info("clicked on  hotel tab");
	} catch (NoSuchElementException ne) {
		logger.error("Not able click on  hotel tab");
		return "FAIL";
	}
	return "PASS";
}

/**
 * <p>This method is used to enter the values for City or hotel name for search
 * @author Smruti
 * @throws InterruptedException 
 * @throws Exception 
 * 
 */
	 public String enterCityOrHotelName(String cityOrHotelName)  throws InterruptedException 
	 {
		 logger.info("Enter City or Hotel  name");
		 try {
			 //_driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locOrHotelName)));
		 _driver.findElement(By.xpath(locOrHotelName)).sendKeys(cityOrHotelName);
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
		 _driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
			 logger.info("City or Hotel  name is Entered"); 
		} catch (NoSuchElementException e) {
			 logger.info("City or Hotel  name  textbox field is not found:"+e.getMessage());
			return "FAIL";
		}
		 return "PASS";
		
		}
	 /**
	  * <p>This method is used to enter the values for recharge of mobile
	  * @author Smruti
	 * @throws Exception 
	  * 
	  */
	 
	 public String enterCheckinDate(String checkInDate) {
		 logger.info("Enter Check in Date");
				try {
					_driver.findElement(By.xpath(checkinDate)).clear();
					_driver.findElement(By.xpath(checkinDate)).sendKeys(checkInDate);
					logger.info("Entered check in date");
				} catch (NoSuchElementException ne) {
					logger.error("Not able Enter check in date");
					return "FAIL";
				}
				return "PASS";
			}
	 
	 
	 /**
	  * <p>This method is used to enter the amount value for recharge
	  * @author Smruti
	 * @throws Exception 
	  * 
	  */
	 public String enterCheckoutDate(String checkOutDate) {
		 logger.info("Enter Check out Date");
				try {
					_driver.findElement(By.xpath(checkoutDate)).clear();
					_driver.findElement(By.xpath(checkoutDate)).sendKeys(checkOutDate);
					logger.info("Entered check out date");
				} catch (NoSuchElementException ne) {
					logger.error("Not able Enter check out date");
					return "FAIL";
				}
				return "PASS";
			}
	 
	 
	 /**
	  * <p>This method is used to enter the email ID field
	  * @author Smruti
	  * 
	  */
	
		 
	 public String noOfAdultsAndChild() 
	 {
		 logger.info("keep the default  value of adults and childs");
		 try {
			 _driver.findElement(By.xpath(noOfAdults)).isDisplayed();
			 _driver.findElement(By.xpath(noOfChilds)).isDisplayed();
			 logger.info("No of Adults and childs are  entered ");
		} catch (Exception e) {
			 logger.info("Default value of Adults and Childs are maintained");
			 return "FAIL";
		}
		return "PASS";
	 }
	 /**
	  * <p>This method is used to click on proceed button to goto Payment detail page
	  * @author Smruti
	  * 
	  */
			
		public String clickSearchBtn() 
		{
		logger.info("click on search button");
			try {
				 _driver.findElement(By.xpath(searchButton)).click();
				 _driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				 logger.info("Click on Search button ");
			} catch (Exception e) {
				 logger.info("Unable to click on search button");
				 return "FAIL";
			}
			return "PASS";
		}	
}
