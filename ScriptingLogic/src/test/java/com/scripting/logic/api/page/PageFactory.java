package com.scripting.logic.api.page;

import com.scripting.logic.base.page.BaseHomePage;

public interface PageFactory {
 
	public LoginPage getLoginPage();
	public BaseHomePage getHomePage();
}
