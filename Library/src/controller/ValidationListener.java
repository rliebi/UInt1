package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.jgoodies.validation.ValidationResultModel;

public class ValidationListener implements
PropertyChangeListener {
public void propertyChange(PropertyChangeEvent evt) {
String property = evt.getPropertyName();
if (ValidationResultModel.PROPERTY_RESULT.equals(property)) {
//	JOptionPane.showMessageDialog(null,
//			"At least one validation result changed");
} else if (ValidationResultModel.PROPERTY_MESSAGES.equals(property)) {
	if (Boolean.TRUE.equals(evt.getNewValue())) {
//		JOptionPane.showMessageDialog(null,
//				"Overall validation changed");
	}
}
}
}