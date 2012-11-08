/*******************************************************************************
 * Copyright 2012 Davy Hélard
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

import android.graphics.Rect;
import android.phy.core.word.World;

/**
 * Base abstract implementation for <code>Wall</code>.
 * Access to bricks and fragments are implemented.
 * You may need to implement a {@link BrickFactory} and override some getters to make their return type correspond to your bricks and fragments.
 * This allow to custom the data and behaviors of the wall while using a common interface.
 * 
 * @author Davy
 */
public abstract class AbstractWall implements Wall
{
	private BrickFactory brickFactory;
	private ArrayList<ArrayList<Brick>> bricks;
	private World world;
	private Rect bounds;
	
	/**
	 * Create a wall
	 * @param brickFactory the factory used for building bricks
	 * @param world the world containing the wall
	 * @param bounds the bounds of the wall
	 */
	public AbstractWall(BrickFactory brickFactory, World world, Rect bounds)
	{
		this.world = world;
		this.brickFactory = brickFactory;
		this.bounds = bounds;
		
		bricks = new ArrayList<ArrayList<Brick>>(bounds.height());
		for (int y = 0; y < bounds.height(); y++)
		{
			ArrayList<Brick> row = new ArrayList<Brick>(bounds.height());
			bricks.add(row);
			for (int x = 0; x < bounds.width(); x++)
			{
				row.add(brickFactory.creatBrick(this, x, y));
			}
		}
	}
	
	/**
	 * Return the factory used for building brick fragments
	 * @return the factory used for building brick fragments
	 */
	public BrickFactory getBrickFactory()
	{
		return brickFactory;
	}
	
	/**
	 * Change the bounds of the wall.
	 * Bricks can be destroyed or created to match the new size.
	 * @param bounds the new bounds of the wall
	 */
	public void setBounds(Rect bounds)
	{
		if (! this.bounds.equals(bounds))
		{
			// change y bounds
			for (int y = this.bounds.height(); y < bounds.height(); y++)
			{
				ArrayList<Brick> row = new ArrayList<Brick>(bounds.width());
				bricks.add(row);
				for (int x = 0; x < this.bounds.width(); x++)
				{
					row.add(brickFactory.creatBrick(this, x, y));
				}
			}
			for (int y = bounds.height(); y < this.bounds.height(); y++)
			{
				bricks.remove(bricks.size());
			}
			
			// change x bounds
			for (int y = 0; y < bounds.height(); y++)
			{
				ArrayList<Brick> row = bricks.get(y);
				
				for (int x = this.bounds.width(); x < bounds.width(); x++)
				{
					row.add(brickFactory.creatBrick(this, x, y));
				}
				for (int x = bounds.width(); x < this.bounds.width(); x++)
				{
					row.remove(row.size());
				}
			}
		}
		this.bounds = bounds;
	}
	
	@Override
	public World getWorld()
	{
		return world;
	}

	@Override
	public int getFragmentX(Fragment fragment)
	{
		return 3 * fragment.getBrick().getX() + fragment.getLocation().getDeltaX();
	}
	
	@Override
	public int getFragmentY(Fragment fragment)
	{
		return 3 * fragment.getBrick().getY() + fragment.getLocation().getDeltaY();
	}
	
	@Override
	public Fragment getFragment(int fragmentX, int fragmentY)
	{
		Fragment fragment;
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
	public Brick get(int x, int y)
	{
		return bricks.get(y - bounds.top).get(x - bounds.left);
	}
	
	@Override
	public Rect getBounds()
	{
		return bounds;
	}
}
