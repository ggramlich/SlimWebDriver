package slimwebdriver;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import fitnesse.slim.converters.ConverterRegistry;

public class SlimWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot, SlimWebElement {
	private static final ByConverter BY_CONVERTER = new ByConverter();

	private static final KeysConverter KEYS_CONVERTER = new KeysConverter();

	WebDriver webDriver;

	private WebDriverProvider provider;

	private SlimWebElement slimElement = new NullWebElement();

	private int timeout = 5;

	public SlimWebDriver() {
		this(null);
	}

	public SlimWebDriver(String browserType) {
		provider = WebDriverProvider.INSTANCE;
		setBrowserType(browserType);
		registerConverters();
	}

	private void registerConverters() {
		ConverterRegistry.addConverter(By.class, BY_CONVERTER);
		ConverterRegistry.addConverter(Keys.class, KEYS_CONVERTER);
	}

	private void setBrowserType(String browserType) {
		if (StringUtils.isEmpty(browserType)) {
			webDriver = provider.getOnlyDriver();
		} else {
			webDriver = provider.getDriver(browserType);
		}
	}

	public boolean useElement(By by) {
		if (hasElement(by)) {
			slimElement = findElement(by);
			return true;
		}
		return false;
	}

	public boolean useInnerElement(By by) {
		if (hasInnerElement(by)) {
			slimElement = findInnerElement(by);
			return true;
		}
		return false;
	}

	public void useWebDriver() {
		slimElement = new NullWebElement();
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

	public SlimWebElement findElement(By by) {
		return new SlimWebElementWrapper(webDriver.findElement(by), webDriver);
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

	public Object executeScript(String script) {
		return ((JavascriptExecutor) webDriver).executeScript(script);
	}

	public Object executeScript(String script, Object args) {
		return ((JavascriptExecutor) webDriver).executeScript(script, args);
	}

	public Object executeScript(String script, Object... args) {
		return ((JavascriptExecutor) webDriver).executeScript(script, args);
	}

	public Object executeAsyncScript(String script) {
		return ((JavascriptExecutor) webDriver).executeAsyncScript(script);
	}

	public Object executeAsyncScript(String script, Object args) {
		return ((JavascriptExecutor) webDriver).executeAsyncScript(script, args);
	}

	public Object executeAsyncScript(String script, Object... args) {
		return ((JavascriptExecutor) webDriver).executeAsyncScript(script, args);
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

	public void deleteAllCookies() {
		manage().deleteAllCookies();
	}

	public void deleteCookieNamed(String cookieName) {
		manage().deleteCookieNamed(cookieName);
	}

	public SlimWebElement findInnerElement(By by) {
		return slimElement.findElement(by);
	}

	public boolean hasInnerElement(By by) {
		return slimElement.hasElement(by);
	}

	public int numberOfInnerElements(By by) {
		return slimElement.numberOfElements(by);
	}

	public void click() {
		slimElement.click();
	}

	public void submit() {
		slimElement.submit();
	}

	@Deprecated
	public String getValue() {
		return slimElement.getAttribute("value");
	}

	public void enterText(String keysToSend) {
		slimElement.sendKeys(keysToSend);
	}

	public void sendKeys(CharSequence... keysToSend) {
		slimElement.sendKeys(keysToSend);
	}

	public void sendKey(Keys key) {
		slimElement.sendKeys(key);
	}

	public void clear() {
		slimElement.clear();
	}

	public String getTagName() {
		return slimElement.getTagName();
	}

	public String getAttribute(String name) {
		return slimElement.getAttribute(name);
	}

	@Deprecated
	public boolean toggle() {
		slimElement.click();
		return slimElement.isSelected();
	}

	public boolean isSelected() {
		return slimElement.isSelected();
	}

	public boolean isEnabled() {
		return slimElement.isEnabled();
	}

	public String getText() {
		return slimElement.getText();
	}

	public boolean isDisplayed() {
		return slimElement.isDisplayed();
	}

	public Point getLocation() {
		return slimElement.getLocation();
	}

	public Dimension getSize() {
		return slimElement.getSize();
	}

	public void hover() {
		actionsBuilder().moveToElement(this).build().perform();
	}

	public void dragAndDropBy(int moveRightBy, int moveDownBy) {
		actionsBuilder().dragAndDropBy(this, moveRightBy, moveDownBy).build().perform();
	}

	public void dragAndDropOn(WebElement element) {
		actionsBuilder().dragAndDrop(this, element).build().perform();
	}

	public String getCssValue(String propertyName) {
		return slimElement.getCssValue(propertyName);
	}

	@Deprecated
	public String getValueOfCssProperty(String propertyName) {
		return getCssValue(propertyName);
	}

	// Special methods in the SlimWebElement interface

	public boolean containsText(String text) {
		return slimElement.containsText(text);
	}

	public void selectByValue(String value) {
		slimElement.selectByValue(value);
	}

	public void selectByText(String text) {
		slimElement.selectByText(text);
	}

	// Methods related to waiting

	public void setTimeOut(int seconds) {
		timeout = seconds;
	}

	public void waitTime(int milliseconds) throws Exception {
		Thread.sleep(milliseconds);
	}

	public void waitForElementToAppear(By by) {
		iWait().until(elementVisible(by));
	}

	public void waitForElementToDisappear(By by) {
		iWait().until(elementInvisible(by));
	}

	public void waitForElementPresent(By by) {
		iWait().until(presenceOfElementLocated(by));
	}

	public void waitForElementAbsent(By by) {
		iWait().until(absenceOfElementLocated(by));
	}

	public void waitForCondition(String javascript) {
		iWait().until(javascriptEvaluatesToTrue("return (" + javascript + ");"));
	}

	private WebDriverWait iWait() {
		return new WebDriverWait(webDriver, timeout);
	}

	private Function<WebDriver, Object> javascriptEvaluatesToTrue(final String javascript) {
		return new Function<WebDriver, Object>() {
			public Object apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(javascript);
			}
		};
	}

	private Function<WebDriver, Boolean> presenceOfElementLocated(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElements(by).size() > 0;
			}
		};
	}

	private Function<WebDriver, Boolean> absenceOfElementLocated(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElements(by).size() == 0;
			}
		};
	}

	private Function<WebDriver, Boolean> elementVisible(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (driver.findElements(by).size() == 0) {
					return false;
				}
				WebElement element = driver.findElement(by);
				return element.isDisplayed();
			}
		};
	}

	private Function<WebDriver, Boolean> elementInvisible(final By by) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (driver.findElements(by).size() == 0) {
					return true;
				}
				WebElement element = driver.findElement(by);
				return !element.isDisplayed();
			}
		};
	}

	private Actions actionsBuilder() {
		return new Actions(webDriver);
	}

}
