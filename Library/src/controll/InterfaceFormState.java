package controll;

import java.util.Observable;
import java.util.Observer;

import views.AbstractStatefullForm;

public interface InterfaceFormState extends Observer{
	public void update(final AbstractStatefullForm FORM,final Observable realObject);
	public void reloadFieldsfromRealObject(final AbstractStatefullForm FORM);
	public void saveChangestoRealObject(final AbstractStatefullForm FORM);
	
}
