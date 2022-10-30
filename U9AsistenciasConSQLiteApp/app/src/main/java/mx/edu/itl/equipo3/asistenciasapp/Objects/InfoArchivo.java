/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                              Clase InfoArchivo
:*
:*  Archivo     : InfoArchivo.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase InfoArchivo para estructurar la carga de informacion de cada archivo
:*                en este objeto con diferentes atributos
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Objects;

import java.io.File;

public class InfoArchivo {
    private String nombre;
    private String pesoKB;
    private String path;
    private String fullPath;
    private File archivo;
    private GrupoEnum grupo;
    private String fecha;

    //----------------------------------------------------------------------------------------------

    public InfoArchivo( String nombre, String pesoKB, String path, String fullPath, File archivo, GrupoEnum grupo, String fecha ) {
        this.nombre = nombre;
        this.pesoKB = pesoKB;
        this.path = path;
        this.fullPath = fullPath;
        this.archivo = archivo;
        this.grupo = grupo;
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    //----------------------------------------------------------------------------------------------

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //----------------------------------------------------------------------------------------------

    public File getArchivo() {
        return archivo;
    }

    //----------------------------------------------------------------------------------------------

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    //----------------------------------------------------------------------------------------------

    public String getPath() {
        return path;
    }

    //----------------------------------------------------------------------------------------------

    public void setPath(String path) {
        this.path = path;
    }

    //----------------------------------------------------------------------------------------------

    public String getFullPath() {
        return fullPath;
    }

    //----------------------------------------------------------------------------------------------

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
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

    public String getPesoKB() {
        return pesoKB;
    }

    //----------------------------------------------------------------------------------------------

    public void setPesoKB(String pesoKB) {
        this.pesoKB = pesoKB;
    }

    //----------------------------------------------------------------------------------------------
}
