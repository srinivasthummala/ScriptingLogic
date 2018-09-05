package com.scripting.logic.component;

import java.util.List;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.scripting.logic.component.HeaderContentComponent.HeaderOptionsComponent.HeaderOptionComponent;
import com.scripting.logic.component.HeaderContentComponent.HeaderOptionsComponent.HeaderOptionComponent.OptionsComponent;

public class HeaderContentComponent extends QAFWebComponent {

	@FindBy(locator = "list.header.content.component.navigation.loc")
	private List<HeaderOptionsComponent> navigationList;

	public HeaderContentComponent(String locator) {
		super(locator);
	}

	public class HeaderOptionsComponent extends QAFWebComponent {

		@FindBy(locator = "list.header.options.component.options.loc")
		private List<HeaderOptionComponent> optionsList;

		public HeaderOptionsComponent(String locator) {
			super(locator);
		}

		public class HeaderOptionComponent extends QAFWebComponent {

			@FindBy(locator = "name.header.option.component.loc")
			private QAFWebElement name;
			@FindBy(locator = "component.header.option.component.dropdown.optons.loc")
			private List<OptionsComponent> dropdownOptions;

			public HeaderOptionComponent(String locator) {
				super(locator);
			}

			public class OptionsComponent extends QAFWebComponent {

				@FindBy(locator = "name.options.component.loc")
				private QAFWebElement name;

				public OptionsComponent(String locator) {
					super(locator);
				}

				public QAFWebElement getName() {
					return name;
				}

			}

			public QAFWebElement getName() {
				return name;
			}

			public List<OptionsComponent> getDropdownOptions() {
				return dropdownOptions;
			}

		}

		public List<HeaderOptionComponent> getOptionsList() {
			return optionsList;
		}

	}

	public List<HeaderOptionsComponent> getNavigationList() {
		return navigationList;
	}

	public void selectDropdownValue(String parent, String child) {
		List<HeaderOptionsComponent> navigationList = getNavigationList();
		if (navigationList.size() > 1) {
			for (HeaderOptionsComponent headerOptionsComponent : navigationList) {
				List<HeaderOptionsComponent.HeaderOptionComponent> optionsList = headerOptionsComponent
						.getOptionsList();
				for (HeaderOptionComponent headerOptionComponent : optionsList) {
					if (headerOptionComponent.getName().getText().contains(parent)) {
						headerOptionComponent.getName().click();
						List<HeaderOptionsComponent.HeaderOptionComponent.OptionsComponent> dropdownOptions = headerOptionComponent
								.getDropdownOptions();
						for (OptionsComponent optionsComponent : dropdownOptions) {
							if (optionsComponent.getName().getText().contains(child)) {
								optionsComponent.getName().click();
							}
						}
					}
				}
			}
		}
	}

}
