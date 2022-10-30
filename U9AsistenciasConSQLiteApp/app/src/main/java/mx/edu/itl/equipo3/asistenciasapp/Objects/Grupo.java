/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                                  Clase Grupo
:*
:*  Archivo     : Grupo.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase Grupo para estructurar la carga de grupos en este objeto con diferentes
:*                atributos
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Objects;

public class Grupo {
    private int id;
    private String nombre;
    private String profe;
    private int clases;

    //----------------------------------------------------------------------------------------------

    public Grupo( int id, String nombre, String profe, int clases ) {
        this.id = id;
        this.nombre = nombre;
        this.profe = profe;
        this.clases = clases;
    }

    //----------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    //----------------------------------------------------------------------------------------------

    public void setId(int id) {
        this.id = id;
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

    public String getProfe() {
        return profe;
    }

    //----------------------------------------------------------------------------------------------

    public void setProfe(String profe) {
        this.profe = profe;
    }

    //----------------------------------------------------------------------------------------------

    public int getClases() {
        return clases;
    }

    //----------------------------------------------------------------------------------------------

    public void setClases(int clases) {
        this.clases = clases;
    }

    //----------------------------------------------------------------------------------------------
}
