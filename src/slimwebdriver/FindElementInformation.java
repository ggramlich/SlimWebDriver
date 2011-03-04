package slimwebdriver;

import org.openqa.selenium.By;

public interface FindElementInformation {
	public boolean hasElement(By by);

	public int numberOfElements(By by);
}
