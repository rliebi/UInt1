package controll;

import java.awt.Color;
import java.util.Observable;

import components.MyJTextField;
import components.StateLogicException;

import views.AbstractStatefullForm;

public interface InterfaceFormState{
	static final Color myBlackColor = new Color(0, 0, 0);
	static final Color myBadColor = new Color(255, 99, 71);
	public void update(final AbstractStatefullForm FORM,final Observable realObject);
	public void reloadFieldsfromRealObject(final AbstractStatefullForm FORM) throws StateLogicException;
	public void saveChangestoRealObject(final AbstractStatefullForm FORM) throws StateLogicException;
	public void keyreleased(MyJTextField mytextfield, final AbstractStatefullForm FORM);
	
}
