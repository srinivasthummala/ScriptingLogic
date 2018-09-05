package com.scripting.logic.base.page;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.selenium.AssertionService;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.scripting.logic.api.page.HomePage;
import com.scripting.logic.base.template.BaseScriptingLogicHeaderTemplate;

public class BaseHomePage extends BaseScriptingLogicHeaderTemplate implements HomePage {

	@FindBy(locator = "login.home.loc")
	private QAFWebElement page;

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void waitForPageToLoad() {
		try {
			getPage().waitForPresent();
			getPage().waitForVisible();
		} catch (Exception e) {
			AssertionService.fail("Not able to find 'Home' page");
		}
	}

	public QAFWebElement getPage() {
		return page;
	}

	// Method to verify page
	public void verifyPage(String pageStatus) {
		if (pageStatus.equalsIgnoreCase("Active")) {
			waitForPageToLoad();
			getPage().assertVisible("'Home' Page ");
		} else if (pageStatus.equalsIgnoreCase("InActive")) {
			getPage().assertNotVisible("'Home Page' ");
		} else {
			Reporter.log(pageStatus, MessageTypes.Fail);
		}
	}

}
