package org.dragon.pathfinderspells;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class PathFinderSpells extends ListActivity {
	
	public static final String ACTIVITY_LAST_POSITION="lastposition";
	private static final int ACTIVITY_SHOWDETAIL=0;	
	
    private boolean isBookmark = false;    
    private String nameFilter = "";
    private Boolean isSearched = false;
    private ArrayList<Spell> spellList;
    private SpellsArrayAdapter spellsAdapter;
    private BookmarkList bookmarkList;
    private int lastPosition;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spells_list);
        
        this.spellList = new ArrayList<Spell>();        
                
        this.initData();

        Intent intent = getIntent();    
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {      
        	this.nameFilter = intent.getStringExtra(SearchManager.QUERY);        	
        	this.isSearched = true;
        	}
        
        this.fillData();
    }
    
    private static String SpellFileName = "export.txt";
    private static String FieldSep = "_\\|\\|_";
    private static String ExportNL = "_NL_";
    public static String NewLine = "\n";    
    
    private void initData() {
    	
    	/*this.mDbHelper.deleteAllSpells();*/
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
    	      String[] splitedLine = strLine.split(FieldSep);    	      
	    	  Spell spell = new Spell(this.getResources());
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
	    	  this.spellList.add(spell);
    	      
    	      i++;
    	    }
    	    //Close the input stream
    	    br.close();
    	}
    	catch(IOException ex)
    	{    	 
    		System.out.println(ex.getMessage());
    	}    	
	}
    
    private void fillData() {            	
    	this.bookmarkList = new BookmarkList(this);
    	
    	ArrayList<Spell> fetchList = new ArrayList<Spell>();
    	for(Spell spell : this.spellList) {
    		boolean addSpell = true;
    		spell.isBookmark = this.bookmarkList.isBookmark(spell.name);
    		if (this.nameFilter.length() > 0) {
    			if (!spell.name.toLowerCase().contains(this.nameFilter.toLowerCase())) {
    				addSpell = false;
    			}
    		}
    		
    		if (this.isBookmark) {
    			if (!spell.isBookmark) {
    				addSpell = false;
    			}
    		}
    		
    		if (addSpell) {    			
    			fetchList.add(spell);
    		}
    	}
    	
    	this.nameFilter = "";
    	
    	this.spellsAdapter = new SpellsArrayAdapter(this, fetchList);    	
        setListAdapter(this.spellsAdapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);        
        Intent i = new Intent(this, SpellDetail.class);
        
        Spell spell = this.spellsAdapter.getItem(position);
        
        i.putExtra(Spell.KEY_SPELL_DETAIL, spell.getBundle());
        this.lastPosition = position;
        startActivityForResult(i, ACTIVITY_SHOWDETAIL);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);     
        fillData();        
        ListView lv = this.getListView();
        lv.setSelection(this.lastPosition);
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.list_menu, menu);		 		 
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_search:
			if (isSearched){
            	nameFilter = "";            	
            	isSearched = false;
            	fillData();
            }
            else
            {
            	onSearchRequested();
            }
			
			return true;
		case R.id.menu_bookmark:
			if (isSearched){
            	nameFilter = "";            	
            	isSearched = false;                	
            }
        	
        	if (isBookmark == false){
            	isBookmark = true;            	
            }
            else
            {
            	isBookmark = false;            	
            }
            
            fillData();
			return true;
		default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem bookMarkMenu = menu.findItem(R.id.menu_bookmark);
		MenuItem searchMenu = menu.findItem(R.id.menu_search);
		
		if (this.isBookmark) {			
			bookMarkMenu.setIcon(R.drawable.favoris); 
		}
		else
		{
			bookMarkMenu.setIcon(R.drawable.nofavoris);
		}
		
		if (this.isSearched) {
			searchMenu.setTitle(R.string.cancel);
			searchMenu.setIcon(R.drawable.annuler);
		}
		else
		{
			searchMenu.setTitle(R.string.search);
			searchMenu.setIcon(R.drawable.ic_menu_search);
		}
		
		return true;
	}
}