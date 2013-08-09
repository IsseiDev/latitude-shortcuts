
package com.rgpike.latitudeshortcuts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

public class LauncherCollection {
    public static final String TAG = "LauncherCollection";

    public final static String MAP_CHECKIN = "checkin";

    public final static String MAP_LIST = "list";

    public final static String MAP_PLACES = "places";

    public final static String MAP_HISTORY = "history";

    private Map<String, Launcher> mLauncherCollection = new HashMap<String, Launcher>();

    public LauncherCollection(Context context) {
        LoadLaunchers(context);
    }

    public Collection<Launcher> getLauncherValues() {
        return mLauncherCollection.values();
    }

    public Launcher getLauncher(String key) {
        Launcher launcher;

        if (mLauncherCollection.containsKey(key)) {
            launcher = mLauncherCollection.get(key);
			if (launcher.getLauncherName().equals(key) == false) {
				Log.e(TAG, "Launcher name (" + launcher.getLauncherName() +
					") does not match launcher key (" + key + ")");
			}
        } else {
            launcher = null;
        }

        return launcher;
    }

    private void LoadLaunchers(Context context) {
        Launcher launcher;

        /* Check In */
        launcher = new Launcher(LauncherCollection.MAP_CHECKIN,
				R.drawable.ic_checkin, context.getResources().getString(
                R.string.TitleCheckin), context.getResources().getString(
                R.string.LatitudeCheckinURI), context.getResources().getString(
                R.string.TitleCheckin), String.format(
                context.getResources().getString(R.string.InstalledAlertMessage), context
                        .getResources().getString(R.string.TitleCheckin)));
        mLauncherCollection.put(LauncherCollection.MAP_CHECKIN, launcher);

        /* Places */
        launcher = new Launcher(LauncherCollection.MAP_PLACES,
				R.drawable.ic_places, context.getResources().getString(
                R.string.TitlePlaces),
                context.getResources().getString(R.string.LatitudePlacesURI), context
                        .getResources().getString(R.string.TitlePlaces), String.format(context
                        .getResources().getString(R.string.InstalledAlertMessage), context
                        .getResources().getString(R.string.TitlePlaces)));
        mLauncherCollection.put(LauncherCollection.MAP_PLACES, launcher);

        /* Friends List */
        launcher = new Launcher(LauncherCollection.MAP_LIST,
				R.drawable.ic_list, context.getResources().getString(
                R.string.TitleList), context.getResources().getString(R.string.LatitudeListURI),
                context.getResources().getString(R.string.TitleList), String.format(context
                        .getResources().getString(R.string.InstalledAlertMessage), context
                        .getResources().getString(R.string.TitleList)));
        mLauncherCollection.put(LauncherCollection.MAP_LIST, launcher);

        /* History */
        launcher = new Launcher(LauncherCollection.MAP_HISTORY,
				R.drawable.ic_history, context.getResources().getString(
                R.string.TitleHistory), context.getResources().getString(
                R.string.LatitudeHistoryURI), context.getResources().getString(
                R.string.TitleHistory), String.format(
                context.getResources().getString(R.string.InstalledAlertMessage), context
                        .getResources().getString(R.string.TitleHistory)));
        mLauncherCollection.put(LauncherCollection.MAP_HISTORY, launcher);
    }
}
