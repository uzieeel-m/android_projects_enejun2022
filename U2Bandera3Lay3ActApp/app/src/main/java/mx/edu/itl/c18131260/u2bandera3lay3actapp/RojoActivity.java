/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*             Pantalla correspondiente al color rojo de la bandera de México
:*
:*  Archivo     : BlancoActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 25/feb/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta activity contiene el color rojo de la badera Mexicana, además de un
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

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RojoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_rojo );
        Toast.makeText(this, "Rojo método onStart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    public void btnAtrasClick(View view){
        //terminamos el activity actual y regresa el control al activity que lo invocó
        finish();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Rojo método onStart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Rojo método onResume", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Rojo método onPause", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Rojo método onStop", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Rojo método onDestroy", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Rojo método onRestart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
}