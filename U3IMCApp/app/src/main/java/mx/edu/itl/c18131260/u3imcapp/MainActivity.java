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
            Toast.makeText(this,"Introduce algunos valores!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //obtener valores de los view
            float peso = Float.parseFloat(edtPeso.getText().toString());
            float estatura = Float.parseFloat(edtEstatura.getText().toString());

            //validar que el peso y la estatura no sean 0
            if (peso <= 0){
                edtPeso.setError("El peso debe ser mayor de 0");
                edtPeso.requestFocus();
                return;
            }
            if(estatura <= 0){
                edtEstatura.setError("La estuatura debe ser mayor de 0");
                edtEstatura.requestFocus();
                return;
            }

            //sacar el imc
            float imc = (float)(peso / Math.pow(estatura, 2));
            //determinar condición de salud
            String condicion = "Su condición de salud es: ";
            if(imc < 15 ){
                condicion += "Delgadez muy severa.";
            } else if(imc > 15 && imc < 15.9){
                condicion += "Delgadez severa.";
            } else if(imc >= 16 && imc < 18.5){
                condicion += "Delgadez.";
            } else if(imc >= 18.5 && imc < 25){
                condicion += "Peso saludable.";
            } else if(imc >= 25 && imc < 30){
                condicion += "Sobrepeso.";
            } else if(imc > 30 && imc < 35){
                condicion += "Obesidad moderada.";
            } else if(imc > 35 && imc < 40){
                condicion += "Obesidad severa.";
            } else if(imc >= 40){
                condicion += "Obesidad muy severa.";
            } else {
                condicion = "¡Valores erróneos!";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.imc)
                    .setTitle("Índice de Masa Corporal")
                    .setMessage("IMC = "+ imc + "\n\n" + condicion)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
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
