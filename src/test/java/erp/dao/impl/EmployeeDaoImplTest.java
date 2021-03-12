package erp.dao.impl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.EmployeeDao;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoImplTest {

	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();


	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test03SelectEmployeeByAll() {
		System.err.printf("%s()%n", "testSelectEmployeeByAll");
		List<Employee> EmployeeList = dao.selectEmployeeByAll();
		Assert.assertNotNull(EmployeeList);
		for (Employee e : EmployeeList) {
			System.out.println(e);
		}
	}

	@Test
	public void test05SelectEmployeeByNo() {
		System.err.printf("%s()%n", "testSelectEmployeeByNo");
		Employee selEmp = new Employee(1003);
		Employee searchEmployee = dao.selectEmployeeByNo(selEmp);
		Assert.assertNotNull(searchEmployee);
		System.out.println(searchEmployee);
	}

	@Test
	public void test01InsertEmployee() {
		System.err.printf("%s()%n", "testInsertEmployee");
		Employee newEmp = new Employee(1004,"천사",new Title(5),new Employee(4377),2000000,new Department(1));
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(newEmp));
	}

	@Test
	public void test02UpdateEmployee() {
		System.err.printf("%s()%n", "testUpdatetEmployee");
		Employee newEmp = new Employee(1004,"천사2",new Title(5),new Employee(1003),2000000,new Department(1));
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(newEmp));
	}

	@Test
	public void test04DeleteEmployee() {
		System.err.printf("%s()%n", "testDeleteEmployee");

		int res = dao.deleteEmployee(1004);
		Assert.assertEquals(1, res);
		System.out.println("Delete Complete");
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
	}

}
