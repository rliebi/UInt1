package views;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import settings.Icons;
import viewPanels.BookPanel;
import viewPanels.CustomerPanel;
import viewPanels.LoanPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;

import localization.Messages;

import domain.Library;

public class MasterView {

	private static final int minimum_window_height = 700;
	private static final int minimum_window_witdh = 1200;
	private JFrame frame;
	private static int windowOpen = 0;
	private Library library;
	private JTabbedPane tabbedPane;
	private static MasterView m;
	private static Window frontWindow;

	private MasterView(Library library) {
		this.library = library;
		initialize();
		frame.setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventPostProcessor(new KeyEventPostProcessor() {
					@Override
					public boolean postProcessKeyEvent(KeyEvent key) {
						if (key.getID() == KeyEvent.KEY_PRESSED) {

							if (windowOpen == 0 || frontWindow == null) {
								if ((key.getKeyCode() == KeyEvent.VK_TAB || (key
										.isControlDown() && key.getKeyCode() == KeyEvent.VK_W))) {

									int tabIndex;
									tabIndex = tabbedPane.getSelectedIndex();
									if (tabIndex == tabbedPane
											.getComponentCount() - 1)
										tabIndex = -1;
									tabbedPane.setSelectedIndex(tabIndex + 1);
								}
							}
							else{
								if ( key.getKeyCode() == KeyEvent.VK_ESCAPE){
									frontWindow.dispose();
								}
							}
						}
						return false;

					}
				});
	}

	public static MasterView getInstance() {
		if (m != null)
			return m;
		return null;

	}

	public static MasterView getInstance(Library lib) throws Exception {
		if (m == null)
			m = new MasterView(lib);
		return m;

	}

	private void initialize() {
		frame = new JFrame("Library Application");
		frame.setMinimumSize(new Dimension(minimum_window_witdh,
				minimum_window_height));
		frame.setBounds(100, 100, 567, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 567, 0 };
		gridBagLayout.rowHeights = new int[] { 588, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel bookTab = new BookPanel(library);
		tabbedPane.addTab(Messages.getString("MasterView.pnlBooks.title"), Icons.IconEnum.BOOK.getIcon(72), bookTab, null);

		JPanel lendingTab = new LoanPanel(library);
		tabbedPane.addTab(Messages.getString("MasterView.pnlLoan.title"), Icons.IconEnum.LOAN.getIcon(72), lendingTab, null);

		CustomerPanel customerTab = new CustomerPanel(library);
		tabbedPane.addTab(Messages.getString("MasterView.pnlCustomers.title"), Icons.IconEnum.CUSTOMER.getIcon(72), customerTab, null);

	}

	public static void setWindowOpen() {
		windowOpen++;
	}

	public static void setWindowClose() {

		if (windowOpen > 0)
			windowOpen--;
	}

	public Point getLocation() {
		return frame.getLocation();
	}

	public JFrame getFrame() {
		return frame;
	}

	public static void setFrontWindow(Window w) {
		if (frontWindow!= null){
			if (frontWindow instanceof JDialog) {
				((JDialog) frontWindow).setModal(false);
			}
			frontWindow.setAlwaysOnTop(false);
		}
		
		frontWindow = w;
		if (frontWindow!= null){
			if (frontWindow instanceof JDialog) {
				((JDialog) frontWindow).setModal(true);
				((JDialog) frontWindow).setModalityType(ModalityType.APPLICATION_MODAL);
			}
			frontWindow.setAlwaysOnTop(true);
		}
	}

	public static Window getFrontWindow() {
		return frontWindow;
	}
}
