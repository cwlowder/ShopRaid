package Entities;

import Entities.Food.Item;
import interfaces.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Badtoasters on 6/18/2017.
 */
public class Enemy extends Entity {
	private List<Item> items;
	public Enemy( int x, int y) {
		setX(x);
		setY(y);
		items = new ArrayList<>();
	}

	public void tick(){
		int xDiff = gameController.getPlayer().getX() - getX();
		int yDiff = gameController.getPlayer().getY() - getY();

		if(xDiff > 0) {
			//setxSpeed(1);
			addxSpeed(getAccel());
		}
		else if (xDiff < 0) {
			//setxSpeed(-1);
			addxSpeed(-getAccel());
		}

		if(yDiff > 0) {
			//setySpeed(1);
			addySpeed(getAccel());
		}
		else if (yDiff < 0) {
			//setySpeed(-1);
			addySpeed(-getAccel());
		}
	}

	@Override
	public double getDeccel() {
		return .3;
	}

	@Override
	public double getAccel() {
		return .5;
	}

	@Override
	public int getWidth() {
		return 80;
	}

	@Override
	public int getHeight() {
		return 40;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public void knockInto(Entity from, Direction colDir) {

	}

	@Override
	public Entity getFuturePosition() {
		Entity entity = new Player((int)(getX() + getxSpeed()), (int)(getY()+ getySpeed()));

		return entity;
	}
}
