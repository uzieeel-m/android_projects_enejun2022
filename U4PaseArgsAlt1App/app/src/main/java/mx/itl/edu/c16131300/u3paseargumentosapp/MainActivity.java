package mx.itl.edu.c16131300.u3paseargumentosapp;

import android.app.Activity;
import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityResultCallback {

    private ActivityResultLauncher activityResultLauncher;

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

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), this
        );

        // Obtenemos las referencias a los campos de texto usuario y contrasena
        edtUsuario    = (EditText) findViewById ( R.id.edtUsuario    );
        edtContrasena = (EditText) findViewById ( R.id.edtContrasena );
    }
    //----------------------------------------------------------------------------------------------
    public void btnUsuarioClick ( View v ) {
        intent = new Intent(this, RellenarCampoActivity.class);
        // Pasamos como argumento el valor del campo Usuario
        intent.putExtra ( "usuario", edtUsuario.getText().toString() );
        intent.putExtra("codigoPeticion", USUARIO_CODIGO);
        activityResultLauncher.launch(intent);
    }
    //----------------------------------------------------------------------------------------------
    public void btnContrasenaClick ( View v ) {
        intent = new Intent ( this, RellenarCampoActivity.class );
        // Pasamos como argumento el valor del campo Contraseña
        intent.putExtra ( "contrasena", edtContrasena.getText().toString() );
        intent.putExtra("codigoPeticion", CONTRASENA_CODIGO);
        activityResultLauncher.launch(intent);
    }

    //----------------------------------------------------------------------------------------------
    /*@Override
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
    }*/
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

    @Override
    public void onActivityResult( Object result) {
        ActivityResult resultado = ((ActivityResult) result);
        Intent data = resultado.getData();
        if(resultado.getResultCode() == USUARIO_CODIGO){
            edtUsuario.setText(data.getStringExtra("dato_nuevo"));
        } else if(resultado.getResultCode() == CONTRASENA_CODIGO){
            edtContrasena.setText(data.getStringExtra("dato_nuevo"));
        }
    }

    //----------------------------------------------------------------------------------------------

}
