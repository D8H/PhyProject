package android.phy.library.wall;

import java.util.EventObject;

public class WallEvent<E> extends EventObject
{
	private Fragment<E> fragment;
	private E previousContent;
	
	public WallEvent(Wall<E> source, Fragment<E> fragment, E previousContent)
	{
		super(source);
		this.fragment = fragment;
		this.previousContent = previousContent;
	}
	
	public Fragment<E> getFragment()
	{
		return fragment;
	}
	
	public E getPreviousContent()
	{
		return previousContent;
	}

	@Override
	public Wall<E> getSource()
	{
		return (Wall<E>) super.getSource();
	}
}
