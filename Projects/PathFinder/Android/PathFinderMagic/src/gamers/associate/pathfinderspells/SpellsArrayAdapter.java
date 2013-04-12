package gamers.associate.pathfinderspells;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
				// isFavoriteView.setVisibility(View.VISIBLE);
				isFavoriteView.setImageResource(R.drawable.favoris);
			}
			else
			{
				// isFavoriteView.setVisibility(View.INVISIBLE);
				isFavoriteView.setImageResource(R.drawable.nofavoris);
			}
			
			isFavoriteView.setTag(position);
			isFavoriteView.setOnClickListener(new View.OnClickListener() {

	            public void onClick(View view) {
	            	Integer positionClick = (Integer) view.getTag();
	            	Spell spellClick = getItem(positionClick);
	            	ImageView isFavoriteViewClick = (ImageView) view;
	            	
	                if (!spellClick.isBookmark){
	                	spellClick.isBookmark = true;
	                	PathFinderSpells.get().getBookmarks().bookmark(spellClick.name);
	                	isFavoriteViewClick.setImageResource(R.drawable.favoris);                	
	                }
	                else
	                {
	                	spellClick.isBookmark = false;
	                	PathFinderSpells.get().getBookmarks().unbookmark(spellClick.name);
	                	isFavoriteViewClick.setImageResource(R.drawable.nofavoris);
	                }
	            }
	        });
			
			LinearLayout spellLayout = (LinearLayout) row.findViewById(R.id.spell_layout);
			spellLayout.setTag(position);
			spellLayout.setOnClickListener(new View.OnClickListener() {
				
	            public void onClick(View view) {
	            	// Toast.makeText(getContext(), "Spell layout click", Toast.LENGTH_SHORT).show();
	            	Intent i = new Intent(getContext(), SpellDetail.class);
	                	            		            
	            	Integer positionClick = (Integer) view.getTag();	            		            	
	                Spell spellClick = getItem(positionClick);
	                view.setBackgroundColor(getContext().getResources().getColor(R.color.color_pf_yellow));
	                
	                i.putExtra(Spell.KEY_SPELL_DETAIL, spellClick.getBundle());
	                PathFinderSpells.get().setLastPosition(positionClick);
	                PathFinderSpells.get().startActivityForResult(i, PathFinderSpells.ACTIVITY_SHOWDETAIL);
	            }
	        });
			
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
				// Disable header select
				rowHeader.setOnClickListener(new View.OnClickListener() {					
		            public void onClick(View view) {		            
		            }
				});
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