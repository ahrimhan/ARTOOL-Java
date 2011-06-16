package kr.ac.kaist.se.artool.staticmodeling;

import java.util.HashMap;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMExternalType;
import kr.ac.kaist.se.aom.structure.StructureFactory;

import org.eclipse.jdt.core.dom.ITypeBinding;

public class ExternalTypeGenerator
{
	private HashMap<String, AOMExternalType> externalTypeMap;
	private AbstractObjectModel aom;
	private AOMExternalType voidType;
	
	public ExternalTypeGenerator(AbstractObjectModel aom)
	{
		externalTypeMap = new HashMap<String, AOMExternalType>();
		this.aom = aom;
		
		voidType = StructureFactory.eINSTANCE.createAOMExternalType(); 
		voidType.setName("void");
		voidType.setFqdn("void");
		aom.getExternalTypes().add(voidType);
		externalTypeMap.put("void", voidType);
	}
	
	public AOMExternalType get(ITypeBinding typeBinding)
	{
		
		ITypeBinding tb = null;
		if( typeBinding.isArray() )
		{
			tb = typeBinding.getElementType().getErasure();
		}
		else
		{
			tb = typeBinding.getErasure();
		}
		if( externalTypeMap.containsKey(tb.getQualifiedName()) )
		{
			return externalTypeMap.get(tb.getQualifiedName());
		}
		else
		{
			AOMExternalType ret = StructureFactory.eINSTANCE.createAOMExternalType();
			aom.getExternalTypes().add(ret);
			ret.setName(tb.getName());
			ret.setFqdn(tb.getQualifiedName());
			externalTypeMap.put(tb.getQualifiedName(), ret);
			return ret;
		}
	}

	public AOMExternalType getVoid() {
		return voidType;
	}
}