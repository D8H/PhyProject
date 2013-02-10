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
package android.phy.library.force;

import android.phy.core.force.Force;
import android.phy.core.solid.MoveableSolid;

public class FrictionForce implements Force
{
	private float coefficientCompLog;
	
	public FrictionForce(float coefficient)
	{
		this.coefficientCompLog = (float) Math.log(1 - coefficient);
	}
	
	@Override
	public void applyOn(MoveableSolid moveableSolid, float seconds)
	{
		float t = seconds * coefficientCompLog;
		float coefficient = 1 + t + t * t / 2; // Taylor series of "seconds -> coefficientComp^seconds" continuous equivalent to  v(n+1) = (1-k) v(n)
		
		moveableSolid.multiplySpeed(coefficient);
	}
}
