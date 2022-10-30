/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*              Pantalla correspondiente al color verde de la bandera de México
:*
:*  Archivo     : VerdeActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 25/feb/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta activity contiene el color verde de la badera Mexicana, además de un
:*                  botón con la leyenda "Siguiente", cuya función es redirigir a la siguiente
:*                  activity, para lo que crea un objeto de la clase Intent, manándole la
:*                  clase del activity siguiente, y lanzándola con el método startActivity();
:*                  También contiene el botón Acerca De donde se muestran los créditos de la app.
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u2bandera3lay3actapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class VerdeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verde);

        Toast.makeText(this, "Verde método onCreate", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    public void btnSiguienteClick(View view){
        //preparamos la invocación al blanco activity
        //clase para decirle que tenemos la intención de hacer alguna acción
        //en este caso, el intento de cambiar una activity por otra.
        Intent intent = new Intent(this, BlancoActivity.class);
        startActivity(intent);

    }
    //----------------------------------------------------------------------------------------------
    public void btnAcercaDeClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acerca de")
                .setMessage("U2Bandera3Lay3ActApp v1.0\n\npor Elí Uziel Montes Pérez\t\t18131260\n\n" +
                        "Todos los derechos reservados.").create().show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Verde método onStart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Verde método onResume", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Verde método onPause", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Verde método onStop", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Verde método onDestroy", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Verde método onRestart", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
}