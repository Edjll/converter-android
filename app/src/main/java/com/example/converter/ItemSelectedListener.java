package com.example.converter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private MainActivity mainActivity;

    public ItemSelectedListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0 && view != null) {
            TextView textView = (TextView) view;
            mainActivity.changeLanguage(textView.getText().toString());
            parent.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
