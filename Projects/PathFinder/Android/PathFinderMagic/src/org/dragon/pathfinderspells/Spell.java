package org.dragon.pathfinderspells;

import java.util.*;

import android.content.res.Resources;
import android.os.Bundle;

public class Spell implements Comparable<Spell> {
	public static final String KEY_SPELL_DETAIL = "spelldetail";
	
	public static final String KEY_SPELL_NAME = "name";       
    public static final String KEY_SPELL_SCHOOL = "school";
    public static final String KEY_SPELL_REGISTER = "register";
    public static final String KEY_SPELL_BRANCH = "branch";
    
    public static final String KEY_SPELL_MAGICIANLEVEL = "macicianlevel";
    public static final String KEY_SPELL_PRIESTLEVEL = "priestlevel";
    public static final String KEY_SPELL_PALADINLEVEL = "paladinlevel";
    public static final String KEY_SPELL_BARDLEVEL = "bardlevel";
    public static final String KEY_SPELL_DRUIDLEVEL = "druidlevel";    
    public static final String KEY_SPELL_STRIKERLEVEL = "strikerlevel";
    
    public static final String KEY_SPELL_CASTINGTIME = "castingtime";
    public static final String KEY_SPELL_COMPONENTS = "components";
    public static final String KEY_SPELL_RANGE = "range";
    public static final String KEY_SPELL_TARGETEFFECTAREA = "targeteffectarea";
    public static final String KEY_SPELL_DURATION = "duration";
    public static final String KEY_SPELL_SAVING = "saving";
    public static final String KEY_SPELL_SPELLRESISTANCE = "spellresistance";
    public static final String KEY_SPELL_SHORTDESCRIPTION = "shortdescription";
    public static final String KEY_SPELL_FULLDESCRIPTION = "fulldescription";    
    public static final String KEY_SPELL_ISBOOKMARK = "isbookmark";
	
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
		
	public boolean isBookmark;
	
	private String levels;
	
	private Resources resources;
	
	public Spell(Resources res){
		this.magianLevel = -1;
		this.priestLevel = -1;
		this.paladinLevel = -1;
		this.bardLevel = -1;
		this.druidLevel = -1;		
		this.strikerLevel = -1;
		this.resources = res;
	}
	
	public Spell(Bundle spellDetail, Resources res) {
		this(res);
		
		this.name = spellDetail.getString(Spell.KEY_SPELL_NAME);
        
        this.school = spellDetail.getString(Spell.KEY_SPELL_SCHOOL);
        this.register = spellDetail.getString(Spell.KEY_SPELL_REGISTER);
        this.branch = spellDetail.getString(Spell.KEY_SPELL_BRANCH);
        
        this.magianLevel = spellDetail.getInt(Spell.KEY_SPELL_MAGICIANLEVEL);
        this.priestLevel = spellDetail.getInt(Spell.KEY_SPELL_PRIESTLEVEL);
        this.paladinLevel = spellDetail.getInt(Spell.KEY_SPELL_PALADINLEVEL);
        this.bardLevel = spellDetail.getInt(Spell.KEY_SPELL_BARDLEVEL);
        this.druidLevel = spellDetail.getInt(Spell.KEY_SPELL_DRUIDLEVEL);        
        this.strikerLevel = spellDetail.getInt(Spell.KEY_SPELL_STRIKERLEVEL);
        
        this.castingTime = spellDetail.getString(Spell.KEY_SPELL_CASTINGTIME);
        this.components = spellDetail.getString(Spell.KEY_SPELL_COMPONENTS);
        this.range = spellDetail.getString(Spell.KEY_SPELL_RANGE);
        this.targetEffectArea = spellDetail.getString(Spell.KEY_SPELL_TARGETEFFECTAREA);
        this.duration = spellDetail.getString(Spell.KEY_SPELL_DURATION);
        this.saving = spellDetail.getString(Spell.KEY_SPELL_SAVING);
        this.spellResistance = spellDetail.getString(Spell.KEY_SPELL_SPELLRESISTANCE);
        this.shortDescription = spellDetail.getString(Spell.KEY_SPELL_SHORTDESCRIPTION);
        this.fullDescription = spellDetail.getString(Spell.KEY_SPELL_FULLDESCRIPTION);
        this.isBookmark = spellDetail.getBoolean(Spell.KEY_SPELL_ISBOOKMARK);
	}
	
