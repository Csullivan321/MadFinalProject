package edu.csullivan.example.sequencegame;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ScoreDatabase extends ListActivity {

    int finalScore = 0, score = 100;
    TextView userName;
    ListView showScore;
    String username = "Christopher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_database);


        userName = findViewById(R.id.userName);

    }

    public void setName(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        finalScore = getIntent().getIntExtra("finalScore", -1);
        String scoreString = String.valueOf(finalScore);
        String Username = userName.getText().toString();
        db.emptyHiScores();     // empty table if required

        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");
        db.addHiScore(new HiScore("28 OCT 2020", "" +Username, +finalScore ));
        //db.addHiScore(new HiScore("28 OCT 2020", "Dobby", 16));
        // db.addHiScore(new HiScore("20 NOV 2020", "DarthV", 20));
        //db.addHiScore(new HiScore("20 NOV 2020", "Bob", 18));
        // db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 22));
        //db.addHiScore(new HiScore("30 NOV 2020", "Joe", 30));
        //db.addHiScore(new HiScore("01 DEC 2020", "DarthV", 22));
        // db.addHiScore(new HiScore("02 DEC 2020", "Gandalf", 132)); */


        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");
        List<HiScore> hiScores = db.getAllHiScores();

        List<HiScore> top5HiScores = db.getTopFiveScores();
        ArrayAdapter<HiScore> adapter = new ArrayAdapter<HiScore>(this,
                android.R.layout.simple_list_item_1, top5HiScores);
        setListAdapter(adapter);

        Log.i("divider", "====================");

        HiScore hiScore = top5HiScores.get(top5HiScores.size() - 1);
        // hiScore contains the 5th highest score
        Log.i("fifth Highest score: ", String.valueOf(hiScore.getScore()) );

        int myCurrentScore = 40;

        if (hiScore.getScore() < myCurrentScore) {
            db.addHiScore(new HiScore("08 DEC 2020", "Elrond", 40));
        }

        Log.i("divider", "====================");

        // Calling SQL statement
        top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            Log.i("Score: ", log);
        }

    }


}

