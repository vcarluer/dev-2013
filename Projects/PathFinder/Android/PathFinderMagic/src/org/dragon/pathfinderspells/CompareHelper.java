package org.dragon.pathfinderspells;

import java.text.Collator;
import java.util.Locale;

public class CompareHelper {
	public static int compareFr(String strBase, String strTo) {
		// Instantiation d'un collator français
	    Collator compareOperator = Collator.getInstance (Locale.FRENCH);    
	    // Comparaison sans tenir compte des accents
	    compareOperator.setStrength (Collator.PRIMARY);	    
	    return compareOperator.compare(strBase, strTo);
	}
}
