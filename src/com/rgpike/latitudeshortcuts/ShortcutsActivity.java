package com.rgpike.latitudeshortcuts;

import android.app.Activity;

import android.os.Bundle;

import android.view.Window;
import android.view.View;

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
		for (Launcher l : launchers.getLauncherValues())
		{
			l.CreateShortcut(getApplicationContext());
		}

		About.ShowDialog(
			this,
			R.drawable.ic_launcher,
			getResources().getString(R.string.TitleAll),
			getResources().getString(R.string.AllAlertText)
			);
	}

    public void onAboutClick(View v)
    {
		About.onAboutClick(this, v);
	}
}
