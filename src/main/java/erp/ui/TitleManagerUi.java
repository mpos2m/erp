package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.AbstractConentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManagerUi extends AbstractManagerUi<Title> {
	
	private TitleService service;
	
	@Override
	protected void setService() {
		service = new TitleService();
	}

	@Override
	protected void tableLoadData() {
		((TitleTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractConentPanel<Title> createContentPanel() {
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> createTablePanel() {
		return new TitleTablePanel();
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Title updateTitle = pContent.getItem();
		service.modifyTitle(updateTitle);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateTitle.gettName() + "정보가 수정되었습니다.");
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Title title = pList.getItem();
		List<Employee> list = service.showEmployeeGroupByTitle(title);
		
		if (list == null) {
			JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		List<String> strList = list
				.parallelStream()
				.map( s->{ return String.format("%s(%d)", s.getEmpName(), s.getEmpNo()); })
				.collect(Collectors.toList());
		
		JOptionPane.showMessageDialog(null, strList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + "삭제 되었습니다.");
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Title updateTitle = pList.getItem();
		pContent.setItem(updateTitle);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title addTitle = pContent.getItem();
		service.addTitle(addTitle);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addTitle + " 추가했습니다.");
	}

}
