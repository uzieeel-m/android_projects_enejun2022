package mx.edu.itl.c85360673.u4sensoresapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //----------------------------------------------------------------------------------------------

    public void btnConsultarSensoresClick ( View v ) {
        Intent intent = new Intent ( this, ConsultarSensoresActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------

    public void btnMonitorearSensoresClick ( View v ) {
        Intent intent = new Intent ( this, MonitorearSensoresActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------

    public void btnAcelerometroClick ( View v ) {
        Intent intent = new Intent ( this, AcelerometroActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void acercaDeClick(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }
}
