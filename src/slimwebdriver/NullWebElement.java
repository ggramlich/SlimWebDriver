package slimwebdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import slimwebdriver.exceptions.NoWebElementChosenException;

public class NullWebElement implements SlimWebElement {

	public void click() {
		throw new NoWebElementChosenException();
	}

	public void submit() {
		throw new NoWebElementChosenException();
	}

	public String getValue() {
		throw new NoWebElementChosenException();
	}

	public void sendKeys(CharSequence... keysToSend) {
		throw new NoWebElementChosenException();
	}

	public void sendKey(Keys key) {
		throw new NoWebElementChosenException();
	}

	public void clear() {
		throw new NoWebElementChosenException();
	}

	public String getTagName() {
		throw new NoWebElementChosenException();
	}

	public String getAttribute(String name) {
		throw new NoWebElementChosenException();
	}

	public boolean toggle() {
		throw new NoWebElementChosenException();
	}

	public boolean isSelected() {
		throw new NoWebElementChosenException();
	}

	public void setSelected() {
		throw new NoWebElementChosenException();
	}

	public boolean isEnabled() {
		throw new NoWebElementChosenException();
	}

	public String getText() {
		throw new NoWebElementChosenException();
	}

	public List<WebElement> findElements(By by) {
		throw new NoWebElementChosenException();
	}

	public SlimWebElement findElement(By by) {
		throw new NoWebElementChosenException();
	}

	public boolean isDisplayed() {
		throw new NoWebElementChosenException();
	}

	public Point getLocation() {
		throw new NoWebElementChosenException();
	}

	public Dimension getSize() {
		throw new NoWebElementChosenException();
	}

	public boolean hasElement(By by) {
		throw new NoWebElementChosenException();
	}

	public int numberOfElements(By by) {
		throw new NoWebElementChosenException();
	}

	public boolean containsText(String text) {
		throw new NoWebElementChosenException();
	}

	public void selectByValue(String value) {
		throw new NoWebElementChosenException();
	}

	public void selectByText(String text) {
		throw new NoWebElementChosenException();
	}

	public String getCssValue(String propertyName) {
		throw new NoWebElementChosenException();
	}

}
