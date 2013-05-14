package viewPanels;

import java.awt.LayoutManager;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.List;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JTable;

import domain.Book;
import domain.Copy;
import domain.Copy.Condition;
import domain.Library;

import viewModels.CopiesTableModel;


import javax.swing.ListSelectionModel;
import java.awt.Insets;
import javax.swing.JButton;

import com.jgoodies.forms.builder.ButtonBarBuilder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Stack;

public class CopyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6720768505050317833L;
	private Book book;
	private Library library;
	private JTable table;
	private JPanel panel;
	private JPanel panel_2;
	private Stack<Copy>additions = new Stack<Copy>();
	private Stack<Copy>deletions = new Stack<Copy>();
	private Stack<Copy>changes = new Stack<Copy>();
	private JPanel panel_3;

	/**
	 * @wbp.parser.constructor
	 */
	public CopyPanel(Library lib, Book book) {
		this.library = lib;
		this.book = book;
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Copies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		
		table = new JTable(new CopiesTableModel(library, book));
		createtableScrollPane(panel).setViewportView(table);
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		ButtonBarBuilder addRemoveCopy = new ButtonBarBuilder();
		addRemoveCopy.addButton(new RemoveAction(),new AddAction());
		panel_3.setLayout(new BorderLayout(0, 0));
		panel_3.add(addRemoveCopy.build(),BorderLayout.EAST);

		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		ButtonBarBuilder buttonBar = new ButtonBarBuilder();

		buttonBar.addButton(new OkAction(), new CancelAction());
		panel_2.add(buttonBar.build(),BorderLayout.EAST);
//		panel_3 = new JPanel();
//		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
//		flowLayout.setAlignment(FlowLayout.RIGHT);
//		panel_2.add(panel_3, BorderLayout.NORTH);
//		
//		btnNewButton = new JButton("New button");
//		panel_3.add(btnNewButton);
//		
//		btnNewButton_1 = new JButton("New button");
//		panel_3.add(btnNewButton_1);
//		TableColumn ConditionColumn = table_1.getColumnModel().getColumn(1);
//		JComboBox<Condition> conditions = new JComboBox<Condition>(Condition.values());
//		ConditionColumn.setCellEditor(new DefaultCellEditor(conditions));
	}
	private JScrollPane createtableScrollPane(JPanel panel_1) {
		panel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane);
		return scrollPane;
	}
	private final class AddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private AddAction() {
			super("Add new Copy");
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			
			additions.add(library.createAndAddCopy(book));
		}
	}
	private final class RemoveAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private RemoveAction() {
			super("Remove Selected Copy");
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private final class OkAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private OkAction() {
			super("Ok");
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private final class CancelAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private CancelAction() {
			super("Cancel");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	protected Copy getSelectedCopy() {
		return library.getCopies().get(table.convertRowIndexToModel(table.getSelectedRow()));
	}
	public CopyPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public CopyPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public CopyPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
