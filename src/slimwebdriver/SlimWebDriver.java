package slimwebdriver;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;

public class SlimWebDriver {
	private WebDriver webDriver;

	private WebDriverProvider provider;

	public SlimWebDriver() {
		this(null);
	}

	public SlimWebDriver(String browserType) {
		provider = WebDriverProvider.INSTANCE;
		this.setBrowserType(browserType);
	}

	private void setBrowserType(String browserType) {
		if (StringUtils.isEmpty(browserType)) {
			webDriver = provider.getOnlyDriver();
		} else {
			webDriver = provider.getDriver(browserType);
		}
	}

	public void openUrl(String url) {
		webDriver.get(url);
	}

	public String title() {
		return webDriver.getTitle();
	}

	public void closeWindow() {
		provider.closeWindow(webDriver);
	}

	public void stopWebDriver() {
		provider.quit(webDriver);
	}

}
