package org.dragon.pathfinderspells;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpellsArrayAdapter extends ArrayAdapter<Spell> {			
	private HashMap<Integer, String> sectionIndexer;	
	private int sortPosition; 
	private int classPosition;
	private TextView sectionText;
    
	public SpellsArrayAdapter(Context context, List<Spell> spells, int sortPosition, int classPosition, TextView sectionText) {
		super(context, R.layout.spells_row, spells);
		
		sectionIndexer = new HashMap<Integer, String>();
		this.sortPosition = sortPosition;
		this.classPosition = classPosition;
		this.sectionText = sectionText;
		
		for(int i = 0; i < spells.size(); i++) {
			sectionIndexer.put(i, this.getSection(this.getItem(i)));
		}
		
		this.setTopPosition(0);
	}
	
	private String getSection(Spell spell) {
		String section = "";		
		switch(this.sortPosition) {
        default:
        case PathFinderSpells.SORT_LEVEL:	            	
        	section = spell.getLevelStr(this.classPosition);
        	break;
        case PathFinderSpells.SORT_SCHOOL:	            	
        	section = spell.school;
        	break;
        case PathFinderSpells.SORT_SHOOL_LEVEL:
        	section = spell.getSchoolLevel(this.classPosition);
        	break;
        case PathFinderSpells.SORT_ALPHA:	            		            
        	// get the first letter of the store
        	section =  spell.name.substring(0, 1);
            // convert to uppercase otherwise lowercase a -z will be sorted after upper A-Z
        	section = section.toUpperCase();
        	if (CompareHelper.compareFr(section, "E") == 0) {
        		section = "E";
        	}
        	
        	break;
        }
		
		return section;
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
				compareValue = this.getSection(spell);
			}
			else {
				Spell previousSpell = this.getItem(position - 1);
				String previousValue = this.getSection(previousSpell);;
				compareValue = this.getSection(spell);
				
				if (CompareHelper.compareFr(previousValue.trim(), compareValue.trim()) != 0) {
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
	
	public void setTopPosition(int position) {
		if (position < this.sectionIndexer.values().size()) {
			this.sectionText.setText(this.sectionIndexer.get(position));
		}
	}
}