package controll;

import java.util.Observable;

import views.AbstractStatefullForm;

public class UnchangedFormState implements InterfaceFormState {

	@Override
	public void update(AbstractStatefullForm FORM, Observable realObject) {
		FORM.getSaveBtn().setEnabled(false);
		FORM.getReloadBtn().setEnabled(true);
		FORM.getReloadBtn().setForeground(myBadColor);
		FORM.setState(new OldFormState());
	}

	@Override
	public void reloadFieldsfromRealObject(AbstractStatefullForm FORM) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveChangestoRealObject(AbstractStatefullForm FORM) {
		// TODO Auto-generated method stub
		
	}

}
