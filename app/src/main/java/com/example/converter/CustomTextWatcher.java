package com.example.converter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class CustomTextWatcher implements TextWatcher {

    private MainActivity mainActivity;

    public CustomTextWatcher(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s) {
        mainActivity.changeText(s.toString());
    }
}
