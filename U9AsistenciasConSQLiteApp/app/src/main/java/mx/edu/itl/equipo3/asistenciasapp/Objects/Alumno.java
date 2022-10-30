/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                              Clase Alumno
:*
:*  Archivo     : Alumno.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase Alumno para estructurar la carga de alumnos en este objeto con diferentes
:*                atributos
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Objects;

import java.util.ArrayList;

public class Alumno {
    private String noControl;
    private String nombreCompleto;
    private String[] nombres;
    private ArrayList<Grupo> grupos = new ArrayList<>();
    private ArrayList<Asistencia> asistencias = new ArrayList<>();

    //----------------------------------------------------------------------------------------------

    public Alumno( String noControl, String nombreCompleto, String[] nombres ) {
        this.noControl = noControl;
        this.nombreCompleto = nombreCompleto;
        this.nombres = nombres;
    }

    //----------------------------------------------------------------------------------------------

    public ArrayList<Asistencia> getAsistencias() {
        return asistencias;
    }

    //----------------------------------------------------------------------------------------------

    public void setAsistencias(ArrayList<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    //----------------------------------------------------------------------------------------------

    public String getNoControl() {
        return noControl;
    }

    //----------------------------------------------------------------------------------------------

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    //----------------------------------------------------------------------------------------------

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    //----------------------------------------------------------------------------------------------

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    //----------------------------------------------------------------------------------------------

    public String[] getNombres() {
        return nombres;
    }

    //----------------------------------------------------------------------------------------------

    public void setNombres(String[] nombres) {
        this.nombres = nombres;
    }

    //----------------------------------------------------------------------------------------------

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    //----------------------------------------------------------------------------------------------

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    //----------------------------------------------------------------------------------------------
}
