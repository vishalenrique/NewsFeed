package com.example.com.newsfeed;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Setting_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
    }
    public static class SettingPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_layout);
            Preference preference=findPreference(getString(R.string.settings_order_by_key));
            bind(preference);
        }

        private void bind(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String s= (String) newValue;
            ListPreference listPreference= (ListPreference) preference;
            int index=listPreference.findIndexOfValue(s);
            CharSequence[] charSequence=listPreference.getEntries();
            preference.setSummary(charSequence[index]);
            return true;
        }
    }
}
