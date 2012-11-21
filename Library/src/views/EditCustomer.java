package views;

import domain.Customer;


public class EditCustomer extends NewCustomer{
	public EditCustomer(){
		super();
		txtStreetNr.setVisible(false);
		lblCustomerWindow.setText("Edit Customer");
	}
	
	
	public static void main(String[] args) {
		EditCustomer window = new EditCustomer();
		window.setVisible();
		Customer testCustomer = new Customer("Senbony", "Tony");
		testCustomer.setAdress("Adamstreet", 8000, "ZŸrich");
		window.setCustomer(testCustomer);
		window.updateFields();
	}
	public void setCustomer(Customer customer){
		realCustomer = customer;
		updateFields();
	}


	private void updateFields() {
		setTxtFirstName(realCustomer.getName());
		setTxtLastName(realCustomer.getSurname());
		setTxtStreetName(realCustomer.getStreet());
		setTxtCityName(realCustomer.getCity());
		setTxtPLZ(realCustomer.getZip()+"");
		
	}

}
