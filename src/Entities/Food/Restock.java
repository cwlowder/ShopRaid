package Entities.Food;

import Entities.Entity;
import Entities.Player;
import interfaces.Direction;

import java.awt.*;

/**
 * Created by Badtoasters on 6/18/2017.
 */
public class Restock extends CanGood {
	@Override
	public void knockInto(Entity from, Direction colDir) {
		if (from instanceof Player) {
			if (getSpeed() < getMaxSpeed() * .4) {
				deSpawnEntity(this);
				((Player) from).restock();
				((Player) from).addTime(10);
			}
		}
	}

	@Override
	public int getWidth() {
		return 15;
	}

	@Override
	public int getHeight() {
		return 15;
	}

	@Override
	public double getDeccel() {
		return .4;
	}

	@Override
	public double getMaxSpeed() {
		return 30;
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}
}
