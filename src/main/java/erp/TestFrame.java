package erp;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.service.EmployeeService;
import erp.ui.content.EmployeeDetailPanel;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

	private JPanel contentPane;
	private EmployeeTablePanel pList;
	private EmployeeDetailPanel panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		EmployeeService service = new EmployeeService();
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		panel = new EmployeeDetailPanel();
		contentPane.add(panel);
	}


}




