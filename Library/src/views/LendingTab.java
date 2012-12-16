package views;

import java.awt.Color;
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
import javax.swing.border.TitledBorder;
import viewModels.LendingTableModel;

import components.MySearchField;

import domain.Library;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LendingTab extends JPanel implements Observer{
	private static final long serialVersionUID = 6034035113335278353L;
	private static final Color background_Color = new Color(226, 226, 226);
	private Library library;
	private JTextField txtSearchfield;
	private JTable lending_table;
	private JButton btnDisplayLoan;
	private EditLoan editLoanWindow;
	private JLabel display_number_of_rents;
	private JLabel lblSearch;
	private WarningWindow warningWindow;
	private LendingTableModel lendingTableModel;
	private JCheckBox chckbxOverdue;
	private JLabel display_number_of_lendings;
	private JLabel display_overdue;
	
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
		panelrentstatistics.setBackground(background_Color);
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
		
		display_number_of_lendings = new JLabel(library.getLoans().size()+"");
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
		
		display_overdue = new JLabel(library.getOverdueLoans().size()+"");
		GridBagConstraints gbc_display_overdue = new GridBagConstraints();
		gbc_display_overdue.gridx = 7;
		gbc_display_overdue.gridy = 0;
		panelrentstatistics.add(display_overdue, gbc_display_overdue);
		
		JPanel panel = new JPanel();
		panel.setBackground(background_Color);
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
				
		lending_table = new JTable();
		lending_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lending_table);
		lendingTableModel = new LendingTableModel(library.getOngoingLoans());
		setLendingModel(lendingTableModel);

		lblSearch = new JLabel("Search: ");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		panel.add(lblSearch, gbc_lblSearch);
		
		//TODO nicer solution for search with overdue
		txtSearchfield = new MySearchField(lending_table,2);
		txtSearchfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				chckbxOverdue.setSelected(false);
			}
		});
		GridBagConstraints gbc_txtSearchfield = new GridBagConstraints();
		gbc_txtSearchfield.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearchfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchfield.gridx = 2;
		gbc_txtSearchfield.gridy = 1;
		panel.add(txtSearchfield, gbc_txtSearchfield);
		txtSearchfield.setColumns(10);
		
		chckbxOverdue = new JCheckBox("Only Overdue");
		chckbxOverdue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxOverdue.getSelectedObjects()!=null){ //is selected
					lending_table.setModel(new LendingTableModel(library.getOverdueLoans()));
				} else {
					LendingTableModel lendingTableModel = new LendingTableModel(library.getOngoingLoans());
					setLendingModel(lendingTableModel);
				}
			}
		});
		GridBagConstraints gbc_chckbxOverdue = new GridBagConstraints();
		gbc_chckbxOverdue.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxOverdue.gridx = 3;
		gbc_chckbxOverdue.gridy = 1;
		panel.add(chckbxOverdue, gbc_chckbxOverdue);
		
		btnDisplayLoan = new JButton("Display Loan");
		btnDisplayLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					editLoanWindow=new EditLoan(library.getOngoingLoans().get(lending_table.convertRowIndexToModel(lending_table.getSelectedRow())));
					editLoanWindow.setVisible();
				} catch (IndexOutOfBoundsException e) {
					warningWindow = new WarningWindow("Please select a Loan!");
					warningWindow.setVisible();
				}
				
			}
		});
		GridBagConstraints gbc_btnDisplay_selected = new GridBagConstraints();
		gbc_btnDisplay_selected.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplay_selected.gridx = 4;
		gbc_btnDisplay_selected.gridy = 1;
		panel.add(btnDisplayLoan, gbc_btnDisplay_selected);
		
		JButton btnNew_rent = new JButton("New Rent");
		btnNew_rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				warningWindow= new WarningWindow("Not yet Implemented!");
				warningWindow.setVisible();
			}
		});
		GridBagConstraints gbc_btnNew_rent = new GridBagConstraints();
		gbc_btnNew_rent.gridx = 5;
		gbc_btnNew_rent.gridy = 1;
		panel.add(btnNew_rent, gbc_btnNew_rent);


	}

	private void setLendingModel(LendingTableModel model) {
		lending_table.setModel(model);
		lending_table.getColumnModel().getColumn(0).setMaxWidth(40);
		lending_table.getColumnModel().getColumn(1).setMaxWidth(40);
		lending_table.getColumnModel().getColumn(3).setMinWidth(90);
		lending_table.getColumnModel().getColumn(3).setMaxWidth(160);
		lending_table.getColumnModel().getColumn(4).setMinWidth(100);
		lending_table.getColumnModel().getColumn(4).setMaxWidth(160);	
	}

	
	public void updateFields(){
		display_number_of_rents.setText(library.getLentOutCopies().size()+"");
		display_number_of_lendings.setText(library.getLoans().size()+"");
		display_overdue.setText(library.getOverdueLoans().size()+"");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateFields();
		LendingTableModel lendingTableModel = new LendingTableModel(library.getOngoingLoans());
		setLendingModel(lendingTableModel);
	}

}
