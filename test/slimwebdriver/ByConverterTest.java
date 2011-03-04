package slimwebdriver;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class ByConverterTest {
	private ByConverter converter;

	@Before
	public void setUp() {
		converter = new ByConverter();
	}

	@Test
	public void byClassName() throws Exception {
		assertEquals(By.className("classy"), converter.fromString("class name: classy"));
		assertEquals(By.className("classy"), converter.fromString("class name:classy"));
	}

	@Test
	public void convertToString() throws Exception {
		By byClass = By.className("classy");
		assertEquals("By.className: classy", converter.toString(byClass));
	}
}
