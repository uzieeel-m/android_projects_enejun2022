/*:
 *: Requiere: <uses-permission android:name="android.permission.CAMERA"/>
 *:           <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 *:           <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 *:
 *: Requiere agregar lo siguiente en el AndroidManifest.xml:
 *:
 *:        <provider
 *:             android:name="androidx.core.content.FileProvider"
 *:             android:authorities="mx.edu.itl.c85360673.u4tomarfotosapp.provider"
 *:             android:exported="false"
 *:             android:grantUriPermissions="true">
 *:             <!-- ressource file to create -->
 *:             <meta-data
 *:                 android:name="android.support.FILE_PROVIDER_PATHS"
 *:                 android:resource="@xml/file_paths">
 *:             </meta-data>
 *:        </provider>
 *:
 *: Requiere crear el archivo  file_paths.xml  en la carpeta de recursos xml :
 *:
 *:          <?xml version="1.0" encoding="utf-8"?>
 *:          <paths xmlns:android="http://schemas.android.com/apk/res/android">
 *:                <external-path name="external_files" path="." />
 *:          </paths>
 *:
 */


package mx.edu.itl.c85360673.u4tomarfotosapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import util.permisos.ChecadorDePermisos;
import util.permisos.PermisoApp;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //variables de la clase
    private PermisoApp [] permisosReq = {
            new PermisoApp  ( Manifest.permission.CAMERA, "Camara", true  ),
            new PermisoApp  ( Manifest.permission.READ_EXTERNAL_STORAGE, "Almacenamiento", true  ),
            new PermisoApp  ( Manifest.permission.WRITE_EXTERNAL_STORAGE, "Almacenamiento", true )
    };
    private Uri uriFoto;
    private static final int CODIGO_CAPTURA_FOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChecadorDePermisos.checarPermisos ( this, permisosReq );

        //resolver error de archivo evitando las políticas de seguridad
        /*StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());*/
    }

    public void btnCapturaSimpleClick ( View v ) {
        //formar el nombre del archivo basado en la fecha y hora que sea +unico
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strFechaHora = simpleDateFormat.format(new Date());
        String archFoto = "foto"+strFechaHora+".jpg";

        File fileFoto = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator +
                "DCIM"+
                File.separator+
                archFoto);
        //dónde quiero que guarde la foto
//        uriFoto = Uri.fromFile(fileFoto);
        //crear uri correspondiente al archivo de destino de la foto.
        //Se usa la clase FileProvider para respetar las políticas de seguridad
        uriFoto = FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID + ".provider",
                fileFoto);
        //crear el intent que llama a la app de la camara y le pasamos el uri
        //del archivo donde se guarda la foto (si es que se captura una)
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
        startActivityForResult(intent, CODIGO_CAPTURA_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_CAPTURA_FOTO){
            if(resultCode == RESULT_OK){
                //si se tomó la foto, abrir el activity para mostrar la
                //foto en antalla completa
                //se envía como argumento el URI de la foto como string
                Intent intent = new Intent(this, MuestraFotoActivity.class);
                intent.putExtra("uri", uriFoto.toString());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );

        if ( requestCode == ChecadorDePermisos.CODIGO_PEDIR_PERMISOS ) {
            ChecadorDePermisos.verificarPermisosSolicitados ( this, permisosReq, permissions, grantResults );
        }
    }

    public void btnCapturaOpcionesClick (View v ) {
        Intent intent = new Intent ( this, CamaraActivity.class ) ;
        startActivity ( intent );
    }

    public void acercaDeClick(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }
}
