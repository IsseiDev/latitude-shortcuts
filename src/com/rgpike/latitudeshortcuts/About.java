package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.text.SpannableString;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class About
{
	public static void ShowDialog(Context context, String title, String message)
	{
		final SpannableString s = new SpannableString(message);
		Linkify.addLinks(s, Linkify.ALL);

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(s);
		alertDialog.setButton(context.getResources().getString(R.string.OK),
			new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
				};
			} );

		alertDialog.show();

		((TextView) alertDialog.findViewById(android.R.id.message)).
			setMovementMethod(LinkMovementMethod.getInstance());
	}
}
