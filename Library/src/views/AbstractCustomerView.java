package views;

import javax.swing.JFrame;
import javax.swing.JLabel;

import components.MyJTextField;

import domain.Customer;

public class AbstractCustomerView {

	protected JFrame frame;
	protected JLabel lblCustomerWindow;
	protected MyJTextField txtFirstName;
	protected MyJTextField txtStreetName;
	protected MyJTextField txtStreetNr;
	protected MyJTextField txtCityName;
	protected MyJTextField txtPLZ;
	protected MyJTextField txtLastName;
	protected Customer realCustomer;

	public AbstractCustomerView() {
		super();
	}

	protected String getLblCustomerWindow() {
		return lblCustomerWindow.getText();
	}

	protected void setLblCustomerWindow(String input) {
		this.lblCustomerWindow.setText(input);
	}

	protected String getTxtFirstName() {
		return txtFirstName.getText();
	}

	protected void setTxtFirstName(String input) {
		txtFirstName.setWrittingSettings();
		this.txtFirstName.setText(input);
	}

	protected String getTxtStreetName() {
		return txtStreetName.getText();
	}

	protected void setTxtStreetName(String input) {
		txtStreetName.setWrittingSettings();
		this.txtStreetName.setText(input);
	}

	protected String getTxtStreetNr() {
		return txtStreetNr.getText();
	}

	protected void setTxtStreetNr(String input) {
		txtStreetNr.setWrittingSettings();
		this.txtStreetNr.setText(input);
	}

	protected String getTxtCityName() {
		return txtCityName.getText();
	}

	protected void setTxtCityName(String input) {
		txtCityName.setWrittingSettings();
		this.txtCityName.setText(input);
	}

	protected String getTxtPLZ() {
		return txtPLZ.getText();
	}

	protected void setTxtPLZ(String input) {
		txtPLZ.setWrittingSettings();
		this.txtPLZ.setText(input);
	}

	protected String getTxtLastName() {
		return txtLastName.getText();
	}

	protected void setTxtLastName(String input) {
		txtLastName.setWrittingSettings();
		this.txtLastName.setText(input);
	}

	public void setVisible() {
		frame.setVisible(true);
	}

}