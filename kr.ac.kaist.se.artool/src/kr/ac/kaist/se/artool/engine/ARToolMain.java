package kr.ac.kaist.se.artool.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.metrics.N_ConsecutiveCall;
import kr.ac.kaist.se.artool.engine.metrics.N_DCICM;
import kr.ac.kaist.se.artool.engine.metrics.N_IBDPC;
import kr.ac.kaist.se.artool.engine.metrics.N_RestrictedVerticalLocality;
import kr.ac.kaist.se.artool.engine.rules.AbstractRule;
import kr.ac.kaist.se.artool.engine.rules.ClassStat;
import kr.ac.kaist.se.artool.engine.rules.CollapseClasRule;
import kr.ac.kaist.se.artool.engine.rules.MoveMethod1Rule;
import kr.ac.kaist.se.artool.engine.rules.MoveMethod2Rule;
import kr.ac.kaist.se.artool.engine.ruleselector.BestSelector;
import kr.ac.kaist.se.artool.engine.ruleselector.InteractiveSelector;
import kr.ac.kaist.se.artool.engine.ruleselector.RuleSelector;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ARToolMain {
	//static float[] prev_metrics;
	private static ARToolMain instance;
	private static final int INTERACTIVE_CHOICE = 0;
	private static final int BEST_CHOICE = 1;
	private static final int locality_from = 2;
	private static final int locality_to = 5;
	
	public static final int DYNAMIC_MODE = 0;
	public static final int STATIC_MODE = 1;
	public static final int DYNAMIC_STATIC_MODE = 2;
	
	private static int execution_mode = DYNAMIC_MODE;
	
	public static void setExecutionMode(int mode)
	{
		execution_mode = mode;
	}
	
	public static int getExecutionMode()
	{
		return execution_mode;
	}
	
	private int pickUntil = 10;
	private boolean tsantalis = false;
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
	
	private int accumulated_refactoring_cost = 0;
	//mode
	//private static boolean useMAX_NDCICM = false;


	//	public boolean isUseMAX_NDCICM() {
	//		return useMAX_NDCICM;
	//	}
	//
	//	public void setUseMAX_NDCICM(boolean useMAX_NDCICM) {
	//		ARToolMain.useMAX_NDCICM = useMAX_NDCICM;
	//	}



	private ARToolMain()
	{
		try {

			ps = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/metric_result1.txt"));
			ps1 = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/basicmetricsuite.txt"));
			ps2 = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/changeimpactanalysis.txt"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println ("Error in writing to file");
		}

		//setUseMAX_NDCICM(true);


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

	private double execute(AbstractRule rule)
	{
		double ret = -1; 
		if( rule != null )
		{
			ret = rule.perform();

			numPerformingRefactoring++;
			accumulated_refactoring_cost += ret;
			
			System.err.println(numPerformingRefactoring + "\t" + accumulated_refactoring_cost + "\tExecute["+rule.getName()+"]\t");
			ps.println(numPerformingRefactoring + "\t" + accumulated_refactoring_cost + "\tExecute["+rule.getName()+"]\t"+rule.getStatus()+"\t");
		}
		return ret;

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

		switch(getExecutionMode())
		{
		case DYNAMIC_MODE:
			ARToolMain.getInstance().getPrintStream1().println("Basically based on DynamicMethodCall.");
			break;
		case STATIC_MODE:
			ARToolMain.getInstance().getPrintStream1().println("Basically based on StaticMethodCall.");
			break;
		case DYNAMIC_STATIC_MODE:
			ARToolMain.getInstance().getPrintStream1().println("Basically based on Dynamic and StaticMethodCall.");
			break;
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
		numPerformingRefactoring = 0;
		accumulated_refactoring_cost = 0;
		
		StatusLogger.getInstance().clear();
		ChangeImpactAnalysis cia = ChangeImpactAnalysis.getInstance();
		//		String changeFile = "/Users/wjsong/git/ARTOOL/jgit/modifiedMethod.csv";
		String changeFile = "/Users/wjsong/git/ARTOOL/resultARTool/modifiedMethod.csv";
		cia.init(aom, changeFile);

		ps.print("Rule\t");
		ps.print("fitness2\t");
		ps.print("fitness3\t");
		ps.print("fitness_static\t");
		ps.print("norm_fitness2\t");
		ps.print("norm_fitness3\t");
		
		ps.print("StaticBoth\t");
		ps.print("DynamicBoth\t");
		ps.print("MPCDBoth\t");
		ps.print("MSC\t");
		ps.print("LCOM2\t");
		ps.print("LCOM3\t");
		ps.print("ciaForClass\t");
		ps.print("ciaForMethod\t");
		ps.print("norm_ciaForClass\t");
		ps.print("norm_ciaForMethod\t");
		ps.print("MPCSBoth\t");
		ps.print("DIT\t");
		ps.print("MIF\t");
		ps.print("PF\t");
		ps.println();

		
		System.err.print("Original\t");
		//file write for further analysis
		ps.print("Original\t");
		//

		StatusLogger.getInstance().openOriginalPhase();
		
		float originalFitness = FitnessFunction.getInstance().calculate(aom, 0);

		//print file[basicmetricsuite.txt] for easy comparison
		printInitialItems();
		printCouplingRelatedMetrics(aom, ARToolMain.getInstance().getPrintStream1());



		switch(getExecutionMode())
		{
		case DYNAMIC_MODE:
			System.err.print("Dynamic!!");
			getPrintStream2().print("Dynamic!!");
			break;
		case STATIC_MODE:
			System.err.print("Static!!");
			getPrintStream2().print("Static!!");
			break;
		case DYNAMIC_STATIC_MODE:
			System.err.print("Dynamic&Static!!");
			getPrintStream2().print("Dynamic&Static!!");
			break;

		}

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
		
		//change impact analysis
		double ciaForClass = cia.analysisOnClass();
		double ciaForMethod = cia.analysisOnMethod();

		System.out.println("CIA for Class:" + ciaForClass);
		System.out.println("CIA for Method:" + ciaForMethod);
		getPrintStream2().println("CIA for Class:" + ciaForClass);
		getPrintStream2().println("CIA for Method:" + ciaForMethod);
		double entityplacement = 0;
		long mem_usage = 0;

		do
		{
			// rule setting
			Vector<AbstractRule> rules = new Vector<AbstractRule>();
			boolean dynamic_mode = true;
			
			switch( getExecutionMode() )
			{
			case DYNAMIC_MODE:
			case DYNAMIC_STATIC_MODE:
				dynamic_mode = true;
				break;
			case STATIC_MODE:
				dynamic_mode = false;
				break;
			}
			
			for( int k = 0; k < 2; k++ )
			{
				N_IBDPC n_ibdpc = N_IBDPC.createInstance(dynamic_mode);
				N_DCICM n_dcicm = N_DCICM.createInstance(dynamic_mode);
				N_ConsecutiveCall n_cc = N_ConsecutiveCall.createInstance(dynamic_mode);
				N_RestrictedVerticalLocality n_rv= N_RestrictedVerticalLocality.createInstance(dynamic_mode, locality_from, locality_to);

				mem_usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.err.println("Before measure:" + mem_usage);
				
				n_ibdpc.measure(aom);
				n_dcicm.measure(aom);
				n_cc.measure(aom);				
				n_rv.measure(aom, n_dcicm);
				
				mem_usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.err.println("After measure:" + mem_usage);
				

				Map.Entry<MultiKey<AOMClass>, int[]>[] n_IBDPC = n_ibdpc.getSortedIBDPC(cutline);
				Map.Entry<MultiKey<AOMMethod>, int[]>[] n_IBDPM = n_ibdpc.getSortedIBDPM(cutline);

				Map.Entry<MultiKey<AOMClass>, int[]>[] n_CCC = n_cc.getSortedCCC(cutline);
				Map.Entry<MultiKey<AOMMethod>, int[]>[] n_CCM = n_cc.getSortedCCM(cutline);
				
				mem_usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.err.println("After sorting:" + mem_usage);
				
                for(int i = 0; i < pickUntil; i++) {
                	try {
                		rules.add(new CollapseClasRule(aom, n_IBDPC, i, dynamic_mode ? "DRule1" : "SRule1", getExecutionMode()));
                	} catch(Exception ex) { }
                }
                for(int i = 0; i < pickUntil; i++) {
                	try {
                		rules.add(new MoveMethod1Rule(aom, n_IBDPM, i, dynamic_mode ? "DRule2" : "SRule2", getExecutionMode()));
                	} catch(Exception ex) { }
                }
                for(int i = 0; i < pickUntil; i++) {
                	try {
                		rules.add(new MoveMethod2Rule(aom, n_IBDPM, i, dynamic_mode ? "DRule3" : "SRule3", getExecutionMode()));
                	} catch(Exception ex) { }
                }

                for( int locality = locality_from; locality <= locality_to; locality++ )
                {

                	Map.Entry<MultiKey<AOMClass>, int[]>[] n_RVC = n_rv.getSortedClassLevel(locality, cutline);
                	Map.Entry<MultiKey<AOMMethod>, int[]>[] n_RVM = n_rv.getSortedMethodLevel(locality, cutline);

                	for(int i = 0; i < pickUntil; i++) {
                		try {
                			rules.add(new CollapseClasRule(aom, n_RVC, i, (dynamic_mode ? "DRule4<" : "SRule4<") + locality + ">", getExecutionMode()));
                		} catch(Exception ex) { ex.printStackTrace(); }
                	}
                	for(int i = 0; i < pickUntil; i++) {
                		try {
                			rules.add(new MoveMethod1Rule(aom, n_RVM, i, (dynamic_mode ? "DRule5<" : "SRule5<") + locality + ">", getExecutionMode()));
                		} catch(Exception ex) {ex.printStackTrace(); }
                	}
                	for(int i = 0; i < pickUntil; i++) {
                		try {
                			rules.add(new MoveMethod2Rule(aom, n_RVM, i, (dynamic_mode ? "DRule6<" : "SRule6<") + locality + ">", getExecutionMode()));
                		} catch(Exception ex) { ex.printStackTrace();}
                	}
                }

                for(int i = 0; i < pickUntil; i++) {
                	try {
                		rules.add(new CollapseClasRule(aom, n_CCC, i, dynamic_mode ? "DRule7" : "SRule7", getExecutionMode()));
                	} catch(Exception ex) { }
                }
                for(int i = 0; i < pickUntil; i++) {
                	try {
                		rules.add(new MoveMethod1Rule(aom, n_CCM, i, dynamic_mode ? "DRule8" : "SRule8", getExecutionMode()));
                	} catch(Exception ex) { }
                }
                for(int i = 0; i < pickUntil; i++) {
                	try {
                		rules.add(new MoveMethod2Rule(aom, n_CCM, i, dynamic_mode ? "DRule9" : "SRule9", getExecutionMode()));
                	} catch(Exception ex) { }
                }



				if( getExecutionMode() == DYNAMIC_STATIC_MODE )
				{
					dynamic_mode = false;
				}
				else
				{
					break;
				}
			} 

			StatusLogger.getInstance().openTrialPhase();
			// trial execution for rules
			trialExecute(rules);
			StatusLogger.getInstance().printStatistics("fitness2");
			StatusLogger.getInstance().printStatistics("fitness3");

			AbstractRule selectedRule = selectRule(rules);
			StatusLogger.getInstance().selectSuite(selectedRule);

			double refactoring_cost = execute(selectedRule);

			if( refactoring_cost >= 0 )
			{
				System.err.print("Performed\t");
				ps.print("Performed\t");
				FitnessFunction.getInstance().calculate(aom, refactoring_cost);

				printCouplingRelatedMetrics(aom, ARToolMain.getInstance().getPrintStream1());

				//change impact analysis
				System.out.print(numPerformingRefactoring + "\t" + accumulated_refactoring_cost + "\tCIA for Class\t" + cia.analysisOnClass());
				System.out.println("\tCIA for Method\t" + cia.analysisOnMethod());
				getPrintStream2().print(numPerformingRefactoring +"\t" + accumulated_refactoring_cost + "\tCIA for Class\t" + cia.analysisOnClass());
				getPrintStream2().print("\tCIA for Method\t" + cia.analysisOnMethod() + "\t");
				getPrintStream2().println(selectedRule.getName());
			}
			
			rules.clear();
			System.gc();

			//				aom.eResource().save(null);

		}while( askContinue(shell) );

		switch( getExecutionMode() )
		{
		case DYNAMIC_MODE:
			ClassStat.getDynamicStat().export("/Users/wjsong/git/ARTOOL/resultARTool/ClassStat.");
			break;
		case STATIC_MODE:
			ClassStat.getStaticStat().export("/Users/wjsong/git/ARTOOL/resultARTool/ClassStat.");
			break;
		case DYNAMIC_STATIC_MODE:
			ClassStat.getDynamicStaticStat().export("/Users/wjsong/git/ARTOOL/resultARTool/ClassStat.");
			break;
		}

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
