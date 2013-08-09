
package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class Launcher {
	private String mLauncherName;

    private int mIconRes;

	private Bitmap mScaledBitmap;

    private String mIconTitle;

    private String mIntentUri;

    private String mAlertTitle;

    private String mAlertMessage;

    public Launcher(String launcherName, int iconRes, String iconTitle, String intentUri, String alertTitle,
            String alertMessage) {
		setLauncherName(launcherName);
        setIconResource(iconRes);
        setIconTitle(iconTitle);
        setIntentUri(intentUri);
        setAlertTitle(alertTitle);
        setAlertMessage(alertMessage);
    }

	public String setLauncherName(String s) {
		mLauncherName = s;
		return s;
	}
	
	public String getLauncherName() {
		return mLauncherName;
	}
	
    public int setIconResource(int r) {
        mIconRes = r;
        return mIconRes;
    }

    public int getIconResource() {
        return mIconRes;
    }

    public Bitmap setIconScaledBitmap(Bitmap bitmap) {
        mScaledBitmap = bitmap;
        return mScaledBitmap;
    }

    public Bitmap getIconScaledBitmap() {
        return mScaledBitmap;
    }

    public String setIconTitle(String r) {
        mIconTitle = r;
        return mIconTitle;
    }

    public String getIconTitle() {
        return mIconTitle;
    }

    public String setIntentUri(String r) {
        mIntentUri = r;
        return mIntentUri;
    }

    public String getIntentUri() {
        return mIntentUri;
    }

    public String setAlertTitle(String r) {
        mAlertTitle = r;
        return mAlertTitle;
    }

    public String getAlertTitle() {
        return mAlertTitle;
    }

    public String setAlertMessage(String r) {
        mAlertMessage = r;
        return mAlertMessage;
    }

    public String getAlertMessage() {
        return mAlertMessage;
    }

    /** Launches an Activity based on this class instance */
    public void LaunchIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mIntentUri));
        context.startActivity(intent);
    }

    // ** Creates and returns an Intent based on class instance */
    public Intent GetShortcut(Context context) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mIconTitle);
        shortcut.putExtra("duplicate", false);

        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(mIntentUri));
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

		if (mScaledBitmap != null) {
			//mScaledBitmap = IconUtils.getIconScaledBitmap((Activity) context, mIconBitmap);
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, mScaledBitmap);
		} else {
			ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(context, mIconRes);
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		}

        return shortcut;
    }

    /** Creates a shortcut based on class instance */
    public void CreateShortcut(Context context) {
        context.sendBroadcast(GetShortcut(context));
    }

    public void ShowDialog(Context context) {
        final SpannableString s = new SpannableString(mAlertMessage);
        Linkify.addLinks(s, Linkify.ALL);

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(mIconTitle);
        alertDialog.setMessage(s);

		if (mScaledBitmap != null) {
        	alertDialog.setIcon(new BitmapDrawable(context.getResources(), mScaledBitmap));
		} else {
        alertDialog.setIcon(mIconRes);
		}

        alertDialog.setButton(context.getResources().getString(R.string.OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    };
                });

        alertDialog.show();

        ((TextView)alertDialog.findViewById(android.R.id.message))
                .setMovementMethod(LinkMovementMethod.getInstance());
    }
}
