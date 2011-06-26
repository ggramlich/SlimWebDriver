package slimwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface SlimWebElement extends WebElement, FindElementInformation {
	public SlimWebElement findElement(By by);

	public void sendKey(Keys key);

	public boolean containsText(String text);

	public void selectByValue(String value);

	public void selectByText(String text);

}
