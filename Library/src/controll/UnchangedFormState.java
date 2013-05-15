package controll;

import java.util.Observable;
import components.MyJTextField;
import components.StateLogicException;

import views.AbstractStatefullForm;
@Deprecated
public class UnchangedFormState implements InterfaceFormState{
	
	public UnchangedFormState(AbstractStatefullForm FORM) {
		FORM.getSaveBtn().setEnabled(false);
		FORM.getReloadBtn().setEnabled(false);
		FORM.getReloadBtn().setForeground(myBlackColor);
	}

	@Override
	public void update(AbstractStatefullForm FORM, Observable realObject) {		
		FORM.setState(new OldFormState(FORM));
	}

	@Override
	public void reloadFieldsfromRealObject(AbstractStatefullForm FORM) throws StateLogicException{
		throw new StateLogicException();
	}

	@Override
	public void saveChangestoRealObject(AbstractStatefullForm FORM) throws StateLogicException {
		throw new StateLogicException();
	}

	@Override
	public void keyreleased(MyJTextField mytextfield, AbstractStatefullForm FORM) {
		if(mytextfield.changed()){
			FORM.setState(new ChangedFormState(FORM));
		} else {
			FORM.getSaveBtn().setEnabled(false);							
		}
	}


}
