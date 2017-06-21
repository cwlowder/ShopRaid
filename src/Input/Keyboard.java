package Input;

import Controllers.GameController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Badtoasters on 6/16/2017.
 */
public class Keyboard extends KeyAdapter {
	private GameController gameController;
	public Keyboard(GameController gameController) {
		super();
		this.gameController = gameController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gameController.addKeyQueud(new KeyQueued(e));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameController.removeKeyQueued(new KeyQueued(e));
	}
}
