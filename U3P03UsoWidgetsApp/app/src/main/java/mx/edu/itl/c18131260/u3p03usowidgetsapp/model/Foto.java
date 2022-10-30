/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Modelo para crear objetos de la clase Foto.
:*
:*  Archivo     : AdminSQLiteOpenHelper.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 1/jun/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase es la estructura para los objetos de la clase foto, tiene atributos
:*                  fotoId, fotoArchivo, sesionId
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -               -                   -
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u3p03usowidgetsapp.model;

public class Foto {

    private Integer fotoId;
    private byte [] fotoArchivo;
    private Integer sesionId;
//------------------------------------------------------------------------------------------
    public Foto(Integer fotoId, byte [] fotoArchivo, Integer sesionId) {
        this.fotoId = fotoId;
        this.fotoArchivo = fotoArchivo;
        this.sesionId = sesionId;
    }
    //------------------------------------------------------------------------------------------
    public Integer getFotoId() {
        return fotoId;
    }
    //------------------------------------------------------------------------------------------
    public void setFotoId(Integer fotoId) {
        this.fotoId = fotoId;
    }
    //------------------------------------------------------------------------------------------
    public byte [] getFotoArchivo() {
        return fotoArchivo;
    }
    //------------------------------------------------------------------------------------------
    public void setFotoCodigo(byte [] fotoArchivo) {
        this.fotoArchivo = fotoArchivo;
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
    @Override
    public String toString(){
        String foto = "{" +
                " fotoId: " + fotoId+
                ", fotoArchivo" + fotoArchivo+
                ", sesionId: "+ sesionId+
                " }";
        return foto;
    }
    //------------------------------------------------------------------------------------------
}
