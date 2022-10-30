/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Modelo para crear objetos de la clase Usuario.
:*
:*  Archivo     : Sesion.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 1/jun/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase contiene la estructura de un usuario, que se compone de:
:*                  usuarioId, usuarioUsername, usuarioPass, usuarioNombre, usuarioCorreo, usuarioTelefono
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -             -                     -
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp.model;

public class Usuario {

    private Integer usuarioId;
    private String usuarioUsername;
    private String usuarioPass;
    private String usuarioNombre;
    private String usuarioCorreo;
    private String usuarioTelefono;

    //------------------------------------------------------------------------------------------
    public Usuario(Integer usuarioId, String usuarioUsername, String usuarioPass, String usuarioNombre, String usuarioCorreo, String usuarioTelefono) {
        this.usuarioId = usuarioId;
        this.usuarioUsername = usuarioUsername;
        this.usuarioPass = usuarioPass;
        this.usuarioNombre = usuarioNombre;
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioTelefono = usuarioTelefono;
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
    public String getUsuarioUsername() {
        return usuarioUsername;
    }
    //------------------------------------------------------------------------------------------
    public void setUsuarioUsername(String usuarioUsername) {
        this.usuarioUsername = usuarioUsername;
    }
    //------------------------------------------------------------------------------------------
    public String getUsuarioPass() {
        return usuarioPass;
    }
    //------------------------------------------------------------------------------------------
    public void setUsuarioPass(String usuarioPass) {
        this.usuarioPass = usuarioPass;
    }
    //------------------------------------------------------------------------------------------
    public String getUsuarioNombre() {
        return usuarioNombre;
    }
    //------------------------------------------------------------------------------------------
    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
    //------------------------------------------------------------------------------------------
    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }
    //------------------------------------------------------------------------------------------
    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }
    //------------------------------------------------------------------------------------------
    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }
    //------------------------------------------------------------------------------------------
    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }
    //------------------------------------------------------------------------------------------
    @Override
    public String toString(){
        String usuario = "{" +
                "usuarioId: "+ usuarioId+
                "usuarioUsername:  " + usuarioUsername+
                "usuarioPass: " + usuarioPass +
                "usuarioNombre: " + usuarioNombre +
                "usuarioCorreo: " + usuarioCorreo+
                "usuarioTelefono: " + usuarioTelefono+
                "}";
        return usuario;
    }
    //------------------------------------------------------------------------------------------
}
