package kr.ac.kaist.se.artool.engine;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;

public class FitnessFunction {
	private BasicMetricSuite bms;
	private static FitnessFunction instance;
	public static FitnessFunction getInstance()
	{
		if( instance == null )
		{
			instance = new FitnessFunction();
			
		}
		return instance;
	}
	
	private FitnessFunction()
	{
		
	}
	
	public static int getInt(Object obj)
	{
		return BasicMetricSuite.getInt(obj);
	}
	
	public static float getFloat(Object obj)
	{
		return BasicMetricSuite.getFloat(obj);
	}
	
	public float calculate(AbstractObjectModel aom)
			//, PrintStream ps)
	{
		float fitness = 0;
	
		if( bms == null ) bms = new BasicMetricSuite();
		bms.measure(aom);
		
		int LOC = 0; int NOM = 0; int NOA = 0; int NOCON = 0;
		int NOO = 0; int DIT = 0; int CLD = 0; int NOC = 0;
		int NOP = 0;
		int NMO = 0;
		int NMI = 0;
		int NMA = 0;
		int WMC = 0;
		//coupling
		int DynamicImport = 0;
		int DynamicExport = 0; //same with MPC (but!!! not really dynamic, just number of method invocation in code at static-level)
		int StaticImport = 0; //alike with RFC
		int StaticExport = 0;
		int DynamicBoth = 0;
		int StaticBoth = 0; //alike with CBO
		//cohesion
		float LCOM2 = 0;
		float LCOM3 = 0;
		float MSC = 0;
		//
		int MPCDE = 0;
		int MPCDI = 0;
		int MPCDBoth = 0;
		//
		int MPCSE = 0;
		int MPCSI = 0;
		int MPCSBoth = 0;
		
		//initialize
		int LOC_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("LOC")); 
		int LOC_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("LOC"));
		int NOM_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOM")); 
		int NOM_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOM"));
		int NOA_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOA"));
		int NOA_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOA"));
		int NOCON_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOCON"));
		int NOCON_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOCON")); 
		int NOO_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOO"));
		int NOO_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOO"));
		int DIT_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DIT"));
		int DIT_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DIT"));
		int CLD_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("CLD"));
		int CLD_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("CLD"));
		int NOC_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOC"));
		int NOC_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOC"));
		int NOP_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOP")); 
		int NOP_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NOP"));
		int NMO_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NMO"));
		int NMO_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NMO"));
		int NMI_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NMI"));
		int NMI_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NMI"));
		int NMA_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NMA"));
		int NMA_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("NMA"));
		int WMC_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("WMC"));
		int WMC_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("WMC"));
		//
		int DynamicImport_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DynamicImport"));
		int DynamicImport_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DynamicImport"));
		int DynamicExport_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DynamicExport"));
		int DynamicExport_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DynamicExport"));
		int StaticImport_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("StaticImport"));
		int StaticImport_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("StaticImport"));
		int StaticExport_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("StaticExport"));
		int StaticExport_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("StaticExport"));
		int DynamicBoth_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DynamicBoth"));
		int DynamicBoth_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("DynamicBoth"));
		int StaticBoth_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("StaticBoth"));
		int StaticBoth_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("StaticBoth"));
		//
		float LCOM2_MIN = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("LCOM2"));
		float LCOM2_MAX = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("LCOM2"));
		float LCOM3_MIN = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("LCOM3"));
		float LCOM3_MAX = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("LCOM3"));
		float MSC_MIN = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("MSC"));
		float MSC_MAX = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("MSC"));
		
		//
		int MPCDE_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCDE"));
		int MPCDE_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCDE"));
		int MPCDI_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCDI"));
		int MPCDI_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCDI"));
		int MPCDBoth_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCDBoth"));
		int MPCDBoth_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCDBoth"));
		//
		int MPCSE_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCSE"));
		int MPCSE_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCSE"));
		int MPCSI_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCSI"));
		int MPCSI_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCSI"));
		int MPCSBoth_MIN = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCSBoth"));
		int MPCSBoth_MAX = getInt(aom.getClasses().get(0).getMeasuredDataSet().get("MPCSBoth"));
		
		int clazz_size = aom.getClasses().size();
		int metric_size = aom.getClasses().get(0).getMeasuredDataSet().size();
		
		
		for( AOMClass clazz : aom.getClasses() )
		{
			LOC += getInt(clazz.getMeasuredDataSet().get("LOC"));
			NOM += getInt(clazz.getMeasuredDataSet().get("NOM"));
			NOA += getInt(clazz.getMeasuredDataSet().get("NOA"));
			NOCON += getInt(clazz.getMeasuredDataSet().get("NOCON"));
			NOO += getInt(clazz.getMeasuredDataSet().get("NOO"));
			DIT += getInt(clazz.getMeasuredDataSet().get("DIT"));
			CLD += getInt(clazz.getMeasuredDataSet().get("CLD"));
			NOC += getInt(clazz.getMeasuredDataSet().get("NOC"));
			NOP += getInt(clazz.getMeasuredDataSet().get("NOP"));
			NMO += getInt(clazz.getMeasuredDataSet().get("NMO"));
			NMI += getInt(clazz.getMeasuredDataSet().get("NMI"));
			NMA += getInt(clazz.getMeasuredDataSet().get("NMA"));
			WMC += getInt(clazz.getMeasuredDataSet().get("WMC"));
			DynamicImport += getInt(clazz.getMeasuredDataSet().get("DynamicImport"));
			DynamicExport += getInt(clazz.getMeasuredDataSet().get("DynamicExport"));
			StaticImport += getInt(clazz.getMeasuredDataSet().get("StaticImport"));
			StaticExport += getInt(clazz.getMeasuredDataSet().get("StaticExport"));
			DynamicBoth += getInt(clazz.getMeasuredDataSet().get("DynamicBoth"));
			StaticBoth += getInt(clazz.getMeasuredDataSet().get("StaticBoth"));
			LCOM2 += getFloat(clazz.getMeasuredDataSet().get("LCOM2"));
			LCOM3 += getFloat(clazz.getMeasuredDataSet().get("LCOM3"));
			MSC += getFloat(clazz.getMeasuredDataSet().get("MSC"));
			MPCDE += getInt(clazz.getMeasuredDataSet().get("MPCDE"));
			MPCDI += getInt(clazz.getMeasuredDataSet().get("MPCDI"));
			MPCDBoth += getInt(clazz.getMeasuredDataSet().get("MPCDBoth"));
			MPCSE += getInt(clazz.getMeasuredDataSet().get("MPCSE"));
			MPCSI += getInt(clazz.getMeasuredDataSet().get("MPCSI"));
			MPCSBoth += getInt(clazz.getMeasuredDataSet().get("MPCSBoth"));
			
			//1
			if( LOC_MIN > getInt(clazz.getMeasuredDataSet().get("LOC")) )
			{
				LOC_MIN = getInt(clazz.getMeasuredDataSet().get("LOC"));
			}
			if ( LOC_MAX < getInt(clazz.getMeasuredDataSet().get("LOC")) )
			{
				LOC_MAX = getInt(clazz.getMeasuredDataSet().get("LOC"));
			}
			
			//2
			if( NOM_MIN > getInt(clazz.getMeasuredDataSet().get("NOM")) )
			{
				NOM_MIN = getInt(clazz.getMeasuredDataSet().get("NOM"));
			}
			if ( NOM_MAX < getInt(clazz.getMeasuredDataSet().get("NOM")) )
			{
				NOM_MAX = getInt(clazz.getMeasuredDataSet().get("NOM"));
			}
			
			//3
			if( NOA_MIN > getInt(clazz.getMeasuredDataSet().get("NOA")) )
			{
				NOA_MIN = getInt(clazz.getMeasuredDataSet().get("NOA"));
			}
			if ( NOA_MAX < getInt(clazz.getMeasuredDataSet().get("NOA")) )
			{
				NOA_MAX = getInt(clazz.getMeasuredDataSet().get("NOA"));
			}
			
			//4
			if( NOCON_MIN > getInt(clazz.getMeasuredDataSet().get("NOCON")) )
			{
				NOCON_MIN = getInt(clazz.getMeasuredDataSet().get("NOCON"));
			}
			if ( NOCON_MAX < getInt(clazz.getMeasuredDataSet().get("NOCON")) )
			{
				NOCON_MAX = getInt(clazz.getMeasuredDataSet().get("NOCON"));
			}
			
			//5
			if( NOO_MIN > getInt(clazz.getMeasuredDataSet().get("NOO")) )
			{
				NOO_MIN = getInt(clazz.getMeasuredDataSet().get("NOO"));
			}
			if ( NOO_MAX < getInt(clazz.getMeasuredDataSet().get("NOO")) )
			{
				NOO_MAX = getInt(clazz.getMeasuredDataSet().get("NOO"));
			}
			
			//6
			if( DIT_MIN > getInt(clazz.getMeasuredDataSet().get("DIT")) )
			{
				DIT_MIN = getInt(clazz.getMeasuredDataSet().get("DIT"));
			}
			if ( DIT_MAX < getInt(clazz.getMeasuredDataSet().get("DIT")) )
			{
				DIT_MAX = getInt(clazz.getMeasuredDataSet().get("DIT"));
			}

			//7
			if( CLD_MIN > getInt(clazz.getMeasuredDataSet().get("CLD")) )
			{
				CLD_MIN = getInt(clazz.getMeasuredDataSet().get("CLD"));
			}
			if ( CLD_MAX < getInt(clazz.getMeasuredDataSet().get("CLD")) )
			{
				CLD_MAX = getInt(clazz.getMeasuredDataSet().get("CLD"));
			}
			
			//8
			if( NOC_MIN > getInt(clazz.getMeasuredDataSet().get("NOC")) )
			{
				NOC_MIN = getInt(clazz.getMeasuredDataSet().get("NOC"));
			}
			if ( NOC_MAX < getInt(clazz.getMeasuredDataSet().get("NOC")) )
			{
				NOC_MAX = getInt(clazz.getMeasuredDataSet().get("NOC"));
			}
			
			//9
			if( NOP_MIN > getInt(clazz.getMeasuredDataSet().get("NOP")) )
			{
				NOP_MIN = getInt(clazz.getMeasuredDataSet().get("NOP"));
			}
			if ( NOP_MAX < getInt(clazz.getMeasuredDataSet().get("NOP")) )
			{
				NOP_MAX = getInt(clazz.getMeasuredDataSet().get("NOP"));
			}
			
			//10
			if( NMO_MIN > getInt(clazz.getMeasuredDataSet().get("NMO")) )
			{
				NMO_MIN = getInt(clazz.getMeasuredDataSet().get("NMO"));
			}
			if ( NMO_MAX < getInt(clazz.getMeasuredDataSet().get("NMO")) )
			{
				NMO_MAX = getInt(clazz.getMeasuredDataSet().get("NMO"));
			}
			
			//11
			if( NMI_MIN > getInt(clazz.getMeasuredDataSet().get("NMI")) )
			{
				NMI_MIN = getInt(clazz.getMeasuredDataSet().get("NMI"));
			}
			if ( NMI_MAX < getInt(clazz.getMeasuredDataSet().get("NMI")) )
			{
				NMI_MAX = getInt(clazz.getMeasuredDataSet().get("NMI"));
			}
			
			//12
			if( NMA_MIN > getInt(clazz.getMeasuredDataSet().get("NMA")) )
			{
				NMA_MIN = getInt(clazz.getMeasuredDataSet().get("NMA"));
			}
			if ( NMA_MAX < getInt(clazz.getMeasuredDataSet().get("NMA")) )
			{
				NMA_MAX = getInt(clazz.getMeasuredDataSet().get("NMA"));
			}
			
			//13
			if( WMC_MIN > getInt(clazz.getMeasuredDataSet().get("WMC")) )
			{
				WMC_MIN = getInt(clazz.getMeasuredDataSet().get("WMC"));
			}
			if ( WMC_MAX < getInt(clazz.getMeasuredDataSet().get("WMC")) )
			{
				WMC_MAX = getInt(clazz.getMeasuredDataSet().get("WMC"));
			}
			
			//14
			if( DynamicImport_MIN > getInt(clazz.getMeasuredDataSet().get("DynamicImport")) )
			{
				DynamicImport_MIN = getInt(clazz.getMeasuredDataSet().get("DynamicImport"));
			}
			if ( DynamicImport_MAX < getInt(clazz.getMeasuredDataSet().get("DynamicImport")) )
			{
				DynamicImport_MAX = getInt(clazz.getMeasuredDataSet().get("DynamicImport"));
			}
			
			//15
			if( DynamicExport_MIN > getInt(clazz.getMeasuredDataSet().get("DynamicExport")) )
			{
				DynamicExport_MIN = getInt(clazz.getMeasuredDataSet().get("DynamicExport"));
			}
			if ( DynamicExport_MAX < getInt(clazz.getMeasuredDataSet().get("DynamicExport")) )
			{
				DynamicExport_MAX = getInt(clazz.getMeasuredDataSet().get("DynamicExport"));
			}
			
			//16
			if( StaticImport_MIN > getInt(clazz.getMeasuredDataSet().get("StaticImport")) )
			{
				StaticImport_MIN = getInt(clazz.getMeasuredDataSet().get("StaticImport"));
			}
			if ( StaticImport_MAX < getInt(clazz.getMeasuredDataSet().get("StaticImport")) )
			{
				StaticImport_MAX = getInt(clazz.getMeasuredDataSet().get("StaticImport"));
			}
			
			//17
			if( StaticExport_MIN > getInt(clazz.getMeasuredDataSet().get("StaticExport")) )
			{
				StaticExport_MIN = getInt(clazz.getMeasuredDataSet().get("StaticExport"));
			}
			if ( StaticExport_MAX < getInt(clazz.getMeasuredDataSet().get("StaticExport")) )
			{
				StaticExport_MAX = getInt(clazz.getMeasuredDataSet().get("StaticExport"));
			}
			
			//18
			if( DynamicBoth_MIN > getInt(clazz.getMeasuredDataSet().get("DynamicBoth")) )
			{
				DynamicBoth_MIN = getInt(clazz.getMeasuredDataSet().get("DynamicBoth"));
			}
			if ( DynamicBoth_MAX < getInt(clazz.getMeasuredDataSet().get("DynamicBoth")) )
			{
				DynamicBoth_MAX = getInt(clazz.getMeasuredDataSet().get("DynamicBoth"));
			}
			
			//19
			if( StaticBoth_MIN > getInt(clazz.getMeasuredDataSet().get("StaticBoth")) )
			{
				StaticBoth_MIN = getInt(clazz.getMeasuredDataSet().get("StaticBoth"));
			}
			if ( StaticBoth_MAX < getInt(clazz.getMeasuredDataSet().get("StaticBoth")) )
			{
				StaticBoth_MAX = getInt(clazz.getMeasuredDataSet().get("StaticBoth"));
			}
			
			//20
			if( LCOM2_MIN > getFloat(clazz.getMeasuredDataSet().get("LCOM2")) )
			{
				LCOM2_MIN = getFloat(clazz.getMeasuredDataSet().get("LCOM2"));
			}
			if ( LCOM2_MAX < getFloat(clazz.getMeasuredDataSet().get("LCOM2")) )
			{
				LCOM2_MAX = getFloat(clazz.getMeasuredDataSet().get("LCOM2"));
			}
			
			//21
			if( LCOM3_MIN > getFloat(clazz.getMeasuredDataSet().get("LCOM3")) )
			{
				LCOM3_MIN = getFloat(clazz.getMeasuredDataSet().get("LCOM3"));
			}
			if ( LCOM3_MAX < getFloat(clazz.getMeasuredDataSet().get("LCOM3")) )
			{
				LCOM3_MAX = getFloat(clazz.getMeasuredDataSet().get("LCOM3"));
			}
			
			//21.5
			if( MSC_MIN > getFloat(clazz.getMeasuredDataSet().get("MSC")) )
			{
				MSC_MIN = getFloat(clazz.getMeasuredDataSet().get("MSC"));
			}
			if ( MSC_MAX < getFloat(clazz.getMeasuredDataSet().get("MSC")) )
			{
				MSC_MAX = getFloat(clazz.getMeasuredDataSet().get("MSC"));
			}
			
			//22
			if( MPCDE_MIN > getInt(clazz.getMeasuredDataSet().get("MPCDE")) )
			{
				MPCDE_MIN = getInt(clazz.getMeasuredDataSet().get("MPCDE"));
			}
			if ( MPCDE_MAX < getInt(clazz.getMeasuredDataSet().get("MPCDE")) )
			{
				MPCDE_MAX = getInt(clazz.getMeasuredDataSet().get("MPCDE"));
			}
			
			//23
			if( MPCDI_MIN > getInt(clazz.getMeasuredDataSet().get("MPCDI")) )
			{
				MPCDI_MIN = getInt(clazz.getMeasuredDataSet().get("MPCDI"));
			}
			if ( MPCDI_MAX < getInt(clazz.getMeasuredDataSet().get("MPCDI")) )
			{
				MPCDI_MAX = getInt(clazz.getMeasuredDataSet().get("MPCDI"));
			}
			
			//24
			if( MPCDBoth_MIN > getInt(clazz.getMeasuredDataSet().get("MPCDBoth")) )
			{
				MPCDBoth_MIN = getInt(clazz.getMeasuredDataSet().get("MPCDBoth"));
			}
			if ( MPCDBoth_MAX < getInt(clazz.getMeasuredDataSet().get("MPCDBoth")) )
			{
				MPCDBoth_MAX = getInt(clazz.getMeasuredDataSet().get("MPCDBoth"));
			}
			
			//25
			if( MPCSE_MIN > getInt(clazz.getMeasuredDataSet().get("MPCSE")) )
			{
				MPCSE_MIN = getInt(clazz.getMeasuredDataSet().get("MPCSE"));
			}
			if ( MPCSE_MAX < getInt(clazz.getMeasuredDataSet().get("MPCSE")) )
			{
				MPCSE_MAX = getInt(clazz.getMeasuredDataSet().get("MPCSE"));
			}
			
			//26
			if( MPCSI_MIN > getInt(clazz.getMeasuredDataSet().get("MPCSI")) )
			{
				MPCSI_MIN = getInt(clazz.getMeasuredDataSet().get("MPCSI"));
			}
			if ( MPCSI_MAX < getInt(clazz.getMeasuredDataSet().get("MPCSI")) )
			{
				MPCSI_MAX = getInt(clazz.getMeasuredDataSet().get("MPCSI"));
			}
			
			//27
			if( MPCSBoth_MIN > getInt(clazz.getMeasuredDataSet().get("MPCSBoth")) )
			{
				MPCSBoth_MIN = getInt(clazz.getMeasuredDataSet().get("MPCSBoth"));
			}
			if ( MPCSBoth_MAX < getInt(clazz.getMeasuredDataSet().get("MPCSBoth")) )
			{
				MPCSBoth_MAX = getInt(clazz.getMeasuredDataSet().get("MPCSBoth"));
			}
		
		}
		
		float LOC_avg = (float) LOC / clazz_size;
		float NOM_avg = (float) NOM / clazz_size;
		float NOA_avg = (float) NOA / clazz_size;
		float NOCON_avg = (float) NOCON / clazz_size;
		float NOO_avg = (float) NOO / clazz_size;
		float DIT_avg = (float) DIT / clazz_size;
		float CLD_avg = (float) CLD / clazz_size;
		float NOC_avg = (float) NOC / clazz_size;
		float NOP_avg = (float) NOP / clazz_size;
		float NMO_avg = (float) NMO / clazz_size;
		float NMI_avg = (float) NMI / clazz_size;
		float NMA_avg = (float) NMA / clazz_size;
		float WMC_avg = (float) WMC / clazz_size;
		float DynamicImport_avg = (float) DynamicImport / clazz_size;
		float DynamicExport_avg = (float) DynamicExport / clazz_size;
		float StaticImport_avg = (float) StaticImport / clazz_size;
		float StaticExport_avg = (float) StaticExport / clazz_size;
		float DynamicBoth_avg = (float) DynamicBoth / clazz_size;
		float StaticBoth_avg = (float) StaticBoth / clazz_size;
		float LCOM2_avg = (float) LCOM2 / clazz_size;
		float LCOM3_avg = (float) LCOM3 / clazz_size;	
		float MSC_avg = (float) MSC / clazz_size;	
		float MPCDE_avg = (float) MPCDE / clazz_size;
		float MPCDI_avg = (float) MPCDI / clazz_size;
		float MPCDBoth_avg = (float) MPCDBoth / clazz_size;
		float MPCSE_avg = (float) MPCSE / clazz_size;
		float MPCSI_avg = (float) MPCSI / clazz_size;
		float MPCSBoth_avg = (float) MPCSBoth / clazz_size;
		
		
		//evaluation function (fitness function)
		int evaluation_mode = ARToolMain.getInstance().getEvaluation_mode();
		
		switch (evaluation_mode)
		{
		case 1: //Maintainability Index
			double maintainabilityIndex = 0;
			int halsteadVolume = 0; int cyclomaticComplexity = 0;
			maintainabilityIndex = 171 - 5.2 * Math.log(halsteadVolume)/Math.log(2) 
				- 0.23*cyclomaticComplexity -16.2 * Math.log(LOC_avg)/Math.log(2);
			
			fitness = (float) maintainabilityIndex;
			break;
			// FIXME:	
		case 2: //QMOOD 
			//extensibility
			//reusability
			fitness = (float) (-0.25*StaticBoth_avg + 0.25*LCOM3_avg + 0.5*StaticExport_avg + 0.5*aom.getClasses().size());
			break;
		case 3: //instability
			// I = Ce / (Ce+Ca)
			fitness = 1 / (StaticExport_avg / (StaticImport_avg + StaticExport_avg));
			break;
		case 4: //my own (simple ver.)
						
			fitness = 
			(float) (

			(MSC_avg / MSC_MAX) / (DynamicBoth_avg / DynamicBoth_MAX)
			);
			
			break;
		case 5: //my own (20110412)
			
			fitness = (float) (
						(0.1 * ( NOM_avg )/( NOM_MAX  )) +
						(0.1 * ( DIT_avg )/( DIT_MAX  )) +
						(0.1 * ( NMO_avg  )/( NMO_MAX )) +
						(0.1 * ( NMI_avg )/( NMI_MAX  )) +
						(0.05 * ( DynamicBoth_avg )/( DynamicBoth_MAX  )) +
						(0.05 * ( StaticBoth_avg  )/( StaticBoth_MAX  )) +
						(0.25 * ( LCOM2_avg )/( LCOM2_MAX  )) +
						(0.25 * ( LCOM3_avg  )/( LCOM3_MAX  )));

			break;
		case 6: //Seng's (complexity metric-trapezoid shaped function)
			//20110425
			int weightValNum = 21;
			//int weightValNum = metric_size;
			float[] weightFitness = new float[weightValNum];
			
			//weight assign
			for( int i = 0; i < weightValNum; i++ )
			{
				weightFitness[i] = (float) 1.0 / (float)weightValNum;
			}
			fitness = (weightFitness[0] * ( LOC_avg )/( LOC_MAX )) +
			(weightFitness[1] * ( NOM_avg )/( NOM_MAX )) +
			(weightFitness[2] * ( NOA_avg )/( NOA_MAX )) +
			(weightFitness[3] * ( NOCON_avg )/( NOCON_MAX )) +
			(weightFitness[4] * ( NOO_avg )/( NOO_MAX )) +
			(weightFitness[5] * ( DIT_avg )/( DIT_MAX )) +
			(weightFitness[6] * ( CLD_avg )/( CLD_MAX )) +
			(weightFitness[7] * ( NOC_avg )/( NOC_MAX )) +
			(weightFitness[8] * ( NOP_avg )/( NOP_MAX )) +
			(weightFitness[9] * ( NMO_avg )/( NMO_MAX )) +
			(weightFitness[10] * ( NMI_avg )/( NMI_MAX )) +
			(weightFitness[11] * ( NMA_avg )/( NMA_MAX )) +
			(weightFitness[12] * ( WMC_avg )/( WMC_MAX )) +
			(weightFitness[13] * ( DynamicImport_avg )/( DynamicImport_MAX )) +
			(weightFitness[14] * ( DynamicExport_avg )/( DynamicExport_MAX )) +
			(weightFitness[15] * ( StaticImport_avg )/( StaticImport_MAX )) +
			(weightFitness[16] * ( StaticExport_avg )/( StaticExport_MAX )) +
			(weightFitness[17] * ( DynamicBoth_avg )/( DynamicBoth_MAX )) +
			(weightFitness[18] * ( StaticBoth_avg )/( StaticBoth_MAX )) +
			(weightFitness[19] * ( LCOM2_avg )/( LCOM2_MAX )) +
			(weightFitness[20] * ( LCOM3_avg )/( LCOM3_MAX ));
			
//			fitness = (weightFitness[0] * ( LOC_avg - LOC_MIN )/( LOC_MAX - LOC_MIN )) +
//			(weightFitness[1] * ( NOM_avg - NOM_MIN )/( NOM_MAX - NOM_MIN )) +
//			(weightFitness[2] * ( NOA_avg - NOA_MIN )/( NOA_MAX - NOA_MIN )) +
//			(weightFitness[3] * ( NOCON_avg - NOCON_MIN )/( NOCON_MAX - NOCON_MIN )) +
//			(weightFitness[4] * ( NOO_avg - NOO_MIN )/( NOO_MAX - NOO_MIN )) +
//			(weightFitness[5] * ( DIT_avg - DIT_MIN )/( DIT_MAX - DIT_MIN )) +
//			(weightFitness[6] * ( CLD_avg - CLD_MIN )/( CLD_MAX - CLD_MIN )) +
//			(weightFitness[7] * ( NOC_avg - NOC_MIN )/( NOC_MAX - NOC_MIN )) +
//			(weightFitness[8] * ( NOP_avg - NOP_MIN )/( NOP_MAX - NOP_MIN )) +
//			(weightFitness[9] * ( NMO_avg - NMO_MIN )/( NMO_MAX - NMO_MIN )) +
//			(weightFitness[10] * ( NMI_avg - NMI_MIN )/( NMI_MAX - NMI_MIN )) +
//			(weightFitness[11] * ( NMA_avg - NMA_MIN )/( NMA_MAX - NMA_MIN )) +
//			(weightFitness[12] * ( WMC_avg - WMC_MIN )/( WMC_MAX - WMC_MIN )) +
//			(weightFitness[13] * ( DynamicImport_avg - DynamicImport_MIN )/( DynamicImport_MAX - DynamicImport_MIN )) +
//			(weightFitness[14] * ( DynamicExport_avg - DynamicExport_MIN )/( DynamicExport_MAX - DynamicExport_MIN )) +
//			(weightFitness[15] * ( StaticImport_avg - StaticImport_MIN )/( StaticImport_MAX - StaticImport_MIN )) +
//			(weightFitness[16] * ( StaticExport_avg - StaticExport_MIN )/( StaticExport_MAX - StaticExport_MIN )) +
//			(weightFitness[17] * ( DynamicBoth_avg - DynamicBoth_MIN )/( DynamicBoth_MAX - DynamicBoth_MIN )) +
//			(weightFitness[18] * ( StaticBoth_avg - StaticBoth_MIN )/( StaticBoth_MAX - StaticBoth_MIN )) +
//			(weightFitness[19] * ( LCOM2_avg - LCOM2_MIN )/( LCOM2_MAX - LCOM2_MIN )) +
//			(weightFitness[20] * ( LCOM3_avg - LCOM3_MIN )/( LCOM3_MAX - LCOM3_MIN ));
			break;
			default:
				throw new RuntimeException("Unknown evaluation mode");
		}
		
		float fitness2 = (float) (
				( 
					(1.0 * (( MSC_avg )/( MSC_MAX )))
					
				) 
				/
				( 
					(0.5 * (( DynamicBoth_avg )/( DynamicBoth_MAX ))) +
					(0.5 * (( MPCDBoth_avg )/( MPCDBoth_MAX )))

				)
				); 

		//case6
		float fitness3 = (float) (
				( 
					(0.5 * (( MSC_avg )/( MSC_MAX ))) +
					(0.25 * (( LCOM2_avg )/( LCOM2_MAX ))) +
					(0.25 * (( LCOM3_avg )/( LCOM3_MAX )))
					
				) 
				/
				( 
					//fix 20110802
						//add in numerator
						//(0.25 * (( LCOM2_avg )/( LCOM2_MAX ))) +
						//(0.25 * (( LCOM3_avg )/( LCOM3_MAX )))
						//add in denominator
						//(0.25 * ( MPCDBoth_avg )/( MPCDBoth_MAX )) +
						//(0.25 * ( MPCSBoth_avg )/( MPCSBoth_MAX )) +
						//delete in denominator
						//(0.25 * (( LCOM2_avg )/( LCOM2_MAX ))) +
						//(0.25 * (( LCOM3_avg )/( LCOM3_MAX )))
					(0.25 * (( MPCDBoth_avg )/( MPCDBoth_MAX ))) +
					(0.25 * (( MPCSBoth_avg )/( MPCSBoth_MAX ))) +
					(0.25 * (( DynamicBoth_avg )/( DynamicBoth_MAX ))) +
					(0.25 * (( StaticBoth_avg )/( StaticBoth_MAX ))) 
					
				)
			); 
		
		float fitness_static = (float) (
			    ( 
					(0.5 * ( MSC_avg )/( MSC_MAX )) +
					(0.25 * (( LCOM2_avg )/( LCOM2_MAX ))) +
					(0.25 * (( LCOM3_avg )/( LCOM3_MAX )))
						
				) 
				/
				( 
					(0.5 * ( MPCSBoth_avg )/( MPCSBoth_MAX )) +
					(0.5 * ( StaticBoth_avg )/( StaticBoth_MAX )) 
				)
			); 
		
		
		
