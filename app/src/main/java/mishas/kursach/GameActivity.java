package mishas.kursach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import Logic.AfricanFighter;
import Logic.AsianFighter;
import Logic.Health;
import Logic.Judge;

public class GameActivity extends AppCompatActivity {

    private AsianFighter aziat;
    private AfricanFighter afro;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        output = (TextView) findViewById(R.id.output);
        createMyFighter();
        createEnemy();
        Judge.fighters.add(afro);
        Judge.fighters.add(aziat);
        Judge.startFight(output);
    }

    private void createMyFighter(){
        HashMap<String, Float> defenceCoefs = new HashMap<>();
        defenceCoefs.put("head", getIntent().getFloatExtra("head",0f));
        defenceCoefs.put("body", getIntent().getFloatExtra("body",0f));
        defenceCoefs.put("hands", getIntent().getFloatExtra("hands",0f));
        defenceCoefs.put("legs", getIntent().getFloatExtra("legs",0f));
        aziat = new AsianFighter(getIntent().getStringExtra("name"),new Health(100f,100f,100f,100f),
                getIntent().getFloatExtra("height",0f),
                getIntent().getFloatExtra("weight",0f),
                getIntent().getFloatExtra("speed",0f),
                getIntent().getFloatExtra("endurance",0f),
                getIntent().getFloatExtra("accuracy",0f),
                getIntent().getFloatExtra("tactics",0f),
                getIntent().getFloatExtra("aggression",0f),defenceCoefs);
    }

    private void createEnemy(){
        HashMap<String, Float> defenceCoefs = new HashMap<>();
        defenceCoefs.put("head",50f);
        defenceCoefs.put("body",50f);
        defenceCoefs.put("hands",50f);
        defenceCoefs.put("legs",50f);
        afro = new AfricanFighter("afro",new Health(100f,100f,100f,100f),
                180f,80f,50f,50f,50f,50f,50f,defenceCoefs);
    }
}