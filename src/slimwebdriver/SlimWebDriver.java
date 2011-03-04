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

import fitnesse.slim.Slim;

public class SlimWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot, FindElementInformation {
	private static final ByConverter BY_CONVERTER = new ByConverter();

	private WebDriver webDriver;

	private WebDriverProvider provider;

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
		return webDriver.findElements(by);
	}

	public WebElement findElement(By by) {
		return new SlimWebElement(webDriver.findElement(by));
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

}
