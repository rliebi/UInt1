package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import domain.Book;
import domain.Library;
import javax.swing.SwingConstants;

import localization.Messages;

import settings.Icons;
import viewPanels.CopyPanel;

import components.LibraryExcption;

public class BookViewer extends AbstractViewer implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4308088491085944879L;
	private JLabel bookPublisher;
	private JLabel bookAuthor;
	private JLabel bookTitle;
	private JLabel bookShelf;
	private Book book;
	private Library library;
	private CopyPanel copyPanel;

	/**
	 * @throws LibraryExcption 
	 */
	public BookViewer(Library lib) throws LibraryExcption {
		this.book = new Book("");
		this.library = lib;
		book.addObserver(this);
		init();
		this.bookPublisher.setText("");
		this.bookAuthor.setText("");
		this.bookTitle.setText("");
		this.bookShelf.setText("");
		BookEditor();
		
		if (book.isInvalid()){
			dispose();
			throw new LibraryExcption();
		}

		else{
			createWindow();
			this.setTitle("Edit Book");
			library.addBook(book);

		}
	}

	/**
	 * @throws LibraryExcption 
	 * @wbp.parser.constructor
	 */
	public BookViewer(Library lib, Book b) throws LibraryExcption {

		this.book = b;
		this.library = lib;
		book.addObserver(this);
		init();
		updateBookLabels();
		createWindow();
		if (book.isInvalid()){
			dispose();
			throw new LibraryExcption();
		}
	}

	/**
	 * 
	 */


	private void updateBookLabels() {
		this.bookPublisher.setText(book.getPublisher());
		this.bookAuthor.setText(book.getAuthor());
		this.bookTitle.setText(book.getName());
		try {
			this.bookShelf.setText(book.getShelf().toString());
		} catch (NullPointerException e) {

		}
	}

	private void init() {
		setTitle(Messages.getString("BookViewer.title"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 133, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, Messages.getString("BookViewer.BookInformation.Title"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 91, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_2.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		bookTitle = new JLabel();
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(bookTitle, gbc_lblNewLabel);

		bookAuthor = new JLabel();
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;

		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(bookAuthor, gbc_lblNewLabel_1);

		bookPublisher = new JLabel();
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;

		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(bookPublisher, gbc_lblNewLabel_2);

		bookShelf = new JLabel();
		bookShelf.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		panel.add(bookShelf, gbc_lblNewLabel_3);

		JButton editBookButton = new JButton(Messages.getString("BookViewer.btnBookEdit.text"),Icons.IconEnum.EDITBOOK.getIcon());
		editBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookEditor();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		panel.add(editBookButton, gbc_btnNewButton);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{175, 0};
		gbl_panel_1.rowHeights = new int[]{16, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		
		copyPanel = new CopyPanel(library,book,this);
		GridBagConstraints gbc_copyPanel = new GridBagConstraints();
		gbc_copyPanel.fill = GridBagConstraints.BOTH;
		panel_1.add(copyPanel, gbc_copyPanel);


	
	}

	public void BookEditor() {
		new BookEditor(library,book,this);

	}

	@Override
	public void update(Observable o, Object arg) {
		updateBookLabels();

	}

}
