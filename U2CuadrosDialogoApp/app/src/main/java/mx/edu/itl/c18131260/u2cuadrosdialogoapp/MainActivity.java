/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*          Clase que despliega distintos cuadros de mensaje y diálogo básicos.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 22/feb/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Los diálogos que se despliegan son los siguientes:
:*                  Toast de duración corta
:*                  Toast de duración larga
:*                  Snackbar
:*                  Dialogo con botón OK
:*                  Diálogo con botones OK y cancelar
:*                  Diálogo con botones de radio selección única.
:*                  Diálogo con casillas de verificación selección múltiple
:*                  Diálogo con layout de login incrustado
:*                  Acerca de
:*                puede usar varios renglones
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  -           -                       -
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131260.u2cuadrosdialogoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //----------------------------------------------------------------------------------------------
    //mensaje de toast de duración corta
    public void btnToastCortoClick(View v){
        Toast.makeText(this, "Toast corto", Toast.LENGTH_SHORT ).show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de toast de duración larga
    public void btnToastLargoClick(View v){
        Toast.makeText(this, "Toast largo", Toast.LENGTH_LONG).show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de snackbar
    public void btnSnackbarClick(View v){
        LinearLayout layoutPrincipal = findViewById(R.id.layoutPrincipal);
        Snackbar.make(layoutPrincipal, "Esto es un Snackbar", Snackbar.LENGTH_SHORT).show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    public void btnDialogoBasicoClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Cuadro de Diálogo básico.").create().show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    public void btnDialogoOkClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Esto es un Diálogo con un botón que dice OK.")
                .setTitle("Diálogo con botón OK")
                .setPositiveButton("OK?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mostrarToast("OK");
                    }
                }).create().show();
    }

    //----------------------------------------------------------------------------------------------
    //método para mostrar un toast reutilizable
    public void mostrarToast(String mensaje){
        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    public void btnDialogoOkCancelClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción")
                .setMessage("Dialog con botones OK y CANCELAR.")
                .setPositiveButton("OK?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mostrarToast("OK");
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false).create().show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    private CharSequence colores [] = { "Verde", "Blanco", "Rojo" };

    public void btnDialogoOpcionesClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Escoja un color bonito:" )
                .setItems ( colores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick ( DialogInterface dialogInterface, int i ) {
                        mostrarToast ( "Color seleccionado: " + colores [ i ] );
                    }
                } )
                .create ()
                .show ();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    private int iColorFavorito = 2;  // Por default el Rojo

    public void btnDialogoRadioClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Escoge tu color favorito:" )
                .setSingleChoiceItems ( colores, iColorFavorito, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick ( DialogInterface dialogInterface, int i ) {
                        iColorFavorito = i;
                        mostrarToast  ( "Escogi�: " + colores [ i ] );
                    }
                } )
                .setPositiveButton ( "Aceptar", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick ( DialogInterface dialogInterface, int i ) {
                        mostrarToast ( "Nuevo color favorito: " + colores [ iColorFavorito ] );
                    }
                } )
                .create()
                .show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    private boolean coloresSeleccionados [] = { false, false, false };  // Ninguna aparecera selecccionada
    private ArrayList<String> coloresFavoritos = new ArrayList<>();

    public void btnDialogoVerificacionClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Seleccione sus colores favoritos:" )
                .setMultiChoiceItems ( colores, coloresSeleccionados, new DialogInterface.OnMultiChoiceClickListener () {
                    @Override
                    public void onClick ( DialogInterface dialogInterface, int i, boolean b ) {
                        if ( b ) {
                            mostrarToast ( "Color seleccionado: " + colores [ i ] );
                            coloresFavoritos.add ( colores [ i ].toString () );
                        }
                        else {
                            mostrarToast ( "Color deseleccionado: " + colores [ i ] );
                            coloresFavoritos.remove ( colores [ i ].toString () );
                        }
                    }
                })
                .setPositiveButton ( "Aceptar", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick ( DialogInterface dialogInterface, int i ) {
                        mostrarToast ( "Colores favoritos: " + coloresFavoritos );
                    }
                })
                .create()
                .show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    private EditText edtUsuario;
    private EditText edtContrasena;
    private View login_layout;

    public void btnDialogoLayoutClick(View v){
        //obtener la instancia de layouts de login y de sus campos usuario y contraseña
        login_layout = getLayoutInflater().inflate(R.layout.login_layout, null);
        edtUsuario = login_layout.findViewById(R.id.edtUsuario);
        edtContrasena = login_layout.findViewById(R.id.edtContrasena);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Iniciar Sesión")
                .setIcon(R.drawable.itl)
                .setView(login_layout)
                .setPositiveButton("Ingresa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mostrarToast("Bienvenido "+edtUsuario.getText().toString()+"("+edtContrasena.getText().toString()+")");
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })//no se puede cerrar si no le pica en cancelar o aceptar
                .setCancelable(false).create().show();
    }

    //----------------------------------------------------------------------------------------------
    //mensaje de cuadro de diálogo básico
    public void btnAcercaDeClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Créditos")
                .setIcon(R.drawable.itl)
                .setMessage("CuadrosDialogoApp v1.0\nEsta app fue hecha por: \n\tElí Uziel Montes Pérez \t18131260\tTodos los derechos reservados.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mostrarToast("si");
                    }
                }).setCancelable(false).create().show();
    }
}