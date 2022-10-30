package mx.edu.itl.c85360673.u4correoconanexosapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final int   CODIGO_ARCHIVO_SELECCIONADO = 1;

    private EditText           edtDestinatario;
    private CheckBox           cbxCcp;
    private EditText           edtCcp;
    private CheckBox           cbxCco;
    private EditText           edtCco;
    private EditText           edtAsunto;
    private EditText           edtMensaje;
    private LinearLayout       linlayArchivosAdjuntos;
    private ArrayList<Uri>     arrlstArchivosAdjuntos;


    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState      );
        setContentView ( R.layout.activity_main  );

        //Obtenemos las referencias de los Views en el layout
        edtDestinatario        = (EditText)     findViewById ( R.id.edtDestinatario      );
        cbxCcp                 = (CheckBox)     findViewById ( R.id.cbxCcp               );
        edtCcp                 = (EditText)     findViewById ( R.id.edtCcp               );
        cbxCco                 = (CheckBox)     findViewById ( R.id.cbxCco               );
        edtCco                 = (EditText)     findViewById ( R.id.edtCco               );
        edtAsunto              = (EditText)     findViewById ( R.id.edtAsunto            );
        edtMensaje             = (EditText)     findViewById ( R.id.edtMensaje           );
        linlayArchivosAdjuntos = (LinearLayout) findViewById(R.id.linlayArchivosAdjuntos );
        arrlstArchivosAdjuntos = new ArrayList<> ();

        //Agregamos los listener para los eventos onCheckedChange de los
        //Checkbox para habilitar o deshabilitar los edits de con copia para y con copia oculta
        cbxCcp.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged ( CompoundButton buttonView, boolean isChecked ) {
                if  ( isChecked ){
                    edtCcp.setEnabled ( true  );
                }else {
                    edtCcp.setEnabled ( false );
                }
            }
        });

        cbxCco.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged ( CompoundButton buttonView, boolean isChecked ) {
                if  ( isChecked ){
                    edtCco.setEnabled ( true  );
                }else {
                    edtCco.setEnabled ( false );
                }
            }
        });

        //Limpiamos todos los hijos del layout.
        linlayArchivosAdjuntos.removeAllViews ();
    }

    //----------------------------------------------------------------------------------------------

    public void btnAdjuntarArchivoClick ( View view ) {
        //Creamos el intent para abrir el explorador de archivos.
        Intent intent = new Intent ( Intent.ACTION_GET_CONTENT );
        //Agregamos el filtro para para que tome aplicaciones que puedan abrir todos los archivos.
        intent.setType             ( "*/*" );
        //Agregamos la categoría de la aplicación, default.
        intent.addCategory         ( Intent.CATEGORY_DEFAULT );

        //Tratamos de abrir el explorador de archivos. Si no se puede, lanzamos un toast con la excepción.
        try {
            startActivityForResult ( Intent.createChooser ( intent, "Seleccione una opción." ),
                                     CODIGO_ARCHIVO_SELECCIONADO );
        } catch ( ActivityNotFoundException e ) {
            Toast.makeText (
                    this,
                    "Explorador de archivos no encontrado.\nPor favor instale un exploador de archivos.",
                    Toast.LENGTH_LONG )
                 .show ();
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        // Si el código de petición es CODIGO_ARCHIVO_SELECCIONAD0
        if (requestCode == CODIGO_ARCHIVO_SELECCIONADO) {
            // Si el código de resultado es igual a RESULT_OK
            if (resultCode == RESULT_OK) {
                //Verificamos que el archivo no se encuentre adjuntado
                boolean estaAdjunto = false;
                for (Uri u : arrlstArchivosAdjuntos) {
                    if (u.getPath().equals(data.getData().getPath())) {
                        estaAdjunto = true;
                        break;
                    }
                }
                if (!estaAdjunto) {
                    //Agregamos un nuevo hijo al layout que sea otro layout linear, horizontal,
                    //Con 2 views, uno que sea un textview para visualizar el nombre del archivo y el
                    //otro un boton para remover el adjunto.
                    LinearLayout linearLayout = new LinearLayout(this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setWeightSum(10f);

                    TextView textView = new TextView(this);
                    String[] segmentosDeLaRuta = data.getData().getPath().split("/");
                    String nombreArchivo = segmentosDeLaRuta[segmentosDeLaRuta.length - 1];
                    textView.setText(nombreArchivo);
                    LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            2f
                    );
                    textView.setLayoutParams(textViewLayoutParams);


                    Button button = new Button(this);
                    button.setText("X");
                    LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            8f
                    );
                    button.setLayoutParams(buttonLayoutParams);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int indice = linlayArchivosAdjuntos.indexOfChild((View) v.getParent());
                            arrlstArchivosAdjuntos.remove(indice);
                            linlayArchivosAdjuntos.removeView((View) v.getParent());
                        }
                    });
                    //Agregamos el botón y el textview al layout creado (horizontal).
                    //Esto simula la adición de un nuevo renglón a una tabla.
                    linearLayout.addView(textView);
                    linearLayout.addView(button);
                    //Agregamos el nuevo "renglón" al layout que lista dichos elementos adjuntos.
                    linlayArchivosAdjuntos.addView(linearLayout);
                    //Agregamos la ruta del archivo en la lista de archivos adjuntos.
                    arrlstArchivosAdjuntos.add(data.getData());
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    public void btnEnviarCorreoClick ( View view ) {
        //Creamos el nuevo intent para enviar el correo.
        Intent emailIntent = new Intent ( Intent.ACTION_SEND_MULTIPLE );
        emailIntent.setData             ( Uri.parse ( "mailto:" ) );
        emailIntent.setType             ( "text/plain" );

        //Mapeamos los valores para el correo a enviar.
        emailIntent.putExtra ( Intent.EXTRA_EMAIL,   new String [] { edtDestinatario.getText().toString() } );
        emailIntent.putExtra ( Intent.EXTRA_CC,      edtCcp.getText ().toString ().split ( ";" ) );
        emailIntent.putExtra ( Intent.EXTRA_BCC,     edtCco.getText ().toString ().split ( ";" ) );
        emailIntent.putExtra ( Intent.EXTRA_SUBJECT, edtAsunto.getText  ().toString () );
        emailIntent.putExtra ( Intent.EXTRA_TEXT,    edtMensaje.getText ().toString () );
        emailIntent.putParcelableArrayListExtra ( Intent.EXTRA_STREAM, arrlstArchivosAdjuntos );

        //Tratamos de iniciar la actividad. Si no se puede, lanzamos excepcion en un toast.
        try {
            startActivity ( Intent.createChooser ( emailIntent, "Seleccione una aplicación para continuar." ) );
        } catch ( ActivityNotFoundException e ) {
            Toast.makeText ( 
			     this, 
				 "No se encontró cliente de correos.\nFavor de instalar cliente de correos.", 
				 Toast.LENGTH_LONG ).show();
        }
        //Limpiamos los campos.
        arrlstArchivosAdjuntos.clear          ();
        edtDestinatario.setText               ( ""    );
        cbxCcp.setChecked                     ( false );
        edtCcp.setEnabled                     ( false );
        edtCcp.setText                        ( ""    );
        cbxCco.setChecked                     ( false );
        edtCco.setEnabled                     ( false );
        edtCco.setText                        ( ""    );
        edtAsunto.setText                     ( ""    );
        edtMensaje.setText                    ( ""    );
        linlayArchivosAdjuntos.removeAllViews ();
    }

    //----------------------------------------------------------------------------------------------
}
