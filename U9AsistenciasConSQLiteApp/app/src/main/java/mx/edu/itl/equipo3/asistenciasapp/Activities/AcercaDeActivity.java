/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*        Activity que muestra un layout con informacion de la app y del equipo
:*
:*  Archivo     : AcercaDeActivity.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Muentra informacion acerca de la escuela, nombre de la app y los nombres
:*                del equipo, ademas de la fecha de creación
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.equipo3.asistenciasapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mx.edu.itl.equipo3.asistenciasapp.R;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }
}