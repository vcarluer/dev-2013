package org.dragon.pathfinderspells;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SpellDetail extends Activity {
	
	private Long mRowId;    
    private SpellsDbAdapter mDbHelper;
    
    private TextView mNameText;
    private TextView mFullDescriptionText;
    private ImageView mIsFavorite;
    private ImageView mIsKnown;
    private ImageView mIsInMemory;
    private TextView mSchoolText;
    private TextView mLevelText;
    private TextView mIncantationTimeText;
    private TextView mComponentsText;
    private TextView mRangeText;
    private TextView mTargetText;
    private TextView mDurationText;
    private TextView mSavingText;
    private TextView mMagicResistanceText;
    private EditText mCommentText;
    
    private int isFavorite;
    private int isKnown;
    private int isInMemory;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDbHelper = new SpellsDbAdapter(this);
        this.mDbHelper.open();
        
        setContentView(R.layout.spell_detail);

        this.mRowId = (savedInstanceState == null) ? null : (Long) savedInstanceState.getSerializable(SpellsDbAdapter.KEY_SPELL_ID);
        if (this.mRowId == null) {
        	Bundle extras = getIntent().getExtras();
        	this.mRowId = (extras != null) ? extras.getLong(SpellsDbAdapter.KEY_SPELL_ID) : null; 
        }
        
        this.mNameText = (TextView) findViewById(R.id.detail_name);
        this.mFullDescriptionText = (TextView) findViewById(R.id.detail_full_description);        
        this.mSchoolText = (TextView) findViewById(R.id.detail_school);
        this.mLevelText = (TextView) findViewById(R.id.detail_level);
        this.mIncantationTimeText = (TextView) findViewById(R.id.detail_incantation_time);   
        this.mCommentText = (EditText) findViewById(R.id.detail_comment);        
        this.mComponentsText = (TextView) findViewById(R.id.detail_components);
        this.mRangeText = (TextView) findViewById(R.id.detail_range);
        this.mTargetText = (TextView) findViewById(R.id.detail_target);
        this.mDurationText = (TextView) findViewById(R.id.detail_duration);
        this.mSavingText = (TextView) findViewById(R.id.detail_saving);
        this.mMagicResistanceText = (TextView) findViewById(R.id.detail_magic_resistance);
        
        this.mIsFavorite = (ImageView) findViewById(R.id.detail_is_favorite);
        this.mIsFavorite.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (isFavorite == 0){
                	isFavorite = 1;
                	mIsFavorite.setImageResource(R.drawable.favoris);
                }
                else
                {
                	isFavorite = 0;
                	mIsFavorite.setImageResource(R.drawable.nofavoris);
                }
            }
        });
        
        this.mIsKnown = (ImageView) findViewById(R.id.detail_is_known);
        this.mIsKnown.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (isKnown == 0){
                	isKnown = 1;
                	mIsKnown.setImageResource(R.drawable.grimoire);
                }
                else
                {
                	isKnown = 0;
                	mIsKnown.setImageResource(R.drawable.nogrimoire);
                }
            }
        });
        
        this.mIsInMemory = (ImageView) findViewById(R.id.detail_is_inmemory);
        this.mIsInMemory.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (isInMemory == 0){
                	isInMemory = 1;
                	mIsInMemory.setImageResource(R.drawable.inmemory);
                }
                else
                {
                	isInMemory = 0;
                	mIsInMemory.setImageResource(R.drawable.noinmemory);
                }
            }
        });
        
        this.populateFields();               
    }
	
	private void populateFields() {
		if (this.mRowId != null){
			Cursor spell = this.mDbHelper.fetchSpell(this.mRowId);
			this.startManagingCursor(spell);
			
			this.mNameText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_NAME)));
			
			this.mFullDescriptionText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_FULLDESCRIPTION)));
			
			this.isFavorite = spell.getInt(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_ISFAVORITE));
			if (this.isFavorite == 1)
			{
				this.mIsFavorite.setImageResource(R.drawable.favoris);
			}			
			
			this.isKnown = spell.getInt(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_ISKNOWN));
			if (this.isKnown == 1)
			{
				this.mIsKnown.setImageResource(R.drawable.grimoire);
			}
			
			this.isInMemory = spell.getInt(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_ISINMEMORY));
			if (this.isInMemory == 1)
			{
				this.mIsInMemory.setImageResource(R.drawable.inmemory);
			}
			
			this.mSchoolText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_SCHOOL)));
			this.mLevelText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_LEVELS)));
			this.mIncantationTimeText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_CASTINGTIME)));
			
			this.mComponentsText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_COMPONENTS)));
			this.mRangeText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_RANGE)));
			this.mTargetText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_TARGETEFFECTAREA)));
			this.mDurationText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_DURATION)));
			this.mSavingText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_SAVING)));
			this.mMagicResistanceText.setText(spell.getString(
					spell.getColumnIndexOrThrow(SpellsDbAdapter.KEY_SPELL_SPELLRESISTANCE)));			
			
			this.mCommentText.setText(
					spell.getString(spell.getColumnIndex(SpellsDbAdapter.KEY_SPELL_COMMENT)));		
		}		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.saveState();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.populateFields();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		// this.saveState();
		outState.putSerializable(SpellsDbAdapter.KEY_SPELL_ID, this.mRowId);
	}

	private void saveState() {				
		Spell spell = new Spell();
		spell.id = this.mRowId;
		spell.comment = this.mCommentText.getText().toString();
		spell.isFavorite = this.isFavorite;
		spell.isKnown = this.isKnown;
		spell.isInMemory = this.isInMemory;
		mDbHelper.updateSpell(spell);		
	}			
}
