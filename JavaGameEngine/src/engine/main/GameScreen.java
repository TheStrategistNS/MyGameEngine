package engine.main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * The game screen.
 * @author Taylor Houthoofd
 *
 */

public class GameScreen extends JFrame {
	
	private static final long serialVersionUID = 4944507058383316306L;
	
	private Insets insets;
	
	/**
	 * Sets up the game screen. Readjusts size to accommodate inserts.
	 * @param width Width of game screen.
	 * @param height Height of game screen.
	 * @param name Name of game.
	 */
	
	public GameScreen(int width, int height, String name) {
		this.setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle(name);
		this.setVisible(true);
		this.addWindowListener(new windowListener());
		
		setLayout(null);
		
		insets = this.getInsets();
		setSize(insets.left + width + insets.right, insets.top + height + insets.bottom);
	}
	
	/**
	 * Adds the given component to the screen.
	 * @param c component
	 */
	
	public void addComponent(Component c) {
		if(c != null) {
			c.setLocation(c.getLocation().x + insets.left, c.getLocation().y + insets.top);
			this.add(c);
		}
	}
	
	/**
	 * Draws the backbuffer to the screen.
	 * @param backbuffer image to draw.
	 */
	
	public void render(BufferedImage backbuffer) {
		
		Graphics g = this.getGraphics();
		g.drawImage(backbuffer, insets.left, insets.top, this);
	}
	
	private class windowListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			JavaEngine.exit();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
