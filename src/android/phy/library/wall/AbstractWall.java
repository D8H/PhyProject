/*******************************************************************************
 * Copyright 2012, 2013 Davy Hélard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package android.phy.library.wall;

import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Rect;

/**
 * Base abstract implementation for <code>Wall</code>.
 * Fragments access is implemented, but access to brick need to be implemented.
 * You may need to implement a {@link BrickFactory}.
 * This allow to custom the data and behaviors of the wall while using a common interface.
 * 
 * @author Davy
 */
public abstract class AbstractWall<E> implements Wall<E>
{
	private final BrickFactory<E> brickFactory;
	private Rect bounds;
	
	private final ArrayList<WallChangeListener<E>> changeListeners = new ArrayList<WallChangeListener<E>>();
	
	
	/**
	 * Create a wall
	 * @param brickFactory the factory used for building bricks
	 * @param bounds the bounds of the wall
	 */
	public AbstractWall(BrickFactory<E> brickFactory, Rect bounds)
	{
		this.brickFactory = brickFactory;
		this.bounds = bounds;
	}
	
	@Override
	public abstract Brick<E> get(int x, int y);
	
	/**
	 * Return the factory used for building brick fragments
	 * @return the factory used for building brick fragments
	 */
	public BrickFactory<E> getBrickFactory()
	{
		return brickFactory;
	}
	
	@Override
	public int getFragmentX(Fragment<E> fragment)
	{
		return 3 * fragment.getBrick().getX() + fragment.getLocation().getDeltaX();
	}
	
	@Override
	public int getFragmentY(Fragment<E> fragment)
	{
		return 3 * fragment.getBrick().getY() + fragment.getLocation().getDeltaY();
	}
	
	@Override
	public Fragment<E> getFragment(int fragmentX, int fragmentY)
	{
		Fragment<E> fragment;
		int brickX = fragmentX / 3;
		int brickY = fragmentY / 3;
		int deltaX = fragmentX % 3;
		int deltaY = fragmentY % 3;
		Brick.FragmentLocation foundFragmentLocation = null;
		Brick.FragmentLocation[] fragments = Brick.FragmentLocation.values();
		int fragmentIndex = 0;
		while(fragmentIndex < fragments.length)
		{
			Brick.FragmentLocation fragmentLocation = fragments[fragmentIndex];
			if (deltaX == fragmentLocation.getDeltaX()
		     && deltaY == fragmentLocation.getDeltaY())
		    {
				foundFragmentLocation = fragmentLocation;
		    }
			fragmentIndex++;
		}
		if (foundFragmentLocation == null)
		{
			throw new UnconsistentFragmentIndexException(fragmentX, fragmentY);
		}
		fragment = get(brickX, brickY).getFragment(foundFragmentLocation);
		return fragment;
	}
	
	@Override
	public Rect getBounds()
	{
		return bounds;
	}
	
	@Override
	public void addWallChangeListener(WallChangeListener<E> listener)
	{
		changeListeners.add(listener);
	}
	
	@Override
	public void removeWallChangeListener(WallChangeListener<E> listener)
	{
		changeListeners.remove(listener);
	}
	
	/**
	 * @return
	 */
	public Collection<WallChangeListener<E>> getWallChangeListeners()
	{
		return changeListeners;
	}
	
	@Override
	public void fireWallFragmentChanged(Fragment<E> fragment, E previousContent)
	{
		if (changeListeners.size() > 0)
		{
			WallEvent<E> event = new WallEvent<E>(this, fragment, previousContent);
			for (WallChangeListener<E> listener : changeListeners)
			{
				listener.wallChanged(event);
			}
		}
	}

}
