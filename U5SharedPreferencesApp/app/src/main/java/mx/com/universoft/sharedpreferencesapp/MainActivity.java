package mx.com.universoft.sharedpreferencesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button      btnGuardarPref,
                btnRecupPref;
    EditText    editText1,
                editText2;
    boolean     preferenciasGuardadas;

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.archivos_main );

        btnGuardarPref     = (Button) findViewById ( R.id.button3 );
        btnRecupPref     = (Button) findViewById ( R.id.button4 );
        editText1   = (EditText) findViewById ( R.id.editText3 );
        editText2   = (EditText) findViewById ( R.id.editText4 );

        btnGuardarPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPreferencias ();
            }
        });

        btnRecupPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarPreferencias ();
            }
        });

    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {

        super.onDestroy ();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onResume () {
        cargarPreferencias ();
        super.onResume ();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onPause () {
        guardarPreferencias ();
        super.onPause ();
    }

    //----------------------------------------------------------------------------------------------

    private void guardarPreferencias () {

        // Obtenemos una referencia al archivo de preferencias y lo ponemos en modo edicion
        // ( el archivo se guarda en  /data/data/mx.com.universoft.sharedpreferencesapp/shared_prefs/preferenciasMiApp.xml )
        SharedPreferences preferences   = getSharedPreferences ( "preferenciasMiApp", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit ();

        // Guardamos valores en el archivo de preferencias en forma de pares ( clave, valor )
        editor.putBoolean ( "preferenciasGuardadas", true );
        editor.putString  ( "preferencia1", editText1.getText().toString() );
        editor.putString  ( "preferencia2", editText2.getText().toString() );

        // Acometemos los cambios para que se guarden permanentemente
        editor.commit     ();

        Toast.makeText ( this, "Guardando preferencias", Toast.LENGTH_SHORT ).show ();
    }

    //----------------------------------------------------------------------------------------------

    private void cargarPreferencias () {
        // Obtenemos la referencia al archivo  preferenciasMiApp
        SharedPreferences preferences = getSharedPreferences ( "preferenciasMiApp", Context.MODE_PRIVATE );

        // Leemos los valores guardados y se muestran en los componentes del layout.
        // Se usan los metodos getXXX ( llave, valor_default ) 
        editText1.setText     ( preferences.getString  ( "preferencia1", "(1) Escriba algo aqui..."    ) );
        editText2.setText     ( preferences.getString  ( "preferencia2", "(2) Escriba aqui tambien..." ) );
        preferenciasGuardadas = preferences.getBoolean ( "preferenciasGuardadas", false     );
    }

    //----------------------------------------------------------------------------------------------
    public void acercaDeClick(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }

}
