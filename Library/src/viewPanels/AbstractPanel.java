package viewPanels;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import views.MasterView;

import components.MySearchField;

import domain.Library;

public abstract class AbstractPanel extends JPanel implements Observer, ComponentListener {
	 
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void componentShown ( ComponentEvent e )
    {
//    	MasterView.setWindowOpen(this);
    	setSearchField();
    }
	@Override
    public void componentHidden ( ComponentEvent e )
    {
//       MasterView.removeWindow(this);
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 5275081448468061576L;
	public AbstractPanel() {
		super();

	}
	protected Library library;
	protected MySearchField searchfield;
	public void setSearchField(){
		MasterView.setSearchfield(searchfield);
	}
	
	

}
