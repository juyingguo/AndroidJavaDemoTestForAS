package com.sp.spmultipleapp.fragment;


import android.os.Bundle;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.spmultipleapp.R;

/**
 *
 */
public class PreferenceSetTestFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private CheckBoxPreference m_prefCheckBoxTwo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preference_test_config);
//        mUptime = findPreference("up_time");
        m_prefCheckBoxTwo=(CheckBoxPreference) findPreference("TestCheckBoxTwo");
        m_prefCheckBoxTwo.setOnPreferenceChangeListener(this);
    }
    @Override
    public boolean onPreferenceClick(Preference preference) {
        // TODO Auto-generated method stub

        return false;
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if (preference == m_prefCheckBoxTwo){
            m_prefCheckBoxTwo.setSummary("checked or no:"+m_prefCheckBoxTwo.isChecked());
        }
        return true;  //保存更新后的值


    }
}