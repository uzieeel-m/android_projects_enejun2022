/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                                Clase Total
:*
:*  Archivo     : Total.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase Total para estructurar la carga de totales de cada alumno en este objeto
:*                con diferentes atributos
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Objects;

public class Total {
    private String noControl;
    private String nombre;
    private int totalPresente;
    private int totalJustificado;
    private int total;
    private String porcentaje;

    //----------------------------------------------------------------------------------------------

    public Total( String noControl, String nombre, int totalPresente, int totalJustificado, int total, String porcentaje ) {
        this.noControl = noControl;
        this.nombre = nombre;
        this.totalPresente = totalPresente;
        this.totalJustificado = totalJustificado;
        this.total = total;
        this.porcentaje = porcentaje;
    }

    //----------------------------------------------------------------------------------------------

    public Total(){
        this.noControl = "";
        this.nombre = "";
        this.totalPresente = 0;
        this.totalJustificado = 0;
        this.total = 0;
        this.porcentaje = "";
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

    public String getNombre() {
        return nombre;
    }

    //----------------------------------------------------------------------------------------------

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //----------------------------------------------------------------------------------------------

    public int getTotalPresente() {
        return totalPresente;
    }

    //----------------------------------------------------------------------------------------------

    public void setTotalPresente(int totalPresente) {
        this.totalPresente = totalPresente;
    }

    //----------------------------------------------------------------------------------------------

    public int getTotalJustificado() {
        return totalJustificado;
    }

    //----------------------------------------------------------------------------------------------

    public void setTotalJustificado(int totalJustificado) {
        this.totalJustificado = totalJustificado;
    }

    //----------------------------------------------------------------------------------------------

    public int getTotal() {
        return total;
    }

    //----------------------------------------------------------------------------------------------

    public void setTotal(int total) {
        this.total = total;
    }

    //----------------------------------------------------------------------------------------------

    public String getPorcentaje() {
        return porcentaje;
    }

    //----------------------------------------------------------------------------------------------

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    //----------------------------------------------------------------------------------------------
}
