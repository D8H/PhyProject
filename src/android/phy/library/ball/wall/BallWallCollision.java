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
package android.phy.library.ball.wall;
import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;
import android.graphics.Rect;
import android.phy.core.collision.Collision;
import android.phy.core.collision.CollisionEvent;
import android.phy.core.solid.MoveableSolid;
import android.phy.core.solid.Solid;
import android.phy.library.ball.Ball;
import android.phy.library.wall.Brick;
import android.phy.library.wall.Fragment;
import android.phy.library.wall.Wall;
import android.phy.util.Pair;
import android.util.FloatMath;

public class BallWallCollision implements Collision
{
	private final ArrayList<BallWallCollisionListener> listeners = new ArrayList<BallWallCollisionListener>();
	private Iterable<Pair<Ball, Wall>> collisionSolidIterable;
	
	
	public BallWallCollision(Iterable<Pair<Ball, Wall>> collisionSolidIterable)
	{
		this.collisionSolidIterable = collisionSolidIterable;
	}
	
	@Override
	public BallWallCollisionEvent testOn(MoveableSolid moveableSolid, Solid solid)
	{
		BallWallCollisionEvent event;
		Ball ball = (Ball) moveableSolid;
		Wall wall = (Wall) solid;
		
		PointF ballCenter = ball.getLocation();
		float ballRadius = ball.getRadius();
		Rect rect = wall.getBounds();
		
//		ballCenter.x = (float) (wallBounds.getX() + wallBounds.width  * 1.6 / wallDimX);
//		ballCenter.y = (float) (wallBounds.getY() + wallBounds.height * 1.4 / wallDimY);
//		ball.setSpeed(1);
//		ball.setAngle((float) (135 * Math.PI / 180));
//		System.out.println("Location : " + ballCenter);
//		System.out.println("Speed : " + ball.getSpeed());
		
		int minX = (int) FloatMath.floor(ballCenter.x - ballRadius);
		int maxX = (int) FloatMath.floor(ballCenter.x + ballRadius);
		int minY = (int) FloatMath.floor(ballCenter.y - ballRadius);
		int maxY = (int) FloatMath.floor(ballCenter.y + ballRadius);
		
		minX = Math.max(minX, rect.left);
		minY = Math.max(minY, rect.top);
		maxX = Math.min(maxX, rect.right - 1);
		maxY = Math.min(maxY, rect.bottom - 1);
		
//		System.out.println(minX + " -> " + maxX + " , " + minY + " -> " + maxY);
//		boolean foundCollision = false;
		boolean foundVerticeCollision = false;
		float deltaXmin = 0;
		float deltaYmin = 0;
		float distanceSqMin = ball.getRadius() * ball.getRadius();
		PointF projectedDelta = new PointF();
		Fragment hittenFragment = null;
		for (int y = minY; y <= maxY; y++)
		{
			for (int x = minX; x <= maxX; x++)
			{
				Brick brick = wall.get(x, y);
				
				float brickCenterX = (x + 0.5f);
				float brickCenterY = (y + 0.5f);
				
				float deltaX = ballCenter.x - brickCenterX;
				float deltaY = ballCenter.y - brickCenterY;
				
				for (Brick.FragmentLocation fragmentLocation : Brick.FragmentLocation.values())
				{
					Fragment fragment = brick.getFragment(fragmentLocation);
					if (fragment.isSolid())
					{
						switch (fragmentLocation)
						{
							case north:
							{
								projectOnSouthFragment(deltaX, -deltaY, projectedDelta);
								projectedDelta.y = - projectedDelta.y;
								break;
							}
							case south:
							{
								projectOnSouthFragment(deltaX, deltaY, projectedDelta);
								break;
							}
							case west:
							{
								projectOnSouthFragment(deltaY, - deltaX, projectedDelta);
								float projectedDeltaY = projectedDelta.y;
								projectedDelta.y = projectedDelta.x;
								projectedDelta.x = - projectedDeltaY;
								break;
							}
							case east:
							{
								projectOnSouthFragment(deltaY, deltaX, projectedDelta);
								float projectedDeltaY = projectedDelta.y;
								projectedDelta.y = projectedDelta.x;
								projectedDelta.x = projectedDeltaY;
								break;
							}
						}
						
						float projectedX = brickCenterX + projectedDelta.x;
						float projectedY = brickCenterY + projectedDelta.y;
							
						float deltaBallX = projectedX - ballCenter.x;
						float deltaBallY = projectedY - ballCenter.y;
						float distanceSq = deltaBallX * deltaBallX + deltaBallY * deltaBallY;
						
						if (distanceSq < distanceSqMin && distanceSq != 0)
						{
							deltaXmin = deltaBallX;
							deltaYmin = deltaBallY;
							distanceSqMin = distanceSq;
							hittenFragment = brick.getFragment(fragmentLocation);
						}
					}
				}
			}
		}
		if (hittenFragment != null && deltaXmin * ball.getSpeed().x + deltaYmin * ball.getSpeed().y > 0)
		{
			ball.bounce(deltaXmin, deltaYmin);
//			System.out.println("angle : " + (ball.getAngle() / Math.PI * 180 % 360));
//			System.out.println("Speed : " + ball.getSpeed());
			event = new BallWallCollisionEvent(this, ball, wall, hittenFragment);
		}
		else
		{
			event = null;
		}
//		System.out.println();
		return event;
	}
	
