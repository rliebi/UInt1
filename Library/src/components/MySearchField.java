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
	private int row_to_search=0;
	
	public MySearchField(JTable table_to_search){
		super("Search");
		this.table_to_search=table_to_search;
		setSorter();
	}
	public MySearchField(JTable table_to_search, int row_to_seach){
		this(table_to_search);
		this.row_to_search=row_to_seach;
	}

	private void setSorter() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final TableRowSorter<AbstractTableModel> sorter = new TableRowSorter(this.table_to_search.getModel()); 
		this.table_to_search.setRowSorter(sorter);	
		addKeyListener(sorter);
	}

	private void addKeyListener(final TableRowSorter<AbstractTableModel> sorter) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//newFilter();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				newFilter();
			}
			
			private void newFilter() {
				RowFilter<AbstractTableModel, Object> rf = null;
				//If current expression doesn't parse, don't update.
				try {
					rf = RowFilter.regexFilter("(?i)"+getText(), row_to_search);
				} catch (java.util.regex.PatternSyntaxException e) {
					return;
				}
				sorter.setRowFilter(rf);
			}
		});
	}

}
