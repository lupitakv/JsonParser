package com.example.juancastro.jsonparser;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class vista_individual extends Activity {
    static final String KEY_ID = "id";
    static final String KEY_NOMBRE_COMUN = "nombre_comun";
    static final String KEY_NOMBRE_CIENT = "nombre_cient";
    static final String KEY_HABITAT = "habitat";
    static final String KEY_CARACTERISTICAS = "caracteristicas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        Intent in = getIntent();

        // Get XML values from previous intent
        String id = in.getStringExtra(KEY_ID);
        String ncomun = in.getStringExtra(KEY_NOMBRE_COMUN);
        String ncient = in.getStringExtra(KEY_NOMBRE_CIENT);
        String habit = in.getStringExtra(KEY_HABITAT);
        String caract = in.getStringExtra(KEY_CARACTERISTICAS);


        // Displaying all values on the screen
        TextView lbl_ida = (TextView) findViewById(R.id.id);
        TextView lblncomun = (TextView) findViewById(R.id.nombre_comun);
        TextView lblncient = (TextView) findViewById(R.id.nombre_cient);
        TextView lblhabit = (TextView) findViewById(R.id.habitat);
        TextView lblcaract = (TextView) findViewById(R.id.caracteristicas);

        lbl_ida.setText(id);
        lblncomun.setText(ncomun);
        lblncient.setText(ncient);
        lblhabit.setText(habit);
        lblcaract.setText(caract);

    }


}


