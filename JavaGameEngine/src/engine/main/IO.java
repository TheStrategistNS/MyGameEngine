package engine.main;

import javax.swing.JOptionPane;

public class IO {
	

	public static String promptForInput(String message) {
		String input;
		input = JOptionPane.showInputDialog(message);
		JavaEngine.getKeyboardInput().reset();
		return input;
	}
}
