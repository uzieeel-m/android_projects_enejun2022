/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Activity para poner una imagen en grande.
:*
:*  Archivo     : FotoActivity.java
:*  Autor       : Ing. Fernando Gil     c85360673
:*  Fecha       : ?
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase toma la imagen que se capturó en el activity anterior y la pone
:*                  en el único ImageView que hay, que ocupa todo el layout, esto para que se
:*                  vea grande.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  18/may/2022 Uziel Montes         Agregar prompt
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c85360673.u4intentsimplicitosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class FotoActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        // Se recupera el argumento con la uri de la foto a mostrar
        String uri = getIntent ().getStringExtra  ("uri" );

        // Se muestra la foto
        imageView = findViewById ( R.id.imageView );
        imageView.setImageURI ( Uri.parse ( uri ) );
    }
}
