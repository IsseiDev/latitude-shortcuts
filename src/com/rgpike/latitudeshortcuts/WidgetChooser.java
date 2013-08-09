package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class WidgetChooser extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgets_layout);
	}

	public void onWidgetClick(View v)
	{
		Intent i = null;
		LauncherCollection launchers = new LauncherCollection(this);

		switch (v.getId())
			{
			case R.id.WidgetCheckinButton:
				i = launchers.getLauncher(
					LauncherCollection.mapCheckin).GetShortcut(this);
				break;
			case R.id.WidgetListButton:
				i = launchers.getLauncher(
					LauncherCollection.mapList).GetShortcut(this);
				break;
			case R.id.WidgetPlacesButton:
				i = launchers.getLauncher(
					LauncherCollection.mapPlaces).GetShortcut(this);
				break;
			case R.id.WidgetHistoryButton:
				i = launchers.getLauncher(
					LauncherCollection.mapHistory).GetShortcut(this);
				break;
			}

		setResult(RESULT_OK, i);

		finish();
	}
}
