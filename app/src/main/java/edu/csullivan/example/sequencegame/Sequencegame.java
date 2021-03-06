package edu.csullivan.example.sequencegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sequencegame extends AppCompatActivity implements SensorEventListener {


    int[] gameSequence = new int[120];
    int num = 0, uSequence = -1, score = 0, increase = 2, round = 0;
    String Name;
    TextView tvScore, tvRound;
    Button btnNorth, btnWest, btnEast, btnSouth;

    private final double NORTH_MOVE_FORWARD = 8;     // upper mag limit
    private final double NORTH_MOVE_BACKWARD = 5;      // lower mag limit

    private final double SOUTH_MOVE_FORWARD =  1;     // upper mag limit
    private final double SOUTH_MOVE_BACKWARD = 4;      // lower mag limit

    private final double EAST_MOVE_FORWARD = 1;     // upper mag limit
    private final double EAST_MOVE_BACKWARD = 0;      // lower mag limit

    private final double WEST_MOVE_FORWARD = -1;     // upper mag limit
    private final double WEST_MOVE_BACKWARD = 0;      // lower mag limit

    boolean highLimitNorth = false;      // detect high limit
    boolean highLimitSouth = false;      // detect high limit
    boolean highLimitEast = false;      // detect high limit
    boolean highLimitWest = false;      // detect high limit

    int counterNorth = 0;
    int counterSouth = 0;
    int counterEast = 0;
    int counterWest = 0;


    boolean highLimit = false;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequencegame);

        Bundle extras = getIntent().getExtras();
        int[] arrayB = extras.getIntArray("numbers");

        btnEast = findViewById(R.id.btnGreen);
        btnWest = findViewById(R.id.btnYellow);
        btnNorth = findViewById(R.id.btnBlue);
        btnSouth = findViewById(R.id.btnRed);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gameSequence = getIntent().getIntArrayExtra("sequence");

        round = getIntent().getIntExtra("round", 1);
        score = getIntent().getIntExtra("score", 0);

        tvScore = findViewById(R.id.tvscore);
        tvRound = findViewById(R.id.tvround);

        tvRound.setText(String.valueOf(round));
        tvScore.setText(String.valueOf(score));
    }

    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // North
        if ((x > NORTH_MOVE_FORWARD && z > 0) && (highLimitNorth == false)) {
            highLimitNorth = true;
        }
        if ((x < NORTH_MOVE_BACKWARD && z > 0) && (highLimitNorth == true)) {
            // we have a tilt to the NORTH
            counterNorth++;
            tvScore.setText(String.valueOf(counterNorth));
            highLimitNorth = false;

            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    btnNorth.setPressed(true);
                    btnNorth.invalidate();
                    btnNorth.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            btnNorth.setPressed(false);
                            btnNorth.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }

        // South Movement
        if ((x < SOUTH_MOVE_FORWARD && z < 0) && (highLimitSouth == false)) {
            highLimitSouth = true;
        }
        if ((x > SOUTH_MOVE_BACKWARD && z < 0) && (highLimitSouth == true)) {
            // we have a tilt to the SOUTH
            counterSouth++;
            tvScore.setText(String.valueOf(counterSouth));
            highLimitSouth = false;


            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    btnSouth.setPressed(true);
                    btnSouth.invalidate();
                    btnSouth.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            btnSouth.setPressed(false);
                            btnSouth.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }

        // East Movement
        if (y > EAST_MOVE_FORWARD && highLimitEast == false) {
            highLimitEast = true;
        }
        if (y < EAST_MOVE_BACKWARD && highLimitEast == true) {
            // we have a tilt to the EAST
            counterEast++;
            tvScore.setText(String.valueOf(counterEast));
            highLimitEast = false;


            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    btnEast.setPressed(true);
                    btnEast.invalidate();
                    btnEast.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            btnEast.setPressed(false);
                            btnEast.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }

        // West Movement
        if (y < WEST_MOVE_FORWARD && highLimitWest == false) {
            highLimitWest = true;
        }
        if (y > WEST_MOVE_BACKWARD && highLimitWest == true) {
            // we have a tilt to the WEST
            counterWest++;
            tvScore.setText(String.valueOf(counterWest));
            highLimitWest = false;


            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {

                    btnWest.setPressed(true);
                    btnWest.invalidate();
                    btnWest.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            btnWest.setPressed(false);
                            btnWest.invalidate();
                        }
                    };
                    handler1.postDelayed(r1, 600);

                } // end runnable
            };
            handler.postDelayed(r, 600);


        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void doScore(View view) {
        Intent scoredb = new Intent(view.getContext(), ScoreDatabase.class);
        startActivity(scoredb);
        ListView showscore;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }




    public void doClick(View view){

        uSequence++;

        switch (view.getId())
        {
            case(R.id.btnBlue) :
                num = 1;
                Name ="North";
                break;
            case(R.id.btnYellow) :
                num = 2;
                Name ="West";
                break;
            case(R.id.btnRed) :
                num = 3;
                Name ="South";
                break;
            case(R.id.btnGreen) :
                num = 4;
                Name ="East";
                break;
        }


        for(int i : gameSequence)
        {
            if(num == gameSequence[uSequence])
            {
                score++;
                tvScore.setText(String.valueOf(score));

                if(uSequence > increase)
                {
                    increase = increase + 2;
                    round++;
                    Intent returnToMain = new Intent(Sequencegame.this, MainActivity.class);
                    returnToMain.putExtra("score", score);
                    returnToMain.putExtra("round", round);
                    returnToMain.putExtra("increase", increase);
                    startActivity(returnToMain);
                }
                return;
            }
            else if(num != gameSequence[uSequence])
            {
                Intent intent = new Intent(view.getContext(), GameOver.class);

                intent.putExtra("score", score);
                intent.putExtra("round", round);

                startActivity(intent);

                return;
            }
        }
    }
}
