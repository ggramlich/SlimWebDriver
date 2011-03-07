package slimwebdriver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;

import fitnesse.slim.StatementExecutor;
import fitnesse.slim.StatementExecutorConsumer;

public abstract class SlimPageFactory implements StatementExecutorConsumer {
	// TODO provide method on the slim statement executor that does not leak the
	// name
	private static final String ACTOR_INSTANCE_NAME = "scriptTableActor";

	public SlimWebDriver slimWebDriver;

	protected Map<String, Class<? extends SlimPageObject>> pageObjectClasses = new HashMap<String, Class<? extends SlimPageObject>>();

	public SlimPageObject pageObject;

	private StatementExecutor statementExecutor;

	public SlimPageFactory() {
		this(null);
	}

	public SlimPageFactory(String browserType) {
		slimWebDriver = new SlimWebDriver(browserType);
		registerPageObjectClasses();
	}

	protected abstract void registerPageObjectClasses();

	protected void registerPageObjectClass(String page, Class<? extends SlimPageObject> klass) {
		pageObjectClasses.put(page, klass);
	}

	public void setStatementExecutor(StatementExecutor statementExecutor) {
		this.statementExecutor = statementExecutor;
	}

	public boolean weAreOnPage(String page) {
		pageObject = PageFactory.initElements(slimWebDriver.webDriver, getPageObjectClass(page));
		initPageObject();
		return pageObject.verify();
	}

	private void initPageObject() {
		pageObject.setWebDriver(slimWebDriver.webDriver);
		pageObject.setSlimPageFactory(this);
		statementExecutor.setInstance(ACTOR_INSTANCE_NAME, pageObject);
	}

	private Class<? extends SlimPageObject> getPageObjectClass(String page) {
		return pageObjectClasses.get(page);
	}

}