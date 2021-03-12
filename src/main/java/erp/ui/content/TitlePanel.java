package erp.ui.content;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import erp.dto.Title;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel {
	private JTextField tfTitleName;
	private JTextField tfTitleNo;


	public TitlePanel() {

		initialize();
	}
	private void initialize() {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setBorder(new TitledBorder(new EmptyBorder(20, 20, 20, 20), "직책정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 20));
		
			
		
		JLabel lblTitleNo = new JLabel("직책 번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책 이름");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleName);
		
		tfTitleName = new JTextField();
		add(tfTitleName);
		tfTitleName.setColumns(10);	
	}
	
	public void setTitle(Title title) {
		tfTitleName.setText(title.gettName());
		tfTitleNo.setText(title.gettNo()+"");
		
		tfTitleNo.setEditable(false);
	}

	public Title getTitle() {
		validCheck();
		String titleName = tfTitleName.getText().trim();
		int titleNo = Integer.parseInt(tfTitleNo.getText().trim());
		return new Title(titleNo,titleName);
	}
	private void validCheck() {
		 if(tfTitleNo.getText().contentEquals("") || tfTitleName.getText().equals("")) {
			 throw new InvalidCheckException();
		 }
		
	}
	public void clearTf() {
		tfTitleName.setText("");
		tfTitleNo.setText("");
	}
}
