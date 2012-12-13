package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import viewModels.CustomerTableModel;
import viewModels.LendingTableModel;

import components.MySearchField;

import domain.Library;

public class LendingTab extends JPanel implements Observer{
	private static final long serialVersionUID = 6034035113335278353L;
	private Library library;
	private JTextField txtSearchfield;
	private JTable loan_table;
	private JButton btnDisplay_selected;
	private EditLoan editLoanWindow;
	private JLabel display_number_of_rents;
	private JLabel lblSearch;
	
	public LendingTab(){
		super();
		this.library = new Library();
		initialize();
	}
	
	public LendingTab(Library library){
		super();
		this.library = library;
		this.library.addObserver(this);
		initialize();
		
	}
	
	private void initialize (){
		GridBagLayout gbl_lendingTab = new GridBagLayout();
		gbl_lendingTab.columnWidths = new int[]{0, 0};
		gbl_lendingTab.rowHeights = new int[]{48, 0, 0};
		gbl_lendingTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_lendingTab.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_lendingTab);
		
		JPanel panelrentstatistics = new JPanel();
		panelrentstatistics.setBorder(new TitledBorder(null, "Rent Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelrentstatistics = new GridBagConstraints();
		gbc_panelrentstatistics.insets = new Insets(0, 0, 5, 0);
		gbc_panelrentstatistics.fill = GridBagConstraints.BOTH;
		gbc_panelrentstatistics.gridx = 0;
		gbc_panelrentstatistics.gridy = 0;
		add(panelrentstatistics, gbc_panelrentstatistics);
		GridBagLayout gbl_panelrentstatistics = new GridBagLayout();
		gbl_panelrentstatistics.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelrentstatistics.rowHeights = new int[]{0, 0};
		gbl_panelrentstatistics.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelrentstatistics.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelrentstatistics.setLayout(gbl_panelrentstatistics);
		
		JLabel lblNumber_of_Rents = new JLabel("Books rented: ");
		GridBagConstraints gbc_lblNumber_of_Rents = new GridBagConstraints();
		gbc_lblNumber_of_Rents.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumber_of_Rents.anchor = GridBagConstraints.EAST;
		gbc_lblNumber_of_Rents.gridx = 0;
		gbc_lblNumber_of_Rents.gridy = 0;
		panelrentstatistics.add(lblNumber_of_Rents, gbc_lblNumber_of_Rents);
		
		display_number_of_rents = new JLabel(library.getLentOutCopies().size()+"");
		GridBagConstraints gbc_display_number_of_rents = new GridBagConstraints();
		gbc_display_number_of_rents.insets = new Insets(0, 0, 0, 5);
		gbc_display_number_of_rents.anchor = GridBagConstraints.WEST;
		gbc_display_number_of_rents.gridx = 1;
		gbc_display_number_of_rents.gridy = 0;
		panelrentstatistics.add(display_number_of_rents, gbc_display_number_of_rents);
		
		JLabel lbl_number_of_lendings = new JLabel("All Loans:");
		GridBagConstraints gbc_lbl_number_of_lendings = new GridBagConstraints();
		gbc_lbl_number_of_lendings.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_number_of_lendings.gridx = 3;
		gbc_lbl_number_of_lendings.gridy = 0;
		panelrentstatistics.add(lbl_number_of_lendings, gbc_lbl_number_of_lendings);
		
		final JLabel display_number_of_lendings = new JLabel(library.getLoans().size()+"");
		GridBagConstraints gbc_display_number_of_lendings = new GridBagConstraints();
		gbc_display_number_of_lendings.insets = new Insets(0, 0, 0, 5);
		gbc_display_number_of_lendings.gridx = 4;
		gbc_display_number_of_lendings.gridy = 0;
		panelrentstatistics.add(display_number_of_lendings, gbc_display_number_of_lendings);
		
		JLabel lblOverdue = new JLabel("Overdue: ");
		GridBagConstraints gbc_lblOverdue = new GridBagConstraints();
		gbc_lblOverdue.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverdue.gridx = 6;
		gbc_lblOverdue.gridy = 0;
		panelrentstatistics.add(lblOverdue, gbc_lblOverdue);
		
		JLabel display_overdue = new JLabel(library.getOverdueLoans().size()+"");
		GridBagConstraints gbc_display_overdue = new GridBagConstraints();
		gbc_display_overdue.gridx = 7;
		gbc_display_overdue.gridy = 0;
		panelrentstatistics.add(display_overdue, gbc_display_overdue);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 6;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane_2);
				
		loan_table = new JTable();
		loan_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(loan_table);
		LendingTableModel lendingTableModel = new LendingTableModel(library.getLoans());
		loan_table.setModel(lendingTableModel);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final TableRowSorter<CustomerTableModel> customerSorter = new TableRowSorter( loan_table.getModel()); 
		loan_table.setRowSorter(customerSorter);
		loan_table.getColumnModel().getColumn(0).setMaxWidth(40);
		loan_table.getColumnModel().getColumn(1).setMaxWidth(40);
		loan_table.getColumnModel().getColumn(3).setMinWidth(90);
		loan_table.getColumnModel().getColumn(3).setMaxWidth(160);
		loan_table.getColumnModel().getColumn(4).setMinWidth(100);
		loan_table.getColumnModel().getColumn(4).setMaxWidth(160);

		lblSearch = new JLabel("Search: ");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		panel.add(lblSearch, gbc_lblSearch);
		
		txtSearchfield = new MySearchField(loan_table,2);
		GridBagConstraints gbc_txtSearchfield = new GridBagConstraints();
		gbc_txtSearchfield.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearchfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchfield.gridx = 2;
		gbc_txtSearchfield.gridy = 1;
		panel.add(txtSearchfield, gbc_txtSearchfield);
		txtSearchfield.setColumns(10);
		
		final JCheckBox chckbxOverdue = new JCheckBox("Only Overdue");
		chckbxOverdue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxOverdue.getSelectedObjects()!=null){ //is selected
				filterKeep("due");
				} else {filterKeep("due|ok");}
			}
		});
		GridBagConstraints gbc_chckbxOverdue = new GridBagConstraints();
		gbc_chckbxOverdue.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxOverdue.gridx = 3;
		gbc_chckbxOverdue.gridy = 1;
		panel.add(chckbxOverdue, gbc_chckbxOverdue);
		
		btnDisplay_selected = new JButton("Display Selected");
		btnDisplay_selected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editLoanWindow=new EditLoan(library.getLoans().get(loan_table.convertRowIndexToModel(loan_table.getSelectedRow())));
				editLoanWindow.setVisible();
				
			}
		});
		GridBagConstraints gbc_btnDisplay_selected = new GridBagConstraints();
		gbc_btnDisplay_selected.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplay_selected.gridx = 4;
		gbc_btnDisplay_selected.gridy = 1;
		panel.add(btnDisplay_selected, gbc_btnDisplay_selected);
		
		JButton btnNew_rent = new JButton("New Rent");
		btnNew_rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnNew_rent = new GridBagConstraints();
		gbc_btnNew_rent.gridx = 5;
		gbc_btnNew_rent.gridy = 1;
		panel.add(btnNew_rent, gbc_btnNew_rent);
		
		filterOutEnded();

	}

	private void filterKeep(String keep) {
		@SuppressWarnings({ "unchecked" })
		TableRowSorter<AbstractTableModel> sorter = (TableRowSorter<AbstractTableModel>) loan_table.getRowSorter();
		loan_table.setRowSorter(sorter);
		RowFilter<AbstractTableModel, Object> rf = RowFilter.regexFilter("(?i)("+ keep +")", 0);
		sorter.setRowFilter(rf);
	}
	private void filterOutEnded(){
		filterKeep("ok|due!");
	}
	
	public void updateFields(){
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateFields();
		
	}

}
