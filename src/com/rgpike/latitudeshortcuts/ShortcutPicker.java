
package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/** Presents a view to allow choosing a shortcut */
public class ShortcutPicker extends Activity {
    public static final String TAG = "ShortcutPicker";

	/** Loads and presents the shortcut picker */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shortcut_picker_layout);
    }

    /** Returns a result intent with the chosen shortcut */
    public void onWidgetClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("shortcut", view.getId());
        setResult(RESULT_OK, intent);

        finish();
    }
}
