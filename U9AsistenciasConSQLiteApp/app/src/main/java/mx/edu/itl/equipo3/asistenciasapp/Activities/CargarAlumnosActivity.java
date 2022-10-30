/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                Activity para cargar la lista de alumnos a la BD
:*
:*  Archivo     : CargarAlumnosActivity.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Activity donde se carga la lista de alumnos usando la clase
:*                CargarAlumnosHelper para hacer uso de metodos y la clase DB
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codekidlabs.storagechooser.StorageChooser;

import java.util.ArrayList;
import java.util.Objects;

import mx.edu.itl.equipo3.asistenciasapp.Adapters.AdapterListaArchivos;
import mx.edu.itl.equipo3.asistenciasapp.Helpers.SnackbarHelper;
import mx.edu.itl.equipo3.asistenciasapp.Objects.Alumno;
import mx.edu.itl.equipo3.asistenciasapp.Helpers.CargarAlumnosHelper;
import mx.edu.itl.equipo3.asistenciasapp.Objects.InfoArchivo;
import mx.edu.itl.equipo3.asistenciasapp.R;
import mx.edu.itl.equipo3.asistenciasapp.SQLite.DB;

public class CargarAlumnosActivity extends AppCompatActivity {

    TextView textViewPath;
    TextView textViewTotalArchivos;
    Button btnLimpiar;
    Button btnCargar;
    Button btnCargarAluSelectFolder;
    RecyclerView cargaAsisRecyclerView;
    ArrayList<InfoArchivo> archivoAlumnos;
    AdapterListaArchivos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_alumnos);
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();

        Objects.requireNonNull( getSupportActionBar() ).hide();

        int enableCargarAlumFolder = extra.getInt("totalAlumnos" );

        textViewPath             = findViewById ( R.id.txtvCargarAluPath );
        textViewTotalArchivos    = findViewById ( R.id.txtvCargarAluCount );
        btnLimpiar               = findViewById ( R.id.btnCargarAluLimpiar );
        btnCargar                = findViewById ( R.id.btnCargarAluCargar );
        btnCargarAluSelectFolder = findViewById( R.id.btnCargarAluSelectFolder );

        cargaAsisRecyclerView = findViewById ( R.id.recyclerViewCargarAlu );
        btnCargarAluSelectFolder.setEnabled( enableCargarAlumFolder > 0 ? false : true );
    }

    //----------------------------------------------------------------------------------------------

    public void onClickAtras ( View v ) {
        finish ( );
    }

    //----------------------------------------------------------------------------------------------

    public void onClickSelectFolder ( View v ) {
        String path = this.getApplicationContext().getExternalFilesDir( Environment.DIRECTORY_DOCUMENTS ).getAbsolutePath();
        final StorageChooser chooser = new StorageChooser.Builder()
                .withActivity( CargarAlumnosActivity.this )
                .withFragmentManager( getFragmentManager() )
                .withMemoryBar( true )
                .allowCustomPath( true )
                .withPredefinedPath( path )
                .setType( StorageChooser.FILE_PICKER )
                .build();

        chooser.setOnSelectListener( new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect( String path ) {
                textViewPath.setText( path );
                archivoAlumnos = CargarAlumnosHelper.getFile( path );

                if ( archivoAlumnos.size() <= 0 ) return;

                activarControles();

                adapter = new AdapterListaArchivos( archivoAlumnos );

                cargaAsisRecyclerView.setAdapter( adapter );
                cargaAsisRecyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()) );

                String label = archivoAlumnos.size() + " Archivos";
                textViewTotalArchivos.setText( label );
            }
        });
        chooser.show();
    }

    //----------------------------------------------------------------------------------------------

    public void onClickLimpiar ( View v ) {
        archivoAlumnos.clear ();
        adapter.notifyDataSetChanged ();
        textViewPath.setText ( "" );
        textViewTotalArchivos.setText ( "0 Archivos" );
        desactivarControles ();
        DB db = new DB( getApplicationContext() );
        db.clearDataBase();
    }

    //----------------------------------------------------------------------------------------------

    public void onClickCargar ( View v ) {
        if ( archivoAlumnos.isEmpty() ) return;

        ProgressDialog progress;
        progress = ProgressDialog.show( this, "Cargando Alumnos",
                "Se están el listado de alumnos", true );


        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<Alumno> alumnos =
                            CargarAlumnosHelper.obtenerAlumnos ( archivoAlumnos.get ( 0 ) );

                    CargarAlumnosHelper.guardarAlumnos ( alumnos, getApplicationContext() );

                    progress.dismiss ();
                    SnackbarHelper.showSnackbar ( v, "Alumnos cargados correctamente", true );
                    btnCargar.setEnabled ( false );
                } catch ( Exception e ) {
                    SnackbarHelper.showSnackbar ( v, "Hubo un problema al cargar los alumnos", true );
                }
            }
        }, 0 );
    }

    //----------------------------------------------------------------------------------------------

    private void activarControles () {
        establecerControles ( true, true );
    }

    //----------------------------------------------------------------------------------------------

    private void desactivarControles () {
        establecerControles ( false, false );
    }

    //----------------------------------------------------------------------------------------------

    private void establecerControles ( boolean setBtnLimpiarEnabled, boolean setBtnCargarEnabled ) {
        btnLimpiar.setEnabled ( setBtnLimpiarEnabled );
        btnCargar.setEnabled ( setBtnCargarEnabled );
    }

    //----------------------------------------------------------------------------------------------
}