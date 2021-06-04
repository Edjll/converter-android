package com.example.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private TextView textViewMeterValue;
    private TextView textViewKilometerValue;
    private TextView textViewCentimeterValue;
    private TextView textViewMileValue;
    private TextView textViewFootValue;
    private TextView textViewInchValue;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    private Spinner spinner;
    private Type inputType;

    private CustomDecimalFormat decimalFormat = CustomDecimalFormat.EN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
        sharedPreferencesEditor = this.sharedPreferences.edit();
        this.decimalFormat = CustomDecimalFormat.valueOf(this.sharedPreferences.getString("decimalFormat", "EN"));

        this.inputType = Type.METER;

        this.editText = findViewById(R.id.editText);
        this.editText.addTextChangedListener(new CustomTextWatcher(this));

        this.textViewMeterValue = findViewById(R.id.textViewMeterValue);
        this.textViewKilometerValue = findViewById(R.id.textViewKilometerValue);
        this.textViewCentimeterValue = findViewById(R.id.textViewCentimeterValue);
        this.textViewMileValue = findViewById(R.id.textViewMileValue);
        this.textViewFootValue = findViewById(R.id.textViewFootValue);
        this.textViewInchValue = findViewById(R.id.textViewInchValue);

        this.spinner = findViewById(R.id.spinnerLanguage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner.setAdapter(adapter);

        this.spinner.setSelection(0, false);
        this.spinner.setOnItemSelectedListener(new ItemSelectedListener(this));

        this.changeText(editText.getText().toString());
    }

    public void changeText(String string) {
        String str = string;

        if (str.length() == 0) str = "0";

        double value = Double.parseDouble(str);

        double meters = inputType.convertToMeter(value);

        this.textViewMeterValue.setText(decimalFormat.format(meters));
        this.textViewKilometerValue.setText(decimalFormat.format(Converter.convertToKilometer(meters)));
        this.textViewCentimeterValue.setText(decimalFormat.format(Converter.convertToCentimeter(meters)));
        this.textViewMileValue.setText(decimalFormat.format(Converter.convertToMile(meters)));
        this.textViewFootValue.setText(decimalFormat.format(Converter.convertToFoot(meters)));
        this.textViewInchValue.setText(decimalFormat.format(Converter.convertToInch(meters)));
    }

    public void onChecked(View view) {
        switch (view.getId()) {
            case R.id.radioButtonMeter:
                this.inputType = Type.METER;
                break;
            case R.id.radioButtonCentimeter:
                this.inputType = Type.CENTIMETER;
                break;
            case R.id.radioButtonKilometer:
                this.inputType = Type.KILOMETER;
                break;
            case R.id.radioButtonFoot:
                this.inputType = Type.FOOT;
                break;
            case R.id.radioButtonMile:
                this.inputType = Type.MILE;
                break;
            case R.id.radioButtonInch:
                this.inputType = Type.INCH;
                break;
        }
        this.changeText(this.editText.getText().toString());
    }

    public void changeLanguage(String language) {
        Locale locale = null;

        switch (language) {
            case "Russian":
                locale = new Locale("ru");
                this.sharedPreferencesEditor.putString("decimalFormat", CustomDecimalFormat.RU.name());
                this.sharedPreferencesEditor.commit();
                break;
            case "Английский":
                locale = new Locale("en");
                this.sharedPreferencesEditor.putString("decimalFormat", CustomDecimalFormat.EN.name());
                this.sharedPreferencesEditor.commit();
                break;
        }

        if (locale == null) return;

        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        recreate();
    }
}
