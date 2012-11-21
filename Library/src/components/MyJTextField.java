package components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyJTextField extends JTextField {

	private static final long serialVersionUID = 5710100378594610649L;
	private String default_text;
	public MyJTextField(String default_text){
		super();
		this.default_text=default_text;
		reset_to_placeholder();
		this.addFocusListener(new MyTextField_FocusAdapter(this));
	}
	private JTextField reset_to_placeholder(){
		this.setForeground(Color.LIGHT_GRAY);
		this.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		this.setText(default_text);
		return this;
	}
	private JTextField clear_textField(){
		this.setText("");
		setWrittingSettings();
		return this;
		
	}
	public void setWrittingSettings() {
		this.setForeground(Color.black);
		this.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	}
	private final class MyTextField_FocusAdapter extends FocusAdapter {
		JTextField myField;
		public MyTextField_FocusAdapter(JTextField field){
			super();
			myField=field;
		}
		@Override
		public void focusGained(FocusEvent e) {
			if(myField.getText().equals(default_text)){
				clear_textField();
			} else { setWrittingSettings();}
		}
		@Override
		public void focusLost(FocusEvent e) {
			if(myField.getText().isEmpty()){
				reset_to_placeholder();
			}
		}
	}
	
}