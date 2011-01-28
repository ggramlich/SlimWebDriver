package slimwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import slimwebdriver.exceptions.UsupportedWebDriverException;

public class SlimWebDriver {
	private final String browserType;
	private WebDriver webDriver;

	public SlimWebDriver(String browserTyp) {
		this.browserType = browserTyp;
	}

	public void getUrl(String url) {
		webDriver().get(url);
	}

	public String title() {
		return webDriver().getTitle();
	}

	public void closeWebDriver() {
		if (webDriver == null) {
			return;
		}
		webDriver.close();
		webDriver = null;
	}

	private WebDriver webDriver() {
		if (webDriver == null) {
			webDriver = createWebDriver();
		}
		return webDriver;
	}

	private WebDriver createWebDriver() {
		if ("firefox".equalsIgnoreCase(browserType)) {
			return new FirefoxDriver();
		}
		throw new UsupportedWebDriverException(browserType);
	}
}
