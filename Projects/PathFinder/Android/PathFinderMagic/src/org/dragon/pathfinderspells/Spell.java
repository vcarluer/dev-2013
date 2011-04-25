package org.dragon.pathfinderspells;

import java.util.*;

import android.content.res.Resources;

public class Spell {	
	public long id;
	public String name;
	public String school;
	public String register;
	public String branch;
	
	public int magianLevel;
	public int priestLevel;
	public int paladinLevel;
	public int bardLevel;
	public int druidLevel;
	public int strikerLevel;	
		
	public String castingTime;
	public String components;
	public String range;
	public String targetEffectArea;
	public String duration;
	public String saving;
	public String spellResistance;
	public String shortDescription;	
	public String fullDescription;
	
	public String comment;
	public int isKnown;
	public int isInMemory;
	public int isFavorite;
		
	
	
	private String levels;
	
	public Spell(){
		this.magianLevel = -1;
		this.priestLevel = -1;
		this.paladinLevel = -1;
		this.bardLevel = -1;
		this.druidLevel = -1;		
		this.strikerLevel = -1;		
	}
	
	public String getLevels(Resources res){
		if (this.levels == null)
		{
			String returnLevels = "";
			
			if (this.magianLevel != -1){
				returnLevels += " " + res.getString(R.string.magician) + " " + String.valueOf(this.magianLevel);
	        }
			
			if (this.priestLevel != -1){
				returnLevels += " " + res.getString(R.string.priest) + " " + String.valueOf(this.priestLevel);
	        }
			
			if (this.paladinLevel != -1){
				returnLevels += " " + res.getString(R.string.paladin) + " " + String.valueOf(this.paladinLevel);
	        }
			
			if (this.bardLevel != -1){
				returnLevels += " " + res.getString(R.string.bard) + " " + String.valueOf(this.bardLevel);
	        }
			
			if (this.druidLevel != -1){
				returnLevels += " " + res.getString(R.string.druid) + " " + String.valueOf(this.druidLevel);
	        }
			
			if (this.strikerLevel != -1){
				returnLevels += " " + res.getString(R.string.striker) + " " + String.valueOf(this.strikerLevel);
	        }
			
			this.levels = returnLevels;		
		}			
		
		return this.levels;							
	}
	
	public void setLevels(String levels){
		this.levels = levels;
	}
}
