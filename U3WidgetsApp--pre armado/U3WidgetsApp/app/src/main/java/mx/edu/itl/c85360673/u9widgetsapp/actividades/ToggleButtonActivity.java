package mx.edu.itl.c85360673.u9widgetsapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import mx.edu.itl.c85360673.u9widgetsapp.R;

public class ToggleButtonActivity extends AppCompatActivity {

    ToggleButton togButn;
    ImageView imgV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button);
        togButn = (ToggleButton) findViewById(R.id.toggleButton);
        imgV = findViewById(R.id.imageView);
    }

    public void onclick(View view){
        if(view.getId() == R.id.toggleButton){
            if(togButn.isChecked()){
                imgV.setImageResource(R.drawable.desbloquear);
            }else{
                imgV.setImageResource(R.drawable.bloquear);
            }
        }
    }
}