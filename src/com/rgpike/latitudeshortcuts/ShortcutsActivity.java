
package com.rgpike.latitudeshortcuts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/* Handles the creation of shortcuts */
public class ShortcutsActivity extends ShortcutCreatorBase {
    public static final String TAG = "ShortcutsActivity";

    private View mView;

	@Override
	public TextView getSetIconTextView() {
        return (TextView) mView.findViewById(R.id.ShortcutsSetIconText);
	}

	@Override
	public TextView getCreateShortcutTextView() {
        return (TextView) mView.findViewById(R.id.ShortcutsCreateShortcutText);
	}

    @Override
    public View getShortcutView() {
        return mView;
    }

    @Override
    public IconButton getSetShortcutButton() {
        return (IconButton)mView.findViewById(R.id.ShortcutsSetShortcutButton);
    }

    @Override
    public IconButton getSetIconButton() {
        return (IconButton)mView.findViewById(R.id.ShortcutsSetIconButton);
    }

    @Override
    public Button getCreateShortcutButton() {
        return (Button)mView.findViewById(R.id.ShortcutsCreateShortcutButton);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        mView = inflater.inflate(R.layout.shortcuts_layout, container, false);

        /* Set create all button callback */
        Button button = (Button)mView.findViewById(R.id.CreateAllButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateAllClick(view);
            }
        });

        /* Parent sets up other buttons */
        super.onCreateView(inflater, container, savedInstanceState);

        return mView;
    }

    /** Creates all the shortcuts on the home screen */
    public void onCreateAllClick(View v) {
        for (Launcher l : getLauncherCollection().getLauncherValues()) {
            l.CreateShortcut(getActivity());
        }

        DialogUtils.ShowDialog(getActivity(), R.drawable.ic_launcher,
                getResources().getString(R.string.TitleAll),
                getResources().getString(R.string.AllAlertText));
    }

    /** Creates and returns a shortcut intent */
    @Override
    public void onCreateShortcutClick(View view) {
        if (getLauncher() != null) {
            getLauncher().CreateShortcut(getActivity());
            getLauncher().ShowDialog(getActivity());
        } else {
            DialogUtils.ShowDialog(getActivity(), 0,
                    getActivity().getResources().getString(R.string.Error), getActivity()
                            .getResources().getString(R.string.ErrorChooseShortcut));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");

        super.onSaveInstanceState(outState);
    }
}
