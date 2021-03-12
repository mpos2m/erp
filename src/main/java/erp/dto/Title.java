package erp.dto;

public class Title {
	private int tNo;
	private String tName;
	
	public Title() {
		
	}

	public Title(int tNo) {
		this.tNo = tNo;
	}

	public Title(int tNo, String tName) {
		this.tNo = tNo;
		this.tName = tName;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	@Override
	public String toString() {
		return String.format("직책 번호 = %s, 직책 이름 = %s", tNo, tName);
	}
	
}
