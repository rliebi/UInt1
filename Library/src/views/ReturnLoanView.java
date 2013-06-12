package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import components.DateChooserComponent;
import components.IconListCellRenderer;

import localization.Messages;
import settings.Icons;


import domain.Copy;
import domain.Copy.Condition;
import domain.IllegalLoanOperationException;
import domain.Library;
import domain.Loan;

public class ReturnLoanView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1601744496385889684L;
	private JPanel contentPane;
	private DateChooserComponent txtReturndatevalue;
	private Library library;
	private Loan loan;

	public ReturnLoanView(Library u_library, final Loan u_loan) {
//		super(MasterView.getMasterFrame(), Messages.getString("LoanCloseView.loanClose.title"), true);
		loan = u_loan;
		library = u_library;
		
//		setIconImage(Toolkit.getDefaultToolkit().getImage(MasterView.class.getResource("/resources/images/logo_16.png")));
		
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, Dimension.LOANCLOSEVIEW_WIDTH.getLength(), Dimensions.LOANCLOSEVIEW_HEIGHT.getLength());
//		setMinimumSize(new Dimension(Dimensions.LOANCLOSEVIEW_MINWIDTH.getLength(), Dimensions.LOANCLOSEVIEW_MINHEIGHT.getLength()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setSize(666,204);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		// Center the window
		int w = getSize().width;
		int h = getSize().height;
//		int x = (MasterView.SCREEN_SIZE.width-w)/2;
//		int y = (MasterView.SCREEN_SIZE.height-h)/2;
		
//		CenterPanel
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, Messages.getString("LoanCloseView.loanClose.title"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		
//		Text
		JLabel lblBookTitelText = new JLabel(Messages.getString("Domain.Book.title"));
		JLabel lblCopyNumberText = new JLabel(Messages.getString("Domain.Copy.inventoryNumber"));
		JLabel lblReturnDateText = new JLabel(Messages.getString("Domain.Loan.returnDate"));
		JLabel lblBookConditionText = new JLabel(Messages.getString("Domain.Copy.condition"));
		
//		Value
		String LoanBookTitle = loan.getCopy().getTitle().getName();
		JLabel lblBooktitelvalue = new JLabel((LoanBookTitle.length() > 80) ? LoanBookTitle.substring(0, 77)+"..." : LoanBookTitle);
		JLabel lblCopynumbervalue = new JLabel(loan.getCopy().getInventoryNumber()+"");
		
		txtReturndatevalue = new DateChooserComponent();
		txtReturndatevalue.setDate(Calendar.getInstance().getTime());
		
		final JComboBox<Condition> comboBoxBookCondition = new JComboBox<Condition>();
		comboBoxBookCondition.setModel(new DefaultComboBoxModel<Condition>(Copy.Condition.values()));
		comboBoxBookCondition.setRenderer(new IconListCellRenderer());
		
//		GruopLayout
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblBookConditionText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblReturnDateText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblCopyNumberText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblBookTitelText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBoxBookCondition, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtReturndatevalue)
						.addComponent(lblCopynumbervalue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblBooktitelvalue, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookTitelText)
						.addComponent(lblBooktitelvalue))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCopyNumberText)
						.addComponent(lblCopynumbervalue))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReturnDateText)
						.addComponent(txtReturndatevalue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookConditionText)
						.addComponent(comboBoxBookCondition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
//		Buttons
		JPanel pnlButtons = new JPanel();
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		
		final JButton btnSave = new JButton(Messages.getString("Domain.Loan.OK"));
		pnlButtons.add(btnSave);
		btnSave.setIcon(Icons.IconEnum.SAVE.getIcon(24));
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!txtReturndatevalue.validateDate()){
//					BalloonTipComponent.getNotification(btnSave, Messages.getString("Tooltip.DateValditionError"), BalloonTipComponent.STATE.ERROR);
				}else{
					GregorianCalendar date = new GregorianCalendar();
					date.setGregorianChange(txtReturndatevalue.getDate());
					if(!checkDateBeforePickup(loan, txtReturndatevalue)){
					}else{
						if(loan.getDaysOverdue(txtReturndatevalue)>0){
						}
						try {
							library.returnCopy(loan, date);
						} catch (IllegalLoanOperationException e) {
							System.out.println("This should never heppen.");
							e.printStackTrace();
						}
						loan.getCopy().setCondition((Condition) comboBoxBookCondition.getSelectedItem());
						closeDialog();
					}
				}
			}
			private boolean checkDateBeforePickup(Loan selectedLoan, DateChooserComponent txtReturnDateValue) {
				boolean ret = false;
				Date choosenReturnDate = txtReturnDateValue.getDate();
				Calendar c = Calendar.getInstance();
				c.setTime(choosenReturnDate);
				c.add(Calendar.DATE, 1);
				
				if(selectedLoan.getPickupDate().getTime().before(c.getTime())){
					ret = true;
				}
				return ret;
			}
		});
		
		JButton btnCancel = new JButton(Messages.getString("Global.btnCancel.title"));
		pnlButtons.add(btnCancel);
//		btnCancel.setFont(Fonts.STANDARD_TEXT_BOLD.getFont());
		btnCancel.setIcon(Icons.IconEnum.CANCEL.getIcon(24));
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				if (ballTipSave != null) ballTipSave.setVisible(false);
				closeDialog();
			}				
		});
		
	this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {}
			
			@Override
			public void windowIconified(WindowEvent arg0) {}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
//				MasterView.setModalWindowOpen(false);
				System.out.println(contentPane.getWidth());
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {}
			
			@Override
			public void windowActivated(WindowEvent arg0) {}
		});
	
	btnCancel.setMnemonic(KeyEvent.VK_C);
	btnSave.setMnemonic(KeyEvent.VK_S);
		
	}
	
	private void closeDialog() {
		this.dispose();
//		MasterView.setModalWindowOpen(false);
	}
	
	
}
