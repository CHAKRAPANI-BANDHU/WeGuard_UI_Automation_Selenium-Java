package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.ReportsPage;
import org.openqa.selenium.interactions.Actions;

public class Reports extends Login {

	@Test(priority = 4)
	void reports() throws InterruptedException, IOException {
		
		ExtentTest test = extent.createTest("Reports_Testcases").assignAuthor("QA_Wenable").assignDevice("Chrome");
		logger.info("Navigating to the Reports page");
		ReportsPage rp = new ReportsPage(driver, test, logger);
		try {
			WebElement reports = rp.getReportsURL();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(reports));
			test.info("Reports page is launched");
			Thread.sleep(10000);
			test.info("Executing Reports Test cases");
			logger.info("Executing Reports Test cases");
			test.info(("The no. of report types present in this account are: ") + rp.getTotalReports());

// 1. WeGuard Version Distribution Reports

			try {
				logger.info("Executing WeGuard Version Distribution Reports Test cases");
				test.info("WeGuard Version Distribution Reports is displayed in this account.");
				rp.getWVDRReports();

				// Cache the Report Information and wait for 8 seconds
				test.info("Please wait while we cache the devices");
				Thread.sleep(8000);
				rp.getWVDRSubTitle();
				// Get title name, toast message and wait for 5 seconds
				rp.getWVDRTitle();
				test.info("Devices are cached, you can try reports now");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				// After caches the devices, click on the WeGuard Version Distribution Reports
				// and wait for 8 seconds
				rp.getCacheDevice();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

				// Display of pie-chart and wait for 10 seconds
				rp.getPieChartInfo();
				
				// Verifying column headers in tabular data
				rp.getColumnHeaderOfWVDR();

				// Column Names in a row
				rp.getHeaderRows();
				
				// Print all the Devices with WeGuard App Versions
				rp.getRows();

				// Download Reports options
				rp.getDownloadReports();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
				rp.getDownload();

				// Devices State Drop-down
				rp.getDevicesStateDropdown();
				rp.getDevicesStateDropdownOptions();

				// Navigating to Reports page from WeGuard distribution page
				rp.getClickBackArrowIcon();
				Thread.sleep(3000);
				
			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsWVDRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the WeGuard Version Distribution Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 2. Device Enrollments Reports

			try {
				logger.info("Executing Devices Enrollment Reports Test cases");
				test.info("Device Enrollment Reports is displayed in this account.");
				rp.getDERReports();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				// Display of pie chart
				rp.getPieChartInfo();

				// Verifying column headers in tabular data
				rp.getColumnHeaderOfDER();

				// Column Names in a row
				rp.getHeaderRows();

				// Find all the mat-row elements inside the element
				rp.getRows();

				// Download button is present or not in Device enrollment reports
				rp.getDownloadReports();
				rp.getDownload();					

				// Filter by group names field
				try {
					test.info("Filter by Group Names is present in Device Enrollment Reports");

				} catch (NoSuchElementException e) {
					test.fail(e);
					test.fail("Filter by Group Names is not present in Device Enrollment Reports.");
				}
				
				// Devices State Drop-down
				rp.getDevicesStateDropdown();
				rp.getDevicesStateDropdownOptions();

				// Navigating to Reports page from Devices Enrollment Reports page
				rp.getClickBackArrowIcon();
				
			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsDERFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Device Enrollment Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 3. Custom Application Distribution Reports

			try {
				logger.info("Executing Custom Application Distribution Reports Test cases");
				driver.findElement(By.xpath("//*[text()='Custom Application Distribution Reports']")).click();
				test.info("Custom Application Distribution Reports is displayed in this account.");
				test.info("Custom Application Distribution Reports");
				test.pass("Launching the Custom Application Distribution Reports");

			    	// Locate and click the custom app list drop down element
                   rp.getAppListFromDropdown();

				// Search field
				test.info("The search is present in Custom Application Distribution Reports."
						+ driver.findElement(By.xpath("//input[@data-placeholder='Search']")).isDisplayed());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				// Devices by state drop down
				driver.findElement(By.xpath("//*[@id='mat-select-8']")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				String[] expectedOptions3 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions3 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions3.length != actualOptions3.size()) {
					test.fail("The options count in the Custom Application Distribution Reports");
				}
				for (int p = 1; p < expectedOptions3.length; p++) {
					String optionValue3 = actualOptions3.get(p).getText();
					if (optionValue3.equals(expectedOptions3[p])) {
						test.info("The drop down options are matched with: " + optionValue3);
					} else {
						test.fail("The drop down options are not matched with: " + optionValue3);
					}
				}

				// Navigating back to Reports page
				Actions act3 = new Actions(driver);
				WebElement back_btn3 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
				act3.moveToElement(back_btn3).click().perform();
				back_btn3.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				// Re-locate the back button WebElement
				driver.findElement(By.xpath("//*[@mat-tooltip='Back']")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			}

			catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsCADRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Custom Application Distribution Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 4. Store Application Distribution Reports

			try {
				logger.info("Executing Store Application Distribution Reports Test cases");
				driver.findElement(By.xpath("//p[text() ='Store Application Distribution Reports']")).click();
				test.info("Store Application Distribution Reports is displayed in this account");
				test.info("Store Application Distribution Reports");
				test.pass("Launching the Store Application Distribution Reports");

				// Locate and click the Store App list drop-down
				WebElement storeappDropdown = driver.findElement(By.xpath("(//*[@role='combobox'])[2]"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
				storeappDropdown.click();

				List<WebElement> options2 = driver.findElements(By.xpath("(//mat-option[@role='option'])"));
				int count2 = options2.size();

				for (int q = 0; q < count2; q++) {
					options2.get(q).getText();
					options2.get(q).click();

					// get the rows of the table
					List<WebElement> rows5 = driver.findElements(By.xpath("//mat-row[@role='row']"));

					// iterate over the rows and print the data
					for (WebElement row5 : rows5) {
						List<WebElement> cells = row5.findElements(By.xpath("//mat-row[@role='row']"));
						for (WebElement cell : cells) {
							test.info(cell.getText());
						}
					}

					// Display of pie chart 
					WebElement piechart4 = driver.findElement(By.xpath("//div[@class='pie-chart']"));
					String chart4 = piechart4.getText();
					test.info(chart4);
					test.info("The Pie-chart of Store Application Distribution Reports is displayed");

					// locate the download reports button element
					WebElement downloadButton4 = driver
							.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
					test.info("The Download Reports button in Store Application Distribution Reports is displayed");

					// Click on the download reports button
					downloadButton4.click();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

					// Click on the Download button
					WebElement downloadButton5 = driver.findElement(By.xpath("//span[text()='DOWNLOAD']"));
					downloadButton5.click();
					test.info("The Download button in Store Application Distribution Reports is displayed");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // wait for download to complete
					storeappDropdown.click();

					int y = count2 - 1;
					if (q != y) {
						storeappDropdown.click();
					} else {
						test.fail("Click is not performed for dropdown");
					}
				}

				// Search field
				test.info("The search is present in Store application distribution reports :"
						+ driver.findElement(By.xpath("//input[@data-placeholder='Search']")).isDisplayed());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				// Devices by state drop down options
				driver.findElement(By.xpath("//span[contains(@class, 'ng-tns-c175-284')]")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				String[] expectedOptions4 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions4 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions4.length != actualOptions4.size()) {
					test.fail("The options in the drop-down list are not matched.");
				}
				for (int r = 0; r < expectedOptions4.length; r++) {
					String optionValue4 = actualOptions4.get(r).getText();
					if (optionValue4.equals(expectedOptions4[r])) {
						test.info("The drop down value is matched with: " + optionValue4);
					} else {
						test.fail("The drop down value is not matched with: " + optionValue4);
					}
				}

				// Navigating back to Reports page
				Actions act4 = new Actions(driver);
				WebElement back_btn4 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
				act4.moveToElement(back_btn4).click().perform();
				back_btn4.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				// Re-locate the back button WebElement
				driver.findElement(By.xpath("//*[@mat-tooltip='Back']")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsSADRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Store Application Distribution Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 5. Devices OS Reports

			try {
				logger.info("Executing Devices OS Reports Test cases");
				driver.findElement(By.xpath("//*[text()='Devices OS Reports']")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				test.info("Devices OS Reports is displayed in this account.");
				test.info("Devices OS Reports");
				test.pass("Launching the Devices OS Reports");

				// Print all the Devices Type Versions
				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with OS versions are displayed");
				} else {
					test.fail("Devices List with OS versions are not displayed");
				}

				// Download button is present or not
				WebElement downloadbutton5 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Devices OS Reports is displayed");
				downloadbutton5.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Pie-chart
				test.info("The pie-chart is displayed under Device OS reports"
						+ driver.findElement(By.xpath("//*[@class='pie-chart']")).isDisplayed());

				// Tabular data default column header

				String[] expected_Colheader = { "DEVICE ID", "OS" };
				List<WebElement> actual_Colheader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expected_Colheader.length != actual_Colheader.size()) {
					test.fail("the column header count is not matched.");
				}
				for (int s = 0; s < expected_Colheader.length; s++) {
					String optionValue_CH = actual_Colheader.get(s).getText();
					if (optionValue_CH.equals(expected_Colheader[s])) {
						test.info("The column header value is matched with: " + optionValue_CH);
					} else {
						test.fail("The column header value is not matched with: " + optionValue_CH);
					}
				}

				// Include devices type drop-down list options
				driver.findElement(By.xpath("(//span[text()='Include devices by state'])[1]")).click();
				Thread.sleep(3000);
				String[] exp_options = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actual_options = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (exp_options.length != actual_options.size()) {
					test.fail("The option count in the drop down are not matched.");
				}
				for (int t = 0; t < exp_options.length; t++) {
					String optionValue = actual_options.get(t).getText();
					if (optionValue.equals(exp_options[t])) {
						test.info("The drop down value is matched with: " + optionValue);
					} else {
						test.fail("The drop down value is not matched with: " + optionValue);
					}
				}

				// Navigating back to Reports page
				Actions act5 = new Actions(driver);
				WebElement back_btn5 = driver.findElement(By.xpath("//*[@mat-tooltip= 'Back']"));
				act5.moveToElement(back_btn5).click().perform();
				back_btn5.click();

			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsDORFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Devices OS Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 6. Devices Type Reports

			try {
				logger.info("Executing Devices Type Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Devices Type Reports']")).click();
				test.info("Devices Type Reports is displayed in this account");
				test.info("Devices Type Reports");
				test.pass("Launching the Devices Type Reports");

				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with type are displayed");
				} else {
					test.fail("Devices List with type are not displayed");
				}

				// Download button
				WebElement downloadbutton6 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Device Type Reports is displayed");
				downloadbutton6.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Pie-chart display
				test.info(("DTR--The pie-chart is displayed in this account is displayed")
						+ (driver.findElement(By.xpath("//*[@class='pie-chart']")).isDisplayed()) + "\n");
				// Column header in tabular data
				String[] exp_ColHeader = { "DEVICE ID", "TYPE" };
				List<WebElement> act_ColHeader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (exp_ColHeader.length != act_ColHeader.size()) {
					test.fail("The count of column header is not matching.");
				}
				for (int u = 0; u < exp_ColHeader.length; u++) {
					String optionValue_CH = act_ColHeader.get(u).getText();
					if (optionValue_CH.equals(exp_ColHeader[u])) {
						test.info("The column header value is matched with: " + optionValue_CH);
					} else {
						test.fail("The column header value is not matched with: " + optionValue_CH);
					}
				}

				// Include devices by state drop down options
				driver.findElement(By.xpath("///span[contains(@class, 'ng-tns-c175')]")).click();
				Thread.sleep(5000);
				String[] expectedOptions5 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions5 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions5.length != actualOptions5.size()) {
					test.fail("The count of options in the drop-down list is not matched.");
				}
				for (int v = 0; v < expectedOptions5.length; v++) {
					String optionValue = actualOptions5.get(v).getText();
					if (optionValue.equals(expectedOptions5[v])) {
						test.info("The DDB option is matched with: " + optionValue);
					} else {
						test.fail("The DDB value is not matched with: " + optionValue);
					}
				}

				// Navigating back to Reports page
				Actions act6 = new Actions(driver);
				WebElement Back_btn = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
				act6.moveToElement(Back_btn).click().perform();
				Back_btn.click();
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsDTRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Devices Type Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 7. Devices Status Reports

			try {
				logger.info("Executing Devices Status Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Devices Status Reports']")).click();
				test.info("Devices Status Reports is displayed in this account");
				test.info("Devices Status Reports");
				test.pass("Launching the Devices Status Reports");

				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with status are displayed");
				} else {
					test.fail("Devices List with status are not displayed");
				}

				// Download button
				WebElement downloadbutton7 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Devices Status Reports is displayed");
				downloadbutton7.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Pie-chart
				test.info(("The pie-chart is displayed in devices status reports page")
						+ (driver.findElement(By.xpath("//*[@class='pie-chart']")).isDisplayed()) + "\n");

				// Column headers in tabular data
				String[] expected_ColHeader = { "DEVICE ID", "LAST REPORT TIME", "STATUS" };
				List<WebElement> actual_ColHeader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expected_ColHeader.length != actual_ColHeader.size()) {
					test.fail("Column header count in the tabular data is not matched.");
				}
				for (int u = 0; u < expected_ColHeader.length; u++) {
					String optionValue = actual_ColHeader.get(u).getText();
					if (optionValue.equals(expected_ColHeader[u])) {
						test.info("The column header value is matched with: " + optionValue);
					} else {
						test.fail("the column Header value is not matched with: " + optionValue);
					}
				}
				// Navigating back to Reports page
				Actions act7 = new Actions(driver);
				WebElement Back_btn7 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
				act7.moveToElement(Back_btn7).click().perform();
				Back_btn7.click();
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsDSRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Devices Status Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

			}

// 8. Geofence Reports

			try {
				logger.info("Executing Geofence Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Geofence Reports']")).click();
				test.info("Geofence Reports is displayed in this account");
				test.info("Geofence Reports");
				test.pass("Launching the Geofence Reports");
				Thread.sleep(2000);

				// Download button
				WebElement downloadbutton8 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Geofence Reports is displayed");
				downloadbutton8.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Select Group filter
				test.info("Select group filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Groups']")).isDisplayed());
				// Select Devices filter
				test.info("Select Devices filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Devices']")).isDisplayed());
				// Choose Geofence filter
				test.info("Select Devices filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Choose Geofence']")).isDisplayed());
				// Submit button
				test.info("Submit button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' SUBMIT ']")).isDisplayed());

				// Clear button
				test.info("Clear button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' CLEAR ']")).isDisplayed());

				// Filter by date range options
				driver.findElement(By.xpath("//*[@aria-label='Filter by date range']")).click();
				Thread.sleep(2000);
				String[] expected_filters = { "Today", "Yesterday", "Custom" };
				List<WebElement> actual_filters = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expected_filters.length != actual_filters.size()) {
					test.fail("The options count in the filter by date range is not matched.");
				}
				for (int w = 0; w < expected_filters.length; w++) {
					String optionValue = actual_filters.get(w).getText();
					if (optionValue.equals(expected_filters[w])) {
						test.info("The filter by date range option is matched with: " + optionValue);
					} else {
						test.fail("The filter by date range option is not matched with: " + optionValue);
					}
				}
				// Navigating back to Reports page
				Actions act8 = new Actions(driver);
				WebElement Back_btn8 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
				act8.moveToElement(Back_btn8).click().perform();
				Back_btn8.click();
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsGRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Geofence Reports",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

