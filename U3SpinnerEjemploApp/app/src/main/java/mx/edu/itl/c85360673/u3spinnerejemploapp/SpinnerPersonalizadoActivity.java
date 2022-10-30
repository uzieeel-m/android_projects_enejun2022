package mx.edu.itl.c85360673.u3spinnerejemploapp;

import androidx.appcompat.app.AppCompatActivity;
import modelo.Club;
import modelo.SpinnerClubesAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SpinnerPersonalizadoActivity extends AppCompatActivity {

    private Spinner spnClubes;
    private ArrayList<Club> clubes;

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_personalizado);

        spnClubes = findViewById ( R.id.spnClubes );

        // Inicializar el ArrayList de clubes
        inicializarClubes ();

        // Crear el adapter del tipo SpinnerClubesAdapter y establecer el layout con  setDropDownViewResource ()
        SpinnerClubesAdapter adaptador = new SpinnerClubesAdapter(this, clubes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador para el spinner
        spnClubes.setAdapter(adaptador);
    }

    //----------------------------------------------------------------------------------------------

    private void inicializarClubes () {
        clubes = new ArrayList<> ( );
        // Agregar elementos al ArrayList
        clubes.add(new Club("Barcelona FC", R.drawable.club_barcelona));
        clubes.add(new Club("Bayern Munich", R.drawable.club_bayern));
        clubes.add(new Club("Chelsea", R.drawable.club_chelsea));
        clubes.add(new Club("Dinamo Kiev", R.drawable.club_dinamokiev));
        clubes.add(new Club("Zagreb", R.drawable.club_zagreb));
    }

    //----------------------------------------------------------------------------------------------

    public  void btnAceptarClick ( View v ) {
         // Recuperar el elemento seleccionado del Spinner y mostrar el nombre del club en un Toast
        int pos = spnClubes.getSelectedItemPosition();
        Club club = clubes.get(pos);

        Toast.makeText(SpinnerPersonalizadoActivity.this, "Club seleccionado: "+ club.getNombre() + ", en posición " + pos, Toast.LENGTH_SHORT).show();
    }

    //----------------------------------------------------------------------------------------------
}
