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

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityLauncher;
    protected String ACTIVITY_REQUEST_CODE;
    //protected static final int REQUEST_CODE_1 = 123;

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

        activityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        int requestCode = intent.getIntExtra(ACTIVITY_REQUEST_CODE, -1);
                        MainActivity.this.onActivityResult(requestCode, result.getResultCode(), intent);
                    }
                }
        );

        // Obtenemos las referencias a los campos de texto usuario y contrasena
        edtUsuario    = (EditText) findViewById ( R.id.edtUsuario    );
        edtContrasena = (EditText) findViewById ( R.id.edtContrasena );
    }
    @SuppressWarnings("deprecation")
    public void startActivityForResult(Intent intent, int requestCode){
        launchActivityForResult(intent, requestCode);
    }

    public void launchActivityForResult(Intent intent, int requestCode){
        if(requestCode == USUARIO_CODIGO){
            ACTIVITY_REQUEST_CODE = "usuario";
        } else if(requestCode == CONTRASENA_CODIGO){
            ACTIVITY_REQUEST_CODE = "contrasena";
        }
        intent.putExtra(ACTIVITY_REQUEST_CODE, requestCode);
        activityLauncher.launch(intent);
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
