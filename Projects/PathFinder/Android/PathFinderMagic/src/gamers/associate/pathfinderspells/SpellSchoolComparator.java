package gamers.associate.pathfinderspells;

import java.util.Comparator;

public class SpellSchoolComparator implements Comparator<Spell> {

	@Override
	public int compare(Spell spell1, Spell spell2) {
		return CompareHelper.compareFr(spell1.school, spell2.school);
	}

}
