package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.ComponentName;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class LatitudeShortcuts extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	private void CreateShortcut(String title, String uri)
	{
        Intent shortcut = new
            Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        shortcut.putExtra("duplicate", false);

        Intent intent = new Intent("android.intent.action.VIEW",
			Uri.parse(uri));
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
            intent);

        ShortcutIconResource iconRes =
            Intent.ShortcutIconResource.fromContext(this,
            R.drawable.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        sendBroadcast(shortcut);
	}

	private void AlertCompleted(String title, String message)
	{
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(getResources().getString(R.string.OK),
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                    {
                    };
            } );

        alertDialog.show();
	}

    public void onCreateCheckInClick(View v)
    {
		CreateShortcut(
            getResources().getString(R.string.TitleCheckin),
            getResources().getString(R.string.LatitudeCheckinURI)
			);

		AlertCompleted(
            getResources().getString(R.string.TitleCheckin),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitleCheckin))
			);
    }    

    public void onCreateListClick(View v)
    {
		CreateShortcut(
            getResources().getString(R.string.TitleList),
            getResources().getString(R.string.LatitudeListURI)
			);

		AlertCompleted(
            getResources().getString(R.string.TitleList),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitleList))
			);
	}

    public void onCreateHistoryClick(View v)
    {
		CreateShortcut(
            getResources().getString(R.string.TitleHistory),
            getResources().getString(R.string.LatitudeHistoryURI)
			);

		AlertCompleted(
            getResources().getString(R.string.TitleHistory),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitleHistory))
			);
	}

    public void onCreatePlacesClick(View v)
    {
		CreateShortcut(
            getResources().getString(R.string.TitlePlaces),
            getResources().getString(R.string.LatitudePlacesURI)
			);

		AlertCompleted(
            getResources().getString(R.string.TitlePlaces),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitlePlaces))
			);
	}

    public void onCreateAllClick(View v)
    {
		CreateShortcut(
            getResources().getString(R.string.TitleCheckin),
            getResources().getString(R.string.LatitudeCheckinURI)
			);
		CreateShortcut(
            getResources().getString(R.string.TitleList),
            getResources().getString(R.string.LatitudeListURI)
			);
		CreateShortcut(
            getResources().getString(R.string.TitleHistory),
            getResources().getString(R.string.LatitudeHistoryURI)
			);
		CreateShortcut(
            getResources().getString(R.string.TitlePlaces),
            getResources().getString(R.string.LatitudePlacesURI)
			);

		AlertCompleted("All", "All");
	}

    public void onAboutClick(View v)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(
            getResources().getString(R.string.AboutAlertTitle));
        alertDialog.setMessage(
            getResources().getString(R.string.AboutAlertMessage));
        alertDialog.setButton(getResources().getString(R.string.OK),
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                    {
                    };

            } );

        alertDialog.show();
    }
}
