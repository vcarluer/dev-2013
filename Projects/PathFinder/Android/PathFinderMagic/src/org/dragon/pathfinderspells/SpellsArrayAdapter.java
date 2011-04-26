package org.dragon.pathfinderspells;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpellsArrayAdapter extends ArrayAdapter<Spell> {			
	
	public SpellsArrayAdapter(Context context, List<Spell> spells) {
		super(context, R.layout.spells_row, spells);
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
			TextView spellShortDesc = (TextView)row.findViewById(R.id.row_short_description);
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
}
