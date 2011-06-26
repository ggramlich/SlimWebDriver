package slimwebdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SlimWebElementWrapper implements SlimWebElement {

	private final WebElement webElement;
	private final WebDriver webDriver;

	public SlimWebElementWrapper(WebElement webElement, WebDriver webDriver) {
		this.webElement = webElement;
		this.webDriver = webDriver;
	}

	public String toString() {
		return String.format("WebElement: &lt;%1$s&gt;%2$s&lt;/%1$s&gt;", getTagName(), getText());
	}

	public void click() {
		webElement.click();
	}

	public void submit() {
		webElement.submit();
	}

	public void sendKeys(CharSequence... keysToSend) {
		webElement.sendKeys(keysToSend);
	}

	public void sendKey(Keys key) {
		webElement.sendKeys(key);
	}

	public void clear() {
		webElement.clear();
	}

	public String getTagName() {
		return webElement.getTagName();
	}

	public String getAttribute(String name) {
		return webElement.getAttribute(name);
	}

	public boolean toggle() {
		webElement.click();
		return webElement.isSelected();
	}

	public boolean isSelected() {
		return webElement.isSelected();
	}

	public void setSelected() {
		webElement.click();
	}

	public boolean isEnabled() {
		return webElement.isEnabled();
	}

	public String getText() {
		return webElement.getText();
	}

	public List<WebElement> findElements(By by) {
		return webElement.findElements(by);
	}

	public SlimWebElement findElement(By by) {
		return new SlimWebElementWrapper(webElement.findElement(by), webDriver);
	}

	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	public Point getLocation() {
		return webElement.getLocation();
	}

	public Dimension getSize() {
		return webElement.getSize();
	}

	public String getCssValue(String propertyName) {
		return webElement.getCssValue(propertyName);
	}

	public boolean hasElement(By by) {
		return numberOfElements(by) > 0;
	}

	public int numberOfElements(By by) {
		return findElements(by).size();
	}

	public boolean containsText(String text) {
		return webElement.getText().contains(text);
	}

	public void selectByValue(String value) {
		createSelect().selectByValue(value);
	}

	public void selectByText(String text) {
		createSelect().selectByVisibleText(text);
	}

	private Select createSelect() {
		return new Select(this);
	}

}