//		int weightValNum = metric_size;
//		float[] weightFitness = new float[weightValNum];
//		
//		//weight assign
//		for( int i = 0; i < weightValNum; i++ )
//		{
//			weightFitness[i] = (float) 1.0 / (float)weightValNum;
//		}
//		
//		float fitness3 = (weightFitness[0] * ( LOC_avg )/( LOC_MAX )) +
//		(weightFitness[1] * ( NOM_avg )/( NOM_MAX )) +
//		(weightFitness[2] * ( NOA_avg )/( NOA_MAX )) +
//		(weightFitness[3] * ( NOCON_avg )/( NOCON_MAX )) +
//		(weightFitness[4] * ( NOO_avg )/( NOO_MAX )) +
//		(weightFitness[5] * ( DIT_avg )/( DIT_MAX )) +
//		(weightFitness[6] * ( CLD_avg )/( CLD_MAX )) +
//		(weightFitness[7] * ( NOC_avg )/( NOC_MAX )) +
//		(weightFitness[8] * ( NOP_avg )/( NOP_MAX )) +
//		(weightFitness[9] * ( NMO_avg )/( NMO_MAX )) +
//		(weightFitness[10] * ( NMI_avg )/( NMI_MAX )) +
//		(weightFitness[11] * ( NMA_avg )/( NMA_MAX )) +
//		(weightFitness[12] * ( WMC_avg )/( WMC_MAX )) +
//		(weightFitness[13] * ( DynamicImport_avg )/( DynamicImport_MAX )) +
//		(weightFitness[14] * ( DynamicExport_avg )/( DynamicExport_MAX )) +
//		(weightFitness[15] * ( StaticImport_avg )/( StaticImport_MAX )) +
//		(weightFitness[16] * ( StaticExport_avg )/( StaticExport_MAX )) +
//		(weightFitness[17] * ( DynamicBoth_avg )/( DynamicBoth_MAX )) +
//		(weightFitness[18] * ( StaticBoth_avg )/( StaticBoth_MAX )) +
//		(weightFitness[19] * ( LCOM2_avg )/( LCOM2_MAX )) +
//		(weightFitness[20] * ( LCOM3_avg )/( LCOM3_MAX ));
				
		//average
		//for printing (2011.04.25)
