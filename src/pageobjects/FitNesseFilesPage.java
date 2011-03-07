package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import slimwebdriver.SlimPageObject;

public class FitNesseFilesPage extends SlimPageObject {

	@FindBy(partialLinkText = "delaysWithAjax")
	WebElement delaysWithAjax;

	public boolean verify() {
		return "Files: files/".equals(webDriver.getTitle());
	}

	public boolean goToDelaysWithAjax() {
		delaysWithAjax.click();
		return weAreOnPage(DemoFitnessePageFactory.DELAYS_WITH_AJAX);
	}

}
