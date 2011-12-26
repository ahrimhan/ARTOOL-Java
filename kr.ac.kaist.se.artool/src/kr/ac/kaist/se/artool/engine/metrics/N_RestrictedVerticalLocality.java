package kr.ac.kaist.se.artool.engine.metrics;

import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.MultiKeyMap;

public abstract class N_RestrictedVerticalLocality {
	private MultiKeyMap<AOMClass, int[]>[] rule4list_Class;
	private MultiKeyMap<AOMMethod, int[]>[] rule4list_Method;	
	private 		Vector<StaticMethodCall> containedDynamictoStaticMethodCall = new Vector<StaticMethodCall>();
	private int locality_from;
	private int locality_to;


	public N_RestrictedVerticalLocality(int locality_from, int locality_to)
	{
		this.locality_from = locality_from;
		this.locality_to  = locality_to;
		
		rule4list_Class = new MultiKeyMap[locality_to - locality_from + 1];
		rule4list_Method = new MultiKeyMap[locality_to - locality_from + 1];
		for( int i = 0; i < locality_to - locality_from + 1; i++ )
		{
			rule4list_Class[i] = new MultiKeyMap<AOMClass, int[]>();
			rule4list_Method[i] = new MultiKeyMap<AOMMethod, int[]>();
		}
	}
	

	private static N_RestrictedVerticalLocality dynamic_instance = null;
	private static N_RestrictedVerticalLocality static_instance = null;
	
	public static N_RestrictedVerticalLocality createInstance(boolean isDynamic, int locality_from, int locality_to)
	{
		N_RestrictedVerticalLocality ret;
		
		if( isDynamic )
		{
			if( dynamic_instance == null )
			{
				dynamic_instance = new N_RestrictedVerticalLocality_Dynamic(locality_from, locality_to);
			}
			ret = dynamic_instance;
		}
		else
		{
			if( static_instance == null )
			{
				static_instance = new N_RestrictedVerticalLocality_Static(locality_from, locality_to);
			}
			ret = static_instance;
		}
		
		return ret;
	}
	
	protected abstract String getN_DCICMString();
	
	protected abstract boolean checkLocalityOption(int ndcicm, int numMethodCall);


	public Map.Entry<MultiKey<AOMMethod>, int[]>[] getSortedMethodLevel(int locality, int cutline)
	{
		return UtilityFunctions.getInstance().__getSortedIBDP(rule4list_Method[locality - locality_from], cutline);
	}

	public Map.Entry<MultiKey<AOMClass>, int[]>[] getSortedClassLevel(int locality, int cutline)
	{
		return UtilityFunctions.getInstance().__getSortedIBDP(rule4list_Class[locality - locality_from], cutline);
	}
	
	public void measure(AbstractObjectModel aom, N_DCICM n_dcicm)
	{
		int ndcicm = 0;
		int numStaticMethodCalls = 0;
		
		for( int i = 0; i < (locality_to - locality_from + 1); i++ )
		{
			rule4list_Class[i].clear();
			rule4list_Method[i].clear();
		}
		containedDynamictoStaticMethodCall.clear();
		
		AOMClass[] aomClasses;
		AOMMethod[] aomMethods;
		AOMClass[] source_targetClasses = new AOMClass[2];
		AOMMethod[] source_targetMethods  = new AOMMethod[2];
		
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				ndcicm = BasicMetricSuite.getInt(method.getMeasuredDataSet().get(getN_DCICMString()));
				aomClasses = n_dcicm.getDCICM().get(method);
				aomMethods = n_dcicm.getMap4N_DMICM().get(method);
				
				if( method.getOwnedScope() != null )
				{
		
					//end: ignore quantity effect of dynamic (just count as one)
					numStaticMethodCalls = method.getOwnedScope().getStaticMethodCalls().size();
					
					//FIXME: 3 adjustable!
					if( ndcicm >= locality_from && numStaticMethodCalls != 0 && checkLocalityOption(ndcicm, numStaticMethodCalls))
					{
						for( int i = 0; i < aomClasses.length - 1; i++)
						{
							for( int j = i + 1; j < aomClasses.length; j++  )
							{
								source_targetClasses[0] = aomClasses[i];
								source_targetClasses[1] = aomClasses[j];
								for( int locality = locality_from; locality <= locality_to && locality <= ndcicm; locality++ )
								{
									UtilityFunctions.getInstance().increase(rule4list_Class[locality - locality_from], source_targetClasses[0], source_targetClasses[1]);
								}
							}
						}
						
						for(int i = 0; i < aomMethods.length - 1 ; i++ )
						{
							for( int j = i + 1; j < aomMethods.length; j++  )
							{
								source_targetMethods[0] = aomMethods[i];
								source_targetMethods[1] = aomMethods[j];
								for( int locality = locality_from; locality <= locality_to && locality <= ndcicm; locality++ )
								{
									UtilityFunctions.getInstance().increase(rule4list_Method[locality - locality_from], source_targetMethods[0], source_targetMethods[1]);
								}
							}
						}
					}
				}
				ndcicm =0;
				numStaticMethodCalls = 0;
			}
		}
	}
}
