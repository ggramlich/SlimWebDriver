package slimwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverProvider {

	public WebDriver getDriver(String browserType) {
		return new FirefoxDriver();
	}

}
