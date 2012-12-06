package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import components.MyJTextField;

import controll.InterfaceFormState;

public abstract class AbstractStatefullForm implements Observer{
	protected static final Color myBlackColor = new Color(0, 0, 0);
	protected static final Color myBadColor = new Color(255, 99, 71);
	protected InterfaceFormState myState;
	protected JButton btnSave;
	protected JButton btnReload;
	
	public abstract void setState(InterfaceFormState newState);
	public abstract void update(Observable arg0, Object arg1);
	public abstract void reloadFieldsfromRealObject();
	public abstract void saveChangestoRealObject();
	public abstract void addListenertoMyFields();
	public JButton getSaveBtn() {
		return btnSave;
	}
	public JButton getReloadBtn() {
		return btnReload;
	}
	public List<MyJTextField> getMyFields(JPanel panel) {
		List<MyJTextField> answerFields = new ArrayList<MyJTextField>();
		for(Component f : panel.getComponents()){
			answerFields.add((MyJTextField)f);
		}
		return answerFields;	
	}
	public void addListenertoMyFields(JPanel panel) {
		for(Component f : panel.getComponents()){
			if(f instanceof MyJTextField){
				final MyJTextField mytextfield = (MyJTextField) f;
				mytextfield.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						super.keyReleased(e);
						if(mytextfield.changed()){
							btnSave.setEnabled(true);
							btnReload.setEnabled(true);
						} else {
							btnSave.setEnabled(false);							
						}
					}
					
				});;
			}
		}
	}
	public void addListenertoReloadbtn() {
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reloadFieldsfromRealObject();		
			}
		});
	}
	public void addListenertoSavebtn() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveChangestoRealObject();
				btnSave.setEnabled(false);
				btnReload.setEnabled(false);
				btnReload.setForeground(myBlackColor);
			}
		});
	}
}
