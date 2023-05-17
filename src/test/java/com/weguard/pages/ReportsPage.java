package com.weguard.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class ReportsPage {
	WebDriver driver;
	ExtentTest test;
	Logger logger;

	public ReportsPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		driver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
	}

	// To get the reports URL
	@FindBy(xpath = "//a[@href='#/reports']")
	@CacheLookup
	WebElement ReportsURL;

	// To get the reports URL
	@FindBy(xpath = "//*[@class = 'reports-card-inner']")
	@CacheLookup
	List<WebElement> TotalReports;

	// Click on the WeGuard Version Distribution Reports
	@FindBy(xpath = "//*[text() ='WeGuard Version Distribution Reports']")
	@CacheLookup
	WebElement WVDReports;

	// Get the title of WeGuard Version Distribution Reports
	@FindBy(xpath = "(//p[@class='categ-heading'])[1]")
	@CacheLookup
	WebElement WVDRTitle;

	// Get the sub title of WeGuard Version Distribution Reports
	@FindBy(xpath = "//p[contains(text(),'Know the version of WeGuard application installed')]")
	@CacheLookup
	WebElement WVDRSubTitle;

	// Cache of Devices
	@FindBy(xpath = "(//p[@class='categ-heading'])[1]")
	@CacheLookup
	WebElement CacheDevices;

	// Pie Chart Information
	@FindBy(xpath = "//div[@class='pie-chart']")
	@CacheLookup
	WebElement PieChartInfo;

	// Header Row
	@FindBy(xpath = "//mat-header-row[@role='row']")
	@CacheLookup
	WebElement HeaderRow;

	// List of Devices/Apps/Logs, etc information
	@FindBy(xpath = "//mat-row")
	@CacheLookup
	List<WebElement> List;

	// Column Header
	@FindBy(xpath = "//*[@role='columnheader']")
	@CacheLookup
	List<WebElement> ColumnHeader;

	// Download Reports button
	@FindBy(xpath = "//button[contains(@class,'mat-focus-indicator download-btn')]//span[1]")
	@CacheLookup
	WebElement DownloadReports;

	// Download button in the Download Reports
	@FindBy(xpath = "//div[@class='download-action-div']//button[1]")
	@CacheLookup
	WebElement Download;

	// Devices State Drop-down
	@FindBy(xpath = "(//span[text()='Include devices by state'])[1]")
	@CacheLookup
	WebElement ClickDevicesStateDropdown;

	// Devices State Drop-down
	@FindBy(tagName = "a")
	@CacheLookup
	List<WebElement> DropdownOptionOfDevicesState;

	// Devices State Drop-down List
	@FindBy(xpath = "//*[@class='mat-option-text']")
	@CacheLookup
	List<WebElement> DropdownListOfDevicesState;

	// Back Arrow Icon in the Reports
	@FindBy(xpath = "//*[@mat-tooltip='Back']")
	@CacheLookup
	WebElement BackArrowIconInReports;
	
	
	
	// Click on the Devices Enrollment Reports
	@FindBy(xpath = "//*[text()='Device Enrollment Reports']")
	@CacheLookup
	WebElement DERReports;

	// Click on the Filter By Group Names in the Devices Enrollment Reports
	@FindBy(xpath = "//div[contains(@class,'p-multiselect-label ng-tns-c262')]")
	@CacheLookup
	WebElement FilterByGroupNameInDERReports;
	
	
	// Click on the select custom/store application drop-down in the Custom/Store Application Distribution Reports
	@FindBy(xpath = "(//*[@role='combobox'])[2]")
	@CacheLookup
	WebElement SelectCustomStoreApplication;
	
	// Get the apps list from the select custom/store application drop-down in the Custom/Store Application Distribution Reports
	@FindBy(xpath = "(//mat-option[@role='option'])")
	@CacheLookup
	List<WebElement> GetCustomStoreApplicationList;
	
	
	
	
	
	

	public WebElement getReportsURL() {
		ReportsURL.click();
		return ReportsURL;
	}

	public int getTotalReports() {
		int i = TotalReports.size();
		return i;
	}

	public void getWVDRReports() {
		test.pass("Launching the WeGuard Version Distribution Reports");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.elementToBeClickable(WVDReports));
		WVDReports.click();
	}

	public void getWVDRTitle() {
		String WVDReportsTitle = WVDRTitle.getText();
		test.info(WVDReportsTitle);
	}

	public void getWVDRSubTitle() {
		WebElement text = WVDRSubTitle;
		String subTitle = text.getText();
		test.info(subTitle);
	}

	public void getCacheDevice() {

		WebElement element = CacheDevices;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void getPieChartInfo() {
		try {
		WebElement piechartElement = PieChartInfo;
		String piechart = piechartElement.getText();
		test.info(piechart);
		test.info("Pie-chart of Reports is displayed");
		} catch (NoSuchElementException e) {
			test.info(e);
			test.info("Pie-chart of Reports is not displayed");
		}
	}
	

	public void getHeaderRows() {
		WebElement headerrow = HeaderRow;
		String WVDRHeadersRow = headerrow.getText();
		test.info("" + WVDRHeadersRow);
	}

	public void getRows() {

			int rows = List.size();
			for (int i = 1; i < rows; i++) {
                List.get(i).isDisplayed();
				test.info(List.get(i).getText());
				logger.info("List is displayed");
			}
		test.info("List is displayed");
	}

	public void getColumnHeaderOfWVDR() {

		String[] expectedColheader = { "DEVICE ID", "VERSION" };
		List<WebElement> actualColheader = ColumnHeader;
		if (expectedColheader.length != actualColheader.size()) {
			test.info("Column header count is not matched with the expected.");
		}
		if (actualColheader.size() != expectedColheader.length) {
			test.fail("Actual column header count does not match expected count");
		} else {
			for (int i = 0; i < expectedColheader.length; i++) {
				String optionValue_CH = actualColheader.get(i).getText();
				if (optionValue_CH.equals(expectedColheader[i])) {
					test.info("Passed on: " + optionValue_CH);
				} else {
					test.fail("Failed on: " + optionValue_CH);
				}
			}
		}
	}

	public void getDownloadReports() {
		WebElement downloadreports = DownloadReports;
		test.info("The Download Reports is displayed");
		downloadreports.click();
	}

	public void getDownload() {
		WebElement download = Download;
		test.info("The Download button is displayed");
		download.click();
	}

	public void getDevicesStateDropdownOptions() {
		List<WebElement> options = DropdownOptionOfDevicesState;
		// Retrieve the text of each option and print it
		for (WebElement option : options) {
			test.info(option.getText());
		}
	}

	public void getDevicesStateDropdown() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ClickDevicesStateDropdown));
	        element.click();

	        // Find all the options in the drop-down
	        List<WebElement> options = DropdownListOfDevicesState;

	        // Retrieve the text of each option and print it
	        for (WebElement option : options) {
	            test.info(option.getText());
	        }

	        String[] expectedOptions = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
	        List<WebElement> actualOptions = DropdownListOfDevicesState;
	        if (expectedOptions.length != actualOptions.size()) {
	            test.fail("The count of options in the drop-down list does not match the expected count.");
	        }

	        for (int j = 0; j < expectedOptions.length; j++) {
	            String optionValue = actualOptions.get(j).getText();
	            if (optionValue.equals(expectedOptions[j])) {
	                test.info("The drop-down option value is matched with: " + optionValue);
	            } else {
	                test.fail("The drop-down option value is not matched with " + optionValue);
	            }
	        }
	    } catch (TimeoutException e) {
	    	test.fail(e);
	        test.fail("Timed out waiting for the DevicesStateDropdown element to be clickable.");
	    } catch (NoSuchElementException e) {
	    	test.fail(e);
	        test.fail("The DevicesStateDropdown element was not found on the page.");
	    }
	}

	public void getClickBackArrowIcon() throws InterruptedException {
		Actions ClickBackArrowIcon = new Actions(driver);
		WebElement back_button = BackArrowIconInReports;
		ClickBackArrowIcon.moveToElement(back_button).click().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		back_button.click();
	}

	public void getDERReports() {
		String title = DERReports.getText();
		test.info(title);
		test.pass("Launching the Devices Enrollment Reports");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.elementToBeClickable(DERReports));
		DERReports.click();
	}

	public void getColumnHeaderOfDER() {

		String[] expectedColheader2 = { "DEVICE ID", "ACTIVATION TIME", "GROUP NAME" };
		List<WebElement> actualColheader2 = ColumnHeader;
		if (expectedColheader2.length != actualColheader2.size()) {
			test.info("Column header count is not matched with the expected.");
		}
		if (actualColheader2.size() != expectedColheader2.length) {
			test.fail("Actual column header count does not match expected count");
		} else {
			for (int i = 0; i < expectedColheader2.length; i++) {
				String optionValue_CH = actualColheader2.get(i).getText();
				if (optionValue_CH.equals(expectedColheader2[i])) {
					test.info("Passed on: " + optionValue_CH);
				} else {
					test.fail("Failed on: " + optionValue_CH);
				}
			}
		}

	}

	public void getFilterByGroupNamesInDERClick() {

		// locate the drop-down/multi-select items from the drop-down
		WebElement dropdown = FilterByGroupNameInDERReports;
		dropdown.click();

		// locate the options container element
		WebElement optionsContainer1 = driver.findElement(By.xpath("//*[@class='p-multiselect-item p-ripple']"));

		// create a list of option elements in the container
		List<WebElement> options = optionsContainer1
				.findElements(By.xpath("//*[@class='p-multiselect-item p-ripple']"));

		// create an empty list to hold the option values
		List<String> optionValues = new ArrayList<String>();

		// iterate over the option elements and get their text
		for (WebElement option : options) {
			String text = option.getText();
			optionValues.add(text);

			// You can also print the option value to the console or a log file
			test.info(text);
		}
	}
	
	public void getAppListFromDropdown() {
		WebElement CustomStoreAppDropdown = SelectCustomStoreApplication;
		CustomStoreAppDropdown.click();

		List<WebElement> options1 = GetCustomStoreApplicationList;
		int count = options1.size();

		for (int n = 0; n < count; n++) {
			options1.get(n).getText();
			options1.get(n).click();

			// get the rows of the table
			List<WebElement> rows = List;

			// iterate over the rows and print the data
			for (WebElement row : rows) {
					test.info(row.getText());
				}
			PieChartInfo.getText();
			test.info("The Pie-chart of Custom/Store Application Distribution Reports is displayed");

			// locate and click the download reports and download buttons elements
			DownloadReports.click();
			Download.click();

			int x = count - 1;
			if (n != x) {
				CustomStoreAppDropdown.click();
				test.pass("Click is performed for dropdown");
			} else {
				test.fail("Click is not performed for dropdown");
			}
	   }
	}

}
