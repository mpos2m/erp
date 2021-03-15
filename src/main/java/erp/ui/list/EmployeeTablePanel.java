package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Employee;
import erp.service.EmployeeService;

@SuppressWarnings("serial")
public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {
	private EmployeeService service;

	@Override
	public void initList() {
		list = service.showEmployees();

	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		setTableCellAlign(SwingConstants.RIGHT, 4);
		// 컬럼별 너비 조정
		setTableCellWidth(100, 150, 100, 200, 150, 100);

	}

	@Override
	public Object[] toArray(Employee t) {
		return new Object[] { t.getEmpNo(), t.getEmpName(), String.format("%s(%d)", t.getTitle().gettName(), t.getDept().getDeptNo()),
				String.format("%s(%d)", t.getManager().getEmpName(), t.getManager().getEmpNo()),
				t.getSalary(), t.getDept().getDeptName()};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "사원번호", "사원명", "직책", "직속상사", "급여", "부서" };
	}

	public void setService(EmployeeService service) {
		this.service = service;
	}

}
