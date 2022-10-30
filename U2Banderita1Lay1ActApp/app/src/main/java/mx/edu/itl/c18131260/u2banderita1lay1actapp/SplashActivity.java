/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                   Clase que despliega una pantalla de carga.
:*
:*  Archivo     : SplashActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 03/mar/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase muestra una pantalla antes de que se abra la aplicación, para
:*                  hacer esto, usa la clase Handler para manejar cuál activity abre.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u2banderita1lay1actapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //la pantalla de splash se presentará en orientación vertical y en pantalla completa
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash);

        //hacer la transisión al siguiente Activity después de 3seg
        //tarea que se va a ejecutar después del tiempo, tiempo que se tardará antes de realizar la tarea
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                                    //intent que llama, el activity que quiero llamar
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
    //----------------------------------------------------------------------------------------------

}