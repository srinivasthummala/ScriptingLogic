package com.scripting.logic.web.page;

import com.scripting.logic.api.page.LoginPage;
import com.scripting.logic.api.page.PageFactory;
import com.scripting.logic.base.page.BaseHomePage;

public class WebPageFactory implements PageFactory {

	@Override
	public LoginPage getLoginPage() {
		return new WebLoginPage();
	}

	@Override
	public BaseHomePage getHomePage() {
		return new WebHomePage();
	}

}
