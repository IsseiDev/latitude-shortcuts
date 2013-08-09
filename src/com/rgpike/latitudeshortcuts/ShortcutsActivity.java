package com.rgpike.latitudeshortcuts;

import android.app.Activity;

import android.os.Bundle;

import android.view.Window;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.Button;

import android.support.v4.app.Fragment;

public class ShortcutsActivity extends Fragment
{
	private LauncherCollection launchers;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		launchers = new LauncherCollection(getActivity());

		View v = (LinearLayout) inflater.inflate(R.layout.shortcuts_layout,
			container, false);

		for (int i :
			new int[]
			{
				R.id.CreateCheckInButton,
				R.id.CreateListButton,
				R.id.CreatePlacesButton,
				R.id.CreateHistoryButton
			})
		{
			Button button = (Button) v.findViewById(i);
			button.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						onCreateShortcutClick(v);
					}
				});
		}

		Button button = (Button) v.findViewById(R.id.CreateAllButton);
		button.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					onCreateAllClick(v);
				}
			});

		button = (Button) v.findViewById(R.id.AboutButton);
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

	public void onCreateShortcutClick(View v)
	{
		switch (v.getId())
			{
			case R.id.CreateCheckInButton:
				launchers.getLauncher(LauncherCollection.mapCheckin).
					CreateShortcut(getActivity());
				launchers.getLauncher(LauncherCollection.mapCheckin).
					ShowDialog(getActivity());
				break;
			case R.id.CreateHistoryButton:
				launchers.getLauncher(LauncherCollection.mapHistory).
					CreateShortcut(getActivity());
				launchers.getLauncher(LauncherCollection.mapHistory).
					ShowDialog(getActivity());
				break;
			case R.id.CreatePlacesButton:
				launchers.getLauncher(LauncherCollection.mapPlaces).
					CreateShortcut(getActivity());
				launchers.getLauncher(LauncherCollection.mapPlaces).
					ShowDialog(getActivity());
				break;
			case R.id.CreateListButton:
				launchers.getLauncher(LauncherCollection.mapList).
					CreateShortcut(getActivity());
				launchers.getLauncher(LauncherCollection.mapList).
					ShowDialog(getActivity());
				break;
			}
	}

	public void onCreateAllClick(View v)
	{
		for (Launcher l : launchers.getLauncherValues())
		{
			l.CreateShortcut(getActivity());
		}

		About.ShowDialog(
			getActivity(),
			R.drawable.ic_launcher,
			getResources().getString(R.string.TitleAll),
			getResources().getString(R.string.AllAlertText)
			);
	}

    public void onAboutClick(View v)
    {
		About.onAboutClick(getActivity(), v);
	}
}
