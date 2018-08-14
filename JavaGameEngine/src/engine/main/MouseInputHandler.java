package engine.main;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

/**
 * 
 * TODO: Do something with this. Doesn't currently do anything but get attached to a component.
 *
 */

public class MouseInputHandler implements MouseInputListener {

	public MouseInputHandler(Component c) {
		c.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

}
