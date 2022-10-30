/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Activity para mostrar la foto seleccionada apliada.
:*
:*  Archivo     : AmpliarFototActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 28/abr/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase muestra la foto que se seleccionó en el GridView en grande, se compone
:*                  de un sólo ImageView donde se pone la imagen, y un Button para regresar a
:*                  la activity anterior.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  1/jun/2022  Uziel Montes         Integración de bases de datos SQLite.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3p03usowidgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AmpliarFotoActivity extends AppCompatActivity {

    ImageView ivFotoAmpliada;
    //------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampliar_foto);

        ivFotoAmpliada = findViewById(R.id.ivFotoAmpliada);

        byte [] byteArrayFotoAmpliada = getIntent().getByteArrayExtra("byteArrayFotoAmpliada");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArrayFotoAmpliada, 0, byteArrayFotoAmpliada.length);
        ivFotoAmpliada.setImageBitmap(Bitmap.createScaledBitmap(bmp, 500, 500, false));
        ivFotoAmpliada.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
    //------------------------------------------------------------------------------------------
    public void cerrarFotoAmpliadaClick(View view){
        finish();
    }
    //------------------------------------------------------------------------------------------
}