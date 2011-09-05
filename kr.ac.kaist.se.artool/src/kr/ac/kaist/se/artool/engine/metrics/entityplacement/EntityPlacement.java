package kr.ac.kaist.se.artool.engine.metrics.entityplacement;

import java.util.HashSet;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public abstract class EntityPlacement {
	
	protected abstract AOMBag<AOMEntity> getEntitySet(AOMMethod method);
	protected abstract AOMBag<AOMEntity> getEntitySet(AOMField field);
	protected abstract boolean useUnique();

	protected EntityPlacement() { };
	
	private static DynamicEntityPlacement dep = null;
	private static StaticEntityPlacement sep = null;
	
	public static double calculate(AbstractObjectModel aom, boolean isDynamic)
	{
		double ret = 0;
		if( isDynamic )
		{
			if( dep == null ) dep = new DynamicEntityPlacement();
			ret = dep.getEntityPlacement(aom);
		}
		else
		{
			if( sep == null ) sep = new StaticEntityPlacement();
			ret = sep.getEntityPlacement(aom);
		}
		return ret;
	}
	
	private AOMBag<AOMEntity> getEntitySet(AOMClass clazz)
	{
		AOMBag<AOMEntity> ret = new AOMBag<AOMEntity>(useUnique());
		
		for( AOMMethod method : clazz.getMethods() )
		{
			ret.addAll(getEntitySet(method));
		}
		
		for( AOMField field : clazz.getFields() )
		{
			ret.addAll(getEntitySet(field));
		}
		
		return ret;
	}
	
	private AOMBag<AOMEntity> getEntitySet(AOMEntity entity)
	{
		if( entity instanceof AOMMethod ) return getEntitySet((AOMMethod)entity);
		else if( entity instanceof AOMField ) return getEntitySet((AOMField)entity);
		return null;
	}
	
	private double getDistance(AOMEntity entity, AOMClass clazz)
	{
		double ret = 1;
		
		AOMBag<AOMEntity> entityEntitySet = getEntitySet(entity);
		AOMBag<AOMEntity> classEntitySet = getEntitySet(clazz);
		
		if( classEntitySet.contains(entity) )
		{
			classEntitySet.remove(entity);
		}
		
		AOMBag<AOMEntity> intersect = entityEntitySet.intersect(classEntitySet);
		AOMBag<AOMEntity> union = entityEntitySet.union(classEntitySet);
		
		
		ret -= (((double)intersect.size()) / ((double)union.size()));
		
		return ret;
	}
	
	protected double getEntityPlacement(AbstractObjectModel aom)
	{
		HashSet<AOMEntity> allEntitySet = new HashSet<AOMEntity>();
		double ret = 0;
		double allEntityCount = 0;
		
		for( AOMClass clazz : aom.getClasses() )
		{
			allEntitySet.addAll(clazz.getMethods());
			allEntitySet.addAll(clazz.getFields());
		}
		
		allEntityCount = allEntitySet.size();
		
		for( AOMClass clazz : aom.getClasses() )
		{
			long clazzEntityCount = clazz.getMethods().size() + clazz.getFields().size();
			
			ret += clazzEntityCount * getEntityPlacement(clazz, allEntitySet, clazzEntityCount) / allEntityCount;
		}
		
		return ret;
	}

	private double getEntityPlacement(AOMClass clazz, HashSet<AOMEntity> allEntitySet, long clazzEntityCount) {
		double ret = 0;
		
		long internalTotalDistance = 0;
		long externalTotalDistance = 0;
		
		for(AOMEntity entity : allEntitySet )
		{
			if( clazz.getMethods().contains(entity) || clazz.getFields().contains(entity) )
			{
				internalTotalDistance += getDistance(entity, clazz);
			}
			else
			{
				externalTotalDistance += getDistance(entity, clazz);
			}
		}
		
		ret = (  ((double)internalTotalDistance) / ((double)clazzEntityCount) ) /
		      (  ((double)externalTotalDistance) / ((double)(allEntitySet.size() - clazzEntityCount)) );
		
		return ret;
	}
	

}
