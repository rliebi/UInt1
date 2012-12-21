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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import components.MyJTextField;

import controll.InterfaceFormState;

public abstract class AbstractStatefullForm implements Observer{
	protected static final Color myBlackColor = new Color(0, 0, 0);
	protected static final Color myBadColor = new Color(255, 99, 71);
	protected InterfaceFormState myState;
	protected JButton btnSave;
	protected JButton btnReload;
	
	public abstract void update(Observable arg0, Object arg1);
	public abstract void reloadFieldsfromRealObject();
	public abstract void saveChangestoRealObject();
	protected abstract void addListenertoMyFields();
	protected abstract void deleteViewObserverFromObject();
	
	public JButton getSaveBtn() {
		return btnSave;
	}
	public JButton getReloadBtn() {
		return btnReload;
	}
	public List<MyJTextField> getMyFields(JPanel panel) {
		List<MyJTextField> answerFields = new ArrayList<MyJTextField>();
		for(Component f : panel.getComponents()){
			if(f instanceof MyJTextField){
				answerFields.add((MyJTextField)f);				
			}
		}
		return answerFields;	
	}
	public void addListenertoMyFields(JPanel panel,final AbstractStatefullForm FORM) {
		for(Component f : panel.getComponents()){
			if(f instanceof MyJTextField){
				final MyJTextField mytextfield = (MyJTextField) f;
				mytextfield.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						super.keyReleased(e);
						myState.keyreleased(mytextfield, FORM);
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
			}
		});
	}
	
	public void setState(InterfaceFormState newState) {
		myState=newState;
		System.out.println("Set state: " + newState.toString());
	}
	public static void addEscapeListener(final JFrame frame) {
	    ActionListener escListener = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            frame.setVisible(false);
	        }
	    };

	    frame.getRootPane().registerKeyboardAction(escListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);

	}
}
