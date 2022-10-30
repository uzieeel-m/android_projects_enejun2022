/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                  Clase que maneja toda la base de datos SQLite
:*
:*  Archivo     : DB.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase que crea las tablas, con metodos para limpiar, obtener e insertar informacion
:*                en cada una de las tablas
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import mx.edu.itl.equipo3.asistenciasapp.Objects.Alumno;
import mx.edu.itl.equipo3.asistenciasapp.Objects.Asistencia;
import mx.edu.itl.equipo3.asistenciasapp.Objects.AsistenciaStatus;
import mx.edu.itl.equipo3.asistenciasapp.Objects.Grupo;
import mx.edu.itl.equipo3.asistenciasapp.Objects.GrupoEnum;

public class DB extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "asistencias.db";
    private static final int VERSION_BD = 1;
    //Tablas
    private static final String TABLE_ALUMNOS = "CREATE TABLE ALUMNOS " +
                                                "(NOCONTROL VARCHAR PRIMARY KEY, NOMBRE VARCHAR)";
    private static final String TABLE_GRUPOS = "CREATE TABLE GRUPOS " +
                                                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR, MAESTRO VARCHAR, TOTAL_CLASES INTEGER, " +
                                                "UNIQUE (NOMBRE) )";
    private static final String TABLE_ASISTENCIAS = "CREATE TABLE ASISTENCIAS " +
                                                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FECHASTR VARCHAR, ESTATUS VARCHAR, ID_GRUPO INTEGER, NOCONTROL_ALUMNO VARCHAR," +
                                                "FOREIGN KEY(NOCONTROL_ALUMNO) REFERENCES ALUMNOS(NOCONTROL), FOREIGN KEY(ID_GRUPO) REFERENCES GRUPOS(ID), " +
                                                "UNIQUE(FECHASTR, ID_GRUPO, NOCONTROL_ALUMNO))";

    //----------------------------------------------------------------------------------------------

    public DB ( Context context ) {
        super( context, NOMBRE_BD, null, VERSION_BD );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( TABLE_GRUPOS );
        db.execSQL( TABLE_ALUMNOS );
        db.execSQL( TABLE_ASISTENCIAS );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "DROP TABLE IF EXISTS GRUPOS" );
        db.execSQL( "DROP TABLE IF EXISTS ALUMNOS" );
        db.execSQL( "DROP TABLE IF EXISTS ASISTENCIAS" );
        onCreate( db );
    }

    //----------------------------------------------------------------------------------------------

    public void clearDataBase(){
        SQLiteDatabase dbWrite = getWritableDatabase();
        dbWrite.execSQL( "DROP TABLE IF EXISTS GRUPOS" );
        dbWrite.execSQL( "DROP TABLE IF EXISTS ALUMNOS" );
        dbWrite.execSQL( "DROP TABLE IF EXISTS ASISTENCIAS" );
        onCreate( dbWrite );
    }

    //----------------------------------------------------------------------------------------------

    public void clearAsistencias(){
        SQLiteDatabase dbWrite = getWritableDatabase();
        dbWrite.execSQL( "DROP TABLE IF EXISTS GRUPOS" );
        dbWrite.execSQL( "DROP TABLE IF EXISTS ASISTENCIAS" );
        dbWrite.execSQL( TABLE_GRUPOS );
        dbWrite.execSQL( TABLE_ASISTENCIAS );
    }

    //----------------------------------------------------------------------------------------------

    public void addAlumno( String noControl, String nombre ) {
        SQLiteDatabase dbWrite = getWritableDatabase();
        if( dbWrite != null ) {
            dbWrite.execSQL( "INSERT INTO ALUMNOS (NOCONTROL, NOMBRE) VALUES ('"+noControl+"','"+nombre+"')" );
            dbWrite.close();
        }
    }

    //----------------------------------------------------------------------------------------------

    public void addGrupo( String nombre, String maestro, int clases ) {
        SQLiteDatabase dbWrite = getWritableDatabase();
        if( dbWrite != null ) {
            dbWrite.execSQL( "INSERT OR IGNORE INTO GRUPOS (NOMBRE, MAESTRO, TOTAL_CLASES) VALUES ('"+nombre+"','"+maestro+"'," + clases + ")" );
            dbWrite.close();
        }
    }

    //----------------------------------------------------------------------------------------------

    public ArrayList<Grupo> getGrupos () {
        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor cursor = dbRead.rawQuery( "SELECT ID, NOMBRE, MAESTRO, TOTAL_CLASES FROM GRUPOS", null );
        ArrayList<Grupo> grupos = new ArrayList<>();
        if ( cursor.moveToFirst() ) {
            do{
                grupos.add(new Grupo(
                        Integer.parseInt( cursor.getString(0) ),
                        cursor.getString ( 1 ),
                        cursor.getString ( 2 ),
                        Integer.parseInt ( cursor.getString ( 3 ) )
                ));
            } while ( cursor.moveToNext() );
        }
        return grupos;
    }

    //----------------------------------------------------------------------------------------------

    public void addAsistencia( String fechaStr, String estatus, int idGrupo, String noControl ) {
        SQLiteDatabase dbWrite = getWritableDatabase();
        if( dbWrite != null ) {
            dbWrite.execSQL( "INSERT OR IGNORE INTO ASISTENCIAS ( FECHASTR, ESTATUS, ID_GRUPO, NOCONTROL_ALUMNO) VALUES " +
                    "('"+fechaStr+"','"+estatus+"',"+idGrupo+",'"+noControl+"') " );
                    //+ "ON CONFLICT DO NOTHING");
            dbWrite.close();
        }
    }

    //----------------------------------------------------------------------------------------------

    public ArrayList<Alumno> getAlumnos(){
        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor cursor = dbRead.rawQuery( "SELECT * FROM ALUMNOS", null );
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String [] nombres = { "nombre" };
        if( cursor.moveToFirst() ){
            do {
                alumnos.add( new Alumno(cursor.getString(0), cursor.getString(1), nombres) );
            }while ( cursor.moveToNext() );
        }
        return  alumnos;
    }

    //----------------------------------------------------------------------------------------------

    public ArrayList<Asistencia> getAsistencias ( String noControl, int idGrupo ) {
        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor cursor
                = dbRead.rawQuery(
                    "SELECT ASISTENCIAS.ID, ASISTENCIAS.FECHASTR, ALUMNOS.NOMBRE, ASISTENCIAS.NOCONTROL_ALUMNO, ASISTENCIAS.ESTATUS, GRUPOS.NOMBRE " +
                    "FROM ASISTENCIAS " +
                    "INNER JOIN ALUMNOS  ON (ALUMNOS.NOCONTROL = ASISTENCIAS.NOCONTROL_ALUMNO) " +
                    "INNER JOIN GRUPOS ON (GRUPOS.ID = ASISTENCIAS.ID_GRUPO) " +
                            "WHERE GRUPOS.ID like " + idGrupo + addFiltersAsistencias(noControl)
                            //"AND ASISTENCIAS.NOCONTROL_ALUMNO = "+noControl+""
                    //+ addFiltersAsistencias ( noControl, idGrupo ),
                    ,null
                );

        ArrayList<Asistencia> asistencias = new ArrayList<>();
        if( cursor.moveToFirst() ){
            do{
                asistencias.add(
                        new Asistencia(
                                cursor.getString ( 1 ),
                                cursor.getString ( 2 ),
                                cursor.getString ( 3 ),
                                AsistenciaStatus.valueOf( cursor.getString ( 4 ) ),
                                GrupoEnum.valueOf ( cursor.getString ( 5 ) )
                        )
                );
            }while( cursor.moveToNext() );
        }
        return asistencias;
    }

    //----------------------------------------------------------------------------------------------

    public ArrayList<Asistencia> getAllAsistencias () {
        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor cursor
                = dbRead.rawQuery(
                "SELECT ASISTENCIAS.ID, ASISTENCIAS.FECHASTR, ALUMNOS.NOMBRE, ASISTENCIAS.NOCONTROL_ALUMNO, ASISTENCIAS.ESTATUS, GRUPOS.NOMBRE " +
                        "FROM ASISTENCIAS " +
                        "INNER JOIN ALUMNOS  ON (ALUMNOS.NOCONTROL = ASISTENCIAS.NOCONTROL_ALUMNO) " +
                        "INNER JOIN GRUPOS ON (GRUPOS.ID = ASISTENCIAS.ID_GRUPO)"
                ,null
        );

        ArrayList<Asistencia> asistencias = new ArrayList<>();
        if( cursor.moveToFirst() ){
            do{
                asistencias.add(
                        new Asistencia(
                                cursor.getString ( 1 ),
                                cursor.getString ( 2 ),
                                cursor.getString ( 3 ),
                                AsistenciaStatus.valueOf( cursor.getString ( 4 ) ),
                                GrupoEnum.valueOf ( cursor.getString ( 5 ) )
                        )
                );
            }while( cursor.moveToNext() );
        }
        return asistencias;
    }

    //----------------------------------------------------------------------------------------------

    private String addFiltersAsistencias ( String noControl ) {
        String where = "";

        if ( noControl.isEmpty() ) where = "";

        if ( !noControl.isEmpty() ) where = " AND ASISTENCIAS.NOCONTROL_ALUMNO = "+noControl+"";

        return where;
    }

    //----------------------------------------------------------------------------------------------
}
