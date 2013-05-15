package viewPanels;

import java.awt.LayoutManager;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import views.BookViewer;


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
	private JPanel table_panel;
	private JPanel south_panel;
	private Stack<Copy>additions = new Stack<Copy>();
	private Stack<Copy>deletions = new Stack<Copy>();
	private Stack<Copy>changes = new Stack<Copy>();
	private JPanel button_panel;
	private JDialog parent;

	/**
	 * @wbp.parser.constructor
	 */
	public CopyPanel(Library lib, Book book, JDialog p) {
		this.library = lib;
		this.book = book;
		this.parent = p;
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
		
		JPanel north_panel = new JPanel();
		north_panel.setBorder(new TitledBorder(null, "Copies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_north_panel = new GridBagConstraints();
		gbc_north_panel.insets = new Insets(0, 0, 5, 0);
		gbc_north_panel.fill = GridBagConstraints.BOTH;
		gbc_north_panel.gridx = 0;
		gbc_north_panel.gridy = 0;
		add(north_panel, gbc_north_panel);
		GridBagLayout gbl_north_panel = new GridBagLayout();
		gbl_north_panel.columnWidths = new int[]{0, 0};
		gbl_north_panel.rowHeights = new int[]{0, 0};
		gbl_north_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_north_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		north_panel.setLayout(gbl_north_panel);
		
		table_panel = new JPanel();
		GridBagConstraints gbc_table_panel = new GridBagConstraints();
		gbc_table_panel.fill = GridBagConstraints.BOTH;
		gbc_table_panel.gridx = 0;
		gbc_table_panel.gridy = 0;
		north_panel.add(table_panel, gbc_table_panel);
		
		table = new JTable(new CopiesTableModel(library, book));
		createtableScrollPane(table_panel).setViewportView(table);
		
		button_panel = new JPanel();
		table_panel.add(button_panel, BorderLayout.NORTH);
		ButtonBarBuilder addRemoveCopy = new ButtonBarBuilder();
		addRemoveCopy.addButton(new RemoveAction(),new AddAction());
		button_panel.setLayout(new BorderLayout(0, 0));
		button_panel.add(addRemoveCopy.build(),BorderLayout.EAST);

		south_panel = new JPanel();
		GridBagConstraints gbc_south_panel = new GridBagConstraints();
		gbc_south_panel.fill = GridBagConstraints.BOTH;
		gbc_south_panel.gridx = 0;
		gbc_south_panel.gridy = 1;
		add(south_panel, gbc_south_panel);
		south_panel.setLayout(new BorderLayout(0, 0));
		
		ButtonBarBuilder buttonBar = new ButtonBarBuilder();

		buttonBar.addButton(new OkAction(), new CancelAction());
		south_panel.add(buttonBar.build(),BorderLayout.EAST);
	}
	private JScrollPane createtableScrollPane(JPanel panel_1) {
		table_panel.setLayout(new BorderLayout(0, 0));
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
			deletions.add(getSelectedCopy());
			library.removeCopy(getSelectedCopy());
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
			dispose();
			
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
			parent.dispose();
			
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
	private void dispose(){
		parent.dispose();
	}
}
