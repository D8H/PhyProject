/*******************************************************************************
 * Copyright 2012 Davy H�lard
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

public abstract class AbstractFragment implements Fragment
{
	private Brick brick;
	private Brick.FragmentLocation location;
	
	
	protected AbstractFragment(Brick brick, FragmentLocation location)
	{
		this.brick = brick;
		this.location = location;
	}
	
	@Override
	public abstract boolean isSolid();
	
	@Override
	public Brick getBrick()
	{
		return brick;
	}
	
	@Override
	public Brick.FragmentLocation getLocation()
	{
		return location;
	}
}