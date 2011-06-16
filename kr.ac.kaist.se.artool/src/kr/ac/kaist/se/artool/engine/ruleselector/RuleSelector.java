package kr.ac.kaist.se.artool.engine.ruleselector;

import java.util.List;

import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

public interface RuleSelector {
	AbstractRule select(List<AbstractRule> rules);
}
