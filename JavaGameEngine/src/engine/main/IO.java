package engine.main;

import javax.swing.JOptionPane;

/**
 * Made for getting information from the player. Still very much under construction.
 * @author Taylor Houthoofd
 *
 */

public class IO {
	//TODO: Add more functionality.
	
	/**
	 * Shows a message box that prompts the player with a message. Resets key controls because modal nature of dialog boxes can mess up keys.
	 * @param message prompt to display
	 * @return user input
	 */
	public static String promptForInput(String message) {
		String input;
		input = JOptionPane.showInputDialog(message);
		JavaEngine.getKeyboardInput().reset();
		return input;
	}
}
