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

/**
 * A brick of wall
 * @author Davy
 */
public interface Brick<E>
{
	/**
	 * The locations of fragments that are contained in a brick.
	 */
	public enum FragmentLocation
	{
		north,
		west,
		south,
		east;
		
		private static final int[] fragmentDeltaX = {1, 0, 1, 2};
		private static final int[] fragmentDeltaY = {0, 1, 2, 1};
		
		/**
		 * Return the delta that is used to calculate x coordinate of fragment.
		 * @return the delta that is used to calculate x coordinate of fragment
		 */
		public int getDeltaX()
		{
			return fragmentDeltaX[ordinal()];
		}
		
		/**
		 * Return the delta that is used to calculate y coordinate of fragment.
		 * @return the delta that is used to calculate y coordinate of fragment
		 */
		public int getDeltaY()
		{
			return fragmentDeltaY[ordinal()];
		}
	}
	
	/**
	 * Return the brick fragment from the specified location.
	 * @param fragmentLocation the location of the fragment
	 * @return the brick fragment from the specified location
	 */
	public Fragment<E> getFragment(FragmentLocation fragmentLocation);
	/**
	 * Return the wall that contain the brick.
	 * @return the wall that contain the brick
	 */
	public Wall<E> getWall();
	/**
	 * Return the x coordinate of the brick.
	 * @return the x coordinate of the brick
	 */
	public int getX();
	/**
	 * Return the y coordinate of the brick.
	 * @return the y coordinate of the brick
	 */
	public int getY();
}
