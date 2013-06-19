package viewPanels;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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


import components.IconCellRenderer;
import components.LibraryExcption;
import components.MySearchField;
import settings.Icons;
import viewModels.BookTableModel;
import views.BookViewer;
import views.WarningWindow;
import domain.Book;
import domain.Library;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import localization.Messages;


public class BookPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 6736293912240376284L;
	private static final Color background_Color = new Color(226, 226, 226);
	private Library library;
	private WarningWindow warningWindow;
	private JTable book_table;
	private JLabel display_number_of_titles;
	private JLabel display_number_of_books;
	private JTextField txtSearch;
    private java.util.List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(3);
	private JButton btnDisplayBook;  
	public BookPanel(){
		super();
		this.library = new Library();
		initialize();
	}
	
	public BookPanel(Library library){
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
		panelInventoryStatistik.setBorder(new TitledBorder(null, Messages.getString("BookPanel.InventoryStatistics.Title"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JLabel lblNumberOfTitles = new JLabel(Messages.getString("BookPanel.InventoryStatistics.Count"));
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
		
		JLabel lblNumberOfBooks_1 = new JLabel(Messages.getString("BookPanel.InventoryStatistics.CopyCount"));
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
				if (book_table.getSelectedRowCount()==1)
					btnDisplayBook.setEnabled(true);
				else
					btnDisplayBook.setEnabled(false);
			}
		});
		book_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					openNewLoanWindow(getSelectedBook());
					
				}
				if (book_table.getSelectedRowCount()==1)
					btnDisplayBook.setEnabled(true);
				else
					btnDisplayBook.setEnabled(false);
			}

			private void openNewLoanWindow(Book book) {
				openEditBookWindow();
			}
		});
		book_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(book_table);
		BookTableModel tableModel = new BookTableModel(library);
		setModel(tableModel);
		
		btnDisplayBook = new JButton(Messages.getString("BooksPanel.DetailButton.title"));
		btnDisplayBook.setIcon(Icons.IconEnum.EDITBOOK.getIcon(24));
		btnDisplayBook.setEnabled(false);
		btnDisplayBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					openEditBookWindow();
				} catch (IndexOutOfBoundsException e) {
					warningWindow = new WarningWindow(Messages.getString("BooksPanel.WarningWindow.NoBookSelected.Message"));
					warningWindow.setVisible();
				}
			}
		});
		//------------Search Field --------------
		txtSearch = new MySearchField(book_table,0,filters);
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
		JButton btnAddNewBook = new JButton(Messages.getString("BooksPanel.AddButton.title"));
		btnAddNewBook.setIcon(Icons.IconEnum.ADD.getIcon(24));
		GridBagConstraints gbc_btnAddNewBook = new GridBagConstraints();
		gbc_btnAddNewBook.gridx = 3;
		gbc_btnAddNewBook.gridy = 1;
		panelBookInventory.add(btnAddNewBook, gbc_btnAddNewBook);
		btnAddNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openNewBookWindow();
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
		book_table.getColumnModel().getColumn(0).setMaxWidth(200);
		book_table.getColumnModel().getColumn(0).setPreferredWidth(120);
		book_table.getColumnModel().getColumn(0).setMinWidth(20);
		book_table.getColumnModel().getColumn(0).setCellRenderer(new IconCellRenderer());

//		book_table.getColumnModel().getColumn(1).setMaxWidth(200);
//		book_table.getColumnModel().getColumn(1).setPreferredWidth(120);
		book_table.getColumnModel().getColumn(1).setMinWidth(120);
		
		book_table.getColumnModel().getColumn(2).setMaxWidth(250);
		book_table.getColumnModel().getColumn(2).setPreferredWidth(120);
		book_table.getColumnModel().getColumn(2).setMinWidth(120);
		
		book_table.getColumnModel().getColumn(3).setMaxWidth(200);
		book_table.getColumnModel().getColumn(3).setPreferredWidth(120);
		book_table.getColumnModel().getColumn(3).setMinWidth(120);
	}
	
	private void openNewBookWindow() {
		try {
			new BookViewer(library);
		} catch (LibraryExcption e) {
			
		}
		
	}
	
	private void openEditBookWindow() {
		try {
			new BookViewer(library, getSelectedBook());
		} catch (LibraryExcption e) {
			// TODO Auto-generated catch block
			
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateFields();
	}

}
