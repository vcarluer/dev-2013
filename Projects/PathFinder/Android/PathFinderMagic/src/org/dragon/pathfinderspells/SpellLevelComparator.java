package org.dragon.pathfinderspells;

import java.util.Comparator;

public class SpellLevelComparator implements Comparator<Spell> {
	private int classPosition;
	
	public SpellLevelComparator(int classPosition) {
		this.classPosition = classPosition;
	}
	
	@Override
	public int compare(Spell spell1, Spell spell2) {
		switch (this.classPosition) {
		case PathFinderSpells.SELECT_BARD:
			return spell1.bardLevel - spell2.bardLevel;
		case PathFinderSpells.SELECT_CLERIC:
			return spell1.priestLevel - spell2.priestLevel;
		case PathFinderSpells.SELECT_DRUID:
			return spell1.druidLevel - spell2.druidLevel;
		case PathFinderSpells.SELECT_PALADIN:
			return spell1.paladinLevel - spell2.paladinLevel;
		case PathFinderSpells.SELECT_RANGER:
			return spell1.strikerLevel - spell2.strikerLevel;
		case PathFinderSpells.SELECT_SORCERER:
			return spell1.magianLevel - spell2.magianLevel;
		case PathFinderSpells.SELECT_WIZARD:
			return spell1.magianLevel - spell2.magianLevel;
		default:
			break;
		}
		
		return 0;
	}

}
