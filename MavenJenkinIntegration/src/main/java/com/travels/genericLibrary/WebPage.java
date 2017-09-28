package com.travels.genericLibrary;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author rcotterill
 * <p>
 * Defines methods that all PageObjects should implement
 * <p>
 *
 */
public interface WebPage {
	/**
	 * 
	 * @return the title of the site
	 */
	public String getTitle();
	/**
	 * 
	 * @return A String provided so the user can return information 
	 * captured from the page.  Otherwise, return the String "success"
	 */
	public String validatePage();
	/**
	 * 
	 * @return  The WebDriver object used for the browser
	 */
	public WebDriver getDriver();

}