//		StatusLogger.getInstance().putVar("LOC", LOC_avg);
//		StatusLogger.getInstance().putVar("NOM", NOM_avg);
//		StatusLogger.getInstance().putVar("NOA", NOA_avg);
//		StatusLogger.getInstance().putVar("NOCON", NOCON_avg);
//		StatusLogger.getInstance().putVar("NOO", NOO_avg);
//		StatusLogger.getInstance().putVar("DIT", DIT_avg);
//		StatusLogger.getInstance().putVar("CLD", CLD_avg);
//		StatusLogger.getInstance().putVar("NOC", NOC_avg);
//		StatusLogger.getInstance().putVar("NOP", NOP_avg);
//		StatusLogger.getInstance().putVar("NMO", NMO_avg);
//		StatusLogger.getInstance().putVar("NMI", NMI_avg);
//		StatusLogger.getInstance().putVar("NMA", NMA_avg);
//		StatusLogger.getInstance().putVar("WMC", WMC_avg);
		
		//print 순서 조정은 여기서.
		//System.out.println (console 출력)
//		StatusLogger.getInstance().putVar("fitness", fitness);
		StatusLogger.getInstance().putVar("fitness2", fitness2);
		StatusLogger.getInstance().putVar("fitness3", fitness3);
		StatusLogger.getInstance().putVar("fitness_static", fitness_static);
		//
