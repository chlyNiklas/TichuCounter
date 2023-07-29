package ch.schreifuchs.tichucounter.lib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThreeWaySwitch extends androidx.appcompat.widget.AppCompatSeekBar {
    private OnSeekBarChangeListener onSeekBarChangeListener;

    public ThreeWaySwitch(Context context) {
        super(context);
        reset();
    }


    public ThreeWaySwitch(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        reset();
    }

    public ThreeWaySwitch(
            @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        reset();
    }

    public void reset() {
        setMax(2);
        setProgress(1);
        setMinWidth((int) (100 * getResources().getDisplayMetrics().density * 0.5f));
        setProgressTintBlendMode(BlendMode.CLEAR);
        setProgressBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        setEnabled(true);

        super.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onProgressChanged(seekBar, progress, b);
                }
                progress = seekBar.getProgress();
                switch (progress) {
                    case 0:
                        setProgressBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        break;
                    case 1:
                        setProgressBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                        break;
                    case 2:
                        setProgressBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        break;

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        });

    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }

    public SwitchState getState() {
        switch (getProgress()) {
            case 0:
                return SwitchState.FALSE;
            case 2:
                return SwitchState.TRUE;
            default:
                return SwitchState.NOTHING;
        }
    }

    public void setState(SwitchState state) {
        switch (state) {
            case TRUE:
                this.setProgress(2);
                break;
            case FALSE:
                this.setProgress(0);
            case NOTHING:
                this.setProgress(1);
        }
    }
}
