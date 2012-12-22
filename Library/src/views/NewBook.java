package views;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import domain.Book;
import domain.Copy;
import domain.Library;
import domain.Shelf;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import viewModels.CopiesTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import components.MyJTextField;
import components.StateLogicException;
import controll.UnchangedFormState;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewBook extends AbstractStatefullForm implements Observer{
	private String bookTitleLabelText = "Titel";
	private String bookAuthorLabel = "Author";
	private String bookPublisherLabel = "Publisher";
	private String bookShelfLabelText = "Shelf";
	private String windowTitle = "Book Detail";
	private String bookInformationLabelText = "Book Information";
	private String booksInformationLabelText = "Copy";
	private String bookAddButtonText = "Add Copy";
	private String bookRemoveButton = "Remove selected";
	private JFrame frame;
	private MyJTextField txtFieldBookTitle;
	private MyJTextField txtFieldBookAuthor;
	private MyJTextField txtFieldBookPublisher;
	private JComboBox cmbShelfNumber;
	private WarningWindow warningWindow;
	private Book realBook;
	private JTable bookTable;
	private Library library;
	private JPanel copy_panel;
	private JLabel txtNrCopies;
	private JComboBox conditionComboBox;
	private JPanel book_panel;
	private boolean is_saved;
	private JButton btnAddCopy;
	private JButton btnRemoveCopy;

	public static void main(String[] args) {
		Library library = new Library();
		Book b = new Book("");
		NewBook newBookWindow = new NewBook(library,b);
		newBookWindow.setVisible();
		newBookWindow.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public NewBook(Library library, Book book) {
		this.library = library;
		is_saved=false;
		realBook = book;
		realBook.addObserver(this);
		initialize();
		setState(new UnchangedFormState(this));
		disableCopyButtons();
		frame.getRootPane().setDefaultButton(btnSave);
	}

	private void disableCopyButtons() {
		btnRemoveCopy.setEnabled(false);
		btnAddCopy.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame(windowTitle);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				deleteViewObserverFromObject();
			}
		});

		frame.setMinimumSize(new Dimension(460, 290));
		frame.setBounds(100, 100, 650, 511);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 225, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 28, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0 };
		frame.getContentPane().setLayout(gridBagLayout);
		
		book_panel = new JPanel();
		book_panel.setBorder(new TitledBorder(null, bookInformationLabelText,
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		book_panel.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(book_panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 7, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		book_panel.setLayout(gbl_panel);

		JLabel lblIsbn = new JLabel(bookTitleLabelText);
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.anchor = GridBagConstraints.WEST;
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 1;
		gbc_lblIsbn.gridy = 0;
		book_panel.add(lblIsbn, gbc_lblIsbn);

		txtFieldBookTitle = new MyJTextField("Title");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		book_panel.add(txtFieldBookTitle, gbc_textField);
		txtFieldBookTitle.setColumns(10);

		JLabel bookTitleLabel = new JLabel(bookAuthorLabel);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		book_panel.add(bookTitleLabel, gbc_lblTitle);

		txtFieldBookAuthor = new MyJTextField("Author");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		book_panel.add(txtFieldBookAuthor, gbc_textField_1);
		txtFieldBookAuthor.setColumns(10);

		JLabel lblAuthor = new JLabel(bookPublisherLabel);
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthor.gridx = 1;
		gbc_lblAuthor.gridy = 2;
		book_panel.add(lblAuthor, gbc_lblAuthor);

		txtFieldBookPublisher = new MyJTextField("Publisher");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 2;
		book_panel.add(txtFieldBookPublisher, gbc_textField_2);
		txtFieldBookPublisher.setColumns(10);

		JLabel lblPublisher = new JLabel(bookShelfLabelText);
		GridBagConstraints gbc_lblPublisher = new GridBagConstraints();
		gbc_lblPublisher.anchor = GridBagConstraints.WEST;
		gbc_lblPublisher.insets = new Insets(0, 0, 0, 5);
		gbc_lblPublisher.gridx = 1;
		gbc_lblPublisher.gridy = 3;
		book_panel.add(lblPublisher, gbc_lblPublisher);

		cmbShelfNumber = new JComboBox(Shelf.values());
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 3;
		book_panel.add(cmbShelfNumber, gbc_textField_3);
		
		//------------------------- ExemplarAnsicht
		
		copy_panel = new JPanel();
		copy_panel.setBorder(new TitledBorder(null, booksInformationLabelText,
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(copy_panel, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		copy_panel.setLayout(gbl_panel_1);
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		copy_panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 55, 0, 0, 188, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 29, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblAnzahl = new JLabel("Anzahl:");
		GridBagConstraints gbc_lblAnzahl = new GridBagConstraints();
		gbc_lblAnzahl.anchor = GridBagConstraints.EAST;
		gbc_lblAnzahl.insets = new Insets(0, 0, 0, 5);
		gbc_lblAnzahl.gridx = 1;
		gbc_lblAnzahl.gridy = 0;
		panel_2.add(lblAnzahl, gbc_lblAnzahl);
		
		txtNrCopies = new JLabel(library.getCopiesOfBook(realBook).size()+"");
		GridBagConstraints gbc_lblNrCopies = new GridBagConstraints();
		gbc_lblNrCopies.insets = new Insets(0, 0, 0, 5);
		gbc_lblNrCopies.anchor = GridBagConstraints.EAST;
		gbc_lblNrCopies.gridx = 2;
		gbc_lblNrCopies.gridy = 0;
		panel_2.add(txtNrCopies, gbc_lblNrCopies);
		JComboBox comboBox = new JComboBox(Copy.Condition.values());
		comboBox.setEnabled(false);

		conditionComboBox = comboBox;

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 0;
		panel_2.add(conditionComboBox, gbc_comboBox);

		btnRemoveCopy = new JButton(bookRemoveButton);
		btnRemoveCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					library.removeCopy(getSelectedCopy());
					setModel();
				} catch (IndexOutOfBoundsException e1) {
					warningWindow = new WarningWindow("Please select a copy to remove!");
					warningWindow.setVisible();
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 4;
		gbc_button.gridy = 0;
		panel_2.add(btnRemoveCopy, gbc_button);

		btnAddCopy = new JButton(bookAddButtonText);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.gridwidth = 2;
		gbc_button_1.gridx = 5;
		gbc_button_1.gridy = 0;
		panel_2.add(btnAddCopy, gbc_button_1);
		btnAddCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				library.createAndAddCopy(realBook);
				setModel();
			}
		});
		bookTable = new JTable(new CopiesTableModel(library, realBook));
		createtableScrollPane(copy_panel).setViewportView(bookTable);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 2;
		frame.getContentPane().add(panel, gbc_panel1);
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel1.rowHeights = new int[]{29, 0};
		gbl_panel1.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel1);
		
		JButton btnReload = new JButton("Reload");
		this.btnReload=btnReload;
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnReload, gbc_btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableCopyButtons();
			}

			private void enableCopyButtons() {
				btnAddCopy.setEnabled(true);
				btnRemoveCopy.setEnabled(true);
			}
		});
		this.btnSave=btnSave;
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 0;
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		panel.add(btnSave, gbc_btnSave);
		this.btnSave=btnSave;
		btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
		
		addListenertoSavebtn();
		addListenertoReloadbtn();
		addListenertoMyFields();
		addEscapeListener(frame);


	}

	protected Copy getSelectedCopy() {
		return library.getCopies().get(bookTable.convertRowIndexToModel(bookTable.getSelectedRow()));
	}

	private JScrollPane createtableScrollPane(JPanel panel_1) {
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_1.add(scrollPane, gbc_scrollPane);
		return scrollPane;
	}

	public void setVisible() {
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		myState.update(this, realBook);
	}

	private void setModel() {
		bookTable.setModel(new CopiesTableModel(library, realBook));
	}

	public void setBook(Book book) {
		this.realBook = book;
		reloadFieldsfromRealObject();
		setState(new UnchangedFormState(this));
	}

	public JPanel getPanel_1() {
		return copy_panel;
	}

	@Override
	public void reloadFieldsfromRealObject() {
		txtFieldBookAuthor.setText(realBook.getAuthor());
		txtFieldBookPublisher.setText(realBook.getPublisher());
		txtFieldBookTitle.setText(realBook.getName());
		txtNrCopies.setText(library.getCopiesOfBook(realBook).size()+"");
		cmbShelfNumber.setSelectedItem(realBook.getShelf());
		try {
			myState.reloadFieldsfromRealObject(this);
		} catch (StateLogicException e) {
			e.printStackTrace();
		}
		if(!is_saved){for(MyJTextField f : getMyFields(book_panel)){f.reset_to_placeholder();}}
	}

	@Override
	public void saveChangestoRealObject() {
		try {
			realBook.setName(txtFieldBookTitle.getText());
			realBook.setAuthor(txtFieldBookAuthor.getText());
			realBook.setPublisher(txtFieldBookPublisher.getText());
			realBook.setShelf((Shelf)cmbShelfNumber.getSelectedItem());
			if(!is_saved){
				library.addBook(realBook);
				setState(new UnchangedFormState(this));
				is_saved=true;
			} else {myState.saveChangestoRealObject(this);}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void addListenertoMyFields() {
		addListenertoMyFields(book_panel,this);
	}
	@Override
	protected void deleteViewObserverFromObject() {
		realBook.deleteObserver(this);
	}
}
