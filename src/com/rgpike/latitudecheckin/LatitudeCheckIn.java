package com.rgpike.latitudecheckin;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.ComponentName;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class LatitudeCheckIn extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent shortcut = new
            Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
            "Check In");
        shortcut.putExtra("duplicate", false);  // Just create once

        Intent intent = new Intent("android.intent.action.VIEW",
            Uri.parse("latitude://latitude/checkin"));
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
            intent);

        ShortcutIconResource iconRes =
            Intent.ShortcutIconResource.fromContext(this,
            R.drawable.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        sendBroadcast(shortcut);

        setContentView(R.layout.main);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Shortcut Created");
        alertDialog.setMessage("Your shortcut is named \"Check In\"");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                    {
                    finish();
                    };

            } );

        alertDialog.show();
    }
}
