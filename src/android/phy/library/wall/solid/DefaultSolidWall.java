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
package android.phy.library.wall.solid;

import android.graphics.Rect;
import android.phy.core.word.World;
import android.phy.library.wall.BrickFactory;
import android.phy.library.wall.DefaultWall;

/**
 * Default implementation for <code>Wall</code> usable in the {@link World}.
 * Access to bricks and fragments are implemented.
 * You may need to implement a {@link BrickFactory}.
 * This allow to custom the data and behaviors of the wall while using a common interface.
 * 
 * @author Davy
 */
public class DefaultSolidWall<E extends SolidContent> extends DefaultWall<E> implements SolidWall<E>
{
	private final World world;
	
	/**
	 * Create a wall
	 * @param brickFactory the factory used for building bricks
	 * @param world the world containing the wall
	 * @param bounds the bounds of the wall
	 */
	public DefaultSolidWall(BrickFactory<E> brickFactory, World world, Rect bounds)
	{
		super(brickFactory, bounds);
		this.world = world;
	}

	@Override
	public World getWorld()
	{
		return world;
	}
	
	@Override
	public SolidBrick<E> get(int x, int y)
	{
		return (SolidBrick<E>) super.get(x, y);
	}
	
	@Override
	public SolidFragment<E> getFragment(int fragmentX, int fragmentY)
	{
		return (SolidFragment<E>) super.getFragment(fragmentX, fragmentY);
	}
}
