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

/**
 * Base abstract implementation for <code>Brick</code>.
 * Access to fragments are implemented.
 * You may need to implement a {@link FragmentFactory} and override some getters to make their return type correspond to your fragments.
 * 
 * @author Davy
 */
public abstract class AbstractBrick<E> implements Brick<E>
{
	private FragmentFactory<E> fragmentFactory;
	private Wall<E> wall;
	private int x;
	private int y;
	private ArrayList<Fragment<E>> fragments;
	
	
	/**
	 * Create a brick
	 * @param fragmentFactory the factory used for building brick fragments
	 * @param wall the wall containing the brick
	 * @param x the x coordinate of the brick in the wall
	 * @param y the y coordinate of the brick in the wall
	 */
	public AbstractBrick(FragmentFactory<E> fragmentFactory, Wall<E> wall, int x, int y)
	{
		this.fragmentFactory = fragmentFactory;
		this.wall = wall;
		this.x = x;
		this.y = y;
		fragments = new ArrayList<Fragment<E>>(4);
		for (FragmentLocation fragmentLocation : FragmentLocation.values())
		{
			fragments.add(fragmentFactory.creatFragment(this, fragmentLocation));
		}
	}
	
	@Override
	public Fragment<E> getFragment(FragmentLocation fragmentLocation)
	{
		return fragments.get(fragmentLocation.ordinal());
	}
	
	@Override
	public Wall<E> getWall()
	{
		return wall;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}
	
	public FragmentFactory<E> getFragmentFactory()
	{
		return fragmentFactory;
	}
}
