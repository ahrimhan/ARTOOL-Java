package kr.ac.kaist.se.artool.search;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.engine.StatusLogger;

public class QMoodEngine implements FitnessFunction {
	private MinimalBasicMetricSuite bms;
	
	public enum TYPE
	{
		FLEXIBILITY,
		REUSABILITY,
		UNDERSTANDABILITY,
		COUNT_OF_TYPE
	};
	
	public static int countOfType = TYPE.COUNT_OF_TYPE.ordinal();
	
	private float initialDCC = 0;
	private float initialCAM = 0;
	private float initialNOP = 0;
	private float initialCIS = 0;
	private float initialNOM = 0;
	
	private float currentDCC = 0;
	private float currentCAM = 0;
	private float currentNOP = 0;
	private float currentCIS = 0;
	private float currentNOM = 0;
	
	
	private void calculateRawMetrics(AbstractObjectModel aom)
	{		
		for( AOMClass clazz : aom.getClasses() )
		{
			currentDCC += getInt(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.DCC));
			currentCAM += getInt(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.CAM));
			currentNOP += getInt(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.NOP));
			currentCIS += getInt(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.CIS));
			currentNOM += getInt(clazz.getMeasuredDataSet().get(MinimalBasicMetricSuite.NOM));
		}
		
		currentDCC /= aom.getClasses().size();
		currentCAM /= aom.getClasses().size();
		currentNOP /= aom.getClasses().size();
		currentCIS /= aom.getClasses().size();
		currentNOM /= aom.getClasses().size();
	}
	
	AbstractObjectModel aom;
	TYPE type;
	
	public QMoodEngine(AbstractObjectModel aom, TYPE type)
	{
		bms = new MinimalBasicMetricSuite();
		bms.measure(aom);
		
		calculateRawMetrics(aom);
		this.aom = aom;
		
		initialDCC = currentDCC;
		initialCAM = currentCAM;
		initialNOP = currentNOP;
		initialCIS = currentCIS;
		initialNOM = currentNOM;
		
		this.type = type;
	}
	
	public static int getInt(Object obj)
	{
		return MinimalBasicMetricSuite.getInt(obj);
	}
	
	public static float getFloat(Object obj)
	{
		return MinimalBasicMetricSuite.getFloat(obj);
	}
	
	@SuppressWarnings("unused")
	public float calculate()
			//, PrintStream ps)
	{
		float[] qmood = new float[countOfType];
	
		bms.measure(aom);
		
		float flexibility = 0;
		float reusability = 0;
		float understandability = 0;
		
		calculateRawMetrics(aom);

		float normDCC = 0;
		float normCAM = 0;
		float normNOP = 0;
		float normCIS = 0;
		float normNOM = 0;
		
		float normDSC = 1;
		float normANA = 1;
		float normDAM = 1;
		float normMOA = 1;
		
		normDCC = currentDCC / initialDCC;
		normCAM = currentCAM / initialCAM;
		normNOP = currentNOP / initialNOP;
		normCIS = currentCIS / initialCIS;
		normNOM = currentNOM / initialNOM;
		
		flexibility = 0.25f * normDAM - 0.25f * normDCC + 0.5f * normMOA + 0.5f * normNOP;
		reusability = 0.5f * normDSC - 0.25f * normDCC + 0.25f * normCAM + 0.5f * normCIS;
		understandability = -0.33f * normDSC - 0.33f * normANA + 0.33f * normDAM - 0.33f * normDCC + 0.33f * normCAM - 0.33f * normNOP - 0.33f * normNOM;
		
		qmood[TYPE.FLEXIBILITY.ordinal()] = flexibility;
		qmood[TYPE.REUSABILITY.ordinal()] = reusability;
		qmood[TYPE.UNDERSTANDABILITY.ordinal()] = understandability;
		
		
		
		StatusLogger.getInstance().putVar("FLEXIBILITY", flexibility);
		StatusLogger.getInstance().putVar("REUSABILITY", reusability);
		StatusLogger.getInstance().putVar("UNDERSTANDABILITY", understandability);

		// file write - printCurrentSuite() �� ��.
		StatusLogger.getInstance().printCurrentSuite();
		//
		System.err.print("Delta Prev\t");
		StatusLogger.getInstance().printDeltaWithPrevious();
		System.err.print("Delta Orig\t");
		StatusLogger.getInstance().printDeltaWithOriginal();
		
		return qmood[type.ordinal()];
			
	}
}