//		StatusLogger.getInstance().putVar("DynamicImport", DynamicImport_avg);
//		StatusLogger.getInstance().putVar("DynamicExport", DynamicExport_avg);
//		StatusLogger.getInstance().putVar("StaticImport", StaticImport_avg);
//		StatusLogger.getInstance().putVar("StaticExport", StaticExport_avg);
//		//
		StatusLogger.getInstance().putVar("StaticBoth", StaticBoth_avg);
		StatusLogger.getInstance().putVar("DynamicBoth", DynamicBoth_avg);
		StatusLogger.getInstance().putVar("MPCDBoth", MPCDBoth_avg);
		//
		StatusLogger.getInstance().putVar("MSC", MSC_avg);
		StatusLogger.getInstance().putVar("LCOM2", LCOM2_avg);
		StatusLogger.getInstance().putVar("LCOM3", LCOM3_avg);
		//
		double ciaForClass = ChangeImpactAnalysis.getInstance().analysisOnClass();
		double ciaForMethod = ChangeImpactAnalysis.getInstance().analysisOnMethod();
		StatusLogger.getInstance().putVar("ciaForClass", (float) ciaForClass );
		StatusLogger.getInstance().putVar("ciaForMethod", (float) ciaForMethod );
//		StatusLogger.getInstance().putVar("normalizedDynamicBoth", ( DynamicBoth_avg )/( DynamicBoth_MAX ));
//		StatusLogger.getInstance().putVar("normalizedStaticBoth", ( StaticBoth_avg )/( StaticBoth_MAX ));
//		StatusLogger.getInstance().putVar("normalizedBothCoupling",
//				((( DynamicBoth_avg )/( DynamicBoth_MAX )) + (( StaticBoth_avg )/( StaticBoth_MAX )))/2);
//		StatusLogger.getInstance().putVar("normalizedLCOM2", ( LCOM2_avg )/( LCOM2_MAX ));
//		StatusLogger.getInstance().putVar("normalizedLCOM3", ( LCOM3_avg )/( LCOM3_MAX ));
		//
		//20110425
//		StatusLogger.getInstance().putVar("MPCDE", MPCDE_avg);
//		StatusLogger.getInstance().putVar("MPCDI", MPCDI_avg);
		//20110511
//		StatusLogger.getInstance().putVar("MPCSE", MPCSE_avg);
//		StatusLogger.getInstance().putVar("MPCSI", MPCSI_avg);
//		StatusLogger.getInstance().putVar("MPCSBoth", MPCSBoth_avg);
		
		// file write - printCurrentSuite() 만 함.
		StatusLogger.getInstance().printCurrentSuite();
		//
		System.err.print("Delta Prev\t");
		StatusLogger.getInstance().printDeltaWithPrevious();
		System.err.print("Delta Orig\t");
		StatusLogger.getInstance().printDeltaWithOriginal();
		
		return fitness;
			
	}
}
