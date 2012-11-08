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

import android.phy.library.wall.Brick.FragmentLocation;

/**
 * A factory of fragments
 * 
 * @author Davy
 */
public interface FragmentFactory
{
	/**
	 * Create a fragment
	 * @param brick the brick containing the fragment
	 * @param location the location of the fragment in the brick
	 */
	public Fragment creatFragment(Brick brick, FragmentLocation location);
}
