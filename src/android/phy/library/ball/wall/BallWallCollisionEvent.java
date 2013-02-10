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
package android.phy.library.ball.wall;

import android.phy.core.collision.CollisionEvent;
import android.phy.library.ball.Ball;
import android.phy.library.wall.Fragment;
import android.phy.library.wall.Wall;

public class BallWallCollisionEvent extends CollisionEvent
{
	private Fragment hittenFragment;
	
	
	public BallWallCollisionEvent(BallWallCollision source, Ball ball, Wall wall, Fragment hittenFragment)
	{
		super(source, ball, wall);
		this.hittenFragment = hittenFragment;
	}

	public Fragment getHittenFragment()
	{
		return hittenFragment;
	}

	@Override
	public Ball getMoveableSolid()
	{
		return (Ball) super.getMoveableSolid();
	}

	@Override
	public Wall getSolid()
	{
		return (Wall) super.getSolid();
	}

	@Override
	public BallWallCollision getSource()
	{
		return (BallWallCollision) super.getSource();
	}
	
	
}
