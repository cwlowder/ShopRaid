package Entities.Food;

import Entities.Entity;
import Entities.Player;
import interfaces.Direction;
import interfaces.Effects;

import java.awt.*;

/**
 * Created by Badtoasters on 6/18/2017.
 */
public class TimeDrink extends CanGood {
	@Override
	public void knockInto(Entity from, Direction colDir) {
		if (from instanceof Player) {
			if (getSpeed() < getMaxSpeed() * .4) {
				deSpawnEntity(this);
				((Player) from).addTime(3);
			}
		}
	}

	@Override
	public double getMaxSpeed() {
		return 30;
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}
}
