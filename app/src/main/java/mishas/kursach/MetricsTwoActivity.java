package mishas.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics_two);
        initFields();
        next.setOnClickListener(this);
        initListeners();
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
    }

    private void initListeners(){
        speedSeek.setOnSeekBarChangeListener(Helper.init(speedValue));
        enduranceSeek.setOnSeekBarChangeListener(Helper.init(enduranceValue));
        accuracySeek.setOnSeekBarChangeListener(Helper.init(accuracyValue));
        tacticsSeek.setOnSeekBarChangeListener(Helper.init(tacticsValue));
        aggressionSeek.setOnSeekBarChangeListener(Helper.init(aggressionValue));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next2:
                Intent intent = new Intent(this, MetricsThreeActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("height",getIntent().getStringExtra("height"));
                intent.putExtra("weight",getIntent().getStringExtra("weight"));
                intent.putExtra("speed",speedValue.getText().toString());
                intent.putExtra("endurance",enduranceValue.getText().toString());
                intent.putExtra("accuracy",accuracyValue.getText().toString());
                intent.putExtra("tactics",tacticsValue.getText().toString());
                intent.putExtra("aggression",aggressionValue.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}