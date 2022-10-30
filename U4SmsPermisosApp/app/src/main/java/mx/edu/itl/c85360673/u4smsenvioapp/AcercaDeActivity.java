/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Activity para mostrar la info de la app.
:*
:*  Archivo     : AcercaDeActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase muestra la información de la aplicación, autor, institución, materia,
:*                  entre otros.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -               -                   -
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c85360673.u4smsenvioapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void cerrarClick(View view){
        Intent intent = new Intent(AcercaDeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}