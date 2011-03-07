package pageobjects;

import slimwebdriver.SlimPageFactory;

public class DemoFitnessePageFactory extends SlimPageFactory {

	public static final String FILES_PAGE = "files page";
	public static final String FITNESSE_STARTPAGE = "fitnesse startpage";
	public static final String DELAYS_WITH_AJAX = "delays with ajax";

	@Override
	protected void registerPageObjectClasses() {
		registerPageObjectClass(FITNESSE_STARTPAGE, FitNesseStartPage.class);
		registerPageObjectClass(FILES_PAGE, FitNesseFilesPage.class);
		registerPageObjectClass(DELAYS_WITH_AJAX, DelaysWithAjaxPage.class);
	}

}
