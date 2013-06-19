package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import localization.Messages;
import settings.Icons;
import viewModels.LoanCloseTableModel;

import components.DateChooserComponent;

import domain.IllegalLoanOperationException;
import domain.Library;
import domain.Loan;

public class ReturnMultipleLoansView extends AbstractViewer {
	private JPanel contentPane;
	private DateChooserComponent txtReturnDateValue;
	private JTable tblMultipleLoansToColse;
	private Library library;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1476558292965472074L;

	public ReturnMultipleLoansView(Library lib,
			final List<Loan> selectedLoanList) {
		library = lib;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle(Messages.getString("ReturnMultipleLoansView.title"));
		setContentPane(contentPane);
		Dimension d = new Dimension(360,397);
		this.setSize(d);
		setMinimumSize(d);
		setMaximumSize(d);
		// North
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new TitledBorder(null, Messages
				.getString("MasterView.pnlSettings.title"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		// Text
		JLabel lblReturnDateText = new JLabel("ReturnDateText");

		// Value
		txtReturnDateValue = new DateChooserComponent();
		txtReturnDateValue.setDate(Calendar.getInstance().getTime());
		// GruopLayout
		GroupLayout gl_pnlNorth = new GroupLayout(pnlNorth);
		gl_pnlNorth.setHorizontalGroup(gl_pnlNorth.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_pnlNorth
						.createSequentialGroup()
						.addComponent(lblReturnDateText,
								GroupLayout.PREFERRED_SIZE, 94,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtReturnDateValue,
								GroupLayout.PREFERRED_SIZE, 221,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(103, Short.MAX_VALUE)));
		gl_pnlNorth
				.setVerticalGroup(gl_pnlNorth
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlNorth
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_pnlNorth
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblReturnDateText)
														.addComponent(
																txtReturnDateValue,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(28)));
		pnlNorth.setLayout(gl_pnlNorth);

		// Center
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new TitledBorder(null, Messages
				.getString("Global.Loans"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneMultipleLoansToColse = new JScrollPane();
		pnlCenter.add(scrollPaneMultipleLoansToColse, BorderLayout.CENTER);

		tblMultipleLoansToColse = new JTable();
		tblMultipleLoansToColse.setModel(new LoanCloseTableModel(
				selectedLoanList));
		tblMultipleLoansToColse.setEnabled(false);
		scrollPaneMultipleLoansToColse.setViewportView(tblMultipleLoansToColse);

		// South
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		final JButton btnSave = new JButton(
				Messages.getString("Global.btnSave.title"));
		pnlSouth.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String confirmationTitle;
				String confirmationMessage;
				List<String> customeroverdue = checkOverdueCustomers(
						selectedLoanList, txtReturnDateValue);
				if (!txtReturnDateValue.validateDate()) {
					confirmationMessage = Messages
							.getString("Tooltip.DateValditionError");
				} else {
					GregorianCalendar date = new GregorianCalendar();
					date.setGregorianChange(txtReturnDateValue.getDate());
					if (!checkDateBeforePickup(selectedLoanList,
							txtReturnDateValue)) {
						confirmationTitle = Messages
								.getString("Tooltip.ValditionInformationTitle");
						confirmationMessage = "At least one date makes no sense";
						JOptionPane.showMessageDialog(null,
								confirmationMessage, confirmationTitle,
								JOptionPane.NO_OPTION,
								Icons.IconEnum.INFORMATION.getIcon(48));
					} else {
						if (customeroverdue.size() > 0) {
							confirmationTitle = Messages
									.getString("Tooltip.ValditionInformationTitle");
							confirmationMessage = Messages
									.getString("Tooltip.reminder");
							for (String i : customeroverdue) {
								confirmationMessage += i;
							}
							JOptionPane.showMessageDialog(MasterView.getFrontWindow(),
									confirmationMessage, confirmationTitle,
									JOptionPane.NO_OPTION,
									Icons.IconEnum.INFORMATION.getIcon(48));
						}
						try {
							for (Loan selectedLoan : selectedLoanList) {
								System.out.println(selectedLoan.getCopy()
										.getInventoryNumber());
								library.returnCopy(selectedLoan, date);
							}
						} catch (IllegalLoanOperationException e) {
							e.printStackTrace();
						}
						confirmationTitle = Messages
								.getString("Tooltip.ValditionInformationTitle");
						confirmationMessage = Messages
								.getString("Tooltip.ValditionSuccessTitle");
						dispose();
						JOptionPane.showMessageDialog(null,
								confirmationMessage, confirmationTitle,
								JOptionPane.NO_OPTION,
								Icons.IconEnum.OK.getIcon(48));
					}
				}

			}

			private boolean checkDateBeforePickup(List<Loan> selectedLoanList,
					DateChooserComponent txtReturnDateValue) {
				boolean ret = false;
				Date choosenReturnDate = txtReturnDateValue.getDate();
				for (Loan selectedLoan : selectedLoanList) {
					Calendar c = Calendar.getInstance();
					c.setTime(choosenReturnDate);
					c.add(Calendar.DATE, 1);
					if (selectedLoan.getPickupDate().getTime()
							.before(c.getTime())) {
						ret = true;
					}
				}
				return ret;
			}

			private List<String> checkOverdueCustomers(
					List<Loan> selectedLoanList,
					DateChooserComponent txtReturnDateValue) {
				List<String> ret = new ArrayList<String>();

				for (Loan selectedLoan : selectedLoanList) {
					if (selectedLoan.getDaysOverdue(txtReturnDateValue) > 0) {
						ret.add("\n" + selectedLoan.getCustomer().getName()
								+ " --> " + selectedLoan.getDaysOverdue() + " "
								+ Messages.getString("Global.Days"));
					}
				}
				return ret;
			}
		});

		JButton btnClose = new JButton(
				Messages.getString("Global.btnClose.title"));
		pnlSouth.add(btnClose);

		btnSave.setIcon(Icons.IconEnum.SAVE.getIcon(24));
		btnClose.setIcon(Icons.IconEnum.CANCEL.getIcon(24));

		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnClose.setMnemonic(KeyEvent.VK_C);
		btnSave.setMnemonic(KeyEvent.VK_S);

	}
}
