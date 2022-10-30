/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                   Clase que utiliza CargarAlumnosActivity
:*
:*  Archivo     : CargarAlumnosHelper.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Helper con diferentes métodos para poder contruir los objetos de Alumnos
:*                y poder mandar a llamar estos metodos en CargarAlumnosActivity
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.edu.itl.equipo3.asistenciasapp.Objects.Alumno;
import mx.edu.itl.equipo3.asistenciasapp.Objects.GrupoEnum;
import mx.edu.itl.equipo3.asistenciasapp.Objects.InfoArchivo;
import mx.edu.itl.equipo3.asistenciasapp.SQLite.DB;

public class CargarAlumnosHelper {

    //----------------------------------------------------------------------------------------------

    public static ArrayList<InfoArchivo> getFile( String path ) {
        // gets the files in the directory
        File fileDirectory = new File( path );

        ArrayList<InfoArchivo> infoArchivos = new ArrayList<>();

        infoArchivos.add(
            new InfoArchivo (
                fileDirectory.getName(),
                String.valueOf( fileDirectory.length() / 1024 ) + "KB",
                path,
                path + File.separator + fileDirectory.getName(),
                fileDirectory,
                    GrupoEnum.NONE,
                ""
            )
        );

        return infoArchivos;
    }

    //----------------------------------------------------------------------------------------------

    public static ArrayList<Alumno> obtenerAlumnos ( InfoArchivo archivoAlumnos ) {
        return procesarArchivo ( archivoAlumnos );
    }

    //----------------------------------------------------------------------------------------------

    private static ArrayList<Alumno> procesarArchivo ( InfoArchivo archivo ) {
        ArrayList<Alumno> alumnos = new ArrayList<> ();
        try {
            BufferedReader br = new BufferedReader( new FileReader( archivo.getArchivo() ) );
            String linea;

            while ( ( linea = br.readLine() ) != null ) {
                Alumno alumno = obtenerAlumno ( linea );

                if ( alumno == null ) continue;

                alumnos.add ( alumno );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return alumnos;
    }

    //----------------------------------------------------------------------------------------------

    private static String regexAlumno = "([A-Za-z]?[0-9]{8,9}),([\\w\\sñÑ]+)$";

    //----------------------------------------------------------------------------------------------

    private static Alumno obtenerAlumno ( String linea ) {
        String lineaSanitizada = sanitizarLinea ( linea );
        if ( ! esAlumnoValido ( lineaSanitizada, regexAlumno ) ) return null;

        Pattern pattern = Pattern.compile ( regexAlumno );
        Matcher partes = pattern.matcher ( lineaSanitizada );

        if ( partes.find () ) {
            String noControl = partes.group ( 1 );
            String nombre = Objects.requireNonNull( partes.group ( 2 ) ).trim();

            return new Alumno ( noControl, nombre, nombre.split ( " " ) );
        }
        return null;
    }

    //----------------------------------------------------------------------------------------------

    private static String sanitizarLinea ( String linea ) {
        return linea.replaceAll( "[^a-zA-Z,ñÑ0-9 ]", "" ).trim ();
    }

    //----------------------------------------------------------------------------------------------

    private static boolean esAlumnoValido ( String linea, String regex ) {
        return linea.matches ( regex );
    }

    //----------------------------------------------------------------------------------------------

    public static void guardarAlumnos( ArrayList<Alumno> arrayList, Context context ){
        DB db = new DB( context );
        for ( Alumno alu : arrayList ){
            db.addAlumno( alu.getNoControl(), alu.getNombreCompleto() );
        }
    }

    //----------------------------------------------------------------------------------------------
}
