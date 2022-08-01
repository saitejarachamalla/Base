package com.saiteja.automation.frameworks.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;


public class Actionsengine extends BaseTestDemo {

	//one approach
	public static void enterUrl(String url) {
		try {
			getDriver().get(url);
			getExtentTest().log(LogStatus.PASS, "URL IS entered as: "+url);
			
			
		} catch (Exception exception) {
			getExtentTest().log(LogStatus.FAIL, "URL IS NOT entered as: "+url);
			
		}
	}
	
	//another approach
	public static void navigateToUrl(String url) {
		try {
			getDriver().navigate().to(url);
			getExtentTest().log(LogStatus.PASS, "URL IS entered as: "+url);
			
			
		} catch (Exception exception) {
			getExtentTest().log(LogStatus.FAIL, "URL IS NOT entered as: "+url);
			
		}
	}
	
	
	// data typing action (on which webelement you want to perfrom data typing action)
	public static void DTA(WebElement element,String elementname,String testData) {
		try {
			Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
			getExtentTest().log(LogStatus.PASS,elementname+ "element is visble");
			element.clear();
			getExtentTest().log(LogStatus.PASS,  elementname+ "element is clear");
			element.sendKeys(testData);
			getExtentTest().log(LogStatus.PASS, "data typing action is done on: "+elementname+ "with testdata"+testData);
		} catch (Exception exception) {
			getExtentTest().log(LogStatus.FAIL,elementname+ "element is not visble");
			
		}
	}
	
	// data typing action using javascript executor(on which webelement you want to perfrom data typing action)
		public static void DTAJS(WebElement element,String elementname,String testData) {
			try {
				Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
				getExtentTest().log(LogStatus.PASS,elementname+ "element is visble");
				element.clear();
				getExtentTest().log(LogStatus.PASS,  elementname+ "element is clear");
				element.sendKeys(testData);
				getExtentTest().log(LogStatus.PASS, "data typing action is done on: "+elementname+ "with testdata"+testData);
			} catch (Exception exception) {
				getExtentTest().log(LogStatus.FAIL,elementname+ "element is not visble");
				
			}
		}
	
		// data typing action by Actions (on which webelement you want to perfrom data typing action)
		public static void DTAByActions(WebElement element,String elementname,String testData) {
			try {
				Actions actions = new Actions(getDriver());
				Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
				getExtentTest().log(LogStatus.PASS,elementname+ "element is visble");
				actions.sendKeys(element, testData).build().perform();;
				getExtentTest().log(LogStatus.PASS, "data typing action is done on: "+elementname+ "with testdata"+testData);
			} catch (Exception exception) {
				getExtentTest().log(LogStatus.FAIL,elementname+ "element is not visble");
				
			}
		}
		
		// data typing action by Actions[other approach] (on which webelement you want to perfrom data typing action)
				public static void DTAByActions1(WebElement element,String elementname,String testData) {
					try {
						Actions actions = new Actions(getDriver());
						Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
						getExtentTest().log(LogStatus.PASS,elementname+ "element is visble");
						actions.click(element).sendKeys(testData).build().perform();;
						getExtentTest().log(LogStatus.PASS, "data typing action is done on: "+elementname+ "with testdata"+testData);
					} catch (Exception exception) {
						getExtentTest().log(LogStatus.FAIL,elementname+ "element is not visble");
						
					}
				}
		
				
				//clickable operation using soft assertions
				public static void click(WebElement element,String elementName) {
					try {
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertTrue(element.isDisplayed()&&element.isEnabled());
						element.click();
						getExtentTest().log(LogStatus.PASS, "Clicked on: "+elementName);
						
					} catch (Exception exception) {
						getExtentTest().log(LogStatus.FAIL, "NOT Clicked on: "+elementName);
						
					}

				}
				
				//clickable operation using java sctipt executor
				public static void clickByJS1(WebElement element,String elementName) {
					try {
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertTrue(element.isDisplayed()&&element.isEnabled());
						element.click();
						getExtentTest().log(LogStatus.PASS, "Clicked on: "+elementName);
						
					} catch (Exception exception) {
						getExtentTest().log(LogStatus.FAIL, "NOT Clicked on: "+elementName);
						
					}

				}
				
				//clickable operation using Actions along with softassert
				public void clickByActions(WebElement element,String elementName) {
					try {
						SoftAssert softAssert = new SoftAssert();
						softAssert.assertTrue(element.isDisplayed()&&element.isEnabled());
						Actions actions = new Actions(getDriver());
						actions.click(element).build().perform();
						getExtentTest().log(LogStatus.PASS, "Clicked on: "+elementName);
						
					} catch (Exception exception) {
						getExtentTest().log(LogStatus.FAIL, "NOT Clicked on: "+elementName);
						
					}

				}
				
				//handle dropdowns [ how means how u handle thorugh visible select by value or text or index]
				public static void handleDropDowns(String how,String howValue,WebElement element,String dropDownName) {
					try {
						Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
						getExtentTest().log(LogStatus.FAIL, "Dropdown is  not visble ");
						Select select = new Select(element);
						if (how.equalsIgnoreCase("text")) {
							select.selectByVisibleText(howValue);
							getExtentTest().log(LogStatus.PASS, "Dropdown is selcted based on text");
						}
						else if (how.equalsIgnoreCase("Value")) {
							select.selectByValue(howValue);
							getExtentTest().log(LogStatus.PASS, "Dropdown is selcted based on value");
						}
						else if (how.equalsIgnoreCase("index")) {
							int indx = Integer.parseInt(howValue);
							select.selectByIndex(indx);
							getExtentTest().log(LogStatus.PASS, "Dropdown is selcted based on index");
							
						}
					} catch (Exception exception) {
						getExtentTest().log(LogStatus.FAIL, "Dropdown is  not visble ");
						
					}

				}
	//handle multiple windows
				public static void handleMultipleWindows() {
					try {
						String curWindow = getDriver().getWindowHandle();
						Set<String> setwindows =  getDriver().getWindowHandles();
						getExtentTest().log(LogStatus.PASS, "Noof windows are: "+setwindows);
						for(String str : setwindows)
						{
							if (!str.equalsIgnoreCase(str)) {
								//getDriver().switchTo().window(str);
								TargetLocator targetLocator =  getDriver().switchTo();
								targetLocator.window(str);
								getExtentTest().log(LogStatus.PASS, "switch to window: "+str);
								
							}
						}
				
					} catch (Exception exception) {
						
					}

				}
				
				//handle multiple windows assume 200 windows
				public static void handleMultipleWindows(int toWhichWindowyouWantToSwitch) {
					try {
						String curWindow = getDriver().getWindowHandle();
						Set<String> setwindows =  getDriver().getWindowHandles();
						getExtentTest().log(LogStatus.PASS, "Noof windows are: "+setwindows);
						List<String> listWindows = new ArrayList<String>(setwindows);
						String switchWindow =  listWindows.get(toWhichWindowyouWantToSwitch);
						getExtentTest().log(LogStatus.PASS, "switch to window: "+switchWindow);
								
					} 
					catch (Exception exception) {
						
					}

				}
}
