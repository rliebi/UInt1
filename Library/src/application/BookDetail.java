package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JList;

import domain.Book;

public class BookDetail implements Observer{
	private String bookTitleLabelText = "Titel+";
	private String bookAuthorLabel = "Author+";
	private String bookPublisherLabel = "Verlag+";
	private String bookShelfLabelText = "Regal+";
	private String windowTitle = "Buch Detailansicht";
	private String bookInformationLabelText = "Buch Informationen";
	private String booksInformationLabelText = "Exemplare";
	private String bookCountLabel = "Anzahl";
	private String bookAddButtonText = "Exemplar hinzufügen";
	private String bookRemoveButton = "Ausgewählte Entfernen";
	private JFrame frame;
	private JTextField txtFieldBookTitle;
	private JTextField txtFieldBookAuthor;
	private JTextField txtFieldBookPublisher;
	private JTextField txtFieldShelfNumber;
//	static private Book book = new Book();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetail window = new BookDetail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookDetail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame(windowTitle);
		frame.setMinimumSize(new Dimension(460, 290));
		frame.setBounds(100, 100, 650, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{225,0};
		gridBagLayout.rowHeights = new int[]{0,50};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0 };
		frame.getContentPane().setLayout(gridBagLayout);
//		book.addObserver(this);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, bookInformationLabelText, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{7, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblIsbn = new JLabel(bookTitleLabelText);
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.anchor = GridBagConstraints.WEST;
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 1;
		gbc_lblIsbn.gridy = 0;
		panel.add(lblIsbn, gbc_lblIsbn);
		
		txtFieldBookTitle = new JTextField();
		txtFieldBookTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//				book.setISBN(txtFieldBookTitle.getText());
			}
		});
		
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 0);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 3;
				gbc_textField.gridy = 0;
				panel.add(txtFieldBookTitle, gbc_textField);
				txtFieldBookTitle.setColumns(10);
		
		JLabel bookTitleLabel = new JLabel(bookAuthorLabel);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		panel.add(bookTitleLabel, gbc_lblTitle);
		
		txtFieldBookAuthor = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		panel.add(txtFieldBookAuthor, gbc_textField_1);
		txtFieldBookAuthor.setColumns(10);
		
		JLabel lblAuthor = new JLabel(bookPublisherLabel);
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthor.gridx = 1;
		gbc_lblAuthor.gridy = 2;
		panel.add(lblAuthor, gbc_lblAuthor);
		
		txtFieldBookPublisher = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 2;
		panel.add(txtFieldBookPublisher, gbc_textField_2);
		txtFieldBookPublisher.setColumns(10);
		
		JLabel lblPublisher = new JLabel(bookShelfLabelText);
		GridBagConstraints gbc_lblPublisher = new GridBagConstraints();
		gbc_lblPublisher.anchor = GridBagConstraints.WEST;
		gbc_lblPublisher.insets = new Insets(0, 0, 0, 5);
		gbc_lblPublisher.gridx = 1;
		gbc_lblPublisher.gridy = 3;
		panel.add(lblPublisher, gbc_lblPublisher);
		
		txtFieldShelfNumber = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 3;
		panel.add(txtFieldShelfNumber, gbc_textField_3);
		txtFieldShelfNumber.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, booksInformationLabelText, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 55, 0, 188, 0, 0};
		gbl_panel_2.rowHeights = new int[]{29, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label = new JLabel("Anzahl 8");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		JButton button = new JButton(bookRemoveButton);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 0;
		panel_2.add(button, gbc_button);
		
		JButton button_1 = new JButton(bookAddButtonText);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.gridwidth = 2;
		gbc_button_1.gridx = 4;
		gbc_button_1.gridy = 0;
		panel_2.add(button_1, gbc_button_1);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_1.add(list, gbc_list);
	}

	@Override
	public void update(Observable o, Object arg) {
//		txtFieldBookTitle.setText(book.getISBN());
//		txtFieldBookAuthor.setText(book.getAuthor());
	}

}
