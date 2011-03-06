package slimwebdriver;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;

public class SlimWebElementWrapper implements SlimWebElement {

	private final WebElement webElement;

	public SlimWebElementWrapper(WebElement webElement) {
		this.webElement = webElement;
	}

	private RenderedWebElement renderedWebElement() {
		return ((RenderedWebElement) webElement);
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

	public String getValue() {
		return webElement.getValue();
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
		return webElement.toggle();
	}

	public boolean isSelected() {
		return webElement.isSelected();
	}

	public void setSelected() {
		webElement.setSelected();
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
		return new SlimWebElementWrapper(webElement.findElement(by));
	}

	public boolean isDisplayed() {
		return renderedWebElement().isDisplayed();
	}

	public Point getLocation() {
		return renderedWebElement().getLocation();
	}

	public Dimension getSize() {
		return renderedWebElement().getSize();
	}

	public void hover() {
		renderedWebElement().hover();
	}

	public void dragAndDropBy(int moveRightBy, int moveDownBy) {
		renderedWebElement().dragAndDropBy(moveRightBy, moveDownBy);
	}

	public void dragAndDropOn(RenderedWebElement element) {
		renderedWebElement().dragAndDropOn(element);
	}

	public String getValueOfCssProperty(String propertyName) {
		return renderedWebElement().getValueOfCssProperty(propertyName);
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

}
