
package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/* Handles the creation of shortcuts */
public abstract class ShortcutCreatorBase extends Fragment {

    public static final String TAG = "ShortcutCreatorBase";

    public abstract IconButton getSetShortcutButton();

    public abstract IconButton getSetIconButton();

	public abstract TextView getSetIconTextView();

    public abstract Button getCreateShortcutButton();

	public abstract TextView getCreateShortcutTextView();

    public abstract View getShortcutView();

    public abstract void onCreateShortcutClick(View view);

    private LauncherCollection mLaunchers;

    private Launcher mLauncher;

	private Uri mUri;

	private int mTextColor;

	private interface activityCodes {
		public static final int IMAGE = 1;
		public static final int SHORTCUT = 2;
	}

	private interface bundleNames {
		public static final String URI = "uri";
		public static final String LAUNCHER = "launcher";
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLaunchers = new LauncherCollection(getActivity());

		mTextColor = getSetIconTextView().getTextColors().getDefaultColor();

        initializeButtons();

		if (savedInstanceState != null) {
			restoreInstanceState(savedInstanceState);
		}

		updateFragment();
		
        return getShortcutView();
    }

	@Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");
		
		if (mLauncher != null) {
			outState.putString(bundleNames.LAUNCHER, mLauncher.getLauncherName());
			Log.d(TAG, "Launcher saved as: " + mLauncher.getLauncherName());
		}

		if (mUri != null) {
			outState.putParcelable(bundleNames.URI, mUri);
			Log.d(TAG, "Uri saved");
		}

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult()");

