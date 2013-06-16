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

import android.phy.library.wall.AbstractBrick;
import android.phy.library.wall.FragmentFactory;
import android.phy.library.wall.Wall;

/**
 * Base abstract implementation for <code>Brick</code>.
 * Access to fragments are implemented.
 * You may need to implement a {@link FragmentFactory} and override some getters to make their return type correspond to your fragments.
 * 
 * @author Davy
 */
public abstract class AbstractSolidBrick<E extends SolidContent> extends AbstractBrick<E> implements SolidBrick<E>
{
	
	
	/**
	 * Create a brick
	 * @param fragmentFactory the factory used for building brick fragments
	 * @param wall the wall containing the brick
	 * @param x the x coordinate of the brick in the wall
	 * @param y the y coordinate of the brick in the wall
	 */
	public AbstractSolidBrick(FragmentFactory<E> fragmentFactory, Wall<E> wall, int x, int y)
	{
		super(fragmentFactory, wall, x, y);
	}
	
	@Override
	public SolidWall<E> getWall()
	{
		return (SolidWall<E>) super.getWall();
	}
	
	@Override
	public SolidFragment<E> getFragment(FragmentLocation fragmentLocation)
	{
		return (SolidFragment<E>) super.getFragment(fragmentLocation);
	}
}
