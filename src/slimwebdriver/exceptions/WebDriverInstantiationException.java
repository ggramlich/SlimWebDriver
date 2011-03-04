package slimwebdriver.exceptions;

public class WebDriverInstantiationException extends RuntimeException {

	public WebDriverInstantiationException(Exception exception) {
		super(exception);
	}

	private static final long serialVersionUID = 1L;

}
