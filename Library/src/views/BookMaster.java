package views;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

import domain.Library;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import components.MySearchField;
import viewModels.TableModel;

public class BookMaster implements Observer{


	private static final Color background_Color = new Color(226, 226, 226);
	private static final int minimum_window_height = 700;
	private static final int minimum_window_witdh = 600;
	private static final String label_NUMBER_OF_TITELS = "Number of Titels: ";
	private static final String borderLabel_INVENTORY_STATISTICS = "Inventory Statistics";
	private static final String TabLabel_LENDING = "Lending";
	private static final String bookTabLabel = "Books";
	private JFrame frame;
	private JTable table;
	private JLabel display_number_of_titles;
	private JLabel display_number_of_books;
	private JLabel lblSelectednumber = new JLabel("0");
	private Library library;
	private BookDetail detailwindow;
	private JTextField txtSearch;
	public BookMaster(Library library) {
		this.library = library;
		initialize();
		library.addObserver(this);
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(minimum_window_witdh, minimum_window_height));
		frame.setBounds(100, 100, 567, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{567, 0};
		gridBagLayout.rowHeights = new int[]{588, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel bookTab = new JPanel();
		tabbedPane.addTab(bookTabLabel, null, bookTab, null);
		GridBagLayout gbl_bookTab = new GridBagLayout();
		gbl_bookTab.columnWidths = new int[]{0, 0};
		gbl_bookTab.rowHeights = new int[]{48, 0, 0};
		gbl_bookTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bookTab.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		bookTab.setLayout(gbl_bookTab);
		
		JPanel panelInventoryStatistik = new JPanel();
		panelInventoryStatistik.setBackground(background_Color);
		panelInventoryStatistik.setBorder(new TitledBorder(null, borderLabel_INVENTORY_STATISTICS, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelLendingStatistik = new GridBagConstraints();
		gbc_panelLendingStatistik.insets = new Insets(0, 0, 5, 0);
		gbc_panelLendingStatistik.fill = GridBagConstraints.BOTH;
		gbc_panelLendingStatistik.gridx = 0;
		gbc_panelLendingStatistik.gridy = 0;
		bookTab.add(panelInventoryStatistik, gbc_panelLendingStatistik);
		GridBagLayout gbl_panelInventoryStatistik = new GridBagLayout();
		gbl_panelInventoryStatistik.columnWidths = new int[]{0, 0, 50, 0, 0, 0};
		gbl_panelInventoryStatistik.rowHeights = new int[]{0, 0};
		gbl_panelInventoryStatistik.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInventoryStatistik.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelInventoryStatistik.setLayout(gbl_panelInventoryStatistik);
		
		JLabel lblNumberOfTitles = new JLabel(label_NUMBER_OF_TITELS);
		GridBagConstraints gbc_lblNumberOfBooks = new GridBagConstraints();
		gbc_lblNumberOfBooks.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberOfBooks.gridx = 0;
		gbc_lblNumberOfBooks.gridy = 0;
		panelInventoryStatistik.add(lblNumberOfTitles, gbc_lblNumberOfBooks);
		
		display_number_of_titles = new JLabel(library.getBooks().size() + "");
		GridBagConstraints gbc_lblNumber_2 = new GridBagConstraints();
		gbc_lblNumber_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumber_2.gridx = 1;
		gbc_lblNumber_2.gridy = 0;
		panelInventoryStatistik.add(display_number_of_titles, gbc_lblNumber_2);
		
		JLabel lblNumberOfBooks_1 = new JLabel("Number of Books");
		GridBagConstraints gbc_lblNumberOfBooks_1 = new GridBagConstraints();
		gbc_lblNumberOfBooks_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberOfBooks_1.gridx = 3;
		gbc_lblNumberOfBooks_1.gridy = 0;
		panelInventoryStatistik.add(lblNumberOfBooks_1, gbc_lblNumberOfBooks_1);
		
		display_number_of_books = new JLabel(library.getCopies().size() + "");
		GridBagConstraints gbc_lblNumber_1 = new GridBagConstraints();
		gbc_lblNumber_1.gridx = 4;
		gbc_lblNumber_1.gridy = 0;
		panelInventoryStatistik.add(display_number_of_books, gbc_lblNumber_1);
		
		JPanel panelBookInventory = new JPanel();
		panelBookInventory.setBackground(background_Color);
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		bookTab.add(panelBookInventory, gbc_panel2);
		GridBagLayout gbl_panelBookInventory = new GridBagLayout();
		gbl_panelBookInventory.columnWidths = new int[]{0, 0, 50, 0, 0, 0};
		gbl_panelBookInventory.rowHeights = new int[]{0, 0, 0};
		gbl_panelBookInventory.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBookInventory.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelBookInventory.setLayout(gbl_panelBookInventory);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelBookInventory.add(scrollPane, gbc_scrollPane);
		
		//---------BookJTable---------------------
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					openDetailWindow();
				}
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent lse) {
		        if (!lse.getValueIsAdjusting()) {
		        		lblSelectednumber.setText(table.getSelectedRows().length+"");
		        }
		    }
		});
		scrollPane.setViewportView(table);
		TableModel tableModel = new TableModel(library.getBooks());
		table.setModel(tableModel);
		JLabel lblSelected = new JLabel("Selected: ");
		GridBagConstraints gbc_lblSelected = new GridBagConstraints();
		gbc_lblSelected.anchor = GridBagConstraints.WEST;
		gbc_lblSelected.insets = new Insets(0, 0, 0, 5);
		gbc_lblSelected.gridx = 0;
		gbc_lblSelected.gridy = 1;
		panelBookInventory.add(lblSelected, gbc_lblSelected);
		//---------JTable---------------------
				
