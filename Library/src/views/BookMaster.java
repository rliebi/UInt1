package views;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



import domain.Library;



public class BookMaster{


	private static final int minimum_window_height = 700;
	private static final int minimum_window_witdh = 600;
	private static final String TabLabel_LENDING = "Lending";
	private static final String bookTabLabel = "Books";
	private JFrame frame;

	private Library library;

	public BookMaster(Library library) {
		this.library = library;
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(minimum_window_witdh, minimum_window_height));
		frame.setBounds(100, 100, 567, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{567, 0};
		gridBagLayout.rowHeights = new int[]{588, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel bookTab = new BookTab(library);
		tabbedPane.addTab(bookTabLabel, null, bookTab, null);


		JPanel lendingTab = new LendingTab(library);
		tabbedPane.addTab(TabLabel_LENDING, null, lendingTab, null);
	
		
		CustomerTab customerTab = new CustomerTab(library);
		tabbedPane.addTab("Customers", null, customerTab, null);
		
	}
	

}
