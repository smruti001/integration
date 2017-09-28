package com.travels.genericLibrary;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService.Builder;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * @author Smruti
 * <p>
 * Factory class to determine how to implement the browser type requested by the
 * test definition in the suite xml file
 * <p>
 * Supported browser types are passed in as a "driver" parameter from the testng.xml file.
 * The values recognized in the code are the following :
 * <ul>
 * <li>FirefoxDriver - run whatever Firefox browser is installed on the target PC which can be local or remote</li>
 * <li>ChromeDriver - run whatever Chrome browser is installed on the local PC - remote can be added if needed</li>
 * <li>InternetExplorer - run whatever IE browser is installed on the target PC</li>
 * <li>InternetExplorer8 - run the IE8 browser in the location identified in browser.properties</li>
 * <li>InternetExplorer9- run the IE8 browser in the location identified in browser.properties</li>
 * <li>HtmlUnitDriver_ie7 simulation</li>
 * <li>HtmlUnitDriver_ie8 simulation</li>
 * <li>HtmlUnitDriver_chrome - Chrome16 simulation</li>
 * <li>HtmlUnitDriver_firefox - Firefox10 simulation</li>
 * </ul> 
 * <p>
 * All HtmlUnitDriver tests will be run on the local machine 
 * 
 * <p>
 * The configuration of the test PCs (local and remote) is provided by the browser.properties file
 */
public class BrowserFactory {
	Map<String,String> browserSpec;
	WebDriver webDriver;
	static Logger logger;
	String ie_driver_path = "D:/Back up/ALL  SELENIUM JARS/IEDriverServer.exe";
	int ie_driver_port;
	String ie9_remote_hub_query;
	String ie8_remote_hub_query;
	String ff_remote_hub_query;
	String firefox;
	String ie8;
	String ie9;
	String chrome_driver_path;
	
	/**
	  * @param  browser_type String that specifies the Webdriver implementation for the session
	  * <p>
	  * parameter will be passed as a parameter from the testng.xml file
	  * 
	  * 
	  */
	public BrowserFactory(String browser_type) {
		Properties prop = new Properties();
		logger = Logger.getLogger(this.getClass().getName());
		 
        try {
            //load a properties file
           	prop.load(BrowserFactory.class.getClassLoader().getResourceAsStream("browser.properties"));
    		ie_driver_path = prop.getProperty("ie_driver_path");
    		ie_driver_port = Integer.parseInt(prop.getProperty("ie_driver_port"));
    		ie8_remote_hub_query=prop.getProperty("ie8_remote_hub_query");
    		ie9_remote_hub_query=prop.getProperty("ie9_remote_hub_query");
    		ff_remote_hub_query=prop.getProperty("ff_remote_hub_query");
    		firefox=prop.getProperty("firefox");
    	    ie8=prop.getProperty("ie8");
    	    ie9=prop.getProperty("ie9");
    	    chrome_driver_path = prop.getProperty("chrome_driver_path");
 
    	} catch (IOException e) {
    		logger.error("Could not find property file");
    		logger.error(e.getMessage());
        }
        
       System.setProperty("webdriver.chrome.driver", chrome_driver_path);
 
		/* select the browser simulation to use */
		if(browser_type.equals("HtmlUnitDriver_ie7")) {
			//webDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_7);
			((HtmlUnitDriver)webDriver).setJavascriptEnabled(true);
		} else if(browser_type.equals("HtmlUnitDriver_ie8")) {
			webDriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
			((HtmlUnitDriver)webDriver).setJavascriptEnabled(true);
		} else if(browser_type.equals("htmlUnitDriver_chrome")) {
			//webDriver = new HtmlUnitDriver(BrowserVersion.CHROME_16);
			((HtmlUnitDriver)webDriver).setJavascriptEnabled(true);	
		} else if(browser_type.equals("HtmlUnitDriver_firefox")) {
			//webDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10);
			((HtmlUnitDriver)webDriver).setJavascriptEnabled(true);
		} else if (browser_type.equals("ChromeDriver")) {
			webDriver = new ChromeDriver();
		} else if (browser_type.equals("FirefoxDriver")) {
			if(firefox.equals("local")) {
				webDriver = new FirefoxDriver();
			} else {
				DesiredCapabilities capabilities =
						DesiredCapabilities.firefox();
				try {
					webDriver = new RemoteWebDriver(new URL("ff_remote_hub_query"), capabilities);
				} catch(MalformedURLException e) {
					logger.error("Could not find property file");
					logger.error(e.getMessage());
				}
			}
		} else if (browser_type.equals("InternetExplorerDriver8")) {
			if(ie8.equals("local"))	{
				Builder builder = new Builder();
				File file = new File(ie_driver_path);
				builder.usingDriverExecutable(file);
				builder.usingPort(ie_driver_port);
				InternetExplorerDriverService ieService = builder.build();
				DesiredCapabilities capabilities =
						DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.VERSION, "8.0");
				webDriver = new InternetExplorerDriver(ieService, capabilities);
			} else {
				DesiredCapabilities capabilities =
				DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.VERSION, "8.0");
				try {
					webDriver = new RemoteWebDriver(new URL(ie8_remote_hub_query), capabilities);
				} catch(MalformedURLException e) {
					logger.error("Could not find property file");
					logger.error(e.getMessage());
				}
			}
		} else if (browser_type.equals("InternetExplorerDriver9")) {
			if(ie9.equals("local"))	{
				Builder builder = new Builder();
				File file = new File(ie_driver_path);
				builder.usingDriverExecutable(file);
				builder.usingPort(ie_driver_port);
				InternetExplorerDriverService ieService = builder.build();
				DesiredCapabilities capabilities =
						DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.VERSION, "9.0");
				webDriver = new InternetExplorerDriver(ieService, capabilities);
			} else {
				DesiredCapabilities capabilities =
				DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.VERSION, "9.0");
				try {
					webDriver = new RemoteWebDriver(new URL(ie9_remote_hub_query), capabilities);
				} catch(MalformedURLException e) {
					logger.error("Could not find the hub URL");
					logger.error(e.getMessage());
				}
			}
		} else if (browser_type.equals("InternetExplorerDriver")) {
			Builder builder = new Builder();
			File file = new File(ie_driver_path);
			builder.usingDriverExecutable(file);
			builder.usingPort(ie_driver_port);
			InternetExplorerDriverService ieService = builder.build();
			DesiredCapabilities capabilities =
					DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.VERSION, "9.0");
			webDriver = new InternetExplorerDriver(ieService, capabilities);
		}
		else {
				logger.error("unknown driver type");
		}
	}
	
	/**
	 * 
	 * @return webDriver the WebDriver object that encapsulates the browser
	 */
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}
