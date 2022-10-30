/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                              Clase Asistencias
:*
:*  Archivo     : Asistencia.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase Asistencias para estructurar la carga de asistencias en este objeto con diferentes
:*                atributos
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Objects;

public class Asistencia {
    private String fecha;
    private String nombre;
    private String noControl;
    private AsistenciaStatus status; // "PRESENTE" | "JUSTIFICADO"
    private GrupoEnum grupo;

    //----------------------------------------------------------------------------------------------

    public Asistencia( String fecha, String nombre, String noControl, AsistenciaStatus status, GrupoEnum grupo ) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.noControl = noControl;
        this.status = status;
        this.grupo = grupo;
    }

    //----------------------------------------------------------------------------------------------

    public String getNombre() {
        return nombre;
    }

    //----------------------------------------------------------------------------------------------

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getFecha() {
        return fecha;
    }

    //----------------------------------------------------------------------------------------------

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //----------------------------------------------------------------------------------------------

    public AsistenciaStatus getStatus() {
        return status;
    }

    //----------------------------------------------------------------------------------------------

    public void setStatus(AsistenciaStatus status) {
        this.status = status;
    }

    //----------------------------------------------------------------------------------------------

    public GrupoEnum getGrupo() {
        return grupo;
    }

    //----------------------------------------------------------------------------------------------

    public void setGrupo(GrupoEnum grupo) {
        this.grupo = grupo;
    }

    //----------------------------------------------------------------------------------------------
}
