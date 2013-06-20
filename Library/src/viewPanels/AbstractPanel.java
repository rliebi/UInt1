package viewPanels;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Observer;

import javax.swing.JPanel;
import views.MasterView;

import components.MySearchField;

import domain.Library;

public abstract class AbstractPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5275081448468061576L;

	public AbstractPanel() {
		super();
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				setSearchField();				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				
			}
		});
	}

	protected Library library;
	protected MySearchField searchfield;

	public void setSearchField() {
		MasterView.setSearchfield(searchfield);
	}

}
