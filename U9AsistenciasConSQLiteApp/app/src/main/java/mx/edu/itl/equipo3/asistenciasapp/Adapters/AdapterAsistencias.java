/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*           Adaptador que se utiliza para mostrar la infomacion de los archivos
:*
:*  Archivo     : AdapterAsistencias.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Adaptor que utiliza CargarAsistenciasActivity para colocarle en el recyclerView
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

import mx.edu.itl.equipo3.asistenciasapp.Objects.Total;
import mx.edu.itl.equipo3.asistenciasapp.R;

public class AdapterAsistencias extends RecyclerView.Adapter<AdapterAsistencias.ViewHolderAsistencias> {

    public interface OnItemClickListener {
        void onItemClick( Total asistencia );
    }

    private final ArrayList<Total> asistencias;
    private final OnItemClickListener listener;

    //----------------------------------------------------------------------------------------------

    public AdapterAsistencias( ArrayList<Total> listaAsistencias, AdapterAsistencias.OnItemClickListener listener ) {
        this.asistencias = listaAsistencias;
        this.listener = listener;
    }

    //----------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public ViewHolderAsistencias onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.list_totales, parent, false );
        return new ViewHolderAsistencias( view );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onBindViewHolder( @NonNull AdapterAsistencias.ViewHolderAsistencias holder, int position ) {
        holder.bind( asistencias.get( position ), listener );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return asistencias.size();
    }

    //----------------------------------------------------------------------------------------------

    static class ViewHolderAsistencias extends RecyclerView.ViewHolder {
        TextView noControl, nombre, totalPresente, totalJustificado, total, porcentaje;

        public ViewHolderAsistencias( @NonNull View itemView ) {
            super( itemView );
            nombre              = ( TextView ) itemView.findViewById( R.id.txtNombre );
            noControl           = ( TextView ) itemView.findViewById( R.id.txtNoControl );
            totalPresente       = ( TextView ) itemView.findViewById( R.id.txtPresente );
            totalJustificado    = ( TextView ) itemView.findViewById( R.id.txtJustificada );
            total               = ( TextView ) itemView.findViewById( R.id.txtTotal );
            porcentaje          = ( TextView ) itemView.findViewById( R.id.txtPorcentaje );
        }

        public void bind( final Total asistencia, final AdapterAsistencias.OnItemClickListener listener ) {
            nombre.setText( asistencia.getNombre() );
            noControl.setText( asistencia.getNoControl() );
            totalPresente.setText( asistencia.getTotalPresente()+"" );
            totalJustificado.setText( asistencia.getTotalJustificado()+"" );
            total.setText( asistencia.getTotal()+"" );
            porcentaje.setText( asistencia.getPorcentaje() );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override public void onClick( View v ) {
                    listener.onItemClick( asistencia );
                }
            });
        }
    }

    //----------------------------------------------------------------------------------------------
}