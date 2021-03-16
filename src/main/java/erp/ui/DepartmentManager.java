package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DepartmentService;
import erp.ui.content.DepartmentPanel;
import erp.ui.content.AbstractConenPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private AbstractConenPanel<Department> pContent;
	private DepartmentTablePanel pList;
	private DepartmentService service;
	
	public DepartmentManager() {
		service = new DepartmentService();
		initialize();
	}
	
	private void initialize() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new DepartmentPanel();
		contentPane.add((DepartmentPanel) pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		JButton btnClear = new JButton("취소");
		pBtns.add(btnClear);
		
		pList = new DepartmentTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListner);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListner);
		popMenu.add(deleteItem);
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListner);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
					Department delDept = pList.getItem();
					service.removeDepartment(delDept);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delDept + "삭제 되었습니다.");
				}
				
				if (e.getActionCommand().equals("수정")) {
					Department updateDept = pList.getItem();
					pContent.setItem(updateDept);
					btnAdd.setText("수정");
				}
				
				if (e.getActionCommand().contentEquals("동일 부서 사원 보기")) {
					/*
					 * 1. EmployeeDao -> selectEmployeeByTitle() 추가
					 * 2. EmployeeDaoImpl -> selectEmployeeByTitle() 구현
					 * 3. EmployeeDaoTest -> Test하기
					 * 4. TitleService -> EmployeeDaoImpl field 추가 및 메서드 추가
					 * 5. 아래 기능 추가
					 * 6. 예외찾아서 추가하기 (신규 직책 추가 시 NullPointException)
					 */
					Department dept = pList.getItem();
					List<Employee> list = service.showEmployeeGroupByDepartment(dept);
					
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
				
			}catch (NotSelectedException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	};
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().contentEquals("추가")) {
					actionPerformedBtnAdd(e);
				}else {
					actionPerformedBtnUpdate(e);
				}
			}
		}catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		//pContent에서 수정된 title 가져오기
		//update 수행
		//pList 갱신
		//pContent clearTf()호출하여 초기화
		//btnAdd 텍스트 변경 수정->추가
		
		Department updateDept = pContent.getItem();
		service.modifyDepartment(updateDept);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() + "정보가 수정되었습니다.");
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department addDept = pContent.getItem();
		service.addDepartment(addDept);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addDept + " 추가했습니다.");
	}
}
