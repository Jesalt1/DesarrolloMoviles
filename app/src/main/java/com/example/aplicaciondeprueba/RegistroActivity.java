package com.example.aplicaciondeprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        TextView var_mensaje = (TextView) findViewById(R.id.messagetxt);
        var_mensaje.setText(message);
    }

}