package cn.examen.exception;

public class GlobolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GlobolException() {
		super();
	}

	public GlobolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GlobolException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobolException(String message) {
		super(message);
	}

	public GlobolException(Throwable cause) {
		super(cause);
	}

}
