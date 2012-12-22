package controll;

import java.util.Observable;

import components.MyJTextField;

import views.AbstractStatefullForm;

public class ChangedFormState implements InterfaceFormState {

	public ChangedFormState(AbstractStatefullForm FORM) {
		FORM.getSaveBtn().setEnabled(true);
		FORM.getReloadBtn().setEnabled(true);
	}

	@Override
	public void update(AbstractStatefullForm FORM, Observable realObject) {
		FORM.setState(new OldFormState(FORM));
	}

	@Override
	public void reloadFieldsfromRealObject(AbstractStatefullForm FORM) {
		FORM.setState(new UnchangedFormState(FORM));
	}

	@Override
	public void saveChangestoRealObject(AbstractStatefullForm FORM) {
		FORM.getSaveBtn().setEnabled(false);
		FORM.getReloadBtn().setEnabled(false);
		FORM.getReloadBtn().setForeground(myBlackColor);
		FORM.setState(new UnchangedFormState(FORM));
		
	}

	@Override
	public void keyreleased(MyJTextField mytextfield, AbstractStatefullForm FORM) {
		if(mytextfield.changed()){
		} else {
			FORM.setState(new UnchangedFormState(FORM));
		}
		
	}


}
