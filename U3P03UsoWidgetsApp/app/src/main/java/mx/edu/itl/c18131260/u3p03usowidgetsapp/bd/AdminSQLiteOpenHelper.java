/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Activity para mostrar la sesión que el usuario seleccionó previamente.
:*
:*  Archivo     : AdminSQLiteOpenHelper.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 1/jun/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase sirve como helper para inicializar la base de datos, tiene los
:*                  siguientes métodos, y cada uno hace:
:*                  - constructor. Inicializa un objeto de la clase con el que se puede manipular la bd.
:*                  - onCreate. Este método contiene la creación de las tablas que componen la bd.
:*                              sólo se ejecuta 1 vez cuando se llama inicialmente al constructor.
:*                  - onUpgrade. Este borra las tablas, por si se actualiza la bd a una nueva versión.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -               -                   -
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u3p03usowidgetsapp.bd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    Context contexto;

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String dbName, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //crear tabla donde se guardarán las fotos
                db.execSQL("CREATE TABLE usuarios(" +
                        "usuarioId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "usuarioUsername TEXT, " +
                        "usuarioPass TEXT, " +
                        "usuarioNombre TEXT, " +
                        "usuarioCorreo TEXT, " +
                        "usuarioTelefono TEXT" +
                        ")"
                );
                db.execSQL("CREATE TABLE sesiones(" +
                        "sesionId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "sesionNombre TEXT, " +
                        "sesionFecha INTEGER, " +
                        "sesionHora INTEGER, " +
                        "sesionLugar TEXT, " +
                        "sesionPaquete TEXT, " +
                        "usuarioId INTEGER," +
                        " FOREIGN KEY(usuarioId) REFERENCES usuarios(usuarioId) " +
                        ")"
                );
                db.execSQL("CREATE TABLE fotos( " +
                        "fotoId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "fotoArchivo BLOB, " +
                        "sesionId INTEGER, " +
                        "FOREIGN KEY(sesionId) REFERENCES sesiones(sesionId)" +
                        ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS sesiones");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fotos");
    }
}
