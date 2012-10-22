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


public class SlidingBallBallCollisionEvent extends BallBallCollisionEvent
{
	private static final long serialVersionUID = 4603885265683074415L;
	
	private float locationDeltaX;
	private float locationDeltaY;

	
	public SlidingBallBallCollisionEvent
	(
		BallBallCollision source,
		Ball moveableSolid,
		Ball solid,
		float locationDeltaX,
		float locationDeltaY
	)
	{
		super(source, moveableSolid, solid);
		this.locationDeltaX = locationDeltaX;
		this.locationDeltaY = locationDeltaY;
	}
	
	public float getLocationDeltaX()
	{
		return locationDeltaX;
	}
	
	public float getLocationDeltaY()
	{
		return locationDeltaY;
	}
}
