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

/**
 * Thrown to indicate that an index is nonsense. Indeed only 4 coordinates over 9 are valid.
 * @see Wall#getFragment(int, int)
 * 
 * @author Davy
 */
public class UnconsistentFragmentIndexException extends RuntimeException
{
	private static final long serialVersionUID = -4814374771131510415L;

	public UnconsistentFragmentIndexException()
	{
		super();
	}

	public UnconsistentFragmentIndexException(int fragmentX, int fragmentY)
	{
		super("(" + fragmentX + ", " + fragmentY + ")");
	}
}
