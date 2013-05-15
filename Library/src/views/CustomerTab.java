package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import components.MySearchField;

import viewModels.CustomerTableModel;
import viewModels.LendingTableModel;
import domain.Customer;
import domain.Library;
import domain.Loan;

public class CustomerTab extends JPanel implements Observer {
	private static final long serialVersionUID = 6034035113335278353L;
	private static final Color background_Color = new Color(226, 226, 226);
	private Library library;
	private NewCustomer newCustomerWindow;
	private EditCustomer editCustomerWindow;
	private JTextField txtSearchfield;
	private JTable customer_table;
	private JLabel displayNrCustomer;
	private JTable customer_loan_jtable;
	private WarningWindow warningWindow;
	private java.util.List<RowFilter<Object, Object>> filters_customer = new ArrayList<RowFilter<Object, Object>>(
			3);
	private java.util.List<RowFilter<Object, Object>> filters_loans = new ArrayList<RowFilter<Object, Object>>(
			3);

	public CustomerTab() {
		super();
		this.library = new Library();
		initialize();
	}

	public CustomerTab(Library library) {
		super();
		this.library = library;
		library.addObserver(this);
		initialize();
	}

	private void initialize() {
		GridBagLayout gbl_customerTab = new GridBagLayout();
		gbl_customerTab.columnWidths = new int[] { 0, 0 };
		gbl_customerTab.rowHeights = new int[] { 48, 150, 100, 0 };
		gbl_customerTab.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_customerTab.rowWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gbl_customerTab);

