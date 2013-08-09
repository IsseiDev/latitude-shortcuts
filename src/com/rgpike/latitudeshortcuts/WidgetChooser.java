
package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/* Handles the creation of shortcuts */
public class WidgetChooser extends ShortcutCreatorBase {
    private View mView;

	@Override
	public TextView getSetIconTextView() {
        return (TextView) mView.findViewById(R.id.WidgetSetIconText);
	}

	@Override
	public TextView getCreateShortcutTextView() {
        return (TextView) mView.findViewById(R.id.WidgetCreateShortcutText);
	}

    @Override
    public View getShortcutView() {
        return mView;
    }

    @Override
    public IconButton getSetShortcutButton() {
        return (IconButton)mView.findViewById(R.id.WidgetSetShortcutButton);
    }

    @Override
    public IconButton getSetIconButton() {
        return (IconButton)mView.findViewById(R.id.WidgetSetIconButton);
    }

    @Override
    public Button getCreateShortcutButton() {
        return (Button)mView.findViewById(R.id.WidgetCreateShortcutButton);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.widgets_layout, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    /** Creates and returns a shortcut intent */
    @Override
    public void onCreateShortcutClick(View view) {
        Intent intent = getLauncher().GetShortcut(getActivity());
        if (intent != null) {
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        } else {
            DialogUtils.ShowDialog(getActivity(), 0, getResources().getString(R.string.Error),
                    getResources().getString(R.string.ErrorChooseShortcut));
        }
    }
}
