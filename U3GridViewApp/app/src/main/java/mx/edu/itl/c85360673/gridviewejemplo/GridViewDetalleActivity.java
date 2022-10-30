package mx.edu.itl.c85360673.gridviewejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

//-----> import com.bumptech.glide.Glide;

public class GridViewDetalleActivity extends AppCompatActivity {

    private ImageView imgvEscudoDetalle;

    //------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_detalle);
        getSupportActionBar().hide();

        imgvEscudoDetalle = findViewById ( R.id.imgvEscudoDetalle );

        // Recuperamos de los argumentos del activity, el ID del drawable del escudo a mostrar
        int escudo = getIntent().getIntExtra("escudo", 0);

        // Reemplazamos la forma tradicional de cargar el recurso ImageView por la libreria Glide
        imgvEscudoDetalle.setImageResource ( escudo );

    }

     //------------------------------------------------------------------------------------------
}
