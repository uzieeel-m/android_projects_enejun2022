package mx.edu.itl.c85360673.u3menusapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MenuContextualFlotanteActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_contextual_flotante);

        edtNombre = (EditText) findViewById ( R.id.edtNombre );
        // Registramos el EditText del Nombre para tener un menu contextual flotante
        registerForContextMenu(edtNombre);

        edtApellido = (EditText) findViewById ( R.id.edtApellido );
        // Registramos el EditText del Apellido para tener un menu contextual flotante
        registerForContextMenu(edtApellido);
    }

    // onCreateContextMenu se invoca al registrar un View con registerForContextMenu ()
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Dependiendo del objeto View se le crea su menu contextual flotante que le corresponda
        if(v.getId() == R.id.edtNombre){
            getMenuInflater().inflate(R.menu.menu_contextual_nombre, menu);
        } else if(v.getId() == R.id.edtApellido){
            getMenuInflater().inflate(R.menu.menu_contextual_apellido, menu);
        }
    }

    // Metodo para manejar la opcion de menu contextual seleccionada por el usuario
    @Override
    public boolean onContextItemSelected ( MenuItem item ) {
        int id = item.getItemId();
        switch (id){
            case R.id.mniFuenteTamano8:
                edtNombre.setTextSize(8);
                break;
            case R.id.mniFuenteColorNegro:
                edtNombre.setTextColor(Color.BLACK);
                break;
            case R.id.mniFuenteColorRojo:
                edtNombre.setTextColor(Color.RED);
                break;
            case R.id.mniFondoAmarillo:
                edtApellido.setBackgroundColor(Color.YELLOW);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

}


