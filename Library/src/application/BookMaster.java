package application;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

import domain.Library;
import javax.swing.JTable;
import javax.swing.JScrollPane;

	private Library library;
	private static final String label_NUMBER_OF_TITELS = "Number of Titels: ";
	private static final String borderLabel_INVENTORY_STATISTICS = "Inventory Statistics";
	private static final String TabLabel_LENDING = "Lending";
	private static final String bookTabLabel = "Books";
	private JFrame frame;
	private JTable table;
	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookMaster window = new BookMaster();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public BookMaster(Library library) {
		this.library = library;
		initialize();
		frame.setVisible(true);
		
	}

	public void listBooks(){
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		panelInventoryStatistik.setBorder(new TitledBorder(null, borderLabel_INVENTORY_STATISTICS, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		bookTab.add(panelInventoryStatistik, gbc_panel);
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
		
		JLabel lblvarNumberOfTitles = new JLabel(library.getBooks().size() + "");
		GridBagConstraints gbc_lblNumber_2 = new GridBagConstraints();
		gbc_lblNumber_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumber_2.gridx = 1;
		gbc_lblNumber_2.gridy = 0;
		panelInventoryStatistik.add(lblvarNumberOfTitles, gbc_lblNumber_2);
		
		JLabel lblNumberOfBooks_1 = new JLabel("Number of Books");
		GridBagConstraints gbc_lblNumberOfBooks_1 = new GridBagConstraints();
		gbc_lblNumberOfBooks_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberOfBooks_1.gridx = 3;
		gbc_lblNumberOfBooks_1.gridy = 0;
		panelInventoryStatistik.add(lblNumberOfBooks_1, gbc_lblNumberOfBooks_1);
		
		JLabel lblNumber_1 = new JLabel(library.getCopies().size() + "");
		GridBagConstraints gbc_lblNumber_1 = new GridBagConstraints();
		gbc_lblNumber_1.gridx = 4;
		gbc_lblNumber_1.gridy = 0;
		panelInventoryStatistik.add(lblNumber_1, gbc_lblNumber_1);
		
		JPanel panelBookInventory = new JPanel();
		panelBookInventory.setBorder(new TitledBorder(null, "Book Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		bookTab.add(panelBookInventory, gbc_panel2);
		GridBagLayout gbl_panelBookInventory = new GridBagLayout();
		gbl_panelBookInventory.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelBookInventory.rowHeights = new int[]{0, 0, 0};
		gbl_panelBookInventory.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBookInventory.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelBookInventory.setLayout(gbl_panelBookInventory);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelBookInventory.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSelected = new JLabel("Selected: ");
		GridBagConstraints gbc_lblSelected = new GridBagConstraints();
		gbc_lblSelected.anchor = GridBagConstraints.WEST;
		gbc_lblSelected.insets = new Insets(0, 0, 0, 5);
		gbc_lblSelected.gridx = 0;
		gbc_lblSelected.gridy = 1;
		panelBookInventory.add(lblSelected, gbc_lblSelected);
		
		JLabel lblSelectednumber = new JLabel("selectedNumber");
		GridBagConstraints gbc_lblSelectednumber = new GridBagConstraints();
		gbc_lblSelectednumber.anchor = GridBagConstraints.WEST;
		gbc_lblSelectednumber.insets = new Insets(0, 0, 0, 5);
		gbc_lblSelectednumber.gridx = 1;
		gbc_lblSelectednumber.gridy = 1;
		panelBookInventory.add(lblSelectednumber, gbc_lblSelectednumber);
		
		JButton btnDisplaySelected = new JButton("Display Selected");
		GridBagConstraints gbc_btnDisplaySelected = new GridBagConstraints();
		gbc_btnDisplaySelected.anchor = GridBagConstraints.EAST;
		gbc_btnDisplaySelected.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplaySelected.gridx = 2;
		gbc_btnDisplaySelected.gridy = 1;
		panelBookInventory.add(btnDisplaySelected, gbc_btnDisplaySelected);
		
		JButton btnAddNewBook = new JButton("Add new Book");
		GridBagConstraints gbc_btnAddNewBook = new GridBagConstraints();
		gbc_btnAddNewBook.gridx = 3;
		gbc_btnAddNewBook.gridy = 1;
		panelBookInventory.add(btnAddNewBook, gbc_btnAddNewBook);
		
		JPanel lendingTab = new JPanel();
		tabbedPane.addTab(TabLabel_LENDING, null, lendingTab, null);
	}

}
