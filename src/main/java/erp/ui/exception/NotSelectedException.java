package erp.ui.exception;

@SuppressWarnings("serial")
public class NotSelectedException extends RuntimeException {
	
	public NotSelectedException() {
		super ("선택되지 않았습니다.");
	}
	
	public NotSelectedException(Throwable cause) {
		super ("선택되지 않았습니다.", cause);
	}
}
