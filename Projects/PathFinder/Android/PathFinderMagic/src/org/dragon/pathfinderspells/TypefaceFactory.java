package org.dragon.pathfinderspells;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Typeface;


public class TypefaceFactory {	
	private static Typeface typePathfinder;
	private static Typeface typeRegular;
	private static Typeface typeBold;
	private static Typeface typeItalic;
	private static Typeface typeBoldItalic;
		
	public static Typeface getPathfinder(Context context) {
		if (typePathfinder == null) {
			typePathfinder = Typeface.createFromAsset(context.getAssets(), "fonts/pathfinder-00.ttf");
		}
		
		return typePathfinder;
	}
	
	public static Typeface getRegular(Context context) {
		if (typeRegular == null) {
			typeRegular = Typeface.createFromAsset(context.getAssets(), "fonts/nexusSansOT-Regular.otf");
		}
		
		return typeRegular;
	}
	
	public static Typeface getBold(Context context) {
		if (typeBold == null) {
			typeBold = Typeface.createFromAsset(context.getAssets(), "fonts/nexusSansOT-Bold.otf");
		}
		
		return typeBold;
	}
	
	public static Typeface getItalic(Context context) {
		if (typeItalic == null) {
			typeItalic = Typeface.createFromAsset(context.getAssets(), "fonts/nexusSansOT-Italic.otf");
		}
		
		return typeItalic;
	}
	
	public static Typeface getBoldItalic(Context context) {
		if (typeBoldItalic == null) {
			typeBoldItalic = Typeface.createFromAsset(context.getAssets(), "fonts/nexusSansOT-BoldItalic.otf");
		}
		
		return typeBoldItalic;
	}
}
