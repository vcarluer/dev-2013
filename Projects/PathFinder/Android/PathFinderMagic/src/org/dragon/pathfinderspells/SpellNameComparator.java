package org.dragon.pathfinderspells;

import java.util.Comparator;

public class SpellNameComparator implements Comparator<Spell> {

	@Override
	public int compare(Spell spell1, Spell spell2) {
		return spell1.name.compareTo(spell2.name);
	}

}
