package mx.edu.itl.c85360673.u4tomarfotosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MuestraFotoActivity extends AppCompatActivity {

    private ImageView imgvFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_foto);

        imgvFoto = findViewById(R.id.imgvFoto);

        //ocultar la barra de acciones
        this.getSupportActionBar().hide();

        //obtener la ubicacion de la foto desde el parametro que viene de los extras
        String strUri = getIntent().getStringExtra("uri");
        imgvFoto.setImageURI(Uri.parse(strUri));
    }
}
