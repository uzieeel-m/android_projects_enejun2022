/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Activity que muestra un form para agendar una sesión nueva.
:*
:*  Archivo     : NuevaSesionActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase presenta un form para que el usuario introduzca los datos requeridos
:*                  para agendar una sesión. Se compone de EditTexts, Spinners, un DatePopupPicker
:*                  para elegir la fecha, y el TimePupupPicker para elegir una hora.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  1/jun/2022  Uziel Montes         Integración de bases de datos SQLite.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import mx.edu.itl.c18131260.u3p03usowidgetsapp.bd.AdminSQLiteOpenHelper;

public class NuevaSesionActivity extends AppCompatActivity {

    //datos del usuario
    private Integer USER_ID;
    private String USER_USERNAME;

    private EditText etContactoNombre, etContactoTelefono, etContactoCorreo, edtNuevaSesionNombre;
    private Spinner spSesionPaquete, spSesionLugar;
    private Button btnSeleccionarHora, btnSeleccionarFecha;
    private TextView tvFechaSel, tvHoraSel;
    //para la fecha
    DatePickerDialog datePickerDialog;
    //para la hora
    private int horas, minutos;
    String [] sesionPaquetes, sesionLugares;

    //formulario
    private String sesionPaquete;
    private String sesionFecha;
    private String sesionHora;
    private String sesionLugar;

    //------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_sesion);

        //recuperar datos del usuario
        SharedPreferences preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        USER_ID = preferences.getInt("USER_ID", 0);
        USER_USERNAME = preferences.getString("USER_USERNAME", "USUARIO_NO_IDENTIFICADO");

        //get views
        etContactoNombre = findViewById(R.id.etContactoNombre);
        etContactoTelefono = findViewById(R.id.etContactoTelefono);
        etContactoCorreo = findViewById(R.id.etContactoCorreo);
        spSesionPaquete = findViewById(R.id.spSesionPaquete);
        spSesionLugar = findViewById(R.id.spSesionLugar);
        btnSeleccionarHora = findViewById(R.id.btnSesionHora);
        btnSeleccionarFecha = findViewById(R.id.btnSeleccionarFecha);
        tvFechaSel = findViewById(R.id.tvFechaSel);
        tvHoraSel = findViewById(R.id.tvHoraSel);
        edtNuevaSesionNombre = findViewById(R.id.edtNuevaSesionNombre);

        sesionPaquetes = getResources().getStringArray(R.array.paquetes);
        sesionLugares = getResources().getStringArray(R.array.lugares);

        //adapter para el spinner del paquete
        ArrayAdapter spaSesionPaquete = ArrayAdapter.createFromResource(this,
                R.array.paquetes, android.R.layout.simple_spinner_item);
        spaSesionPaquete.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSesionPaquete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        //Paquete económico
                        sesionPaquete = sesionPaquetes[0];
                        break;
                    case 1:
                        //paquete básico
                        sesionPaquete = sesionPaquetes[1];
                        break;
                    case 2:
                        //paquete premium
                        sesionPaquete = sesionPaquetes[2];
                        break;
                    default:
                        //paquete económico
                        sesionPaquete = sesionPaquetes[0];
                        break;
                }
            }

            //--------------------------------------------------------------------------------------
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
            //--------------------------------------------------------------------------------------
        });

        //adapter para el spinner del lugar
        ArrayAdapter spaSesionLugar = ArrayAdapter.createFromResource(this,
                R.array.lugares, android.R.layout.simple_spinner_item);
        spaSesionLugar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSesionLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        //Bosque Urbano
                        sesionLugar = sesionLugares[0];
                        break;
                    case 1:
                        //venustiano
                        sesionLugar = sesionLugares[1];
                        break;
                    case 2:
                        //parque fundadores
                        sesionLugar = sesionLugares[2];
                        break;
                    case 3:
                        //alameda
                        sesionLugar = sesionLugares[3];
                        break;
                    case 4:
                        //otro
                        sesionLugar = sesionLugares[4];
                        break;
                    default:
                        //bosque urbano
                        sesionLugar = sesionLugares[0];
                        break;
                }
            }

            //--------------------------------------------------------------------------------------
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //hacer nada
            }
            //--------------------------------------------------------------------------------------
        });

        iniciarDatePicker();
        autoLlenarCamposContacto();
    }

    //------------------------------------------------------------------------------------------
    private void autoLlenarCamposContacto() {
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getReadableDatabase();

            String query = "SELECT usuarioNombre, usuarioCorreo, usuarioTelefono FROM usuarios WHERE usuarioId = "+USER_ID;
            Cursor fila = db.rawQuery(query, null);
            if (fila.moveToFirst()){
                String usuarioNombre = fila.getString(0);
                String usuarioCorreo = fila.getString(1);
                String usuarioTelefono = fila.getString(2);

                etContactoNombre.setText(usuarioNombre);
                etContactoCorreo.setText(usuarioCorreo);
                etContactoTelefono.setText(usuarioTelefono);

                fila.close();
                db.close();
            } else {
                Toast.makeText(this, "No se encontró el usuario :(", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    //------------------------------------------------------------------------------------------
    public void iniciarDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onDateSet(DatePicker datePicker, int anho, int mes, int dia) {
                mes+=1;
                sesionFecha = dia+"/"+mes+"/"+anho;
                tvFechaSel.setText(sesionFecha);
            }
        };

        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int anho = calendario.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, anho, mes, dia);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    //------------------------------------------------------------------------------------------
    public void agendarSesionClick(View view){
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getWritableDatabase();

            //obtener datos
            String sesionNombre = edtNuevaSesionNombre.getText().toString();
            String sesionFecha = tvFechaSel.getText().toString();
            String sesionHora = tvHoraSel.getText().toString();
            String sesionLugar = spSesionLugar.getSelectedItem().toString();
            String sesionPaquete = spSesionPaquete.getSelectedItem().toString();
            Integer usuarioId = USER_ID;

            //validar campos vacíos
            if(!sesionNombre.isEmpty() && !sesionFecha.isEmpty() && !sesionHora.isEmpty() && !sesionLugar.isEmpty() && !sesionPaquete.isEmpty()){
                ContentValues nuevaSesion = new ContentValues();

                nuevaSesion.put("sesionNombre", sesionNombre);
                nuevaSesion.put("sesionFecha", sesionFecha);
                nuevaSesion.put("sesionHora", sesionHora);
                nuevaSesion.put("sesionLugar", sesionLugar);
                nuevaSesion.put("sesionPaquete", sesionPaquete);
                nuevaSesion.put("usuarioId", usuarioId);

                db.insert("sesiones", null, nuevaSesion);
                Toast.makeText(this, "Sesión agendada exitosamente", Toast.LENGTH_SHORT).show();
                db.close();
                Intent intent = new Intent(NuevaSesionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Debes llenar todos los campos!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    //------------------------------------------------------------------------------------------
    public void seleccionarFechaClick(View view){
        //abrir popup del calendario
        datePickerDialog.show();
    }

    //------------------------------------------------------------------------------------------
    public void popTimePicker(View view) {
        //
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int horaSeleccionada, int minutoSeleccionado) {
                horas = horaSeleccionada;
                minutos = minutoSeleccionado;
                sesionHora = horas+":"+minutos;
                tvHoraSel.setText(sesionHora);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, horas, minutos, true);
        timePickerDialog.setTitle("Selecciona la hora de tu sesión");
        timePickerDialog.show();
    }

    //------------------------------------------------------------------------------------------
    public void cancelarClick(View view){
        Intent intent = new Intent(NuevaSesionActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    //------------------------------------------------------------------------------------------
}