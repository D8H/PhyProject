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
 * An iterable over the pair of cardinal product of specified iterable excluding {e, e}.
 * For instance, if elements are (a, b, c), this iterable will be ({b, a}, {c, a), {c, b}).
 * It's the lower triangle of the matrix vertical(elements) x horizontal(elements).
 *
 * @param <E> the type of elements
 * 
 * @author Davy
 */
public class IntraIterablePairIterable<E> implements Iterable<Pair<E, E>>
{
	private Iterable<? extends E> elements;
	
	
	/**
	 * @param elements
	 */
	public IntraIterablePairIterable(Iterable<? extends E> elements)
	{
		this.elements = elements;
	}
	
	/**
	 * @return
	 */
	public Iterable<? extends E> getElements()
	{
		return elements;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Pair<E, E>> iterator()
	{
		return new PairIterator();
	}
	
	private class PairIterator implements Iterator<Pair<E, E>>
	{
		private Iterator<? extends E> firstIterator;
		private Iterator<? extends E> secondIterator;
		private Pair<E, E> nextPair;
		private Pair<E, E> nextNextPair;
		
		public PairIterator()
		{
			firstIterator  = elements.iterator();
			secondIterator = elements.iterator();
			
			if (firstIterator.hasNext())
			{
				firstIterator.next();
				if (firstIterator.hasNext())
				{
					E first  = firstIterator.next();
					E second = secondIterator.next();
					
					nextNextPair = new Pair<E, E>(first, second);
					nextPair = new Pair<E, E>();
				}
				else
				{
					nextNextPair = null;
				}
			}
			else
			{
				nextNextPair = null;
			}
		}
		
		@Override
		public boolean hasNext()
		{
			return nextNextPair != null;
		}

		@Override
		public Pair<E, E> next()
		{
			Pair<E, E> pair = nextNextPair;
			nextNextPair = nextPair;
			nextPair = pair;
			nextNextPair.first = nextPair.first;
			
			E second = secondIterator.next();
			if (second == nextNextPair.first)
			{
				if (firstIterator.hasNext())
				{
					nextNextPair.first = firstIterator.next();
					secondIterator = elements.iterator();
					nextNextPair.second = secondIterator.next(); // no secondIterator.hasNext() needed, we now its not empty
				}
				else
				{
					nextNextPair = null;
				}
			}
			else
			{
				nextNextPair.second = second;
			}
			
			return nextPair;
		}

		@Override
		public void remove()
		{
		}
	}
}
