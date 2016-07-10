package kr.ac.kaist.se.deltatable.model;

import java.security.InvalidParameterException;
import java.util.Vector;

public class BulkDTSystem<CT, ET> {
	private Vector<DTLink<ET>> links;
	private Vector<DTMembership<CT, ET>> memberships;
	private DTSystemObjectLayer<CT, ET> objectLayer;
	
	public BulkDTSystem()
	{
		links = new Vector<DTLink<ET>>();
		objectLayer = new DTSystemObjectLayer<CT, ET>();
		memberships = new Vector<DTMembership<CT, ET>>();
	}
	
	public DTClass<CT> addClass(CT origin)
	{
		return objectLayer.addClass(origin);
	}

	public DTEntity<ET> addMethod(DTClass<CT> owner, ET origin)
	{
		DTEntity<ET> entity = objectLayer.addMethod(origin);
		memberships.add(new DTMembership<CT, ET>(owner, entity));
		return entity;
	}
	
	public DTEntity<ET> addField(DTClass<CT> owner, ET origin)
	{
		DTEntity<ET> entity = objectLayer.addField(origin);
		memberships.add(new DTMembership<CT, ET>(owner, entity));
		return entity;
	}
	
	public DTEntity<ET> addEntity(DTClass<CT> owner, ET origin, boolean isMethod)
	{
		DTEntity<ET> entity = objectLayer.addEntity(origin, isMethod);
		memberships.add(new DTMembership<CT, ET>(owner, entity));
		return entity;
	}
	
	public DTEntity<ET> addEntity(CT originOwner, ET origin, boolean isMethod)
	{
		DTClass<CT> owner = objectLayer.getDTClass(originOwner);
		return addEntity(owner, origin, isMethod);
	}
	
	
	public void addLink(ET from, ET to)
	{
		DTEntity<ET> fromEntity = objectLayer.getDTEntity(from);
		DTEntity<ET> toEntity = objectLayer.getDTEntity(to);
		if( fromEntity != null && toEntity != null )
		{
			if( fromEntity.getType() == DTEntityType.DTMethod )
			{
				if( toEntity.getType() == DTEntityType.DTField )
				{
					links.add(new DTLink<ET>(fromEntity, toEntity, DTLinkType.AttributeReference));
				}
				else
				{
					links.add(new DTLink<ET>(fromEntity, toEntity, DTLinkType.MethodCall));
				}
				
				fromEntity.addReference(toEntity);
			}
			else
			{
				throw new InvalidParameterException("Field cannot be from-part of link");
			}
		}
		else
		{
			throw new InvalidParameterException("Not registered entity element");
		}
	}

	public Vector<DTLink<ET>> getLinks() {
		return links;
	}

	public Vector<DTMembership<CT, ET>> getMemberships() {
		return memberships;
	}

	public DTSystemObjectLayer<CT, ET> getObjectLayer() {
		return objectLayer;
	}
}
