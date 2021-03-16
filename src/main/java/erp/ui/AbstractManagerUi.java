package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Title;
import erp.ui.content.AbstractConentPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManagerUi<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;

	protected AbstractConentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;

	public AbstractManagerUi() {
		setService(); // service연셜
		initialize();
		tableLoadData(); // Component load data
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = createContentPanel();
		contentPane.add((AbstractConentPanel<Title>) pContent);

		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		JButton btnClear = new JButton("취소");
		pBtns.add(btnClear);

		pList = createTablePanel();

		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);

		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원 보기");
		empListByDeptItem.addActionListener(this);
		popMenu.add(empListByDeptItem);

		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}

				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}

				if (e.getActionCommand().contentEquals("동일 부서 사원 보기")) {
					actionPerformedMenuGubun();
				}
			}else {
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected abstract void setService();

	protected abstract void tableLoadData();

	protected abstract AbstractConentPanel<T> createContentPanel();

	protected abstract AbstractCustomTablePanel<T> createTablePanel();
	
	protected abstract void actionPerformedBtnUpdate(ActionEvent e);

	protected abstract void actionPerformedMenuGubun();

	protected abstract void actionPerformedMenuDelete();
	
	protected abstract void actionPerformedMenuUpdate();	

	protected abstract void actionPerformedBtnAdd(ActionEvent e);
}
