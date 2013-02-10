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
package android.phy.library.ball;
import android.phy.common.solid.AbstractMouvableSolid;
import android.phy.core.word.World;


public class Ball extends AbstractMouvableSolid implements Cloneable
{
	private float radius;
	private float defaultAngle;
	
	
	public Ball(World world, float mass, float x, float y, float radius)
	{
		this(world, mass, x, y, radius, 0);
	}
	
	public Ball(World world, float mass, float x, float y, float radius, float defaultAngle)
	{
		super(world, mass, x, y);
		this.radius = radius;
		this.defaultAngle = defaultAngle;
	}
	
	public float getRadius()
	{
		return radius;
	}
	
	public void setRadius(float radius)
	{
		this.radius = radius;
	}
	
	public void bounceHorizontally()
	{
		speed.y = -speed.y;
		angle = (float) (-angle + defaultAngle * (Math.random() - 0.5));
	}
	
	public void bounceVertically()
	{
		speed.x = -speed.x;
		angle = (float) (Math.PI - angle + defaultAngle * (Math.random() - 0.5));
	}
	
	public void bounce(float normalX, float normalY)
	{
		float tangentAngle = (float) Math.atan2(-normalX, normalY);
		setAngle(tangentAngle + (tangentAngle - angle));
	}
	
	public float getSpeedNorm()
	{
		return (float) Math.hypot(getSpeed().x, getSpeed().y);
	}
	
	public boolean contains(float x, float y)
	{
		float deltaX = x - getLocation().x;
		float deltaY = y - getLocation().y;
		return deltaX * deltaX + deltaY * deltaY < radius * radius;
	}
	
	@Override
	public Ball clone()
	{
		Ball clone;
		clone = (Ball) super.clone();
		return clone;
	}
}
