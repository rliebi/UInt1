package views;

import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationResultViewFactory;

import domain.Book;
import domain.Shelf;

public class BookEditor extends JPanel implements Observer {
//	private final JFrame frame = new JFrame("Book");
	public final JDialog dialog = new JDialog();
	private final JTextField title = new JTextField(30);
	private final JTextField author = new JTextField(30);
	private final JTextField publisher = new JTextField(30);
	private Book book;
	private final JComboBox<Shelf> shelf = new JComboBox<Shelf>(Shelf.values());
	private final ValidationResultModel validationResultModel = new DefaultValidationResultModel();
	/**
	 * 
	 */
	private static final long serialVersionUID = -7539085064049707218L;
	private ValidationResult validationResult;

	public BookEditor() {
		validationResult = new ValidationResult();

		this.validationResultModel
				.addPropertyChangeListener(new ValidationListener());
		this.dialog.add(createPanel());
//		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.dialog.pack();
		this.dialog.setModal(true);
		
	}
	public BookEditor(Book book){
		this();
		this.book = book;
		this.title.setText(book.getName());
		this.author.setText(book.getAuthor());
		this.publisher.setText(book.getPublisher());
		this.shelf.setSelectedItem(book.getShelf());
		
	}
	// validate each of the three input fields
	public void validate() {
		validationResult = new ValidationResult();
		// validate the field
		if (!ValidationUtils.hasMinimumLength(this.title.getText(),1)) {
			validationResult.addError("The Title field can not be blank.");
		}
		// validate the username field
		if (!ValidationUtils.hasMinimumLength(this.author.getText(),1)) {
			validationResult.addError("The Author field can not be blank.");
		}

		// validate the phoneNumber field
		if (!ValidationUtils.hasMinimumLength(this.publisher.getText(),1)) {
			validationResult
					.addError("The Publisher field can not be blank.");
		}
		if (shelf.getSelectedIndex() < 0){
			validationResult
			.addError("The Shelf field can not be blank.");
		}
	}

	private JPanel createPanel() {
		FormLayout layout = new FormLayout("pref, 4dlu, pref:grow");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		int columnCount = builder.getColumnCount();
//		 builder.setDefaultDialogBorder();
		builder.border(getBorder());
		builder.append("Titel", this.title);
		builder.append("Author", this.author);
		builder.append("Publisher", this.publisher);
		builder.append("Shelf", this.shelf);

		ButtonBarBuilder buttonBar = new ButtonBarBuilder();

		buttonBar.addButton(new OkAction(), new CancelAction());
		// .buildOKCancelBar(new JButton(
		// new OkAction()), new JButton(new CancelAction()));
		builder.append(buttonBar.build(), columnCount);
		JComponent validationResultsComponent =
                ValidationResultViewFactory.createReportList(
                        this.validationResultModel);
        builder.appendUnrelatedComponentsGapRow();
        builder.appendRow("fill:50dlu:grow");
        builder.nextLine(2);
        builder.append(validationResultsComponent, columnCount);
		return builder.getPanel();
	}

	public void show() {
		this.dialog.setVisible(true);
		this.dialog.getParent().setEnabled(false);
	}

	private final class OkAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private OkAction() {
			super("Ok");
		}

		public void actionPerformed(ActionEvent e) {
            //don't close the frame on OK unless it validates    
			validate();
            validationResultModel.setResult(validationResult);
            if (!validationResultModel.hasErrors()) {
            		saveToBook();
            		System.out.println(book.getShelf());
                dialog.dispose();
            }
		}

		private void saveToBook() {
			book.setAuthor(author.getText());
			book.setName(title.getText());
			book.setPublisher(publisher.getText());
			book.setShelf((Shelf) shelf.getSelectedItem());
		}
	}
	
	public BookEditor(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	private final class CancelAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private CancelAction() {
			super("Cancel");
		}

		public void actionPerformed(ActionEvent e) {
			dialog.dispose();
		}
	}

	public BookEditor(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public BookEditor(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	// display informative dialogs for specific validation events
	private static final class ValidationListener implements
			PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			String property = evt.getPropertyName();
			if (ValidationResultModel.PROPERTY_RESULT.equals(property)) {
//				JOptionPane.showMessageDialog(null,
//						"At least one validation result changed");
			} else if (ValidationResultModel.PROPERTY_MESSAGES.equals(property)) {
				if (Boolean.TRUE.equals(evt.getNewValue())) {
//					JOptionPane.showMessageDialog(null,
//							"Overall validation changed");
				}
			}
		}
	}
}
