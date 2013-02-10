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
package android.phy.library.ball.bounds;

import java.util.ArrayList;
import java.util.List;

import android.phy.core.collision.Collision;
import android.phy.core.collision.CollisionEvent;
import android.phy.core.solid.MoveableSolid;
import android.phy.core.solid.Solid;
import android.phy.library.BoundsSolid;
import android.phy.library.ball.Ball;
import android.phy.util.Pair;


public class BallBoundsCollision implements Collision
{
	private final ArrayList<BallBoundsCollisionListener> listeners = new ArrayList<BallBoundsCollisionListener>();
	private Iterable<Pair<Ball, BoundsSolid>> collisionSolidIterable;
	private BallBoundsCollisionEffect effect;
	
	public BallBoundsCollision(Iterable<Pair<Ball, BoundsSolid>> collisionSolidIterable, BallBoundsCollisionEffect effect)
	{
		this.collisionSolidIterable = collisionSolidIterable;
		this.effect = effect;
		this.effect.setCollision(this);
	}

	@Override
	public CollisionEvent testOn(MoveableSolid moveableSolid, Solid solid)
	{
		BallBoundsCollisionEvent event;
		
		Ball ball = (Ball) moveableSolid;
		BoundsSolid boundsSolid = (BoundsSolid) solid;
		
		event = effect.calculOn(ball, boundsSolid);
		
		return event;
	}
	
	@Override
	public void apply(CollisionEvent collisionEvent)
	{
		BallBoundsCollisionEvent ballBoundsCollisionEvent = (BallBoundsCollisionEvent) collisionEvent;
		
		effect.apply(ballBoundsCollisionEvent);
	}
	
	public void addCollisionListener(BallBoundsCollisionListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeCollisionListener(BallBoundsCollisionListener listener)
	{
		listeners.remove(listener);
	}
	
	public List<BallBoundsCollisionListener> getCollisionListeners()
	{
		return listeners;
	}
	
	protected void fireGameIsFinished(BallBoundsCollisionEvent event)
	{
		for(BallBoundsCollisionListener listener : getCollisionListeners())
		{
			listener.onBallBoundsCollision(event);
		}
	}

	@Override
	public Iterable<Pair<Ball, BoundsSolid>> getCollisionIterable()
	{
		return collisionSolidIterable;
	}
}
