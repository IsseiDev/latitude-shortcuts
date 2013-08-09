package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Context;

import android.net.Uri;

import android.app.AlertDialog;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.text.SpannableString;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class LatitudeShortcuts extends Activity
{
	private Map<String, Launcher> launchers =
		new HashMap<String, Launcher>();

	private class Launcher
	{
		public final static String mapCheckin = "checkin";
		public final static String mapList = "list";
		public final static String mapPlaces = "places";
		public final static String mapHistory = "history";

		private ShortcutIconResource icon;
		private String iconTitle;
		private String intentUri;
		private String alertTitle;
		private String alertMessage;

		public Launcher(ShortcutIconResource icon, String iconTitle, String intentUri, String alertTitle, String alertMessage)
		{
			setIconResource(icon);
			setIconTitle(iconTitle);
			setIntentUri(intentUri);
			setAlertTitle(alertTitle);
			setAlertMessage(alertMessage);
		}

		public ShortcutIconResource setIconResource(ShortcutIconResource r)
		{
			icon = r;
			return icon;
		}

		public ShortcutIconResource getIconResource()
		{
			return icon;
		}

		public String setIconTitle(String r)
		{
			iconTitle = r;
			return iconTitle;
		}

		public String getIconTitle()
		{
			return iconTitle;
		}

		public String setIntentUri(String r)
		{
			intentUri = r;
			return intentUri;
		}

		public String getIntentUri()
		{
			return intentUri;
		}

		public String setAlertTitle(String r)
		{
			alertTitle = r;
			return alertTitle;
		}

		public String getAlertTitle()
		{
			return alertTitle;
		}

		public String setAlertMessage(String r)
		{
			alertMessage = r;
			return alertMessage;
		}

		public String getAlertMessage()
		{
			return alertMessage;
		}

		public void CreateShortcut()
		{
			Intent shortcut = new
				Intent("com.android.launcher.action.INSTALL_SHORTCUT");

			shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, iconTitle);
			shortcut.putExtra("duplicate", false);

			Intent intent = new Intent("android.intent.action.VIEW",
				Uri.parse(intentUri));
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
				intent);

			shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				icon);

			sendBroadcast(shortcut);
		}
	}

	private void LoadLaunchers()
	{
		Launcher launcher;

		/* Check In */
		launcher = new Launcher(
			Intent.ShortcutIconResource.fromContext(this,
				R.drawable.ic_checkin),
			getResources().getString(R.string.TitleCheckin),
			getResources().getString(R.string.LatitudeCheckinURI),
			getResources().getString(R.string.TitleCheckin),
			String.format(
				getResources().getString(R.string.InstalledAlertMessage),
				getResources().getString(R.string.TitleCheckin))
			);
		launchers.put(Launcher.mapCheckin, launcher);

		/* Places */
		launcher = new Launcher(
			Intent.ShortcutIconResource.fromContext(this,
				R.drawable.ic_places),
			getResources().getString(R.string.TitlePlaces),
			getResources().getString(R.string.LatitudePlacesURI),
			getResources().getString(R.string.TitlePlaces),
			String.format(
				getResources().getString(R.string.InstalledAlertMessage),
				getResources().getString(R.string.TitlePlaces))
			);
		launchers.put(Launcher.mapPlaces, launcher);

		/* Friends List */
		launcher = new Launcher(
			Intent.ShortcutIconResource.fromContext(this,
				R.drawable.ic_list),
			getResources().getString(R.string.TitleList),
			getResources().getString(R.string.LatitudeListURI),
			getResources().getString(R.string.TitleList),
			String.format(
				getResources().getString(R.string.InstalledAlertMessage),
				getResources().getString(R.string.TitleList))
			);
		launchers.put(Launcher.mapList, launcher);

		/* History */
		launcher = new Launcher(
			Intent.ShortcutIconResource.fromContext(this,
				R.drawable.ic_history),
			getResources().getString(R.string.TitleHistory),
			getResources().getString(R.string.LatitudeHistoryURI),
			getResources().getString(R.string.TitleHistory),
			String.format(
				getResources().getString(R.string.InstalledAlertMessage),
				getResources().getString(R.string.TitleHistory))
			);
		launchers.put(Launcher.mapHistory, launcher);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		LoadLaunchers();
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

	public void onCreateShortcutClick(View v)
	{
		switch (v.getId())
			{
			case R.id.CreateCheckInButton:
				launchers.get(Launcher.mapCheckin).CreateShortcut();
				ShowDialog(
					launchers.get(Launcher.mapCheckin).getAlertTitle(),
					launchers.get(Launcher.mapCheckin).getAlertMessage()
					);
				break;
			case R.id.CreateHistoryButton:
				launchers.get(Launcher.mapHistory).CreateShortcut();
				ShowDialog(
					launchers.get(Launcher.mapHistory).getAlertTitle(),
					launchers.get(Launcher.mapHistory).getAlertMessage()
					);
				break;
			case R.id.CreatePlacesButton:
				launchers.get(Launcher.mapPlaces).CreateShortcut();
				ShowDialog(
					launchers.get(Launcher.mapPlaces).getAlertTitle(),
					launchers.get(Launcher.mapPlaces).getAlertMessage()
					);
				break;
			case R.id.CreateListButton:
				launchers.get(Launcher.mapList).CreateShortcut();
				ShowDialog(
					launchers.get(Launcher.mapList).getAlertTitle(),
					launchers.get(Launcher.mapList).getAlertMessage()
					);
				break;
			}
	}

	public void onCreateAllClick(View v)
	{
		for (Launcher l : launchers.values())
		{
			l.CreateShortcut();
		}

		ShowDialog(
			getResources().getString(R.string.TitleAll),
			getResources().getString(R.string.AllAlertText)
			);
	}

    public void onAboutClick(View v)
    {
		Context c = getApplicationContext();
		String versionName;

		try
		{
			versionName = c.getPackageManager().
				getPackageInfo(c.getPackageName(), 0 ).versionName;
		}
		catch(Exception e)
		{
			versionName = "Unknown";
		}

		String msg = "Version: " + versionName + "\n\n" +
			getResources().getString(R.string.AboutAlertMessage);

		ShowDialog(
            getResources().getString(R.string.AboutAlertTitle),
            msg
			);
	}
}
