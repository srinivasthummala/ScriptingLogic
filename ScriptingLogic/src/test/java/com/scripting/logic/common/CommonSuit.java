package com.scripting.logic.common;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.qmetry.qaf.automation.ui.selenium.AssertionService;
import com.scripting.logic.api.page.PageFactory;
import com.scripting.logic.web.page.WebPageFactory;

public class CommonSuit extends WebDriverTestCase {

	private PageFactory pageFactory;

	protected PageFactory getPageFactory() {
		if (null == pageFactory) {
			pageFactory = "web".equalsIgnoreCase(
					ConfigurationManager.getBundle().getString("platform.name", "web")) ? new WebPageFactory() : null;
		}
		if (pageFactory == null) {
			AssertionService.fail("Unable to create page factory instance, please check 'platform.name' parameter");
		}
		return pageFactory;
	}
}
