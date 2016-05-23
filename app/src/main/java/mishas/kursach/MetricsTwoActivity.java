package mishas.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.*;
import android.widget.TextView;

import java.util.Random;

import Logic.Judge;

public class MetricsTwoActivity extends AppCompatActivity implements OnClickListener{

    private TextView speedValue;
    private SeekBar speedSeek;
    private TextView enduranceValue;
    private SeekBar enduranceSeek;
    private TextView accuracyValue;
    private SeekBar accuracySeek;
    private TextView tacticsValue;
    private SeekBar tacticsSeek;
    private TextView aggressionValue;
    private SeekBar aggressionSeek;
    private Button next;
    private EditText sumValue;
    private Button random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics_two);
        initFields();
        clear();
        initListeners();
        next.setOnClickListener(this);
        random.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("speedValue",speedSeek.getProgress());
        savedInstanceState.putInt("enduranceValue",enduranceSeek.getProgress());
        savedInstanceState.putInt("accuracyValue",accuracySeek.getProgress());
        savedInstanceState.putInt("tacticsValue",tacticsSeek.getProgress());
        savedInstanceState.putInt("aggressionValue",aggressionSeek.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        speedSeek.setProgress(savedInstanceState.getInt("speedValue"));
        enduranceSeek.setProgress(savedInstanceState.getInt("enduranceValue"));
        accuracySeek.setProgress(savedInstanceState.getInt("accuracyValue"));
        tacticsSeek.setProgress(savedInstanceState.getInt("tacticsValue"));
        aggressionSeek.setProgress(savedInstanceState.getInt("aggressionValue"));
    }

    private void initFields(){
        speedValue = (TextView) findViewById(R.id.speedValue);
        speedSeek = (SeekBar) findViewById(R.id.speedSeek);
        enduranceValue = (TextView) findViewById(R.id.enduranceValue);
        enduranceSeek = (SeekBar) findViewById(R.id.enduranceSeek);
        accuracyValue = (TextView) findViewById(R.id.accuracyValue);
        accuracySeek = (SeekBar) findViewById(R.id.accuracySeek);
        tacticsValue = (TextView) findViewById(R.id.tacticsValue);
        tacticsSeek = (SeekBar) findViewById(R.id.tacticsSeek);
        aggressionValue = (TextView) findViewById(R.id.aggressionValue);
        aggressionSeek = (SeekBar) findViewById(R.id.aggressionSeek);
        next = (Button) findViewById(R.id.next2);
        sumValue = (EditText) findViewById(R.id.sumValue);
        random = (Button) findViewById(R.id.random);
    }

    private void initListeners(){
        speedSeek.setOnSeekBarChangeListener(init(speedValue));
        enduranceSeek.setOnSeekBarChangeListener(init(enduranceValue));
        accuracySeek.setOnSeekBarChangeListener(init(accuracyValue));
        tacticsSeek.setOnSeekBarChangeListener(init(tacticsValue));
        aggressionSeek.setOnSeekBarChangeListener(init(aggressionValue));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next2:
                Intent intent = new Intent(this, MetricsThreeActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("height",getIntent().getFloatExtra("height",0f));
                intent.putExtra("weight",getIntent().getFloatExtra("weight",0f));
                intent.putExtra("speed",(float) speedSeek.getProgress());
                intent.putExtra("endurance",(float) enduranceSeek.getProgress());
                intent.putExtra("accuracy",(float) accuracySeek.getProgress());
                intent.putExtra("tactics",(float) tacticsSeek.getProgress());
                intent.putExtra("aggression",(float) aggressionSeek.getProgress());
                startActivity(intent);
                break;
            case R.id.random:
                Random rand = new Random();
                int coef = rand.nextInt(100);
                speedSeek.setProgress(coef);
                coef = rand.nextInt(100);
                enduranceSeek.setProgress(coef);
                coef = rand.nextInt(100);
                accuracySeek.setProgress(coef);
                coef = rand.nextInt(100);
                tacticsSeek.setProgress(coef);
                coef = rand.nextInt(100);
                aggressionSeek.setProgress(coef);
                break;
            default:
                break;
        }
    }

    private OnSeekBarChangeListener init(final TextView textView){
        OnSeekBarChangeListener seekListener = new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
                int sum = 0;
                sum += speedSeek.getProgress();
                sum += enduranceSeek.getProgress();
                sum += accuracySeek.getProgress();
                sum += tacticsSeek.getProgress();
                sum += aggressionSeek.getProgress();
                if (Judge.controlTwo(sum) < 0)
                    next.setEnabled(false);
                else next.setEnabled(true);
                sumValue.setText(Judge.controlTwo(sum).toString());
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
        speedSeek.setProgress(0);
        enduranceSeek.setProgress(0);
        accuracySeek.setProgress(0);
        tacticsSeek.setProgress(0);
        aggressionSeek.setProgress(0);
    }
}