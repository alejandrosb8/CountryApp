package com.example.countryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText searchText;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.searchText);
        outputText = (TextView) findViewById(R.id.outputText);
    }

    @SuppressLint("SetTextI18n")
    public void searchItem(View view){

        String [] items = readFile();
        String country = "";
        String capital = "";

        boolean itemExist = false;

        for (String item : items){
            String [] setOfItem = item.split("-");
            for (int i = 0; i < 2; i++){
                if (searchText.getText().toString().equalsIgnoreCase(setOfItem[i])){
                    itemExist = true;
                    country = setOfItem[0];
                    capital = setOfItem[1];
                    break;
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

    String [] readFile() {
        String [] nullArray = {""};
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
                String itemsArray[] = items.split("_");

                br.close();
                file.close();

                return itemsArray;

            } catch (IOException e) {

            }
        } else {
            return nullArray;
        }
        return nullArray;
    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Agregar país o capital")
                .setMessage("El país o capital que se ingreso no existe, ¿lo quiere agregar?")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = searchText.getText().toString();
                                openAddItem(text);
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

    void openAddItem(String text){
        Intent intent = new Intent(this, addItem.class);
        intent.putExtra("item", text);
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