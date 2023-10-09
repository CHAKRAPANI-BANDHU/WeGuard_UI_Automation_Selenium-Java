package com.weguard.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//input[@id='mat-input-0']")
	@CacheLookup
	WebElement UserId;

	@FindBy(xpath = "//input[@id='mat-input-1']")
	@CacheLookup
	WebElement Password;
	
    @FindBy(xpath = "//mat-slide-toggle[@id='mat-slide-toggle-1']/label/div")
	@CacheLookup
    WebElement RememberMe;

	@FindBy(xpath = "(//button[@class='mat-focus-indicator fullWidth btnBorderRadius10 mat-raised-button mat-button-base mat-primary'])[1]")
	@CacheLookup
	WebElement loginButton;
	
	@FindBy(xpath = "(//ul[@class='footerUl']//span)[2]")
	@CacheLookup
	WebElement PortalVersion;

	public void setUserId(String uid) {
		UserId.sendKeys(uid);
	}

	public void setPassword(String pwd) {
		Password.sendKeys(pwd);
	}

	public void rememberMe(String rememberme) {
		RememberMe.click();
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	
	public String portalVersion() {
		String value = PortalVersion.getText();
		return value;
	}
}
