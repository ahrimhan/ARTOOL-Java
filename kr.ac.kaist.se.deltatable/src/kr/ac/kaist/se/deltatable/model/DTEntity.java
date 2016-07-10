package kr.ac.kaist.se.deltatable.model;

import java.util.HashSet;
import java.util.Set;

public class DTEntity<ET> {
	private int index;
	private ET origin;
	private DTEntityType type;
	
	private HashSet<DTEntity<ET>> reference;
	
	public DTEntity(ET origin, DTEntityType type)
	{
		this.origin = origin;
		this.index = -1;
		this.type = type;
		this.reference = new HashSet<DTEntity<ET>>();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ET getOrigin() {
		return origin;
	}

	public void setOrigin(ET origin) {
		this.origin = origin;
	}

	public DTEntityType getType() {
		return type;
	}

	public void setType(DTEntityType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode()
	{
		return origin.hashCode();
	}
	
	@Override
	public boolean equals(Object entity)
	{
		if( entity instanceof DTEntity )
		{
			return origin.equals(((DTEntity<?>)entity).origin);
		}
		return false;
	}
	
	protected void addReference(DTEntity<ET> entity)
	{
		if( (entity.getType() == DTEntityType.DTField && getType() == DTEntityType.DTMethod) ||
				(entity.getType() == DTEntityType.DTMethod && getType() == DTEntityType.DTField) )
		{
			reference.add(entity);
			entity.reference.add(this);
		}
	}
	
	public Set<DTEntity<ET>> getReference()
	{
		return reference;
	}

}
