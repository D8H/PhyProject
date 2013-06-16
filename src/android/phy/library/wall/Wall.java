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
package android.phy.library.wall;

import android.graphics.Rect;

/**
 * A wall composed of triangular fragments. Fours fragments form a square named {@link Brick}.
 * 
 * @author Davy
 */
public interface Wall<E>
{
	/**
	 * Return the bounds of the wall.
	 * <p>
	 * Valid brick indexes are x : [left, right[ and y : [top, bottom[
	 * </p>
	 * @return the bounds of the wall
	 */
	public Rect getBounds();
	/**
	 * Return the brick at the specified coordinates.
	 * @param x the x coordinate of the returned brick
	 * @param y the y coordinate of the returned brick
	 * @return the brick at the specified coordinates.
	 */
	public Brick<E> get(int x, int y);
	/**
	 * Return the fragment at the specified coordinates.
	 * <p>
	 * The fragment coordinates allow some symmetries.
	 * A brick contains 9 different coordinates :
	 * <table frame="border" rules="all">
	 *    <tr>
	 *        <td></td>
	 *        <td>north</td>
	 *        <td></td>
	 *    </tr>
	 *    <tr>
	 *        <td>west</td>
	 *        <td></td>
	 *        <td>east</td>
	 *    </tr>
	 *    <tr>
	 *        <td></td>
	 *        <td>south</td>
	 *        <td></td>
	 *    </tr>
	 * </table>
	 * <ul>
	 * <li>north : (+1, +0)</li>
	 * <li>south : (+1, +2)</li>
	 * <li>west : (+0, +1)</li>
	 * <li>east : (+2, +1)</li>
	 * </ul>
	 * </p>
	 * @param fragmentX the x coordinate of the returned fragment
	 * @param fragmentY the y coordinate of the returned fragment
	 * @return the fragment at the specified coordinates
	 */
	public Fragment<E> getFragment(int fragmentX, int fragmentY);
	/**
	 * Return the x coordinate of the specified fragment.
	 * @param fragment the fragment whose the coordinate is returned
	 * @return the x coordinate of the specified fragment
	 * @see Wall#getFragment(int, int)
	 */
	public int getFragmentX(Fragment<E> fragment);
	/**
	 * Return the y coordinate of the specified fragment.
	 * @param fragment the fragment whose the coordinate is returned
	 * @return the y coordinate of the specified fragment
	 * @see Wall#getFragment(int, int)
	 */
	public int getFragmentY(Fragment<E> fragment);
	
	/**
	 * @param listener
	 */
	public void addWallChangeListener(WallChangeListener<E> listener);
	
	/**
	 * @param listener
	 */
	public void removeWallChangeListener(WallChangeListener<E> listener);
	
	/**
	 * @param fragment
	 * @param previousContent
	 */
	public void fireWallFragmentChanged(Fragment<E> fragment, E previousContent);
}
