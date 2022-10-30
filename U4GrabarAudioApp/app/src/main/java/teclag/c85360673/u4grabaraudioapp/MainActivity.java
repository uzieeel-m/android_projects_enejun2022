package teclag.c85360673.u4grabaraudioapp;


import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

import mx.edu.itl.c18131260.androlib.util.permisos.PermisoApp;
import mx.edu.itl.c18131260.androlib.util.permisos.ChecadorDePermisos;

public class MainActivity extends AppCompatActivity {

    private TextView txtvMensajes;
    private EditText edtGuardarComo;
    private Button   btnGrabar;
    private Button   btnDetener;
    private Button   btnReproducir;

    private PermisoApp [] permisosReq= {
            new PermisoApp(Manifest.permission.RECORD_AUDIO, "Audio", true),
            new PermisoApp(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Almacenamiento", true),
            new PermisoApp(Manifest.permission.READ_EXTERNAL_STORAGE, "Leer almacenamiento", true)
    };
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String ruta = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    private String fichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvMensajes     = findViewById ( R.id.txtvMensaje );
        edtGuardarComo   = findViewById ( R.id.edtGuardarComo );
        btnGrabar        = findViewById ( R.id.btnGrabar );
        btnDetener       = findViewById ( R.id.btnDetener );
        btnReproducir    = findViewById ( R.id.btnReproducir );

        ChecadorDePermisos.checarPermisos(this, permisosReq);

        txtvMensajes.setText("");
        btnGrabar.setEnabled(true);
        btnDetener.setEnabled(false);
        btnReproducir.setEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == ChecadorDePermisos.CODIGO_PEDIR_PERMISOS){
            ChecadorDePermisos.verificarPermisosSolicitados(this, permisosReq, permissions, grantResults);
        }
    }

    public void btnGrabarClick (View v ) {
        fichero = ruta + edtGuardarComo.getText().toString() + ".3gp";

        mediaRecorder = new MediaRecorder();
        //establecer el mocrofono como fuente de audio
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //establecemos el formato del archivo en jgp
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //establecer el codificador de audio
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        //establecer el archivo de salida de la grabación
        mediaRecorder.setOutputFile(fichero);

        try {
            txtvMensajes.setText("Grabando...");
            btnGrabar.setEnabled(false);
            btnDetener.setEnabled(true);
            btnReproducir.setEnabled(false);

            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException ex){
            txtvMensajes.setText("");
            btnGrabar.setEnabled(true);
            btnDetener.setEnabled(false);
            btnReproducir.setEnabled(false);

            Toast.makeText(this, "Fichero:"+fichero+"\nFallo al hacer la grabación\n"+ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnDetenerClick ( View v ) {
        txtvMensajes.setText("");
        btnGrabar.setEnabled(true);
        btnDetener.setEnabled(false);
        btnReproducir.setEnabled(true);

        mediaRecorder.stop();
        mediaRecorder.release();
    }

    public void btnReproducirClick ( View v ) {
        mediaPlayer = new MediaPlayer();
        try {
            //configurar el archivo a reproducir
            mediaPlayer.setDataSource(fichero);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    txtvMensajes.setText("Reproduciendo...");
                    btnGrabar.setEnabled(false);
                    btnDetener.setEnabled(false);
                    btnReproducir.setEnabled(false);

                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    txtvMensajes.setText("");
                    btnGrabar.setEnabled(true);
                    btnDetener.setEnabled(false);
                    btnReproducir.setEnabled(true);

                    mediaPlayer.release();
                }
            });
            mediaPlayer.prepare();
        } catch (IOException ex){
            txtvMensajes.setText("");
            btnGrabar.setEnabled(true);
            btnDetener.setEnabled(false);
            btnReproducir.setEnabled(false);

            Toast.makeText(this, "Fallo al reproducir el audio", Toast.LENGTH_SHORT).show();
        }
    }

    public void acercaDeClick(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }

}
