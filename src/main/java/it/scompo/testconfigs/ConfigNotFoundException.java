package it.scompo.testconfigs;

public class ConfigNotFoundException extends Exception {

	/**
	 * uid
	 */
	private static final long serialVersionUID = 4110081172859224191L;

	/**
	 * 
	 */
	public ConfigNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConfigNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ConfigNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ConfigNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
