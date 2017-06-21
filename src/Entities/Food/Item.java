package Entities.Food;

import Entities.Entity;
import interfaces.Placeable;

import java.awt.*;

/**
 * Created by Badtoasters on 6/17/2017.
 */
public abstract class Item extends Entity {
	public Item() {

	}

	public double getPoints() {
		return 1;
	}

	@Override
	public Entity getFuturePosition() {
		Entity entity = new BoxFood();
		entity.setPos((int)(getX() + getxSpeed()), (int)(getY()+ getySpeed()));
		return entity;
	}
}
