package kr.ac.kaist.se.artool.search.candidate;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.staticmodel.StaticFieldAccess;
import kr.ac.kaist.se.aom.staticmodel.StaticMethodCall;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.SystemEntitySet;
import kr.ac.kaist.se.artool.engine.refactoring.MoveMethodCommand;
import kr.ac.kaist.se.artool.search.MoveMethodEventListener;
import kr.ac.kaist.se.artool.search.candidate.JavaDeltaMatrixEngine.MatrixTuple;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.MatrixEntry;
import no.uib.cipr.matrix.sparse.LinkedSparseMatrix;

public class ExternalDependencyEngine extends JavaDeltaMatrixEngine {

	public ExternalDependencyEngine(SystemEntitySet sts)
	{
		super(sts);
	}
	
	public Set<MoveMethodCommand> getPositiveRefactorings()
	{
		MatrixTuple tuple = getDeltaMatrix();
		Matrix em = tuple.externalMatrix;
	
		
		TreeSet<MoveMethodCommand> mmcSet = new TreeSet<MoveMethodCommand>(new Comparator<MoveMethodCommand>() {

			@Override
			public int compare(MoveMethodCommand o1, MoveMethodCommand o2) {
				if( o1.getDeltaValue().compareTo(o2.getDeltaValue()) < 0 )
				{
					return -1;
				}
				
				// do not permit equality
				return 1;
			}
			
		});
		
		
		for( int i = 0; i < sts.methods.size(); i++ )
		{
	
			for( int j = 0; j < em.numColumns(); j++ )
			{
				
				AOMMethod method = sts.methods.get(i);
				AOMClass clazz = sts.classes.get(j);
				if( method.getOwnedScope() != null && (method.getOwnedScope().getStaticMethodCalls().size() + method.getOwnedScope().getStaticFieldAccesses().size() + method.getStaticReferer().size()) != 0)
				{	
					//if( dm.get(i,  j) < 0 )
					{
						
						MoveMethodCommand mmc = new MoveMethodCommand(method, clazz, new SingleDeltaValue((float)(-1 * em.get(i, j))));
						mmcSet.add(mmc);
					}
				}
			}
		}
	

		return mmcSet;
	}
}
