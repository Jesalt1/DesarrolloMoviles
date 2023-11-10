package com.example.aplicaciondeprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String test_email = "admin",test_pass = "123456";
    private EditText var_email, var_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //acceso a input de texto
        var_email = (EditText) findViewById(R.id.emailtxt);
        var_password = (EditText) findViewById(R.id.passwordtxt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuPrincipal = getMenuInflater();
        menuPrincipal.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.CapturaGrupo)
        {
            CapturaGrupoitem(this.getCurrentFocus());
            return true;
        } else if (item.getItemId()==R.id.AcercaDeMenuItem)
        {
            AcercaDeitem(this.getCurrentFocus());
            return true;
        } else if (item.getItemId()==R.id.NoHaceNada)
        {
            //Toast.makeText(this.getCurrentFocus().getContext(), "NO HACE NADA", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;

    }

    //region Menu
    private void CapturaGrupoitem(View v)
    {
        Intent register_view = new Intent(this, GroupCapture.class);
        startActivity(register_view);
    }

    private void AcercaDeitem(View v)
    {
        Intent register_view = new Intent(this, AcercaDe.class);
        startActivity(register_view);
    }

//endregion menu
    public void ingresar(View v){
        String cont_email = var_email.getText().toString();
        String cont_pass = var_password.getText().toString();

        if(cont_email.equals(test_email)&&cont_pass.equals(test_pass)) {
            Intent register_view = new Intent(this, RegistroActivity.class);
            register_view.putExtra("message",cont_email);
            startActivity(register_view);
        }else {
            Toast.makeText(v.getContext(), "DATOS INCORRECTOS", Toast.LENGTH_LONG).show();
        }
    }

    public void Salir(){
        finish();
    }

    public void registrar(View v){
        Intent registro_view = new Intent(this, RegistroActivity2.class);
        startActivity(registro_view);
    }
}