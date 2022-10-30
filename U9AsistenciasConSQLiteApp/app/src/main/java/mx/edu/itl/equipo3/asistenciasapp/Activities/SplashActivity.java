/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                      Splash que se muestra al inciar la aplicación
:*
:*  Archivo     : SplashActivity.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Muestra al inciar la aplicación el titulo de la app, un icono y un progress bar
:*                durante 5 segundos y despues va directo al MainActivity
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import mx.edu.itl.equipo3.asistenciasapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Pasar a la pantalla principal despues de 5 segs
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( SplashActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        }, 5000 );
    }
}