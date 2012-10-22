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
package android.phy.common.solid;

import android.graphics.PointF;
import android.phy.core.solid.MoveableSolid;
import android.phy.core.word.World;
import android.util.FloatMath;


public class AbstractMouvableSolid implements MoveableSolid, Cloneable
{
	private World world;
	private float mass;
	private PointF location;
	protected float angle;
	protected PointF speed;
	
	
	public AbstractMouvableSolid(World world, float mass, float x, float y)
	{
		this.world = world;
		this.mass = mass;
		location = new PointF(x, y);
		speed = new PointF();
	}

	@Override
	public PointF getLocation()
	{
		return location;
	}

	@Override
	public void setLocation(PointF location)
	{
		this.location = location;
	}
	
	@Override
	public void setLocation(float x, float y)
	{
		this.location.x = x;
		this.location.y = y;
	}
	
	@Override
	public void move(float seconds)
	{
		location.x += speed.x * seconds;
		location.y += speed.y * seconds;
	}

	@Override
	public PointF getSpeed()
	{
		return speed;
	}

	@Override
	public void setSpeed(float speedX, float speedY)
	{
		speed.x = speedX;
		speed.y = speedY;
		angle = (float) Math.atan2(speedY, speedX); //TODO angleIsUpToDate
	}

	@Override
	public World getWorld()
	{
		return world;
	}
	
	@Override
	public float getAngle()
	{
		return angle;
	}
	
	@Override
	public void setAngle(float angle)
	{
		this.angle = angle;
		
		float speed = this.speed.length();
		this.speed.x = speed * FloatMath.cos(angle);
		this.speed.y = speed * FloatMath.sin(angle);
	}
	
	public void setAngleTo(float targetX, float targetY)
	{
		angle = (float) Math.atan2(targetY - location.y, targetX - location.x);
	}
	
	@Override
	public void setSpeed(float speed)
	{
		this.speed.x = speed * FloatMath.cos(angle);
		this.speed.y = speed * FloatMath.sin(angle);
	}
	
	@Override
	public void multiplySpeed(float factor)
	{
		speed.x *= factor;
		speed.y *= factor;
	}
	
	@Override
	protected AbstractMouvableSolid clone()
	{
		AbstractMouvableSolid clone;
		try
		{
			clone = (AbstractMouvableSolid) super.clone();
			clone.location = new PointF();
			clone.speed    = new PointF();
			clone.location.set(location);
			clone.speed.set(location);
		}
		catch (CloneNotSupportedException e)
		{
			clone = null;
		}
		return clone;
	}

	@Override
	public float getMass()
	{
		return mass;
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this))
				+ " [mass=" + mass + ", location=(" + location.x + " " + location.y
				+ "), angle=" + angle + ", speed=(" + speed.x + " " + speed.y + ")]";
	}
}
