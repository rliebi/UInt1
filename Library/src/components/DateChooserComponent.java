package components;

import java.util.GregorianCalendar;

import com.toedter.calendar.JDateChooser;

public class DateChooserComponent extends JDateChooser {

	private static final long serialVersionUID = -429563304003144563L;

	public DateChooserComponent (){
		super();
	}
	
	public boolean validateDate(){
		boolean ret = true;
		GregorianCalendar date = new GregorianCalendar();
		try{
			date.setGregorianChange(getDate());
		}catch (NullPointerException e){
			ret = false;
		}
		return ret;
	}
	
}
