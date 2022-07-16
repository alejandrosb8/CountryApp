package com.example.countryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchItem(View view){

        System.out.println("HOLA");

        AlertDialog dialog = createSimpleDialog();
        dialog.show();

    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Agregar país o capital")
                .setMessage("El país o capital que se ingreso no existe, ¿lo quiere agregar?")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = (EditText) findViewById(R.id.searchText);
                                String text = editText.getText().toString();
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
}