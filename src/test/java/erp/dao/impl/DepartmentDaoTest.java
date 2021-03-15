package erp.dao.impl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.DepartmentDao;
import erp.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s()%n", "test04SelectDepartmentByAll");
		List<Department> list = dao.selectDepartmentByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectDepartmentByNo() {
		System.out.printf("%s()%n", "test05SelectDepartmentByNo");
		Department dept = dao.selectDepartmentByNo(new Department(3));
		Assert.assertNotNull(dept);
		System.out.println(dept);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "test01InsertDepartment");
		Department newDept = new Department(5, "테스트", 20);
		int res = dao.insertDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDept));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n", "test02UpdateDepartment");
		Department newDept = new Department(5, "테스트2", 30);
		int res = dao.updateDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDept));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n", "test03DeleteDepartment");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1, res);
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
	}

}
