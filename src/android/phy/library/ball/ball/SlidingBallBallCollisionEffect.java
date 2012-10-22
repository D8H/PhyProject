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

import android.graphics.PointF;
import android.phy.library.ball.AbstractBallBallCollisionEffect;
import android.phy.library.ball.Ball;
import android.util.FloatMath;

public class SlidingBallBallCollisionEffect extends AbstractBallBallCollisionEffect
{
	
	@Override
	public BallBallCollisionEvent calculOn(Ball ball, Ball otherBall)
	{
		BallBallCollisionEvent event;
		
		PointF ballCenter = ball.getLocation();
		float radius      = ball.getRadius();
		
		PointF otherBallCenter = otherBall.getLocation();
		float otherRadius      = otherBall.getRadius();
		
		float deltaX = otherBallCenter.x - ballCenter.x;
		float deltaY = otherBallCenter.y - ballCenter.y;
		
		float deltaSq = deltaX * deltaX + deltaY * deltaY;
		float radiusSum = radius + otherRadius;
		
		float locationDeltaX, locationDeltaY;
		if (radiusSum * radiusSum - deltaSq > 0 && deltaSq > 0)
		{
			float delta = FloatMath.sqrt(deltaSq);
			
			locationDeltaX = (deltaX - deltaX * radiusSum / delta) / 2;
			locationDeltaY = (deltaY - deltaY * radiusSum / delta) / 2;
		}
		else
		{
			locationDeltaX = 0;
			locationDeltaY = 0;
		}
		
		event = new SlidingBallBallCollisionEvent
		(
			getCollision(),
			ball,
			otherBall,
			locationDeltaX,
			locationDeltaY
		);
		
		return event;
	}
	
	@Override
	public void apply(BallBallCollisionEvent collisionEvent)
	{
		SlidingBallBallCollisionEvent ballBallCollisionEvent = (SlidingBallBallCollisionEvent) collisionEvent;
		
		float locationDeltaX = ballBallCollisionEvent.getLocationDeltaX();
		float locationDeltaY = ballBallCollisionEvent.getLocationDeltaY();
		{
			Ball ball = ballBallCollisionEvent.getMoveableSolid();
			
			PointF ballLocation = ball.getLocation();
			ball.setLocation
			(
				ballLocation.x + locationDeltaX,
				ballLocation.y + locationDeltaY
			);
		}
		{
			Ball ball = ballBallCollisionEvent.getSolid();
			
			PointF ballLocation = ball.getLocation();
			ball.setLocation
			(
				ballLocation.x - locationDeltaX,
				ballLocation.y - locationDeltaY
			);
		}
	}
}
