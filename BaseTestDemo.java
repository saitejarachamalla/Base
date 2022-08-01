package com.saiteja.automation.frameworks.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saiteja.automation.Utilities.Driverpaths;
import com.saiteja.automation.Utilities.ScreenshotUtility;
import com.saiteja.automation.customisedexceptions.FramewroksException;

public class BaseTestDemo {
	/*
	This method is used for opening browser
	param: which browser want to open(chrome,ie,firefox,edge,etc.)
	*/
	private static WebDriver driver;
	private static String tcName;
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	
	
	@Parameters({"browser"})
	@BeforeSuite
	public void openbroswer(@Optional("chrome")String browser) {
	if(browser.equalsIgnoreCase("firefox"))
	{
		System.setProperty(Driverpaths.firefoxkey, Driverpaths.firefoxvalue);
		driver = new FirefoxDriver();
		init();
	}
	else if (browser.equalsIgnoreCase("chrome")) {
		System.setProperty(Driverpaths.chromekey, Driverpaths.chromevalue);
		driver = new ChromeDriver();
		init();
	}
	else if (browser.equalsIgnoreCase("ie")) {
		System.setProperty(Driverpaths.iekey, Driverpaths.ievalue);
		driver = new InternetExplorerDriver();
		init();
	}
	}
	public void init() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		}
	/*
	 To close the browser
	 */
	@AfterSuite
	public void closebrowser() throws FramewroksException {
		if(driver !=null) {
			driver.close();
		}
			else {
				FramewroksException exception =  new FramewroksException("Diver is pointing to null");
				//throw exception
				System.out.println("DRIVER IS POINTING TO NULL");
				throw exception;
			}
		
	}
	
	/*
	Here i'm passing a parameter called method. 
	Method is a class which contains information about a method or class or interface  
	*/
	
	@BeforeMethod
	public void beforeTCExectuion(Method method) {
		tcName = method.getName();
		System.out.println("Current execution test case is: "+tcName);
		extentTest = extentReports.startTest(tcName);
		extentTest.log(LogStatus.PASS, "Current execution testcase is: "+tcName);
	}
	
	@AfterMethod
	public void afterTCExecution(ITestResult result) throws IOException {
		String tcName = result.getName();
		if(result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Test case is passed: "+tcName);
			extentTest.log(LogStatus.PASS, "Test case is passed: "+tcName);
			
		}
		else if(result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Test case is Failed: "+tcName);
			extentTest.log(LogStatus.FAIL, "Test case is Failed: "+tcName);
			extentTest.log(LogStatus.FAIL, result.getThrowable());
			ScreenshotUtility.takescreenshot();
			
		}
		if(result.getStatus() == ITestResult.SKIP) {
			System.out.println("Test case is Skipped: "+tcName);
			extentTest.log(LogStatus.SKIP, "Test case is Skipped: "+tcName);
			extentTest.log(LogStatus.SKIP, result.getThrowable());
			ScreenshotUtility.takescreenshot();
			
		}
		extentReports.endTest(extentTest);
		extentReports.flush();
	}
	
	
	@BeforeTest
	public void initReports() {
		extentReports =  new ExtentReports("H:\\javapractise\\frameworks\\Reports\\report.html");
		
	}
	
	@AfterTest
	public void generateReports() throws FramewroksException {
		if (extentReports!=null) {
			extentReports.close();
		}
		else {
			FramewroksException exception =  new FramewroksException("Extent reports is pointing to null");
			throw exception;
			//throw exception
		}
		
	}
	
	
	
	/*
	To access webDriver object outside the class
	*/
		public static WebDriver getDriver() {
			return driver;
		}
		
		/*
		To access webDriver object outside the class
		*/
		
		public static String getTcName() {
			return tcName;
		}
		
		/*
		To access extent test object outside the class
		*/
		
		public static ExtentTest getExtentTest() {
			return extentTest;
		}
}
