package org.dragon.pathfinderspells;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;
    private int width;
    private int height;

    private Integer[] mImageIds = {
            R.drawable.toutes,
    		R.drawable.bard,
            R.drawable.cleric,
            R.drawable.druid,
            R.drawable.paladin,
            R.drawable.ranger,
            R.drawable.sorcerer,
            R.drawable.wizard
    };

    public ImageAdapter(Context c, int width, int height) {
        mContext = c;
        this.width = width;
        this.height = height;
        /*TypedArray a = mContext.obtainStyledAttributes(R.styleable.SpellGallery);
        mGalleryItemBackground = a.getResourceId(
                R.styleable.SpellGallery_android_galleryItemBackground, 0);            
        a.recycle();*/
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[position]);        
        
        int iHeight = height - 100;
        // TODO: hard coded ratio here with proper image constant size
        int iWidth = Math.round(iHeight * 0.7f);
        
        i.setLayoutParams(new Gallery.LayoutParams(iWidth, iHeight));
        i.setScaleType(ImageView.ScaleType.FIT_END);
        i.setFocusable(true);
        i.setFocusableInTouchMode(true);
        // i.setBackgroundResource(mGalleryItemBackground);
        i.setBackgroundResource(R.drawable.gallery_background);
        
        return i;
    }
}
