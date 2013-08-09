package com.rgpike.latitudeshortcuts;

import android.app.TabActivity;

import android.os.Bundle;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import android.content.Intent;

public class LatitudeShortcuts extends TabPager
{
	private TabHost tabHost;
	private TabPager.TabSet[] tabs;

	@Override
	public TabHost getTabHost()
	{
		return tabHost;
	}

	@Override
	public TabPager.TabSet[] getTabSet()
	{
		return tabs;
	}

	@Override
	public int getViewPagerId()
	{
		return R.id.viewpager;
	}

	private void initTabs()
	{
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		tabs = new TabPager.TabSet[2];

		tabs[0] = new TabPager.TabSet();
		tabs[0].tabSpec = tabHost.newTabSpec("Launchers");
		tabs[0].tabSpec.setIndicator(
			getResources().getString(R.string.TabLaunchers));
		tabs[0].fragmentClass = LaunchersActivity.class;
 
		tabs[1] = new TabPager.TabSet();
		tabs[1].tabSpec = tabHost.newTabSpec("Shortcuts");
		tabs[1].tabSpec.setIndicator(
			getResources().getString(R.string.TabShortcuts));
		tabs[1].fragmentClass = ShortcutsActivity.class;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initTabs();
		super.startTabPager();
	}
}
