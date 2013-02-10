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
package android.phy.core.collision;

import java.util.EventObject;

import android.phy.core.solid.MoveableSolid;
import android.phy.core.solid.Solid;
import android.phy.util.Pair;


public class CollisionEvent extends EventObject
{
	private static final long serialVersionUID = -7690003481229295023L;
	
	private MoveableSolid moveableSolid;
	private Solid solid;
	private Iterable<Pair<MoveableSolid, Solid>> collisionIterable;
	
	public CollisionEvent(Collision source, MoveableSolid moveableSolid, Solid solid)
	{
		super(source);
		this.moveableSolid = moveableSolid;
		this.solid = solid;
	}
	
	public MoveableSolid getMoveableSolid()
	{
		return moveableSolid;
	}
	
	public Solid getSolid()
	{
		return solid;
	}
	
	public Iterable<Pair<MoveableSolid, Solid>> getCollisionIterable()
	{
		return collisionIterable;
	}

	public void setCollisionIterable(Iterable<Pair<MoveableSolid, Solid>> collisionIterable)
	{
		this.collisionIterable = collisionIterable;
	}

	@Override
	public Collision getSource()
	{
		return (Collision) super.getSource();
	}
}
