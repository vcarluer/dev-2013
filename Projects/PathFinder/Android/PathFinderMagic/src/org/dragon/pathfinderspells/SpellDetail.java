package org.dragon.pathfinderspells;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpellDetail extends Activity {
		
    private TextView mNameText;    
    private ImageView mIsBookmark;    
    private TextView mSchoolText;
    private TextView mSchoolCaptionText;
    private TextView mLevelText;
    private TextView mLevelCaptionText;
    private TextView mCastingTimeText;
    private TextView mCastingTimeCaptionText;
    private TextView mComponentsText;
    private TextView mComponentsCaptionText;
    private TextView mRangeText;
    private TextView mRangeCaptionText;
    private TextView mTargetEffectAreaText;
    private TextView mTargetEffectAreaCaptionText;
    private TextView mDurationText;
    private TextView mDurationCaptionText;
    private TextView mSavingText;
    private TextView mSavingCaptionText;
    private TextView mSpellResistanceText;
    private TextView mSpellResistanceCaptionText;
    private TextView mFullDescriptionText;
    
    private LinearLayout mLayoutCastingTime;
    private LinearLayout mLayoutComponents;
    private LinearLayout mLayoutRange;
    private LinearLayout mLayoutTarget;
    private LinearLayout mLayoutDuration;
    private LinearLayout mLayoutSaving;
    private LinearLayout mLayoutSpellResistance;
    
    private Spell spell;
        
    private boolean wasBookmark;
    private BookmarkList bookmarkList;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.spell_detail);

        Bundle spellBundle = (savedInstanceState == null) ? null : savedInstanceState;        
        if (spellBundle == null) {
        	spellBundle = getIntent().getExtras().getBundle(Spell.KEY_SPELL_DETAIL);
        }     	
        
        this.spell = new Spell(spellBundle, this.getResources());
        this.wasBookmark = this.spell.isBookmark;
        this.bookmarkList = new BookmarkList(this);                
        
        this.mNameText = (TextView) findViewById(R.id.detail_name);
        this.mNameText.setTypeface(TypefaceFactory.getPathfinder(this));
        
        this.mFullDescriptionText = (TextView) findViewById(R.id.detail_full_description);
        this.mFullDescriptionText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mSchoolText = (TextView) findViewById(R.id.detail_school);       
        this.mSchoolCaptionText = (TextView) findViewById(R.id.detail_school_caption);
        this.mSchoolCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mSchoolText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mLevelText = (TextView) findViewById(R.id.detail_level);
        this.mLevelCaptionText = (TextView) findViewById(R.id.detail_level_caption);
        this.mLevelCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mLevelText.setTypeface(TypefaceFactory.getRegular(this));    
        
        this.mCastingTimeText = (TextView) findViewById(R.id.detail_casting_time);    
        this.mCastingTimeCaptionText = (TextView) findViewById(R.id.detail_casting_time_caption);
        this.mCastingTimeCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mCastingTimeText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mComponentsText = (TextView) findViewById(R.id.detail_components);
        this.mComponentsCaptionText = (TextView) findViewById(R.id.detail_components_caption);
        this.mComponentsCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mComponentsText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mRangeText = (TextView) findViewById(R.id.detail_range);
        this.mRangeCaptionText = (TextView) findViewById(R.id.detail_range_caption);
        this.mRangeCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mRangeText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mTargetEffectAreaText = (TextView) findViewById(R.id.detail_target);
        this.mTargetEffectAreaCaptionText = (TextView) findViewById(R.id.detail_target_caption);
        this.mTargetEffectAreaCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mTargetEffectAreaText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mDurationText = (TextView) findViewById(R.id.detail_duration);
        this.mDurationCaptionText = (TextView) findViewById(R.id.detail_duration_caption);
        this.mDurationCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mDurationText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mSavingText = (TextView) findViewById(R.id.detail_saving);
        this.mSavingCaptionText = (TextView) findViewById(R.id.detail_saving_caption);
        this.mSavingCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mSavingText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mSpellResistanceText = (TextView) findViewById(R.id.detail_spell_resistance);
        this.mSpellResistanceCaptionText = (TextView) findViewById(R.id.detail_spell_resistance_caption);
        this.mSpellResistanceCaptionText.setTypeface(TypefaceFactory.getBold(this));
        this.mSpellResistanceText.setTypeface(TypefaceFactory.getRegular(this));
        
        this.mIsBookmark = (ImageView) findViewById(R.id.detail_is_bookmark);
        this.mIsBookmark.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (!spell.isBookmark){
                	spell.isBookmark = true;
                	bookmarkList.bookmark(spell.name);
                	mIsBookmark.setImageResource(R.drawable.favoris);                	
                }
                else
                {
                	spell.isBookmark = false;
                	bookmarkList.unbookmark(spell.name);
                	mIsBookmark.setImageResource(R.drawable.nofavoris);
                }
            }
        });
                
        this.mLayoutCastingTime = (LinearLayout) findViewById(R.id.layout_casting_time);
        this.mLayoutComponents = (LinearLayout) findViewById(R.id.layout_components);
        this.mLayoutDuration = (LinearLayout) findViewById(R.id.layout_duration);
        this.mLayoutRange = (LinearLayout) findViewById(R.id.layout_range);        
        this.mLayoutSaving = (LinearLayout) findViewById(R.id.layout_saving);
        this.mLayoutSpellResistance = (LinearLayout) findViewById(R.id.layout_spell_resistance);
        this.mLayoutTarget = (LinearLayout) findViewById(R.id.layout_target);

        this.populateFields();               
    }
	
	private void setField(TextView textView, String value, View container) {
		if (value.trim().length() > 0) {
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
			
			if (this.spell.isBookmark)
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
		if (this.wasBookmark != this.spell.isBookmark) {
			this.bookmarkList.saveBookmark(this);
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}
