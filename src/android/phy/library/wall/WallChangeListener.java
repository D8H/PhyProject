package android.phy.library.wall;

import java.util.EventListener;

public interface WallChangeListener<E> extends EventListener
{
	public void wallChanged(WallEvent<E> wallEvent);
}
