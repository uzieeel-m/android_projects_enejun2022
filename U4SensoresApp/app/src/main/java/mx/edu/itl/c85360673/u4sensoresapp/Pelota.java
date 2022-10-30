package mx.edu.itl.c85360673.u4sensoresapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class Pelota extends View implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    private int x;
    private int y;
    private int radio = 50;
    private int ancho;
    private int alto;
    //establecer el color de la pelota
    private Paint paint;

    public Pelota(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //obtener las dimensiones de la pantalla del dispositivo
        Display pantalla = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        Point dimensiones = new Point();
        pantalla.getSize(dimensiones);

        ancho = dimensiones.x;
        alto = dimensiones.y;
        x = ancho / 2;
        y = alto / 2;

        //establecer el color de la pelota
        paint = new Paint();
        paint.setColor(Color.MAGENTA);

        //obtenemos la instancia del acelerómetro
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    //registrar coomo un listener del objeto pelota
    public void  iniciar(){
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void detener(){
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //calcular la nueva posición x y de la pelota en base a las lecturas del acelerómetro
        //en los ejes X, Y
        x -= sensorEvent.values[0]*6;
        y += sensorEvent.values[1]*6;

        //checar si no se sobrepasan los limites de la pantalla
        if(x<radio){ //borde izquierdo
            x = radio;
        } else if(x>ancho-radio){//borde derech
            x = ancho-radio;
        }

        if(y<radio){ //borde izquierdo
            y = radio;
        } else if(y>alto-2*radio){//borde derech
            y = alto-2*radio;
        }

        //invocar invalidate del view para invocar el método draw
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //Dibujar la pelota en las coordenadas x, y
        canvas.drawCircle(x, y, radio, paint);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
