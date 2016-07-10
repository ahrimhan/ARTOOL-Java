package kr.ac.kaist.se.deltatable.model;

public class DTMembership<CT, ET> {
	private DTClass<CT> clazz;
	private DTEntity<ET> entity;
	
	public DTMembership(DTClass<CT> clazz, DTEntity<ET> entity)
	{
		this.clazz = clazz;
		this.entity = entity;
	}
	
	public DTClass<CT> getClazz() {
		return clazz;
	}
	public DTEntity<ET> getEntity() {
		return entity;
	}
}
