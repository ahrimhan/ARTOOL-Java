package kr.ac.kaist.se.artool.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.metrics.N_DCICM;
import kr.ac.kaist.se.artool.engine.metrics.N_DCICM_Static;
import kr.ac.kaist.se.artool.engine.metrics.N_IBDPC;
import kr.ac.kaist.se.artool.engine.metrics.N_IBDPC_Static;
import kr.ac.kaist.se.artool.engine.rules.AbstractRule;
import kr.ac.kaist.se.artool.engine.rules.ClassStat;
import kr.ac.kaist.se.artool.engine.rules.Rule1;
import kr.ac.kaist.se.artool.engine.rules.Rule10;
import kr.ac.kaist.se.artool.engine.rules.Rule10_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule1_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule2;
import kr.ac.kaist.se.artool.engine.rules.Rule2_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule3;
import kr.ac.kaist.se.artool.engine.rules.Rule3_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule4;
import kr.ac.kaist.se.artool.engine.rules.Rule4_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule5;
import kr.ac.kaist.se.artool.engine.rules.Rule5_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule6;
import kr.ac.kaist.se.artool.engine.rules.Rule6_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule7;
import kr.ac.kaist.se.artool.engine.rules.Rule7_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule8;
import kr.ac.kaist.se.artool.engine.rules.Rule8_Static;
import kr.ac.kaist.se.artool.engine.rules.Rule9;
import kr.ac.kaist.se.artool.engine.rules.Rule9_Static;
import kr.ac.kaist.se.artool.engine.ruleselector.BestSelector;
import kr.ac.kaist.se.artool.engine.ruleselector.InteractiveSelector;
import kr.ac.kaist.se.artool.engine.ruleselector.RuleSelector;
import kr.ac.kaist.se.artool.util.UtilityFunctions;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ARToolMain {
	//static float[] prev_metrics;
	private static ARToolMain instance;
	private static final int INTERACTIVE_CHOICE = 0;
	private static final int BEST_CHOICE = 1;
	// FIXME: 
	private int chosenSelector = INTERACTIVE_CHOICE;
	
	private static RuleSelector[] selectorSet = { new InteractiveSelector(), new BestSelector()};
	
	private FileOutputStream fos;
	private PrintStream ps;
	private PrintStream ps1;
	private PrintStream ps2;
	
	public PrintStream getPrintStream() {
		return ps;
	}

	public PrintStream getPrintStream1() {
		return ps1;
	}
	
	public PrintStream getPrintStream2() {
		return ps2;
	}
	
	private int numPerformingRefactoring = 0;
	//mode
	private static int evaluation_mode = 0;
	//private static boolean useMAX_NDCICM = false;
	private static boolean dynamic_mode = true;
	
	
//	public boolean isUseMAX_NDCICM() {
//		return useMAX_NDCICM;
//	}
//
//	public void setUseMAX_NDCICM(boolean useMAX_NDCICM) {
//		ARToolMain.useMAX_NDCICM = useMAX_NDCICM;
//	}

	public static boolean isDynamic_mode() {
		return dynamic_mode;
	}

	public static void setDynamic_mode(boolean dynamic_mode) {
		ARToolMain.dynamic_mode = dynamic_mode;
	}

	public int getEvaluation_mode() {
		return evaluation_mode;
	}

	public void setEvaluation_mode(int evaluation_mode) {
		ARToolMain.evaluation_mode = evaluation_mode;
	}

	private ARToolMain()
	{
		try {
			ps = new PrintStream(new FileOutputStream("./resultARTool/metric_result1.txt"));
			ps1 = new PrintStream(new FileOutputStream("./resultARTool/basicmetricsuite.txt"));
			ps2 = new PrintStream(new FileOutputStream("./resultARTool/changeimpactanalysis.txt"));
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println ("Error in writing to file");
		}
		
		setEvaluation_mode(4);
		//setUseMAX_NDCICM(true);
		setDynamic_mode(false);
		
		
	}
	
	public static ARToolMain getInstance()
	{
		if( instance == null )
		{
			instance = new ARToolMain();
		}
		
		return instance;
	}
	

	private static final int cutline = 10;
	
	private void trialExecute(Vector<AbstractRule> rules)
	{
		
		for( AbstractRule rule : rules )
		{
			System.err.print("Trial["+rule.getName()+"]\t");
			ps.print("Trial["+rule.getName()+"]\t");
			StatusLogger.getInstance().openTrialSuite(rule);
			rule.trial();
		}
	}
	
	private AbstractRule selectRule(Vector<AbstractRule> rules) {
		return selectorSet[chosenSelector].select(rules);
	}

	private boolean execute(AbstractRule rule)
	{
		boolean isPerformed = false; 
		if( rule != null )
		{
			numPerformingRefactoring++;
			System.err.println(numPerformingRefactoring + "\tExecute["+rule.getName()+"]\t");
			ps.println(numPerformingRefactoring + "\tExecute["+rule.getName()+"]\t"+rule.getStatus()+"\t");
			isPerformed = rule.perform();
		}
		return isPerformed;
		
	}
	
	//FIXME: need to fix rule4 
	public EMap<HashSet<AOMClass>, Integer> getRule4List(AbstractObjectModel aom, N_DCICM n_dcicm)
	{
		int ndcicm = 0;
		int numStaticMethodCalls = 0;
		
		EMap<HashSet<AOMClass>, Integer> rule4list = new BasicEMap<HashSet<AOMClass>, Integer>();
		
		
		AOMClass[] aomClasses;
		AOMClass[] source_targetClasses = new AOMClass[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				ndcicm = BasicMetricSuite.getInt(method.getMeasuredDataSet().get("N_DCICM"));
				aomClasses = n_dcicm.getDCICM().get(method).toArray(new AOMClass[ndcicm]);
				
				if( method.getOwnedScope() != null )
				{
					numStaticMethodCalls = method.getOwnedScope().getStaticMethodCalls().size();
					if( ndcicm != 0 && numStaticMethodCalls != 0 &&
							ndcicm == numStaticMethodCalls )
					{
						for( int i = 0; i < aomClasses.length - 1 ; )
						{
							source_targetClasses[0] = aomClasses[i];
							source_targetClasses[1] = aomClasses[++i];
							UtilityFunctions.getInstance().increase(rule4list, source_targetClasses[0], source_targetClasses[1]);
						}
					}
				}
				ndcicm =0;
				numStaticMethodCalls = 0;
			}
		}
		return rule4list;
	}
	
