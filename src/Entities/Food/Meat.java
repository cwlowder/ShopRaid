package Entities.Food;

import Entities.Entity;
import Entities.Player;
import interfaces.Direction;

import java.awt.*;

/**
 * Created by Badtoasters on 6/18/2017.
 */
public class Meat extends Item {
	@Override
	public void knockInto(Entity from, Direction colDir) {
		if (from instanceof Player) {
			if (getSpeed() < getMaxSpeed() * .4) {
				deSpawnEntity(this);
				((Player)from).addItem(this);
			}
		}
	}

	@Override
	public int getWidth() {
		return 30;
	}

	@Override
	public int getHeight() {
		return 20;
	}

	@Override
	public double getPoints() {
		return 20;
	}

	@Override
	public Color getColor() {
		return Color.pink;
	}
}
