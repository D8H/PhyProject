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
package android.phy.library.ball.ball;

import android.graphics.PointF;
import android.phy.library.ball.Ball;
import android.util.FloatMath;

public class BounceBallBallCollisionEffect extends AbstractBallBallCollisionEffect
{
	
	
	@Override
	public BallBallCollisionEvent calculOn(Ball ball, Ball otherBall)
	{
		BallBallCollisionEvent event;
		
		PointF ballCenter = ball.getLocation();
		PointF speed      = ball.getSpeed();
		float  mass       = ball.getMass();
		
		PointF otherBallCenter = otherBall.getLocation();
		PointF otherSpeed      = otherBall.getSpeed();
		float  otherMass       = otherBall.getMass();
		
		float deltaX = otherBallCenter.x - ballCenter.x;
		float deltaY = otherBallCenter.y - ballCenter.y;
		
		float deltaSq = deltaX * deltaX + deltaY * deltaY;
		float delta = FloatMath.sqrt(deltaSq);
		float deltaUnitX = deltaX / delta;
		float deltaUnitY = deltaY / delta;
		
		float speedCosB = speed.x * deltaUnitX + speed.y * deltaUnitY;
		float otherSpeedCosB = otherSpeed.x * deltaUnitX + otherSpeed.y * deltaUnitY;
		
		float force = 2 * (speedCosB - otherSpeedCosB) * (mass * otherMass) / (mass + otherMass);
		
		event = new BounceBallBallCollisionEvent
		(
			getCollision(),
			ball,
			otherBall,
			force,
			deltaUnitX,
			deltaUnitY
		);
		
//		Log.v("test", "");
//		Log.v("test", "" + ball);
//		Log.v("test", "" + otherBall);
//		Log.v("test", "deltaUnit : " + deltaUnitX + ", " + deltaUnitY);
//		Log.v("test", "speedCosB      : " + speedCosB);
//		Log.v("test", "otherSpeedCosB : " + otherSpeedCosB);
//		Log.v("test", "force : " + force);
		
		return event;
	}

	@Override
	public void apply(BallBallCollisionEvent collisionEvent)
	{
		BounceBallBallCollisionEvent ballBallCollisionEvent = (BounceBallBallCollisionEvent) collisionEvent;
		
		float force = ballBallCollisionEvent.getForce();
		if (force > 0)
		{
			float deltaUnitX = ballBallCollisionEvent.getDeltaUnitX();
			float deltaUnitY = ballBallCollisionEvent.getDeltaUnitY();
			{
				Ball ball = ballBallCollisionEvent.getMoveableSolid();
				float mass = ball.getMass();
							
				float deltaSpeedX = - force * deltaUnitX / mass;
				float deltaSpeedY = - force * deltaUnitY / mass;
//				Log.v("test", "---> deltaSpeed : " + deltaSpeedX + ", " + deltaSpeedY);
				
				PointF ballSpeed = ball.getSpeed();
				ball.setSpeed
				(
					ballSpeed.x + deltaSpeedX,
					ballSpeed.y + deltaSpeedY
				);
			}
			{
				Ball otherBall = ballBallCollisionEvent.getSolid();
				float otherMass = otherBall.getMass();
	
				float otherDeltaSpeedX = force * deltaUnitX / otherMass;
				float otherDeltaSpeedY = force * deltaUnitY / otherMass;
//				Log.v("test", "---> otherDeltaSpeed : " + otherDeltaSpeedX + ", " + otherDeltaSpeedY);
				
				PointF otherBallSpeed = otherBall.getSpeed();
				otherBall.setSpeed
				(
					otherBallSpeed.x + otherDeltaSpeedX,
					otherBallSpeed.y + otherDeltaSpeedY
				);
			}
		}
	}	
}
