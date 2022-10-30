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
:*  Archivo     : SesionActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase muestra la sesión seleccionada, se muestran sus datos (nombre y fecha),
:*                    así como las fotos que pertenecen a ella. Para mostrar las fotos, se utiliza un
:*                    GridView. En este view se implpementó el Menú de Acción Contextual, que se
:*                      muestra al hacer click en cada foto; este menú ofrece las acciones de
:*                      ampliar, descargar, esconder y eliminar, para que sean implementados en
:*                      futuras versiones de la app.
:*              Este archivo contiene 2 clases, la segunda es el adaptador para el gridview, que
:*              se basa en la clase ArrayAdapter para poner las fotos en cada elemento del grid con
:*              base en un arreglo de ids.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  1/jun/2022  Uziel Montes         Integración de bases de datos SQLite.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import mx.edu.itl.c18131260.u3p03usowidgetsapp.model.Foto;
import mx.edu.itl.c18131260.u3p03usowidgetsapp.model.Sesion;
import mx.edu.itl.c18131260.u3p03usowidgetsapp.bd.AdminSQLiteOpenHelper;

public class SesionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //datos de la sesión
    private Integer USER_ID;
    private String USER_USERNAME;
    private Integer SESION_ID;
    //para saber si es vista de admin
    private Boolean ES_ADMIN = false;
    //arreglo con ids de fotos pertenecientes a la sesión
    private ArrayList<Foto> fotosSesion;

    //para guardar las fotos que se seleccionan
    private ArrayList<Foto> fotosSeleccionadasGV;
    private Integer positionFotoSeleccionada;
    private GridView gvFotos;
    private AdaptadorGrid adaptadorGrid;
    private TextView tvSesionNombre, tvSesionFecha;
    private ImageView ivFoto;
    private FloatingActionButton fabAgregarFotos;
    private ImageButton btnDescargar, btnCompartir;

    //pick images
    private ArrayList<Uri> imagesUri;
    private ActivityResultLauncher<Intent> intentLauncher;

    //info sesión
    private Sesion sesionAbierta;

    //--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        //recuperar datos del usuario
        SharedPreferences preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        USER_ID = preferences.getInt("USER_ID", 0);
        USER_USERNAME = preferences.getString("USER_USERNAME", "USUARIO_NO_IDENTIFICADO");
        if(USER_USERNAME.equals("admin1")){
            ES_ADMIN = true;
        }
        SESION_ID = getIntent().getIntExtra("sesionId",0);

        gvFotos = findViewById(R.id.gvFotos);
        tvSesionNombre = findViewById(R.id.tvSesionNombre);
        tvSesionFecha = findViewById(R.id.tvSesionFecha);
        ivFoto = findViewById(R.id.ivFoto);
        fabAgregarFotos = findViewById(R.id.fabAgregarFotos);
        btnCompartir = findViewById(R.id.btnCompartir);
        btnDescargar = findViewById(R.id.btnDescargar);
        imagesUri = new ArrayList<>();

        //image picker
        activityResult();

        //obtener datos de la sesión elegida
        obtenerDatosSesionAbierta();

        //llenar arreglo de fotos
        fotosSesion = recuperarFotos();

        //establecer adaptador del grid
        adaptadorGrid = new AdaptadorGrid(this, R.layout.grid_item_sesion, fotosSesion);
        gvFotos.setAdapter(adaptadorGrid);

        //menu de accion contextual
        //para guardar las fotos seleccionadas en el menu
        fotosSeleccionadasGV = new ArrayList<Foto>();
        gvFotos.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gvFotos.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                if(checked){
                    fotosSeleccionadasGV.add(fotosSesion.get(position));
                }
            }
            //--------------------------------------------------------------------------------------
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                //inflate the menu for the cab
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.menu_accion_foto, menu);
                return true;
            }
            //--------------------------------------------------------------------------------------
            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
            //--------------------------------------------------------------------------------------
            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.miDescargar:
                        //descargar imagen
                        Toast.makeText(getApplicationContext(), R.string.algun_dia_descargar, Toast.LENGTH_SHORT).show();
                        actionMode.finish();
                        break;
                    case R.id.miEsconder:
                        //esconder imagen
                        Toast.makeText(getApplicationContext(), R.string.algun_dia_ocultar, Toast.LENGTH_SHORT).show();
                        actionMode.finish();
                        break;
                    case R.id.miEliminar: {
                        //eliminar imagen
                        try {
                            fotosSesion.removeAll(fotosSeleccionadasGV);
                            eliminarFotos();
                            actionMode.finish();
                        } catch (Exception ex){
                            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    }
                    default:
                        return false;
                }
                return false;
            }
            //--------------------------------------------------------------------------------------
            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                fotosSeleccionadasGV.clear();
            }
            //--------------------------------------------------------------------------------------
        });
        gvFotos.setOnItemClickListener(this::onItemClick);

        //poner nombre de la sesión y fecha en textviews
        tvSesionNombre.setText(sesionAbierta.getSesionNombre());
        tvSesionFecha.setText(sesionAbierta.getSesionFecha());

        //Esconder fab de usuarios normales
        fabAgregarFotos.setVisibility(View.GONE);
        fabAgregarFotos.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                //abrir galeria para seleccionar fotos a subir
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(
                        "content://media/internal/images/media"
                ));
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                intentLauncher.launch(Intent.createChooser(intent, getString(R.string.seleccionar_fotos)));
            }
        });

        //editar fab
        if(ES_ADMIN){
            //es el admin
            btnCompartir.setVisibility(View.INVISIBLE);
//            btnDescargar.setVisibility(View.INVISIBLE);
            fabAgregarFotos.setVisibility(View.VISIBLE);
        }

    }
    //--------------------------------------------------------------------------------------------------
    private void eliminarFotos() {
        //después de preguntarle al usuario, dijo que sí quería eliminar las fotos seleccionadas en
        //el menú, entonces borrrarlas tanto del arreglo, como de la base de datos
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getWritableDatabase();
            if(!fotosSeleccionadasGV.isEmpty()){
                for(int i=0; i<fotosSeleccionadasGV.size(); i++){
                    Integer fotoId = fotosSeleccionadasGV.get(i).getFotoId();
                    Integer cantFotosBorradas = db.delete("fotos", "fotoId = " + fotoId, null);
                    Toast.makeText(this, getString(R.string.fotos_borradas), Toast.LENGTH_SHORT).show();
                }
            }
            db.close();
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }
    //--------------------------------------------------------------------------------------------------
    private byte[] imagemTratada(byte[] imagem_img){

        while (imagem_img.length > 500000){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;

    }
    //--------------------------------------------------------------------------------------------------
    private boolean insertarFoto(String uriPath){
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getWritableDatabase();
            String ruta = uriPath.substring(5);
            FileInputStream fs = new FileInputStream(ruta);
            byte[] imgByte = imagemTratada(new byte[fs.available()]);
            fs.read(imgByte);
            ContentValues fotoValues = new ContentValues();
            fotoValues.put("fotoArchivo", imgByte);
            fotoValues.put("sesionId", SESION_ID);
            db.insert("fotos", null, fotoValues);
            fs.close();
            return true;
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
    //--------------------------------------------------------------------------------------------------
    private ArrayList<Foto> recuperarFotos() {
        ArrayList<Foto> fotosRecuperadas = new ArrayList<>();
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getWritableDatabase();
            String query = "SELECT fotoId, fotoArchivo FROM fotos WHERE sesionId = "+SESION_ID;
            Cursor fila = db.rawQuery(query, null);
            if(fila.getCount() >= 1){
                while(fila.moveToNext()){
                    Integer fotoId = fila.getInt(0);
                    byte [] fotoArchivo = fila.getBlob(1);
                    Foto foto = new Foto(fotoId, fotoArchivo, SESION_ID);
                    fotosRecuperadas.add(foto);
                }
                fila.close();
                db.close();
            } else {
                Toast.makeText(this, getString(R.string.error_sesion_sin_fotos), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
        return fotosRecuperadas;
    }
    //--------------------------------------------------------------------------------------------------
    private void activityResult(){
        intentLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if(result.getResultCode() == Activity.RESULT_OK){
                Uri imageUri;
                if(result.getData().getClipData() != null){
                    //seleccionar multiples imagenes
                    Integer count = result.getData().getClipData().getItemCount();
                    for(int i=0; i<count; i++){
                        imageUri = result.getData().getClipData().getItemAt(i).getUri();
                        imagesUri.add(imageUri);
                    }
                } else {
                    //seleccionar una imagen
                    imageUri = result.getData().getData();
                    imagesUri.add(imageUri);
                }
                
                //insertar imagenes
                for (int i=0; i<imagesUri.size(); i++){
                    if(insertarFoto(imagesUri.get(i).getPath())){
                        //sí se insertó, actualizar la vista
//                        Toast.makeText(this, getString(R.string.sal_para_actualizar), Toast.LENGTH_SHORT).show();
                    } else {
                        //no se insertó
                        Toast.makeText(this, getString(R.string.error_elegir_imagen), Toast.LENGTH_SHORT).show();
                    }
                }
                fotosSesion = recuperarFotos();
            }
                });
    }

    //--------------------------------------------------------------------------------------------------
    private void obtenerDatosSesionAbierta() {
        try {
            AdminSQLiteOpenHelper primerplanodb = new AdminSQLiteOpenHelper(this, "primerplano.db", null, 1);
            SQLiteDatabase db = primerplanodb.getReadableDatabase();
            String query = "SELECT sesionId, sesionNombre, sesionFecha, sesionHora, sesionLugar, sesionPaquete, usuarioId FROM sesiones WHERE sesionId ="+SESION_ID;
            Cursor fila = db.rawQuery(query, null);
            if(fila.moveToFirst()){
                Integer sesionId = fila.getInt(0);
                String sesionNombre = fila.getString(1);
                String sesionFecha = fila.getString(2);
                String sesionHora = fila.getString(3);
                String sesionLugar = fila.getString(4);
                String sesionPaquete = fila.getString(5);
                Integer usuarioId = fila.getInt(6);
                sesionAbierta = new Sesion(sesionId, sesionNombre, sesionFecha, sesionHora, sesionLugar, sesionPaquete, usuarioId);
            } else {
                Toast.makeText(this, getString(R.string.sesion_no_encontrada), Toast.LENGTH_LONG).show();
            }
            fila.close();
            db.close();
        } catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }



    //--------------------------------------------------------------------------------------------------
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //view.
        byte [] byteArrayFotoAmpliada = fotosSesion.get(position).getFotoArchivo();
        Intent intent = new Intent(SesionActivity.this, AmpliarFotoActivity.class);
        intent.putExtra("byteArrayFotoAmpliada", byteArrayFotoAmpliada);
        startActivity(intent);
    }
    //--------------------------------------------------------------------------------------------------
    public void compartirSesionClick(View view){
        Toast.makeText(this, getString(R.string.compartir_sesion), Toast.LENGTH_SHORT).show();
    }
    //--------------------------------------------------------------------------------------------------
    public void descargarSesionClick(View view){
        Toast.makeText(this, getString(R.string.algun_dia_archivo_zip), Toast.LENGTH_SHORT).show();
    }
    //--------------------------------------------------------------------------------------------------
    public void verInfoSesionClick(View view){
        //consultar info sesion a la base de datos
        String infoSesion = consultarInfoSesion();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.info_sesion0))
                .setMessage(infoSesion)
                .setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nada
                    }
                }).create().show();
    }
    //--------------------------------------------------------------------------------------------------
    private String consultarInfoSesion() {
        String infoSesion = "";
        infoSesion+=getString(R.string.info_sesion_nombre) + sesionAbierta.getSesionNombre() + "\n" +
                getString(R.string.info_sesion_fecha) + sesionAbierta.getSesionFecha() + "\n" +
                getString(R.string.info_sesion_hora) + sesionAbierta.getSesionHora() + "\n" +
                getString(R.string.info_sesion_lugar) + sesionAbierta.getSesionLugar() + "\n"+
                getString(R.string.info_sesion_paquete)+ sesionAbierta.getSesionPaquete();
        return infoSesion;
    }
}

