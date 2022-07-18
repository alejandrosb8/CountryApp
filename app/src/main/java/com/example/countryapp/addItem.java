package com.example.countryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class addItem extends AppCompatActivity {

    EditText countryText;
    EditText capitalText;
    String list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        countryText = (EditText) findViewById(R.id.addCountryText);
        capitalText = (EditText) findViewById(R.id.addCapitalText);
        Intent intent = getIntent();
        list = intent.getStringExtra("list");
    }

    public void saveItem(View view) {
        String country = countryText.getText().toString().trim();
        String capital = capitalText.getText().toString().trim();

        if (!country.equals("") && !capital.equals("")) {

            try {
                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("items.txt", Activity.MODE_PRIVATE));

                String savedItems = list;

                String newItem;
                if (savedItems.equals("")) {
                    newItem = country + "-" + capital;
                } else {
                    newItem = "_" + country + "-" + capital;
                }

                System.out.println(savedItems + newItem);
                file.write(savedItems + newItem);
                file.flush();
                file.close();
            } catch (IOException e) {

            }

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