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

import java.util.ArrayList;

import android.graphics.Rect;

/**
 * Default implementation for <code>Wall</code>.
 * Access to bricks and fragments are implemented.
 * You may need to implement a {@link BrickFactory}.
 * This allow to custom the data and behaviors of the wall while using a common interface.
 * 
 * @author Davy
 */
public class DefaultWall<E> extends AbstractWall<E>
{
	private ArrayList<ArrayList<Brick<E>>> bricks;
	
	/**
	 * Create a wall
	 * @param brickFactory the factory used for building bricks
	 * @param bounds the bounds of the wall
	 */
	public DefaultWall(BrickFactory<E> brickFactory, Rect bounds)
	{
		super(brickFactory, bounds);
		
		bricks = new ArrayList<ArrayList<Brick<E>>>(bounds.height());
		for (int y = 0; y < bounds.height(); y++)
		{
			ArrayList<Brick<E>> row = new ArrayList<Brick<E>>(bounds.width());
			bricks.add(row);
			for (int x = 0; x < bounds.width(); x++)
			{
				row.add(brickFactory.creatBrick(this, x, y));
			}
		}
	}
	
	@Override
	public Brick<E> get(int x, int y)
	{
		Brick<E> brick;
		Rect bounds = getBounds();
		brick = bricks.get(y - bounds.top).get(x - bounds.left);
		return brick;
	}
}