		JPanel panelCustomerStats = new JPanel();
		panelCustomerStats.setBackground(background_Color);
		panelCustomerStats.setBorder(new TitledBorder(null,
				"Customer Statistics", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagConstraints gbc_panelCustomerStats = new GridBagConstraints();
		gbc_panelCustomerStats.insets = new Insets(0, 0, 5, 0);
		gbc_panelCustomerStats.fill = GridBagConstraints.BOTH;
		gbc_panelCustomerStats.gridx = 0;
		gbc_panelCustomerStats.gridy = 0;
		add(panelCustomerStats, gbc_panelCustomerStats);
		GridBagLayout gbl_panelCustomerStats = new GridBagLayout();
		gbl_panelCustomerStats.columnWidths = new int[] { 76, 91, 0, 0, 0 };
		gbl_panelCustomerStats.rowHeights = new int[] { 16, 0 };
		gbl_panelCustomerStats.columnWeights = new double[] { 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelCustomerStats.rowWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		panelCustomerStats.setLayout(gbl_panelCustomerStats);

		JLabel lblNrCustomers = new JLabel("Nr. Customers");
		GridBagConstraints gbc_lblNrCustomers = new GridBagConstraints();
		gbc_lblNrCustomers.insets = new Insets(0, 0, 0, 5);
		gbc_lblNrCustomers.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNrCustomers.gridx = 0;
		gbc_lblNrCustomers.gridy = 0;
		panelCustomerStats.add(lblNrCustomers, gbc_lblNrCustomers);

		displayNrCustomer = new JLabel(library.getCustomers().size() + "");
		GridBagConstraints gbc_displayNrCustomer = new GridBagConstraints();
		gbc_displayNrCustomer.insets = new Insets(0, 0, 0, 5);
		gbc_displayNrCustomer.anchor = GridBagConstraints.WEST;
		gbc_displayNrCustomer.gridx = 1;
		gbc_displayNrCustomer.gridy = 0;
		panelCustomerStats.add(displayNrCustomer, gbc_displayNrCustomer);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(background_Color);
		panel_1.setBorder(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 200, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
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
				// TODO Correctly implement search with library observe
				// on selection, set correct filter instead of setting model
				// customer_loan_jtable.setModel(new
				// LendingTableModel(library));
				filterLoans();

			}

			private void filterLoans() {
				RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
					public boolean include(
							Entry<? extends Object, ? extends Object> entry) {
						for (int i = 0; i < library.getCustomerOngoingLoans(
								getSelectedCustomer()).size(); i++) {
							if (entry.getValue(1).equals(
									library.getCustomerOngoingLoans(
											getSelectedCustomer()).get(i)
											.getCopy().getInventoryNumber()))
								return true;
						}
						return false;
					}
				};
				filters_loans.add(rf);
				applyFilter(customer_loan_jtable, filters_loans);
			}
		});
		customer_table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					arg0.consume();
					openEditCustomerWindow();
				}
			}
		});
		customer_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(customer_table);
		customer_table.setModel(new CustomerTableModel(library));
		customer_table.getColumnModel().getColumn(0).setMaxWidth(30);
		customer_table.getColumnModel().getColumn(1).setMaxWidth(30);
		customer_table.setSurrendersFocusOnKeystroke(false);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TableRowSorter<CustomerTableModel> customerSorter = new TableRowSorter(customer_table.getModel());
		customer_table.setRowSorter(customerSorter);
		customerSorter.setSortsOnUpdates(true);

		JButton btnDisplayCustomer = new JButton("Display Customer");
		btnDisplayCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					openEditCustomerWindow();
				} catch (IndexOutOfBoundsException e) {
					warningWindow = new WarningWindow(
							"Please select a customer!");
					warningWindow.setVisible();
				}
			}
		});

		JLabel lblSearch = new JLabel("Search: ");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		panel_1.add(lblSearch, gbc_lblSearch);

		txtSearchfield = new MySearchField(customer_table, 0, filters_customer);
		GridBagConstraints gbc_txtSearchfield_1 = new GridBagConstraints();
		gbc_txtSearchfield_1.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearchfield_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchfield_1.gridx = 1;
		gbc_txtSearchfield_1.gridy = 1;
		panel_1.add(txtSearchfield, gbc_txtSearchfield_1);
		txtSearchfield.setColumns(10);
		GridBagConstraints gbc_btnDisplaySelected_1 = new GridBagConstraints();
		gbc_btnDisplaySelected_1.anchor = GridBagConstraints.EAST;
		gbc_btnDisplaySelected_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplaySelected_1.gridx = 2;
		gbc_btnDisplaySelected_1.gridy = 1;
		panel_1.add(btnDisplayCustomer, gbc_btnDisplaySelected_1);

		JButton btnNewCustomer = new JButton("New Customer");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newCustomerWindow = new NewCustomer(new Customer("Last",
						"First"), library);
				newCustomerWindow.setVisible();
			}
		});
		GridBagConstraints gbc_btnNewCustomer = new GridBagConstraints();
		gbc_btnNewCustomer.gridx = 3;
		gbc_btnNewCustomer.gridy = 1;
		panel_1.add(btnNewCustomer, gbc_btnNewCustomer);

		JPanel panel = new JPanel();
		panel.setBackground(background_Color);
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 254, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 78, 29, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		customer_loan_jtable = new JTable();
		scrollPane.setViewportView(customer_loan_jtable);
		customer_loan_jtable.setModel(new LendingTableModel(library));

		// TODO set here non matching filter, to clear list for the beginning
		final TableRowSorter<AbstractTableModel> sorter = new TableRowSorter<AbstractTableModel>(
				(AbstractTableModel) customer_loan_jtable.getModel());
		customer_loan_jtable.setRowSorter(sorter);
		// sorter.setRowFilter(RowFilter.andFilter(arg0)
		JButton btnNewButton = new JButton("Return Loan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (getSelectedLoan().isOverdue()) {
						warningWindow = new WarningWindow(
								"Ask for 3 CHF, book is late!");
						warningWindow.setVisible();
					}
					int curentselection = customer_table.getSelectedRow();
					getSelectedLoan().returnCopy();
					customer_table.changeSelection(curentselection, 1, false, false);
				} catch (IndexOutOfBoundsException e) {
					warningWindow = new WarningWindow("Please Select a Loan!");
					warningWindow.setVisible();
				}
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
					warningWindow = new WarningWindow(
							"Please Select a customer and Loan!");
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
		panel.add(btnNewButton, gbc_btnNewButton);

	}

	public void updateFields() {
		displayNrCustomer.setText(library.getCustomers().size() + "");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateFields();
	}

	private Loan getSelectedLoan() {
		return library.getCustomerOngoingLoans(getSelectedCustomer()).get(
				customer_loan_jtable
						.convertColumnIndexToModel(customer_loan_jtable
								.getSelectedRow()));
	}

	private Customer getSelectedCustomer() {
		return library.getCustomers().get(
				customer_table.convertRowIndexToModel(customer_table
						.getSelectedRow()));
	}

	private void openEditCustomerWindow() {
		editCustomerWindow = new EditCustomer(getSelectedCustomer());
		editCustomerWindow.setVisible();
	}

	private void applyFilter(JTable table,
			List<RowFilter<Object, Object>> filters) {
		@SuppressWarnings("unchecked")
		TableRowSorter<LendingTableModel> sorter = (TableRowSorter<LendingTableModel>) table
				.getRowSorter();

		RowFilter<Object, Object> serviceFilter = RowFilter.andFilter(filters);

		sorter.setRowFilter(serviceFilter);
		table.setRowSorter(sorter);
		AbstractTableModel t = (AbstractTableModel) table.getModel();
		t.fireTableDataChanged();
	}
}
