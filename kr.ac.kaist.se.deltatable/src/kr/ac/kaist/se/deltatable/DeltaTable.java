package kr.ac.kaist.se.deltatable;

import kr.ac.kaist.se.deltatable.model.BulkDTSystem;
import kr.ac.kaist.se.deltatable.model.DTClass;
import kr.ac.kaist.se.deltatable.model.DTEntity;
import kr.ac.kaist.se.deltatable.model.DTEntityType;
import kr.ac.kaist.se.deltatable.model.DTLink;
import kr.ac.kaist.se.deltatable.model.DTMembership;
import kr.ac.kaist.se.deltatable.model.DTSystemObjectLayer;

public class DeltaTable<CT, ET> {
	private DTSystemObjectLayer<CT, ET> objectLayer;
	private DeltaTableEngine engine;
	public static final double couplingWeight = 0.7;
	public static final double cohesionWeight = 0.3;

	
	private DeltaTable(DTSystemObjectLayer<CT, ET> objectLayer)
	{
		this.objectLayer = objectLayer;
	}
	
	
	public static <CT, ET> DeltaTable<CT, ET> getInstance(BulkDTSystem<CT, ET> bulkSystem, MoveMethodValidityChecker checker)
	{
		DeltaTable<CT, ET> dt = new DeltaTable<CT, ET>(bulkSystem.getObjectLayer());
		DeltaTableInfo info = DeltaTableInfo.getInstance(bulkSystem.getObjectLayer().getClassSize(), bulkSystem.getObjectLayer().getEntitySize(),
				bulkSystem.getObjectLayer().getMethodSize());

		for( DTLink<ET> link : bulkSystem.getLinks() )
		{
			int fromEntityIdx = link.getFrom().getIndex();
			int toEntityIdx = link.getTo().getIndex();
			info.addLink(fromEntityIdx, toEntityIdx);
		}
		
//		for( DTEntity<ET> entity : bulkSystem.getObjectLayer().getEntities() )
//		{
//			if( entity.getType() == DTEntityType.DTMethod )
//			{
//				for( DTEntity<ET> referedField : entity.getReference() )
//				{
//					for( DTEntity<ET> referedMethod : referedField.getReference() )
//					{
//						if( entity != referedMethod && (!entity.equals(referedMethod)) )
//						{
//							info.addLink(entity.getIndex(), referedMethod.getIndex(), cohesionWeight);
//						}
//					}
//				}
//			}
//		}
		
		for( DTMembership<CT, ET> membership : bulkSystem.getMemberships() )
		{
			info.addMembership(membership.getEntity().getIndex(), membership.getClazz().getIndex());
		}
	
		dt.engine = DeltaTableEngine.getInstance(info, checker);
		return dt;
	}
	
	public void move(ET entity, CT fromClazz, CT toClazz)
	{
		DTClass<CT> fromDTClass = objectLayer.getDTClass(fromClazz);
		DTClass<CT> toDTClass = objectLayer.getDTClass(toClazz);
		DTEntity<ET> dtEntity = objectLayer.getDTEntity(entity);
		engine.move(dtEntity.getIndex(), fromDTClass.getIndex(), toDTClass.getIndex());
	}
}
