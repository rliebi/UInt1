package components;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class MyJTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8665319028734514364L;

	public MyJTable() {
		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "none");
		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
						InputEvent.SHIFT_DOWN_MASK), "none");
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				//Print the Table width
				JTableHeader th = getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				for (int x = 0, y = tcm.getColumnCount(); x < y; x++) {
					 TableColumn tc = tcm.getColumn(x);
					 System.out.println("Column name = "+tc.getHeaderValue()+", width = "+tc.getWidth());
				}

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public MyJTable(TableModel dm) {
		super(dm);
		// TODO Auto-generated constructor stub
	}

	public MyJTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// TODO Auto-generated constructor stub
	}

	public MyJTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	public MyJTable(Vector<?> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public MyJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public MyJTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// TODO Auto-generated constructor stub
	}

}
