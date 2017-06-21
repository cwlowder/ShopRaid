package Entities;

import Entities.Food.*;
import interfaces.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Badtoasters on 6/17/2017.
 */
public class Shelf extends Entity {
	private List<Item> items;

	public Shelf() {
		Random r = new Random();
		items = new ArrayList<>();
		stockShelf(r);
	}

	public void stockShelf(Random r) {
		items = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			int prob = r.nextInt(200);
			if(prob < 110) {
				items.add(new CanGood());
			}
			else if(prob < 165) {
				items.add(new BoxFood());
			}
			else if(prob < 190) {
				items.add(new Meat());
			}
			else {
				int special = r.nextInt(20);
				if (special < 10) {
					items.add(new TimeDrink());
				}
				else if (special < 19){
					items.add(new SpeedFood());
				}
				else {
					items.add(new Restock());
				}
			}
		}
	}

	@Override
	public double getxSpeed() {
		return 0;
	}

	@Override
	public double getySpeed() {
		return 0;
	}

	@Override
	public double getAccel() {
		return 0;
	}

	@Override
	public Color getColor() {
		if (items.isEmpty()) {
			return Color.GRAY;
		}
		return Color.white;
	}

	@Override
	public int getWidth() {
		return 100;
	}

	@Override
	public int getHeight() {
		return 100;
	}

	@Override
	public void knockInto(Entity from, Direction colDir) {
		Random random = new Random();

		if(from instanceof Player){
			if(from.getSpeed() > ((Player)from).getOrigMaxSpeed()*.8) {
				for (int i = 0; i < 1+random.nextInt(8); i++) {
					if(items.size() > 0) {
						Item thrown = items.get(0);
						items.remove(0);
						int xDir = -1;
						int yDir = -1;
						int xTrans = from.getWidth()/2;
						int yTrans = from.getHeight()/2;
						if(colDir == Direction.LEFT) {
							yDir = 1;
							xTrans = 0;
						}
						else if (colDir == Direction.RIGHT) {
							yDir = 1;
							xTrans = from.getWidth() - thrown.getWidth();
						}
						else if (colDir == Direction.DOWN) {
							xDir = 1;
							yTrans = from.getHeight() - thrown.getHeight();
						}
						else if (colDir == Direction.UP) {
							xDir = 1;
							xTrans= 0;
						}

						thrown.setX(from.getX() + xTrans);
						thrown.setY(from.getY() + yTrans);
						double xSrandom = xDir*random.nextDouble()*5;
						thrown.setxSpeed(xDir*from.getxSpeed() * 1.5 + xSrandom);

						double ySrandom = xDir*random.nextDouble()*5;
						thrown.setySpeed(yDir*from.getySpeed() * 1.5 + ySrandom);

						from.spawnEntity(thrown);
					}
				}
			}
		}
	}

	@Override
	public Entity getFuturePosition() {
		return this;
	}
}
