
package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

/** Presents and processes a widget selection screen */
public class WidgetChooser extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgets_layout);
    }

    /** Sets a widget/Intent based on view id */
    public void onWidgetClick(View v) {
        Intent i = null;
        LauncherCollection launchers = new LauncherCollection(this);

        switch (v.getId()) {
            case R.id.WidgetCheckinButton:
                i = launchers.getLauncher(LauncherCollection.MAP_CHECKIN).GetShortcut(this);
                break;
            case R.id.WidgetListButton:
                i = launchers.getLauncher(LauncherCollection.MAP_LIST).GetShortcut(this);
                break;
            case R.id.WidgetPlacesButton:
                i = launchers.getLauncher(LauncherCollection.MAP_PLACES).GetShortcut(this);
                break;
            case R.id.WidgetHistoryButton:
                i = launchers.getLauncher(LauncherCollection.MAP_HISTORY).GetShortcut(this);
                break;
        }

        setResult(RESULT_OK, i);

        finish();
    }
}
