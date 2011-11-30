package kr.ac.kaist.se.artool.engine.metrics;

public class N_RestrictedVerticalLocality_Dynamic extends
		N_RestrictedVerticalLocality {

	public N_RestrictedVerticalLocality_Dynamic(int locality_from, int locality_to)
	{
		super(locality_from, locality_to);
	}
	
	@Override
	protected String getN_DCICMString() {
		// TODO Auto-generated method stub
		return N_DCICM_Dynamic.N_DCICM;
	}

	@Override
	protected boolean checkLocalityOption(int ndcicm, int numMethodCall) {
		// TODO Auto-generated method stub
		return true;
	}

}
