package org.dragon.pathfinderspells;

import java.util.Comparator;

public class SpellShoolLevelComparator implements Comparator<Spell> {
	private int classPosition;
	
	public SpellShoolLevelComparator(int classPosition) {
		this.classPosition = classPosition;
	}
	
	@Override
	public int compare(Spell spell1, Spell spell2) {
		return CompareHelper.compareFr(spell1.getSchoolLevel(this.classPosition), spell2.getSchoolLevel(this.classPosition));		
	}

}
