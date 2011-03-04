package slimwebdriver.exceptions;

public class UnsupportedWebDriverException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedWebDriverException(String browserType) {
		super("Unsupported web driver '" + browserType + "'");
	}

}
