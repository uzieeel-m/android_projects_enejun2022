/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Clase principal de la aplicación que muestra las sesiones
:*                      del usuario.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase es la que ve primero el usuario, cuenta con, en orden de aparición:
:*                  Button: para abrir el AcercaDe
:*                  Spinner: para que, en futuras versiones, se puedan ordenar las sesiones según
:*                  el criterio seleccionado
:*                  ListView: para enlistar todas las sesiones
:*                  PopupMenu: se aplica a cada elemento del listView, tiene las opciones de Abrir,
:*                  Esconder y Eliminar.
:*                  Button: para abrir el formulario para agendar una sesión nueva
:*              Este archivo contiene 2 clases, la segunda es el adaptador para el listview, que
:*              se basa en la clase ArrayAdapter para poner las fotos en cada elemento de la lista con
:*              base en un arraylist de sesiones.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  1/jun/2022  Uziel Montes         Integración de bases de datos SQLite.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.edu.itl.c18131260.u3p03usowidgetsapp.model.Sesion;
import mx.edu.itl.c18131260.u3p03usowidgetsapp.bd.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    //datos del usuario
    private Integer USER_ID;
    private String USER_USERNAME;
    //para saber si es vista de admin
    private Boolean ES_ADMIN = false;

    private TextView txtBienvenida;
    private ListView lv_sesiones;
    private ArrayList<Sesion> listaSesiones;
    private Button btnNuevaSesion;

    //spinner
    private Spinner sp_ordenar;
    
//------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperar datos del usuario de sharedPreferences
        SharedPreferences preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        USER_ID = preferences.getInt("USER_ID", 0);
        USER_USERNAME = preferences.getString("USER_USERNAME", "USUARIO_NO_IDENTIFICADO");
        if(USER_USERNAME.equals("admin1")){
            ES_ADMIN = true;
        }

        lv_sesiones = findViewById(R.id.lv_sesiones);
        sp_ordenar = findViewById(R.id.sp_ordenar);
        txtBienvenida = findViewById(R.id.txtBienvenida);
        btnNuevaSesion = findViewById(R.id.btnAgendar);

        txtBienvenida.setText(getString(R.string.bienvenido) +", "+USER_USERNAME);

        //inicializar lista de sesiones para mostrarlas
        listaSesiones = new ArrayList<>();

        //llenar arreglo de sesiones del usuario (si es que tiene)
        llenarListaSesiones();
        //mostrar sesiones en el list view
        lv_sesiones.setAdapter(new Adapter(this, listaSesiones));

        //adaptador del spinner
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this,
                R.array.ordenar, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ordenar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //ordenar el list view por la opcion que se haya elegido
                switch (i){
                    case 0:
                        //nombre a-z
                        break;
                    case 1:
                        //nombre z-a
                        break;
                    case 2:
                        //fecha asc
                        break;
                    case 3:
                        //fecha desc
                        break;
                    default:
                        //nombre a-z
                        break;
                }
                //Toast.makeText(adapterView.getContext(), "Orden tipo: "+i, Toast.LENGTH_SHORT).show();
            }
            //--------------------------------------------------------------------------------------
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //hacer nada
            }
        });

        if(ES_ADMIN){
            btnNuevaSesion.setVisibility(View.GONE);
        }
    }
//------------------------------------------------------------------------------------------
    private void llenarListaSesiones() {
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getReadableDatabase();

            String query = "";
            if(!ES_ADMIN){
                query = "SELECT sesionId, sesionNombre, sesionFecha, sesionHora, sesionLugar, sesionPaquete, sesiones.usuarioId " +
                        "FROM sesiones INNER JOIN usuarios " +
                        "ON usuarios.usuarioId = sesiones.usuarioId" +
                        " WHERE sesiones.usuarioId = "+USER_ID;
            } else {
                query = "SELECT sesionId, sesionNombre, sesionFecha, sesionHora, sesionLugar, sesionPaquete, sesiones.usuarioId "+
                        "FROM sesiones";
            }

            Cursor fila = db.rawQuery(query, null);

            if(fila.getCount() >= 1){
                while(fila.moveToNext()){
                    //obtener datos de la fila
                    Integer sesionId = fila.getInt(0);
                    String sesionNombre = fila.getString(1);
                    String sesionFecha = fila.getString(2);
                    String sesionHora = fila.getString(3);
                    String sesionLugar = fila.getString(4);
                    String sesionPaquete = fila.getString(5);
                    Integer usuarioId = fila.getInt(6);

                    //crear objeto Sesion
                    Sesion sesion = new Sesion(sesionId, sesionNombre, sesionFecha, sesionHora, sesionLugar, sesionPaquete, usuarioId);

                    //mostrar los datos en el list view
                    listaSesiones.add(sesion);
                }

            } else {
                Toast.makeText(this, getString(R.string.sin_sesiones), Toast.LENGTH_SHORT).show();
            }
            fila.close();
            db.close();
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }
//------------------------------------------------------------------------------------------
    public void agendarSesionClick(View view){
        Intent tent = new Intent(MainActivity.this, NuevaSesionActivity.class);
        startActivity(tent);
        finish();
    }
//------------------------------------------------------------------------------------------
    public void acercaDeClick(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }
//------------------------------------------------------------------------------------------
    public void cerrarSesionClick(View view){
        Intent intent = new Intent(MainActivity.this, IniciarSesionActivity.class);
        startActivity(intent);
        finish();
    }
//------------------------------------------------------------------------------------------
}

//------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------
class Adapter extends ArrayAdapter<Sesion> implements View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {

    private Context context;
    private ArrayList<Sesion> listaSesiones;
    private int positionArreglo;

    //------------------------------------------------------------------------------------------
    public Adapter(@NonNull Context context, ArrayList<Sesion> listaSesiones) {
        super(context, R.layout.lvitem_sesion, listaSesiones);
        this.context = context;
        this.listaSesiones = listaSesiones;
    }

    //------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        this.positionArreglo = position;
        Sesion sesion = listaSesiones.get(position);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lvitem_sesion, parent, false);
        }

        TextView nombre_sesion = convertView.findViewById(R.id.lvitem_nombre_sesion);
        TextView fecha_sesion = convertView.findViewById(R.id.lvitem_fecha_sesion);

        nombre_sesion.setText(sesion.getSesionNombre());
        fecha_sesion.setText(sesion.getSesionFecha());

        convertView.setOnLongClickListener(this);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SesionActivity.class);
                intent.putExtra("sesionId", listaSesiones.get(position).getSesionId());
                context.startActivity(intent);
            }
        });
        
        return convertView;
    }
    //------------------------------------------------------------------------------------------
    @Override
    public boolean onLongClick(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup_sesion, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();
        return false;
    }
    //------------------------------------------------------------------------------------------
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        try{
            int id = menuItem.getItemId();
            switch (id){
                case R.id.pmi_abrir:
                    //abrir la sesion
                    Intent intent = new Intent(context, SesionActivity.class);
                    intent.putExtra("sesionId", listaSesiones.get(positionArreglo).getSesionId());
                    context.startActivity(intent);
                    break;
                case R.id.pmi_esconder:
                    //esconder sesión
                    Toast.makeText(context, "La sesión seleccionada se esconderá algún día...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pmi_eliminar:
                    //eliminar sesión
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Eliminar sesión")
                            .setMessage("¿Estás seguro de querer eliminar la sesión seleccionada?")
                            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, "La sesión seleccionada se eliminará algún día...", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false).create().show();
                    break;
            }
        } catch (Exception ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
        }
        return false;
    }
}