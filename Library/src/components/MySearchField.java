package components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import localization.Messages;

public class MySearchField extends MyJTextField{

	private static final long serialVersionUID = 4798414752488878629L;
	private final JTable table_to_search;
	private int rowToSort=0;
	private List<RowFilter<Object, Object>> filters;
	private int currentfilter = -1;
	
	public MySearchField(JTable table_to_search, int rowToSort, List<RowFilter<Object,Object>> filters_to_apply){
		super(Messages.getString("Global.Search"));
		this.table_to_search=table_to_search;
		this.rowToSort=rowToSort;
		this.filters = filters_to_apply;
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
				RowFilter<Object, Object> rf = null;
				try {

					rf = RowFilter.regexFilter("(?i)"+getText());
					if (currentfilter==-1)
					{
						filters.add(rf);  
						currentfilter = filters.indexOf(rf);

					}
					else
					{

						filters.set(currentfilter, rf);
					}
	                RowFilter<Object,Object> serviceFilter = RowFilter.andFilter(filters); 
					sorter.setRowFilter(serviceFilter);
				} catch (java.util.regex.PatternSyntaxException e) {
					return;
				}
				try {
					if (table_to_search.getSelectedRowCount() <1)
					table_to_search.setRowSelectionInterval(0, 0);
				} catch (Exception e) {
					
				}

			}
		});
	}

}
