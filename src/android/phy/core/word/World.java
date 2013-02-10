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
package android.phy.core.word;

import java.util.ArrayList;
import java.util.Collection;

import android.phy.core.collision.Collision;
import android.phy.core.collision.CollisionEvent;
import android.phy.core.force.Force;
import android.phy.core.solid.MoveableSolid;
import android.phy.core.solid.Solid;
import android.phy.util.NTreeNode;
import android.phy.util.Pair;


/**
 * <p>
 * A word containing solids mobile or not. It defines the loop of the physic engine in the method {@link World#run} :
 * <ul>
 * <li>Move mobile solids {@link MoveableSolid}</li>
 * <li>Apply forces {@link Force}</li>
 * <li>Test collisions {@link Collision}</li>
 * <li>Apply collisions</li>
 * <li>fire that time shift {@link World#addWorldListener}</li>
 * </ul>
 * This class didn't know the calculus that are done.
 * It's the role of the classes citées previously.
 * For instance the calculus for collisions is defined in the implementations of the interface {@link Collision}.
 * This allows using this physic engine for applications that need specifics calculus.
 * <p>
 * </p>
 * A World is Runable. You may use a {@link Thread} or something.
 * </p>
 * @author Davy
 */
public class World implements Runnable
{
	private NTreeNode<Solid> solids;
	private NTreeNode<Solid> motionlessSolids;
	private NTreeNode<MoveableSolid> mobileSolids;
	private Collection<ForceDefinition> forceDefinitions;
	private Collection<Collision> collisions;
	private int minInterval;
	private boolean isOver;
	private int timeMillis;
	
	private final ArrayList<WorldListener> listeners = new ArrayList<WorldListener>();
	private boolean isPaused;
	
	
	/**
	 * Creates a word with a minimal interval between two state calculus
	 * @param minInterval this interval
	 */
	public World(int minInterval)
	{
		this.minInterval = minInterval;
		solids = new NTreeNode<Solid>();
		motionlessSolids = new NTreeNode<Solid>();
		mobileSolids = new NTreeNode<MoveableSolid>();
		solids.getChildren().add(motionlessSolids);
		solids.getChildren().add(mobileSolids);
		forceDefinitions = new ArrayList<World.ForceDefinition>();
		collisions = new ArrayList<Collision>();
		isOver = false;
		isPaused = true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		int lastInterval = minInterval;
		ArrayList<CollisionEvent> collisionEvents = new ArrayList<CollisionEvent>();
		while (! isOver)
		{
			synchronized (this)
			{
				while (isPaused && ! isOver)
				{
					try
					{
						this.wait();
					}
					catch (InterruptedException e)
					{
						isPaused = false;
						isOver = true;
						break;
					}
				}
			}
			if (! isOver)
			{
				//Log.v("test", "time : " + ((double) timeMillis / 1000));
				float lastIntervalSecondes = lastInterval / 1000.0f;
				long startTimeMillis = System.currentTimeMillis();
				
				for (MoveableSolid movableSolid : mobileSolids)
				{
					movableSolid.move(lastIntervalSecondes);
				}
				
				for (ForceDefinition forceDefinition : forceDefinitions)
				{
					Force force = forceDefinition.force;
					for (MoveableSolid solid : forceDefinition.solids)
					{
						force.applyOn(solid, lastIntervalSecondes);
					}
				}
				
				for (Collision collision : collisions)
				{
					//Log.v("test", "collision : " + collision);
					for (Pair<? extends MoveableSolid, ? extends Solid> pair : collision.getCollisionIterable())
					{
						MoveableSolid moveableSolid = pair.first;
						Solid otherSolid = pair.second;
						
						//Log.v("test", moveableSolid + " --> " + otherSolid);
						
						CollisionEvent collisionEvent = collision.testOn(moveableSolid, otherSolid);
						if (collisionEvent != null)
						{
							collisionEvents.add(collisionEvent);
						}
					}
				}
				
				for (CollisionEvent collisionEvent : collisionEvents)
				{
					Collision collision = collisionEvent.getSource();
					collision.apply(collisionEvent);
				}
				collisionEvents.clear();
				
				fireTimeShifted();
				//lastInterval = minInterval;
				
				lastInterval = (int) (System.currentTimeMillis() - startTimeMillis);
				if (lastInterval < minInterval)
				{
					try
					{
						Thread.sleep(minInterval - lastInterval);
						lastInterval = minInterval;
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				lastInterval = (int) (System.currentTimeMillis() - startTimeMillis);
				timeMillis += lastInterval;
			}
		}
	}

	/**
	 * Return all defined collisions.
	 * Modify the return collection in order to add or remove some definitions.
	 * @return all defined collisions.
	 */
	public Collection<Collision> getCollisions()
	{
		return collisions;
	}
	
	/**
	 * @param force
	 * @param solids
	 */
	public void addForceDefinition(Force force, Iterable<? extends MoveableSolid> solids)
	{
		ForceDefinition forceDefinition = new ForceDefinition();
		forceDefinition.force = force;
		forceDefinition.solids = solids;
		forceDefinitions.add(forceDefinition);
	}
	
	/**
	 * Return the tree root of all the solids mobile or not.
	 * @return the tree root of all the solids
	 */
	public NTreeNode<Solid> getSolids()
	{
		return solids;
	}
	
	/**
	 * Return the tree root of all motionless solids.
	 * Motionless solids may be add to the word using the returning tree.
	 * @return the tree root of all motionless solids.
	 */
	public NTreeNode<Solid> getMotionlessSolids()
	{
		return motionlessSolids;
	}
	
	/**
	 * Return the tree root of all mobile solids.
	 * Mobile solids may be add to the word using the returning tree.
	 * @return the tree root of all mobile solids.
	 */
	public NTreeNode<MoveableSolid> getMobileSolids()
	{
		return mobileSolids;
	}

	/**
	 * 
	 * @param listener
	 */
	public void addWorldListener(WorldListener listener)
	{
		listeners.add(listener);
	}
	
	/**
	 * @param listener
	 */
	public void removeWorldListener(WorldListener listener)
	{
		listeners.remove(listener);
	}
	
	/**
	 * @return
	 */
	public Collection<WorldListener> getWorldListeners()
	{
		return listeners;
	}
	
	private void fireTimeShifted()
	{
		for (WorldListener listener : getWorldListeners())
		{
			listener.timeShifted();
		}
	}
	
	/**
	 * Return the milliseconds from the beginning of the word
	 * @return the milliseconds from the beginning of the word
	 */
	public int getTimeMillis()
	{
		return timeMillis;
	}
	
	/**
	 * Change the milliseconds from the beginning of the word
	 * @param timeMillis those milliseconds
	 */
	public void setTimeMillis(int timeMillis)
	{
		this.timeMillis = timeMillis;
	}
	
	public synchronized void setOver(boolean isOver)
	{
		this.isOver = isOver;
		if (isOver)
		{
			this.notify();
		}
	}
	
	public boolean isOver()
	{
		return isOver;
	}

	/**
	 * Froze the world time.
	 * No state will be calculated before the method {@link World#resume} is call.
	 */
	public void pause()
	{
		isPaused = true;
	}
	
	/**
	 * Return true if the time is froze
	 * @return true if the time is froze
	 */
	public boolean isPaused()
	{
		return isPaused;
	}

	/**
	 * Unfroze the world time.
	 * @see World#pause()
	 */
	public synchronized void resume()
	{
		isPaused = false;
		this.notify();
	}
	
	private class ForceDefinition
	{
		public Force force;
		public Iterable<? extends MoveableSolid> solids;
	}
}
