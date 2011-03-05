package slimwebdriver;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import slimwebdriver.exceptions.NoWebElementChosenException;
import fitnesse.slim.Slim;

public class SlimWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot, FindElementInformation,
	WebElement {
	private static final ByConverter BY_CONVERTER = new ByConverter();

	private WebDriver webDriver;

	private WebDriverProvider provider;

	private WebElement element = new NullWebElement();

	public SlimWebDriver() {
		this(null);
	}

	public SlimWebDriver(String browserType) {
		provider = WebDriverProvider.INSTANCE;
		setBrowserType(browserType);
		registerByConverter();
	}

	private void registerByConverter() {
		Slim.addConverter(By.class, BY_CONVERTER);
	}

	private void setBrowserType(String browserType) {
		if (StringUtils.isEmpty(browserType)) {
			webDriver = provider.getOnlyDriver();
		} else {
			webDriver = provider.getDriver(browserType);
		}
	}

	public void useElement(By by) {
		element = findElement(by);
	}

	public void useWebDriver() {
		element = new NullWebElement();
	}

	public void openUrl(String url) {
		webDriver.get(url);
	}

	public String title() {
		return getTitle();
	}

	public void closeWindow() {
		provider.closeWindow(webDriver);
	}

	public void stopWebDriver() {
		provider.quit(webDriver);
	}

	public void stopAllWebDrivers() {
		provider.quit();
	}

	public void get(String url) {
		webDriver.get(url);
	}

	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	public WebElement findElement(By by) {
		return element.findElement(by);
	}

	public String getPageSource() {
		return webDriver.getPageSource();
	}

	public Set<String> getWindowHandles() {
		return webDriver.getWindowHandles();
	}

	public String getWindowHandle() {
		return webDriver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return webDriver.switchTo();
	}

	public Navigation navigate() {
		return webDriver.navigate();
	}

	public Options manage() {
		return webDriver.manage();
	}

	public void close() {
		closeWindow();
	}

	public void quit() {
		stopWebDriver();
	}

	public Object executeScript(String script, Object... args) {
		return ((JavascriptExecutor) webDriver).executeScript(script, args);
	}

	public Object executeAsyncScript(String script, Object... args) {
		return ((JavascriptExecutor) webDriver).executeAsyncScript(script, args);
	}

	public boolean isJavascriptEnabled() {
		return ((JavascriptExecutor) webDriver).isJavascriptEnabled();
	}

	// TODO - provide method that saves a file
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return ((TakesScreenshot) webDriver).getScreenshotAs(target);
	}

	public boolean hasElement(By by) {
		return numberOfElements(by) > 0;
	}

	public int numberOfElements(By by) {
		return findElements(by).size();
	}

	public void click() {
		element.click();
	}

	public void submit() {
		element.submit();
	}

	public String getValue() {
		return element.getValue();
	}

	public void sendKeys(CharSequence... keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void clear() {
		element.clear();
	}

	public String getTagName() {
		return element.getTagName();
	}

	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	public boolean toggle() {
		return element.toggle();
	}

	public boolean isSelected() {
		return element.isSelected();
	}

	public void setSelected() {
		element.setSelected();
	}

	public boolean isEnabled() {
		return element.isEnabled();
	}

	public String getText() {
		return element.getText();
	}

	public class NullWebElement implements WebElement {

		@Override
		public void click() {
			throw new NoWebElementChosenException();
		}

		@Override
		public void submit() {
			throw new NoWebElementChosenException();
		}

		@Override
		public String getValue() {
			throw new NoWebElementChosenException();
		}

		@Override
		public void sendKeys(CharSequence... keysToSend) {
			throw new NoWebElementChosenException();
		}

		@Override
		public void clear() {
			throw new NoWebElementChosenException();
		}

		@Override
		public String getTagName() {
			throw new NoWebElementChosenException();
		}

		@Override
		public String getAttribute(String name) {
			throw new NoWebElementChosenException();
		}

		@Override
		public boolean toggle() {
			throw new NoWebElementChosenException();
		}

		@Override
		public boolean isSelected() {
			throw new NoWebElementChosenException();
		}

		@Override
		public void setSelected() {
			throw new NoWebElementChosenException();
		}

		@Override
		public boolean isEnabled() {
			throw new NoWebElementChosenException();
		}

		@Override
		public String getText() {
			throw new NoWebElementChosenException();
		}

		@Override
		public List<WebElement> findElements(By by) {
			return webDriver.findElements(by);
		}

		@Override
		public WebElement findElement(By by) {
			return new SlimWebElement(webDriver.findElement(by));
		}
	}

}
