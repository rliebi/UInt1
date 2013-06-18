package components;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import settings.Icons;

import domain.Copy;
import domain.Copy.Condition;

public class IconListCellRenderer extends JLabel implements ListCellRenderer<Object> {

	private static final long serialVersionUID = 6922884206824595356L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		ImageIcon icon = null;
		
		if (value instanceof Copy.Condition) {
			switch ((Condition) value) {
			case GOOD:
			case NEW:
			case DAMAGED:
				icon = Icons.IconEnum.CONDITION_GOOD.getIcon();
				break;
			case LOST:
			case WASTE:
				icon = Icons.IconEnum.CONDITION_BAD.getIcon();
				break;
			}
		}
		if (icon != null) setIcon(icon);
		setText(value.toString());

		return this;
	}

}
