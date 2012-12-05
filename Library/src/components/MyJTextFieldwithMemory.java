package components;

public class MyJTextFieldwithMemory extends MyJTextField{
	
	private static final long serialVersionUID = -5715038427462541002L;
	private String old_text;
	
	private MyJTextFieldwithMemory(String default_text) {
		super(default_text);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public MyJTextFieldwithMemory(String default_text, String old_text){
		this(default_text);
		this.old_text=old_text;
		setText(old_text);
		setWrittingSettings();
	}
	public boolean changed(){
		return !(old_text.equals(getText()));
	}
	public void setTextReload(String s){
		old_text=s;
		setWrittingSettings();
		setText(s);
	}

}