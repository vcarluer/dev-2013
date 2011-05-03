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
	HashMap<String, Integer> alphaIndexer;
    String[] sections;
    
	public SpellsArrayAdapter(Context context, List<Spell> spells) {
		super(context, R.layout.spells_row, spells);
		
		alphaIndexer = new HashMap<String, Integer>();
        int size = spells.size();

        for (int x = 0; x < size; x++) {
            Spell s = spells.get(x);

	// get the first letter of the store
            String ch =  s.name.substring(0, 1);
	// convert to uppercase otherwise lowercase a -z will be sorted after upper A-Z
            ch = ch.toUpperCase();

	// HashMap will prevent duplicates
            alphaIndexer.put(ch, x);
        }

        Set<String> sectionLetters = alphaIndexer.keySet();

    // create a list from the set to sort
        ArrayList<String> sectionList = new ArrayList<String>(sectionLetters); 

        Collections.sort(sectionList);

        sections = new String[sectionList.size()];

        sectionList.toArray(sections);
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row =  inflater.inflate(R.layout.spells_row, null);
		
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
		}
		
		return row;
	}
	
	public int getPositionForSection(int section) {
        return alphaIndexer.get(sections[section]);
    }

    public int getSectionForPosition(int position) {
        return 1;
    }

    public Object[] getSections() {
         return sections;
    }
}