        switch (requestCode) {
            case activityCodes.SHORTCUT:
                processGetShortcut(resultCode, data);
                break;
            case activityCodes.IMAGE:
                processGetImage(resultCode, data);
                break;
            default:
                Log.e(TAG, "Unhandled activity result request code: " + requestCode);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onSetShortcutClick(View view) {
        Log.d(TAG, "onSetShortcutClick()");
        Intent shortcutPicker = new Intent(getActivity(), ShortcutPicker.class);
        startActivityForResult(shortcutPicker, activityCodes.SHORTCUT);
    }

    /** Launches an intent to choose a an icon */
    protected void onSetIconClick(View view) {
		if (mUri == null) {
	        startActivityForResult(IconUtils.genGetImageIntent(), activityCodes.IMAGE);
		} else {
			mUri = null;
			getSetIconButton().restoreState();
			mLauncher.setIconScaledBitmap(null);
		}
    }
	
	protected void onCreateShortcutBaseClick(View view) {
		onCreateShortcutClick(view);

		getSetShortcutButton().restoreState();
		getSetIconButton().restoreState();
		
		mLauncher = null;
		mUri = null;
		updateFragment();
	}

	protected LauncherCollection getLauncherCollection() {
		return mLaunchers;
	}
	
	protected Launcher getLauncher() {
		return mLauncher;
	}

	private void restoreInstanceState(Bundle savedInstanceState) {
		String launcherEntry = savedInstanceState.getString(bundleNames.LAUNCHER);
		if (launcherEntry != null) {
			Log.d(TAG, "Restored launcher to: " + launcherEntry);
		}

		mUri = savedInstanceState.getParcelable(bundleNames.URI);
		if (mUri != null) {
			Log.d(TAG, "Restored launcher to: " + launcherEntry);
		}

		mLauncher = mLaunchers.getLauncher(launcherEntry);
	}
	
    /** Processes the result from getting an image */
    private void processGetImage(int resultCode, Intent data) {
		boolean iconUpdated = false;

        if ((data != null) && (resultCode == Activity.RESULT_OK)) {
		    mUri = data.getData();

            if (mUri != null) {
				iconUpdated = updateCustomIcon();
            }
        }

        if ((iconUpdated == false) && (resultCode != Activity.RESULT_CANCELED)) {
            Log.d(TAG, "Could not create icon");
            DialogUtils.ShowDialog(getActivity(), 0,
                    getActivity().getResources().getString(R.string.Error), getActivity()
                            .getResources().getString(R.string.ErrorIcon));
        }
    }

    /** Processes the result of the shortcut selection activity */
    private void processGetShortcut(int resultCode, Intent data) {
        mLauncher = null;

        if ((data != null) && (resultCode == Activity.RESULT_OK)) {
            String launcherEntry;
			Bundle bundle = data.getExtras();
            int clickedButton = bundle.getInt("shortcut");
			Log.d(TAG, "Shortcut" + clickedButton + " was picked");

            switch (clickedButton) {
                case R.id.WidgetCheckinButton:
                    launcherEntry = LauncherCollection.MAP_CHECKIN;
                    break;
                case R.id.WidgetListButton:
                    launcherEntry = LauncherCollection.MAP_LIST;
                    break;
                case R.id.WidgetPlacesButton:
                    launcherEntry = LauncherCollection.MAP_PLACES;
                    break;
                case R.id.WidgetHistoryButton:
                    launcherEntry = LauncherCollection.MAP_HISTORY;
                    break;
                default:
                    Log.e(TAG, "Unhandled button click: " + clickedButton);
					launcherEntry = null;
					mLauncher = null;
                    break;
            }

			mLauncher = mLaunchers.getLauncher(launcherEntry);
			updateButtons();
			updateHelpText();
        }

        if ((mLauncher == null) && (resultCode != Activity.RESULT_CANCELED)) {
            DialogUtils.ShowDialog(getActivity(), 0,
                    getActivity().getResources().getString(R.string.Error), getActivity()
                            .getResources().getString(R.string.ErrorShortcut));
        }
    }

	private void updateButtons() {
		if (mLauncher != null) {
			/* Set button icon to shortcut icon */
			int icon = mLauncher.getIconResource();
			Drawable drawable = getActivity().getResources().getDrawable(icon);
			getSetShortcutButton().setIconFromDrawable(drawable);

			/* Set icon button title to selected shortcut name */
			String title = mLauncher.getIconTitle();
			getSetShortcutButton().setText(title);

			/* Enable set icon and create shortcut buttons */
			getSetIconButton().setEnabled(true);
			getCreateShortcutButton().setEnabled(true);
		} else {
			getSetIconButton().setEnabled(false);
			getCreateShortcutButton().setEnabled(false);
		}
	}

	private boolean updateCustomIcon() {
		Bitmap bitmap = null;

		if (mUri != null) {
			bitmap = IconUtils.getBitmapFromUri(getActivity(), mUri);

			if (bitmap != null) {
				bitmap = IconUtils.getIconScaledBitmap(getActivity(), bitmap);
				
				if (bitmap != null) {
					mLauncher.setIconScaledBitmap(bitmap);
					getSetIconButton().setIconFromBitmap(bitmap);
					getSetIconButton().setText(getActivity().getResources().getString(R.string.WidgetUnsetIconText));
				}
			}
		}

		return (bitmap == null) ? false : true;
	}

	private void updateHelpText() {
		int color;

		if (mLauncher != null) {
			color = mTextColor;
		} else {
			color = Color.DKGRAY;
		}

		getSetIconTextView().setTextColor(color);
		getCreateShortcutTextView().setTextColor(color);
	}

	private void updateFragment() {
		updateButtons();
		updateHelpText();
		updateCustomIcon();
	}
	
    /** Sets up the button call backs to come to this fragment */
    private void initializeButtons() {
        Button button;

        /* Set up the shortcut selection button */
        button = getSetShortcutButton();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onSetShortcutClick(view);
            }
        });
		((IconButton) button).saveState();

        /* Set up the icon button, then disable it */
        button = getSetIconButton();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onSetIconClick(view);
            }
        });
		((IconButton) button).saveState();

        /* Set up the create shortcut button, then disable it */
        button = getCreateShortcutButton();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateShortcutBaseClick(view);
            }
        });
    }
}
