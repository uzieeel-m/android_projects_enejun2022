/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*             Clase que despliega en pantalla la bandera de México completa.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 03/mar/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase carga el diseño de pantalla en activity_main.xml que contiene
:*                las 3 secciones de la badera de México y el escudo nanional. Se basa en el
:*                  uso de 3 componentes, LinearLayout y un ImageView para mostrar el escudo.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u2banderita1lay1actapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "hola desde onCreate", Toast.LENGTH_SHORT).show();
    }

    public void botonAcercaDeClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.itl_logo)
                .setTitle("Acerca de")
                .setMessage("TecNM Campus La Laguna\n\n" +
                        "U2Banderita1Lay1ActApp v1.0\n\n" +
                        "por Elí Uziel Montes Pérez\t\t18131260\n\n" +
                        "Todos los derechos reservados.").create().show();
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