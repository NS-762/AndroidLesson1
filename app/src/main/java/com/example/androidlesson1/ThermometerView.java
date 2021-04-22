package com.example.androidlesson1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ThermometerView extends View {

    private int thermometerColor = Color.WHITE;
    private int levelrColor = Color.GREEN;

    private RectF thermometerRectangle = new RectF();
    private Rect levelRectangle = new Rect();
    private RectF headRectangle = new RectF();

    private Paint thermometerPaint;
    private Paint levelPaint;
    private int width = 0;
    private int height = 0;
    private int level = 100;
    private final static int padding = 10;
    private final static int round = 5;
    private final static int hradWidth = 10;


    public  ThermometerView(Context context) {
        super(context);
        init();
    }


    public  ThermometerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public  ThermometerView(Context context, @Nullable AttributeSet attrs,
                            int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ThermometerView,
                0, 0);

        thermometerColor = typedArray.getColor(R.styleable.ThermometerView_thermometer_color, Color.GRAY);

    }
}
