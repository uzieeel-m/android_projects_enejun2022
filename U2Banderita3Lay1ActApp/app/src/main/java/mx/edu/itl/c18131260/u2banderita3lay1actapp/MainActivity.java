/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                 Clase principal para lanzar las demás activities.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 03/mar/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase contiene el método btnProcesarClick(), que recibe como parámetro
:*                  un view, según el id del mismo, es el layout que se mostrará con el método
:*                  setContentView(). El primer layout que se muestra es el activity_verde.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u2banderita3lay1actapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verde);
        Toast.makeText(this, "hola desde onCreate", Toast.LENGTH_SHORT).show();
    }

    public void btnProcesarClick(View view){
        if(view.getId() == R.id.btnAcercaDe){
            //mostramos en AlertDialog con la info acerca de
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.itl_logo)
                    .setTitle("Acerca de")
                    .setMessage("U2Banderita3Lay1ActApp v1.0\n\n" +
                            "Por Elí Uziel Montes Pérez\t\t18131260\n\n" +
                            "Todos los derechos reservados.").create().show();
        } else if(view.getId() == R.id.btnVerdeSiguiente){
            //cambiamos al layout blanco
            setContentView(R.layout.activity_blanco);
        } else if(view.getId() == R.id.btnBlancoAtras){
            //regresamos a la seccion verde
            setContentView(R.layout.activity_verde);
        } else if(view.getId() == R.id.btnBlancoSiguiente){
            //avanzamos a la seccion roja
            setContentView(R.layout.activity_rojo);
        } else if(view.getId() == R.id.btnRojoAtras){
            //regresamos a la seccion blanca
            setContentView(R.layout.activity_blanco);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "hola desde onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "hola desde onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "hola desde onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "hola desde onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "hola desde onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "hola desde onRestart", Toast.LENGTH_SHORT).show();
    }
}