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

import android.phy.library.ball.Ball;


public abstract class AbstractBallBallCollisionEffect implements BallBallCollisionEffect
{
	private BallBallCollision collision;

	@Override
	public abstract BallBallCollisionEvent calculOn(Ball ball, Ball otherBall);

	@Override
	public abstract void apply(BallBallCollisionEvent collisionEvent);

	@Override
	public BallBallCollision getCollision()
	{
		return collision;
	}

	@Override
	public void setCollision(BallBallCollision collision)
	{
		this.collision = collision;
	}
}
