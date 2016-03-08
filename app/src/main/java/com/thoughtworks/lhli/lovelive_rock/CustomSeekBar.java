package com.thoughtworks.lhli.lovelive_rock;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class CustomSeekBar extends SeekBar {

    private Paint paint = new Paint();
    private String text = "0";

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double thumb_x = ((double) this.getProgress() / this.getMax()) * (double) this.getWidth();
        paint.setTextSize(50.0f);
        canvas.drawText(text, (float) thumb_x, this.getHeight(), paint);
    }

    public void setText(String text) {
        this.text = text;
    }
}
