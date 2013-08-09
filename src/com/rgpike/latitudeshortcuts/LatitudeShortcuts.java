package com.rgpike.latitudeshortcuts;

import android.app.TabActivity;

import android.os.Bundle;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import android.content.Intent;

/* Handles the main activity */
public class LatitudeShortcuts extends TabPager
{
	private TabHost mTabHost;
	private TabPager.TabSet[] mTabs;

	@Override
	public TabHost getTabHost()
	{
		return mTabHost;
	}

	@Override
	public TabPager.TabSet[] getTabSet()
	{
		return mTabs;
	}

	@Override
	public int getViewPagerId()
	{
		return R.id.viewpager;
	}

	/** Initialize the tabs used in the app */
	private void initTabs()
	{
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mTabs = new TabPager.TabSet[2];

		mTabs[0] = new TabPager.TabSet();
		mTabs[0].tabSpec = mTabHost.newTabSpec("Launchers");
		mTabs[0].tabSpec.setIndicator(
			getResources().getString(R.string.TabLaunchers));
		mTabs[0].fragmentClass = LaunchersActivity.class;
 
		mTabs[1] = new TabPager.TabSet();
		mTabs[1].tabSpec = mTabHost.newTabSpec("Shortcuts");
		mTabs[1].tabSpec.setIndicator(
			getResources().getString(R.string.TabShortcuts));
		mTabs[1].fragmentClass = ShortcutsActivity.class;
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
