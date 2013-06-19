package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import localization.Messages;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;

import domain.Book;
import domain.Library;
import domain.Shelf;

public class BookEditor extends AbstractEditor {

	private final JTextField title = new JTextField();
	private final JTextField author = new JTextField();
	private final JTextField publisher = new JTextField();
	private Book book;
	private final JComboBox<Shelf> shelf = new JComboBox<Shelf>(Shelf.values());
	private boolean newBook = false;
	private Library library;

	public BookEditor(Library library, Book book, Component p) {
		super(p);
		this.book = book;
		this.library = library;
		this.title.setText(book.getName());
		this.author.setText(book.getAuthor());
		this.publisher.setText(book.getPublisher());
		this.shelf.setSelectedItem(book.getShelf());
		d.setTitle(Messages.getString("CreateNewBook.title"));

		createPanel();

	}

	public BookEditor(Library library, Component p) {
		super(p);
		this.book = new Book("");
		newBook = true;
		this.library = library;
		d.setTitle(Messages.getString("EditBook.title"));

		createPanel();

	}

	public void createPanel() {
		DefaultFormBuilder builder = builder();
		CellConstraints cc = new CellConstraints();
		builder.add(
				new JLabel(Messages
						.getString("BookEditor.lblBookTitleText.title")), cc
						.xy(2, 2));
		builder.add(title, cc.xyw(4, 2, 3));
		builder.add(
				new JLabel(Messages
						.getString("BookEditor.lblBookAuthorText.title")), cc
						.xy(2, 4));
		builder.add(author, cc.xyw(4, 4, 3));
		builder.add(
				new JLabel(Messages
						.getString("BookEditor.lblBookPublisherText.title")),
				cc.xy(2, 6));
		builder.add(publisher, cc.xyw(4, 6, 3));
		builder.add(
				new JLabel(Messages
						.getString("BookEditor.lblBookShelfText.title")), cc
						.xy(2, 8));

		builder.add(shelf, cc.xyw(4, 8, 3));
		builder.add(buttonPanel(), cc.xyw(6, 9, 1));
		d.getContentPane().add(builder.getPanel());
		d.setPreferredSize(builder.getPanel().getPreferredSize());
		d.setMinimumSize(new Dimension(400, 200));
		d.setVisible(true);

	}

	@Override
	public void validate() {
		checkTxtField(title);
		checkTxtField(author);
		checkTxtField(publisher);
		if (shelf.getSelectedIndex() < 0) {
			shelf.setBackground(new Color(754909184, true));
			shelf.getEditor().getEditorComponent()
					.setBackground(new Color(754909184, true));

			validationResult.addError("Field must not be empty");
		} else {
			shelf.setBackground(new Color(255, 255, 255, 255));
		}

	}

	@Override
	protected void saveTask() {
		saveToBook();
		if (newBook)
			library.addBook(book);
		d.dispose();
	}

	@Override
	protected void cancelTask() {
		book.setInvalid();
		d.dispose();

	}

	private void saveToBook() {
		book.setAuthor(author.getText());
		book.setName(title.getText());
		book.setPublisher(publisher.getText());
		book.setShelf((Shelf) shelf.getSelectedItem());
	}
}
