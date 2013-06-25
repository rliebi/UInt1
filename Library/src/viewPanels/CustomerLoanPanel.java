package viewPanels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import localization.Messages;
import settings.Icons;
import viewModels.CustomerCopiesTableModel;
import views.CustomerAddLoanView;
import views.ReturnLoanView;
import views.ReturnMultipleLoansView;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import components.IconCellRenderer;
import components.MyJTable;

import controller.TableSelectListener;
import domain.Customer;
import domain.Library;
import domain.Loan;

public class CustomerLoanPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6720768505050317833L;
	private Library library;
	private MyJTable table;
	private JPanel table_panel;
	private JPanel south_panel;

	private JPanel button_panel;
	private JDialog parent;
	private Customer customer;

	/**
	 * @wbp.parser.constructor
	 */

	public CustomerLoanPanel(Library library2, Customer customer, JDialog p) {
		this.library = library2;
		this.customer = customer;
		this.parent = p;
		table = new MyJTable(new CustomerCopiesTableModel(library,
				this.customer));
		init();

	}

	private void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel north_panel = new JPanel();
		north_panel.setBorder(new TitledBorder(null, "Copies",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_north_panel = new GridBagConstraints();
		gbc_north_panel.insets = new Insets(0, 0, 5, 0);
		gbc_north_panel.fill = GridBagConstraints.BOTH;
		gbc_north_panel.gridx = 0;
		gbc_north_panel.gridy = 0;
		add(north_panel, gbc_north_panel);
		GridBagLayout gbl_north_panel = new GridBagLayout();
		gbl_north_panel.columnWidths = new int[] { 0, 0 };
		gbl_north_panel.rowHeights = new int[] { 0, 0 };
		gbl_north_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_north_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		north_panel.setLayout(gbl_north_panel);

		table_panel = new JPanel();
		GridBagConstraints gbc_table_panel = new GridBagConstraints();
		gbc_table_panel.fill = GridBagConstraints.BOTH;
		gbc_table_panel.gridx = 0;
		gbc_table_panel.gridy = 0;
		north_panel.add(table_panel, gbc_table_panel);

		createtableScrollPane(table_panel).setViewportView(table);
		setModel();
		button_panel = new JPanel();
		table_panel.add(button_panel, BorderLayout.NORTH);
		ButtonBarBuilder addRemoveCopy = new ButtonBarBuilder();
		final JButton addLoanBtn = new JButton();

		addLoanBtn.setAction(new AddLoanAction());
		addLoanBtn.setIcon(Icons.IconEnum.ADDLOAN.getIcon(16));
		addLoanBtn.setText(Messages.getString("CopyPanel.btnAddloan.text"));
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				setAddLoanButtonState(addLoanBtn);

			}

		});
		setAddLoanButtonState(addLoanBtn);

		JButton returnLoanBtn = new JButton();
		returnLoanBtn.setAction(new ReturnAction());
		returnLoanBtn.setIcon(Icons.IconEnum.CLOSELOAN.getIcon(16));
		returnLoanBtn.setText(Messages
				.getString("CopyPanel.btnReturnLoan.text"));
		returnLoanBtn.setEnabled(false);

		table.getSelectionModel().addListSelectionListener(
				new TableSelectListener(table, returnLoanBtn, returnLoanBtn
						.getText(), false));

		addRemoveCopy.addButton(returnLoanBtn, addLoanBtn);
		button_panel.setLayout(new BorderLayout(0, 0));
		button_panel.add(addRemoveCopy.build(), BorderLayout.EAST);

		south_panel = new JPanel();
		GridBagConstraints gbc_south_panel = new GridBagConstraints();
		gbc_south_panel.fill = GridBagConstraints.BOTH;
		gbc_south_panel.gridx = 0;
		gbc_south_panel.gridy = 1;
		add(south_panel, gbc_south_panel);
		south_panel.setLayout(new BorderLayout(0, 0));

		ButtonBarBuilder buttonBar = new ButtonBarBuilder();
		JButton closeBtn = new JButton();
		closeBtn.addActionListener(new CloseLoanAction());
		closeBtn.setIcon(Icons.IconEnum.CANCEL.getIcon(24));
		closeBtn.setText(Messages.getString("Global.btnClose.title"));
		buttonBar.addButton(closeBtn);
		south_panel.add(buttonBar.build(), BorderLayout.EAST);
	}

	/**
	 * 
	 */
	private void setModel() {

		table.getColumnModel().getColumn(0)
				.setCellRenderer(new IconCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setMaxWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(500);
	}

	private JScrollPane createtableScrollPane(JPanel panel_1) {
		table_panel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane);
		return scrollPane;
	}

	private final class ReturnAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private ReturnAction() {
			super("Return Loan");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRowCount() == 1) {
				// int rightNr =getSelectedCopies().get(0);
				new ReturnLoanView(library, library.getCustomerOngoingLoans(
						customer).get(0)).setVisible(true);
			}
			if (table.getSelectedRowCount() > 1) {
				List<Loan> loanList = new ArrayList<Loan>();
				for (int i : table.getSelectedRows()) {
					loanList.add(library.getCustomerOngoingLoans(customer).get(
							i));
				}
				new ReturnMultipleLoansView(library, loanList).setVisible(true);
			}
		}
	}

	private final class CloseLoanAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private CloseLoanAction() {
			super("Close");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();

		}
	}

	private void setAddLoanButtonState(final JButton addLoanBtn) {
		if (library.isCustomerTrustworthy(customer))
			addLoanBtn.setEnabled(true);
		else
			addLoanBtn.setEnabled(false);
	}

	private final class AddLoanAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5190401524732242389L;

		@Override
		public void actionPerformed(ActionEvent e) {
			new CustomerAddLoanView(customer, library);
		}

	}

	protected CustomerLoanPanel getCopyPanel() {
		return this;
	}

	protected Stack<Loan> getSelectedCopies() {
		Stack<Loan> loans = new Stack<Loan>();
		if (table.getSelectedRowCount() > 1) {
			int[] rows = table.getSelectedRows();
			Loan c = null;
			for (int i : rows) {
				c = library.getCustomerOngoingLoans(customer).get(
						table.convertRowIndexToModel(i));
				loans.add(c);
			}
		}
		if (table.getSelectedRowCount() == 1)
			loans.add(library.getCustomerOngoingLoans(customer).get(
					table.convertRowIndexToModel(table.getSelectedRow())));
		return loans;
	}

	public CustomerLoanPanel(LayoutManager layout) {
		super(layout);

	}

	public CustomerLoanPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

	}

	public CustomerLoanPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

	}

	private void dispose() {
		parent.dispose();
	}
}
