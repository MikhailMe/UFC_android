package mishas.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MetricsOneActivity extends AppCompatActivity implements OnClickListener{

    private Button next;
    private EditText nameValue;
    private TextView heightValue;
    private SeekBar heightSeek;
    private TextView weightValue;
    private SeekBar weightSeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics_one);
        initFields();
        next.setOnClickListener(this);
        initListeners();
    }

    private void initFields() {
        next = (Button) findViewById(R.id.next);
        nameValue = (EditText) findViewById(R.id.nameValue);
        heightValue = (TextView) findViewById(R.id.heightValue);
        heightSeek = (SeekBar) findViewById(R.id.speedSeek);
        weightValue = (TextView) findViewById(R.id.weightValue);
        weightSeek = (SeekBar) findViewById(R.id.weightSeek);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                Intent intent = new Intent(this, MetricsTwoActivity.class);
                intent.putExtra("name",nameValue.getText().toString());
                intent.putExtra("height",heightValue.getText().toString());
                intent.putExtra("weight",weightValue.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initListeners(){
        heightSeek.setOnSeekBarChangeListener(Helper.init(heightValue));
        weightSeek.setOnSeekBarChangeListener(Helper.init(weightValue));
    }
}