//	public EMap<HashSet<AOMClass>, Integer> getRule4List(AbstractObjectModel aom, N_DCICM n_dcicm)
//	{
//		int ndcicm = 0;
//		int numDynamicMethodCalls = 0;
//		
//		EMap<HashSet<AOMClass>, Integer> rule4list = new BasicEMap<HashSet<AOMClass>, Integer>();
//		
//		
//		AOMClass[] aomClasses;
//		AOMClass[] source_targetClasses = new AOMClass[2]; 
//		
//		for( AOMClass clazz : aom.getClasses() )
//		{
//			for( AOMMethod method : clazz.getMethods() )
//			{
//				ndcicm = BasicMetricSuite.getInt(method.getMeasuredDataSet().get("N_DCICM"));
//				aomClasses = n_dcicm.getDCICM().get(method).toArray(new AOMClass[ndcicm]);
//				
//				if( method.getOwnedScope() != null )
//				{
//					numDynamicMethodCalls = method.getOwnedScope().getDynamicMethodCalls().size();
//					if( ndcicm != 0 && numDynamicMethodCalls != 0 &&
//							ndcicm == numDynamicMethodCalls )
//					{
//						for( int i = 0; i < aomClasses.length - 1 ; )
//						{
//							source_targetClasses[0] = aomClasses[i];
//							source_targetClasses[1] = aomClasses[++i];
//							UtilityFunctions.getInstance().increase(rule4list, source_targetClasses[0], source_targetClasses[1]);
//						}
//					}
//				}
//				ndcicm =0;
//				numDynamicMethodCalls = 0;
//			}
//		}
//		return rule4list;
//	}
	
	public EMap<HashSet<AOMClass>, Integer> getRule4List_Static(AbstractObjectModel aom, N_DCICM_Static n_dcicm_static)
	{
		int ndcicm_static = 0;
		int numStaticMethodCalls = 0;
		
		EMap<HashSet<AOMClass>, Integer> rule4list = new BasicEMap<HashSet<AOMClass>, Integer>();
		
		
		AOMClass[] aomClasses;
		AOMClass[] source_targetClasses = new AOMClass[2]; 
		
		for( AOMClass clazz : aom.getClasses() )
		{
			for( AOMMethod method : clazz.getMethods() )
			{
				ndcicm_static = BasicMetricSuite.getInt(method.getMeasuredDataSet().get("N_DCICM_Static"));
				aomClasses = n_dcicm_static.getDCICM_Static().get(method).toArray(new AOMClass[ndcicm_static]);
				
				if( method.getOwnedScope() != null )
				{
					numStaticMethodCalls = method.getOwnedScope().getStaticMethodCalls().size();
					if( ndcicm_static != 0 && numStaticMethodCalls!=0 &&
							ndcicm_static == numStaticMethodCalls )
					{
						for( int i = 0; i < aomClasses.length - 1 ; )
						{
							source_targetClasses[0] = aomClasses[i];
							source_targetClasses[1] = aomClasses[++i];
							UtilityFunctions.getInstance().increase(rule4list, source_targetClasses[0], source_targetClasses[1]);
						}
					}
				}
				ndcicm_static =0;
				numStaticMethodCalls = 0;
			}
		}
		return rule4list;
	}
	
	public static int getInt(Object obj)
	{
		return BasicMetricSuite.getInt(obj);
	}
	
	
	//always followed after FitnessFunction.getInstance().calculate(aom);
	public void printCouplingRelatedMetrics(AbstractObjectModel aom, PrintStream ps)
	{
		//20110425 for temporary printing for comparison
		//not average, for each class
		for( AOMClass clazz : aom.getClasses() )
		{
			ps.print(clazz.getFqdn()+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("DynamicExport"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("StaticExport"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("MPCDE"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("MPCSE"))+"\t");
			//
			ps.print(getInt(clazz.getMeasuredDataSet().get("DynamicImport"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("StaticImport"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("MPCDI"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("MPCSI"))+"\t");
			//
			ps.print(getInt(clazz.getMeasuredDataSet().get("DynamicBoth"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("StaticBoth"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("MPCDBoth"))+"\t");
			ps.print(getInt(clazz.getMeasuredDataSet().get("MPCSBoth"))+"\t");
			//
			ps.println();
		}
	}
	
	public void printInitialItems()
	{
		
		if(isDynamic_mode() == true)
		{
			ARToolMain.getInstance().getPrintStream1().println("Basically based on DynamicMethodCall.");
		}
		else
		{
			ARToolMain.getInstance().getPrintStream1().println("Basically based on StaticMethodCall.");
		}
		
		ARToolMain.getInstance().getPrintStream1().print("ClassName"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("DynamicExport"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("StaticExport"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("MPCDE"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("MPCSE"+"\t");
		//
		ARToolMain.getInstance().getPrintStream1().print("DynamicImport"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("StaticImport"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("MPCDI"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("MPCSI"+"\t");
		//
		ARToolMain.getInstance().getPrintStream1().print("DynamicBoth"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("StaticBoth"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("MPCDBoth"+"\t");
		ARToolMain.getInstance().getPrintStream1().print("MPCSBoth"+"\t");
		//
		ARToolMain.getInstance().getPrintStream1().println();
	}
	private static MessageDialogWithToggle dialog;	
	public void run(AbstractObjectModel aom, final Shell shell) throws IOException
	{	
	
		shell.getDisplay().syncExec(
				  new Runnable() {
				    public void run(){
				    	 dialog = 
							MessageDialogWithToggle.openYesNoQuestion(shell, "Analysis Selection", "Select dynamic or static analysis", "Dynamic?", true, null, null);
				    }
				  });
	
		this.setDynamic_mode(dialog.getToggleState());
		StatusLogger.getInstance().clear();
		ChangeImpactAnalysis cia = ChangeImpactAnalysis.getInstance();
		
		ps.print("Rule\t");
		ps.print("fitness2\t");
		ps.print("fitness3\t");
		ps.print("fitness_static\t");
		ps.print("StaticBoth\t");
		ps.print("DynamicBoth\t");
		ps.print("MPCDBoth\t");
		ps.print("MSC\t");
		ps.print("LCOM2\t");
		ps.println("LCOM3");
		
		System.err.print("Original\t");
		//file write for further analysis
		ps.print("Original\t");
		//
		
		StatusLogger.getInstance().openOriginalPhase();
		float originalFitness = FitnessFunction.getInstance().calculate(aom);
		
		//print file[basicmetricsuite.txt] for easy comparison
		printInitialItems();
		printCouplingRelatedMetrics(aom, ARToolMain.getInstance().getPrintStream1());
		
		String changeFile = "./resultARTool/modifiedMethod.csv";
		
		cia.init(aom, changeFile);
		
		if( isDynamic_mode() )
		{
			System.err.print("Dynamic!!");
			getPrintStream2().print("Dynamic!!");
			
			if(cia.isStepTwo())
			{
				System.err.println(" (step2)");
				getPrintStream2().println(" (step2)");
			}
			
			else
			{
				System.err.println(" (step1)");
				getPrintStream2().println(" (step1)");
			}
			
		}
		else
		{
			System.err.print("Static!!");
			getPrintStream2().print("Static!!");
			
			if(cia.isStepTwo())
			{
				System.err.println(" (step2)");
				getPrintStream2().println(" (step2)");
			}
			
			else
			{
				System.err.println(" (step1)");
				getPrintStream2().println(" (step1)");
			}
		}
		
		//change impact analysis
		double ciaForClass = cia.analysisOnClass();
		double ciaForMethod = cia.analysisOnMethod();
				
		System.out.println("CIA for Class:" + ciaForClass);
		System.out.println("CIA for Method:" + ciaForMethod);
		getPrintStream2().println("CIA for Class:" + ciaForClass);
		getPrintStream2().println("CIA for Method:" + ciaForMethod);

		if(isDynamic_mode() == true) //basically based on DynamicMethodCall
		{
			
			do
			{
				N_IBDPC n_ibdpc = new N_IBDPC();
				n_ibdpc.measure(aom);
				
				N_DCICM n_dcicm = new N_DCICM();
				n_dcicm.measure(aom);
				
				Map.Entry<HashSet<AOMClass>, Integer>[] n_IBDPC = n_ibdpc.getSortedIBDPC(cutline);
				Map.Entry<HashSet<AOMMethod>, Integer>[] n_IBDPM = n_ibdpc.getSortedIBDPM(cutline);

				//AbstractRule rule4 = new Rule4(aom, sortedRule4list);
				Map.Entry<HashSet<AOMClass>, Integer>[] sortedRule4list =
					UtilityFunctions.getInstance().__getSortedIBDP(getRule4List(aom, n_dcicm), cutline);
				
				
				// rule setting
				Vector<AbstractRule> rules = new Vector<AbstractRule>();
				// my approach (dynamic)
				rules.add(new Rule1(aom, n_IBDPC));
				rules.add(new Rule2(aom, n_IBDPM));
				rules.add(new Rule3(aom, n_IBDPM));
				rules.add(new Rule4(aom, sortedRule4list));
				rules.add(new Rule5(aom, cutline));
				rules.add(new Rule6(aom, cutline));
				rules.add(new Rule7(aom, cutline));
				rules.add(new Rule8(aom, cutline));
				rules.add(new Rule9(aom, cutline));
				rules.add(new Rule10(aom, cutline));
				
				StatusLogger.getInstance().openTrialPhase();
				// trial execution for rules
				trialExecute(rules);
			
				
				AbstractRule selectedRule = selectRule(rules);
				StatusLogger.getInstance().selectSuite(selectedRule);
				
				boolean isExcuted = execute(selectedRule);
				
				if(isExcuted)
				{
					System.err.print("Performed\t");
					ps.print("Performed\t");
					
					FitnessFunction.getInstance().calculate(aom);
					printCouplingRelatedMetrics(aom, ARToolMain.getInstance().getPrintStream1());
					
					//change impact analysis
					System.out.print(numPerformingRefactoring +"\tCIA for Class\t" + cia.analysisOnClass());
					System.out.println("\tCIA for Method\t" + cia.analysisOnMethod());
					getPrintStream2().print(numPerformingRefactoring +"\tCIA for Class\t" + cia.analysisOnClass());
					getPrintStream2().println("\tCIA for Method\t" + cia.analysisOnMethod());
				}
				
				System.gc();
			}while( askContinue(shell) );
						
			
//			ClassStat.getDynamicStat().export("./resultARTool/ClassStat.");
		}
		else //basically based on StaticMethodCall
		{
			do
			{
				N_IBDPC_Static n_ibdpc_static = new N_IBDPC_Static();
				n_ibdpc_static.measure(aom);
				
				N_DCICM_Static n_dcicm_static = new N_DCICM_Static();
				n_dcicm_static.measure(aom);
				
				Map.Entry<HashSet<AOMClass>, Integer>[] n_IBDPC_Static = n_ibdpc_static.getSortedIBDPC(cutline);
				Map.Entry<HashSet<AOMMethod>, Integer>[] n_IBDPM_Static = n_ibdpc_static.getSortedIBDPM(cutline);
				//
				//AbstractRule rule4 = new Rule4(aom, sortedRule4list);
				Map.Entry<HashSet<AOMClass>, Integer>[] sortedRule4list =
					UtilityFunctions.getInstance().__getSortedIBDP(getRule4List_Static(aom, n_dcicm_static), cutline);
				
				// rule setting
				Vector<AbstractRule> rules = new Vector<AbstractRule>();
				// static approach
				rules.add(new Rule1_Static(aom, n_IBDPC_Static));
				rules.add(new Rule2_Static(aom, n_IBDPM_Static));
				rules.add(new Rule3_Static(aom, n_IBDPM_Static));
				rules.add(new Rule4_Static(aom, sortedRule4list));
				rules.add(new Rule5_Static(aom, cutline));
				rules.add(new Rule6_Static(aom, cutline));
				rules.add(new Rule7_Static(aom, cutline));
				rules.add(new Rule8_Static(aom, cutline));
				rules.add(new Rule9_Static(aom, cutline));
				rules.add(new Rule10_Static(aom, cutline));
				
				StatusLogger.getInstance().openTrialPhase();
				// trial execution for rules
				trialExecute(rules);
			
				
				AbstractRule selectedRule = selectRule(rules);
				StatusLogger.getInstance().selectSuite(selectedRule);
				
				//execute(selectedRule);
				boolean isExcuted = execute(selectedRule);
				
				if(isExcuted)
				{
					System.err.print("Performed\t");
					ps.print("Performed\t");
					
					FitnessFunction.getInstance().calculate(aom);
					printCouplingRelatedMetrics(aom, ARToolMain.getInstance().getPrintStream1());
					
					System.out.print(numPerformingRefactoring +"\tCIA for Class\t" + cia.analysisOnClass());
					System.out.println("\tCIA for Method\t" + cia.analysisOnMethod());
					getPrintStream2().print(numPerformingRefactoring +"\tCIA for Class\t" + cia.analysisOnClass());
					getPrintStream2().println("\tCIA for Method\t" + cia.analysisOnMethod());
					
//					System.out.println("CIA for Class:" + cia.analysisOnClass());
//					System.out.println("CIA for Method:" + cia.analysisOnMethod());
//					getPrintStream2().println("CIA for Class:" + cia.analysisOnClass());
//					getPrintStream2().println("CIA for Method:" + cia.analysisOnMethod());
	
				}
				System.gc();
			}while( askContinue(shell) );
			
//			ClassStat.getStaticStat().export("./resultARTool/ClassStat.");
		}

		//igsong: 20110531
//		String changeFile = "./resultARTool/modifiedMethod.csv";
//		ChangeImpactAnalysis cia = ChangeImpactAnalysis.getInstance(aom);
//		int ciaForClass = cia.analysisOnClass(changeFile);
//		int ciaForMethod = cia.analysisOnMethod(changeFile);
//		
//		if( isDynamic_mode() )
//		{
//			System.err.println("Dynamic!!");
//		}
//		else
//		{
//			System.err.println("Static!!");
//		}
//		
//		System.out.println("CIA for Class:" + ciaForClass);
//		System.out.println("CIA for Method:" + ciaForMethod);
//		
		ps.close();
		ps1.close();
		ps2.close();
/////// If the AbstractObjectModel should be saved, please remove below comment marks
//		
//		
//		aom.eResource().save(null);
//		
//		
	}
	
	private boolean cont = false;

	private boolean askContinue(final Shell shell) {
		cont = false;
		Display display = shell.getDisplay();
		display.syncExec(
				  new Runnable() {
				    public void run(){
						cont = MessageDialog.openQuestion(shell, "Continue?",  "Do you want to continue refactoring?");
				    }
				  });
		return cont;
	}

	
	private static final String[] labelArray = {"Yes", "No"};
}
