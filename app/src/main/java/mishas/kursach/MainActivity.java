package mishas.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button newGame;
    private Button about;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();
        newGame.setOnClickListener(this);
        about.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initFields(){
        newGame = (Button) findViewById(R.id.newGame);
        about = (Button) findViewById(R.id.about);
        exit = (Button) findViewById(R.id.exit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newGame:
                startActivity(new Intent(this, MetricsOneActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.exit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }
}