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

/**
 * Base abstract implementation for <code>Brick</code>.
 * Access to fragments are implemented.
 * You may need to implement a {@link FragmentFactory} and override some getters to make their return type correspond to your fragments.
 * 
 * @author Davy
 */
public abstract class AbstractBrick implements Brick
{
	private FragmentFactory fragmentFactory;
	private Wall wall;
	private int x;
	private int y;
	private Fragment[] fragments;
	
	
	/**
	 * Create a brick
	 * @param fragmentFactory the factory used for building brick fragments
	 * @param wall the wall containing the brick
	 * @param x the x coordinate of the brick in the wall
	 * @param y the y coordinate of the brick in the wall
	 */
	public AbstractBrick(FragmentFactory fragmentFactory, Wall wall, int x, int y)
	{
		this.fragmentFactory = fragmentFactory;
		this.wall = wall;
		this.x = x;
		this.y = y;
		fragments = new Fragment[4];
		for (FragmentLocation fragmentLocation : FragmentLocation.values())
		{
			fragments[fragmentLocation.ordinal()] = fragmentFactory.creatFragment(this, fragmentLocation);
		}
	}
	
	@Override
	public Fragment getFragment(FragmentLocation fragmentLocation)
	{
		return fragments[fragmentLocation.ordinal()];
	}
	
	@Override
	public Wall getWall()
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
	
	public FragmentFactory getFragmentFactory()
	{
		return fragmentFactory;
	}
}
