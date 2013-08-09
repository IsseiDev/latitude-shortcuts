
package com.rgpike.latitudeshortcuts;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/** Presents and processes a widget selection screen */
public class WidgetFragmentWrapper extends FragmentActivity {
    public static final String TAG = "WidgetFragmentWrapper";

	/** Presents a shortcut chooser activity for creating a shortcut */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_wrapper_layout);
    }
}
