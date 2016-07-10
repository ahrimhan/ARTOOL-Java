package kr.ac.kaist.se.artool.search.candidate;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodApplicabilityChecker;
import kr.ac.kaist.se.deltatable.DeltaTable;
import kr.ac.kaist.se.deltatable.DeltaTableEngine;
import kr.ac.kaist.se.deltatable.DeltaTableEntryIterator;
import kr.ac.kaist.se.deltatable.DeltaTableInfo;
import kr.ac.kaist.se.deltatable.MoveMethodValidityChecker;

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
		
		
		DeltaTableInfo info = DeltaTableInfo.getInstance(ses.classes.size(), ses.entities.size(), ses.methods.size());
		
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
	
		engine = DeltaTableEngine.getInstance(info, new MoveMethodValidityChecker() {
			
			@Override
			public boolean check(int entityIdx, int toClassIdx) {
				AOMClass targetClass = ses.classes.get(toClassIdx);
				AOMMethod movingMethod = ses.methods.get(entityIdx);
				
				return MoveMethodApplicabilityChecker.isApplicable(movingMethod, targetClass);
			}
		});
	}
	
	@Override
	public void moveMethodPerformed(AOMClass fromClass, AOMMethod method, AOMClass toClass, boolean isRollbackAction) {
		engine.move(method.getIndex(), fromClass.getIndex(), toClass.getIndex());
	}

	@Override
	public CandidateIterator getCandidateIterator(int maxCandidateCount) {
		engine.eval();
		
		DeltaTableEntryIterator iterator = engine.getTopK(100);
		return new NativeDeltaMatrixCandidateIteratorAdaptor(ses, iterator);
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
