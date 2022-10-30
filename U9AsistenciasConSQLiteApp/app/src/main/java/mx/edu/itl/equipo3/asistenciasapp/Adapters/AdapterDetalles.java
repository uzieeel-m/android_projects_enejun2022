/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*     Adaptador que se utiliza para mostrar los detalles de las asistencias de cada alumnos
:*
:*  Archivo     : AdapterDetalles.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Adaptador que utiliza DetallesAlumnosActiviy para colocarle en el recyclerView
:*                y mostrar la información de este
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.edu.itl.equipo3.asistenciasapp.Objects.Asistencia;
import mx.edu.itl.equipo3.asistenciasapp.R;

public class AdapterDetalles extends RecyclerView.Adapter<AdapterDetalles.ViewHolderDetalles>{

    ArrayList<Asistencia> listaDetalles;

    //----------------------------------------------------------------------------------------------

    public AdapterDetalles( ArrayList<Asistencia> listaDetalles ) {
        this.listaDetalles = listaDetalles;
    }

    //----------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public ViewHolderDetalles onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_detalles, parent, false );
        return new ViewHolderDetalles( view );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onBindViewHolder( @NonNull ViewHolderDetalles holder, int position ) {
        holder.fecha.setText( listaDetalles.get( position ).getFecha() );
        holder.estatus.setText( listaDetalles.get( position ).getStatus().toString() );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return listaDetalles.size();
    }

    //----------------------------------------------------------------------------------------------

    public class ViewHolderDetalles extends RecyclerView.ViewHolder {
        TextView fecha, estatus;

        public ViewHolderDetalles(@NonNull View itemView) {
            super(itemView);
            fecha   = (TextView) itemView.findViewById(R.id.txtFecha);
            estatus = (TextView) itemView.findViewById(R.id.txtEstatus);
        }
    }

    //----------------------------------------------------------------------------------------------
}