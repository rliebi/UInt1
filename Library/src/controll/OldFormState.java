package controll;

import java.util.Observable;

import components.MyJTextField;

import views.AbstractStatefullForm;
@Deprecated
public class OldFormState implements InterfaceFormState {
	public OldFormState(AbstractStatefullForm FORM){
		FORM.getSaveBtn().setEnabled(false);
		FORM.getReloadBtn().setEnabled(true);
		FORM.getReloadBtn().setForeground(myBadColor);
	}

	@Override
	public void update(AbstractStatefullForm FORM, Observable realObject) { //This is not a logic error
	}

	@Override
	public void reloadFieldsfromRealObject(AbstractStatefullForm FORM) {
		FORM.setState(new UnchangedFormState(FORM));		
	}

	@Override
	public void saveChangestoRealObject(AbstractStatefullForm FORM) {
		FORM.setState(new UnchangedFormState(FORM));
	}

	@Override
	public void keyreleased(MyJTextField mytextfield, AbstractStatefullForm FORM) {
		if(mytextfield.changed()){
			FORM.getSaveBtn().setEnabled(true);
			FORM.getReloadBtn().setEnabled(true);
		} else {
			FORM.getSaveBtn().setEnabled(false);							
		}
	}

}
