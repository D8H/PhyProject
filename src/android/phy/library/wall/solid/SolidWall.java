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
package android.phy.library.wall.solid;

import android.phy.core.solid.Solid;
import android.phy.core.word.World;
import android.phy.library.ball.wall.BallWallCollision;
import android.phy.library.wall.Wall;

/**
 * A wall usable in the {@link World}.
 * This common interface allow to use collisions like {@link BallWallCollision}.
 * 
 * @author Davy
 */
public interface SolidWall<E extends SolidContent> extends Solid, Wall<E>
{
	@Override
	public SolidBrick<E> get(int x, int y);
	
	@Override
	public SolidFragment<E> getFragment(int fragmentX, int fragmentY);
}
