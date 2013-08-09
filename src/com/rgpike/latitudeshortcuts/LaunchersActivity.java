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

public class LaunchersActivity extends Activity
{
	private LauncherCollection launchers;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchers_layout);
		launchers = new LauncherCollection(this);
	}

	public void onLaunchClick(View v)
	{
		switch (v.getId())
			{
			case R.id.LaunchCheckinButton:
				launchers.getLauncher(
					LauncherCollection.mapCheckin).LaunchIntent(this);
				break;
			case R.id.LaunchListButton:
				launchers.getLauncher(
					LauncherCollection.mapList).LaunchIntent(this);
				break;
			case R.id.LaunchPlacesButton:
				launchers.getLauncher(
					LauncherCollection.mapPlaces).LaunchIntent(this);
				break;
			case R.id.LaunchHistoryButton:
				launchers.getLauncher(
					LauncherCollection.mapHistory).LaunchIntent(this);
				break;
			}
	}

    public void onAboutClick(View v)
    {
		About.onAboutClick(this, v);
	}
}
