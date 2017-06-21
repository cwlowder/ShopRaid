package interfaces;

/**
 * Created by Badtoasters on 6/17/2017.
 */
public interface Placeable {
	int getX();
	int getY();

	void setX(int x);
	void setY(int y);

	default void setPos(int x, int y) {
		setX(x);
		setY(y);
	}
}
