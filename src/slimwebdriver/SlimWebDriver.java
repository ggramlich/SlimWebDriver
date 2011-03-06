package slimwebdriver;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import fitnesse.slim.Slim;

public class SlimWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot, SlimWebElement {
	private static final ByConverter BY_CONVERTER = new ByConverter();

	WebDriver webDriver;

	private WebDriverProvider provider;

	private SlimWebElement slimElement = new NullWebElement();

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

	public SlimWebElement findInnerElement(By by) {
		return slimElement.findElement(by);
	}

	public boolean hasInnerElement(By by) {
		return slimElement.hasElement(by);
	}

	public int numberOfInnerElements(By by) {
		return slimElement.numberOfElements(by);
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
		return new SlimWebElementWrapper(webDriver.findElement(by));
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

	public Object executeScript(String script, Object args) {
		return ((JavascriptExecutor) webDriver).executeScript(script, args);
	}

	public Object executeScript(String script, Object... args) {
		return ((JavascriptExecutor) webDriver).executeScript(script, args);
	}

	public Object executeAsyncScript(String script, Object args) {
		return ((JavascriptExecutor) webDriver).executeAsyncScript(script, args);
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
		slimElement.click();
	}

	public void submit() {
		slimElement.submit();
	}

	public String getValue() {
		return slimElement.getValue();
	}

	public void sendKeys(CharSequence keysToSend) {
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

	public boolean toggle() {
		return slimElement.toggle();
	}

	public boolean isSelected() {
		return slimElement.isSelected();
	}

	public void setSelected() {
		slimElement.setSelected();
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
		slimElement.hover();
	}

	public void dragAndDropBy(int moveRightBy, int moveDownBy) {
		slimElement.dragAndDropBy(moveRightBy, moveDownBy);
	}

	public void dragAndDropOn(RenderedWebElement element) {
		element.dragAndDropOn(element);
	}

	public String getValueOfCssProperty(String propertyName) {
		return slimElement.getValueOfCssProperty(propertyName);
	}

	public boolean containsText(String text) {
		return slimElement.containsText(text);
	}

}
