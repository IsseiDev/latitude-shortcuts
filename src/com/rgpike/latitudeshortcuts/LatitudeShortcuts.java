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
import android.view.Window;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.text.SpannableString;

public class LatitudeShortcuts extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	private void CreateShortcut(ShortcutIconResource iconRes, String title, String uri)
	{
        Intent shortcut = new
            Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        shortcut.putExtra("duplicate", false);

        Intent intent = new Intent("android.intent.action.VIEW",
			Uri.parse(uri));
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
            intent);

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        sendBroadcast(shortcut);
	}


	private void ShowDialog(String title, String message)
	{
		final SpannableString s = new SpannableString(message);
		Linkify.addLinks(s, Linkify.ALL);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(s);
        alertDialog.setButton(getResources().getString(R.string.OK),
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                    {
                    };
            } );

        alertDialog.show();

		((TextView) alertDialog.findViewById(android.R.id.message)).
			setMovementMethod(LinkMovementMethod.getInstance());

	}

	private void CreateShortcutAndShowDialog(ShortcutIconResource iconRes, String launcherTitle, String uri, String alertTitle, String alertMessage)
	{
		CreateShortcut(iconRes, launcherTitle, uri);
		ShowDialog(alertTitle, alertMessage);
	}

    public void onCreateCheckInClick(View v)
    {
		CreateShortcutAndShowDialog(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_checkin),
            getResources().getString(R.string.TitleCheckin),
            getResources().getString(R.string.LatitudeCheckinURI),
            getResources().getString(R.string.TitleCheckin),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitleCheckin))
			);
    }    

    public void onCreateListClick(View v)
    {
		CreateShortcutAndShowDialog(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_list),
            getResources().getString(R.string.TitleList),
            getResources().getString(R.string.LatitudeListURI),
            getResources().getString(R.string.TitleList),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitleList))
			);
	}

    public void onCreateHistoryClick(View v)
    {
		CreateShortcutAndShowDialog(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_history),
            getResources().getString(R.string.TitleHistory),
            getResources().getString(R.string.LatitudeHistoryURI),
            getResources().getString(R.string.TitleHistory),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitleHistory))
			);
	}

    public void onCreatePlacesClick(View v)
    {
		CreateShortcutAndShowDialog(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_places),
            getResources().getString(R.string.TitlePlaces),
            getResources().getString(R.string.LatitudePlacesURI),
            getResources().getString(R.string.TitlePlaces),
			String.format(
            	getResources().getString(R.string.InstalledAlertMessage),
            	getResources().getString(R.string.TitlePlaces))
			);
	}

    public void onCreateAllClick(View v)
    {
		CreateShortcut(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_checkin),
            getResources().getString(R.string.TitleCheckin),
            getResources().getString(R.string.LatitudeCheckinURI)
			);
		CreateShortcut(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_list),
            getResources().getString(R.string.TitleList),
            getResources().getString(R.string.LatitudeListURI)
			);
		CreateShortcut(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_history),
            getResources().getString(R.string.TitleHistory),
            getResources().getString(R.string.LatitudeHistoryURI)
			);
		CreateShortcut(
            Intent.ShortcutIconResource.fromContext(this,
            	R.drawable.ic_places),
            getResources().getString(R.string.TitlePlaces),
            getResources().getString(R.string.LatitudePlacesURI)
			);

		ShowDialog(
            getResources().getString(R.string.TitleAll),
            getResources().getString(R.string.AllAlertText)
			);
	}

    public void onAboutClick(View v)
    {
		ShowDialog(
            getResources().getString(R.string.AboutAlertTitle),
            getResources().getString(R.string.AboutAlertMessage)
			);
    }
}
