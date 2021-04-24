package com.example.androidlesson1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ThermometerView extends View {

    private int thermometerColor = Color.WHITE;
    private int levelColor = Color.GREEN;

    private RectF thermometerRectangle = new RectF();
    private RectF headRectangle = new RectF();

    private Rect levelRectangle = new Rect();
    private Paint thermometerPaint;
    private Paint levelPaint;
    private int width = 0;
    private int height = 0;
    private int level = 100;
    private final static int padding = 10;
    private final static int round = 40;
    private final static int headWidth = 30;


    Bitmap bitmap;
    Bitmap scaledBitmap;


    public ThermometerView(Context context) {
        super(context);
        init();
    }


    public ThermometerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public ThermometerView(Context context, @Nullable AttributeSet attrs,
                           int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ThermometerView,
                0, 0);

        thermometerColor = typedArray.getColor(R.styleable.ThermometerView_thermometer_color, Color.WHITE);
        levelColor = typedArray.getColor(R.styleable.ThermometerView_level_color, getResources().getColor(R.color.purple_500));
        level = typedArray.getInteger(R.styleable.ThermometerView_level, 100);
        typedArray.recycle();
    }

    private void init() {
        thermometerPaint = new Paint();
        thermometerPaint.setColor(thermometerColor);
        thermometerPaint.setStyle(Paint.Style.FILL);


        levelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Shader shader = createShader();


//        levelPaint.setColor(levelColor);
        levelPaint.setShader(shader);
        levelPaint.setStyle(Paint.Style.FILL);
    }

    public Shader createShader() {
        /*bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.my_gradient_drawable);
        BitmapShader shader = new BitmapShader(bitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);*/
        LinearGradient shader = new LinearGradient(0, 0, 0, 130,
                new int[]{
                        Color.parseColor("#ff472b"),
                        Color.parseColor("#ffd147"),
                        Color.parseColor("#a8ff51")}
                , null, Shader.TileMode.CLAMP);
        return shader;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
/*        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();*/


        width = h / 6;
        if (width >= w) {
            width = w;
            height = w * 6;
        } else {
            height = h;
        }

/*        thermometerRectangle.set(padding, height - padding, width - padding - headWidth,
                padding);
        headRectangle.set(width - padding - headWidth, 2 * padding, width - padding,
                height - 2 * padding);
        levelRectangle.set(2 * padding, 2 * padding, (int)((width-2*padding - headWidth) * ((double)level/(double)100)),
                height - 2 * padding);*/


        thermometerRectangle.set(width / 3, height * 5 / 6, width - (width / 3), 0);
        headRectangle.set(0, height, width, height * 2 / 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawRoundRect(thermometerRectangle, round, round, thermometerPaint);
//        canvas.drawRect(levelRectangle, levelPaint);
//        canvas.drawRoundRect(headRectangle, round, round, thermometerPaint);
        canvas.drawCircle(width / 2, height * 11 / 12 - height / 50, width / 2, thermometerPaint);
    }
}
