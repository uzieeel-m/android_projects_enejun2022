package mx.itl.edu.c16131300.u3paseargumentosapp;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RellenarCampoActivity extends AppCompatActivity {

    EditText edtValorCampo;
    int codigoPeticion;

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_campo);

        // Obtenemos la referencia del EditText con el valor del campo
        edtValorCampo = (EditText) findViewById ( R.id.edtValorCampo );
        // Recuperamos el valor del argumento "valor" que viene desde MainActivity
        // y lo establecemos como valor del EditText
        String usuario = getIntent ().getStringExtra ( "usuario" );
        String contrasena = getIntent().getStringExtra("contrasena");
        if ( usuario != null ) {
            edtValorCampo.setText(usuario);
        } else if(contrasena != null){
            edtValorCampo.setText(contrasena);
        }

        codigoPeticion = getIntent().getIntExtra("codigoPeticion", 123);
    }
    //----------------------------------------------------------------------------------------------
    public void btnAceptarClick ( View v ) {
        // Obtenemos el Intent que invocó a este Activity
        Intent intent = getIntent ();
        // Establecemos el resultado a devolver  y terminamos el Activity
        intent.putExtra ( "dato_nuevo", edtValorCampo.getText().toString() );

        setResult ( codigoPeticion, intent );
        finish ();
    }
    //----------------------------------------------------------------------------------------------
    public void btnCancelarClick ( View v ) {
        // Establecemos el codigo a devolver en CANCELADO y terminamos el Activity
        setResult (RESULT_CANCELED );
        finish ();
    }
    //----------------------------------------------------------------------------------------------
}