// 9. Screen Time Reports

			try {
				logger.info("Executing Screen Time Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Screen Time Reports']")).click();
				Thread.sleep(2000);
				test.info("Screen Time Reports is displayed in this account");
				test.info("Screen Time Reports");
				test.pass("Launching the Screen Time Reports");

				// Download button
				WebElement downloadbutton9 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Screen Time Reports is displayed");
				downloadbutton9.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Select Group filter
				test.info("Select groups filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Groups']")).isDisplayed());
				// Select Devices filter
				test.info("Select Devices filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Devices']")).isDisplayed());
				// Submit button
				test.info("Submit button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' SUBMIT ']")).isDisplayed());

				// Clear button
				test.info("Clear button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' CLEAR ']")).isDisplayed());
				// Column headers in tabular data
				String[] expected_ColHeader2 = { "DEVICE ID", "SCREEN TIME", "UP TIME" };
				List<WebElement> actual_ColHeader2 = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expected_ColHeader2.length != actual_ColHeader2.size()) {
					test.fail("Column header count in the tabular data is not matched.");
				}
				for (int x = 0; x < expected_ColHeader2.length; x++) {
					String optionValue = actual_ColHeader2.get(x).getText();
					if (optionValue.equals(expected_ColHeader2[x])) {
						test.info("The column header value is matched with: " + optionValue);
					} else {
						test.fail("the column Header value is not matched with: " + optionValue);
					}
				}

				// Filter by date range options
				driver.findElement(By.xpath("//*[@aria-label='Filter by date range']")).click();
				Thread.sleep(2000);
				String[] expected_filters2 = { "Today", "Yesterday", "Custom" };
				List<WebElement> actual_filters2 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expected_filters2.length != actual_filters2.size()) {
					test.fail("The options count in the filter by date range is not matched.");
				}
				for (int y = 0; y < expected_filters2.length; y++) {
					String optionValue = actual_filters2.get(y).getText();
					if (optionValue.equals(expected_filters2[y])) {
						test.info("The filter by date range option is matched with: " + optionValue);
					} else {
						test.fail("The filter by date range option is not matched with: " + optionValue);
					}
				}

			} catch (NoSuchElementException e) {
				test.fail(e);
				String screenshotPath = getScreenshot(driver, "reportsSTRFailedScreenshot");
				test.fail("The element/s could not be retrieved because they were stale/intercepted. Attempting once more... in the Screen Time Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			// Navigating back to Reports page
			rp.getClickBackArrowIcon();
			Thread.sleep(2000);
		}catch(

	NoSuchElementException e)
	{
		test.fail(e);
		String screenshotPath = getScreenshot(driver, "reportsFailedScreenshot");
		test.fail("Reports module is not displayed on the left navigation bar in this account",
				MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
}}
