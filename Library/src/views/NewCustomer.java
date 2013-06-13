package views;

import javax.swing.JFrame;

import domain.Customer;
import domain.Library;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

import components.MyJTextField;
import components.StateLogicException;
import controll.UnchangedFormState;

import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Deprecated
public class NewCustomer extends AbstractStatefullForm{
	

	protected JFrame frame;
	private JLabel lblCustomerWindow;
	private MyJTextField txtFirstName;
	private MyJTextField txtStreetName;
	private MyJTextField txtCityName;
	private MyJTextField txtPLZ;
	private MyJTextField txtLastName;
	private Customer realCustomer;
	private JPanel panel;
	private boolean is_saved;
	private Library library;
	private WarningWindow warningWindow;
	@Deprecated
	public NewCustomer(Customer customer, Library library) {
		is_saved=false;
		this.library=library;
		setCustomer(customer);
		customer.addObserver(this);
		initialize();
		lblCustomerWindow.setText("New Customer");
		frame.getRootPane().setDefaultButton(btnSave);
		setState(new UnchangedFormState(this));
	}


	public static void main(String[] args) {
		Customer testCustomer = new Customer("", "");
		Library library = new Library();
		NewCustomer newcustomer_window = new NewCustomer(testCustomer,library);
		newcustomer_window.setVisible();
		newcustomer_window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setCustomer(Customer customer){
		realCustomer = customer;
	}
	
	public int deregister(){
		realCustomer.deleteObserver(this);
		return 0;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		myState.update(this, realCustomer);
	}


	public void setVisible(){
		frame.setVisible(true);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame("New Customer");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				deleteViewObserverFromObject();
			}
		});
		frame.setResizable(false);
		frame.setBounds(100, 100, 416, 262);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		addEscapeListener(frame);
		
		panel = new JPanel();
		panel.setMinimumSize(new Dimension(60, 30));
		panel.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 170, 0, 82, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblCustomerWindow = new JLabel("New Customer");
		GridBagConstraints gbc_lblNewCustomer = new GridBagConstraints();
		gbc_lblNewCustomer.gridwidth = 5;
		gbc_lblNewCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewCustomer.gridx = 0;
		gbc_lblNewCustomer.gridy = 0;
		panel.add(lblCustomerWindow, gbc_lblNewCustomer);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		panel.add(lblName, gbc_lblName);
		
		txtFirstName = new MyJTextField("First",realCustomer.getSurname());
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 2;
		panel.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new MyJTextField("Last");
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.gridwidth = 2;
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 2;
		gbc_txtLastName.gridy = 2;
		panel.add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);
		
		JLabel lblStreet = new JLabel("Street");
		GridBagConstraints gbc_lblStreet = new GridBagConstraints();
		gbc_lblStreet.anchor = GridBagConstraints.EAST;
		gbc_lblStreet.insets = new Insets(0, 0, 5, 5);
		gbc_lblStreet.gridx = 0;
		gbc_lblStreet.gridy = 3;
		panel.add(lblStreet, gbc_lblStreet);
		
		txtStreetName = new MyJTextField("Street");
		GridBagConstraints gbc_txtStreetName = new GridBagConstraints();
		gbc_txtStreetName.insets = new Insets(0, 0, 5, 5);
		gbc_txtStreetName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStreetName.gridx = 1;
		gbc_txtStreetName.gridy = 3;
		panel.add(txtStreetName, gbc_txtStreetName);
		txtStreetName.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 4;
		panel.add(lblCity, gbc_lblCity);
		
		txtCityName = new MyJTextField("City");
		GridBagConstraints gbc_txtCityName = new GridBagConstraints();
		gbc_txtCityName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCityName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCityName.gridx = 1;
		gbc_txtCityName.gridy = 4;
		panel.add(txtCityName, gbc_txtCityName);
		txtCityName.setColumns(10);
		
		txtPLZ = new MyJTextField("Plz");
		txtPLZ.setMaximumSize(new Dimension(60, 2147483647));
		txtPLZ.setMinimumSize(new Dimension(60, 28));
		txtPLZ.setPreferredSize(new Dimension(60, 28));
		GridBagConstraints gbc_txtPLZ = new GridBagConstraints();
		gbc_txtPLZ.anchor = GridBagConstraints.WEST;
		gbc_txtPLZ.insets = new Insets(0, 0, 5, 5);
		gbc_txtPLZ.gridx = 2;
		gbc_txtPLZ.gridy = 4;
		panel.add(txtPLZ, gbc_txtPLZ);
		txtPLZ.setColumns(10);
		
		addListenertoMyFields();
		
		btnReload = new JButton("Reload");
		btnReload.setEnabled(false);
		addListenertoReloadbtn();
		GridBagConstraints gbc_btnRload = new GridBagConstraints();
		gbc_btnRload.anchor = GridBagConstraints.EAST;
		gbc_btnRload.insets = new Insets(0, 0, 0, 5);
		gbc_btnRload.gridx = 1;
		gbc_btnRload.gridy = 6;
		panel.add(btnReload, gbc_btnRload);
		
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		addListenertoSavebtn();
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.anchor = GridBagConstraints.WEST;
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 6;
		panel.add(btnSave, gbc_btnSave);
		
		addEscapeListener(frame);
	}


	@Override
	public void reloadFieldsfromRealObject() {
		this.txtFirstName.setTextReload(realCustomer.getSurname());
		txtLastName.setWrittingSettings();
		this.txtLastName.setTextReload(realCustomer.getName());
		this.txtStreetName.setTextReload(realCustomer.getStreet());
		this.txtCityName.setTextReload(realCustomer.getCity());
		this.txtPLZ.setTextReload(realCustomer.getZip()+"");
		try {
			myState.reloadFieldsfromRealObject(this);
		} catch (StateLogicException e) {
			e.printStackTrace();
		}
		if(!is_saved){for(MyJTextField f : getMyFields(panel)){f.reset_to_placeholder();}}
	}


	@Override
	public void saveChangestoRealObject() {
		try {
			realCustomer.setName(txtLastName.getText());
			txtLastName.setTextReload();
			realCustomer.setSurname(txtFirstName.getText());
			txtFirstName.setTextReload();
			realCustomer.setAdress(txtStreetName.getText(), Integer.parseInt(txtPLZ.getText()), txtCityName.getText());
			txtStreetName.setTextReload();
			myState.saveChangestoRealObject(this);
			if(!is_saved){
				library.addCustomer(realCustomer);
				setState(new UnchangedFormState(this));
				is_saved=true;
			}else {myState.saveChangestoRealObject(this);}
		} catch (StateLogicException e) {
			e.printStackTrace();
		} catch (NumberFormatException ee){
			warningWindow = new WarningWindow("Please insert only a number for ZIP!");
			warningWindow.setVisible();
		}
	}

	@Override
	protected void addListenertoMyFields() {
		addListenertoMyFields(panel,this);
	}
	
	public List<MyJTextField> getMyFields() {
		return getMyFields(panel);
	}
	@Override
	protected void deleteViewObserverFromObject() {
		realCustomer.deleteObserver(this);
	}

}
