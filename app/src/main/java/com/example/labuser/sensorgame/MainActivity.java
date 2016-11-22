package com.example.labuser.sensorgame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MainActivity extends AppCompatActivity implements SensorEventListener{


    private Button startGame;
    private SensorManager mSensorManager;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private Sensor mAccelerometer;
    double i = Math.floor(Math.random() * 2) +1;
    private float z;
    private float y;
    //int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startGame = (Button) findViewById(R.id.startGame);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();



    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                z = event.values[0];
                y = event.values[0];

                break;
            case Surface.ROTATION_90:
                z = -event.values[0];
                y = event.values[1];

                break;
            case Surface.ROTATION_180:
                z = -event.values[0];
                y = event.values[0];

                break;
            case Surface.ROTATION_270:
                z = event.values[0];
                y = event.values[1];

                break;
        }
        String[] action;
        action = new String[3];
        action[0] = "UP";
        action[1] = "LEFT";
        action[2] = "RIGHT";

       /*
        if (i == 0) {
            //display string[0]
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[0]);
            if(y > 0){
                //add a text view that says correct
                i = Math.floor(Math.random() * 2) +1;
                //TextView scoreT = (TextView) findViewById(R.id.scoreText);
                //score++;
                //scoreT.setText(score);

            }
            else{
                //add text that says wrong
                i = Math.floor(Math.random() * 2) +1;

            }

        }
        */

        if (i == 1 && z < 5) {
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[1]);
            //move phone left
            //add a text view that says correct
            i = Math.floor(Math.random() * 2) + 1;
            //TextView scoreT = (TextView) findViewById(R.id.scoreText);
            // score++;
            // scoreT.setText(score);

        }
            else{
                //add text that says wrong
                i = Math.floor(Math.random() * 2) +1;


            }



        if (i == 2 && z > 5) {
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(action[2]);
            //move phone right
                //add a text view that says correct
                i = Math.floor(Math.random() * 2) +1;
                //TextView scoreT = (TextView) findViewById(R.id.scoreText);

                //score = score + 1;

                //scoreT.setText(score);
            }
            else{
                //add text that says wrong
                i = Math.floor(Math.random() * 2) +1;

            }
        }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void startGame(View v) {

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);


        //got this timer code from android studio development help
        new CountDownTimer(30000, 1000) {
            TextView timer = (TextView) findViewById(R.id.mTextField);

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();
        //end of timer code




    }
}