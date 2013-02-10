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
package android.phy.core.solid;

import android.graphics.PointF;


/**
 * A Solid of the world that can {@link MoveableSolid#move(float)}.
 * @author Davy
 */
public interface MoveableSolid extends Solid
{
	/**
	 * Move the solid for the equivalent of a specify duration.
	 * @param seconds the duration of movement to apply.
	 */
	public void move(float seconds);
	
	/**
	 * @return
	 */
	public PointF getLocation();
	/**
	 * @return
	 */
	public float getAngle();
	/**
	 * @return
	 */
	public PointF getSpeed();
	
	/**
	 * @param location
	 */
	public void setLocation(PointF location);
	/**
	 * @param x
	 * @param y
	 */
	public void setLocation(float x, float y);
	
	/**
	 * @param angle
	 */
	public void setAngle(float angle);
	
	/**
	 * @param speedX
	 * @param speedY
	 */
	public void setSpeed(float speedX, float speedY);
	/**
	 * Multiply the speed by a specify factor.
	 * Notice that this calculus keep the angle identical.
	 * @param factor the factor to apply to the speed
	 */
	public void multiplySpeed(float factor);
	/**
	 * @param speed
	 */
	public void setSpeed(float speed);
	
	/**
	 * @return
	 */
	public float getMass();
}