//*-------------------------------------------------------------------------------------------------
//*-------------------------------------------------------------------------------------------------
 class AdaptadorGrid extends ArrayAdapter {

    private ArrayList<Foto> fotos;
    private int layoutResId;
    private LayoutInflater inflater;
    byte [] byteArrayFotoAmpliada;

    //--------------------------------------------------------------------------------------------------
    public AdaptadorGrid(@NonNull Context context, int resource, @NonNull ArrayList<Foto> fotos){
        super(context, resource, fotos);
        this.fotos = fotos;
        layoutResId = resource;
        inflater = LayoutInflater.from(context);
    }

    //--------------------------------------------------------------------------------------------------
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try{
            byteArrayFotoAmpliada = fotos.get(position).getFotoArchivo();
            if(convertView == null){
                convertView = inflater.inflate(layoutResId, parent, false);
            }
            ImageView ivFoto = convertView.findViewById(R.id.ivFoto);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArrayFotoAmpliada, 0, byteArrayFotoAmpliada.length);
            ivFoto.setImageBitmap(Bitmap.createScaledBitmap(bmp, 500, 500, false));
            ivFoto.setScaleType(ImageView.ScaleType.FIT_CENTER);

            return convertView;
        } catch (Exception ex){
            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
    /*/--------------------------------------------------------------------------------------------------
    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        fotos.add((Foto) object);
    }
    //--------------------------------------------------------------------------------------------------
    @Override
    public void remove(@Nullable Object object) {
        super.remove(object);
        fotos.remove(object);
    }*/
    //--------------------------------------------------------------------------------------------------
}