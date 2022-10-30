/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       descripcion breve de la clase (centrado)
:*
:*  Archivo     : ActivityMain.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 22/mar/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : descripcion funcional más detallada de lo que hace esta clase
:*                puede usar varios renglones
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u3ocultartecladoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import util.OcultarTecladoAdaptador;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layoutPrincipal;

    //--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtener la referencia de layout principal
        layoutPrincipal = findViewById(R.id.layoutPrincipal);
        //al dar click en el layout, ocultar teclado
        layoutPrincipal.setOnClickListener( new OcultarTecladoAdaptador(this) );
    }
    //--------------------------------------------------------------------------------------------------
    public void btnSalirClick(View view){
        finish();
    }
    //--------------------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        //anulamos la acción del boton ATRÁS
    }
    //--------------------------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemAcercaDe:
                Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemSalir:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}