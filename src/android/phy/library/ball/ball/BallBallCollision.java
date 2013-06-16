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
import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;
import android.phy.core.collision.Collision;
import android.phy.core.collision.CollisionEvent;
import android.phy.core.solid.MoveableSolid;
import android.phy.core.solid.Solid;
import android.phy.library.ball.Ball;
import android.phy.util.Pair;


public class BallBallCollision implements Collision
{
	private final ArrayList<BallBallCollisionListener> listeners = new ArrayList<BallBallCollisionListener>();
	private Iterable<? extends Pair<? extends Ball, ? extends Ball>> collisionSolidIterable;
	private BallBallCollisionEffect collisionEffect;
	
	
	public BallBallCollision(Iterable<? extends Pair<? extends Ball, ? extends Ball>> collisionSolidIterable, BallBallCollisionEffect collisionEffect)
	{
		this.collisionSolidIterable = collisionSolidIterable;
		this.collisionEffect = collisionEffect;
		this.collisionEffect.setCollision(this);
	}

	@Override
	public CollisionEvent testOn(MoveableSolid moveableSolid, Solid solid)
	{
		BallBallCollisionEvent event;
		
		Ball ball = (Ball) moveableSolid;
		PointF ballCenter = ball.getLocation();
		float radius      = ball.getRadius();
		
		Ball otherBall = (Ball) solid;
		PointF otherBallCenter = otherBall.getLocation();
		float otherRadius      = otherBall.getRadius();
		
		float deltaX = otherBallCenter.x - ballCenter.x;
		float deltaY = otherBallCenter.y - ballCenter.y;
		float radiusSum = radius + otherRadius;
		
		if (Math.abs(deltaX) < radiusSum && Math.abs(deltaY) < radiusSum
		 && deltaX * deltaX + deltaY * deltaY < radiusSum * radiusSum)
		{
			event = collisionEffect.calculOn(ball, otherBall);
		}
		else
		{
			event = null;
		}
		return event;
	}
	
	@Override
	public void apply(CollisionEvent collisionEvent)
	{
		BallBallCollisionEvent ballBallCollisionEvent = (BallBallCollisionEvent) collisionEvent;
		
		collisionEffect.apply(ballBallCollisionEvent);
		fireGameIsFinished(ballBallCollisionEvent);
	}
	
	public void addCollisionListener(BallBallCollisionListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeCollisionListener(BallBallCollisionListener listener)
	{
		listeners.remove(listener);
	}
	
	public List<BallBallCollisionListener> getCollisionListeners()
	{
		return listeners;
	}
	
	protected void fireGameIsFinished(BallBallCollisionEvent event)
	{
		for (BallBallCollisionListener listener : getCollisionListeners())
		{
			listener.onBallBallCollision(event);
		}
	}

	@Override
	public Iterable<? extends Pair<? extends Ball, ? extends Ball>> getCollisionIterable()
	{
		return collisionSolidIterable;
	}
}
