package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.WeShieldPage;

public class WeShield extends Login {

	@Test(priority = 13)
	void weshield() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("WeShield_Testcases").assignAuthor("QA_Wenable").assignDevice("Chrome");
		logger.info("Navigating to the WeShield page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WeShieldPage weshield = new WeShieldPage(driver, test, logger);
		// WeShield Page
		try {
			weshield.getWeShieldURLClick();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (driver.getCurrentUrl().equals("https://qa-cloud.weguard.io/#/weshield"))
			{
			test.pass("WeShield module is displayed on the left navigation bar in this account");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	// Overview tab
			try {
				//driver.findElement(By.xpath("//span[text()='Overview']")).click();
				test.pass("Overview tab is clicked");
				// Get the Columns names and devices list
				WebElement columnNames = driver.findElement(By.xpath("//mat-header-row[@role='row']"));
				String columns = columnNames.getText();
				test.info(columns);
				
				WebElement devicesList = driver.findElement(By.xpath("//mat-row[@role='row']"));
				String listOfDevices = devicesList.getText();
				test.info(listOfDevices);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "weshieldOverviewTabScreenshot");
				test.fail("WeShield module is not displayed on the left navigation bar in this account",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			
	// Scan Results tab
			try {
				driver.findElement(By.xpath("//span[contains(text(),'Scan Results')]")).click();
				test.pass("Scan Results tab is clicked");
				// Get the Columns names and devices list
				WebElement columnNames = driver.findElement(By.xpath("//mat-header-row[@role='row']"));
				String columns = columnNames.getText();
				test.info(columns);
				
				WebElement devicesList = driver.findElement(By.xpath("//mat-row[@role='row']"));
				String listOfDevices = devicesList.getText();
				test.info(listOfDevices);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "weshieldScanResultsTabScreenshot");
				test.fail("WeShield module is not displayed on the left navigation bar in this account",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}		
			
	// Threats tab
			try {
				driver.findElement(By.xpath("//span[text()='Threats']")).click();
				test.pass("Threats tab is clicked");
				// Get the Columns names and devices list
				WebElement threats = driver.findElement(By.xpath("//mat-header-row[@role='row']"));
				String threatsdetails = threats.getText();
				test.info(threatsdetails);
				
				WebElement devicesList = driver.findElement(By.xpath("//mat-row[@role='row']"));
				String listOfDevices = devicesList.getText();
				test.info(listOfDevices);

			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "weshieldThreatsTabScreenshot");
				test.fail("WeShield module is not displayed on the left navigation bar in this account",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}	
			
		 }
		}catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "weshieldFailedScreenshot");
			test.fail("WeShield module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}
