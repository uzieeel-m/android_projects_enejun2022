package mx.edu.itl.c85360673.u4sensoresapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AcelerometroActivity extends AppCompatActivity {

    private Pelota pelota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);

        pelota = findViewById(R.id.pelota);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pelota.iniciar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pelota.detener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pelota.detener();
    }
}
