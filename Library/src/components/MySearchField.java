package components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class MySearchField extends MyJTextField{

	private static final long serialVersionUID = 4798414752488878629L;
	private final JTable table_to_search;
	private int rowToSort=0;
	
	public MySearchField(JTable table_to_search){
		this(table_to_search,0);
	}
	public MySearchField(JTable table_to_search, int rowToSort){
		super("Search");
		this.table_to_search=table_to_search;
		this.rowToSort=rowToSort;
		setSorter();
	}


	private void setSorter() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final TableRowSorter<AbstractTableModel> sorter = new TableRowSorter(this.table_to_search.getModel()); 
		this.table_to_search.setRowSorter(sorter);	
		addKeyListener(sorter);
		sorter.toggleSortOrder(rowToSort);
		sorter.setSortsOnUpdates(true);
	}

	private void addKeyListener(final TableRowSorter<AbstractTableModel> sorter) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				newFilter();
			}
			
			private void newFilter() {
				RowFilter<AbstractTableModel, Object> rf = null;
				try {
					rf = RowFilter.regexFilter("(?i)"+getText());
				} catch (java.util.regex.PatternSyntaxException e) {
					return;
				}
				sorter.setRowFilter(rf);
			}
		});
	}

}
