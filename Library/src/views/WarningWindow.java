package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Font;

public class WarningWindow {

	private JFrame frame;
	private JLabel txtWarning;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarningWindow window = new WarningWindow();
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WarningWindow() {
		initialize();
		frame.getRootPane().setDefaultButton(btnOk);
	}
	public WarningWindow(String warningtext){
		this();
		txtWarning.setText(warningtext);
	}
	public void setVisible(){
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Warning Window");
		frame.setResizable(false);
		frame.setBounds(100, 100, 275, 137);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblWarning = new JLabel("Warning");
		lblWarning.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblWarning = new GridBagConstraints();
		gbc_lblWarning.insets = new Insets(0, 0, 5, 0);
		gbc_lblWarning.gridx = 0;
		gbc_lblWarning.gridy = 0;
		frame.getContentPane().add(lblWarning, gbc_lblWarning);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		txtWarning = new JLabel("You have a Problem!");
		GridBagConstraints gbc_txtWarning = new GridBagConstraints();
		gbc_txtWarning.gridx = 0;
		gbc_txtWarning.gridy = 0;
		panel.add(txtWarning, gbc_txtWarning);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 2;
		frame.getContentPane().add(btnOk, gbc_btnOk);
	}

}
