/*******************************************************************************
 * Copyright 2012, 2013 Davy H�lard
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

import android.phy.library.wall.Brick.FragmentLocation;

/**
 * Base abstract implementation for <code>Fragment</code>.
 * 
 * @author Davy
 */
public abstract class AbstractFragment<E> implements Fragment<E>
{
	private Brick<E> brick;
	private Brick.FragmentLocation location;
	
	
	/**
	 * Create a fragment
	 * @param brick the brick containing the fragment
	 * @param location the location of the fragment in the brick
	 */
	public AbstractFragment(Brick<E> brick, FragmentLocation location)
	{
		this.brick = brick;
		this.location = location;
	}
	
	@Override
	public Brick<E> getBrick()
	{
		return brick;
	}
	
	@Override
	public Brick.FragmentLocation getLocation()
	{
		return location;
	}

	@Override
	public int getX()
	{
		return 3 * brick.getX() + location.getDeltaX();
	}

	@Override
	public int getY()
	{
		return 3 * brick.getY() + location.getDeltaY();
	}
}
