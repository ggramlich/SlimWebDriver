package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import slimwebdriver.SlimPageObject;

public class FitNesseStartPage extends SlimPageObject {

	@FindBy(linkText = "Files")
	private WebElement filesLink;

	public boolean verify() {
		return "FrontPage".equals(webDriver.getTitle());
	}

	public boolean goToFilesPage() {
		filesLink.click();
		return transitionToPage(new FitNesseFilesPage());
	}

}
