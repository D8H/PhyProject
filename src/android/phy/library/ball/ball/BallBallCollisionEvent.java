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
package android.phy.library.ball.ball;

import android.phy.core.collision.CollisionEvent;
import android.phy.library.ball.Ball;

public class BallBallCollisionEvent extends CollisionEvent
{
	private static final long serialVersionUID = 4603885265683074415L;
	
	
	public BallBallCollisionEvent
	(
		BallBallCollision source,
		Ball moveableSolid,
		Ball solid
	)
	{
		super(source, moveableSolid, solid);
	}
	
	@Override
	public Ball getMoveableSolid()
	{
		return (Ball) super.getMoveableSolid();
	}

	@Override
	public Ball getSolid()
	{
		return (Ball) super.getSolid();
	}

	@Override
	public BallBallCollision getSource()
	{
		return (BallBallCollision) super.getSource();
	}
}
