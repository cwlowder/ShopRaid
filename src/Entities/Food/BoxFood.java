package Entities.Food;

import Entities.Entity;
import Entities.Player;
import interfaces.Direction;

import java.awt.*;

/**
 * Created by Badtoasters on 6/18/2017.
 */
public class BoxFood extends Item {
	@Override
	public void knockInto(Entity from, Direction colDir) {
		if(from instanceof Player) {
			if (getSpeed() < getMaxSpeed() * .2) {
				deSpawnEntity(this);
				((Player)from).addItem(this);
			}
		}
	}

	@Override
	public double getPoints() {
		return 5;
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}

	@Override
	public double getMaxSpeed() {
		return 15;
	}

	@Override
	public int getWidth() {
		return 20;
	}

	@Override
	public int getHeight() {
		return 40;
	}

	@Override
	public double getDeccel() {
		return .5;
	}

}
