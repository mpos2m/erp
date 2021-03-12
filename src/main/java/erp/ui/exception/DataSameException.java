package erp.ui.exception;

public class DataSameException extends RuntimeException {

	public DataSameException() {
		super("동일한 직책명이 있습니다.");
	}

	public DataSameException(Throwable cause) {
		super("동일한 직책명이 있습니다.", cause);
	}

}
