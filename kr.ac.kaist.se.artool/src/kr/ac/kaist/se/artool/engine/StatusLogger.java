package kr.ac.kaist.se.artool.engine;

import java.text.Format;
import java.text.NumberFormat;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Stack;

import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

import org.eclipse.emf.common.util.BasicEMap;

public class StatusLogger {
	private static StatusLogger instance = null;
	private Stack<BasicEMap<String, Float>> log;
	private BasicEMap<AbstractRule, BasicEMap<String, Float>> trials;
		
	public void clear()
	{
		log.clear();
		trials.clear();
	}
	
	public static StatusLogger getInstance()
	{
		if( instance == null )
		{
			instance = new StatusLogger();
		}
		return instance;
	}
	
	private StatusLogger()
	{
		log = new Stack<BasicEMap<String, Float>>();
		trials = new BasicEMap<AbstractRule, BasicEMap<String, Float>>();
	}
	
	
	public void openTrialPhase()
	{
		trials.clear();
	}
	
	public void openTrialSuite(AbstractRule rule)
	{
		trials.put(rule, new BasicEMap<String, Float>());
	}
	
	
	public void selectSuite(AbstractRule rule)
	{
		if( log.size() > 1 )
		{
			log.pop();
		}
		log.push(trials.get(rule));
	}
	
	public void openOriginalPhase()
	{
		log.push(new BasicEMap<String, Float>());
	}
	
	public void putVar(String var, Float value)
	{
		getCurrentSuite().put(var, value);
	}
	
	public void printCurrentSuite()
	{
		ListIterator<Entry<String, Float>> iterator = getCurrentSuite().listIterator();
		float value = 0;
	
		while( iterator.hasNext())
		{
			value = iterator.next().getValue();
			System.err.print(value + "\t");
			//System.err.print(Float.toString(value) + "\t");
			NumberFormat nf= NumberFormat.getInstance();
			
			nf.setMaximumFractionDigits(8);
			nf.setMinimumFractionDigits(8);
			ARToolMain.getInstance().getPrintStream().print(nf.format(value) + "\t");
		}
		System.err.println();
		ARToolMain.getInstance().getPrintStream().println();
	}
	
	public BasicEMap<String, Float> getPreviousPhase()
	{
		return log.peek();
	}
	
	public BasicEMap<String, Float> getTrialPhase(AbstractRule rule)
	{
		return trials.get(rule);
	}
	
	public BasicEMap<String, Float> getOriginalPhase()
	{
		return log.firstElement();
	}
	
	public BasicEMap<String, Float> getCurrentSuite()
	{
		if( trials.size() == 0 )
		{
//			System.err.println("initialize!!!");
			return log.peek();
		}
		
		BasicEMap<String, Float> ret = trials.get(trials.size()-1).getValue();
		
		return ret;
	}
	
	private void _printDelta(BasicEMap<String, Float> previous)
	{
		ListIterator<Entry<String, Float>> iterator = getCurrentSuite().listIterator();
		
		while( iterator.hasNext())
		{
			Entry<String, Float> curEntry = iterator.next();
			float diff = curEntry.getValue() - previous.get(curEntry.getKey());
			NumberFormat nf= NumberFormat.getInstance();
			
			nf.setMaximumFractionDigits(8);
			nf.setMinimumFractionDigits(8);
			
			System.err.print(nf.format(diff) + "\t");
		}
		System.err.println();
	}
	
	public void printDeltaWithPrevious()
	{
		BasicEMap<String, Float> previous = getPreviousPhase();
		_printDelta(previous);
		
	}
	
	public void printDeltaWithOriginal()
	{
		BasicEMap<String, Float> original = log.firstElement();
		_printDelta(original);
	}
}
