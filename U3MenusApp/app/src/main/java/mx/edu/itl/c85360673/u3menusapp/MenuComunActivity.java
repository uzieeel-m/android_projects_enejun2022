package mx.edu.itl.c85360673.u3menusapp;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


// Esta clase es la superclase de los Activity's que van a compartir un menu en comun
// En este caso el menu tendrá 2 opciones en comun: Acerca de y Cambiar el color de fondo de la pantalla.

public class MenuComunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate ( R.menu.menu_comun, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch ( id ) {
            case R.id.mniAcercaDe : Toast.makeText ( this, "TecLaguna v1.0", Toast.LENGTH_SHORT ).show (); break;
            default               : return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
