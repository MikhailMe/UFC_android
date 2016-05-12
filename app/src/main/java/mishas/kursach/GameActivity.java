package mishas.kursach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

import Logic.AfricanFighter;
import Logic.AsianFighter;
import Logic.Health;
import Logic.Judge;
import Logic.ScheduledTask;

public class GameActivity extends AppCompatActivity {

    private AsianFighter aziat;
    private AfricanFighter afro;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //createMyFighter();
        createEnemy();
        output = (TextView) findViewById(R.id.output);
        Judge.fighters.add(aziat);
        Judge.fighters.add(afro);
        ScheduledTask task = new ScheduledTask(output);
        Judge.startFight(task);
    }

    private void createMyFighter(){
        HashMap<String, Float> defenceCoefs = new HashMap<>();
        defenceCoefs.put("head", Float.valueOf(getIntent().getStringExtra("head")));
        defenceCoefs.put("body", Float.valueOf(getIntent().getStringExtra("body")));
        defenceCoefs.put("hands", Float.valueOf(getIntent().getStringExtra("hands")));
        defenceCoefs.put("legs", Float.valueOf(getIntent().getStringExtra("legs")));
        aziat = new AsianFighter(getIntent().getStringExtra("name"),new Health(100f,100f,100f,100f),
                Float.valueOf(getIntent().getStringExtra("height")),
                Float.valueOf(getIntent().getStringExtra("weight")),
                Float.valueOf(getIntent().getStringExtra("speed")),
                Float.valueOf(getIntent().getStringExtra("endurance")),
                1f ,Float.valueOf(getIntent().getStringExtra("accuracy")),
                Float.valueOf(getIntent().getStringExtra("tactics")),
                Float.valueOf(getIntent().getStringExtra("aggression")),defenceCoefs);
    }

    private void createEnemy(){
        HashMap<String, Float> defenceCoefs = new HashMap<>();
        defenceCoefs.put("head",50f);
        defenceCoefs.put("body",50f);
        defenceCoefs.put("hands",50f);
        defenceCoefs.put("legs",50f);
        afro = new AfricanFighter("afro",new Health(100f,100f,100f,100f),
                180f,80f,50f,50f,1f,50f,50f,50f,defenceCoefs);
        aziat = new AsianFighter("aziat",new Health(100f,100f,100f,100f),
                180f,80f,50f,50f,1f,50f,50f,50f,defenceCoefs);
    }
}

/*
    private void createMyFighter(){
        HashMap<String, Float> defenceCoefs = new HashMap<>();
        defenceCoefs.put("0", Float.parseFloat(getIntent().getStringExtra("head")));
        defenceCoefs.put("1", Float.parseFloat(getIntent().getStringExtra("body")));
        defenceCoefs.put("2", Float.parseFloat(getIntent().getStringExtra("hands")));
        defenceCoefs.put("3", Float.parseFloat(getIntent().getStringExtra("legs")));
        float height = Float.parseFloat(getIntent().getStringExtra("height"));
        float weight = Float.parseFloat(getIntent().getStringExtra("weight"));
        float speed = Float.parseFloat(getIntent().getStringExtra("speed"));
        float endurance = Float.parseFloat(getIntent().getStringExtra("endurance"));
        float accuracy = Float.parseFloat(getIntent().getStringExtra("accuracy"));
        float tactics = Float.parseFloat(getIntent().getStringExtra("tactics"));
        float aggression = Float.parseFloat(getIntent().getStringExtra("aggression"));
        aziat = new AsianFighter(getIntent().getStringExtra("name"),new Health(100f,100f,100f,100f),
                height, weight, speed, endurance,
                1f ,accuracy,tactics, aggression,defenceCoefs);
    }*/
