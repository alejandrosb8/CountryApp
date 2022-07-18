package com.example.countryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addItem extends AppCompatActivity {

    EditText countryText;
    EditText capitalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        countryText = (EditText) findViewById(R.id.addCountryText);
        capitalText = (EditText) findViewById(R.id.addCapitalText);
    }

    public void saveItem(View view) {
        String country = countryText.getText().toString();
        String capital = capitalText.getText().toString();

        if (!country.equals("") && !capital.equals("")) {
            Storage storage = new Storage();
            storage.saveFile(country, capital);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void exchangeTexts(View view) {
        String aux = countryText.getText().toString();
        countryText.setText(capitalText.getText().toString());
        capitalText.setText(aux);
    }
}