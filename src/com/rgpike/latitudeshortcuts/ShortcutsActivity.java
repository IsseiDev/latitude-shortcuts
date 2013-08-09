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

/* Handles the creation of shortcuts */
public class ShortcutsActivity extends Fragment
{
	private LauncherCollection mLaunchers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		mLaunchers = new LauncherCollection(getActivity());

		View v = (LinearLayout) inflater.inflate(R.layout.shortcuts_layout,
			container, false);

		setButtonCallbacks(v);

		return v;
	}

	/** Sets up the button callbacks to come to this fragment */
	private void setButtonCallbacks(View v)
	{
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
	}

	/* Creates a shortcut based on the ID of the view */
	public void onCreateShortcutClick(View v)
	{
		switch (v.getId())
			{
			case R.id.CreateCheckInButton:
				mLaunchers.getLauncher(LauncherCollection.MAP_CHECKIN).
					CreateShortcut(getActivity());
				mLaunchers.getLauncher(LauncherCollection.MAP_CHECKIN).
					ShowDialog(getActivity());
				break;
			case R.id.CreateHistoryButton:
				mLaunchers.getLauncher(LauncherCollection.MAP_HISTORY).
					CreateShortcut(getActivity());
				mLaunchers.getLauncher(LauncherCollection.MAP_HISTORY).
					ShowDialog(getActivity());
				break;
			case R.id.CreatePlacesButton:
				mLaunchers.getLauncher(LauncherCollection.MAP_PLACES).
					CreateShortcut(getActivity());
				mLaunchers.getLauncher(LauncherCollection.MAP_PLACES).
					ShowDialog(getActivity());
				break;
			case R.id.CreateListButton:
				mLaunchers.getLauncher(LauncherCollection.MAP_LIST).
					CreateShortcut(getActivity());
				mLaunchers.getLauncher(LauncherCollection.MAP_LIST).
					ShowDialog(getActivity());
				break;
			}
	}

	/** Creates all the shortcuts on the home screen */
	public void onCreateAllClick(View v)
	{
		for (Launcher l : mLaunchers.getLauncherValues())
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

	/** Shows the About dialog */
    public void onAboutClick(View v)
    {
		About.onAboutClick(getActivity(), v);
	}
}
