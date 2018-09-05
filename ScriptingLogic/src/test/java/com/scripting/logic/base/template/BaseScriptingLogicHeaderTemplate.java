package com.scripting.logic.base.template;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.scripting.logic.component.HeaderContentComponent;
import com.scripting.logic.template.ScripingLogicHeaderTemplate;

/**
 * @author Srinivas Thummala
 */
public abstract class BaseScriptingLogicHeaderTemplate extends WebDriverBaseTestPage<WebDriverTestPage>
		implements ScripingLogicHeaderTemplate {

	@FindBy(locator = "section.scripting.logic.header.template.loc")
	private QAFWebElement headerSection;
	@FindBy(locator = "component.scripting.logic.header.content.loc")
	private HeaderContentComponent headerContentComponent;	

	public QAFWebElement getHeaderSection() {
		return headerSection;
	}
	public HeaderContentComponent getHeaderDropdownComponent() {
		return headerContentComponent;
	}



	// Method to verify header content
	public void verifyHeaderContent() {
		if (getHeaderSection().verifyVisible()) {
			Reporter.logWithScreenShot("Header section successfully verified");
		}
	}

//	// Method to do logout
//	public void doLogout() {
//		if (getAccountIcon().isPresent() && getAccountIcon().isDisplayed()) {
//			getAccountIcon().click();
//			getAccountPopUp().waitForVisible();
//			getAccountPopUp().getLogoutButton().click();
//		} else {
//			Reporter.logWithScreenShot("User is already logged out");
//		}
//	}
}
