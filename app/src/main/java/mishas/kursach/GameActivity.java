package mishas.kursach;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Logic.Fighter;
import Logic.Health;
import Logic.Judge;

public class GameActivity extends AppCompatActivity implements OnClickListener{

    private Fighter yourFighter;
    private Fighter enemy;
    private TextView output;
    private TextView result;
    private Button newFight;
    private Button exit;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newFight:
                startActivity(new Intent(this,MetricsOneActivity.class));
                break;
            case R.id.exit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public class MyASyncTask extends AsyncTask<Void,String,Void> {

        private String str;

        @Override
        protected Void doInBackground(Void... params){
            do{
                try{
                    task();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                publishProgress(str);
            }while(!str.contains("won"));
            return null;
        }

        @Override
        protected void onProgressUpdate(String... params){
            super.onProgressUpdate(params);
            if (params[0].contains("won")){
                result.setText(params[0]);
                newFight.setEnabled(true);
                newFight.setVisibility(View.VISIBLE);
            }
            else output.append(params[0] + '\n');
        }

        private void task() throws InterruptedException{
            str = Judge.battle();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initFields();
        newFight.setOnClickListener(this);
        exit.setOnClickListener(this);
        clear();
        createMyFighter();
        createEnemy();
        Judge.fighters.add(yourFighter);
        Judge.fighters.add(enemy);
        Judge.startFight();
        MyASyncTask task = new MyASyncTask();
        task.execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceSaved){
        super.onSaveInstanceState(savedInstanceSaved);
        savedInstanceSaved.putString("output",output.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceSaved){
        super.onRestoreInstanceState(savedInstanceSaved);
        output.setText(savedInstanceSaved.getString("output"));
    }

    private void createMyFighter(){
        HashMap<String, Float> defenceCoefs = new HashMap<>();
        defenceCoefs.put("head", getIntent().getFloatExtra("head",0f));
        defenceCoefs.put("body", getIntent().getFloatExtra("body",0f));
        defenceCoefs.put("hands", getIntent().getFloatExtra("hands",0f));
        defenceCoefs.put("legs", getIntent().getFloatExtra("legs",0f));
        yourFighter = new Fighter(getIntent().getStringExtra("name"),new Health(100f,100f,100f,100f),
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
        defenceCoefs.put("head",55f);
        defenceCoefs.put("body",55f);
        defenceCoefs.put("hands",55f);
        defenceCoefs.put("legs",55f);
        enemy = new Fighter("enemy",new Health(100f,100f,100f,100f),
                185f,85f,65f,65f,65f,65f,65f,defenceCoefs);
    }

    private void initFields(){
        output = (TextView) findViewById(R.id.output);
        result = (TextView) findViewById(R.id.result);
        newFight = (Button) findViewById(R.id.newFight);
        exit = (Button) findViewById(R.id.exit);
    }

    private void clear(){
        output.setText("");
        result.setText("");
        yourFighter = null;
        enemy = null;
        newFight.setVisibility(View.INVISIBLE);
        newFight.setEnabled(false);
    }
}