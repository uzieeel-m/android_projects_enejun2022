/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Clase para que un usuario nuevo se registre.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase proporciona los métodos que permiten que un usuario se registre a
:*                  la base de datos del sistema.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -               -                   -
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mx.edu.itl.c18131260.u3p03usowidgetsapp.bd.AdminSQLiteOpenHelper;

public class RegistroActivity extends AppCompatActivity {

    //declaracion de campos
    private EditText edtNombreUsuario;
    private EditText edtContrasena;
    private EditText edtContrasena2;
    private EditText edtNombreCompleto;
    private EditText edtCorreo;
    private EditText edtTelefono;

    //------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombreUsuario = findViewById(R.id.edtRegistroNombreUsuario);
        edtContrasena = findViewById(R.id.edtRegistroContrasena);
        edtContrasena2 = findViewById(R.id.edtRegistroContrasena2);
        edtNombreCompleto = findViewById(R.id.edtRegistroNombreCompleto);
        edtCorreo = findViewById(R.id.edtRegistroCorreo);
        edtTelefono = findViewById(R.id.edtRegistroTelefono);
    }

    //------------------------------------------------------------------------------------------
    public void registrarseClick(View view){
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getWritableDatabase();

            //obtener valores de los campos
            String usuarioUsername = edtNombreUsuario.getText().toString();
            String usuarioPass = edtContrasena.getText().toString();
            String usuarioPass2 = edtContrasena2.getText().toString();
            String usuarioNombre = edtNombreCompleto.getText().toString();
            String usuarioCorreo = edtCorreo.getText().toString();
            String usuarioTelefono = edtTelefono.getText().toString();

//            String insercion = "{\nusuarioUsername: "+usuarioUsername+",\nusuarioPass: "+usuarioPass+",\nusuarioNombre: "+usuarioNombre+",\nusuarioCorreo:"+usuarioCorreo+"\nusuarioTelefono: "+usuarioTelefono+"\n}";
//            Toast.makeText(this, insercion, Toast.LENGTH_LONG).show();

            //validar campos vacíos
            if(!usuarioUsername.isEmpty() &&
                    !usuarioPass.isEmpty() &&
                    !usuarioPass2.isEmpty() &&
                    !usuarioNombre.isEmpty() &&
                    !usuarioCorreo.isEmpty() &&
                    !usuarioTelefono.isEmpty()){
                if(validarContrasenas(usuarioPass, usuarioPass2)){
                    ContentValues valores = new ContentValues();
                    valores.put("usuarioUsername", usuarioUsername);
                    valores.put("usuarioPass", usuarioPass);
                    valores.put("usuarioNombre", usuarioNombre);
                    valores.put("usuarioCorreo", usuarioCorreo);
                    valores.put("usuarioTelefono", usuarioTelefono);

                    db.insert("usuarios", null, valores);
                    Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show();
                    db.close();
                    limpiarCampos();
                    Intent intent = new Intent(RegistroActivity.this, IniciarSesionActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }

    }

    //------------------------------------------------------------------------------------------
    private boolean validarContrasenas(String contrasena1, String contrasena2){
        return contrasena1.equals(contrasena2);
    }

    //------------------------------------------------------------------------------------------
    private void limpiarCampos(){
        edtNombreUsuario.setText("");
        edtContrasena.setText("");
        edtContrasena2.setText("");
        edtNombreCompleto.setText("");
        edtCorreo.setText("");
        edtTelefono.setText("");
    }

    //------------------------------------------------------------------------------------------
    public void regresarClick(View view){
        Intent intent = new Intent(RegistroActivity.this, IniciarSesionActivity.class);
        startActivity(intent);
        finish();
    }
    //------------------------------------------------------------------------------------------
}