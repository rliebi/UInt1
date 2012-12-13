package views;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
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
import java.util.Observable;

import javax.swing.JRadioButton;

import components.MyJTextField;
import controll.OldFormState;
import controll.UnchangedFormState;
import controll.ChangedFormState;

import domain.Book;
import domain.Copy;
import domain.Customer;
import domain.Loan;

public class EditLoan extends AbstractStatefullForm{

	private JFrame frame;
	private MyJTextField txtTitel;
	private MyJTextField txtId;
	private MyJTextField txtFromDate;
	private MyJTextField txtToDate;
	private JLabel lblTo;
	private JLabel lblCustomer;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JLabel lblLoanDetail;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JLabel lblReturned;
	private Loan realLoan;
	private JPanel panel;
	private ButtonGroup rdbtnGroup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loan loan = new Loan(new Customer("LastName", "Firstname"), new Copy(new Book("BookTitel")));
					EditLoan window = new EditLoan(loan);
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
	public EditLoan(Loan loan) {
		realLoan=loan;
		initialize();
		setState(new UnchangedFormState(this));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
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
		
		txtTitel = new MyJTextField("Titel", realLoan.getCopy().getTitle().getName());
		GridBagConstraints gbc_txtTitel = new GridBagConstraints();
		gbc_txtTitel.gridwidth = 3;
		gbc_txtTitel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitel.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitel.gridx = 1;
		gbc_txtTitel.gridy = 2;
		panel.add(txtTitel, gbc_txtTitel);
		txtTitel.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.gridx = 4;
		gbc_lblId.gridy = 2;
		panel.add(lblId, gbc_lblId);
		
		txtId = new MyJTextField("ID", realLoan.getCopy().getInventoryNumber() + "");
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.insets = new Insets(0, 0, 5, 0);
		gbc_txtId.gridx = 5;
		gbc_txtId.gridy = 2;
		panel.add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		JLabel lblFrom = new JLabel("From:");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.anchor = GridBagConstraints.EAST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 0;
		gbc_lblFrom.gridy = 3;
		panel.add(lblFrom, gbc_lblFrom);
		
		txtFromDate = new MyJTextField("From Date", realLoan.getPickupDatetoString());
		GridBagConstraints gbc_txtFromDate = new GridBagConstraints();
		gbc_txtFromDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtFromDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFromDate.gridx = 1;
		gbc_txtFromDate.gridy = 3;
		panel.add(txtFromDate, gbc_txtFromDate);
		txtFromDate.setColumns(10);
		
		lblTo = new JLabel("To:");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.anchor = GridBagConstraints.EAST;
		gbc_lblTo.gridx = 2;
		gbc_lblTo.gridy = 3;
		panel.add(lblTo, gbc_lblTo);
		
		txtToDate = new MyJTextField("To date", realLoan.getdueDatetoString());
		GridBagConstraints gbc_txtToDate = new GridBagConstraints();
		gbc_txtToDate.gridwidth = 3;
		gbc_txtToDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtToDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtToDate.gridx = 3;
		gbc_txtToDate.gridy = 3;
		panel.add(txtToDate, gbc_txtToDate);
		txtToDate.setColumns(10);
		
		lblCustomer = new JLabel("Customer:");
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.anchor = GridBagConstraints.EAST;
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 0;
		gbc_lblCustomer.gridy = 4;
		panel.add(lblCustomer, gbc_lblCustomer);
		
		txtFirstName = new MyJTextField("First Name", realLoan.getCustomer().getSurname());
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 4;
		panel.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new MyJTextField("Last Name", realLoan.getCustomer().getName());
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.gridwidth = 4;
		gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 2;
		gbc_txtLastName.gridy = 4;
		panel.add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);

		
		lblReturned = new JLabel("Returned:");
		GridBagConstraints gbc_lblReturned = new GridBagConstraints();
		gbc_lblReturned.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturned.gridx = 0;
		gbc_lblReturned.gridy = 5;
		panel.add(lblReturned, gbc_lblReturned);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!realLoan.isLent()){
					setUnchanged();
				} else {setChanged();}
			}
		});
		GridBagConstraints gbc_rdbtnYes = new GridBagConstraints();
		gbc_rdbtnYes.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnYes.gridx = 1;
		gbc_rdbtnYes.gridy = 5;
		panel.add(rdbtnYes, gbc_rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(realLoan.isLent()){
					setUnchanged();
				} else {setChanged();}
			}
		});
		if(realLoan.isLent())rdbtnNo.setSelected(true);
		GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
		gbc_rdbtnNo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNo.gridx = 3;
		gbc_rdbtnNo.gridy = 5;
		panel.add(rdbtnNo, gbc_rdbtnNo);
		
		rdbtnGroup = new ButtonGroup();
		rdbtnGroup.add(rdbtnYes);
		rdbtnGroup.add(rdbtnNo);

		btnReload = new JButton("Reload");
		
		GridBagConstraints gbc_btnReload = new GridBagConstraints();
		gbc_btnReload.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnReload.insets = new Insets(0, 0, 0, 5);
		gbc_btnReload.gridx = 1;
		gbc_btnReload.gridy = 6;
		panel.add(btnReload, gbc_btnReload);
		
		
		btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 6;
		panel.add(btnSave, gbc_btnSave);
		
		for(MyJTextField field : getMyFields(panel)){
			field.setEnabled(false);
		}
		addListenertoReloadbtn();
		addListenertoSavebtn();
	}
	
	private void setChanged(){
		setState(new ChangedFormState(this));
	}
	private void setUnchanged(){
		setState(new UnchangedFormState(this));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		setState(new OldFormState(this));
		
	}

	@Override
	public void reloadFieldsfromRealObject() {
		txtTitel.setTextReload(realLoan.getCopy().getTitle().getName());
		txtId.setTextReload(realLoan.getCopy().getInventoryNumber() + "");
		txtFromDate.setTextReload(realLoan.getPickupDatetoString());
		txtToDate.setTextReload(realLoan.getdueDatetoString());
		if(realLoan.isLent()){
			rdbtnNo.setSelected(true);
		} else {rdbtnYes.setSelected(true);}
		setState(new UnchangedFormState(this));
	}

	@Override
	public void saveChangestoRealObject() {
		if(btnActivated(rdbtnYes)){
			realLoan.returnCopy();
		} else {realLoan.unreturnCopy();}
		setState(new UnchangedFormState(this));
	}

	@Override
	public void addListenertoMyFields() {
		addListenertoMyFields(panel,this);
	}

	private boolean btnActivated(JRadioButton button) {
		return button.getSelectedObjects()!=null;
	}

}
