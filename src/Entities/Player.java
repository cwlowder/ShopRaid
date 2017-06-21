package Entities;

import Controllers.GameController;
import Entities.Food.Item;
import interfaces.Direction;
import interfaces.Effects;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Badtoasters on 6/16/2017.
 */
public class Player extends Entity {
	public GameController gameController;
	private List<Item> items;
	Map<Effects, Long> activeEffects = new HashMap<>();

	public Player( int x, int y) {
		setX(x);
		setY(y);
		items = new ArrayList<>();
	}

	public void tick() {
		Long current = System.currentTimeMillis();
		for (Effects effects: activeEffects.keySet()) {
			if(activeEffects.get(effects) < current) {
				System.out.println(current + " " + activeEffects.get(effects));
				activeEffects.remove(effects);
			}
		}
	}

	@Override
	public int getHeight() {
		return 40;
	}

	@Override
	public Color getColor() {
		if(activeEffects.containsKey(Effects.SPEEDUP)) {
			return Color.GREEN;
		}
		else {
			return Color.ORANGE;
		}
	}

	@Override
	public int getWidth() {
		return 80;
	}

	@Override
	public void knockInto(Entity from, Direction colDir) {

	}

	@Override
	public double getMaxSpeed() {
		if(activeEffects.containsKey(Effects.SPEEDUP)) {
			return super.getMaxSpeed()*2;
		}
		return super.getMaxSpeed();
	}

	public double getOrigMaxSpeed() {
		return super.getMaxSpeed();
	}

	@Override
	public double getDeccel() {
		return .3;
	}

	@Override
	public double getAccel() {
		return .6;
	}

	@Override
	public Entity getFuturePosition() {
		Entity entity = new Player((int)(getX() + getxSpeed()), (int)(getY()+ getySpeed()));

		return entity;
	}

	public double getPoints() {
		double points = 0;

		for (Item item:items) {
			points += item.getPoints();
		}

		return points;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void addTime(int t) {
		gameController.addTime(t*1000);
	}

	public void addEffect(Effects speedup, int durration) {
		activeEffects.put(speedup, activeEffects.getOrDefault(speedup, System.currentTimeMillis()) + durration*1_000L);
	}

	public void restock() {
		gameController.restock();
	}
}
