package slimwebdriver;

import org.openqa.selenium.WebDriver;

public abstract class SlimPageObject {

	protected WebDriver webDriver;
	public SlimPageFactory slimPageFactory;

	public abstract boolean verify();

	public void setSlimWebDriver(SlimWebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void setSlimPageFactory(SlimPageFactory slimPageFactory) {
		this.slimPageFactory = slimPageFactory;
	}

	protected boolean weAreOnPage(String page) {
		return slimPageFactory.weAreOnPage(page);
	}

	protected SlimWebDriver getSlimWebDriver() {
		return slimPageFactory.slimWebDriver;
	}

}
