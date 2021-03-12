package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class Emp_detail {
	private int empNo;
	private boolean gender;
	private Date hiredate;
	private byte[] pic;
	
	public Emp_detail() {

	}

	public Emp_detail(int empNo) {
		this.empNo = empNo;
	}

	public Emp_detail(int empNo, boolean gender, Date hiredate, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hiredate = hiredate;
		this.pic = pic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return String.format("Emp_detail [empNo=%s, gender=%s, hiredate=%s, pic=%s]", empNo, gender, hiredate,
				Arrays.toString(pic));
	}
	
}
