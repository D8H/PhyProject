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
package android.phy.core.collision;

import android.phy.core.solid.MoveableSolid;
import android.phy.core.solid.Solid;
import android.phy.util.Pair;


/**
 * A collision between two kind of {@link Solid}.
 * @author Davy
 */
public interface Collision
{
	/**
	 * Test an eventual collision between two {@link Solid}.
	 * <p>
	 * Most of calculus should done in this method and results pass to {@link Collision#apply(CollisionEvent)} through the event.
	 * In no way the solids must be modify; It is the aim of {@link Collision#apply(CollisionEvent)}.
	 * </p>
	 * <p>
	 * The application of collisions is separated from its calculus, because otherwise calculus would be done over a state that was modified by previous collision calculus.
	 * This would result to a calculus which dependents on the order of collision calculus. The result would be hard to predict and calculus to debug.
	 * Moreover this separation can be beneficial for parallelization.
	 * </p>
	 * @param moveableSolid
	 * @param solid
	 * @return
	 */
	public CollisionEvent testOn(MoveableSolid moveableSolid, Solid solid);
	/**
	 * Apply the collision on the two solids.
	 * Avoid as much as possible to do calculus in this method.
	 * @param collisionEvent the collision event which contains all needed data to apply collision.
	 * @see Collision#testOn(MoveableSolid, Solid)
	 */
	public void apply(CollisionEvent collisionEvent);
	/**
	 * Return the iterable of all pair of solids concerned by this collision
	 * @return this iterable
	 */
	public Iterable<? extends Pair<? extends MoveableSolid, ? extends Solid>> getCollisionIterable();
}
