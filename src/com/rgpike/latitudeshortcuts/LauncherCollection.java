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
import java.util.Collection;

public class LauncherCollection
{
	public final static String mapCheckin = "checkin";
	public final static String mapList = "list";
	public final static String mapPlaces = "places";
	public final static String mapHistory = "history";

	private Map<String, Launcher> l =
		new HashMap<String, Launcher>();

	public LauncherCollection(Context context)
	{
		LoadLaunchers(context);
	}

	public Collection<Launcher> getLauncherValues()
	{
		return l.values();
	}

	public Launcher getLauncher(String key)
	{
		Launcher launcher;

		if (l.containsKey(key))
		{
			launcher = l.get(key);
		}
		else
		{
			launcher = null;
		}

		return launcher;
	}

	private void LoadLaunchers(Context context)
	{
		Launcher launcher;

		/* Check In */
		launcher = new Launcher(
				R.drawable.ic_checkin,
			context.getResources().getString(R.string.TitleCheckin),
			context.getResources().getString(R.string.LatitudeCheckinURI),
			context.getResources().getString(R.string.TitleCheckin),
			String.format(
				context.getResources().
					getString(R.string.InstalledAlertMessage),
				context.getResources().getString(R.string.TitleCheckin))
			);
		l.put(LauncherCollection.mapCheckin, launcher);

		/* Places */
		launcher = new Launcher(
				R.drawable.ic_places,
			context.getResources().getString(R.string.TitlePlaces),
			context.getResources().getString(R.string.LatitudePlacesURI),
			context.getResources().getString(R.string.TitlePlaces),
			String.format(
				context.getResources().
					getString(R.string.InstalledAlertMessage),
				context.getResources().getString(R.string.TitlePlaces))
			);
		l.put(LauncherCollection.mapPlaces, launcher);

		/* Friends List */
		launcher = new Launcher(
				R.drawable.ic_list,
			context.getResources().getString(R.string.TitleList),
			context.getResources().getString(R.string.LatitudeListURI),
			context.getResources().getString(R.string.TitleList),
			String.format(
				context.getResources().
					getString(R.string.InstalledAlertMessage),
				context.getResources().getString(R.string.TitleList))
			);
		l.put(LauncherCollection.mapList, launcher);

		/* History */
		launcher = new Launcher(
				R.drawable.ic_history,
			context.getResources().getString(R.string.TitleHistory),
			context.getResources().getString(R.string.LatitudeHistoryURI),
			context.getResources().getString(R.string.TitleHistory),
			String.format(
				context.getResources().
					getString(R.string.InstalledAlertMessage),
				context.getResources().getString(R.string.TitleHistory))
			);
		l.put(LauncherCollection.mapHistory, launcher);
	}
}
