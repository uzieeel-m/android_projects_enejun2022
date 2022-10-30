/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Modelo para crear objetos de la clase Sesion.
:*
:*  Archivo     : Sesion.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase contiene la estructura de una sesión fotográfica, que se compone de:
:*                  sesionId , sesionNombre, sesionFecha, sesionHora, sesionLugar, sesionPaquete, usuarioId
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  1/jun/2022  Uziel Montes         Integración de bases de datos SQLite.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp.model;

public class Sesion {

    private Integer sesionId;
    private String sesionNombre;
    private String sesionFecha;
    private String sesionHora;
    private String sesionLugar;
    private String sesionPaquete;
    private Integer usuarioId;

    //------------------------------------------------------------------------------------------
    public Sesion(Integer sesionId, String sesionNombre, String sesionFecha, String sesionHora, String sesionLugar, String sesionPaquete, Integer usuarioId) {
        this.sesionId = sesionId;
        this.sesionNombre = sesionNombre;
        this.sesionFecha = sesionFecha;
        this.sesionHora = sesionHora;
        this.sesionLugar = sesionLugar;
        this.sesionPaquete = sesionPaquete;
        this.usuarioId = usuarioId;
    }
    //------------------------------------------------------------------------------------------
    public Integer getSesionId() {
        return sesionId;
    }
    //------------------------------------------------------------------------------------------
    public void setSesionId(Integer sesionId) {
        this.sesionId = sesionId;
    }
    //------------------------------------------------------------------------------------------
    public String getSesionNombre() {
        return sesionNombre;
    }
    //------------------------------------------------------------------------------------------
    public void setSesionNombre(String sesionNombre) {
        this.sesionNombre = sesionNombre;
    }
    //------------------------------------------------------------------------------------------
    public String getSesionFecha() {
        return sesionFecha;
    }
    //------------------------------------------------------------------------------------------
    public void setSesionFecha(String sesionFecha) {
        this.sesionFecha = sesionFecha;
    }
    //------------------------------------------------------------------------------------------
    public String getSesionHora() {
        return sesionHora;
    }
    //------------------------------------------------------------------------------------------
    public void setSesionHora(String sesionHora) {
        this.sesionHora = sesionHora;
    }
    //------------------------------------------------------------------------------------------
    public String getSesionLugar() {
        return sesionLugar;
    }
    //------------------------------------------------------------------------------------------
    public void setSesionLugar(String sesionLugar) {
        this.sesionLugar = sesionLugar;
    }
    //------------------------------------------------------------------------------------------
    public String getSesionPaquete() {
        return sesionPaquete;
    }
    //------------------------------------------------------------------------------------------
    public void setSesionPaquete(String sesionPaquete) {
        this.sesionPaquete = sesionPaquete;
    }
    //------------------------------------------------------------------------------------------
    public Integer getUsuarioId() {
        return usuarioId;
    }
    //------------------------------------------------------------------------------------------
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    //------------------------------------------------------------------------------------------
    @Override
    public String toString(){
        String sesion = "{  " +
                            "sesionId: "+sesionId+
                            "sesionNombre: "+ sesionNombre+
                            ", sesionFecha: "+ sesionFecha+
                            ", sesionHora: "+ sesionHora+
                            ", sesionLugar: "+sesionLugar+
                            ", sesionPaquete: "+ sesionPaquete+
                            ", usuarioId: "+usuarioId+
                        "}";
        return sesion;
    }
    //------------------------------------------------------------------------------------------
}
