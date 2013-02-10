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

import android.phy.library.ball.Ball;


public class BounceBallBallCollisionEvent extends BallBallCollisionEvent
{
	private static final long serialVersionUID = 4603885265683074415L;
	
	private float force;
	private float deltaUnitX;
	private float deltaUnitY;

	
	public BounceBallBallCollisionEvent
	(
		BallBallCollision source,
		Ball moveableSolid,
		Ball solid,
		float force,
		float deltaUnitX,
		float deltaUnitY
	)
	{
		super(source, moveableSolid, solid);
		this.force = force;
		this.deltaUnitX = deltaUnitX;
		this.deltaUnitY = deltaUnitY;
	}
	
	public float getForce()
	{
		return force;
	}

	public float getDeltaUnitX()
	{
		return deltaUnitX;
	}

	public float getDeltaUnitY()
	{
		return deltaUnitY;
	}
}
