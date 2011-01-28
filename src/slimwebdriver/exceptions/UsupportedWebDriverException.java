package slimwebdriver.exceptions;

public class UsupportedWebDriverException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsupportedWebDriverException(String browserType) {
		super("Unsupported web driver '" + browserType + "'");
	}

}
