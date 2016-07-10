package kr.ac.kaist.se.deltatable.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DTSystemObjectLayer<CT, ET> {
	private Vector<DTClass<CT>> classes;
	private Vector<DTEntity<ET>> entities;

	private Map<CT, DTClass<CT>> classMap;
	private Map<ET, DTEntity<ET>> entityMap;
	private int methodSize;

	public DTSystemObjectLayer() {
		classes = new Vector<DTClass<CT>>();
		entities = new Vector<DTEntity<ET>>();
		
		classMap = new HashMap<CT, DTClass<CT>>();
		entityMap = new HashMap<ET, DTEntity<ET>>();
		
		methodSize = 0;
	
	}
	
	public Vector<DTClass<CT>> getClasses() {
		return classes;
	}

	public Vector<DTEntity<ET>> getEntities() {
		return entities;
	}

	public int getMethodSize()
	{
		return methodSize;
	}
	
	public int getFieldSize()
	{
		return entities.size() - methodSize;
	}
	
	public int getClassSize()
	{
		return classes.size();
	}
	
	public int getEntitySize()
	{
		return entities.size();
	}
	
	public DTClass<CT> getDTClass(CT origin)
	{
		return classMap.get(origin);
	}
	
	public DTEntity<ET> getDTEntity(ET origin)
	{
		return entityMap.get(origin);
	}

	public DTClass<CT> addClass(CT origin)
	{
		int index = classes.size();
		DTClass<CT> clazz = new DTClass<CT>(origin, index);
		classes.add(clazz);
		classMap.put(origin, clazz);
		return clazz;
	}

	public DTEntity<ET> addMethod(ET origin)
	{
		DTEntity<ET> entity = new DTEntity<ET>(origin, DTEntityType.DTMethod);
		addEntity(entity, methodSize);
		methodSize++;
		return entity;
	}
	
	public DTEntity<ET> addField(ET origin)
	{
		DTEntity<ET> entity = new DTEntity<ET>(origin, DTEntityType.DTField);
		addEntity(entity, entities.size());
		return entity;
	}
	
	public DTEntity<ET> addEntity(ET origin, boolean isMethod)
	{
		DTEntity<ET> entity;
		
		if( isMethod )
		{
			entity = new DTEntity<ET>(origin, DTEntityType.DTMethod);
			
		}
		else
		{
			entity = new DTEntity<ET>(origin, DTEntityType.DTField);
		}
		
		addEntity(entity, entities.size());
		return entity;
	}
	
	public void addEntity(DTEntity<ET> entity, int index)
	{
		entity.setIndex(index);
		entities.add(index, entity);
		entityMap.put(entity.getOrigin(), entity);
		
		for( int i = index + 1; i < entities.size(); i++ )
		{
			entities.get(i).setIndex(i);
		}
	}
}