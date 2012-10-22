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
package android.phy.library.ball.bounds;

import android.graphics.PointF;
import android.graphics.RectF;
import android.phy.library.BoundsSolid;
import android.phy.library.ball.Ball;
import android.phy.library.ball.bounds.BallBoundsCollisionEvent.HorizontalEdge;
import android.phy.library.ball.bounds.BallBoundsCollisionEvent.VerticalEdge;

public class BounceBallBoundsCollisionEffect extends AbstractBallBoundsCollisionEffect
{
	private boolean bounceAtLeaving;
	
	
	public BounceBallBoundsCollisionEffect(boolean bounceAtLeaving)
	{
		this.bounceAtLeaving = bounceAtLeaving;
	}
	
	public boolean isBounceAtLeaving()
	{
		return bounceAtLeaving;
	}
	
	@Override
	public BallBoundsCollisionEvent calculOn(Ball ball, BoundsSolid boundsSolid)
	{
		BallBoundsCollisionEvent event;
		
		PointF ballCenter = ball.getLocation();
		float radius = ball.getRadius();
		PointF speed = ball.getSpeed();
		
		RectF bounds = boundsSolid.getBounds();
		
		float xMin = ballCenter.x - radius;
		float xMax = ballCenter.x + radius;
		float yMin = ballCenter.y - radius;
		float yMax = ballCenter.y + radius;
		
		boolean ballIsLeaving = false;
		VerticalEdge verticalEdge;
		HorizontalEdge horizontalEdge;
		
		if (bounds.left > xMin && bounds.left < xMax)
		{
			verticalEdge = VerticalEdge.LEFT;
			ballIsLeaving = speed.x < 0;
		}
		else if (bounds.right > xMin && bounds.right < xMax)
		{
			verticalEdge = VerticalEdge.RIGHT;
			ballIsLeaving = speed.x > 0;
		}
		else
		{
			verticalEdge = null;
		}
		
		if (bounds.top > yMin && bounds.top < yMax)
		{
			horizontalEdge = HorizontalEdge.TOP;
			ballIsLeaving = speed.y < 0;
		}
		else if (bounds.bottom > yMin && bounds.bottom < yMax)
		{
			horizontalEdge = HorizontalEdge.BOTTOM;
			ballIsLeaving = speed.y > 0;
		}
		else
		{
			horizontalEdge = null;
		}
		
		if (verticalEdge != null
		 || horizontalEdge != null)
		{
			event = new BallBoundsCollisionEvent
			(
				getCollision(),
				ball,
				boundsSolid,
				ballIsLeaving,
				verticalEdge,
				horizontalEdge
			);
		}
		else
		{
			event = null;
		}
		return event;
	}
	
	@Override
	public void apply(BallBoundsCollisionEvent collisionEvent)
	{
		if (collisionEvent.ballIsLeaving())
		{
			Ball ball = collisionEvent.getMoveableSolid();
			
			if (collisionEvent.getHorizontalEdge() != null)
			{
				ball.bounceHorizontally();
			}
			if (collisionEvent.getVerticalEdge() != null)
			{
				ball.bounceVertically();
			}
		}
	}
}
