package com.scripting.logic.api.page;

public interface LoginPage {

	public void verifyPage(String pageStatus);
	public void launchPage();
	public void verifyLoginObjectsAlignment();
	public void doLogin(Object userDetails);
}
