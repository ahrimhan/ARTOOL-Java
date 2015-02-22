package kr.ac.kaist.se.artool.engine.ruleselector;

import kr.ac.kaist.se.artool.engine.rules.AbstractRule;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public class MyViewerComparator extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = 1 - DESCENDING;

	public MyViewerComparator() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}
	
	public static final int low1 = 2;
	public static final int high1 = low1 + RuleSelectionDialog.statusAttributes.length;
	public static final int low2 = high1;
	public static final int high2 = low2 + RuleSelectionDialog.statusAttributes.length;

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		AbstractRule p1 = (AbstractRule) e1;
		AbstractRule p2 = (AbstractRule) e2;
		int rc = 0;


		
		if( low1 <= propertyIndex && propertyIndex < high2 )
		{
			float sp1, sp2;
			if( propertyIndex < high1 )
			{
				String selectedAttr = RuleSelectionDialog.statusAttributes[propertyIndex - low1];
				sp1 = RuleSelectionDialog.getAttr(selectedAttr, p1);
				sp2 = RuleSelectionDialog.getAttr(selectedAttr, p2);
				
			}
			else
			{
				String selectedAttr = RuleSelectionDialog.statusAttributes[propertyIndex - low2];
				sp1 = RuleSelectionDialog.calculateDelta(selectedAttr, p1);
				sp2 = RuleSelectionDialog.calculateDelta(selectedAttr, p2);
			}
			
			if( sp1 == Float.NaN ) rc = -1;
			else if ( sp2 == Float.NaN ) rc = 1;
			else if ( sp1 < sp2) rc = -1;
			else rc = 1;
		}
		else
		{
			switch (propertyIndex) {
			case 0:
				rc = p1.getName().compareTo(p2.getName());
				break;
			case 1:
				rc = p1.getStatus().compareTo(p2.getStatus());
				break;
			default:
				rc = 0;
			}
		}
		
		
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}

}