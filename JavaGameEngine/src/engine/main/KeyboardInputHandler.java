package engine.main;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard input handler for the engine.
 * @author Taylor Houthoofd
 *
 */

public class KeyboardInputHandler implements KeyListener {
	
	private boolean[] keys;
	
	/**
	 * Constructor. Attaches this keyboard handler to a component.
	 * @param c Component to attach to.
	 */
	
	public KeyboardInputHandler(Component c) {
		c.addKeyListener(this);
		keys = new boolean[256];
	}
	
	/**
	 * Used to check if a key is down. Use the KeyEvent virtual keyboard for key codes.
	 * @param keyCode Key code to check if pressed.
	 * @return State of key.
	 */
	
	public boolean isKeyDown(int keyCode) {
		if(keyCode > 0 && keyCode < 256) {
			return keys[keyCode];
		}
		return false;
	}
	
	/**
	 * Stores the state of a key when pressed.
	 */

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = true;
		}
	}
	
	/**
	 * Stores the state of a key when pressed.
	 */

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = false;
		}
	}
	
	/**
	 * Not implemented.
	 */

	public void keyTyped(KeyEvent e) {
		
	}

}
