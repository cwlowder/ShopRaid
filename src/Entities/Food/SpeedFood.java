package Entities.Food;

import Entities.Entity;
import Entities.Player;
import interfaces.Direction;
import interfaces.Effects;

import java.awt.*;

/**
 * Created by Badtoasters on 6/18/2017.
 */
public class SpeedFood extends Item {
	@Override
	public void knockInto(Entity from, Direction colDir) {
		if (from instanceof Player) {
			if (getSpeed() < getMaxSpeed() * .4) {
				deSpawnEntity(this);

				((Player) from).addEffect(Effects.SPEEDUP, 3);
			}
		}
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public double getMaxSpeed() {
		return 30;
	}

	@Override
	public double getPoints() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 20;
	}

	@Override
	public int getHeight() {
		return 20;
	}
}
