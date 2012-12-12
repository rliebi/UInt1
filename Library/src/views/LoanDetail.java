package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class LoanDetail {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblTo;
	private JLabel lblCustomer;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblLoanDetail;
	private JButton btnReturnBook;
	private JButton btnReload;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JLabel lblReturned;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanDetail window = new LoanDetail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoanDetail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 80, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 20, 0, 0, 0, 20, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblLoanDetail = new JLabel("Loan Detail");
		lblLoanDetail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLoanDetail = new GridBagConstraints();
		gbc_lblLoanDetail.gridwidth = 6;
		gbc_lblLoanDetail.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoanDetail.gridx = 0;
		gbc_lblLoanDetail.gridy = 0;
		panel.add(lblLoanDetail, gbc_lblLoanDetail);
		
		JLabel lblTitel = new JLabel("Titel:");
		GridBagConstraints gbc_lblTitel = new GridBagConstraints();
		gbc_lblTitel.anchor = GridBagConstraints.EAST;
		gbc_lblTitel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitel.gridx = 0;
		gbc_lblTitel.gridy = 2;
		panel.add(lblTitel, gbc_lblTitel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.gridx = 4;
		gbc_lblId.gridy = 2;
		panel.add(lblId, gbc_lblId);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFrom = new JLabel("From:");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.anchor = GridBagConstraints.EAST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 0;
		gbc_lblFrom.gridy = 3;
		panel.add(lblFrom, gbc_lblFrom);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		lblTo = new JLabel("To:");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.anchor = GridBagConstraints.EAST;
		gbc_lblTo.gridx = 2;
		gbc_lblTo.gridy = 3;
		panel.add(lblTo, gbc_lblTo);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 3;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		lblCustomer = new JLabel("Customer:");
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.anchor = GridBagConstraints.EAST;
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 0;
		gbc_lblCustomer.gridy = 4;
		panel.add(lblCustomer, gbc_lblCustomer);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.gridwidth = 4;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 4;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		lblReturned = new JLabel("Returned:");
		GridBagConstraints gbc_lblReturned = new GridBagConstraints();
		gbc_lblReturned.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturned.gridx = 0;
		gbc_lblReturned.gridy = 5;
		panel.add(lblReturned, gbc_lblReturned);
		
		rdbtnYes = new JRadioButton("Yes");
		GridBagConstraints gbc_rdbtnYes = new GridBagConstraints();
		gbc_rdbtnYes.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYes.gridx = 1;
		gbc_rdbtnYes.gridy = 5;
		panel.add(rdbtnYes, gbc_rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
		gbc_rdbtnNo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNo.gridx = 3;
		gbc_rdbtnNo.gridy = 5;
		panel.add(rdbtnNo, gbc_rdbtnNo);
		GridBagConstraints gbc_btnReload = new GridBagConstraints();
		gbc_btnReload.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnReload.insets = new Insets(0, 0, 0, 5);
		gbc_btnReload.gridx = 1;
		gbc_btnReload.gridy = 6;
		panel.add(btnReload, gbc_btnReload);
		
		btnReturnBook = new JButton("Save");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnReturnBook = new GridBagConstraints();
		gbc_btnReturnBook.gridwidth = 2;
		gbc_btnReturnBook.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnReturnBook.insets = new Insets(0, 0, 0, 5);
		gbc_btnReturnBook.gridx = 2;
		gbc_btnReturnBook.gridy = 6;
		panel.add(btnReturnBook, gbc_btnReturnBook);
	}

}
