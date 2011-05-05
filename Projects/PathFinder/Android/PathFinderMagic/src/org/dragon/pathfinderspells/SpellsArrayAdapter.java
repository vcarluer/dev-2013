package org.dragon.pathfinderspells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SpellsArrayAdapter extends ArrayAdapter<Spell> implements SectionIndexer {			
	private HashMap<String, Integer> sortIndexer;
	private String[] sections;
	private int sortPosition; 
	private int classPosition;	
    
	public SpellsArrayAdapter(Context context, List<Spell> spells, int sortPosition, int classPosition) {
		super(context, R.layout.spells_row, spells);
		
		sortIndexer = new HashMap<String, Integer>();
		this.sortPosition = sortPosition;
		this.classPosition = classPosition;		
        int size = spells.size();

        for (int x = 0; x < size; x++) {
            Spell spell = spells.get(x);
            String ch = "";
            switch(this.sortPosition) {
            default:
            case PathFinderSpells.SORT_LEVEL:
            	ch = this.getLevelSection(spell);
            	break;
            case PathFinderSpells.SORT_SCHOOL:
            	ch = spell.school;
            	break;
            case PathFinderSpells.SORT_ALPHA:
            	// get the first letter of the store
                ch =  spell.name.substring(0, 1);
                // convert to uppercase otherwise lowercase a -z will be sorted after upper A-Z
                ch = ch.toUpperCase();                
            	break;
            }                        

            // HashMap will prevent duplicates
            sortIndexer.put(ch, x);
        }

        Set<String> sectionSet = sortIndexer.keySet();

        // create a list from the set to sort
        ArrayList<String> sectionList = new ArrayList<String>(sectionSet); 

        Collections.sort(sectionList);

        sections = new String[sectionList.size()];

        sectionList.toArray(sections);
	}
	
	private String getLevelSection(Spell spell) {
		String level = "";
		switch(this.classPosition) {
		case PathFinderSpells.SELECT_BARD: 
			level = String.valueOf(spell.bardLevel);
			break;
		case PathFinderSpells.SELECT_CLERIC:
			level = String.valueOf(spell.priestLevel);
			break;
		case PathFinderSpells.SELECT_DRUID:
			level = String.valueOf(spell.druidLevel);
			break;
		case PathFinderSpells.SELECT_PALADIN:
			level = String.valueOf(spell.paladinLevel);
			break;
		case PathFinderSpells.SELECT_RANGER:
			level = String.valueOf(spell.strikerLevel);
			break;
		case PathFinderSpells.SELECT_SORCERER_WIZARD:
			level = String.valueOf(spell.magianLevel);
			break;    		
		case PathFinderSpells.SELECT_TOUTES:
		default:
			break;
		}
		
		return level;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.spells_row, null);
		
		Spell spell = this.getItem(position);
		if (spell != null)
		{
			TextView spellName = (TextView)row.findViewById(R.id.row_name);
			spellName.setTypeface(TypefaceFactory.getRegular(this.getContext()));
			TextView spellShortDesc = (TextView)row.findViewById(R.id.row_short_description);
			spellShortDesc.setTypeface(TypefaceFactory.getItalic(this.getContext()));
			ImageView isFavoriteView = (ImageView) row.findViewById(R.id.row_is_favorite);
			spellName.setText(spell.name);
			spellShortDesc.setText(spell.shortDescription);
			if (spell.isBookmark) {
				isFavoriteView.setVisibility(View.VISIBLE);
			}
			else
			{
				isFavoriteView.setVisibility(View.INVISIBLE);
			}
			
			TextView rowHeader = (TextView) row.findViewById(R.id.row_header);
			boolean showHeader = false;
			String compareValue = "";
			if (position == 0) {
				showHeader = true;
				switch(this.sortPosition) {
	            default:
	            case PathFinderSpells.SORT_LEVEL:	            	
	            	compareValue = this.getLevelSection(spell);
	            	break;
	            case PathFinderSpells.SORT_SCHOOL:	            	
	            	compareValue = spell.school;
	            	break;
	            case PathFinderSpells.SORT_ALPHA:	            		            
	            	// get the first letter of the store
	            	compareValue =  spell.name.substring(0, 1);
	                // convert to uppercase otherwise lowercase a -z will be sorted after upper A-Z
	            	compareValue = compareValue.toUpperCase();                
	            	break;
	            }
			}
			else {
				Spell previousSpell = this.getItem(position - 1);
				String previousValue = "";							
				switch(this.sortPosition) {
	            default:
	            case PathFinderSpells.SORT_LEVEL:
	            	previousValue = this.getLevelSection(previousSpell);
	            	compareValue = this.getLevelSection(spell);
	            	break;
	            case PathFinderSpells.SORT_SCHOOL:
	            	previousValue = previousSpell.school;
	            	compareValue = spell.school;
	            	break;
	            case PathFinderSpells.SORT_ALPHA:
	            	previousValue = previousSpell.name.substring(0, 1).toUpperCase();	            	
	            	// get the first letter of the store
	            	compareValue =  spell.name.substring(0, 1);
	                // convert to uppercase otherwise lowercase a -z will be sorted after upper A-Z
	            	compareValue = compareValue.toUpperCase();                
	            	break;
	            }
				
				if (previousValue.trim().toUpperCase().compareTo(compareValue.trim().toUpperCase()) != 0) {
					showHeader = true;
				}
			}
			
			
			if (showHeader) {
				rowHeader.setText(compareValue.trim());
				rowHeader.setTypeface(TypefaceFactory.getPathfinder(getContext()));
			}
			else {
				rowHeader.setVisibility(View.GONE);
			}
		}
		
		return row;
	}
	
	@Override
	public int getPositionForSection(int section) {
        if (section >= sections.length) section = sections.length - 1;
		return sortIndexer.get(sections[section]);
    }
	
	@Override
    public int getSectionForPosition(int position) {
        return 1;
    }
	
	@Override
    public Object[] getSections() {
         return sections;
    }
}
