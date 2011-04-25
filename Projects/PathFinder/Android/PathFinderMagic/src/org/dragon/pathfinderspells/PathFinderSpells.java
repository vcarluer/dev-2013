package org.dragon.pathfinderspells;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class PathFinderSpells extends ListActivity {
	
	private static final int ACTIVITY_SHOWDETAIL=0;
	
	private SpellsDbAdapter mDbHelper;
	private Boolean forceInit = false;
	
	private ImageView mIsFavorite;
    private ImageView mIsKnown;
    private ImageView mIsInMemory;
    private ImageView mSearch;
    private TextView mSearchText;
    
    private int isFavorite = -1;
    private int isKnown = -1;
    private int isInMemory = -1;
    private String nameFilter = "";
    private Boolean isSearched = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spells_list);
        
        this.mDbHelper = new SpellsDbAdapter(this);
        this.mDbHelper.open();
        Cursor init = this.mDbHelper.fetchAllInit();
        if (init.getCount() == 0 || this.forceInit)
        {        	
        	this.initData();
        }                             
        
        this.mIsFavorite = (ImageView) findViewById(R.id.filter_is_favorite);
        
        this.mIsFavorite.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	if (isSearched){
                	nameFilter = "";
                	mSearch.setImageResource(R.drawable.ic_menu_search);
                	mSearchText.setText(R.string.search);
                	isSearched = false;                	
                }
            	
            	if (isFavorite == -1){
                	isFavorite = 1;
                	mIsFavorite.setImageResource(R.drawable.favoris);
                }
                else
                {
                	isFavorite = -1;
                	mIsFavorite.setImageResource(R.drawable.nofavoris);
                }
                
                fillData();
            }
        });
        
        this.mIsKnown = (ImageView) findViewById(R.id.filter_is_known);
        this.mIsKnown.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	if (isSearched){
                	nameFilter = "";
                	mSearch.setImageResource(R.drawable.ic_menu_search);
                	mSearchText.setText(R.string.search);
                	isSearched = false;                	
                }
            	
            	if (isKnown == -1){
                	isKnown = 1;
                	mIsKnown.setImageResource(R.drawable.grimoire);
                }
                else
                {
                	isKnown = -1;
                	mIsKnown.setImageResource(R.drawable.nogrimoire);
                }
                
                fillData();
            }
        });
        
        this.mIsInMemory = (ImageView) findViewById(R.id.filter_is_inmemory);
        this.mIsInMemory.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	if (isSearched){
                	nameFilter = "";
                	mSearch.setImageResource(R.drawable.ic_menu_search);
                	mSearchText.setText(R.string.search);
                	isSearched = false;                	
                }
            	
            	if (isInMemory == -1){
                	isInMemory = 1;
                	mIsInMemory.setImageResource(R.drawable.inmemory);
                }
                else
                {
                	isInMemory = -1;
                	mIsInMemory.setImageResource(R.drawable.noinmemory);
                }
                
                fillData();
            }
        });
        
        this.mSearch = (ImageView) findViewById(R.id.filter_search);
        this.mSearchText = (TextView) findViewById(R.id.filter_search_text);
        this.mSearch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (isSearched){
                	nameFilter = "";
                	mSearch.setImageResource(R.drawable.ic_menu_search);
                	mSearchText.setText(R.string.search);
                	isSearched = false;
                	fillData();
                }
                else
                {
                	onSearchRequested();
                }
            }
        });
        
        Intent intent = getIntent();    
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {      
        	this.nameFilter = intent.getStringExtra(SearchManager.QUERY);
        	this.mSearch.setImageResource(R.drawable.annuler);
        	this.mSearchText.setText(R.string.cancel);
        	this.isSearched = true;
        	}
        
        this.fillData();
    }
    
    private static String SpellFileName = "export.txt";
    // private static String ExportSep = "_||_";
    private static String ExportNL = "_NL_";
    public static String NewLine = "\n";
    
    private void initData() {
    	
    	this.mDbHelper.deleteAllSpells();
    	AssetManager assets = getAssets();    	     	    		      
    	try
    	{
    		InputStream input = assets.open(SpellFileName);
    		// Get the object of DataInputStream
    	    DataInputStream in = new DataInputStream(input);
    	    String charSet = "UTF-8";
    	    BufferedReader br = new BufferedReader(new InputStreamReader(in, charSet));
    	    String strLine;
    	    //Read File Line By Line
    	    int i = 0;
    	    while ((strLine = br.readLine()) != null)   {
    	      // Print the content on the console
    	      String[] splitedLine = strLine.split("_\\|\\|_");    	      
	    	  Spell spell = new Spell();
	    	  spell.name = splitedLine[0];
	    	  spell.school = splitedLine[1];
	    	  spell.register = splitedLine[2];
	    	  spell.branch = splitedLine[3];
	    	  if (splitedLine[4].length() > 0) {
	    		  spell.magianLevel = Integer.parseInt(splitedLine[4]); 
	    	  }
	    	  if (splitedLine[5].length() > 0) {
	    		  spell.priestLevel = Integer.parseInt(splitedLine[5]); 
	    	  }
	    	  if (splitedLine[6].length() > 0) {
	    		  spell.paladinLevel = Integer.parseInt(splitedLine[6]); 
	    	  }
	    	  if (splitedLine[7].length() > 0) {
	    		  spell.bardLevel = Integer.parseInt(splitedLine[7]); 
	    	  }
	    	  if (splitedLine[8].length() > 0) {
	    		  spell.druidLevel = Integer.parseInt(splitedLine[8]); 
	    	  }
	    	  if (splitedLine[9].length() > 0) {
	    		  spell.strikerLevel = Integer.parseInt(splitedLine[9]); 
	    	  }
	    	  
	    	  spell.castingTime = splitedLine[10];
	    	  spell.components = splitedLine[11];
	    	  spell.range = splitedLine[12];
	    	  spell.targetEffectArea = splitedLine[13];
	    	  spell.duration = splitedLine[14];
	    	  spell.saving = splitedLine[15];
	    	  spell.spellResistance = splitedLine[16];
	    	  spell.shortDescription = splitedLine[17];
	    	  String fullDesc = splitedLine[18];
	    	  fullDesc = fullDesc.replaceAll(ExportNL, NewLine);
	    	  spell.fullDescription = fullDesc;
	    	      	    	  	    	  
	    	  spell.comment = "";
	    	  	    	  
	    	  this.mDbHelper.createSpell(spell);    	      
    	      
    	      i++;
    	    }
    	    //Close the input stream
    	    in.close();
    	    
    	    this.mDbHelper.createInit();
    	}
    	catch(IOException ex)
    	{    	 
    		System.out.println(ex.getMessage());
    	}    	
	}
    
    private void fillData() {
        // Get all of the rows from the database and create the item list
    	Cursor spellsCursor = mDbHelper.fetchAllSpells(this.nameFilter, this.isFavorite, this.isKnown, this.isInMemory);
    	this.nameFilter = "";
        startManagingCursor(spellsCursor);
        
        // Create an array to specify the fields we want to display in the list
        String[] from = new String[]{SpellsDbAdapter.KEY_SPELL_NAME, SpellsDbAdapter.KEY_SPELL_SHORTDESCRIPTION};
        
        // and an array of the fields we want to bind those fields to
        int[] to = new int[]{R.id.row_name, R.id.row_short_description};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter spells = 
        	    new SimpleCursorAdapter(this, R.layout.spells_row, spellsCursor, from, to);
        setListAdapter(spells);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);        
        Intent i = new Intent(this, SpellDetail.class);
        i.putExtra(SpellsDbAdapter.KEY_SPELL_ID, id);        
        startActivityForResult(i, ACTIVITY_SHOWDETAIL);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);     
        // fillData();        
    }
}