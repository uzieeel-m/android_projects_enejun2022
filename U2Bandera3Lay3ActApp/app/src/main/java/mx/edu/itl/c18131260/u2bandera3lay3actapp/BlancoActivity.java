/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*              Pantalla correspondiente al color blanco de la bandera de México
:*
:*  Archivo     : BlancoActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 25/feb/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta activity contiene el color blanco de la badera Mexicana, además de un
:*                  botón con la leyenda "Siguiente", cuya función es redirigir a la siguiente
:*                  activity, para lo que crea un objeto de la clase Intent, manándole la
:*                  clase del activity siguiente, y lanzándola con el método startActivity().
:*                  También contiene el botón Atrás para finalizar el activity actual y volver al
:*                  anterior.
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u2bandera3lay3actapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BlancoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_blanco );
        Toast.makeText(this, "Blanco método onCreate", Toast.LENGTH_SHORT).show();
    }
//--------------------------------------------------------------------------------------------------
    public void btnAtrasClick(View view){
        //terminamos el activity actual y regresa el control al activity que lo invocó
        finish();
    }
    //----------------------------------------------------------------------------------------------
    public void btnSiguienteClick(View view){
        //preparamos la invocación al rojo activity
        Intent intent = new Intent(this, RojoActivity.class);
        //lanzar activity rojo
        startActivity(intent);
    }

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Blanco método onStart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Blanco método onResume", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Blanco método onPause", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Blanco método onStop", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Blanco método onDestroy", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Blanco método onRestart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------

}