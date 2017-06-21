import Controllers.GameController;
import Controllers.GraphicsController;

/**
 * Created by Badtoasters on 6/16/2017.
 */
public class main {
	private static GraphicsController graphicsController;
	private static GameController gameController;
	public static void main(String[] args) {
		gameController = new GameController();
		graphicsController = new GraphicsController(gameController);

		run();
	}

	public static void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1_000_000_000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (true) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			graphicsController.render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
	}

	public static void tick() {
		gameController.tick();
	}
}
