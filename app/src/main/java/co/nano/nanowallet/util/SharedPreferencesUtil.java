package co.nano.nanowallet.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import co.nano.nanowallet.model.AvailableCurrency;

/**
 * Shared Preferences utility module
 */
public class SharedPreferencesUtil {
    private static final String LOCAL_CURRENCY = "local_currency";

    private final SharedPreferences mPrefs;

    public SharedPreferencesUtil(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private boolean has(String key) {
        return mPrefs.contains(key);
    }

    private String get(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    private boolean get(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    private void set(String key, String value) {
        SharedPreferences.Editor editor = mPrefs.edit();

        if (value != null) {
            editor.putString(key, value);
        } else {
            editor.remove(key);
        }

        editor.apply();
    }

    private void set(String key, boolean value) {
        SharedPreferences.Editor editor = mPrefs.edit();

        editor.putBoolean(key, value);

        editor.apply();
    }

    public boolean hasLocalCurrency() {
        return has(LOCAL_CURRENCY);
    }

    public AvailableCurrency getLocalCurrency() {
        return AvailableCurrency.valueOf(get(LOCAL_CURRENCY, AvailableCurrency.USD.toString()));
    }

    public void setLocalCurrency(AvailableCurrency localCurrency) {
        set(LOCAL_CURRENCY, localCurrency.toString());
    }

    public void clearLocalCurrency() {
        set(LOCAL_CURRENCY, null);
    }

    public void clearAll() {
        clearLocalCurrency();
    }

}