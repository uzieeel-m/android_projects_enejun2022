package mx.edu.itl.c85360673.u3menusapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class TercerActivity extends MenuComunActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);

        layout = (LinearLayout) findViewById ( R.id.layoutPrincipal );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch ( id ) {
            case R.id.mniCambiarColorFondo:
                layout.setBackgroundColor ( Color.BLUE );
                return true;
            default  :
                return super.onOptionsItemSelected(item);
        }
    }
}
