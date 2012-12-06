package views;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import components.MyJTextField;
import controll.InterfaceFormState;

public abstract class AbstractStatefullForm implements Observer{
	protected InterfaceFormState myState;
	
	public abstract List<MyJTextField> getMyFields();
	public abstract JButton getSaveBtn();
	public abstract JButton getReloadBtn();
	public abstract void setState(InterfaceFormState newState);
	public abstract void update(Observable object);
	public abstract void update(Observable arg0, Object arg1);
	public abstract void reloadFieldsfromRealObject();
	public abstract void saveChangestoRealObject();
	public abstract void addListenertoMyFields();
	public abstract void addListenertoSavebtn();
	public abstract void addListenertoReloadbtn();
}
