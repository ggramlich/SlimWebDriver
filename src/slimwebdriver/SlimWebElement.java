package slimwebdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SlimWebElement implements WebElement, FindElementInformation {

	private final WebElement webElement;

	public SlimWebElement(WebElement webElement) {
		this.webElement = webElement;
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

	public WebElement findElement(By by) {
		return new SlimWebElement(webElement.findElement(by));
	}

	public boolean hasElement(By by) {
		return numberOfElements(by) > 0;
	}

	public int numberOfElements(By by) {
		return findElements(by).size();
	}
}
