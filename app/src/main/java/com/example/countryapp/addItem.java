package com.example.countryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SymbolTable;
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
        String country = lowerCase(countryText.getText().toString().trim());
        country = upperCaseFirst(country);
        String capital = lowerCase(capitalText.getText().toString().trim());
        capital = upperCaseFirst(capital);
        boolean itemExist = false;

        String[] items = list.split("_");
        for (String item : items) {
            String[] setOfItem = item.split("-");
            if (setOfItem[0].equals(country) && setOfItem[1].equals(capital)) {
                itemExist = true;
            }
        }

        if (!itemExist && !country.equals("") && !capital.equals("")) {
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

    private String lowerCase(String string) {
        char[] array = string.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = Character.toLowerCase(array[i]);
        }
        return new String(array);
    }

    private String upperCaseFirst(String string) {
        char[] array = string.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }
}