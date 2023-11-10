package com.example.aplicaciondeprueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegistroActivity2 extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 1;

    TextView cedula;
    TextView nombre;
    TextView apellido;
    TextView edad;
    RatingBar ratingbart_tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);


      cedula = findViewById(R.id.numCedula);
      nombre = findViewById(R.id.txtnombre);
      apellido = findViewById(R.id.txtApellido);
      edad = findViewById(R.id.numEdad);
      ratingbart_tmp = findViewById(R.id.rtbarIngles);



        // Verifica si el permiso ya está concedido
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // El permiso ya está concedido
            // Puedes realizar la acción que requiere el permiso aquí
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();


        } else {
            // El permiso no está concedido, solicítalo
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
        }

        StateSD();



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_STORAGE) {
            // Verifica si el permiso fue concedido
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realiza la acción
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();

            } else {
                // Permiso denegado, muestra un mensaje o toma alguna acción
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show();

            }
        }
    }


    private int StateSD()
    {
        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(this, "memoria montada", Toast.LENGTH_LONG).show();
            return 0;

        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            Toast.makeText(this, "memoria montada solo lectura", Toast.LENGTH_LONG).show();
            return 1;
        }
        else
        {
            Toast.makeText(this, "Error de memoria", Toast.LENGTH_LONG).show();
            return 2;
        }

    }
    public void Registrar(View v)
    {


        float rating = ratingbart_tmp.getRating();

        String info;
        info = cedula.getText().toString() + ";";
        info = info + nombre.getText() + ";";
        info = info + apellido.getText()+ ";";
        info = info + edad.getText() + ";";
        info = info + String.valueOf(rating) + "\n";

        int estadoSD = StateSD();
        String inf = "";
        if(estadoSD == 0)
        {
            try {
                //crea directorio en raiz

                File file = new File(getExternalFilesDir(null),"Usurious.txt" );
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true));
                writer.write(info.toString());
                writer.close();

                cedula.setText(null);
                nombre.setText(null);
                apellido.setText(null);
                edad.setText(null);

                Toast.makeText(v.getContext(), "Guardado con exito", Toast.LENGTH_LONG).show();

            }catch (Exception ex)
            {
                Toast.makeText(v.getContext(), "Error al guardar", Toast.LENGTH_LONG).show();
                //probando git
            }

        }
    }

    public void leerArchivo(View v) {
        // Comprobar si el almacenamiento externo está disponible
        int estado = StateSD();
        if (estado==0) {
            // Crear un objeto File con la ruta del archivo
            File file = new File(getExternalFilesDir(null),"Usurious.txt" );

            try {
                // Leer el archivo usando BufferedReader
                BufferedReader br = new BufferedReader(new FileReader(file));
                String linea;

                // Leer la primera línea (supongo que solo hay una línea en tu archivo)
                if ((linea = br.readLine()) != null) {
                    // Dividir la línea en partes usando el separador ";"
                    String[] partes = linea.split(";");

                    // Llenar las cajas de texto con los datos
                    cedula.setText(partes[0]);
                    nombre.setText(partes[1]);
                    apellido.setText(partes[2]);
                    edad.setText(partes[3]);
                    // Obtener el valor flotante para el RatingBar
                    float ratingValue = Float.parseFloat(partes[4]);

                    // Establecer el valor del RatingBar
                    ratingbart_tmp.setRating(ratingValue);
                }

                // Cerrar el BufferedReader
                br.close();
            } catch (IOException e) {
                Log.d("TAG", "Mensaje de depuración");
                e.printStackTrace();
            }
        }
    }

}