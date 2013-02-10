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

import android.phy.core.collision.CollisionEvent;
import android.phy.library.BoundsSolid;
import android.phy.library.ball.Ball;

public class BallBoundsCollisionEvent extends CollisionEvent
{
	private static final long serialVersionUID = 4603885265683074415L;
	
	public enum VerticalEdge
	{
		LEFT,
		RIGHT
	}
	
	public enum HorizontalEdge
	{
		TOP,
		BOTTOM
	}
	
	private boolean ballIsLeaving;
	private VerticalEdge verticalEdge;
	private HorizontalEdge horizontalEdge;
	
	
	public BallBoundsCollisionEvent
	(
		BallBoundsCollision source,
		Ball moveableSolid,
		BoundsSolid solid,
		boolean ballIsLeaving,   
		VerticalEdge verticalEdge,
		HorizontalEdge horizontalEdge
	)
	{
		super(source, moveableSolid, solid);
		this.ballIsLeaving = ballIsLeaving;
		this.verticalEdge = verticalEdge;
		this.horizontalEdge = horizontalEdge;
	}
	
	public boolean ballIsLeaving()
	{
		return ballIsLeaving;
	}
	
	public VerticalEdge getVerticalEdge()
	{
		return verticalEdge;
	}
	
	public HorizontalEdge getHorizontalEdge()
	{
		return horizontalEdge;
	}
	
	@Override
	public Ball getMoveableSolid()
	{
		return (Ball) super.getMoveableSolid();
	}

	@Override
	public BoundsSolid getSolid()
	{
		return (BoundsSolid) super.getSolid();
	}

	@Override
	public BallBoundsCollision getSource()
	{
		return (BallBoundsCollision) super.getSource();
	}
}
