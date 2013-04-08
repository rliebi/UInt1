package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;


import components.MySearchField;
import viewModels.BookTableModel;
import domain.Book;
import domain.Library;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class BookTab extends JPanel implements Observer{
	private static final long serialVersionUID = 6736293912240376284L;
	private static final Color background_Color = new Color(226, 226, 226);
	private Library library;
	private WarningWindow warningWindow;
	private static final String label_NUMBER_OF_TITELS = "Number of Titels: ";
	private static final String borderLabel_INVENTORY_STATISTICS = "Inventory Statistics";
	private JTable book_table;
	private JLabel display_number_of_titles;
	private JLabel display_number_of_books;
	private JTextField txtSearch;
	private EditBook detailwindow;
	private NewBook newBookDetailWindow;
	private NewLoan newLoanWindow;
	public BookTab(){
		super();
		this.library = new Library();
		initialize();
	}
	
	public BookTab(Library library){
		super();
		this.library = library;
		library.addObserver(this);
		initialize();
	}
	
	private void initialize (){
		GridBagLayout gbl_bookTab = new GridBagLayout();
		gbl_bookTab.columnWidths = new int[]{0, 0};
		gbl_bookTab.rowHeights = new int[]{48, 0, 0};
		gbl_bookTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bookTab.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_bookTab);
		
		JPanel panelInventoryStatistik = new JPanel();
		panelInventoryStatistik.setBackground(background_Color);
		panelInventoryStatistik.setBorder(new TitledBorder(null, borderLabel_INVENTORY_STATISTICS, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelLendingStatistik = new GridBagConstraints();
		gbc_panelLendingStatistik.insets = new Insets(0, 0, 5, 0);
		gbc_panelLendingStatistik.fill = GridBagConstraints.BOTH;
		gbc_panelLendingStatistik.gridx = 0;
		gbc_panelLendingStatistik.gridy = 0;
		add(panelInventoryStatistik, gbc_panelLendingStatistik);
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
		add(panelBookInventory, gbc_panel2);
		GridBagLayout gbl_panelBookInventory = new GridBagLayout();
		gbl_panelBookInventory.columnWidths = new int[]{0, 50, 200, 0, 0};
		gbl_panelBookInventory.rowHeights = new int[]{0, 0, 0};
		gbl_panelBookInventory.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		//---------BookJTable---------------------
		book_table = new JTable();
		book_table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					arg0.consume();
					openEditBookWindow();
				}
			}
		});
		book_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					openNewLoanWindow(getSelectedBook());
					
				}
			}

			private void openNewLoanWindow(Book book) {
				newLoanWindow = new NewLoan(library,book);
				newLoanWindow.setVisible(true);
			}
		});
		book_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(book_table);
		BookTableModel tableModel = new BookTableModel(library);
		setModel(tableModel);
		JLabel lblSearch = new JLabel("Search: ");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.WEST;
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		panelBookInventory.add(lblSearch, gbc_lblSearch);
		
		JButton btnDisplayBook = new JButton("Edit Book");
		btnDisplayBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					openEditBookWindow();
				} catch (IndexOutOfBoundsException e) {
					warningWindow = new WarningWindow("Please Select a Book");
					warningWindow.setVisible();
				}
			}
		});
		//------------Search Field --------------
		txtSearch = new MySearchField(book_table);
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 1;
		panelBookInventory.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		GridBagConstraints gbc_btnDisplaySelected = new GridBagConstraints();
		gbc_btnDisplaySelected.anchor = GridBagConstraints.EAST;
		gbc_btnDisplaySelected.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplaySelected.gridx = 2;
		gbc_btnDisplaySelected.gridy = 1;
		panelBookInventory.add(btnDisplayBook, gbc_btnDisplaySelected);
		//------------Search Field --------------

		//------------Button Add new Book--------		
		JButton btnAddNewBook = new JButton("Add new Book");
		GridBagConstraints gbc_btnAddNewBook = new GridBagConstraints();
		gbc_btnAddNewBook.gridx = 3;
		gbc_btnAddNewBook.gridy = 1;
		panelBookInventory.add(btnAddNewBook, gbc_btnAddNewBook);
		btnAddNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newBookDetailWindow = new NewBook(library,new Book(""));
				newBookDetailWindow.setVisible();
			}
		});
		//------------Button Add new Book--------
	}
	
	public void updateFields(){
		display_number_of_titles.setText(library.getBooks().size() + "");
		display_number_of_books.setText(library.getCopies().size() + "");
	}
	
	
	private Book getSelectedBook() {
		return library.getBooks().get(book_table.convertRowIndexToModel(book_table.getSelectedRow()));
	}

	private void setModel(BookTableModel model) {
		book_table.setModel(model);
		book_table.getColumnModel().getColumn(0).setMaxWidth(60);

		book_table.getColumnModel().getColumn(2).setMaxWidth(40);
			
	}

	private void openEditBookWindow() {
		detailwindow = new EditBook(library, getSelectedBook());
		detailwindow.setVisible();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		updateFields();
		BookTableModel model = new BookTableModel(library.getBooks());
		setModel(model);
	}

}
