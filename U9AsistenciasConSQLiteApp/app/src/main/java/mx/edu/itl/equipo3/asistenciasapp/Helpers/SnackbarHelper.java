/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                       Clase para mostrar un Snackbar
:*
:*  Archivo     : SnackbarHelper.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase para mostrar un Snackbar en diferentes clases recibiendo el mensaje
:*
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Helpers;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarHelper {

    //----------------------------------------------------------------------------------------------

    public static void showSnackbar ( View view, String mensaje, boolean corto ) {
        Snackbar mySnackbar = Snackbar.make ( view, mensaje, corto ? Snackbar.LENGTH_SHORT : Snackbar.LENGTH_LONG );
        mySnackbar.show ();
    }

    //----------------------------------------------------------------------------------------------
}
