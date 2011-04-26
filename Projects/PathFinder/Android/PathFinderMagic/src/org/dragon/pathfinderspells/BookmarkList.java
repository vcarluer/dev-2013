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
import java.util.HashSet;

import android.content.Context;

public class BookmarkList {
	private static String BookmarkFileName = "bookmark.txt";
	public HashSet<String> bookmarkList;
	
	public BookmarkList(Context context)
	{
		this.bookmarkList = new HashSet<String>();
		this.readFileAndFill(context);
	}
	
	private void readFileAndFill(Context context) {
		this.bookmarkList.clear();
    	try
    	{
    		InputStream input = context.openFileInput(BookmarkFileName);
    		// Get the object of DataInputStream
    	    DataInputStream in = new DataInputStream(input);
    	    String charSet = "UTF-8";
    	    BufferedReader br = new BufferedReader(new InputStreamReader(in, charSet));
    	    String strLine;
    	    //Read File Line By Line
    	    int i = 0;    	    
    	    while ((strLine = br.readLine()) != null)   {
    	    	this.bookmark(strLine);    	   
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
	
	private void writeFile(Context context) {
		try
    	{
    		OutputStream output = context.openFileOutput(BookmarkFileName, Context.MODE_PRIVATE);
    		// Get the object of DataInputStream
    		DataOutputStream out = new DataOutputStream(output);
    	    String charSet = "UTF-8";
    	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, charSet));
    	    
    	    for(String spellName : this.bookmarkList) {
    	    	bw.write(spellName + "\n");
    	    }
    	    
    	    bw.close();
    	}
    	catch(IOException ex)
    	{    	 
    		System.out.println(ex.getMessage());
    	}  
	}
	
	public void saveBookmark(Context context) {
		this.writeFile(context);
	}
	
	public void bookmark(String spellName) {
		if (!this.bookmarkList.contains(spellName.toLowerCase())) {
			this.bookmarkList.add(spellName.toLowerCase());			
		}
	}
	
	public void unbookmark(String spellName) {
		if (this.bookmarkList.contains(spellName.toLowerCase())) {
			this.bookmarkList.remove(spellName.toLowerCase());
		}
	}
	
	public boolean isBookmark(String spellName) {
		return this.bookmarkList.contains(spellName.toLowerCase());
	}
}
