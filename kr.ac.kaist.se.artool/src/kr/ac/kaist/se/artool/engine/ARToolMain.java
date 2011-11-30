package kr.ac.kaist.se.artool.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;

import kr.ac.kaist.se.aom.AbstractObjectModel;
import kr.ac.kaist.se.aom.structure.AOMClass;
import kr.ac.kaist.se.aom.structure.AOMMethod;
import kr.ac.kaist.se.artool.engine.metrics.BasicMetricSuite;
import kr.ac.kaist.se.artool.engine.metrics.N_ConsecutiveCall;
import kr.ac.kaist.se.artool.engine.metrics.N_DCICM;
import kr.ac.kaist.se.artool.engine.metrics.N_IBDPC;
import kr.ac.kaist.se.artool.engine.metrics.N_RestrictedVerticalLocality;
import kr.ac.kaist.se.artool.engine.metrics.N_RestrictedVerticalLocality_Dynamic;
import kr.ac.kaist.se.artool.engine.metrics.entityplacement.EPMoveMethodCandidate;
import kr.ac.kaist.se.artool.engine.metrics.entityplacement.EntityPlacement;
import kr.ac.kaist.se.artool.engine.rules.AbstractRule;
import kr.ac.kaist.se.artool.engine.rules.ClassStat;
import kr.ac.kaist.se.artool.engine.rules.CollapseClasRule;
import kr.ac.kaist.se.artool.engine.rules.DynamicEP_MoveMethod;
import kr.ac.kaist.se.artool.engine.rules.MoveMethod1Rule;
import kr.ac.kaist.se.artool.engine.rules.MoveMethod2Rule;
import kr.ac.kaist.se.artool.engine.rules.StaticEP_MoveMethod;
import kr.ac.kaist.se.artool.engine.ruleselector.BestSelector;
import kr.ac.kaist.se.artool.engine.ruleselector.InteractiveSelector;
import kr.ac.kaist.se.artool.engine.ruleselector.RuleSelector;

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

			ps = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/metric_result1.txt"));
			ps1 = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/basicmetricsuite.txt"));
			ps2 = new PrintStream(new FileOutputStream("/Users/wjsong/git/ARTOOL/resultARTool/changeimpactanalysis.txt"));

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
		//		String changeFile = "/Users/wjsong/git/ARTOOL/jgit/modifiedMethod.csv";
		String changeFile = "/Users/wjsong/git/ARTOOL/resultARTool/modifiedMethod.csv";
		cia.init(aom, changeFile);

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



		//		StatusLogger.getInstance().putVar("StaticEP", (float)static_entityplacement);


		float originalFitness = FitnessFunction.getInstance().calculate(aom);

		//print file[basicmetricsuite.txt] for easy comparison
		printInitialItems();
		printCouplingRelatedMetrics(aom, ARToolMain.getInstance().getPrintStream1());



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
		double entityplacement = 0;
		long mem_usage = 0;


		do
		{

			N_IBDPC n_ibdpc = N_IBDPC.createInstance(isDynamic_mode());
			N_DCICM n_dcicm = N_DCICM.createInstance(isDynamic_mode());
			N_ConsecutiveCall n_cc = N_ConsecutiveCall.createInstance(isDynamic_mode());

			n_ibdpc.measure(aom);
			n_dcicm.measure(aom);
			n_cc.measure(aom);

			N_RestrictedVerticalLocality n_rv = new N_RestrictedVerticalLocality_Dynamic(locality_from, locality_to);
			n_rv.measure(aom, n_dcicm);

			Map.Entry<HashSet<AOMClass>, int[]>[] n_IBDPC = n_ibdpc.getSortedIBDPC(cutline);
			Map.Entry<HashSet<AOMMethod>, int[]>[] n_IBDPM = n_ibdpc.getSortedIBDPM(cutline);

			Map.Entry<HashSet<AOMClass>, int[]>[] n_CCC = n_cc.getSortedCCC(cutline);
			Map.Entry<HashSet<AOMMethod>, int[]>[] n_CCM = n_cc.getSortedCCM(cutline);



			// rule setting
			Vector<AbstractRule> rules = new Vector<AbstractRule>();

			if( tsantalis )
			{
				TreeSet<EPMoveMethodCandidate> ts = new TreeSet<EPMoveMethodCandidate>(new Comparator<EPMoveMethodCandidate>() {
					@Override
					public int compare(EPMoveMethodCandidate arg0,
							EPMoveMethodCandidate arg1) {
						//							if(Math.abs(arg0.getDistance() - arg1.getDistance()) < 0.0001) return 0;    
						return Double.compare(arg0.getDistance(), arg1.getDistance());
					}
				});
				entityplacement = EntityPlacement.calculate(aom, isDynamic_mode(), ts, pickUntil);
				System.err.println("EntityPlacement:" + entityplacement);
				EPMoveMethodCandidate[] candidates = ts.toArray(new EPMoveMethodCandidate[0]);

				for(int i = 0; i < pickUntil; i++) {
					try {
						System.err.println("Distance[" + i + "]:" + candidates[i].getDistance());
						if( isDynamic_mode() )
						{
							rules.add( new DynamicEP_MoveMethod(aom, candidates, i));
						}
						else
						{
							rules.add( new StaticEP_MoveMethod(aom, candidates, i));
						}
					} catch(Exception ex) { ex.printStackTrace(); }
				}

			}
			else
			{

				for(int i = 0; i < pickUntil; i++) {
					try {
						rules.add(new CollapseClasRule(aom, n_IBDPC, i, "Rule1", isDynamic_mode()));
					} catch(Exception ex) { }
				}
				for(int i = 0; i < pickUntil; i++) {
					try {
						rules.add(new MoveMethod1Rule(aom, n_IBDPM, i, "Rule2", isDynamic_mode()));
					} catch(Exception ex) { }
				}
				for(int i = 0; i < pickUntil; i++) {
					try {
						rules.add(new MoveMethod2Rule(aom, n_IBDPM, i, "Rule3", isDynamic_mode()));
					} catch(Exception ex) { }
				}

				for( int locality = locality_from; locality <= locality_to; locality++ )
				{

					Map.Entry<HashSet<AOMClass>, int[]>[] n_RVC = n_rv.getSortedClassLevel(locality, cutline);
					Map.Entry<HashSet<AOMMethod>, int[]>[] n_RVM = n_rv.getSortedMethodLevel(locality, cutline);

					for(int i = 0; i < pickUntil; i++) {
						try {
							rules.add(new CollapseClasRule(aom, n_RVC, i, "Rule4<" + locality + ">", isDynamic_mode()));
						} catch(Exception ex) { }
					}
					for(int i = 0; i < pickUntil; i++) {
						try {
							rules.add(new MoveMethod1Rule(aom, n_RVM, i, "Rule5<" + locality + ">", isDynamic_mode()));
						} catch(Exception ex) { }
					}
					for(int i = 0; i < pickUntil; i++) {
						try {
							rules.add(new MoveMethod2Rule(aom, n_RVM, i, "Rule6<" + locality + ">", isDynamic_mode()));
						} catch(Exception ex) { }
					}
				}

				for(int i = 0; i < pickUntil; i++) {
					try {
						rules.add(new CollapseClasRule(aom, n_CCC, i, "Rule7", isDynamic_mode()));
					} catch(Exception ex) { }
				}
				for(int i = 0; i < pickUntil; i++) {
					try {
						rules.add(new MoveMethod1Rule(aom, n_CCM, i, "Rule8", isDynamic_mode()));
					} catch(Exception ex) { }
				}
				for(int i = 0; i < pickUntil; i++) {
					try {
						rules.add(new MoveMethod2Rule(aom, n_CCM, i, "Rule9", isDynamic_mode()));
					} catch(Exception ex) { }
				}

			}


			StatusLogger.getInstance().openTrialPhase();
			// trial execution for rules
			trialExecute(rules);
			StatusLogger.getInstance().printStatistics("fitness2");
			StatusLogger.getInstance().printStatistics("fitness3");

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

			//				aom.eResource().save(null);

		}while( askContinue(shell) );

		if( isDynamic_mode() )
		{
			ClassStat.getDynamicStat().export("/Users/wjsong/git/ARTOOL/resultARTool/ClassStat.");
		}
		else
		{
			ClassStat.getStaticStat().export("/Users/wjsong/git/ARTOOL/resultARTool/ClassStat.");
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
