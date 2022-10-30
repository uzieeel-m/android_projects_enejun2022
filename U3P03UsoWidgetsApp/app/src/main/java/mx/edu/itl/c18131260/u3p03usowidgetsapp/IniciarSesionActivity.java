/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Activity para que los usuarios inicien sesión.
:*
:*  Archivo     : IniciarSesionActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 1/jun/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase muestra permite a un usuario ingresar su nombre de usuario y contraseña
:*                  para iniciar sesión y así ingresar a la app. También se le da la opción de crear
:*                  ua cuenta si no tiene una.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -               -                   -
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u3p03usowidgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mx.edu.itl.c18131260.u3p03usowidgetsapp.bd.AdminSQLiteOpenHelper;

public class IniciarSesionActivity extends AppCompatActivity {

    private EditText edtNombreUsuario;
    private EditText edtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        edtNombreUsuario = findViewById(R.id.edtNombreUsuario);
        edtContrasena = findViewById(R.id.edtContrasena);
    }

    public void iniciarSesionClick(View view){
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getReadableDatabase();

            String usuarioUsername = edtNombreUsuario.getText().toString();
            String usuarioPass = edtContrasena.getText().toString();

            if(!usuarioUsername.isEmpty() && !usuarioPass.isEmpty()){
                //consulta
                String query = "SELECT usuarioId, usuarioUsername, usuarioPass FROM usuarios " +
                        "WHERE usuarioUsername = ?";
                //ejecutar consulta
                String [] args = {usuarioUsername};
                Cursor fila = db.rawQuery(query, args);

                //revisar si consulta tiene valores
                if(fila.moveToFirst()){
                    //sí tiene valores, recogerlos
                    Integer id = fila.getInt(0);
                    String username = fila.getString(1);
                    String password = fila.getString(2);
                    //verificar que las contraseñas coincidan
                    if(verificarContrasenas(password, usuarioPass)){
                        //sí coinciden, entrar
                        fila.close();
                        db.close();
                        //guardar datos del usuario en SharedPreferences
                        SharedPreferences preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("USER_ID", id);
                        editor.putString("USER_USERNAME", username);
                        editor.commit();
                        Intent intent = new Intent(IniciarSesionActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //contraseñas no coinciden
                        Toast.makeText(this, "Contraseña incorrecta, intenta de nuevo.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Nombre de usuario incorrecto!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Debes introducir tu nombre usuario y contraseña para iniciar sesión!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void registrarseClick(View view){
        Intent intent = new Intent(IniciarSesionActivity.this, RegistroActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean verificarContrasenas(String contrasenaBD, String contrasenaUSER){
        return contrasenaBD.equals(contrasenaUSER);
    }

}