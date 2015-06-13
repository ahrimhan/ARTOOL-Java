package kr.ac.kaist.se.artool.engine.metrics.entityplacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public abstract class EntityPlacement {
	protected HashMap<AOMEntity, AOMBag<AOMEntity>> cache;
	protected abstract AOMBag<AOMEntity> _getEntitySet(AOMMethod method);
	protected abstract AOMBag<AOMEntity> _getEntitySet(AOMField field);
	protected abstract boolean useUnique();
	protected HashMap<AOMClass, HashMap<AOMEntity, Double>> distanceCache;
	
	private AOMBag<AOMEntity> getEntitySet(AOMMethod method)
	{
		if( cache.containsKey( method ) )
		{
			return cache.get(method);
		}
		else
		{
			AOMBag<AOMEntity> ret =  _getEntitySet(method);
			cache.put(method, ret);
			return ret;
		}
	}
	
	private AOMBag<AOMEntity> getEntitySet(AOMField field)
	{
		if( cache.containsKey( field ) )
		{
			return cache.get(field);
		}
		else
		{
			AOMBag<AOMEntity> ret =   _getEntitySet(field);
			cache.put(field, ret);
			return ret;
		}
	}

	protected EntityPlacement() { 
		cache = new HashMap<AOMEntity, AOMBag<AOMEntity>>(); 
		distanceCache = new HashMap<AOMClass, HashMap<AOMEntity, Double>>();
	}
	
	private static StaticEntityPlacement sep = null;
	
	public static double calculate(AbstractObjectModel aom, SortedSet<EPMoveMethodCandidate> candidates, int size)
	{
		double ret = 0;
		

		{
			if( sep == null ) sep = new StaticEntityPlacement();
			sep.cache.clear();
			sep.distanceCache.clear();
			ret = sep.getEntityPlacement(aom, candidates, size);
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
	
	private synchronized double getDistance(AOMEntity entity, AOMClass clazz)
	{
		double ret = 1;
		
		
		if( distanceCache.get(clazz) != null && distanceCache.get(clazz).get(entity) != null )
		{
			ret = distanceCache.get(clazz).get(entity);
		}
		else
		{
			AOMBag<AOMEntity> entityEntitySet = getEntitySet(entity);
			AOMBag<AOMEntity> classEntitySet = getEntitySet(clazz);
			
			if( classEntitySet.contains(entity) )
			{
				classEntitySet.remove(entity);
			}
			
			AOMBag<AOMEntity> intersect = entityEntitySet.intersect(classEntitySet);
			AOMBag<AOMEntity> union = entityEntitySet.union(classEntitySet);
			
			HashMap<AOMEntity, Double> subCache = null;
			if( distanceCache.get(clazz) == null )
			{
				subCache = new HashMap<AOMEntity, Double>();
				distanceCache.put(clazz, subCache);
			}
			else
			{
				subCache = distanceCache.get(clazz);
			}
			
			ret -= (((double)intersect.size()) / (((double)union.size()) + 0.0000001));
			
			subCache.put(entity, ret);
		}
		return ret;
	}
	
	protected double allEntityCount = 0;
	protected HashSet<AOMEntity> allEntitySet = null;
	protected double entityplacement = 0;
	protected SortedSet<EPMoveMethodCandidate> candidates;
	
	protected synchronized void addEntityPlacement(double ep)
	{
		entityplacement += ep;
	}
	
	private class MyRunner implements Runnable
	{
		
		private AOMClass clazz;
		private long clazzEntityCount;
		private int size;
		public MyRunner(AOMClass clazz, long clazzEntityCount, int size)
		{
			this.clazz = clazz;
			this.clazzEntityCount = clazzEntityCount;
			this.size = size;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			addEntityPlacement(clazzEntityCount * getEntityPlacementPrivate(clazz, allEntitySet, clazzEntityCount, candidates, size) / allEntityCount);
		}
		
	}
	
	
	protected double getEntityPlacement(AbstractObjectModel aom, SortedSet<EPMoveMethodCandidate> candidates, int size)
	{
		entityplacement = 0;
		if( allEntitySet == null )
		{
			allEntitySet = new HashSet<AOMEntity>();
			for( AOMClass clazz : aom.getClasses() )
			{
				for( AOMMethod method : clazz.getMethods() )
				{
					if( !((AOMMethod) method).getName().startsWith("set") && !((AOMMethod) method).getName().startsWith("get") )
					{
						allEntitySet.add(method);
					}
				}
				allEntitySet.addAll(clazz.getFields());
			}
			
			allEntityCount = allEntitySet.size();
		}
		
		this.candidates = candidates;
		
		List<AOMClass> classList = aom.getClasses();
		for(  int  i = 0; i < classList.size();  )
		{
			AOMClass clazz = classList.get(i);
			long clazzEntityCount = clazz.getMethods().size() + clazz.getFields().size();
			Thread t1 = new Thread(new MyRunner(clazz, clazzEntityCount, size));
			t1.start();
			i++;
			
			Thread t2 = null;

			if( i < classList.size() )
			{
				clazz = classList.get(i);
				clazzEntityCount = clazz.getMethods().size() + clazz.getFields().size();
				t2 = new Thread(new MyRunner(clazz, clazzEntityCount, size));
				t2.start();
				i++;
			}
			
			Thread t3 = null;

			if( i < classList.size() )
			{
				clazz = classList.get(i);
				clazzEntityCount = clazz.getMethods().size() + clazz.getFields().size();
				t3 = new Thread(new MyRunner(clazz, clazzEntityCount, size));
				t3.start();
				i++;
			}
			
			Thread t4 = null;

			if( i < classList.size() )
			{
				clazz = classList.get(i);
				clazzEntityCount = clazz.getMethods().size() + clazz.getFields().size();
				t4 = new Thread(new MyRunner(clazz, clazzEntityCount, size));
				t4.start();
				i++;
			}
			
			try {
				t1.join();
				if( t2 != null ) t2.join();
				if( t3 != null ) t3.join();
				if( t4 != null ) t4.join();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return entityplacement;
	}

	private double getEntityPlacementPrivate(AOMClass clazz, HashSet<AOMEntity> allEntitySet, long clazzEntityCount, SortedSet<EPMoveMethodCandidate> candidates, int size) {
		double ret = 0;
		
		long internalTotalDistance = 0;
		long externalTotalDistance = 0;
		
		for(AOMEntity entity : allEntitySet )
		{
			double distance;
			distance = getDistance(entity, clazz);
			
			if( (entity instanceof AOMMethod && ((AOMMethod) entity).getOwner() == clazz) ||
					(entity instanceof AOMField && ((AOMField) entity).getOwner() == clazz) )
			{
				internalTotalDistance += distance;
			}
			else
			{

				externalTotalDistance += distance;
				
				if( entity instanceof AOMMethod && distance < getDistance(entity, ((AOMMethod) entity).getOwner()) )
				{
					EPMoveMethodCandidate cd = new EPMoveMethodCandidate((AOMMethod)entity, clazz, distance);
					candidates.add(cd);
					if( candidates.size() > size )
					{
						candidates.remove(candidates.last());
					}
				}
			}
		}
		
		ret = (  ((double)internalTotalDistance) / ((double)clazzEntityCount) ) /
		      (  ((double)externalTotalDistance) / ((double)(allEntitySet.size() - clazzEntityCount)) + 0.0000001);
		
		return ret;
	}
	

}
