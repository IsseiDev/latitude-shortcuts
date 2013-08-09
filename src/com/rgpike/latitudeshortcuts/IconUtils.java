
package com.rgpike.latitudeshortcuts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.content.res.Configuration;
import android.view.Display;
import android.util.Log;

/** Presents and processes a widget selection screen */
public class IconUtils {
    public static final String TAG = "IconUtils";

    /** Creates an Intent used to retrieve an image */
    public static Intent genGetImageIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        return intent;
    }

	private static int getScale(int originalWidth,int originalHeight, final int requiredWidth,final int requiredHeight) {
		int scale = 1;
			          
		if ((originalWidth > requiredWidth) ||
				(originalHeight > requiredHeight)) {
			if (originalWidth < originalHeight) {
				scale = Math.round((float)originalWidth / requiredWidth);
			} else {
				scale = Math.round((float)originalHeight / requiredHeight);
			}
	}
														         
	return scale;
	}

    /** Retreives a Bitmap image from a URI */
    public static Bitmap getBitmapFromUri(Activity activity, Uri uri) {
        Bitmap bitmap = null;

        try {
			BitmapFactory.Options options = new BitmapFactory.Options();

            InputStream stream = activity.getContentResolver().openInputStream(uri);
            if (stream != null) {
				options.inJustDecodeBounds = true;
                bitmap = BitmapFactory.decodeStream(stream, null, options);
				stream.close();
			}

	        stream = activity.getContentResolver().openInputStream(uri);
			if (stream != null) {
				options.inSampleSize = getScale(options.outWidth, options.outHeight,
					128, 128);
				options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(stream, null, options);

                stream.close();
            }
        } catch (FileNotFoundException e) {
			Log.d(TAG, "Could not open bitmap URI for input stream");
        } catch (IOException e) {
			Log.d(TAG, "Problem while reading bitmap");
        }

        return bitmap;
    }

    public static int getIconSize(Activity activity) {
        int iconSize = 72;

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                iconSize = 36;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                iconSize = 48;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                iconSize = 72;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                iconSize = 96;
                break;
        /*
         * case DisplayMetrics.DENSITY_XXHIGH: iconSize = 128; break; case
         * DisplayMetrics.DENSITY_DEFAULT: case DisplayMetrics.DENSITY_TV:
         * iconSize = 72; break;
         */
        }

        return iconSize;
    }

	public static Bitmap getIconScaledBitmap(Activity activity, Bitmap bitmap) {
		int iconSize = IconUtils.getIconSize(activity);
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,
			iconSize, iconSize, true);
		return scaledBitmap;
	}
}
