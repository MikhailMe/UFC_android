package mishas.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics_three);
        initFields();
        fight.setOnClickListener(this);
        initListeners();
    }

    private void initFields(){
        headValue = (TextView) findViewById(R.id.HeadValue);
        headSeek = (SeekBar) findViewById(R.id.HeadSeek);
        bodyValue = (TextView) findViewById(R.id.BodyValue);
        bodySeek = (SeekBar) findViewById(R.id.BodySeek);
        handsValue = (TextView) findViewById(R.id.HandsValue);
        handsSeek = (SeekBar) findViewById(R.id.HandsSeek);
        legsValue = (TextView) findViewById(R.id.LegsValue);
        legsSeek = (SeekBar) findViewById(R.id.LegsSeek);
        fight = (Button) findViewById(R.id.Fight);
    }

    private void initListeners(){
        headSeek.setOnSeekBarChangeListener(Helper.init(headValue));
        bodySeek.setOnSeekBarChangeListener(Helper.init(bodyValue));
        handsSeek.setOnSeekBarChangeListener(Helper.init(handsValue));
        legsSeek.setOnSeekBarChangeListener(Helper.init(legsValue));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Fight:
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("height",getIntent().getStringExtra("height"));
                intent.putExtra("speed",getIntent().getStringExtra("speed"));
                intent.putExtra("endurance",getIntent().getStringExtra("endurance"));
                intent.putExtra("accuracy",getIntent().getStringExtra("accuracy"));
                intent.putExtra("tactics",getIntent().getStringExtra("tactics"));
                intent.putExtra("aggression",getIntent().getStringExtra("aggression"));
                intent.putExtra("head",headValue.getText().toString());
                intent.putExtra("body",bodyValue.getText().toString());
                intent.putExtra("hands",handsValue.getText().toString());
                intent.putExtra("legs",legsValue.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
