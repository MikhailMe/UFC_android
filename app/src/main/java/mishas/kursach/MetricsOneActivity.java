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
        clear();
        initListeners();
        next.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("nameValue",nameValue.getText().toString());
        savedInstanceState.putInt("heightValue",heightSeek.getProgress());
        savedInstanceState.putInt("weightValue",weightSeek.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nameValue.setText(savedInstanceState.getString("nameValue"));
        heightSeek.setProgress(savedInstanceState.getInt("heightValue"));
        weightSeek.setProgress(savedInstanceState.getInt("weightValue"));
    }

    private void initFields() {
        next = (Button) findViewById(R.id.next);
        nameValue = (EditText) findViewById(R.id.nameValue);
        heightValue = (TextView) findViewById(R.id.heightValue);
        heightSeek = (SeekBar) findViewById(R.id.heightSeek);
        weightValue = (TextView) findViewById(R.id.weightValue);
        weightSeek = (SeekBar) findViewById(R.id.weightSeek);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                Intent intent = new Intent(this, MetricsTwoActivity.class);
                intent.putExtra("name",nameValue.getText().toString());
                intent.putExtra("height",(float) heightSeek.getProgress());
                intent.putExtra("weight",(float) weightSeek.getProgress());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initListeners(){
        heightSeek.setOnSeekBarChangeListener(init(heightValue));
        weightSeek.setOnSeekBarChangeListener(init(weightValue));
    }

    public SeekBar.OnSeekBarChangeListener init(final TextView textView){
        SeekBar.OnSeekBarChangeListener sbListenerH = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        return sbListenerH;
    }

    private void clear(){
        nameValue.setText("");
        heightSeek.setProgress(0);
        weightSeek.setProgress(0);
    }
}