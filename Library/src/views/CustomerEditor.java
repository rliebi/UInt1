package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;

import localization.Messages;



import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.validation.ValidationResult;

import domain.Customer;
import domain.Library;


public class CustomerEditor extends AbstractEditor {
	private JTextField firstName = new JTextField();
	private JTextField lastName = new JTextField();
	private JTextField street = new JTextField();
	private JTextField zip = new JTextField(10);
	private JTextField city = new JTextField(20);
	private Library library;
	private Customer customer;
	private boolean newCustomer = false;
	public CustomerEditor(Library l,Component p){
		super(p);
		this.library = l;
		customer = new Customer("", "");
		newCustomer = true;
		d.setTitle(Messages.getString("CreateNewCustomerTitle.title"));
		createPanel();
	}
	public CustomerEditor(Library l,Customer c,Component p) {
		super(p);
		this.library = l;
		this.customer = c;
		d.setTitle(Messages.getString("EditCustomerTitle.title"));
		firstName.setText(c.getName());
		lastName.setText(c.getSurname());
		street.setText(c.getStreet());
		zip.setText(c.getZip()+"");
		city.setText(c.getCity());
		
		createPanel();
	}
	
	public void createPanel(){
		DefaultFormBuilder builder = builder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name:"),     cc.xy (2, 2));
        builder.add(firstName, cc.xy(4,2));
        builder.add(lastName, cc.xy(6,2));
        builder.add(new JLabel("Street:"),    cc.xy (2, 4));
        builder.add(street,        cc.xyw(4, 4, 3));  
        builder.add(new JLabel("ZIP/City:"), cc.xy (2, 6));
        builder.add(zip,        cc.xy (4, 6));
        builder.add(city,        cc.xy (6, 6));
		builder.add(buttonPanel(),cc.xyw(6, 9, 1));
		d.getContentPane().add(builder.getPanel());
		d.setPreferredSize(builder.getPanel().getPreferredSize());
		d.setMinimumSize(new Dimension(400,170));
		d.setVisible(true);

	}
	
	
	@Override
	public void validate() {
		validationResult = new ValidationResult();
		checkTxtField(firstName);
		checkTxtField(city);
		checkTxtField(lastName);
		checkTxtField(street);
		checkTxtField(zip);
		try{
			Integer.parseInt(zip.getText());
			if (zip.getText().length() !=4) throw new NumberFormatException();
			zip.setBackground(new Color(255, 255, 255,255));
		}
		catch (NumberFormatException e){
			zip.setBackground(new Color(754909184,true));
			validationResult.addError("Field must contain only numbers");

		}
			
	}
	@Override
	protected void saveTask() {
		customer.setAdress(street.getText(), Integer.parseInt(zip.getText()), city.getText());
		customer.setName(firstName.getText());
		customer.setSurname(lastName.getText());
		if (newCustomer)
			library.addCustomer(customer);
		d.dispose();
	}
	@Override
	protected void cancelTask() {
		d.dispose();
	}
	public Customer getCustomer() {
		return customer;
	}
}
