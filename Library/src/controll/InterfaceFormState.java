package controll;

import java.awt.Color;
import java.util.Observable;

import views.AbstractStatefullForm;

public interface InterfaceFormState{
	static final Color myBlackColor = new Color(0, 0, 0);
	static final Color myBadColor = new Color(255, 99, 71);
	public void update(final AbstractStatefullForm FORM,final Observable realObject);
	public void reloadFieldsfromRealObject(final AbstractStatefullForm FORM);
	public void saveChangestoRealObject(final AbstractStatefullForm FORM);
	
}
