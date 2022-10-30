/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*                Clase de la barra de rating
:*
:*  Archivo     : RatingBarActivity.java
:*  Autor       : Gerardo Salomon Rodriguez Campos     17130831
:*  Fecha       : 08/11/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripci�n : Esta clase se encarga de hacer la barra de rating
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c85360673.u9widgetsapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import mx.edu.itl.c85360673.u9widgetsapp.R;

public class RatingBarActivity extends AppCompatActivity {

    private View ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        ratingBar = (android.widget.RatingBar)findViewById(R.id.rBar);



    }
}