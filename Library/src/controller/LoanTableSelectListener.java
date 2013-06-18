package controller;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoanTableSelectListener implements ListSelectionListener {
	private JTable table;
	private JButton button;
	private String searchFor;
	boolean deleteCopy;

	public LoanTableSelectListener(JTable table, JButton button,
			String searchFor, boolean deleteCopy) {
		this(table, button, searchFor);
		this.deleteCopy = deleteCopy;
	}

	public LoanTableSelectListener(JTable table, JButton button,
			String searchFor) {
		super();
		this.table = table;
		this.button = button;
		this.searchFor = searchFor;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (!arg0.getValueIsAdjusting()) {
			if (arg0.getSource() == table.getSelectionModel()
					&& table.getRowSelectionAllowed()) {

				button.setEnabled(false);
				if (table.getSelectedRowCount() == 1 || deleteCopy) {
					if (table.getValueAt(table.getSelectedRow(), 1).toString()
							.startsWith(searchFor) != deleteCopy) {
						button.setEnabled(true);
					} else {
						button.setEnabled(false);
					}
				}
			}
		}
	}

}