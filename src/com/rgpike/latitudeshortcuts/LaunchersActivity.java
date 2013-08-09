package com.rgpike.latitudeshortcuts;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.Button;

import android.support.v4.app.Fragment;

/* Handles launching of Latitude intents */
public class LaunchersActivity extends Fragment
{
	private LauncherCollection mLaunchers;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		mLaunchers = new LauncherCollection(getActivity());

		View v = (LinearLayout) inflater.inflate(R.layout.launchers_layout,
			container, false);

		setButtonCallbacks(v);

		return v;
	}

	/* Sets the button clicks to come to this fragment */
	private void setButtonCallbacks(View v)
	{
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

	}

	/** Launch a feature based on the view ID */
	public void onLaunchClick(View v)
	{
		switch (v.getId())
			{
			case R.id.LaunchCheckinButton:
				mLaunchers.getLauncher(
					LauncherCollection.MAP_CHECKIN).LaunchIntent(getActivity());
				break;
			case R.id.LaunchListButton:
				mLaunchers.getLauncher(
					LauncherCollection.MAP_LIST).LaunchIntent(getActivity());
				break;
			case R.id.LaunchPlacesButton:
				mLaunchers.getLauncher(
					LauncherCollection.MAP_PLACES).LaunchIntent(getActivity());
				break;
			case R.id.LaunchHistoryButton:
				mLaunchers.getLauncher(
					LauncherCollection.MAP_HISTORY).LaunchIntent(getActivity());
				break;
			}
	}

	/** Show the About dialog */
    public void onAboutClick(View v)
    {
		About.onAboutClick(getActivity(), v);
	}
}
