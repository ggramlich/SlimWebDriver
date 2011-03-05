package slimwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;

public interface SlimWebElement extends RenderedWebElement, FindElementInformation {
	public SlimWebElement findElement(By by);
}
