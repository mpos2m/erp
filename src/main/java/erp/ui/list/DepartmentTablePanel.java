package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.service.DepartmentService;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {
	private DepartmentService service = new DepartmentService();
	
	@Override
	public void initList() {
		list = service.showDepartment();
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0,1);
		setTableCellWidth(100,250);
	}

	@Override
	public Object[] toArray(Department d) {

		return new Object[] {d.getDeptNo(), d.getDeptName(), d.getFloor()};
	}

	@Override
	public String[] getColumnNames() {

		return new String[] {"부서번호","부서이름","위치"};
	}

}
