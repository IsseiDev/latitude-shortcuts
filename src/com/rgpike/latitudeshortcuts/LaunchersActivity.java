package com.rgpike.latitudeshortcuts;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.Button;

import android.support.v4.app.Fragment;

public class LaunchersActivity extends Fragment
{
	private LauncherCollection launchers;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		launchers = new LauncherCollection(getActivity());

		View v = (LinearLayout) inflater.inflate(R.layout.launchers_layout,
			container, false);

		for (int i :
			new int[]
			{
				R.id.LaunchCheckinButton,
				R.id.LaunchListButton,
				R.id.LaunchPlacesButton,
				R.id.LaunchHistoryButton
			})
		{
			Button button = (Button) v.findViewById(i);
			button.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						onLaunchClick(v);
					}
				});
		}

		Button button = (Button) v.findViewById(R.id.AboutButton);
		button.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					onAboutClick(v);
				}
			});

		return v;
	}

	public void onLaunchClick(View v)
	{
		switch (v.getId())
			{
			case R.id.LaunchCheckinButton:
				launchers.getLauncher(
					LauncherCollection.mapCheckin).LaunchIntent(getActivity());
				break;
			case R.id.LaunchListButton:
				launchers.getLauncher(
					LauncherCollection.mapList).LaunchIntent(getActivity());
				break;
			case R.id.LaunchPlacesButton:
				launchers.getLauncher(
					LauncherCollection.mapPlaces).LaunchIntent(getActivity());
				break;
			case R.id.LaunchHistoryButton:
				launchers.getLauncher(
					LauncherCollection.mapHistory).LaunchIntent(getActivity());
				break;
			}
	}

    public void onAboutClick(View v)
    {
		About.onAboutClick(getActivity(), v);
	}
}
