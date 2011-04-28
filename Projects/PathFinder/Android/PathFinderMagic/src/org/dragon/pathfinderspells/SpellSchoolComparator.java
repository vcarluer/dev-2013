package org.dragon.pathfinderspells;

import java.util.Comparator;

public class SpellSchoolComparator implements Comparator<Spell> {

	@Override
	public int compare(Spell spell1, Spell spell2) {
		return spell1.school.compareTo(spell2.school);
	}

}
