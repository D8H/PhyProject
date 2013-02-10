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
package android.phy.util;

import java.util.Iterator;

/**
 * An iterable over the cardinal product of two specified iterables.
 * For instance, if firsts are (1, 2, 3) and seconds (a, b, c), this iterable will be ((1, a), (2, b), (3, c)).
 *
 * @param <F> the type of first elements
 * @param <S> the type of second elements
 * 
 * @author Davy
 */
public class InterIterablePairIterable<F, S> implements Iterable<Pair<F, S>>
{
	private Iterable<? extends F> firsts;
	private Iterable<? extends S> seconds;
	
	
	/**
	 * @param moveableGroup
	 * @param otherGroup
	 */
	public InterIterablePairIterable(
			Iterable<? extends F> moveableGroup,
			Iterable<? extends S> otherGroup)
	{
		this.firsts = moveableGroup;
		this.seconds = otherGroup;
	}
	
	/**
	 * @return
	 */
	public Iterable<? extends F> getFirsts()
	{
		return firsts;
	}
	
	/**
	 * @return
	 */
	public Iterable<? extends S> getSeconds()
	{
		return seconds;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Pair<F, S>> iterator()
	{
		return new PairIterator();
	}
	
	private class PairIterator implements Iterator<Pair<F, S>>
	{
		private Iterator<? extends F> firstIterator;
		private Iterator<? extends S> secondIterator;
		private final Pair<F, S> pair;
		private final boolean isNotEmpty;
		
		public PairIterator()
		{
			pair = new Pair<F, S>();
			firstIterator = firsts.iterator();
			secondIterator = seconds.iterator();
			isNotEmpty = firstIterator.hasNext() && secondIterator.hasNext();
			if (isNotEmpty)
			{
				pair.first = firstIterator.next();
			}
		}
		
		@Override
		public boolean hasNext()
		{
			return isNotEmpty && (firstIterator.hasNext() || secondIterator.hasNext());
		}

		@Override
		public Pair<F, S> next()
		{
			if (! secondIterator.hasNext())
			{
				pair.first = firstIterator.next();
				secondIterator = seconds.iterator();
			}
			pair.second = secondIterator.next();
			return pair;
		}

		@Override
		public void remove()
		{
		}
	}
}
