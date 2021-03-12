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
import erp.service.TitleService;
import erp.ui.content.TitlePanel;
import erp.ui.exception.DataSameException;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnadd;
	private TitlePanel pcontent;
	private TitleTablePanel plist;
	private TitleService service;
	private JButton btncencle;
	
	public TitleManager() {
		service = new TitleService();
		initialize();
	}
	private void initialize() {
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pcontent = new TitlePanel();
		contentPane.add(pcontent);
		
		JPanel pbtn = new JPanel();
		contentPane.add(pbtn);
		
		btnadd = new JButton("추가");
		btnadd.addActionListener(this);
		pbtn.add(btnadd);
		
		btncencle = new JButton("취소");
		btncencle.addActionListener(this);
		pbtn.add(btncencle);
		
		plist = new TitleTablePanel();
		plist.setService(service);
		plist.loadData();
		contentPane.add(plist);
		
		JPopupMenu popupMenu = createPopupMenu();
		plist.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListner);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListner);
		popMenu.add(deleteItem);
	
		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(popupMenuListner);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getActionCommand().equals("삭제")) {
					Title deltitle = plist.getItem();
					service.removeTitle(deltitle);
					plist.loadData();
					JOptionPane.showMessageDialog(null, deltitle + " 이 삭제되었습니다.");
				}
				if(e.getActionCommand().equals("수정")) {
					Title updatetitle = plist.getItem();
					pcontent.setTitle(updatetitle);
					if(btnadd.getText().equals("추가")) {
						btnadd.setText("수정");
					}			
				}
			}catch(NotSelectedException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	};

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btncencle) {
			actionPerformedBtncencle(e);
		}
		try {
			if (e.getSource() == btnadd) {
				if(btnadd.getText().equals("추가")) {
					actionPerformedBtnadd(e);
				}else {
					actionPerformedUpdatebtn(e);
				}
			}
		}catch(InvalidCheckException | SqlConstraintException | DataSameException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pcontent.clearTf();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	private void actionPerformedUpdatebtn(ActionEvent e) {
		Title updatetitle = pcontent.getTitle();		
		service.modifyTitle(updatetitle);
		plist.loadData();
		pcontent.clearTf();
		btnadd.setText("추가");
		JOptionPane.showMessageDialog(null, "수정되었습니다.");
	}
	protected void actionPerformedBtnadd(ActionEvent e) {
		Title title = pcontent.getTitle();
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + " 이 추가되었습니다.");
		plist.loadData();
		pcontent.clearTf();
	}
	protected void actionPerformedBtncencle(ActionEvent e) {
		pcontent.clearTf();
		btnadd.setText("추가");
	}
}
