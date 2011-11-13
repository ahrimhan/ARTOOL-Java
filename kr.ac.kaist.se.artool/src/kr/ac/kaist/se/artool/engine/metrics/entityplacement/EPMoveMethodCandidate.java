package kr.ac.kaist.se.artool.engine.metrics.entityplacement;

import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class EPMoveMethodCandidate {
	private double distance;
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public AOMMethod getMethod() {
		return method;
	}

	public void setMethod(AOMMethod method) {
		this.method = method;
	}

	public AOMClass getClazz() {
		return clazz;
	}

	public void setClazz(AOMClass clazz) {
		this.clazz = clazz;
	}

	private AOMMethod method;
	private AOMClass clazz;
	
	public EPMoveMethodCandidate(AOMMethod method, AOMClass clazz, double distance)
	{
		this.distance = distance;
		this.method = method;
		this.clazz = clazz;
	}
	
	
}
