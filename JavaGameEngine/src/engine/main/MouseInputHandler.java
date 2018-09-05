package engine.main;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

/**
 * Mouse input handler for the Java engine. When mouse clicks on the screen, sends a mouse event to any GameObjects 
 * clicked on that are set to receive mouse events.
 * @author Taylor Houthoofd
 *
 */

public class MouseInputHandler implements MouseInputListener {
	JavaEngine game;
	ObjectController controller;
	JFrame frame;
	
	/**
	 * Constructor.
	 * @param c the game screen
	 * @param game the JavaEngine object
	 */

	public MouseInputHandler(JFrame c, JavaEngine game) {
		frame = c;
		c.addMouseListener(this);
		this.game = game;
		controller = game.getObjectController();
	}
	
	public void mouseClicked(MouseEvent e) {
		Point p = new Point((e.getX() - frame.getInsets().left) / (int)game.getXRatio(), (e. getY() - frame.getInsets().top) / (int)game.getYRatio());
		
		System.out.printf("MouseClicked event at (%d, %d)\n",p.x, p.y);
		GameObject[] objects = controller.getGameObjects("MouseInteract");
		for(GameObject obj: objects) {
			System.out.printf("Testing object at (%d, %d)\n",obj.getPos().x, obj.getPos().y);
			if(obj.isTouching(p)) {
				GameEvent click = new GameEvent(GameEvent.MOUSE_EVENT);
				click.put("point", p);		
				click.put("x", p.x);
				click.put("y", p.y);
				obj.triggerEvent(null, click);
			}
		}
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
