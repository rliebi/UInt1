package controll;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import components.MyJTable;
import domain.Setting;

public class TableSelectListener implements ListSelectionListener {
	 private JTable table;
	 private JButton button;
	 private String buttonText;
	 private boolean useMaxValue;
	 
	 public TableSelectListener(MyJTable table, JButton button, String buttonText, boolean useMaxValue) {
		 super();
		 this.table = table;
		 this.button = button;
		 this.buttonText = buttonText;
		 this.useMaxValue = useMaxValue;

	 }
	 
	 public TableSelectListener(JTable table, JButton button, String buttonText, boolean useMaxValue) {
		 super();
		 this.table = table;
		 this.button = button;
		 this.buttonText = buttonText;
		 this.useMaxValue = useMaxValue;

	 }
	 

	 @Override
	 public void valueChanged(ListSelectionEvent e) {
		 if (!e.getValueIsAdjusting()) {
			 if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
				 String text = buttonText;
				 if (table.getSelectedRowCount() > 0) {
					 button.setEnabled(true);
					 if (useMaxValue) {
						 if (table.getSelectedRowCount() > Setting.getMaxOpenDetail()) {
							 button.setEnabled(false);
							 
						 } else {
							 button.setEnabled(true);
						 }
					 }
					 text += " (" + table.getSelectedRowCount() + ")";
				 } else {
					 button.setEnabled(false);
				 }
				 button.setText(text);
			 }
		 }
	 }
}
