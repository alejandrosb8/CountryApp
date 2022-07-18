package com.example.countryapp;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Storage extends AppCompatActivity {

    public void saveFile(String country, String capital) {
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("items.txt", Activity.MODE_PRIVATE));

            String savedItems = readFile();

            String newItem;
            if (savedItems.equals("")) {
                newItem = country + "-" + capital;
            } else {
                newItem = "_" + country + "-" + capital;
            }

            file.write(savedItems + newItem);
            file.flush();
            file.close();
        } catch (IOException e) {

        }
    }

    public String readFile() {
        String files[] = fileList();
        if (fileExist(files, "items.txt")) {
            try {
                InputStreamReader file = new InputStreamReader(openFileInput("items.txt"));
                BufferedReader br = new BufferedReader(file);
                String line = br.readLine();
                String items = "";

                while (line != null) {
                    items += line + "_";
                    line = br.readLine();
                    System.out.println(line);
                }

                br.close();
                file.close();

                return items;

            } catch (IOException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    // Metodo para comprobar que un archivo fichero existe.
    private boolean fileExist(String files[], String name) {
        for (int i = 0; i < files.length; i++) {
            if (name.equals(files[i])) {
                return true;
            }
        }
        return false;
    }
}
