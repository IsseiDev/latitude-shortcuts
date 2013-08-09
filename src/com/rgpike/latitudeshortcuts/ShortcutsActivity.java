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

public class ShortcutsActivity extends Activity
{
	private LauncherCollection launchers;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shortcuts_layout);

		launchers = new LauncherCollection(this);
	}

	public void onCreateShortcutClick(View v)
	{
		switch (v.getId())
			{
			case R.id.CreateCheckInButton:
				launchers.getLauncher(LauncherCollection.mapCheckin).
					CreateShortcut(getApplicationContext());
				launchers.getLauncher(LauncherCollection.mapCheckin).
					ShowDialog(this);
				break;
			case R.id.CreateHistoryButton:
				launchers.getLauncher(LauncherCollection.mapHistory).
					CreateShortcut(getApplicationContext());
				launchers.getLauncher(LauncherCollection.mapHistory).
					ShowDialog(this);
				break;
			case R.id.CreatePlacesButton:
				launchers.getLauncher(LauncherCollection.mapPlaces).
					CreateShortcut(getApplicationContext());
				launchers.getLauncher(LauncherCollection.mapPlaces).
					ShowDialog(this);
				break;
			case R.id.CreateListButton:
				launchers.getLauncher(LauncherCollection.mapList).
					CreateShortcut(getApplicationContext());
				launchers.getLauncher(LauncherCollection.mapList).
					ShowDialog(this);
				break;
			}
	}

	public void onCreateAllClick(View v)
	{
		//for (Launcher l : launchers.l.values())
		for (Launcher l : launchers.getLauncherValues())
		{
			l.CreateShortcut(getApplicationContext());
		}

		About.ShowDialog(
			this,
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

		About.ShowDialog(
			this,
            getResources().getString(R.string.AboutAlertTitle),
            msg
			);
	}
}
