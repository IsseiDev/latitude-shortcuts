package com.rgpike.latitudeshortcuts;

import android.app.TabActivity;

import android.os.Bundle;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import android.content.Intent;

public class LatitudeShortcuts extends TabActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();

        TabSpec launcherspec = tabHost.newTabSpec("Launchers");
        launcherspec.setIndicator("Launchers");
        Intent launchersIntent = new Intent(this, LaunchersActivity.class);
        launcherspec.setContent(launchersIntent);
 
        TabSpec shortcutsspec = tabHost.newTabSpec("Shortcuts");
        shortcutsspec.setIndicator("Shortcuts");
        Intent shortcutsIntent = new Intent(this, ShortcutsActivity.class);
        shortcutsspec.setContent(shortcutsIntent);
 
        tabHost.addTab(launcherspec);
        tabHost.addTab(shortcutsspec);
	}
}
