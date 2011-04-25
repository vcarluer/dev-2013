package org.dragon.pathfinderspells;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpellDetail extends Activity {
		
    private TextView mNameText;    
    private ImageView mIsBookmark;    
    private TextView mSchoolText;    
    private TextView mLevelText;
    private TextView mCastingTimeText;
    private TextView mComponentsText;
    private TextView mRangeText;
    private TextView mTargetEffectAreaText;
    private TextView mDurationText;
    private TextView mSavingText;
    private TextView mSpellResistanceText;
    private TextView mFullDescriptionText;
    
    private LinearLayout mLayoutCastingTime;
    private LinearLayout mLayoutComponents;
    private LinearLayout mLayoutRange;
    private LinearLayout mLayoutTarget;
    private LinearLayout mLayoutDuration;
    private LinearLayout mLayoutSaving;
    private LinearLayout mLayoutSpellResistance;
    
    private Spell spell;
    
    private boolean isBookmark;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.spell_detail);

        Bundle spellBundle = (savedInstanceState == null) ? null : savedInstanceState;        
        if (spellBundle == null) {
        	spellBundle = getIntent().getExtras().getBundle(Spell.KEY_SPELL_DETAIL);        	 
        }
        
        this.spell = new Spell(spellBundle, this.getResources());
        
        this.mNameText = (TextView) findViewById(R.id.detail_name);
        this.mFullDescriptionText = (TextView) findViewById(R.id.detail_full_description);        
        this.mSchoolText = (TextView) findViewById(R.id.detail_school);       
        this.mLevelText = (TextView) findViewById(R.id.detail_level);
        this.mCastingTimeText = (TextView) findViewById(R.id.detail_casting_time);    
        this.mComponentsText = (TextView) findViewById(R.id.detail_components);
        this.mRangeText = (TextView) findViewById(R.id.detail_range);
        this.mTargetEffectAreaText = (TextView) findViewById(R.id.detail_target);
        this.mDurationText = (TextView) findViewById(R.id.detail_duration);
        this.mSavingText = (TextView) findViewById(R.id.detail_saving);
        this.mSpellResistanceText = (TextView) findViewById(R.id.detail_spell_resistance);
        
        this.mIsBookmark = (ImageView) findViewById(R.id.detail_is_bookmark);
        this.mIsBookmark.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (!isBookmark){
                	isBookmark = true;
                	mIsBookmark.setImageResource(R.drawable.favoris);
                }
                else
                {
                	isBookmark = false;
                	mIsBookmark.setImageResource(R.drawable.nofavoris);
                }
            }
        });
                
        this.mLayoutCastingTime = (LinearLayout) findViewById(R.id.layout_casting_time);
        this.mLayoutComponents = (LinearLayout) findViewById(R.id.layout_components);
        this.mLayoutDuration = (LinearLayout) findViewById(R.id.layout_duration);
        this.mLayoutRange = (LinearLayout) findViewById(R.id.layout_duration);        
        this.mLayoutSaving = (LinearLayout) findViewById(R.id.layout_saving);
        this.mLayoutSpellResistance = (LinearLayout) findViewById(R.id.layout_spell_resistance);
        this.mLayoutTarget = (LinearLayout) findViewById(R.id.layout_target);
                
        this.populateFields();               
    }
	
	private void setField(TextView textView, String value, View container) {
		if (value.length() > 0) {
			container.setVisibility(View.VISIBLE);
			textView.setText(value);
		}
		else
		{
			container.setVisibility(View.GONE);
		}
	}
	
	private void populateFields() {
		if (this.spell != null){
			
			this.mNameText.setText(this.spell.name);
			
			this.mFullDescriptionText.setText(this.spell.fullDescription);
			
			this.isBookmark = this.spell.isBookmark;
			if (this.isBookmark)
			{
				this.mIsBookmark.setImageResource(R.drawable.favoris);
			}			
						
			this.mSchoolText.setText(this.spell.getFullSchool());	
			
			this.mLevelText.setText(this.spell.getLevels());
			this.setField(this.mCastingTimeText, this.spell.castingTime, this.mLayoutCastingTime);			
			this.setField(this.mComponentsText, this.spell.components, this.mLayoutComponents);
			this.setField(this.mRangeText, this.spell.range, this.mLayoutRange);
			this.setField(this.mTargetEffectAreaText, this.spell.targetEffectArea, this.mLayoutTarget);
			this.setField(this.mDurationText, this.spell.duration, this.mLayoutDuration);
			this.setField(this.mSavingText, this.spell.saving, this.mLayoutSaving);
			this.setField(this.mSpellResistanceText, this.spell.spellResistance, this.mLayoutSpellResistance);			
		}		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		this.saveState();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		this.populateFields();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {		
		super.onSaveInstanceState(outState);
		this.saveState();		
		this.spell.fillBundle(outState);
	}

	private void saveState() {				
		/*Spell spell = new Spell();
		spell.id = this.mRowId;
		spell.comment = this.mCommentText.getText().toString();
		spell.isFavorite = this.isFavorite;
		spell.isKnown = this.isKnown;
		spell.isInMemory = this.isInMemory;
		mDbHelper.updateSpell(spell);*/		
	}			
}
