/*******************************************************************************
 * Copyright 2012, 2013 Davy H�lard
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
 * A fragment of brick
 * @author Davy
 */
public interface Fragment<E>
{
	/**
	 * Return the brick that contains the fragment.
	 * @return the brick that contains the fragment
	 */
	public Brick<E> getBrick();
	/**
	 * Return the location of the fragment in the brick.
	 * @return the location of the fragment in the brick
	 */
	public Brick.FragmentLocation getLocation();
	/**
	 * Return the x coordinate of the fragment.
	 * @return the x coordinate of the fragment
	 */
	public int getX();
	/**
	 * Return the y coordinate of the fragment.
	 * @return the y coordinate of the fragment
	 */
	public int getY();
	
	/**
	 * Return the content in the fragment
	 * @return the content in the fragment
	 */
	public E getContent();
	
	/**
	 * Set the content in the fragment
	 * @param content the content to set in the fragment
	 */
	public void setContent(E content);
}
