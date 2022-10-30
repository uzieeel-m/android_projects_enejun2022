package mx.edu.itl.ctl85360673.almacenintextapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class AlmacenInternoActivity extends AppCompatActivity {

    private final String FILENAME = "MiArchivo.txt";
    private String texto;
    private TextView  textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen_interno);

        textView = (TextView) findViewById ( R.id.textView );
    }

    public void btnGuardarClick ( View v )
    {
        texto = textView.getText().toString();

        try {
            FileOutputStream fos = this.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write ( texto.getBytes () );
            fos.close();
            Toast.makeText(this, "Archivo guardado.", Toast.LENGTH_SHORT).show();
        } catch ( IOException ex ) {
            Toast.makeText(this, "Error al guardar el archivo.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnLeerClick ( View v ) {

        texto = "";
        int   caracter;

        try {
            FileInputStream fis = this.openFileInput ( FILENAME );
            while ( ( caracter = fis.read() ) != -1 )
               texto += (char) caracter;
            fis.close ();
            textView.setText ( texto );
            Toast.makeText(this, "Leyendo el archivo "+FILENAME+".", Toast.LENGTH_SHORT).show();
        } catch ( IOException ex ) {
            Toast.makeText(this, "Error al leer el archivo.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnInformacionClick  ( View v ) {
        // Obtener la ruta de la carpeta del almacenamiento interno
        File rutaAlmacenInterno = this.getFilesDir ();
        String carpetaAlmInt = rutaAlmacenInterno.getAbsolutePath ();

        texto = "Carpeta Almacenamiento Interno: " + carpetaAlmInt + "\n";
        Toast.makeText(this, "Mostrando información de la dirección:\n"+texto, Toast.LENGTH_LONG).show();
        // Obtener la lista de archivos en almacen interno
        String archivosList [] = this.fileList ();

        texto += "Archivos: \n";
        for ( String archivo : archivosList ) {
            texto += archivo + "\n";
        }

        textView.setText ( texto );

    }



















}
