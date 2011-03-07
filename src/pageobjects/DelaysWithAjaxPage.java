package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import slimwebdriver.SlimPageObject;

public class DelaysWithAjaxPage extends SlimPageObject {

	@FindBy(id = "changeTitle")
	private WebElement changeTitleButton;

	@FindBy(id = "newTitle")
	private WebElement newTitleInput;

	public boolean verify() {
		return "Delays With Ajax".equals(webDriver.getTitle());
	}

	public void changeTitleTo(String title) {
		newTitleInput.clear();
		newTitleInput.sendKeys(title);
		changeTitleButton.click();
		getSlimWebDriver().waitForCondition("document.title == '" + title + "'");
	}
}
