package kr.ac.kaist.se.artool.engine.ruleselector;

import java.util.List;

import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class InteractiveSelector implements RuleSelector {
	private List<AbstractRule> rules;
	private AbstractRule selectedRule;
	@Override
	public AbstractRule select(List<AbstractRule> irules) {
		rules = irules;
		Display display = PlatformUI.getWorkbench().getDisplay();
		display.syncExec(
				  new Runnable() {
				    public void run(){
						Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
						RuleSelectionDialog dialog = new RuleSelectionDialog(shell, rules);
						dialog.open();
						selectedRule = dialog.getSelectedRule();
				    }
				  });
		

		
		return selectedRule;
	}

}
