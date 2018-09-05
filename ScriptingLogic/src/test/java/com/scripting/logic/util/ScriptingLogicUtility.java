package com.scripting.logic.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.core.QAFTestBase;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;

/**
 * @author yogesh.pathrabe
 */
public class ScriptingLogicUtility {

	// Method to move mouse on element
	public static void moveMouseOnElement(QAFWebElement element) {
		try {
			Actions actions = new Actions(new WebDriverTestBase().getDriver());
			actions.moveToElement(element).perform();
			String mouseOverScript =
					"if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) new WebDriverTestBase().getDriver())
					.executeScript(mouseOverScript, element);
		} catch (Exception e) {
		}
	}

	// Method to to scroll element into center
	public static void scrollToCenter(QAFWebElement element) {
		QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
		try {
			Point point = element.getLocation();
			long height = (long) ((JavascriptExecutor) driver)
					.executeScript("return document.documentElement.clientHeight");
			((JavascriptExecutor) driver).executeScript("window.scroll(" + point.getX()
					+ "," + (point.getY() - (height / 2)) + ");");
		} catch (Exception e) {
		}
	}

	// Method to to scroll element into view
	public static void scrollToView(Object data) {
		try {
			QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			if (data instanceof String) {
				WebElement element = driver.findElement(data.toString());
				javascriptExecutor.executeScript("arguments[0].scrollIntoView();",
						element);
			} else if (data instanceof WebElement) {
				javascriptExecutor.executeScript("arguments[0].scrollIntoView();",
						(WebElement) data);
			}
		} catch (Exception e) {
		}
	}

	// Method to to scroll element into view
	public static void scrollToBottom() {
		try {
			QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			javascriptExecutor
					.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
		}
	}

	// Method to switch to new window
	public static void switchToNewWindow() {
		QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
		QAFTestBase.pause(2500);
		ConfigurationManager.getBundle().setProperty(
				"current.window", driver.getWindowHandle());
		if (driver.getWindowHandles().size() > 1) {
			for (String window : driver.getWindowHandles()) {
				driver.switchTo().window(window);
			}
		}
	}

	// Method to switch back to old window
	public static void switchToOldWindow() {
		QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
		String oldWindow = ConfigurationManager.getBundle()
				.getString("current.window");
		if (driver.getWindowHandles().size() > 1) {
			for (String window : driver.getWindowHandles()) {
				if (!window.equals(oldWindow)) {
					driver.switchTo().window(window);
					driver.close();
				}
			}
			driver.switchTo().window(oldWindow);
			QAFTestBase.pause(1000);
		}
	}

	// Method to switch on frame
	public static void switchOnFrame(String frameName) {
		QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
		driver.switchTo().frame(frameName);
	}

	// Method to switch back from frame
	public static void switchBackFromFrame() {
		QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
		driver.switchTo().defaultContent();
	}

	// Method to navigate back
	public static void navigateBack() {
		new WebDriverTestBase().getDriver().navigate().back();
	}

	/**
	 * @param element
	 *            = ["Element to enter value"]
	 * @param value
	 *            = ["Value to enter"]
	 */
	public static void enterValueUsingActions(QAFWebElement element, String value) {
		ScriptingLogicUtility.scrollToView(element);
		element.executeScript("focus();");
		Actions actions = new Actions(new WebDriverTestBase().getDriver());
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(value);
		actions.build().perform();
	}

	/**
	 * @param element
	 *            = ["Element to click"]
	 */
	public static void clickUsingJavaScript(QAFWebElement element) {
		ScriptingLogicUtility.scrollToCenter(element);
		element.executeScript("focus();");
		if (new WebDriverTestBase().getBrowser().contains("firefox")) {
			element.click();
		} else {
			JavascriptExecutor javascriptExecutor =
					(JavascriptExecutor) new WebDriverTestBase().getDriver();
			javascriptExecutor.executeScript("arguments[0].click()", element);
		}
	}

	/**
	 * @param element
	 *            = ["Element to click"]
	 */
	public static void clickUsingActions(QAFWebElement element) {
		if (new WebDriverTestBase().getBrowser().contains("firefox")) {
			element.click();
		} else {
			Actions actions = new Actions(new WebDriverTestBase().getDriver());
			actions.click(element).perform();
		}
	}

	/**
	 * @param propertyName
	 *            = ["Display", "More", "etc."]
	 * @param messageType
	 *            = ["Pass", "Fail", "etc."]
	 */
	// Method to log properties specific message
	public static void logPropertiesMessage(String propertyName, String messageType) {
		if (messageType.equals("fail")) {
			Reporter.log("Not able to find '" + propertyName
					+ "' keyword in properties file", MessageTypes.Fail);
		} else {
			Reporter.log("Not able to find '" + propertyName
					+ "' keyword in properties file");
		}
	}

	/**
	 * @param element
	 *            = ["Element name", "etc."]
	 * @param value
	 *            = ["Value to enter", "etc."]
	 */
	// Method to enter value
	public static void enterValue(QAFWebElement element, String value) {

		element.clear();
		element.sendKeys(value + Keys.TAB);
		if (element.getAttribute("value").equalsIgnoreCase(value)) {
			Reporter.logWithScreenShot(value + " value successfully entered",
					MessageTypes.Pass);
		} else {
			Reporter.logWithScreenShot("Failed to enter " + value + " value",
					MessageTypes.Fail);
		}
	}
}
