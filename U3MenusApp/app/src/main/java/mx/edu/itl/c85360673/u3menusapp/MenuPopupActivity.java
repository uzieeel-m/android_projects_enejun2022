package mx.edu.itl.c85360673.u3menusapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPopupActivity extends AppCompatActivity implements View.OnLongClickListener,
                                                                    PopupMenu.OnMenuItemClickListener {

    EditText edtApodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_popup);

        edtApodo = (EditText) findViewById ( R.id.edtApodo );
        // Establecemos un listener para el evento LongClick sobre el editText del Apodo
        edtApodo.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        if(id == R.id.edtApodo){
            configurar_popmenu_apodo(v);
            return true;
        }
        return false;
    }

    private void configurar_popmenu_apodo ( View v ) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup_apodo, popupMenu.getMenu());;
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick ( MenuItem item ) {
        int id = item.getItemId();
        switch (id){
            case R.id.mniApodoAmlo:
                edtApodo.setText("AMLO");
                break;
            case R.id.mniApodoBorolas:
                edtApodo.setText("Borolas");
                break;
            case R.id.mniApodoPenita:
                edtApodo.setText("Peñita");
                break;
            default:
                return false;
        }
        return true;
    }


}
