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
package android.phy.common.solid;

import android.phy.core.word.World;


public class AbstractRotableSolid extends AbstractMouvableSolid implements RotableSolid
{
	private float rotationSpeed;
	
	public AbstractRotableSolid(World world, float mass, float x, float y)
	{
		super(world, mass, x, y);
		rotationSpeed = 0;
	}
	
	@Override
	public void move(float seconds)
	{
		super.move(seconds);
		angle += rotationSpeed * seconds;
	}
	
	@Override
	public float getRotationSpeed()
	{
		return rotationSpeed;
	}
	
	@Override
	public void setRotationSpeed(float rotationSpeed)
	{
		this.rotationSpeed = rotationSpeed;
	}
}
