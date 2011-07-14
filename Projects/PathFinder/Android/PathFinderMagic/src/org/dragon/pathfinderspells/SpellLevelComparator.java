package org.dragon.pathfinderspells;

import java.util.Comparator;

public class SpellLevelComparator implements Comparator<Spell> {
	private int classPosition;
	
	public SpellLevelComparator(int classPosition) {
		this.classPosition = classPosition;
	}
	
	@Override
	public int compare(Spell spell1, Spell spell2) {
		return spell1.getLevel(this.classPosition) - spell2.getLevel(this.classPosition);
	}

}
