package controll;

import java.util.Observable;

import views.AbstractStatefullForm;

public interface InterfaceFormState{
	public void update(final AbstractStatefullForm FORM,final Observable realObject);
	public void reloadFieldsfromRealObject(final AbstractStatefullForm FORM);
	public void saveChangestoRealObject(final AbstractStatefullForm FORM);
	
}
