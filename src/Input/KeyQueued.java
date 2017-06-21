package Input;

import java.awt.event.KeyEvent;

/**
 * Created by Badtoasters on 6/16/2017.
 */
public class KeyQueued {
	private KeyEvent event;

	public KeyQueued(KeyEvent event) {
		this.event = event;
	}

	public KeyEvent getEvent() {
		return event;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		KeyQueued keyQueued = (KeyQueued) o;

		return event != null ? event.getKeyChar()== keyQueued.event.getKeyChar() : keyQueued.event == null;
	}

	@Override
	public int hashCode() {
		return event != null ? event.getKeyChar() : 0;
	}
}
