package slimwebdriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverProviderTest {
	@Test
	public void testCreateFirefox() throws Exception {
		WebDriverProvider provider = new WebDriverProvider();
		WebDriver firefoxDriver = provider.getDriver("firefox");
		assertTrue(firefoxDriver instanceof FirefoxDriver);
		assertEquals(firefoxDriver, provider.getDriver("firefox"));
	}
}
