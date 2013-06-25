package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class CustomerAddLoanView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearchfield;
	private JTable copiesTable;
	private JTable loanTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerAddLoanView dialog = new CustomerAddLoanView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerAddLoanView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCustomer = new JLabel("Customer:");
			GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
			gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomer.gridx = 0;
			gbc_lblCustomer.gridy = 0;
			contentPanel.add(lblCustomer, gbc_lblCustomer);
		}
		{
			JLabel lblcustomername = new JLabel("%customer_name");
			GridBagConstraints gbc_lblcustomername = new GridBagConstraints();
			gbc_lblcustomername.insets = new Insets(0, 0, 5, 5);
			gbc_lblcustomername.gridx = 1;
			gbc_lblcustomername.gridy = 0;
			contentPanel.add(lblcustomername, gbc_lblcustomername);
		}
		{
			txtSearchfield = new JTextField();
			txtSearchfield.setText("Search_field");
			GridBagConstraints gbc_txtSearchfield = new GridBagConstraints();
			gbc_txtSearchfield.gridwidth = 2;
			gbc_txtSearchfield.insets = new Insets(0, 0, 5, 5);
			gbc_txtSearchfield.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSearchfield.gridx = 0;
			gbc_txtSearchfield.gridy = 1;
			contentPanel.add(txtSearchfield, gbc_txtSearchfield);
			txtSearchfield.setColumns(10);
		}
		{
			JButton btnAddLoan = new JButton("Add");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
			gbc_btnAdd.gridx = 2;
			gbc_btnAdd.gridy = 1;
			contentPanel.add(btnAddLoan, gbc_btnAdd);
		}
		{
			JScrollPane copiesScrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(copiesScrollPane, gbc_scrollPane);
			{
				copiesTable = new JTable();
				copiesScrollPane.setViewportView(copiesTable);
			}
		}
		{
			JScrollPane loanScrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(loanScrollPane, gbc_scrollPane);
			{
				loanTable = new JTable();
				loanScrollPane.setViewportView(loanTable);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
