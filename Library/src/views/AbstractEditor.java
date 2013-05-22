package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import localization.Messages;
import settings.Icons;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.util.ValidationUtils;

public abstract class AbstractEditor {
	protected final ValidationResultModel validationResultModel = new DefaultValidationResultModel();

	protected JDialog d = new JDialog();
	protected ValidationResult validationResult = new ValidationResult();
	public AbstractEditor(Component p) {
		super();
		d.setLocationRelativeTo(p);
	}

	/**
	 * @return
	 */
	protected final DefaultFormBuilder builder() {
		FormLayout layout = new FormLayout(
	            "10px,pref, 8px, 100px, 4px, 200px:grow,10px",   
	            "10px,pref, 6px, pref, 6px, pref, 6px, pref,pref,10px"); 
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		return builder;
	}

	/**
	 * @return
	 */
	protected final JPanel buttonPanel() {
		JButton closeBtn = new JButton();
		closeBtn.setAction(new CancelAction());
	    closeBtn.setIcon(Icons.IconEnum.CANCEL.getIcon(16));
		closeBtn.setText(Messages.getString("Global.btnCancel.title"));
	    JButton saveBtn = new JButton();
	    saveBtn.setAction(new OkAction());
		saveBtn.setIcon(Icons.IconEnum.SAVE.getIcon(16));
		saveBtn.setText(Messages.getString("Global.btnSave.title"));


		ButtonBarBuilder buttonBar = new ButtonBarBuilder();
		buttonBar.addButton(closeBtn);
		buttonBar.addButton(saveBtn);
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new BorderLayout(0, 0));
		button_panel.add(buttonBar.build(),BorderLayout.EAST);
		return button_panel;
	}
	public abstract void validate();
	protected abstract void saveTask();
	protected abstract void cancelTask();
	protected abstract void createPanel();
	public void checkTxtField(JTextField t) {
		if (!ValidationUtils.hasMinimumLength(t.getText(),1)) {
			t.setBackground(new Color(754909184,true));
			validationResult.addError("Field must not be empty");
		}else{
			t.setBackground(new Color(255, 255, 255,255));
		}
	}
	protected final class OkAction extends AbstractAction {
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
            		saveTask();
                
            }
		}
	}
	protected final class CancelAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private CancelAction() {
			super("Cancel");
		}

		public void actionPerformed(ActionEvent e) {
			cancelTask();
		}


	}
}