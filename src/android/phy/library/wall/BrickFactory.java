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
package android.phy.library.wall;

/**
 * A factory of bricks
 * 
 * @author Davy
 */
public interface BrickFactory
{
	/**
	 * Create a brick
	 * @param wall the wall containing the brick
	 * @param x the x coordinate of the brick in the wall
	 * @param y the y coordinate of the brick in the wall
	 */
	public Brick creatBrick(Wall wall, int x, int y);
}
