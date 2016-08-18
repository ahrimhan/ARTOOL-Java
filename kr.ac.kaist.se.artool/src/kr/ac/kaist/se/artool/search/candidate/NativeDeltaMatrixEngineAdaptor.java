package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodApplicabilityChecker;
import kr.ac.kaist.se.deltatable.DeltaTableEngine;
import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator;
import kr.ac.kaist.se.deltatable.DeltaTableInfo;

public class NativeDeltaMatrixEngineAdaptor implements DeltaMatrixEngine {

	private SystemEntitySet ses;
//	private DeltaTable<AOMClass, AOMEntity> dt;
	    
	private DeltaTableEngine engine;
	
	public NativeDeltaMatrixEngineAdaptor(SystemEntitySet ses)
	{
		this.ses = ses;
//		
//		BulkDTSystem<AOMClass, AOMEntity> bulkSystem = new BulkDTSystem<AOMClass, AOMEntity>();
//		
//		ses.classes.forEach(clazz -> { bulkSystem.addClass(clazz); });
//		ses.methods.forEach(method -> { bulkSystem.addEntity(method.getOwner(), method, true); });
//		ses.fields.forEach(field -> { bulkSystem.addEntity(field.getOwner(), field, false); } );
//		dt = DeltaTable.getInstance(bulkSystem);
		
		
		DeltaTableInfo info = DeltaTableInfo.getInstance(ses.classes.size(), ses.entities.size(), ses.methods.size(), ses.methodsPossibleToMove.size());
		
		for( AOMMethod method : ses.methods )
		{
			int fromEntityIdx = method.getIndex();
			
			if( method.getOwnedScope() != null )
			{
				for( StaticMethodCall smc : method.getOwnedScope().getStaticMethodCalls() )
				{
					int toEntityIdx = smc.getCallee().getIndex();
					info.addLink(fromEntityIdx, toEntityIdx);
					
				}
				
				
				for( StaticFieldAccess sfa : method.getOwnedScope().getStaticFieldAccesses() )
				{
					int toEntityIdx = sfa.getAccessedField().getIndex();
					info.addLink(fromEntityIdx, toEntityIdx);
				}
			}
		}
		
		for( AOMMethod method : ses.methods )
		{
			info.addMembership(method.getIndex(), method.getOwner().getIndex());
		}
		
		for( AOMField field : ses.fields )
		{
			info.addMembership(field.getIndex(), field.getOwner().getIndex());
		}
		
		
		int possibleMoveMethodCount = 0;
		
		for( int i = 0; i < ses.methods.size(); i++ )
		{
			AOMMethod movingMethod = ses.methods.get(i);
			
			if( MoveMethodApplicabilityChecker.isApplicableForGivenMethod(movingMethod) )
			{	
				for( int j = 0; j < ses.classes.size(); j++ )
				{	
					AOMClass targetClass = ses.classes.get(j);	
					if( movingMethod.getOwner() != targetClass &&
							MoveMethodApplicabilityChecker.isApplicableForTargetClass(movingMethod, targetClass) )
					{
						info.possibleMoveMethod(i, j);
						possibleMoveMethodCount++;
					}
				}
			}
		}
		
		System.err.println("Possible Candidate Count:" + possibleMoveMethodCount);
		
		engine = DeltaTableEngine.getInstance(info);
	}
	
	@Override
	public void moveMethodPerformed(AOMClass fromClass, AOMMethod method, AOMClass toClass, boolean isRollbackAction) {
		engine.move(method.getIndex(), fromClass.getIndex(), toClass.getIndex());
	}
	
	public void printMemStat(String header)
	{
		float mb =  1024.0f; 
		 
		// get Runtime instance
		Runtime instance = Runtime.getRuntime();
 
		System.out.println("[" + header + "] ***** Heap utilization statistics [KB] *****\n");
 
		// available memory
		System.out.println("Total Memory: " + instance.totalMemory() / mb);
 
		// free memory
		System.out.println("Free Memory: " + instance.freeMemory() / mb);
 
		// used memory
		System.out.println("Used Memory: "
				+ (instance.totalMemory() - instance.freeMemory()) / mb);
 
		// Maximum available memory
		System.out.println("Max Memory: " + instance.maxMemory() / mb);
	}

	@Override
	public CandidateIterator getCandidateIterator(int maxCandidateCount) {
//		printMemStat("Before Eval");
		engine.eval();
//		printMemStat("After Eval");
		DeltaTableEntryIterator iterator = engine.getTopK(maxCandidateCount);
//		printMemStat("After getTopK");

		
		CandidateIterator ret = new NativeDeltaMatrixCandidateIteratorAdaptor(ses, iterator);
//		printMemStat("After Create CandidateIterator");

		
		return ret;
	}

	@Override
	public void setAdjust(boolean adjustOption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCohesiveFactorRate(double coupling, double cohesive) {
		// TODO Auto-generated method stub
		
	}

}
