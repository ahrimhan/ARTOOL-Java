package kr.ac.kaist.se.artool.engine;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMEntity;
import kr.ac.kaist.se.aom.structure.AOMField;
import kr.ac.kaist.se.aom.structure.AOMMethod;

public class SystemEntitySet {
	public List<AOMClass> classes;
	public List<AOMMethod> methods;
	public List<AOMField> fields;
	public List<AOMEntity> entities;
	
	public SystemEntitySet(AbstractObjectModel aom)
	{
		classes = new ArrayList<AOMClass>(aom.getClasses());
		methods = new ArrayList<AOMMethod>();
		fields = new ArrayList<AOMField>();
		entities = new ArrayList<AOMEntity>();
		
		
		for(int i = 0; i < classes.size(); i++ )
		{
			AOMClass clazz = classes.get(i);
			clazz.setIndex(i);
			fields.addAll(clazz.getFields());
			methods.addAll(clazz.getMethods());
		}
		
		entities.addAll(methods);
		entities.addAll(fields);
		
		for(int i = 0; i < entities.size(); i++ )
		{
			entities.get(i).setIndex(i);
		}
	}
}
