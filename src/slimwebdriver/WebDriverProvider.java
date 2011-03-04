package slimwebdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;

import slimwebdriver.exceptions.MultipleDriversRegisteredException;
import slimwebdriver.exceptions.NoDriverRegisteredException;
import slimwebdriver.exceptions.UnsupportedWebDriverException;
import slimwebdriver.exceptions.WebDriverInstantiationException;

public class WebDriverProvider {

	public static final WebDriverProvider INSTANCE = new WebDriverProvider();

	enum DRIVER {
		firefox {
			WebDriver create() {
				return new FirefoxDriver();
			}
		},

		htmlunit {
			WebDriver create() {
				return new HtmlUnitDriver(true);
			}
		},

		ie {
			WebDriver create() {
				return new InternetExplorerDriver();
			}
		},

		internetexplorer {
			WebDriver create() {
				return new InternetExplorerDriver();
			}
		},

		chrome {
			WebDriver create() {
				return new ChromeDriver();
			}
		},

		android {
			WebDriver create() {
				return new AndroidDriver();
			}
		},

		iphone {
			WebDriver create() throws Exception {
				return new IPhoneDriver();
			}
		};

		abstract WebDriver create() throws Exception;
	}

	Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	public WebDriver getDriver(String browserType) {
		for (DRIVER driver : DRIVER.values()) {
			String drivername = driver.name();
			if (drivername.equalsIgnoreCase(browserType)) {
				if (!drivers.containsKey(drivername)) {
					try {
						drivers.put(drivername, driver.create());
					} catch (Exception exception) {
						throw new WebDriverInstantiationException(exception);
					}
				}
				return drivers.get(drivername);
			}
		}
		throw new UnsupportedWebDriverException(browserType);
	}

	public void quit() {
		Set<String> browserTypes = drivers.keySet();
		for (String browserType : browserTypes) {
			quitDriver(browserType);
		}
		drivers.clear();
	}

	private void quitDriver(String browserType) {
		WebDriver driver = drivers.get(browserType);
		driver.quit();
	}

	public WebDriver getOnlyDriver() {
		if (drivers.isEmpty()) {
			throw new NoDriverRegisteredException();
		}
		if (drivers.size() > 1) {
			throw new MultipleDriversRegisteredException();
		}
		return drivers.values().iterator().next();
	}

	public void closeWindow(WebDriver driver) {
		if (driver.getWindowHandles().size() > 1) {
			driver.close();
		} else {
			quit(driver);
		}
	}

	public void quit(WebDriver driver) {
		driver.quit();
		removeDriver(driver);
	}

	private boolean removeDriver(WebDriver driver) {
		return drivers.values().remove(driver);
	}

}
