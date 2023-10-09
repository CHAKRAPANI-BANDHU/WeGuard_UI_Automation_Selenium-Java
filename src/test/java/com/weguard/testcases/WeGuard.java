//package com.weguard.testcases;
//
//import java.io.IOException;
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.weguard.pages.DashboardPage;
//import com.weguard.pages.LoginPage;
//
//public class WeGuard extends Login {
//	
//	@Test(priority = 1)
//	public void login() throws InterruptedException, IOException {
//
//		ExtentTest test = extent.createTest("Login_Testcases").assignAuthor("QA_Wenable").assignDevice("Chrome");
//
//		// Accessing the WeGuard URL
//		driver.get("https://qa-cloud.weguard.io/#/login");
////		driver.get(System.getenv("WEGUARD_URL"));
//		
//		logger.info("Navigating to WeGuard Login page");
//		logger.info("WeGuard URL is launched");
//		test.info("Executing Login Testcase");
//		test.info("Title of the page: " + driver.getTitle());
//		LoginPage lp = new LoginPage(driver);
//		
//		// portal version
//		String pv = lp.portalVersion();
//		test.info(pv);
//		
//		lp.setUserId("chakrapani.bandhu@weguard.com");
////		lp.setUserId(System.getenv("WEGUARD_USERNAME"));
//		test.info("Entered User ID");
//		lp.setPassword("Wenable@473");
////		lp.setPassword(System.getenv("WEGUARD_PASSWORD"));
//		test.info("Entered Password");
//		lp.rememberMe(("RememberMe"));
//		lp.clickLogin();
//		Thread.sleep(8000);
//		if (driver.getCurrentUrl().equals("https://qa-cloud.weguard.io/#/dashboard"))
//		{
//			test.pass("Successfully Logged In.");
//			logger.info("Successfully Logged In.");
//		}
//		else if (driver.findElement(By.xpath("//*[@href=\"#/dashboard\"]")).isDisplayed()) {
//			test.pass("Dashboard page is launched");
//			Thread.sleep(3000);
//			logger.info("Dashboard page is launched");
//		} else {
//			test.info("Page is loading");
//			String screenshotPath = getScreenshot(driver, "dashboardisnotnavigatedFailedScreenshot");
//			test.fail("Failed to Login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			driver.quit();
//		}
//	}
//	
//	@Test(priority = 2)
//	void dashboard() throws InterruptedException, IOException 
//	{
//		ExtentTest test = extent.createTest("Dashboard_Testcases").assignAuthor("QA_Wenable").assignDevice("Chrome");
//		logger.info("Navigating to the Dashboard page");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		DashboardPage db = new DashboardPage(driver, test, logger);
//		
//		// Dashboard Page
//		try {
//		    db.getdashboardURL();
//			test.pass("Dashboard module is displayed on the left navigation bar");
//		    test.info("Executing Dashboard Features Testcases");
//		    Thread.sleep(5000);
//		
//		// Logo check (Top left corner)
//		try {
//			db.getLogo();
//			test.pass("Logo is displayed");
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardLogoFailedScreenshot");
//			test.fail("Logo is not displayed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Hamburger icon is present or not
//		try {
//			db.hamburgerIcon();
//			test.pass("Hamburger icon is displayed");
//
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardHamburgerIconFailedScreenshot");
//			test.fail("Hamburger icon is not displayed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Display of Active Users and count
//		try {
//			db.activeUsers();
//			String ActiveUser_Count = db.activeUsersCount();
//			test.pass("Active Users: " + ActiveUser_Count);
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardActiveUsersFailedScreenshot");
//			test.fail("The Active Users count is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//
//		// Display of Active Devices and count
//		try {
//			db.activeDevices();;
//			String ActiveDevices_Count = db.activeDevicesCount();
//			test.pass("Active Devices: " + ActiveDevices_Count);
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardActiveDevicesFailedScreenshot");
//			test.fail("The Active Devices count is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Non-Compliant
//		try {
//			db.nonCompliant();
//			String NonCompliant_Count = db.nonCompliantCount();
//			test.pass("Non-compliant Devices: " + NonCompliant_Count);
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardNonCompliantDevicesFailedScreenshot");
//			test.fail("The Non-compliant devices count is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//
//		// Files
//		try {
//			db.files();
//			String Files_Count = db.filesCount();
//			test.pass("Files: " + Files_Count);
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardFilesCountFailedScreenshot");
//			test.fail("Files count is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Messages
//		try {
//			db.messages();
//			String Messages_Count = db.messagesCount();
//			test.pass("Messages: " + Messages_Count);
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardMessagesCountFailedScreenshot");
//			test.fail("Messages count is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Calls
//		try {
//			db.calls();
//			String Calls_Count = db.callsCount();
//			test.info("Calls: " + Calls_Count);
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardCallsCountFailedScreenshot");
//			test.fail("Calls count is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//
//		// Device status wise Pie-Chart
//		try {
//			db.devicesStatusPieChart();
//			test.info("Device status pie-chart is displayed");
//
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardDeviceStatusPieChartFailedScreenshot");
//			test.fail("Device status pie-chart is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//
//		// Device check-in graph
//		try {
//			db.devicesCheckinBarGraph();
//			test.info("Device check-in bar graph is displayed");
//
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardDeviceCheckInFailedScreenshot");
//			test.fail("Device check-in graph is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Manufacturer wise pie-chart
//		try {
//			db.manufacturerPieChart();
//			test.pass("Manufacturer pie-chart is displayed.");
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardDeviceCheckInFailedScreenshot");
//			test.fail("Manufacturer pie-chart is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//
//		// Recently enrolled Devices
//		try {
//			
//			test.pass("Recently enrolled devices list is displayed.");
//			db.recentlyEnrolledDevicesList();
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardRecentlyEnrolledDevicesScreenshot");
//			test.fail("Recently enrolled devices list is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// High Data Consuming Devices
//		try {
//			test.pass("High Data Consuming Devices list is displayed.");
//			db.highDataConsumingDevicesList();
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardHighDataConsumingDevicesFailedScreenshot");
//			test.fail("High Data Consuming Devices list is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		
//		// Recent Activity
//		try {
//			int activity_num = db.recentActivity();
//			test.info("No. of recent activities displayed: " + activity_num);
//			if (activity_num != 100) {
//				test.pass("Recent activity count is not equal to 100");
//			}
//		} catch (NoSuchElementException e) {
//			test.info(e);
//			String screenshotPath = getScreenshot(driver, "dashboardRecentActivityScreenshot");
//			test.fail("Recent activity list is not displayed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//	} catch (NoSuchElementException e) {
//		test.info(e);
//		String screenshotPath = getScreenshot(driver, "dashboardFailedScreenshot");
//		test.fail("Dashboard module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//	}
//  }
//
//}