		GridBagConstraints gbc_lblSelectednumber = new GridBagConstraints();
		gbc_lblSelectednumber.anchor = GridBagConstraints.EAST;
		gbc_lblSelectednumber.insets = new Insets(0, 0, 0, 5);
		gbc_lblSelectednumber.gridx = 1;
		gbc_lblSelectednumber.gridy = 1;
		panelBookInventory.add(lblSelectednumber, gbc_lblSelectednumber);
		
		JButton btnDisplaySelected = new JButton("Display Selected");

		btnDisplaySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openDetailWindow();
			}
		});
		//------------Search Field --------------
		txtSearch = new MySearchField(table);

		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 2;
		gbc_txtSearch.gridy = 1;
		panelBookInventory.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		GridBagConstraints gbc_btnDisplaySelected = new GridBagConstraints();
		gbc_btnDisplaySelected.anchor = GridBagConstraints.EAST;
		gbc_btnDisplaySelected.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplaySelected.gridx = 3;
		gbc_btnDisplaySelected.gridy = 1;
		panelBookInventory.add(btnDisplaySelected, gbc_btnDisplaySelected);
		//------------Search Field --------------

		//------------Button Add new Book--------		
		JButton btnAddNewBook = new JButton("Add new Book");
		GridBagConstraints gbc_btnAddNewBook = new GridBagConstraints();
		gbc_btnAddNewBook.gridx = 4;
		gbc_btnAddNewBook.gridy = 1;
		panelBookInventory.add(btnAddNewBook, gbc_btnAddNewBook);
		//------------Button Add new Book--------
		
//-------------Lending Tab---------------
		JPanel lendingTab = new LendingTab(library);
		tabbedPane.addTab(TabLabel_LENDING, null, lendingTab, null);

//-------------Lending Tab---------------
		
//-------------------------Customer Tab ---------------------------------------------------		
		
		CustomerTab customerTab = new CustomerTab(library);
		tabbedPane.addTab("Customers", null, customerTab, null);

//-------------------------Customer Tab-----------------------------------
		
	}
	
	private void openDetailWindow() {
		if (detailwindow == null)
			detailwindow = new BookDetail(library, library.getBooks().get(
					table.convertRowIndexToModel(table.getSelectedRow())));
		detailwindow.setVisible();
		detailwindow.setBook(library.getBooks().get(
				table.convertRowIndexToModel(table.getSelectedRow())));
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		display_number_of_titles.setText(library.getBooks().size() + "");
		display_number_of_books.setText(library.getCopies().size() + "");		
	}
}
