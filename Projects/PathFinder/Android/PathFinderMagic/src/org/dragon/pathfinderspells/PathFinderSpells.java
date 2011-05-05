package org.dragon.pathfinderspells;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PathFinderSpells extends ListActivity {
	
	public static final String ACTIVITY_LAST_POSITION="lastposition";
	private static final int ACTIVITY_SHOWDETAIL=0;	
	private static final String FILE_PREFERENCE = "preferences";
	
	public static final int SELECT_TOUTES = 0;
	public static final int SELECT_SORCERER_WIZARD = 1;
	public static final int SELECT_CLERIC = 2;
	public static final int SELECT_DRUID = 3;
	public static final int SELECT_BARD = 4;		
	public static final int SELECT_PALADIN = 5;
	public static final int SELECT_RANGER = 6;	
	
	private static final int DIALOG_SORT = 0;
	private static final int DIALOG_SORT_NOCLASS = 1;
	private static final int DIALOG_SELECT_CLASS = 2;
	
	public static final int SORT_ALPHA = 0;	
	public static final int SORT_SCHOOL = 1;
	public static final int SORT_LEVEL = 2;
	
    private boolean isBookmark;    
    private String nameFilter;
    private Boolean isSearched;
    private ArrayList<Spell> spellList;
    private SpellsArrayAdapter spellsAdapter;
    private BookmarkList bookmarkList;
    private int lastPosition;
           
    private int classPosition;
    private int sortPosition;
    private TextView recallClassText;
    private TextView recallSortText;
    private ImageView recallSearchImage;
    private ImageView recallBookmarkImage;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spells_list);                
        
        TextView emptyText = (TextView) findViewById(android.R.id.empty);
        emptyText.setTypeface(TypefaceFactory.getBold(this));
        
        this.recallClassText = (TextView) findViewById(R.id.recall_class);
        this.recallClassText.setTypeface(TypefaceFactory.getRegular(this));
        this.recallSortText = (TextView) findViewById(R.id.recall_sort);
        this.recallSortText.setTypeface(TypefaceFactory.getRegular(this));
        this.recallSearchImage = (ImageView) findViewById(R.id.recall_search);
        this.recallSearchImage.setVisibility(View.GONE);
        this.recallBookmarkImage = (ImageView) findViewById(R.id.recall_bookmark);
        this.recallBookmarkImage.setVisibility(View.GONE);
                
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        this.spellList = new ArrayList<Spell>();                      
        this.initData();
        
        this.isSearched = false;
        this.nameFilter = "";   
        this.readPreferences();

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
    		
    		switch(this.classPosition) {
    		case SELECT_BARD: 
    			if (spell.bardLevel == -1) {
    				addSpell = false;
    			}
    			break;
    		case SELECT_CLERIC:
    			if (spell.priestLevel == -1) {
    				addSpell = false;
    			}
    			break;
    		case SELECT_DRUID:
    			if (spell.druidLevel == -1) {
    				addSpell = false;
    			}
    			break;
    		case SELECT_PALADIN:
    			if (spell.paladinLevel == -1) {
    				addSpell = false;
    			}
    			break;
    		case SELECT_RANGER:
    			if (spell.strikerLevel == -1) {
    				addSpell = false;
    			}
    			break;
    		case SELECT_SORCERER_WIZARD:
    			if (spell.magianLevel == -1) {
    				addSpell = false;
    			}
    			break;    		
    		case SELECT_TOUTES:
    			default:
    				break;
    		}
    		
    		if (addSpell) {    			
    			fetchList.add(spell);
    		}
    	}    	    	
    	
    	switch (this.sortPosition) {
    	case SORT_ALPHA:
    		Collections.sort(fetchList, new SpellNameComparator());
    		break;
    	case SORT_SCHOOL:
    		Collections.sort(fetchList, new SpellSchoolComparator());
    		break;
    	case SORT_LEVEL:
    		Collections.sort(fetchList, new SpellLevelComparator(this.classPosition));
    		break;
    		default:
    			break;
    	}
    	
    	this.spellsAdapter = new SpellsArrayAdapter(this, fetchList, this.sortPosition, this.classPosition);    	
        setListAdapter(this.spellsAdapter);
        
        this.setRecall();
        
        this.writePreferences();
    }
    
    private void setRecall() {
    	switch(this.classPosition) {
		case SELECT_BARD: 
			this.recallClassText.setText(R.string.menu_bard);
			break;
		case SELECT_CLERIC:
			this.recallClassText.setText(R.string.menu_priest);
			break;
		case SELECT_DRUID:
			this.recallClassText.setText(R.string.menu_druid);
			break;
		case SELECT_PALADIN:
			this.recallClassText.setText(R.string.menu_paladin);
			break;
		case SELECT_RANGER:
			this.recallClassText.setText(R.string.menu_striker);
			break;
		case SELECT_SORCERER_WIZARD:
			this.recallClassText.setText(R.string.menu_magician);
			break;    		
		case SELECT_TOUTES:
			default:
				this.recallClassText.setText(R.string.menu_all_classes);
				break;
		}
    	
    	switch (this.sortPosition) {    	
    	case 1:
    		this.recallSortText.setText(R.string.menu_school);
    		break;
    	case 2:
    		this.recallSortText.setText(R.string.menu_level);
    		break;
    	case 0:
    	default:
    		this.recallSortText.setText(R.string.menu_alpha);    		    		
    		break;
    	}
    	
    	if (this.nameFilter.length() > 0) {
			this.recallSearchImage.setVisibility(View.VISIBLE);
		}
    	else {
    		this.recallSearchImage.setVisibility(View.GONE);
    	}
    		
		
		if (this.isBookmark) {
			this.recallBookmarkImage.setVisibility(View.VISIBLE);
		}
		else {
			this.recallBookmarkImage.setVisibility(View.GONE);
		}
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
			/*if (isSearched){
            	nameFilter = "";            	
            	isSearched = false;                	
            }*/
        	
        	if (isBookmark == false){
            	isBookmark = true;            	
            }
            else
            {
            	isBookmark = false;            	
            }
            
            fillData();
			return true;
		case R.id.menu_class:
			showDialog(DIALOG_SELECT_CLASS);
			return true;
		case R.id.menu_sort:
			if (this.classPosition == SELECT_TOUTES) {
				showDialog(DIALOG_SORT_NOCLASS);
			}
			else {
				showDialog(DIALOG_SORT);
			}			
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
			searchMenu.setIcon(R.drawable.search);
		}		
		
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {		
		super.onConfigurationChanged(newConfig);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		Resources res = getResources();		
		AlertDialog.Builder builder;
		
	    switch(id) {
	    case DIALOG_SORT:	    		    	
	    	final CharSequence[] items = {res.getString(R.string.menu_alpha), res.getString(R.string.menu_school), res.getString(R.string.menu_level)};

			builder = new AlertDialog.Builder(this);
			builder.setTitle("Choisissez un tri");
			builder.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        sortPosition = item;
			    	Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
			    	fillData();
			    }
			});
			dialog = builder.create();
	        break;	
	    case DIALOG_SORT_NOCLASS:	    		    	
	    	final CharSequence[] items2 = {res.getString(R.string.menu_alpha), res.getString(R.string.menu_school)};

			builder = new AlertDialog.Builder(this);
			builder.setTitle("Choisissez un tri");
			builder.setItems(items2, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        sortPosition = item;
			    	Toast.makeText(getApplicationContext(), items2[item], Toast.LENGTH_SHORT).show();
			    	fillData();
			    }
			});
			dialog = builder.create();
	        break;
	    case DIALOG_SELECT_CLASS:
	    	final CharSequence[] itemsClass = {
	    			res.getString(R.string.menu_all_classes), 
	    			res.getString(R.string.menu_magician),	    			
	    			res.getString(R.string.menu_priest),
	    			res.getString(R.string.menu_druid),
	    			res.getString(R.string.menu_bard),	    			
	    			res.getString(R.string.menu_paladin),
	    			res.getString(R.string.menu_striker)	    			
	    			};
	    	builder = new AlertDialog.Builder(this);
			builder.setTitle("Choisissez une classe");
			builder.setItems(itemsClass, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        classPosition = item;
			    	Toast.makeText(getApplicationContext(), itemsClass[item], Toast.LENGTH_SHORT).show();
			    	fillData();
			    }
			});
			dialog = builder.create();
	        break;
	    default:
	        dialog = null;
	    }
	    return dialog;
	}
	
	private void readPreferences() {		
    	try
    	{
    		InputStream input = openFileInput(FILE_PREFERENCE);
    		// Get the object of DataInputStream
    	    DataInputStream in = new DataInputStream(input);
    	    String charSet = "UTF-8";
    	    BufferedReader br = new BufferedReader(new InputStreamReader(in, charSet));
    	    String strLine;
    	    //Read File Line By Line
    	    int i = 0;    	    
    	    while ((strLine = br.readLine()) != null)   {    	    	
    	    	switch(i) {
    	    	case 0:
    	    		this.classPosition = Integer.parseInt(strLine);
    	    		break;
    	    	case 1:
    	    		this.sortPosition = Integer.parseInt(strLine);
    	    		break;
    	    	case 2:
    	    		this.isBookmark = Boolean.parseBoolean(strLine);
    	    		break;
    	    	default:
    	    		break;
    	    	}
    	    	
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
	
	private void writePreferences() {
		try
    	{
    		OutputStream output = openFileOutput(FILE_PREFERENCE, Context.MODE_PRIVATE);
    		// Get the object of DataInputStream
    		DataOutputStream out = new DataOutputStream(output);
    	    String charSet = "UTF-8";
    	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, charSet));
    	        	    
    	    bw.write(this.classPosition + "\n");
    	    bw.write(this.sortPosition + "\n");
    	    bw.write(this.isBookmark + "\n");
    	    
    	    bw.close();
    	}
    	catch(IOException ex)
    	{    	 
    		System.out.println(ex.getMessage());
    	}  
	}
}