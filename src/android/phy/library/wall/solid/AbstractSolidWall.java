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
import android.phy.library.wall.AbstractWall;
import android.phy.library.wall.BrickFactory;

/**
 * Base abstract implementation for <code>Wall</code> usable in the {@link World}.
 * You may need to implement a {@link BrickFactory}.
 * This allow to custom the data and behaviors of the wall while using a common interface.
 * 
 * @author Davy
 */
public abstract class AbstractSolidWall<E extends SolidContent> extends AbstractWall<E> implements SolidWall<E>
{
	private final World world;
	
	/**
	 * Create a wall
	 * @param brickFactory the factory used for building bricks
	 * @param world the world containing the wall
	 * @param bounds the bounds of the wall
	 */
	public AbstractSolidWall(BrickFactory<E> brickFactory, World world, Rect bounds)
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
	public abstract SolidBrick<E> get(int x, int y);
	
	@Override
	public SolidFragment<E> getFragment(int fragmentX, int fragmentY)
	{
		return (SolidFragment<E>) super.getFragment(fragmentX, fragmentY);
	}
}
