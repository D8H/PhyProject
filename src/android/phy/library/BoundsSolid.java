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
package android.phy.library;

import android.graphics.RectF;
import android.phy.core.solid.Solid;
import android.phy.core.word.World;


public class BoundsSolid implements Solid
{
	private RectF bounds;
	private World world;
	
	
	public BoundsSolid(World world, RectF bounds)
	{
		this.world = world;
		this.bounds = bounds;
	}
	
	public RectF getBounds()
	{
		return bounds;
	}

	public void setBounds(RectF bounds)
	{
		this.bounds = bounds;
	}

	@Override
	public World getWorld()
	{
		return world;
	}
}
