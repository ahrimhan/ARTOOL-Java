package kr.ac.kaist.se.artool.engine;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.metrics.entityplacement.EntityPlacement;

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
	
	public float calculate(AbstractObjectModel aom, double refactoring_cost)
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
		//
		float MIF  = 0;
		float possible_overrides = 0;
		float total_overrides = 0;
		
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
		
		float MIF_MIN = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("MIF"));
		float MIF_MAX = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("MIF"));
//		float PF_MIN = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("PF"));
//		float PF_MAX = getFloat(aom.getClasses().get(0).getMeasuredDataSet().get("PF"));

		
		int clazz_size = aom.getClasses().size();
		int metric_size = aom.getClasses().get(0).getMeasuredDataSet().size();
		
		
		for( AOMClass clazz : aom.getClasses() )
		{

			int l_LOC = ((int[])clazz.getMeasuredDataSet().get("LOC"))[0];
			int l_NOM = ((int[])clazz.getMeasuredDataSet().get("NOM"))[0];
			int l_NOA = ((int[])clazz.getMeasuredDataSet().get("NOA"))[0];
			int l_NOCON = ((int[])clazz.getMeasuredDataSet().get("NOCON"))[0];
			int l_NOO = ((int[])clazz.getMeasuredDataSet().get("NOO"))[0];
			int l_DIT = ((int[])clazz.getMeasuredDataSet().get("DIT"))[0];
			int l_CLD = ((int[])clazz.getMeasuredDataSet().get("CLD"))[0];
			int l_NOC = ((int[])clazz.getMeasuredDataSet().get("NOC"))[0];
			int l_NOP = ((int[])clazz.getMeasuredDataSet().get("NOP"))[0];
			int l_NMO = ((int[])clazz.getMeasuredDataSet().get("NMO"))[0];
			int l_NMI = ((int[])clazz.getMeasuredDataSet().get("NMI"))[0];
			int l_NMA = ((int[])clazz.getMeasuredDataSet().get("NMA"))[0];
			int l_WMC = ((int[])clazz.getMeasuredDataSet().get("WMC"))[0];
			int l_DynamicImport = ((int[])clazz.getMeasuredDataSet().get("DynamicImport"))[0];
			int l_DynamicExport = ((int[])clazz.getMeasuredDataSet().get("DynamicExport"))[0];
			int l_StaticImport = ((int[])clazz.getMeasuredDataSet().get("StaticImport"))[0];
			int l_StaticExport = ((int[])clazz.getMeasuredDataSet().get("StaticExport"))[0];
			int l_DynamicBoth = ((int[])clazz.getMeasuredDataSet().get("DynamicBoth"))[0];
			int l_StaticBoth = ((int[])clazz.getMeasuredDataSet().get("StaticBoth"))[0];
			float l_LCOM2 = ((float[])clazz.getMeasuredDataSet().get("LCOM2"))[0];
			float l_LCOM3 = ((float[])clazz.getMeasuredDataSet().get("LCOM3"))[0];
			float l_MSC = ((float[])clazz.getMeasuredDataSet().get("MSC"))[0];
			int l_MPCDE = ((int[])clazz.getMeasuredDataSet().get("MPCDE"))[0];
			int l_MPCDI = ((int[])clazz.getMeasuredDataSet().get("MPCDI"))[0];
			int l_MPCDBoth = ((int[])clazz.getMeasuredDataSet().get("MPCDBoth"))[0];
			int l_MPCSE = ((int[])clazz.getMeasuredDataSet().get("MPCSE"))[0];
			int l_MPCSI = ((int[])clazz.getMeasuredDataSet().get("MPCSI"))[0];
			int l_MPCSBoth = ((int[])clazz.getMeasuredDataSet().get("MPCSBoth"))[0];
			float l_MIF = ((float[])clazz.getMeasuredDataSet().get("MIF"))[0];
//			float l_PF = getFloat(clazz.getMeasuredDataSet().get("PF"));
			
			LOC += l_LOC;
			NOM += l_NOM;
			NOA += l_NOA;
			NOCON += l_NOCON;
			NOO += l_NOO;
			DIT += l_DIT;
			CLD += l_CLD;
			NOC += l_NOC;
			NOP += l_NOP;
			NMO += l_NMO;
			NMI += l_NMI;
			NMA += l_NMA;
			WMC += l_WMC;
			DynamicImport += l_DynamicImport;
			DynamicExport += l_DynamicExport;
			StaticImport += l_StaticImport;
			StaticExport += l_StaticExport;
			DynamicBoth += l_DynamicBoth;
			StaticBoth += l_StaticBoth;
			LCOM2 += l_LCOM2;
			LCOM3 += l_LCOM3;
			MSC += l_MSC;
			MPCDE += l_MPCDE;
			MPCDI += l_MPCDI;
			MPCDBoth += l_MPCDBoth;
			MPCSE += l_MPCSE;
			MPCSI += l_MPCSI;
			MPCSBoth += l_MPCSBoth;
			MIF += l_MIF;
			possible_overrides += l_NMA + l_NOC;
			total_overrides += l_NMO;
			//1
			if( LOC_MIN > l_LOC )
			{
				LOC_MIN = l_LOC;
			}
			if ( LOC_MAX < l_LOC )
			{
				LOC_MAX = l_LOC;
			}
			
			//2
			if( NOM_MIN > l_NOM )
			{
				NOM_MIN = l_NOM;
			}
			if ( NOM_MAX < l_NOM )
			{
				NOM_MAX = l_NOM;
			}
			
			//3
			if( NOA_MIN > l_NOA )
			{
				NOA_MIN = l_NOA;
			}
			if ( NOA_MAX < l_NOA )
			{
				NOA_MAX = l_NOA;
			}
			
			//4
			if( NOCON_MIN > l_NOCON )
			{
				NOCON_MIN = l_NOCON;
			}
			if ( NOCON_MAX < l_NOCON )
			{
				NOCON_MAX = l_NOCON;
			}
			
			//5
			if( NOO_MIN > l_NOO )
			{
				NOO_MIN = l_NOO;
			}
			if ( NOO_MAX < l_NOO )
			{
				NOO_MAX = l_NOO;
			}
			
			//6
			if( DIT_MIN > l_DIT )
			{
				DIT_MIN = l_DIT;
			}
			if ( DIT_MAX < l_DIT )
			{
				DIT_MAX = l_DIT;
			}

			//7
			if( CLD_MIN > l_CLD )
			{
				CLD_MIN = l_CLD;
			}
			if ( CLD_MAX < l_CLD )
			{
				CLD_MAX = l_CLD;
			}
			
			//8
			if( NOC_MIN > l_NOC )
			{
				NOC_MIN = l_NOC;
			}
			if ( NOC_MAX < l_NOC )
			{
				NOC_MAX = l_NOC;
			}
			
			//9
			if( NOP_MIN > l_NOP )
			{
				NOP_MIN = l_NOP;
			}
			if ( NOP_MAX < l_NOP )
			{
				NOP_MAX = l_NOP;
			}
			
			//10
			if( NMO_MIN > l_NMO )
			{
				NMO_MIN = l_NMO;
			}
			if ( NMO_MAX < l_NMO )
			{
				NMO_MAX = l_NMO;
			}
			
			//11
			if( NMI_MIN > l_NMI )
			{
				NMI_MIN = l_NMI;
			}
			if ( NMI_MAX < l_NMI )
			{
				NMI_MAX = l_NMI;
			}
			
			//12
			if( NMA_MIN > l_NMA )
			{
				NMA_MIN = l_NMA;
			}
			if ( NMA_MAX < l_NMA )
			{
				NMA_MAX = l_NMA;
			}
			
			//13
			if( WMC_MIN > l_WMC )
			{
				WMC_MIN = l_WMC;
			}
			if ( WMC_MAX < l_WMC )
			{
				WMC_MAX = l_WMC;
			}
			
			//14
			if( DynamicImport_MIN > l_DynamicImport )
			{
				DynamicImport_MIN = l_DynamicImport;
			}
			if ( DynamicImport_MAX < l_DynamicImport )
			{
				DynamicImport_MAX = l_DynamicImport;
			}
			
			//15
			if( DynamicExport_MIN > l_DynamicExport )
			{
				DynamicExport_MIN = l_DynamicExport;
			}
			if ( DynamicExport_MAX < l_DynamicExport )
			{
				DynamicExport_MAX = l_DynamicExport;
			}
			
			//16
			if( StaticImport_MIN > l_StaticImport )
			{
				StaticImport_MIN = l_StaticImport;
			}
			if ( StaticImport_MAX < l_StaticImport )
			{
				StaticImport_MAX = l_StaticImport;
			}
			
			//17
			if( StaticExport_MIN > l_StaticExport )
			{
				StaticExport_MIN = l_StaticExport;
			}
			if ( StaticExport_MAX < l_StaticExport )
			{
				StaticExport_MAX = l_StaticExport;
			}
			
			//18
			if( DynamicBoth_MIN > l_DynamicBoth )
			{
				DynamicBoth_MIN = l_DynamicBoth;
			}
			if ( DynamicBoth_MAX < l_DynamicBoth )
			{
				DynamicBoth_MAX = l_DynamicBoth;
			}
			
			//19
			if( StaticBoth_MIN > l_StaticBoth )
			{
				StaticBoth_MIN = l_StaticBoth;
			}
			if ( StaticBoth_MAX < l_StaticBoth )
			{
				StaticBoth_MAX = l_StaticBoth;
			}
			
			//20
			if( LCOM2_MIN > l_LCOM2 )
			{
				LCOM2_MIN = l_LCOM2;
			}
			if ( LCOM2_MAX < l_LCOM2 )
			{
				LCOM2_MAX = l_LCOM2;
			}
			
			//21
			if( LCOM3_MIN > l_LCOM3 )
			{
				LCOM3_MIN = l_LCOM3;
			}
			if ( LCOM3_MAX < l_LCOM3 )
			{
				LCOM3_MAX = l_LCOM3;
			}
			
			//21.5
			if( MSC_MIN > l_MSC )
			{
				MSC_MIN = l_MSC;
			}
			if ( MSC_MAX < l_MSC )
			{
				MSC_MAX = l_MSC;
			}
			
			//22
			if( MPCDE_MIN > l_MPCDE )
			{
				MPCDE_MIN = l_MPCDE;
			}
			if ( MPCDE_MAX < l_MPCDE )
			{
				MPCDE_MAX = l_MPCDE;
			}
			
			//23
			if( MPCDI_MIN > l_MPCDI )
			{
				MPCDI_MIN = l_MPCDI;
			}
			if ( MPCDI_MAX < l_MPCDI )
			{
				MPCDI_MAX = l_MPCDI;
			}
			
			//24
			if( MPCDBoth_MIN > l_MPCDBoth )
			{
				MPCDBoth_MIN = l_MPCDBoth;
			}
			if ( MPCDBoth_MAX < l_MPCDBoth )
			{
				MPCDBoth_MAX = l_MPCDBoth;
			}
			
			//25
			if( MPCSE_MIN > l_MPCSE )
			{
				MPCSE_MIN = l_MPCSE;
			}
			if ( MPCSE_MAX < l_MPCSE )
			{
				MPCSE_MAX = l_MPCSE;
			}
			
			//26
			if( MPCSI_MIN > l_MPCSI )
			{
				MPCSI_MIN = l_MPCSI;
			}
			if ( MPCSI_MAX < l_MPCSI )
			{
				MPCSI_MAX = l_MPCSI;
			}
			
			//27
			if( MPCSBoth_MIN > l_MPCSBoth )
			{
				MPCSBoth_MIN = l_MPCSBoth;
			}
			if ( MPCSBoth_MAX < l_MPCSBoth )
			{
				MPCSBoth_MAX = l_MPCSBoth;
			}
		
			
			if( MIF_MIN > l_MIF )
			{
				MIF_MIN = l_MIF;
			}
			
			if( MIF_MAX < l_MIF )
			{
				MIF_MAX = l_MIF;
			}
			
	
		}
		
		float LOC_avg = ((float) LOC) / clazz_size;
		float NOM_avg = ((float) NOM) / clazz_size;
		float NOA_avg = ((float) NOA) / clazz_size;
		float NOCON_avg = ((float) NOCON) / clazz_size;
        float NOO_avg = ((float) NOO) / clazz_size;
        float DIT_avg = ((float) DIT) / clazz_size;
        float CLD_avg = ((float) CLD) / clazz_size;
        float NOC_avg = ((float) NOC) / clazz_size;
        float NOP_avg = ((float) NOP) / clazz_size;
        float NMO_avg = ((float) NMO) / clazz_size;
        float NMI_avg = ((float) NMI) / clazz_size;
        float NMA_avg = ((float) NMA) / clazz_size;
        float WMC_avg = ((float) WMC) / clazz_size;
        float DynamicImport_avg = ((float) DynamicImport) / clazz_size;
        float DynamicExport_avg = ((float) DynamicExport) / clazz_size;
        float StaticImport_avg = ((float) StaticImport) / clazz_size;
        float StaticExport_avg = ((float) StaticExport) / clazz_size;
        float DynamicBoth_avg = ((float) DynamicBoth) / clazz_size;
        float StaticBoth_avg = ((float) StaticBoth) / clazz_size;
        float LCOM2_avg = (float) LCOM2 / clazz_size;
        float LCOM3_avg = (float) LCOM3 / clazz_size;   
        float MSC_avg = ((float) MSC) / clazz_size;     
        float MPCDE_avg = ((float) MPCDE) / clazz_size;
        float MPCDI_avg = ((float) MPCDI) / clazz_size;
        float MPCDBoth_avg = ((float) MPCDBoth) / clazz_size;
        float MPCSE_avg = ((float) MPCSE) / clazz_size;
        float MPCSI_avg = ((float) MPCSI) / clazz_size;
        float MPCSBoth_avg = ((float) MPCSBoth) / clazz_size;
		float MIF_avg = ((float) MIF) / clazz_size;
		float PF_avg = total_overrides / possible_overrides;
		
		
		//evaluation function (fitness function)
		int evaluation_mode = 4;
		
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
					((( MSC_avg - MSC_MIN)/( MSC_MAX - MSC_MIN + 0.000001 )))
					
				) 
				/
				( 
					(0.5 * (( DynamicBoth_avg - DynamicBoth_MIN)/( DynamicBoth_MAX - DynamicBoth_MIN + 0.000001 ))) +
					(0.5 * (( MPCDBoth_avg - MPCDBoth_MIN )/( MPCDBoth_MAX - MPCDBoth_MIN + 0.000001 ))) + 0.000001

				)
				); 

		//case6
		float fitness3 = (float) (
				( 
					((( MSC_avg )/( MSC_MAX )))
					//(0.25 * (( LCOM2_avg )/( LCOM2_MAX ))) +
					//(0.25 * (( LCOM3_avg )/( LCOM3_MAX )))
					//LCOM 은 lack of cohesion in method라서 분모로 가야한다!
					
				)  //test comment
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
					( MSC_avg )/( MSC_MAX )
					//(0.25 * (( LCOM2_avg )/( LCOM2_MAX ))) +
					//(0.25 * (( LCOM3_avg )/( LCOM3_MAX )))
						
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
		
		refactoring_cost = refactoring_cost + 1;
		
		StatusLogger.getInstance().putVar("norm_fitness2", StatusLogger.getInstance().getDeltaWithPrevious("fitness2") / (float)refactoring_cost);
		StatusLogger.getInstance().putVar("norm_fitness3", StatusLogger.getInstance().getDeltaWithPrevious("fitness3") / (float)refactoring_cost);

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
		StatusLogger.getInstance().putVar("norm_ciaForClass", StatusLogger.getInstance().getDeltaWithPrevious("ciaForClass") / (float)refactoring_cost);
		StatusLogger.getInstance().putVar("norm_ciaForMethod", StatusLogger.getInstance().getDeltaWithPrevious("ciaForMethod") / (float)refactoring_cost);

		
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
		StatusLogger.getInstance().putVar("MPCSBoth", MPCSBoth_avg);
		
		StatusLogger.getInstance().putVar("DIT", DIT_avg);
		StatusLogger.getInstance().putVar("MIF", MIF_avg);
		StatusLogger.getInstance().putVar("PF", PF_avg);
		


		
		
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
