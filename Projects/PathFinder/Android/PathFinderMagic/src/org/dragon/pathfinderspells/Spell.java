package org.dragon.pathfinderspells;

import java.util.*;

import android.content.res.Resources;

public class Spell {	
	public long id;
	public String name;
	public String shortDescription;
	public String school;
	public String incantationTime;
	public String components;
	public String range;
	public String target;
	public String duration;
	public String saving;
	public String magicResistance;
	public String fullDescription;
	public String comment;
	public int isKnown;
	public int isInMemory;
	public int isFavorite;
	public int magianLevel;
	public int priestLevel;
	public int druidLevel;
	public int paladinLevel;
	public int strikerLevel;
	public int bardLevel;
	
	private String levels;
	
	public Spell(){
		this.magianLevel = -1;
		this.priestLevel = -1;
		this.druidLevel = -1;
		this.paladinLevel = -1;
		this.strikerLevel = -1;
		this.bardLevel = -1;
	}
	
	public String getLevels(Resources res){
		if (this.levels == null)
		{
			String returnLevels = "";
						
			if (this.bardLevel != -1){
				returnLevels += " " + res.getString(R.string.bard) + " " + String.valueOf(bardLevel);
	        }
			
			if (this.magianLevel != -1){
				returnLevels += " " + res.getString(R.string.magician) + " " + String.valueOf(magianLevel);
	        }
			
			this.levels = returnLevels;		
		}			
		
		return this.levels;							
	}
	
	public void setLevels(String levels){
		this.levels = levels;
	}
}
