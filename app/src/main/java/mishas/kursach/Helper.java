package mishas.kursach;

import android.widget.SeekBar;
import android.widget.SeekBar.*;
import android.widget.TextView;

public final class Helper {
    public static OnSeekBarChangeListener init(final TextView textView){
        OnSeekBarChangeListener sbListenerH = new OnSeekBarChangeListener() {
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
}
