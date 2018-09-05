package com.scripting.logic.test.suit;

import java.util.Map;

import org.testng.annotations.Test;

import com.qmetry.qaf.automation.testng.dataprovider.QAFDataProvider;
import com.scripting.logic.api.page.HomePage;
import com.scripting.logic.api.page.LoginPage;
import com.scripting.logic.base.page.BaseHomePage;
import com.scripting.logic.common.CommonSuit;

public class LoginPageTestSuit extends CommonSuit {

	@QAFDataProvider(dataFile = "resources/testdata/testdata.xls", sheetName = "Sheet1", key = "user1")
	@Test
	public void loginVerifyTest(Map<String, String> data) {
		LoginPage loginPage = getPageFactory().getLoginPage();
		loginPage.launchPage();
		loginPage.verifyPage("Active");
		loginPage.verifyLoginObjectsAlignment();
		loginPage.doLogin(data);
//		HomePage homePage = getPageFactory().getHomePage();
//		homePage.verifyPage("Active");
		BaseHomePage baseHomePage = new BaseHomePage();
		baseHomePage.getHeaderDropdownComponent().selectDropdownValue("Clients", "Add Client");
	}

}
