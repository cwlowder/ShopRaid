package Controllers;

import Entities.Entity;
import Entities.Food.Item;
import Entities.Shelf;
import Input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Badtoasters on 6/16/2017.
 */
public class GraphicsController extends Canvas {
	private JFrame frame;
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;

	private GameController gameController;

	public GraphicsController(GameController gameController) {
		this.gameController = gameController;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(WIDTH,HEIGHT);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setVisible(true);
		addKeyListener(new Keyboard(gameController));
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(6);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//////
		g.setColor(Color.black);
		g.fillRect(0,0,10000,10000);

		for(Shelf shelf: gameController.getShelves()) {
			g.setColor(shelf.getColor());
			g.fillRect(shelf.getX(), shelf.getY(), shelf.getWidth(), shelf.getHeight());
		}

		for (Entity entity : gameController.getEntities()) {
			g.setColor(entity.getColor());
			g.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		}

		g.setColor(gameController.getPlayer().getColor());
		int x = gameController.getPlayer().getX();
		int y = gameController.getPlayer().getY();
		g.fillRect(x, y, gameController.getPlayer().getWidth(), gameController.getPlayer().getHeight());

		g.setColor(Color.BLACK);
		g.drawString("Score: "+gameController.getPlayer().getPoints(), 10,10);
		g.drawString("Time:  "+gameController.getTimeLeft(), 10,30);

		g.dispose();
		bs.show();
	}
}
