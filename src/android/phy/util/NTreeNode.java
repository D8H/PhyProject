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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * This class represents an N-Tree node.
 * Each branch from a node is an <code>Iterable</code> that can be an <code>NTreeNode</code> too.
 * A branch that is not an <code>NTreeNode</code> (an {@link ArrayList} for instance) may contain the leafs.
 * 
 * @param <E> the type of leafs
 * 
 * @author Davy
 */
public class NTreeNode<E> implements Iterable<E>
{
	private Collection<Iterable<? extends E>> children;
	
	
	/**
	 * 
	 */
	public NTreeNode()
	{
		children = new ArrayList<Iterable<? extends E>>();
	}
	
	/**
	 * @return
	 */
	public Collection<Iterable<? extends E>> getChildren()
	{
		return children;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator()
	{
		return new AllNodesIterator();
	}
	
	private class AllNodesIterator implements Iterator<E>
	{
		private Iterator<Iterable<? extends E>> childrenIterator;
		private Iterator<? extends E> littleChildrenIterator;
		private E next;
		
		public AllNodesIterator()
		{
			childrenIterator = children.iterator();
			if (childrenIterator.hasNext())
			{
				littleChildrenIterator = (Iterator<? extends E>) childrenIterator.next().iterator();
				findNextNotEmptyLittleChildrenIterator();
			}
			else
			{
				next = null;
			}
		}

		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public E next()
		{
			E current = next;
			
			if (littleChildrenIterator.hasNext())
			{
				next = littleChildrenIterator.next();
			}
			else
			{
				findNextNotEmptyLittleChildrenIterator();
			}
			
			return current;
		}
		
		private void findNextNotEmptyLittleChildrenIterator()
		{
			while (childrenIterator.hasNext() && ! littleChildrenIterator.hasNext())
			{
				littleChildrenIterator = (Iterator<? extends E>) childrenIterator.next().iterator();
			}
			if (littleChildrenIterator.hasNext())
			{
				next = littleChildrenIterator.next();
			}
			else
			{
				next = null;
			}
		}

		@Override
		public void remove()
		{
			littleChildrenIterator.remove();
		}
	}
}
