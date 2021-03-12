package erp.dao;

import java.util.List;

import erp.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentBydeptNo(Department dept);
	
	int insertDepartment(Department dept);
	int updateDepartment(Department dept);
	int deleteDepartment(int dept);
	
}
