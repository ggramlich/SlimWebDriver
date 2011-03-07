package slimwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import fitnesse.slim.StatementExecutor;
import fitnesse.slim.StatementExecutorConsumer;

public abstract class SlimPageObject implements StatementExecutorConsumer {
	// TODO provide method on the slim statement executor that does not leak the
	// name
	private static final String ACTOR_INSTANCE_NAME = "scriptTableActor";

	protected WebDriver webDriver;

	private StatementExecutor statementExecutor;

	// Cannot use the SlimWebDriver here directly, because the PageFactory tries
	// to initialize that field somehow
	private WebDriverContainer webDriverContainer;

	public SlimPageObject() {
		webDriverContainer = new WebDriverContainer();
		webDriver = webDriverContainer.getSlimWebDriver().webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public abstract boolean verify();

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	protected SlimWebDriver getSlimWebDriver() {
		return webDriverContainer.getSlimWebDriver();
	}

	public void setStatementExecutor(StatementExecutor statementExecutor) {
		this.statementExecutor = statementExecutor;
	}

	public boolean transitionToPage(SlimPageObject pageObject) {
		pageObject.setStatementExecutor(statementExecutor);
		statementExecutor.setInstance(ACTOR_INSTANCE_NAME, pageObject);
		return pageObject.verify();
	}

	private class WebDriverContainer {
		private SlimWebDriver slimWebDriver;

		public SlimWebDriver getSlimWebDriver() {
			if (slimWebDriver == null) {
				slimWebDriver = new SlimWebDriver();
			}
			return slimWebDriver;
		}
	}

}
