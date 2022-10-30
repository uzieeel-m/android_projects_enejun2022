package mx.edu.itl.c85360673.u9widgetsapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import mx.edu.itl.c85360673.u9widgetsapp.R;

public class ImageButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);
    }

    public void btnUnoClick(View view){
        Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
    }
    public void btnDosClick(View view){
        Toast.makeText(this, "Sí", Toast.LENGTH_SHORT).show();
    }
}