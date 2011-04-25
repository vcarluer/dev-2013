package org.dragon.pathfinderspells;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SpellsDbAdapter {

	public static final String TAG = "SpellsDbAdapter";
	
	public static final String KEY_SPELL_ID = "_id";
	public static final String KEY_SPELL_NAME = "name";
    public static final String KEY_SPELL_SHORTDESCRIPTION = "shortdescription";    
    public static final String KEY_SPELL_SCHOOL = "school";
    public static final String KEY_SPELL_REGISTER = "register";
    public static final String KEY_SPELL_BRANCH = "branch";
    public static final String KEY_SPELL_CASTINGTIME = "castingtime";
    public static final String KEY_SPELL_COMPONENTS = "components";
    public static final String KEY_SPELL_RANGE = "range";
    public static final String KEY_SPELL_TARGETEFFECTAREA = "targeteffectarea";
    public static final String KEY_SPELL_DURATION = "duration";
    public static final String KEY_SPELL_SAVING = "saving";
    public static final String KEY_SPELL_SPELLRESISTANCE = "spellresistance";
    public static final String KEY_SPELL_FULLDESCRIPTION = "fulldescription";
    public static final String KEY_SPELL_COMMENT = "comment";
    public static final String KEY_SPELL_ISKNOWN = "isknown";
    public static final String KEY_SPELL_ISINMEMORY = "isinmemory";
    public static final String KEY_SPELL_ISFAVORITE = "isfavorite";
    public static final String KEY_SPELL_MAGICIANLEVEL = "macicianlevel";
    public static final String KEY_SPELL_PRIESTLEVEL = "priestlevel";
    public static final String KEY_SPELL_DRUIDLEVEL = "druidlevel";
    public static final String KEY_SPELL_PALADINLEVEL = "paladinlevel";
    public static final String KEY_SPELL_STRIKERLEVEL = "strikerlevel";
    public static final String KEY_SPELL_BARDLEVEL = "bardlevel";
    public static final String KEY_SPELL_LEVELS = "levels";
    public static final String KEY_INIT_ISINIT = "isinit";
    
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_SPELL_TABLE = "spell";    
    private static final String DATABASE_INIT_TABLE = "init";
    private static final int DATABASE_VERSION = 17;
        
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_SPELL_CREATE =
        "create table spell(" +
        "_id integer primary key autoincrement, " +
        "name text not null, " +
		"shortdescription text null," +
		"school text," +
		"register text," +
		"branch text," +
		"castingtime text," +
		"components text," +
		"range text," +
		"targeteffectarea text," +
		"duration text," +
		"saving text," +
		"spellresistance text," +
		"comment text," +
		"fulldescription text," +
		"isknown integer," +
		"isinmemory integer," +
		"isfavorite integer," +
		"macicianlevel integer," +
		"priestlevel integer," +
		"druidlevel integer," +
		"paladinlevel integer," +
		"strikerlevel integer," +
		"bardlevel integer," +
		"levels text" +
		");";
    
    private static final String DATABASE_INIT_CREATE =
       "create table init(" +
        "isinit integer);";
		    

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_SPELL_CREATE);
            db.execSQL(DATABASE_INIT_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_SPELL_TABLE);            
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_INIT_TABLE);
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public SpellsDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the spell database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public SpellsDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createSpell(Spell spell) {
        ContentValues initialValues = new ContentValues();
                
        initialValues.put(KEY_SPELL_NAME, spell.name);
        initialValues.put(KEY_SPELL_SHORTDESCRIPTION, spell.shortDescription);    
        initialValues.put(KEY_SPELL_SCHOOL, spell.school);
        initialValues.put(KEY_SPELL_REGISTER, spell.register);
        initialValues.put(KEY_SPELL_BRANCH, spell.branch);
        initialValues.put(KEY_SPELL_CASTINGTIME, spell.castingTime);
        initialValues.put(KEY_SPELL_COMPONENTS, spell.components);
        initialValues.put(KEY_SPELL_RANGE, spell.range);
        initialValues.put(KEY_SPELL_TARGETEFFECTAREA, spell.targetEffectArea);
        initialValues.put(KEY_SPELL_DURATION, spell.duration);
        initialValues.put(KEY_SPELL_SAVING, spell.saving);
        initialValues.put(KEY_SPELL_SPELLRESISTANCE, spell.spellResistance);
        initialValues.put(KEY_SPELL_FULLDESCRIPTION, spell.fullDescription);
        initialValues.put(KEY_SPELL_COMMENT, spell.comment);
        initialValues.put(KEY_SPELL_ISKNOWN, spell.isKnown);
        initialValues.put(KEY_SPELL_ISINMEMORY, spell.isInMemory);
        initialValues.put(KEY_SPELL_ISFAVORITE, spell.isFavorite);
        initialValues.put(KEY_SPELL_MAGICIANLEVEL, spell.magianLevel);
        initialValues.put(KEY_SPELL_PRIESTLEVEL, spell.priestLevel);
        initialValues.put(KEY_SPELL_DRUIDLEVEL, spell.druidLevel);
        initialValues.put(KEY_SPELL_PALADINLEVEL, spell.paladinLevel);
        initialValues.put(KEY_SPELL_STRIKERLEVEL, spell.strikerLevel);
        initialValues.put(KEY_SPELL_BARDLEVEL, spell.bardLevel);
        initialValues.put(KEY_SPELL_LEVELS, spell.getLevels(this.mCtx.getResources()));

        return mDb.insert(DATABASE_SPELL_TABLE, null, initialValues);
    }

    public boolean deleteSpell(long rowId) {

        return mDb.delete(DATABASE_SPELL_TABLE, KEY_SPELL_ID + "=" + rowId, null) > 0;
    }
    
    public boolean deleteAllSpells() {

        return mDb.delete(DATABASE_SPELL_TABLE, null, null) > 0;       
    }

    public Cursor fetchAllSpells() {

        Cursor returnCursor = mDb.query(DATABASE_SPELL_TABLE, new String[] {
        		KEY_SPELL_ID, 
        		KEY_SPELL_NAME,
        		KEY_SPELL_CASTINGTIME,
        		KEY_SPELL_ISKNOWN,
        		KEY_SPELL_ISINMEMORY,
                KEY_SPELL_SHORTDESCRIPTION}, 
                null, null, null, null, null);              
        
        return returnCursor;
    }
    
    public Cursor fetchAllSpells(String nameFilter, int isFavoriteFilter, int isKnownFilter, int isInMemoryFilter) throws SQLException {
    	    	
		String where = "";
		
		if (nameFilter != ""){
			where += KEY_SPELL_NAME + " LIKE '%" + nameFilter + "%'";
		}
		
    	if (isFavoriteFilter != -1){
    		if (where != ""){
    			where += " and ";
    		}
    		
    		where += KEY_SPELL_ISFAVORITE + "=" + isFavoriteFilter;
    	}
    	
    	if (isKnownFilter != -1){
    		if (where != ""){
    			where += " and ";
    		}
    		
    		where += KEY_SPELL_ISKNOWN + "=" + isKnownFilter;
    	}
    	
    	if (isInMemoryFilter != -1){
    		if (where != ""){
    			where += " and ";
    		}
    		
    		where += KEY_SPELL_ISINMEMORY + "=" + isInMemoryFilter;
    	}
    	
    	if (where == ""){
    			where = null;
    	}
    	
    	Cursor returnCursor = mDb.query(DATABASE_SPELL_TABLE, new String[] {
        		KEY_SPELL_ID, 
        		KEY_SPELL_NAME,
        		KEY_SPELL_CASTINGTIME,
        		KEY_SPELL_ISKNOWN,
        		KEY_SPELL_ISINMEMORY,
                KEY_SPELL_SHORTDESCRIPTION}, 
                where, null, null, null, null);              
        
        return returnCursor;

    }

    /**
     * Return a Cursor positioned at the spell that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchSpell(long rowId) throws SQLException {

        Cursor mCursor =

            mDb.query(true, DATABASE_SPELL_TABLE, new String[] {
            		KEY_SPELL_ID, 
            		KEY_SPELL_NAME,                    
                    KEY_SPELL_FULLDESCRIPTION,
                    KEY_SPELL_SCHOOL,
                    KEY_SPELL_REGISTER,
                    KEY_SPELL_BRANCH,
                    KEY_SPELL_LEVELS,
                    KEY_SPELL_CASTINGTIME,
                    KEY_SPELL_COMPONENTS,
                    KEY_SPELL_RANGE,
                    KEY_SPELL_TARGETEFFECTAREA,
                    KEY_SPELL_DURATION,
                    KEY_SPELL_SAVING,
                    KEY_SPELL_SPELLRESISTANCE,
                    KEY_SPELL_COMMENT,
                    KEY_SPELL_ISKNOWN,
                    KEY_SPELL_ISINMEMORY,
                    KEY_SPELL_ISFAVORITE},
                    KEY_SPELL_ID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }       
    
    public boolean updateSpell(Spell spell) {
        ContentValues args = new ContentValues();
        /*args.put(KEY_SPELL_NAME, spell.name);
        args.put(KEY_SPELL_SHORTDESCRIPTION, spell.shortDescription);    
        args.put(KEY_SPELL_SCHOOL, spell.school);
        args.put(KEY_SPELL_INCANTATIONTIME, spell.incantationTime);
        args.put(KEY_SPELL_COMPONENTS, spell.components);
        args.put(KEY_SPELL_RANGE, spell.range);
        args.put(KEY_SPELL_TARGET, spell.target);
        args.put(KEY_SPELL_DURATION, spell.duration);
        args.put(KEY_SPELL_SAVING, spell.saving);
        args.put(KEY_SPELL_MAGICRESISTANCE, spell.magicResistance);
        args.put(KEY_SPELL_FULLDESCRIPTION, spell.fullDescription);*/
        args.put(KEY_SPELL_COMMENT, spell.comment);
        args.put(KEY_SPELL_ISKNOWN, spell.isKnown);
        args.put(KEY_SPELL_ISINMEMORY, spell.isInMemory);
        args.put(KEY_SPELL_ISFAVORITE, spell.isFavorite);
        /*args.put(KEY_SPELL_MAGICIANLEVEL, spell.magianLevel);
        args.put(KEY_SPELL_PRIESTLEVEL, spell.priestLevel);
        args.put(KEY_SPELL_DRUIDLEVEL, spell.druidLevel);
        args.put(KEY_SPELL_PALADINLEVEL, spell.paladinLevel);
        args.put(KEY_SPELL_STRIKERLEVEL, spell.strikerLevel);
        args.put(KEY_SPELL_BARDLEVEL, spell.bardLevel);*/

        return mDb.update(DATABASE_SPELL_TABLE, args, KEY_SPELL_ID + "=" + spell.id, null) > 0;
    }
    
    public Cursor fetchAllInit() {

        Cursor returnCursor = mDb.query(DATABASE_INIT_TABLE, new String[] {
        		KEY_INIT_ISINIT}, 
                null, null, null, null, null);              
        
        return returnCursor;
    }
    
    public long createInit() {
        ContentValues initialValues = new ContentValues();
                
        initialValues.put(KEY_INIT_ISINIT, 1);
        
        return mDb.insert(DATABASE_INIT_TABLE, null, initialValues);
    }
}
