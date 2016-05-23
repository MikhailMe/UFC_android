package mishas.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

import Logic.Judge;

public class MetricsThreeActivity extends AppCompatActivity implements OnClickListener{

    private TextView headValue;
    private SeekBar headSeek;
    private TextView bodyValue;
    private SeekBar bodySeek;
    private TextView handsValue;
    private SeekBar handsSeek;
    private TextView legsValue;
    private SeekBar legsSeek;
    private Button fight;
    private EditText sumValue;
    private Button random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics_three);
        initFields();
        clear();
        initListeners();
        fight.setOnClickListener(this);
        random.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("headValue",headSeek.getProgress());
        savedInstanceState.putInt("bodyValue",bodySeek.getProgress());
        savedInstanceState.putInt("handsValue",handsSeek.getProgress());
        savedInstanceState.putInt("legsValue",legsSeek.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        headSeek.setProgress(savedInstanceState.getInt("headValue"));
        bodySeek.setProgress(savedInstanceState.getInt("bodyValue"));
        handsSeek.setProgress(savedInstanceState.getInt("handsValue"));
        legsSeek.setProgress(savedInstanceState.getInt("legsValue"));
    }

    private void initFields(){
        headValue = (TextView) findViewById(R.id.headValue);
        headSeek = (SeekBar) findViewById(R.id.headSeek);
        bodyValue = (TextView) findViewById(R.id.bodyValue);
        bodySeek = (SeekBar) findViewById(R.id.bodySeek);
        handsValue = (TextView) findViewById(R.id.handsValue);
        handsSeek = (SeekBar) findViewById(R.id.handsSeek);
        legsValue = (TextView) findViewById(R.id.legsValue);
        legsSeek = (SeekBar) findViewById(R.id.legsSeek);
        fight = (Button) findViewById(R.id.Fight);
        sumValue = (EditText) findViewById(R.id.sumValue);
        random = (Button) findViewById(R.id.random);
    }

    private void initListeners(){
        headSeek.setOnSeekBarChangeListener(init(headValue));
        bodySeek.setOnSeekBarChangeListener(init(bodyValue));
        handsSeek.setOnSeekBarChangeListener(init(handsValue));
        legsSeek.setOnSeekBarChangeListener(init(legsValue));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Fight:
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("height",getIntent().getFloatExtra("height",0f));
                intent.putExtra("weight",getIntent().getFloatExtra("weight",0f));
                intent.putExtra("speed",getIntent().getFloatExtra("speed",0f));
                intent.putExtra("endurance",getIntent().getFloatExtra("endurance",0f));
                intent.putExtra("accuracy",getIntent().getFloatExtra("accuracy",0f));
                intent.putExtra("tactics",getIntent().getFloatExtra("tactics",0f));
                intent.putExtra("aggression",getIntent().getFloatExtra("aggression",0f));
                intent.putExtra("head",(float) headSeek.getProgress());
                intent.putExtra("body",(float) bodySeek.getProgress());
                intent.putExtra("hands",(float) handsSeek.getProgress());
                intent.putExtra("legs",(float) legsSeek.getProgress());
                startActivity(intent);
                break;
            case R.id.random:
                Random rand = new Random();
                int coef = rand.nextInt(100);
                headSeek.setProgress(coef);
                coef = rand.nextInt(100);
                bodySeek.setProgress(coef);
                coef = rand.nextInt(100);
                handsSeek.setProgress(coef);
                coef = rand.nextInt(100);
                legsSeek.setProgress(coef);
                break;
            default:
                break;
        }
    }

    private SeekBar.OnSeekBarChangeListener init(final TextView textView){
        SeekBar.OnSeekBarChangeListener seekListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
                int sum = 0;
                sum += headSeek.getProgress();
                sum += bodySeek.getProgress();
                sum += handsSeek.getProgress();
                sum += legsSeek.getProgress();
                if (Judge.controlThree(sum) < 0)
                    fight.setEnabled(false);
                else fight.setEnabled(true);
                sumValue.setText(Judge.controlThree(sum).toString());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        return seekListener;
    }

    private void clear(){
        headSeek.setProgress(0);
        bodySeek.setProgress(0);
        handsSeek.setProgress(0);
        legsSeek.setProgress(0);
    }
}
