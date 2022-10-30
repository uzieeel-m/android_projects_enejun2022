package mx.edu.itl.c85360673.u4reproaudioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button btnFiesta;
    private MediaPlayer mp;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        position = 0;
        btnFiesta = findViewById ( R.id.btnFiesta );
        btnFiesta.setOnTouchListener(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view.getId() == R.id.btnFiesta){
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    reproducirAudio();
                    break;
                case MotionEvent.ACTION_UP:
                    detenerAudio();
                    break;
            }
            return true;
        }
        return false;
    }
    //----------------------------------------------------------------------------------------------
    private void reproducirAudio() {
        if(mp == null){
            mp = MediaPlayer.create(this, R.raw.tropicalisimo);
            if(position != 0){
                mp.seekTo(position);
            }
            mp.start();
        }
    }
    //----------------------------------------------------------------------------------------------
    private void detenerAudio() {
        if(mp != null){
            position = mp.getCurrentPosition();
            mp.stop();
            mp = null;
        }
    }

    //----------------------------------------------------------------------------------------------

    public void acercaDeClick(View view){
        //Log.println(Log.INFO,"acercaDeClic", "llegué");
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }

}