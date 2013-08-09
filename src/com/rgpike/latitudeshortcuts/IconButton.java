package com.rgpike.latitudeshortcuts;

import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.app.Activity;
import android.view.Display;

public class IconButton extends Button {
	private String text;
	private Drawable icon;

	Context mContext;
	
	public IconButton(Context context) {
		super(context);
		mContext = context;
	}

	public IconButton(Context context, AttributeSet attributes) {
		super(context, attributes);
		mContext = context;
	}

	public IconButton(Context context, AttributeSet attributes, int defStyle) {
		super(context, attributes, defStyle);
		mContext = context;
	}

	public void saveState() {
		int offset;
		text = getText().toString();
		Drawable[] drawable = getCompoundDrawables();

		if (getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
			offset = 1;
		} else {
			offset = 0;
		}
		
		icon = drawable[offset];
	}

	public void restoreState() {
		setText(text);
		setIconFromDrawable(icon);
	}

	public void setIconFromDrawable(Drawable drawable) {
		if (getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
	        setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
		} else {
	        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
		}
	}

    /** Sets button icon based on bitmap */
    public void setIconFromBitmap(Bitmap bitmap) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, true);

        Drawable drawable = new BitmapDrawable(((Activity) mContext).getResources(), scaledBitmap);
		setIconFromDrawable(drawable);
    }

	private int getScreenOrientation() {
		Display getOrient = ((Activity) mContext).getWindowManager().getDefaultDisplay();
		int orientation = Configuration.ORIENTATION_UNDEFINED;

		if (getOrient.getWidth() == getOrient.getHeight()) {
			orientation = Configuration.ORIENTATION_SQUARE;
		} else { 
			if (getOrient.getWidth() < getOrient.getHeight()) {
				orientation = Configuration.ORIENTATION_PORTRAIT;
			} else { 
				orientation = Configuration.ORIENTATION_LANDSCAPE;
			}
		}

	return orientation;
	}
}
