package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;
import java.util.Vector;

import android.content.Context;

import android.view.View;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.TabContentFactory;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;

import android.util.Log;

public abstract class TabPager extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener
{
	public class TabSet
	{
		public TabSpec tabSpec;
		public Class fragmentClass;
	}

	private static final String TAG = "TabPager";

	public abstract TabHost getTabHost();
	public abstract TabSet[] getTabSet();
	public abstract int getViewPagerId();

	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;

	private TabSpec lastTabUsed = null;

	private class PagerAdapter extends FragmentPagerAdapter
	{
		private List<Fragment> fragments;

		public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position)
		{
			return fragments.get(position);
		}

		@Override
		public int getCount()
		{
			return fragments.size();
		}
	}

	private class TabFactory implements TabContentFactory
	{
		Context context;

		public TabFactory(Context context)
		{
			this.context = context;
		}

		public View createTabContent(String s)
		{
			return new View(context);
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
    }

	public void startTabPager()
	{
		List<Fragment> fragments = new Vector<Fragment>();
		FragmentManager fm;
		fm = getSupportFragmentManager();

		for (TabSet tab : getTabSet())
		{
			TabSpec tabSpec = tab.tabSpec;
			tabSpec.setContent(new TabFactory(this));

			Fragment f = fm.findFragmentByTag(tabSpec.getTag());
			if (f != null)
			{
				if (f.isDetached() == false)
				{
					// Detach
					FragmentTransaction ft =
						getSupportFragmentManager().beginTransaction();
					ft.detach(f);
					ft.commit();
					getSupportFragmentManager().executePendingTransactions();
				}
			}

			getTabHost().addTab(tabSpec);

			f = Fragment.instantiate(this, tab.fragmentClass.getName());

			fragments.add(f);
		}

		pagerAdapter = new PagerAdapter(
			getSupportFragmentManager(), fragments);

		viewPager = (ViewPager) findViewById(getViewPagerId());
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOnPageChangeListener(this);
	
		Log.d(TAG, "All TabSpecs added to TabHost");

		getTabHost().setOnTabChangedListener(this);
	}

	public void onTabChanged(String tag)
	{
		int pos = getTabHost().getCurrentTab();
		viewPager.setCurrentItem(pos);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
	}

	@Override
	public void onPageSelected(int position)
	{
		getTabHost().setCurrentTab(position);
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
	}
}
