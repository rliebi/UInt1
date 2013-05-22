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

public class BookEditor extends AbstractEditor{


	private final JTextField title = new JTextField();
	private final JTextField author = new JTextField();
	private final JTextField publisher = new JTextField();
	private Book book;
	private final JComboBox<Shelf> shelf = new JComboBox<Shelf>(Shelf.values());
	private boolean newBook = false;
	private Library library;
	public BookEditor(Library library, Book book,Component p) {
		super(p);
		this.book = book;
		this.library = library;
		this.title.setText(book.getName());
		this.author.setText(book.getAuthor());
		this.publisher.setText(book.getPublisher());
		this.shelf.setSelectedItem(book.getShelf());
		d.setTitle(Messages.getString("CreateNewBookTitle.title"));

		createPanel();

	}
	public BookEditor(Library library,Component p) {
		super(p);
		this.book = new Book("");
		newBook = true;
		this.library = library;
		d.setTitle(Messages.getString("EditBookTitle.title"));

		createPanel();

	}
	
	public void createPanel(){
		DefaultFormBuilder builder = builder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel(Messages.getString("BooksAddView.lblBookTitleText.title")),     cc.xy (2, 2));
        builder.add(title, cc.xyw(4,2,3));
        builder.add(new JLabel(Messages.getString("BooksAddView.lblBookAuthorText.title")),    cc.xy (2, 4));
        builder.add(author,        cc.xyw(4, 4, 3));  
        builder.add(new JLabel(Messages.getString("BooksAddView.lblBookPublisherText.title")), cc.xy (2, 6));
        builder.add(publisher,        cc.xyw (4, 6,3));
        builder.add(new JLabel(Messages.getString("BooksAddView.lblBookShelfText.title")), cc.xy (2, 8));

        builder.add(shelf,cc.xyw(4, 8, 3));
		builder.add(buttonPanel(),cc.xyw(6, 9, 1));
		d.getContentPane().add(builder.getPanel());
		d.setPreferredSize(builder.getPanel().getPreferredSize());
		d.setMinimumSize(new Dimension(400,200));
		d.setModal(true);
		d.setVisible(true);

	}
	@Override
	public void validate() {
		checkTxtField(title);
		checkTxtField(author);
		checkTxtField(publisher);
		if (shelf.getSelectedIndex() < 0){
			shelf.setBackground(new Color(754909184,true));
			shelf.getEditor().getEditorComponent().setBackground(new Color(754909184,true));
			
			validationResult.addError("Field must not be empty");
		}else{
			shelf.setBackground(new Color(255, 255, 255,255));
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
//	private final JFrame frame = new JFrame("Book");
//	public final JDialog dialog = new JDialog();
//	private final JTextField title = new JTextField(30);
//	private final JTextField author = new JTextField(30);
//	private final JTextField publisher = new JTextField(30);
//	private Book book;
//	private final JComboBox<Shelf> shelf = new JComboBox<Shelf>(Shelf.values());
//	private final ValidationResultModel validationResultModel = new DefaultValidationResultModel();
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -7539085064049707218L;
//	private ValidationResult validationResult;
//
//	public BookEditor() {
//		validationResult = new ValidationResult();
//
//		this.validationResultModel
//				.addPropertyChangeListener(new ValidationListener());
//		this.dialog.add(createPanel());
////		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.dialog.pack();
//		this.dialog.setModal(true);
//		
//	}
//	public BookEditor(Book book){
//		this();
//		this.book = book;
//		this.title.setText(book.getName());
//		this.author.setText(book.getAuthor());
//		this.publisher.setText(book.getPublisher());
//		this.shelf.setSelectedItem(book.getShelf());
//		
//	}
//	// validate each of the three input fields
//	public void validate() {
//		validationResult = new ValidationResult();
//		// validate the field
//		if (!ValidationUtils.hasMinimumLength(this.title.getText(),1)) {
//			validationResult.addError("The Title field can not be blank.");
//		}
//		// validate the username field
//		if (!ValidationUtils.hasMinimumLength(this.author.getText(),1)) {
//			validationResult.addError("The Author field can not be blank.");
//		}
//
//		// validate the phoneNumber field
//		if (!ValidationUtils.hasMinimumLength(this.publisher.getText(),1)) {
//			validationResult
//					.addError("The Publisher field can not be blank.");
//		}
//		if (shelf.getSelectedIndex() < 0){
//			validationResult
//			.addError("The Shelf field can not be blank.");
//		}
//	}
//
//	private JPanel createPanel() {
//		FormLayout layout = new FormLayout("pref, 4dlu, pref:grow");
//		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
//		int columnCount = builder.getColumnCount();
////		 builder.setDefaultDialogBorder();
//		builder.border(getBorder());
//		builder.append("Titel", this.title);
//		builder.append("Author", this.author);
//		builder.append("Publisher", this.publisher);
//		builder.append("Shelf", this.shelf);
//
//		ButtonBarBuilder buttonBar = new ButtonBarBuilder();
//
//		buttonBar.addButton(new OkAction(), new CancelAction());
//		// .buildOKCancelBar(new JButton(
//		// new OkAction()), new JButton(new CancelAction()));
//		builder.append(buttonBar.build(), columnCount);
//		JComponent validationResultsComponent =
//                ValidationResultViewFactory.createReportList(
//                        this.validationResultModel);
//        builder.appendUnrelatedComponentsGapRow();
//        builder.appendRow("fill:50dlu:grow");
//        builder.nextLine(2);
//        builder.append(validationResultsComponent, columnCount);
//		return builder.getPanel();
//	}
//
//	public void show() {
//		this.dialog.setVisible(true);
//		this.dialog.getParent().setEnabled(false);
//	}
//
//	private final class OkAction extends AbstractAction {
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//
//		private OkAction() {
//			super("Ok");
//		}
//
//		public void actionPerformed(ActionEvent e) {
//            //don't close the frame on OK unless it validates    
//			validate();
//            validationResultModel.setResult(validationResult);
//            if (!validationResultModel.hasErrors()) {
//            		saveToBook();
//                dialog.dispose();
//            }
//		}
//
//		private void saveToBook() {
//			book.setAuthor(author.getText());
//			book.setName(title.getText());
//			book.setPublisher(publisher.getText());
//			book.setShelf((Shelf) shelf.getSelectedItem());
//		}
//	}
//	
//	public BookEditor(LayoutManager layout) {
//		super(layout);
//		// TODO Auto-generated constructor stub
//	}
//
//	private final class CancelAction extends AbstractAction {
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//
//		private CancelAction() {
//			super("Cancel");
//		}
//
//		public void actionPerformed(ActionEvent e) {
//			book.setInvalid();
//			dialog.dispose();
//		}
//	}
//
//	public BookEditor(boolean isDoubleBuffered) {
//		super(isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
//
//	public BookEditor(LayoutManager layout, boolean isDoubleBuffered) {
//		super(layout, isDoubleBuffered);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void update(Observable o, Object arg) {
//		// TODO Auto-generated method stub
//
//	}
}
