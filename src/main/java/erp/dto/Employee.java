package erp.dto;

public class Employee {
	private int empNo;
	private String empName;
	private Title tNo;
	private Employee manager;
	private int salary;
	private Department dept;
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title tNo, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.tNo = tNo;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title gettNo() {
		return tNo;
	}

	public void settNo(Title tNo) {
		this.tNo = tNo;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return String.format("Employee [empNo=%s, empName=%s, title=%s, manager=%s, salary=%s, dept=%s]", empNo,
				empName, tNo, manager, salary, dept);
	}

}