	public String getLevels(){
		if (this.levels == null)
		{
			String returnLevels = "";
			
			if (this.magianLevel != -1){
				returnLevels += " " + this.resources.getString(R.string.magician) + " " + String.valueOf(this.magianLevel);
	        }
			
			if (this.priestLevel != -1){
				returnLevels += " " + this.resources.getString(R.string.priest) + " " + String.valueOf(this.priestLevel);
	        }
			
			if (this.paladinLevel != -1){
				returnLevels += " " + this.resources.getString(R.string.paladin) + " " + String.valueOf(this.paladinLevel);
	        }
			
			if (this.bardLevel != -1){
				returnLevels += " " + this.resources.getString(R.string.bard) + " " + String.valueOf(this.bardLevel);
	        }
			
			if (this.druidLevel != -1){
				returnLevels += " " + this.resources.getString(R.string.druid) + " " + String.valueOf(this.druidLevel);
	        }
			
			if (this.strikerLevel != -1){
				returnLevels += " " + this.resources.getString(R.string.striker) + " " + String.valueOf(this.strikerLevel);
	        }
			
			this.levels = returnLevels;		
		}			
		
		return this.levels;							
	}
	
	public String getFullSchool() {
		String fullSchool = this.school;
		if (this.branch.length() > 0) {
			fullSchool += " (" + this.branch + ")";
		}
		
		if (this.register.length() > 0) {
			fullSchool += " [" + this.register + "]";
		}
		
		return fullSchool;
	}
	
	public void setLevels(String levels){
		this.levels = levels;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int compareTo(Spell another) {
		return this.name.compareToIgnoreCase(another.name); 
	}
	
	public Bundle getBundle() {
		Bundle spellDetail = new Bundle();
		
		this.fillBundle(spellDetail);
        
        return spellDetail;
	}
	
	public void fillBundle(Bundle spellDetail) {
		spellDetail.putString(Spell.KEY_SPELL_NAME, this.name);
        
        spellDetail.putString(Spell.KEY_SPELL_SCHOOL, this.school);
        spellDetail.putString(Spell.KEY_SPELL_REGISTER, this.register);
        spellDetail.putString(Spell.KEY_SPELL_BRANCH, this.branch);
        
        spellDetail.putInt(Spell.KEY_SPELL_MAGICIANLEVEL, this.magianLevel);
        spellDetail.putInt(Spell.KEY_SPELL_PRIESTLEVEL, this.priestLevel);
        spellDetail.putInt(Spell.KEY_SPELL_PALADINLEVEL, this.paladinLevel);
        spellDetail.putInt(Spell.KEY_SPELL_BARDLEVEL, this.bardLevel);
        spellDetail.putInt(Spell.KEY_SPELL_DRUIDLEVEL, this.druidLevel);        
        spellDetail.putInt(Spell.KEY_SPELL_STRIKERLEVEL, this.strikerLevel);
        
        spellDetail.putString(Spell.KEY_SPELL_CASTINGTIME, this.castingTime);
        spellDetail.putString(Spell.KEY_SPELL_COMPONENTS, this.components);
        spellDetail.putString(Spell.KEY_SPELL_RANGE, this.range);
        spellDetail.putString(Spell.KEY_SPELL_TARGETEFFECTAREA, this.targetEffectArea);
        spellDetail.putString(Spell.KEY_SPELL_DURATION, this.duration);
        spellDetail.putString(Spell.KEY_SPELL_SAVING, this.saving);
        spellDetail.putString(Spell.KEY_SPELL_SPELLRESISTANCE, this.spellResistance);
        spellDetail.putString(Spell.KEY_SPELL_SHORTDESCRIPTION, this.shortDescription);
        spellDetail.putString(Spell.KEY_SPELL_FULLDESCRIPTION, this.fullDescription);
        spellDetail.putBoolean(Spell.KEY_SPELL_ISBOOKMARK, this.isBookmark);
	}
}
