package mx.itl.edu.c16131300.u3paseargumentosapp;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int USUARIO_CODIGO    = 1024;  // Valor arbitrario para identificar el campo Usuario
    final static int CONTRASENA_CODIGO = 2048;  // Valor arbitrario para identificar el campo Contraseña

    EditText edtUsuario;
    EditText edtContrasena;
    Intent   intent;

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos las referencias a los campos de texto usuario y contrasena
        edtUsuario    = (EditText) findViewById ( R.id.edtUsuario    );
        edtContrasena = (EditText) findViewById ( R.id.edtContrasena );
    }
    //----------------------------------------------------------------------------------------------
    public void btnUsuarioClick ( View v ) {
        intent = new Intent(this, RellenarCampoActivity.class);
        // Pasamos como argumento el valor del campo Usuario
        intent.putExtra ( "valor", edtUsuario.getText().toString() );
        startActivityForResult ( intent, USUARIO_CODIGO );
    }
    //----------------------------------------------------------------------------------------------
    public void btnContrasenaClick ( View v ) {
        intent = new Intent ( this, RellenarCampoActivity.class );
        // Pasamos como argumento el valor del campo Contraseña
        intent.putExtra ( "valor", edtContrasena.getText().toString() );
        startActivityForResult ( intent, CONTRASENA_CODIGO );
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult ( requestCode, resultCode, data );
        if ( resultCode == Activity.RESULT_OK ) {
            if ( requestCode == USUARIO_CODIGO )
                edtUsuario.setText (  data.getStringExtra ( "valor_campo" ) );
            else if ( requestCode == CONTRASENA_CODIGO )
                edtContrasena.setText ( data.getStringExtra ( "valor_campo" ) );
        }

        // Colocamos el enfoque (cursor) en el campo correspondiente
        if ( requestCode == USUARIO_CODIGO )
            edtUsuario.requestFocus ();
        else if ( requestCode == CONTRASENA_CODIGO )
            edtContrasena.requestFocus ();
    }
    //----------------------------------------------------------------------------------------------
    // Al dar clic en boton INGRESAR se muestra Toast con el usuario y contraseña capturados.

    public void btnIngresarClick ( View v ) {
        Toast.makeText (
         this,
           "Bienvenido: " + edtUsuario.getText().toString() + ", " + edtContrasena.getText().toString(),
                Toast.LENGTH_LONG
        ).show ();
    }
    //----------------------------------------------------------------------------------------------

}
