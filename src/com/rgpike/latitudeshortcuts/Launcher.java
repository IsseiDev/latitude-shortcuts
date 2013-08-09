package com.rgpike.latitudeshortcuts;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Context;

import android.net.Uri;

import android.app.AlertDialog;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.text.SpannableString;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Launcher
{
	private int iconRes;
	private String iconTitle;
	private String intentUri;
	private String alertTitle;
	private String alertMessage;

	public Launcher(int iconRes, String iconTitle, String intentUri, String alertTitle, String alertMessage)
	{
		setIconResource(iconRes);
		setIconTitle(iconTitle);
		setIntentUri(intentUri);
		setAlertTitle(alertTitle);
		setAlertMessage(alertMessage);
	}

	public int setIconResource(int r)
	{
		iconRes = r;
		return iconRes;
	}

	public int getIconResource()
	{
		return iconRes;
	}

	public String setIconTitle(String r)
	{
		iconTitle = r;
		return iconTitle;
	}

	public String getIconTitle()
	{
		return iconTitle;
	}

	public String setIntentUri(String r)
	{
		intentUri = r;
		return intentUri;
	}

	public String getIntentUri()
	{
		return intentUri;
	}

	public String setAlertTitle(String r)
	{
		alertTitle = r;
		return alertTitle;
	}

	public String getAlertTitle()
	{
		return alertTitle;
	}

	public String setAlertMessage(String r)
	{
		alertMessage = r;
		return alertMessage;
	}

	public String getAlertMessage()
	{
		return alertMessage;
	}

	public void LaunchIntent(Context context)
	{
		Intent intent = new
			Intent(Intent.ACTION_VIEW,
			Uri.parse(intentUri));
		context.startActivity(intent);
	}

	public void CreateShortcut(Context context)
	{
		Intent shortcut = new
			Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, iconTitle);
		shortcut.putExtra("duplicate", false);

		Intent intent = new Intent("android.intent.action.VIEW",
			Uri.parse(intentUri));
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
			intent);

		ShortcutIconResource icon =
			Intent.ShortcutIconResource.fromContext(context, iconRes);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
			icon);

		context.sendBroadcast(shortcut);
	}

	public void ShowDialog(Context context)
	{
		final SpannableString s = new SpannableString(alertMessage);
		Linkify.addLinks(s, Linkify.ALL);

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(iconTitle);
		alertDialog.setMessage(s);
		alertDialog.setIcon(iconRes);
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
