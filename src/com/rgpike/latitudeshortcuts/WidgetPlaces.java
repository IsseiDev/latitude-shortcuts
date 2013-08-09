package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class WidgetPlaces extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchers_layout);

		LauncherCollection launchers = new LauncherCollection(this);

		Intent i = launchers.getLauncher(
			LauncherCollection.mapPlaces).GetShortcut(this);

		setResult(RESULT_OK, i);

		finish();
	}
}
