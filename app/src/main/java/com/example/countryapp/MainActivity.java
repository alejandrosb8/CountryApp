package com.example.countryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText searchText;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.searchText);
        outputText = (TextView) findViewById(R.id.outputText);

        loadItemDefault();
    }

    @SuppressLint("SetTextI18n")
    public void searchItem(View view){

        String [] items = readFile().split("_");
        String country = "";
        String capital = "";

        boolean itemExist = false;

        if (items.length > 1){
            for (String item : items){
                String [] setOfItem = item.split("-");
                for (int i = 0; i < 2; i++){
                    if (searchText.getText().toString().trim().equalsIgnoreCase(setOfItem[i])){
                        itemExist = true;;
                        country = setOfItem[0];
                        capital = setOfItem[1];
                        break;
                    }
                }
            }
        }

        if (!itemExist){
            AlertDialog dialog = createSimpleDialog();
            dialog.show();
        } else {
            outputText.setText(capital + " es la capital de " + country);
        }

    }

    public String readFile() {
        String files[] = fileList();
        if (fileExist(files, "items.txt")) {
            try {
                InputStreamReader file = new InputStreamReader(openFileInput("items.txt"));
                BufferedReader br = new BufferedReader(file);
                String line = br.readLine();

                br.close();
                file.close();
                return line;

            } catch (IOException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    public void loadItemDefault() {
        String country = "Venezuela";
        String capital = "Caracas";
        String country2 = "Francia";
        String capital2 = "Paris";

        String [] items = readFile().split("_");

        if (items.length < 2){
            try {
                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("items.txt", Activity.MODE_PRIVATE));


                file.write(country + "-" + capital + "_" + country2 + "-" + capital2);
                file.flush();
                file.close();
            } catch (IOException e) {

            }
        }
    }


    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Agregar país o capital")
                .setMessage("El país o capital que se ingreso no existe, ¿lo quiere agregar?")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                openAddItem(readFile());
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        return builder.create();
    }

    void openAddItem(String list){
        Intent intent = new Intent(this, addItem.class);
        intent.putExtra("list", list);
        startActivity(intent);
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