package com.travels.testclass;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.travels.genericLibrary.BrowserFactory;
import com.travels.genericLibrary.WebPage;
import com.travels.pageclass.HomePage;



	/**
	 * <p>Test Description: Verify Pharmacy Locator Login with Valid Memdber SignIn Information.
	 *	@author aswami 
	 */

    
	public class HotelSearch {	
	private static Map<String,WebPage> sessions;
	private static Logger logger;
	private static int testStep;
	private static String testStatus;
	private static String testDataXlFile;
	private static String testDataSheet;
	private static String testDataStartPoint;
    private static String testDataEndPoint;
	private static String testDescription;
	private static String driver;
	private static int    timeout;
	//private static String excelFilePath;
	//private static String tableEndName;
	//@Test(enabled = true, groups = { "Functional" })
	//@Parameters( { "mobileNo","operator","amount","emailId","cardNum","nameOnCard","month","year","cvv"})//reads data from xml
	@Test(enabled = true, groups = { "Functional" }, dataProvider = "DataProvider")
	public void PHPTravelsSearch(String hotelOrCityName,String checkINDate, String checkOUTDate) throws InterruptedException
	{
		testStep=1;
		testStatus="PASS";
		Reporter.log("<p> <b>Test Description: </b> <br> " + testDescription
				+ ".<br></p>");
		HomePage hp = (HomePage) sessions.get("HomePage");
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue> Open travels home page </font>");
		hp.openAppUrl();
		Reporter.log("Home page is opened");
		
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>get page title </font>");
		if(testStatus.equalsIgnoreCase(hp.getTitle())){
			Reporter.log("title of application is:"+hp.appTitle);
		}else{
			Reporter.log("Title of the application is not found");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}
		
		
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>validate page </font>");
		if(testStatus.equalsIgnoreCase(hp.validatePage())){
			Reporter.log("page is validated");
		}else{
			Reporter.log("page is not validated");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}
		/**
		 * Method that prints the test status message on TestNg reporter output
		 * 
		 * @author smmohapatra
		 *         <p>
		 * @param status
		 *            - Used to decide success or failure of test and print the
		 *            message to report accordingly
		 * @param stepNo
		 *            - To specify test step No where test failed on the report
		 */
		
		
		
		
		
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>click Hotels tab </font>");
		if(testStatus.equalsIgnoreCase(hp.clickHotelsTab())){
			Reporter.log("clicked on Hotel tab");
		}else{
			Reporter.log("Unable to click on Hotel tab");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}
	
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>enter location or Hotel name </font>");
		if(testStatus.equalsIgnoreCase(hp.enterCityOrHotelName(hotelOrCityName))){
			Reporter.log("location or hotel name is entered successfully:"+hotelOrCityName);
		}else{
			Reporter.log("Unable to enter location or hotel name");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}

		/*Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>select check-in date</font>");
		if(testStatus.equalsIgnoreCase(hp.enterCheckinDate(checkINDate))){
			Reporter.log("Check in date  is selected  successfully:"+checkINDate);
		}else{
			Reporter.log("Unable to select operator");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}

		
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>enter check-out date</font>");
		if(testStatus.equalsIgnoreCase(hp.enterCheckoutDate(checkOUTDate))){
			Reporter.log("check out date  is entered successfully:"+checkOUTDate);
		}else{
			Reporter.log("Unable to enter amount");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}*/
		
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>Keep default value of  adults and child</font>");
		if(testStatus.equalsIgnoreCase(hp.noOfAdultsAndChild())){
			Reporter.log("no of adults and child  are entered successfully.");
		}else{
			Reporter.log("Unable to enter no of adults and child");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}
		
		
		
		Reporter.log("<br>Step " + (testStep++)+ ":<font color=blue>click on search button </font>");
		if(testStatus.equalsIgnoreCase(hp.clickSearchBtn())){
			Reporter.log("Search button is clicked");
		}else{
			Reporter.log("Unable to click the search button");
			testStatus="FAIL";
			printTestStatus(testStatus,testStep-1);
			return;
		}
	}
		public void printTestStatus(String status,int stepNo){
			String stepStatus="PASS";
			if (stepStatus.equalsIgnoreCase(status)){
				testStatus="Passed";
				Reporter.log("<br><br><font color=Green> Test Status : "+ testStatus +" </font>");
			}
			else{
				testStatus="Failed";
				Reporter.log("<br><br><font color=Red> Test Status : "+ testStatus +" <br> Review Step:" + stepNo+"</font>");
			}
		
		
	}
		/**
		 * Method that set the values for variables which are used in DataProvider
		 * @author aswami
		 * <p>
		 * @param testDataFile  - name of excel file having test data
		 * @param dataSheet - name of the sheet in excel file
		 * @param startCell - start point from where test data begins in the sheet
		 * @param endCell -	end point from where test data ends in the sheet
		 * values for all parameters above are specified in testng xml file which is located under resource folder
		 */
		
	/**
	 * Method to shut down the session and close the browser
	 * @author aswami
	 * <p>
	 * @param driver - the Browser type associated with the session
	 * 
	 */
				
				
				@BeforeClass(enabled = true, groups = { "Functional" }) 
				@Parameters( { "driver", "timeout",})
				public void beforeClass(String browserDriver, int timeOut){
					  driver=browserDriver;
					  timeout=timeOut;
					  sessions = new HashMap<String, WebPage>();
					  WebDriver webdriver = null;
					  logger = Logger.getLogger(this.getClass().getName());
					 logger.info("select the driver");
					  BrowserFactory bf = new BrowserFactory(driver);
					  webdriver = bf.getWebDriver();
					 logger.info("return the driver");		   
					  try {
						  /* save the session so it can be passed to other tests  */
						  HomePage hp = new HomePage(webdriver, timeout);
						  sessions.put("HomePage", hp);
					  } catch(Exception e) {
						  logger.info("exception*******************"+e.getMessage());
						  webdriver.quit();
					  }

					}
				@BeforeClass(enabled = true, groups = { "Functional" }) 
				@Parameters( {"testDataXlFileName","testDataSheetName","testDataStartCell","testDataEndCell"})
				public void setTestData(String testDataXlFileName, String testDataSheetName,String testDataStartCell,String testDataEndCell){
					testDataXlFile =testDataXlFileName ;
					testDataSheet =testDataSheetName ;
					testDataStartPoint=testDataStartCell;
					testDataEndPoint=testDataEndCell;
				}
				
		@AfterClass(enabled = true, groups = { "Functional" },alwaysRun=true)
		@Parameters( { "driver" })
		public void afterClass(String driver) {
				sessions.get("HomePage").getDriver().quit();
		}
		
	
	/**
	 * <p>Method to Create data provider which returns array of Objects containing test data to @Test Method
	 * @author aswami
	 * @param testDataXlFile - xls file name for reading 
	 * @param testDataSheet - xl sheet name to read 
	 * @param testDataStartPoint - cell value  to start reading test data  
	 * @param testDataEndPoint - cell values to stop reading test data
	 * @return - Array of Objects containing test data
	 * */
	@DataProvider(name = "DataProvider", parallel = false)
	public Object[][] dataProvider() throws Exception {

    Object[][] retObjArr=getTableArray(testDataXlFile,testDataSheet, testDataStartPoint, testDataEndPoint);
        return(retObjArr);
	}
	
	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableStartName, String tableEndName) throws Exception {
		String[][] tabArray = null;
		Workbook workbook = Workbook.getWorkbook(
				this.getClass().getClassLoader().getResourceAsStream(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow,startCol,endRow,endCol,ci,cj;
		Cell tableStart = sheet.findCell(tableStartName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();
		//System.out.println(sheet.getCell(6, 7).getContents());
		//Cell tableEnd= sheet.getCell(6, 7);
		Cell tableEnd= sheet.findCell(tableEndName, startCol+1,startRow+1, 100, 100,  false);
		//Cell tableEnd= sheet.findCell(tableEndName, startCol+1,startRow+1, 100, 64000,  false); 
		endRow=tableEnd.getRow();
		endCol=tableEnd.getColumn();
		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci=0;
		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				System.out.println(tabArray[ci][cj]);
			}
		}

		return (tabArray);
	
	}
	
    


}





