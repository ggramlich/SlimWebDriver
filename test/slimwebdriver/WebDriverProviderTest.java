package slimwebdriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import slimwebdriver.exceptions.MultipleDriversRegisteredException;
import slimwebdriver.exceptions.NoDriverRegisteredException;
import slimwebdriver.exceptions.UnsupportedWebDriverException;

public class WebDriverProviderTest {

	private static final String FIREFOX = "firefox";
	private static final String HTMLUNIT = "htmlunit";
	private WebDriverProvider provider;

	@Before
	public void startProvider() {
		provider = new WebDriverProvider();
	}

	@After
	public void closeBrowserWindows() {
		provider.quit();
	}

	@Test
	public void createFirefox() throws Exception {
		WebDriver firefoxDriver = provider.getDriver(FIREFOX);
		assertTrue(firefoxDriver instanceof FirefoxDriver);
		assertEquals(firefoxDriver, provider.getDriver(FIREFOX));
	}

	@Test
	public void createHtmlUnit() throws Exception {
		WebDriver htmlUnit = provider.getDriver(HTMLUNIT);
		assertTrue(htmlUnit instanceof HtmlUnitDriver);
		assertEquals(htmlUnit, provider.getDriver(HTMLUNIT));
	}

	@Test
	public void createUnknownDriver() throws Exception {
		try {
			provider.getDriver("unknown");
			fail("No exception");
		} catch (UnsupportedWebDriverException exception) {
			assertEquals("Unsupported web driver 'unknown'", exception.getMessage());
		}
	}

	@Test(expected = NoDriverRegisteredException.class)
	public void getOnlyDriverThrowsExceptionIfNoDriverIsAvailable() throws Exception {
		provider.getOnlyDriver();
	}

	@Test(expected = MultipleDriversRegisteredException.class)
	public void getOnlyDriverThrowsExceptionIfMoreThanOneDriverIsAvailable() throws Exception {
		provider.getDriver(HTMLUNIT);
		provider.getDriver(FIREFOX);
		provider.getOnlyDriver();
	}

	@Test
	public void getOnlyDriverForSingleKnownDriver() throws Exception {
		WebDriver webDriver = provider.getDriver(HTMLUNIT);
		assertEquals(webDriver, provider.getOnlyDriver());
	}

	@Test
	public void closeWindowCausesNewDriverToBeUsed() throws Exception {
		WebDriver driver = provider.getDriver(HTMLUNIT);
		provider.closeWindow(driver);
		assertNotSame(driver, provider.getDriver(HTMLUNIT));
	}

	@Test
	public void closeWindowShouldNotCauseOtherDriverToBeUsedWhenMoreWindowsAreOpen() throws Exception {
		WebDriver driver = provider.getDriver(HTMLUNIT);
		driver.get("http://www.google.com");
		((JavascriptExecutor) driver).executeScript("window.open('', 'other window')");
		provider.closeWindow(driver);
		assertSame(driver, provider.getDriver(HTMLUNIT));
	}

	@Test
	public void quitDriverCausesNewDriverToBeUsed() throws Exception {
		WebDriver driver = provider.getDriver(HTMLUNIT);
		provider.quit(driver);
		assertNotSame(driver, provider.getDriver(HTMLUNIT));
	}
}
