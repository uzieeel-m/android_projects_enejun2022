/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*          Activity que muestra las asistencias con fecha obtenidas de la BD
:*
:*  Archivo     : DetallesAlumnoActivity.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Activity que muestra los detelles del alumno por fecha, estatus y ademas
:*                una grafica, hace uso de la clase AdapterDetalles para mostrar en ReclyclerView
:*                y la clase de DB para obtener la informacion cargada
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import mx.edu.itl.equipo3.asistenciasapp.Adapters.AdapterDetalles;
import mx.edu.itl.equipo3.asistenciasapp.Objects.Asistencia;
import mx.edu.itl.equipo3.asistenciasapp.Objects.Grupo;
import mx.edu.itl.equipo3.asistenciasapp.R;
import mx.edu.itl.equipo3.asistenciasapp.SQLite.DB;

public class DetallesAlumnoActivity extends AppCompatActivity {

    private TextView noControlView;
    private ArrayList<Asistencia> listaDetalles;
    private RecyclerView recyclerViewDetalles;
    private String nombre, noControl;
    private int idGrupo, presentes, justificados;
    private DB db;
    private ArrayList<Grupo> grupos;
    private int clases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_alumno);
        noControlView = ( TextView ) findViewById( R.id.txtTitulo );

        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();

        nombre      = extra.getString( "nombre" );
        noControl   = extra.getString( "noControl" );
        idGrupo     = extra.getInt( "idGrupo" );
        presentes   = extra.getInt( "presentes" );
        justificados = extra.getInt( "justificados" );

        noControlView.setText( nombre );

        db = new DB( getApplicationContext() );
        getGrupos();

        listaDetalles = new ArrayList<>();
        recyclerViewDetalles = ( RecyclerView ) findViewById( R.id.recyclerView );
        recyclerViewDetalles.setLayoutManager( new LinearLayoutManager( this ) );
        llenarDetalles();

        AdapterDetalles adapter = new AdapterDetalles( listaDetalles );
        recyclerViewDetalles.setAdapter( adapter );

        construirGrafica( presentes, justificados, clases );
    }

    //----------------------------------------------------------------------------------------------

    private void llenarDetalles(){
        listaDetalles = db.getAsistencias( noControl, idGrupo );
    }

    //----------------------------------------------------------------------------------------------

    private void getGrupos () {;
        grupos = db.getGrupos();

        for( Grupo grupo : grupos ) {
            if( grupo.getId() == idGrupo ) {
                clases = grupo.getClases();
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    private void construirGrafica( int presentes, int justificados, int clases ){
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add( new ValueDataEntry( "Presentes", presentes ) );
        data.add( new ValueDataEntry( "Justificados", justificados ) );
        data.add( new ValueDataEntry( "Faltas", clases-presentes-justificados ) );
        pie.data( data );
        pie.title( "Detalles Asistencias" );
        AnyChartView anyChartView = ( AnyChartView ) findViewById( R.id.anyChartView );
        anyChartView.setChart( pie );
    }

    //----------------------------------------------------------------------------------------------
}