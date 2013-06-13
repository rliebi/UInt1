package views;


import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;

public abstract class AbstractViewer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133936580146906508L;

	public AbstractViewer() {
		MasterView.setWindowOpen();
		setFrontWindow();
		this.addWindowListener(new WindowListener() {
			
			
			@Override
			public void windowClosing(WindowEvent e) {


			}
			
			@Override
			public void windowClosed(WindowEvent e) {

				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				init_last();	
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				MasterView.setWindowClose();
				MasterView.setFrontWindow(null);
				
			}
		});

	}
	public void init_last(){
		this.setLocationRelativeTo(MasterView.getInstance().getFrame());


	}
	
	protected void createWindow() {
		this.setMinimumSize(new Dimension(900, 500));
		this.setLocationRelativeTo(MasterView.getInstance().getFrame());
		this.setTitle("Edit Book");
		this.setVisible(true);
	}
	
	public void setFrontWindow(){
		MasterView.setFrontWindow(this);
		
	}
	
	public void unsetFrontWindow(){
		
	}

	
}
