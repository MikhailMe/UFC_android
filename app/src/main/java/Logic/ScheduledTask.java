package Logic;

import android.widget.TextView;

import java.util.TimerTask;

public class ScheduledTask extends TimerTask {

    private TextView output;

    public ScheduledTask(TextView output){
        this.output = output;
    }

    @Override
    public void run() {
        output.append(Judge.battle() + '\n');
    }
}