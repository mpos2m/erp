package erp.dao.impl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.TitleDao;
import erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoImplTest {

	private static TitleDao dao = TitleDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test03SelectTitleByAll() {
		System.err.printf("%s()%n", "testSelectTitleByAll");
		List<Title> titleList = dao.selectTitleByAll();
		Assert.assertNotNull(titleList);
//		titleList.stream().forEach(System.out::println);
		for (Title t : titleList) {
			System.out.println(t);
		}
	}

	@Test
	public void test05SelectTitleByNo() {
		System.err.printf("%s()%n", "testSelectTitleByNo");
		Title title = new Title(5);
		Title searchTitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchTitle);
		System.out.println(searchTitle);
	}

	@Test
	public void test01InsertTitle() {
		System.err.printf("%s()%n", "testInsertTitle");
		Title newTitle = new Title(6, "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test02UpdateTitle() {
		System.err.printf("%s()%n", "testUpdateTitle");
		Title newTitle = new Title(6, "계약직");
		int res = dao.updateTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test04DeleteTitle() {
		System.err.printf("%s()%n", "testDeleteTitle");

		int res = dao.deleteTitle(6);
		Assert.assertEquals(1, res);
		System.out.println("Delete Complete");
		dao.selectTitleByAll().stream().forEach(System.out::println);
		
	}
}
