package Entities.Food;

import Entities.Entity;
import Entities.Player;
import interfaces.Direction;

import java.awt.*;

/**
 * Created by Badtoasters on 6/17/2017.
 */
public class CanGood extends Item {
	@Override
	public void knockInto(Entity from, Direction colDir) {
		if(from instanceof Player) {
			if (getSpeed() < getMaxSpeed() * .4) {
				deSpawnEntity(this);
				((Player)from).addItem(this);
			}
		}
	}

	@Override
	public double getMaxSpeed() {
		return 20;
	}

	@Override
	public int getWidth() {
		return 10;
	}

	@Override
	public int getHeight() {
		return 20;
	}

	@Override
	public double getDeccel() {
		return .1;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}
}
