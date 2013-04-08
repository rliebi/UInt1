package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import components.MySearchField;

import viewModels.CustomerTableModel;
import viewModels.LendingTableModel;
import domain.Book;
import domain.Customer;
import domain.Library;
import domain.Loan;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;

public class NewLoan extends JFrame implements Observer{
	private static final long serialVersionUID = 6034035113335278353L;
	private static final Color background_Color = new Color(226, 226, 226);
	private Library library;
	private EditCustomer editCustomerWindow;
	private JTextField txtSearchfield;
	private JTable customer_table;
	private JTable customer_loan_jtable;
	private WarningWindow warningWindow;
	private Book book;
	public NewLoan(){
		super();
		this.library = new Library();
		initialize();
	}
	
	public NewLoan(Library library){
		super();
		this.library = library;
		library.addObserver(this);
		initialize();
	}
	public NewLoan(Library library, Book book){
		super();
		this.library = library;
		this.book = book;
		library.addObserver(this);
		initialize();
	}
	private void initialize (){
		this.setBounds(100, 100, 600, 500);
		this.setMinimumSize(new Dimension(416, 262));
		GridBagLayout gbl_customerTab = new GridBagLayout();
		gbl_customerTab.columnWidths = new int[]{0, 0};
		gbl_customerTab.rowHeights = new int[]{32, 150, 100, 0, 0};
		gbl_customerTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_customerTab.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gbl_customerTab);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChoseCustomerTo = new JLabel(String.format("Chose customer to lend Book %s to:", book.getName()));
		panel_2.add(lblChoseCustomerTo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(background_Color);
		panel_1.setBorder(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 200, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		
		customer_table = new JTable();
		
		customer_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					openEditCustomerWindow();
				}
				//TODO fix this
				customer_loan_jtable.setModel(new LendingTableModel(library));
				

			}
		});
		customer_table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					arg0.consume();
					openEditCustomerWindow();
				}
			}
		});
		customer_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(customer_table);
		customer_table.setModel(new CustomerTableModel(library));
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TableRowSorter<CustomerTableModel> customerSorter = new TableRowSorter( customer_table.getModel());
		
		  class IntComparator implements Comparator<Integer> {
	            public boolean equals(Object o2) {
	                return this.equals(o2);
	            }

				@Override
				public int compare(Integer o1, Integer o2) {
					System.out.println(o1);
					 return o1.compareTo(o2);
					 
				}
	        }

		
		customerSorter.setSortsOnUpdates(true);
		customerSorter.setComparator(0, new IntComparator());
		customer_table.getColumnModel().getColumn(0).setMaxWidth(30);
		customer_table.setRowSorter(customerSorter);
		JLabel lblSearch = new JLabel("Search: ");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		panel_1.add(lblSearch, gbc_lblSearch);
		
		txtSearchfield = new MySearchField(customer_table);
		GridBagConstraints gbc_txtSearchfield_1 = new GridBagConstraints();
		gbc_txtSearchfield_1.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearchfield_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchfield_1.gridx = 1;
		gbc_txtSearchfield_1.gridy = 1;
		panel_1.add(txtSearchfield, gbc_txtSearchfield_1);
		txtSearchfield.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(background_Color);
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{254, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{78, 29, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		customer_loan_jtable = new JTable();
		scrollPane.setViewportView(customer_loan_jtable);
		
		JButton btnApplyLoan = new JButton("ApplyLoan");
		btnApplyLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int loancount = library.getCustomerOngoingLoans(getSelectedCustomer()).size();
				if (loancount>=3){
					warningWindow = new WarningWindow("Cannot have more than 3 Loans!");
					notifyAll();
				}else{
					warningWindow = new WarningWindow(":" + loancount);
					

				}
				System.out.println(getSelectedCustomer());
//				System.out.println(library.getAvailableCopiesOfBook(book).get(0).getTitle());
				
				library.createAndAddLoan(getSelectedCustomer(), library.getAvailableCopiesOfBook(book).get(0));
				
				System.out.println(loancount);
			}

		});
		
		JButton btnDisplayLoan = new JButton("Display Loan");
		btnDisplayLoan.addActionListener(new ActionListener() {
			private EditLoan editLoanWindow;

			public void actionPerformed(ActionEvent arg0) {
				try {
					editLoanWindow = new EditLoan(getSelectedLoan());
					editLoanWindow.setVisible();
				} catch (IndexOutOfBoundsException e) {
					warningWindow = new WarningWindow("Please Select a customer and Loan!");
				}
			}
		});
		GridBagConstraints gbc_btnDisplayLoan = new GridBagConstraints();
		gbc_btnDisplayLoan.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplayLoan.gridx = 1;
		gbc_btnDisplayLoan.gridy = 1;
		panel.add(btnDisplayLoan, gbc_btnDisplayLoan);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnApplyLoan, gbc_btnNewButton);
		
	}
	
	public void updateFields(){
//		displayNrCustomer.setText(library.getCustomers().size()+"");
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateFields();
	}

	private Loan getSelectedLoan() {
		return library.getCustomerOngoingLoans(getSelectedCustomer()).get(customer_loan_jtable.convertColumnIndexToModel(customer_loan_jtable.getSelectedRow()));
	}
	private Customer getSelectedCustomer() {
		return library.getCustomers().get(customer_table.convertRowIndexToModel(customer_table.getSelectedRow()));
	}

	private void openEditCustomerWindow() {
		editCustomerWindow = new EditCustomer(getSelectedCustomer());
		editCustomerWindow.setVisible();
	}

}
