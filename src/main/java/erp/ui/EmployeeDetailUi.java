package erp.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmployeeDetailService;
import erp.ui.content.EmployeeDetailPanel;

@SuppressWarnings("serial")
public class EmployeeDetailUi extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pbtns;
	private EmployeeDetailPanel pItem;
	private JButton btnAdd;
	private EmployeeDetailService service;
	private JButton btnCencel;

	public EmployeeDetailUi(boolean isBtns, EmployeeDetailService service) {
		this.service = service;
		initialize(isBtns);
	}

	private void initialize(boolean isBtns) {
		setTitle("사원 세부정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);

		pbtns = new JPanel();

		contentPane.add(pbtns, BorderLayout.SOUTH);

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pbtns.add(btnAdd);

		btnCencel = new JButton();
		btnCencel.addActionListener(this);
		pbtns.add(btnCencel);

		if (isBtns) {

			btnAdd.setText("추가");
			btnCencel.setText("취소");
		} else {
			btnAdd.setText("수정");
			btnCencel.setText("삭제");
		}

	}

	public void setEmpNo(Employee empNo) {
		pItem.setTfEmpno(empNo);
	}

	public void setDetailItems(EmployeeDetail empDetail) {
//		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("삭제")) {
			actionPerformedBtndel(e);
		}
		if (e.getActionCommand().equals("취소")) {
			actionPerformedBtnCencel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail updateEmpDetail = pItem.getItem();
		service.modifyEmployeeDetail(updateEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "수정 완료");
		dispose();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
//		1. 입력한 empdetail 가져오기
//		2. service에 적용
		EmployeeDetail newEmpDetail = pItem.getItem();
		service.addEmployeeDetail(newEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "추가 완료");
		dispose();
	}

	protected void actionPerformedBtnCencel(ActionEvent e) {
		pItem.clearTf();
		if (btnAdd.getText().equals("수정")) {
			btnAdd.setText("추가");
		}
	}

	protected void actionPerformedBtndel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo()));
		JOptionPane.showMessageDialog(null, "삭제 완료");
		pItem.clearTf();
	}
}
