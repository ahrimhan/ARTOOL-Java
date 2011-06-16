package kr.ac.kaist.se.artool.engine.ruleselector;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

public class BestSelector implements RuleSelector {
	@Override
	public AbstractRule select(List<AbstractRule> rules) {
		AbstractRule ret = null;
		ret = Collections.max(rules, new Comparator<AbstractRule>(){
			@Override
			public int compare(AbstractRule arg0, AbstractRule arg1) {
				return (int)(arg0.getFitness() - arg1.getFitness());
			}
			
		});
		return ret;
	}
}
