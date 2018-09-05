package com.scripting.logic.base.page;

import java.util.Map;

import org.hamcrest.Matchers;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.data.BaseFormDataBean;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.annotations.UiElement;
import com.qmetry.qaf.automation.ui.annotations.UiElement.Type;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.selenium.AssertionService;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.scripting.logic.api.page.LoginPage;

public class BaseLoginPage extends WebDriverBaseTestPage<WebDriverTestPage> implements LoginPage {

	@FindBy(locator = "login.page.loc")
	private QAFWebElement page;
	@FindBy(locator = "label.login.page.email.loc")
	private QAFWebElement emailLabel;
	@FindBy(locator = "text.box.login.page.email.loc")
	private QAFWebElement emailTextBox;
	@FindBy(locator = "label.login.page.password.loc")
	private QAFWebElement passwordLabel;
	@FindBy(locator = "text.box.login.page.password.loc")
	private QAFWebElement passwordTextBox;
	@FindBy(locator = "button.login.page.login.loc")
	private QAFWebElement loginButton;

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		driver.get("/");

	}

	@Override
	public void waitForPageToLoad() {
		try {
			getPage().waitForPresent();
			getPage().waitForVisible();
		} catch (Exception e) {
			AssertionService.fail("Not able to find 'Login' page");
		}
	}

	public void launchPage() {
		launchPage(null);
	}

	public class LoginFormBean extends BaseFormDataBean {

		@UiElement(fieldLoc = "text.box.login.page.email.loc", fieldType = Type.textbox, order = 1)
		public String userName;
		@UiElement(fieldLoc = "text.box.login.page.password.loc", fieldType = Type.textbox, order = 2)
		public String password;
		
		public String getUserName() {
			return userName;
		}
		public String getPassword() {
			return password;
		}
		
	}

	public QAFWebElement getPage() {
		return page;
	}

	public QAFWebElement getEmailLabel() {
		return emailLabel;
	}

	public QAFWebElement getEmailTextBox() {
		return emailTextBox;
	}

	public QAFWebElement getPasswordLabel() {
		return passwordLabel;
	}

	public QAFWebElement getPasswordTextBox() {
		return passwordTextBox;
	}

	public QAFWebElement getLoginButton() {
		return loginButton;
	}

	// Method to verify page
	public void verifyPage(String pageStatus) {
		if (pageStatus.equalsIgnoreCase("Active")) {
			waitForPageToLoad();
			getPage().assertVisible("'Log In' Page ");
		} else if (pageStatus.equalsIgnoreCase("InActive")) {
			getPage().assertNotVisible("'Log In Page' ");
		} else {
			Reporter.log(pageStatus, MessageTypes.Fail);
		}
	}

	public void verifyLoginObjectsAlignment() {

		int emailLabel = getEmailLabel().getLocation().getY();
		int emailTextBox = getEmailTextBox().getLocation().getY();
		Validator.verifyThat("Email label and Email text box are properly aligned", emailLabel,
				Matchers.equalTo(emailTextBox));

		int passwordLabel = getPasswordLabel().getLocation().getY();
		int passwordTextBox = getPasswordTextBox().getLocation().getY();
		Validator.verifyThat("Password label and Password text box are properly aligned", passwordLabel,
				Matchers.equalTo(passwordTextBox));

		emailLabel = getEmailLabel().getLocation().getX();
		passwordLabel = getPasswordLabel().getLocation().getX();
		Validator.verifyThat("Password label and Email labels are properly aligned", emailLabel,
				Matchers.equalTo(passwordLabel));

		passwordTextBox = getPasswordTextBox().getLocation().getX();
		emailTextBox = getEmailTextBox().getLocation().getX();
		Validator.verifyThat("Email text box and Password text boxes are properly aligned", emailTextBox,
				Matchers.equalTo(passwordTextBox));
	}

	// Method to do login
	@SuppressWarnings("unchecked")
	public void doLogin(Object userDetails) {
		LoginFormBean loginFormBean = new LoginFormBean();
		if (userDetails instanceof String) {
			loginFormBean.fillFromConfig(userDetails.toString());
		} else if (userDetails instanceof Map) {
			Map<String, String> data = (Map<String, String>) userDetails;
			loginFormBean.fillData(data);
		}
		loginFormBean.fillUiElements();
		Reporter.logWithScreenShot(
				"Entered User Name: " + loginFormBean.getUserName() + " and Password: " + loginFormBean.getPassword());
		getLoginButton().click();
	}
}
