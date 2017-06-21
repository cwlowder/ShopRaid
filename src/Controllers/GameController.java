package Controllers;

import Entities.Entity;
import Entities.Player;
import Entities.Shelf;
import Entities.Enemy;
import Input.KeyQueued;
import interfaces.Direction;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Badtoasters on 6/16/2017.
 */
public class GameController {
	private Player player;
	private List<Shelf> shelves = new ArrayList<>();
	List<Entity> entities;
	List<Entity> entitiesRemoved;
	private Long timeEnd;

	public GameController() {
		Entity.gameController= this;
		entities = new ArrayList<>();
		entitiesRemoved = new ArrayList<>();

		player = new Player(400,400);
		player.gameController = this;

		Enemy enemy = new Enemy(400, 600);
		entities.add(enemy);

		for (int i = 0; i < 10; i++) {
			Shelf one = new Shelf();
			one.setPos(-50, 100*i-50);
			shelves.add(one);
			Shelf two = new Shelf();
			two.setPos(900, 100*i-50);
			shelves.add(two);
		}

		for (int i = 1; i < 10; i++) {
			Shelf one = new Shelf();
			one.setPos(100*i-50, -50);
			shelves.add(one);
			Shelf two = new Shelf();
			two.setPos(100*i-50, 900);
			shelves.add(two);
		}

		for (int i = 0; i < 5; i++) {
			Shelf one = new Shelf();
			one.setPos(200, 100*i+300);
			shelves.add(one);
			Shelf two = new Shelf();
			two.setPos(600, 100*i+300);
			shelves.add(two);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void tick() {
		getPlayer().tick();
		if(timeEnd == null) {
			timeEnd = System.currentTimeMillis() + 30*1000;
		}

		if(getTimeLeft() < 0) {
			return;
		}

		boolean collide = collideTick();

		if(!collide) {
			setNextPos(player);
			if (!keyQueuedList.isEmpty()) {
				keyTick();
			} else {
				calcNextSpeed(player);
			}
		}
		else {
			handleCollision(player);
		}

		for (Entity entity:entities) {
			setNextPos(entity);
			calcNextSpeed(entity);
		}

		tickRemoveEntities();

		for (Entity entity:entities) {
			if (entity instanceof Enemy) {
				((Enemy) entity).tick();
			}
		}
	}

	private boolean collideTick() {
		for (Entity entity: entities) {
			for (Shelf shelf: shelves) {
				Direction colDir = collide(entity, shelf);
				if(colDir != Direction.NONE) {
					entity.knockInto(shelf, colDir);
					handleCollision(entity);
				}
			}
		}

		// handle player collisions
		boolean collide = false;

		for(Shelf shelf: shelves) {
			Direction colDir = collide(getPlayer(), shelf);
			if(colDir != Direction.NONE) {
				shelf.knockInto(player, colDir);
				collide = true;
			}
		}

		for (Entity entity: entities) {
			Direction colDir = collide(entity, player);
			if(colDir != Direction.NONE) {
				entity.knockInto(player, colDir);
			}
		}
		return collide;
	}


	private void handleCollision(Entity collided) {
		int diffX = collided.getxSpeed()!=0?(collided.getxSpeed() < 0?1:-1):0;
		int diffY = collided.getySpeed()!=0?(collided.getySpeed() < 0?1:-1):0;
		collided.setX(collided.getX() + diffX);
		collided.setY(collided.getY() + diffY);
		collided.setxSpeed( -.5*collided.getxSpeed());
		collided.setySpeed( -.5*collided.getySpeed());
	}

	private void tickRemoveEntities() {
		if(!entitiesRemoved.isEmpty()) {
			for (Entity removed: entitiesRemoved) {
				entities.remove(removed);
			}
		}
	}

	private void setNextPos(Entity entity) {
		entity.setX((int) (entity.getX() + entity.getxSpeed()));
		entity.setY((int) (entity.getY() + entity.getySpeed()));
	}

	private void calcNextSpeed(Entity entity) {
		if (entity.getxSpeed() > 0) {
			entity.addxSpeed(-entity.getDeccel());
			if(entity.getxSpeed() < 0){
				entity.setxSpeed(0);
			}
		} else if (entity.getxSpeed() < 0) {
			entity.addxSpeed(entity.getDeccel());
			if(entity.getxSpeed() > 0){
				entity.setxSpeed(0);
			}
		}
		if (entity.getySpeed() > 0) {
			entity.addySpeed(-entity.getDeccel());
			if(entity.getySpeed() < 0){
				entity.setySpeed(0);
			}
		} else if (entity.getySpeed() < 0) {
			entity.addySpeed(entity.getDeccel());
			if(entity.getySpeed() > 0){
				entity.setySpeed(0);
			}
		}
	}

	public Direction collide(Entity bouncer, Entity wall) {
		bouncer = bouncer.getFuturePosition();
		wall = wall.getFuturePosition();
		boolean UL = false, UR = false, BL = false, BR = false;

		// checks UL corner
		if(bouncer.getX() > wall.getX()) {
			if(bouncer.getY() > wall.getY()) {
				if(bouncer.getX() < wall.getX()+wall.getWidth()) {
					if(bouncer.getY() < wall.getY()+wall.getHeight()) {
						UL = true;
					}
				}
			}
		}

		// checks UR corner
		if(bouncer.getX()+bouncer.getWidth() > wall.getX()) {
			if(bouncer.getY() > wall.getY()) {
				if(bouncer.getX()+bouncer.getWidth() < wall.getX()+wall.getWidth()) {
					if(bouncer.getY() < wall.getY()+wall.getHeight()) {
						UR = true;
					}
				}
			}
		}

		// checks BL corner
		if(bouncer.getX() > wall.getX()) {
			if(bouncer.getY()+bouncer.getHeight() > wall.getY()) {
				if(bouncer.getX() < wall.getX()+wall.getWidth()) {
					if(bouncer.getY()+bouncer.getHeight() < wall.getY()+wall.getHeight()) {
						BL =true;
					}
				}
			}
		}

		// checks BR corner
		if(bouncer.getX()+bouncer.getWidth() > wall.getX()) {
			if(bouncer.getY()+bouncer.getHeight() > wall.getY()) {
				if(bouncer.getX() < wall.getX()+wall.getWidth()) {
					if(bouncer.getY()+bouncer.getHeight() < wall.getY()+wall.getHeight()) {
						BR = true;
					}
				}
			}
		}
		if(BL || BR || UL || UR) {
			if(BL) {
				if (UL) {
					return Direction.LEFT;
				}
				else if(BR) {
					return Direction.DOWN;
				}
			}
			else if (UR) {
				if (UL) {
					return Direction.UP;
				}
				if (BR) {
					return Direction.RIGHT;
				}
			}
			return Direction.UNSURE;
		}

		return Direction.NONE;
	}

	public final Object keyQueuedGate = new Object();

	public void keyTick() {
		synchronized (keyQueuedGate) {
			for (KeyQueued keyQueued : keyQueuedList) {
				switch (keyQueued.getEvent().getKeyCode()) {
					case KeyEvent.VK_W:
						player.addySpeed(-player.getAccel());
						break;
					case KeyEvent.VK_S:
						player.addySpeed(player.getAccel());
						break;
					case KeyEvent.VK_D:
						player.addxSpeed(player.getAccel());
						break;
					case KeyEvent.VK_A:
						player.addxSpeed(-player.getAccel());
						break;
				}
			}
		}
	}

	private List<KeyQueued> keyQueuedList = new ArrayList<>();

	public void addKeyQueud(KeyQueued keyQueued) {
		synchronized (keyQueuedGate) {
			if (!keyQueuedList.contains(keyQueued)) {
				keyQueuedList.add(keyQueued);
			}
		}
	}

	public void removeKeyQueued(KeyQueued keyQueued) {
		synchronized (keyQueuedGate) {
			keyQueuedList.remove(keyQueued);
		}
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void spawnEntity(Entity thrown) {
		entities.add(thrown);
	}

	public void deSpawnEntity(Entity destroyed) {
		entitiesRemoved.add(destroyed);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public int getTimeLeft() {
		if(timeEnd != null) {
			int timeLeft = (int) ((timeEnd - System.currentTimeMillis())/1000);
			if(timeLeft >= 0) {
				return timeLeft;
			}
			else {
				return -1;
			}
		}
		else {
			return 0;
		}
	}

	public void addTime(int t) {
		timeEnd += t;
	}

	public void restock() {
		Random r = new Random();
		for (Shelf shelf: getShelves()) {
			shelf.stockShelf(r);
		}
	}
}