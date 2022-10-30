/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                   Clase principal para calcular el IMC.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Elí Uziel Montes Pérez     18131260
:*  Fecha       : 09/mar/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Esta clase muestra un conjunto de views para calcular el IMC. Para esto
			se pide el peso y la altura de la persona. El IMC se calcula con 
			la fórmula: peso / estatura^2.
			También, dependiendo del IMC, se le dice al usuario su estado
			de salud.
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131260.u3imcapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtPeso, edtEstatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //esconder barra de acción
        getSupportActionBar().hide();
        //inicializar variables de peso y estatura
        edtPeso = findViewById(R.id.edtPeso);
        edtEstatura = findViewById(R.id.edtEstatura);
    }

    public void btnCalcularIMCClick(View view){
        //validar que los campos no estén vacíos
        if(edtPeso.getText().toString().isEmpty() || edtEstatura.getText().toString().isEmpty()){
            Toast.makeText(this,getString(R.string.introduce_valores), Toast.LENGTH_SHORT).show();
            return;
        } else {
            //obtener valores de los view
            float peso = Float.parseFloat(edtPeso.getText().toString());
            float estatura = Float.parseFloat(edtEstatura.getText().toString());

            //validar que el peso y la estatura no sean 0
            if (peso <= 0){
                edtPeso.setError(getString(R.string.error_peso));
                edtPeso.requestFocus();
                return;
            }
            if(estatura <= 0){
                edtEstatura.setError(getString(R.string.error_estatura));
                edtEstatura.requestFocus();
                return;
            }

            //sacar el imc
            float imc = (float)(peso / Math.pow(estatura, 2));
            //determinar condición de salud
            String condicion = getString(R.string.condicion);
            if(imc < 15 ){
                condicion += getString(R.string.condicion_del_muy_sev);
            } else if(imc > 15 && imc < 15.9){
                condicion += getString(R.string.condicion_del_sev);
            } else if(imc >= 16 && imc < 18.5){
                condicion += getString(R.string.condicion_del);
            } else if(imc >= 18.5 && imc < 25){
                condicion += getString(R.string.condicion_peso_sal);
            } else if(imc >= 25 && imc < 30){
                condicion += getString(R.string.condicion_sobrepeso);
            } else if(imc > 30 && imc < 35){
                condicion += getString(R.string.condicion_obesidad);
            } else if(imc > 35 && imc < 40){
                condicion += getString(R.string.condicion_obesidad_sev);
            } else if(imc >= 40){
                condicion += getString(R.string.condicion_obesidad_muy_sev);
            } else {
                condicion = getString(R.string.valores_err);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.imc)
                    .setTitle(getString(R.string.indiceIMC))
                    .setMessage("IMC = "+ imc + "\n\n" + condicion)
                    .setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        }
    }

    public void btnAcercaDeClick(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }

}
