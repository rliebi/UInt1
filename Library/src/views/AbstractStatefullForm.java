package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import components.MyJTextField;
import controll.InterfaceFormState;

public abstract class AbstractStatefullForm implements Observer{
	@SuppressWarnings("unused")
	private InterfaceFormState myState;
	
	public abstract MyJTextField getMyFields();
	public abstract JButton getSaveBtn();
	public abstract JButton getReloadBtn();
	public abstract void setState(InterfaceFormState newState);
	public abstract void update(Observable object);
	public abstract void reloadFieldsfromRealObject();
	public abstract void saveChangestoRealObject();
	public abstract void addListenertoMyFields();
	public abstract void addListenertoSavebtn();
	public abstract void addListenertoReloadbtn();
}
