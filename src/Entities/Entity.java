package Entities;

import Controllers.GameController;
import interfaces.Direction;
import interfaces.Placeable;
import interfaces.Sizable;

import java.awt.*;

/**
 * Created by Badtoasters on 6/17/2017.
 */
public abstract class Entity implements Placeable, Sizable {
	public static GameController gameController;
	private int x;
	private int y;

	private double maxSpeed = 10;
	private double xSpeed = 0;
	private double ySpeed = 0;
	private double accel = .5;
	private double deccel = .5;

	public abstract Color getColor();

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return 100;
	}

	@Override
	public int getHeight() {
		return 100;
	}

	public abstract void knockInto(Entity from, Direction colDir);

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;

		fixSpeed();
	}

	private void fixSpeed() {
		if(xSpeed > getMaxSpeed()) {
			xSpeed = getMaxSpeed();
		}
		if(xSpeed < -getMaxSpeed()) {
			xSpeed = -getMaxSpeed();
		}
		if(ySpeed > getMaxSpeed()) {
			ySpeed = getMaxSpeed();
		}
		if(ySpeed < -getMaxSpeed()) {
			ySpeed = -getMaxSpeed();
		}

		double speed = getSpeed();
		if(speed >= getMaxSpeed()) {
			double sRatio = getMaxSpeed() / speed;
			xSpeed *= sRatio;
			ySpeed *= sRatio;
		}
	}

	public double getSpeed() {
		return Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
	}

	public void addxSpeed(double xChange) {
		xSpeed += xChange;
		fixSpeed();
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
		fixSpeed();
	}

	public void addySpeed(double yChange) {
		ySpeed += yChange;
		fixSpeed();
	}

	public double getySpeed() {
		return ySpeed;
	}

	public double getAccel() {
		return accel;
	}

	public double getDeccel() {
		return deccel;
	}

	protected void spawnEntity(Entity thrown) {
		gameController.spawnEntity(thrown);
	}

	protected void deSpawnEntity(Entity destroyed) {
		gameController.deSpawnEntity(destroyed);
	}

	public abstract Entity getFuturePosition();
}
