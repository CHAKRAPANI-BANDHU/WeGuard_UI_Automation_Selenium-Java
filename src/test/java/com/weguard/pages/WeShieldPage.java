package com.weguard.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class WeShieldPage {

	WebDriver driver;
	ExtentTest test;
	Logger logger;

	public WeShieldPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		driver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
	}
	
	// To get the reports URL
	@FindBy(xpath = "//*[@href=\"#/weshield\"]")
	@CacheLookup
	WebElement WeShieldURL;
	
	public void getWeShieldURLClick() {
		WeShieldURL.click();
	}
	
}
	