	@Override
	public void apply(CollisionEvent collisionEvent)
	{
		BallWallCollisionEvent ballWallCollisionEvent = (BallWallCollisionEvent) collisionEvent;
		fireBallWallCollision(ballWallCollisionEvent);
	}
	
	public void addCollisionListener(BallWallCollisionListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeCollisionListener(BallWallCollisionListener listener)
	{
		listeners.remove(listener);
	}
	
	public List<BallWallCollisionListener> getCollisionListeners()
	{
		return listeners;
	}
	
	protected void fireBallWallCollision(BallWallCollisionEvent event)
	{
		for (BallWallCollisionListener listener : getCollisionListeners())
		{
			listener.onBallWallCollision(event);
		}
	}
	
	private void projectOnSouthFragment(float deltaX, float deltaY, PointF projectedDelta)
	{
		float projectedDeltaX, projectedDeltaY;
		if (deltaY > 0.5f)
		{
			projectedDeltaY = 0.5f;
			if (deltaX < -0.5f)
			{
				projectedDeltaX = -0.5f;
			}
			else if (deltaX > 0.5f)
			{
				projectedDeltaX = 0.5f;
			}
			else
			{
				projectedDeltaX = deltaX;
			}
		}
		else if (deltaX < 0)
		{
			projectedDeltaX = (deltaX - deltaY) / 2;
			projectedDeltaY = - projectedDeltaX;						
			if (projectedDeltaX < -0.5f)
			{
				projectedDeltaX = -0.5f;
				projectedDeltaY = 0.5f;
			}
			else if (projectedDeltaX > 0)
			{
				projectedDeltaX = 0;
				projectedDeltaY = 0;
			}
		}
		else
		{
			projectedDeltaX = (deltaX + deltaY) / 2;
			projectedDeltaY = projectedDeltaX;						
			if (projectedDeltaX < 0)
			{
				projectedDeltaX = 0;
				projectedDeltaY = 0;
			}
			else if (projectedDeltaX > 0.5f)
			{
				projectedDeltaX = 0.5f;
				projectedDeltaY = 0.5f;
			}
		}
		projectedDelta.x = projectedDeltaX;
		projectedDelta.y = projectedDeltaY;
	}
	
//	private float moveInto(float value, float min, float max, Boolean valueIsMoved)
//	{
//		float moveValue;
//		if (value < min)
//		{
//			moveValue = min;
//		}
//		else if (value > max)
//		{
//			moveValue = max;
//		}
//		else
//		{
//			moveValue = value;
//		}
//		return moveValue;
//	}

	@Override
	public Iterable<Pair<Ball, Wall>> getCollisionIterable()
	{
		return collisionSolidIterable;
	}
}
