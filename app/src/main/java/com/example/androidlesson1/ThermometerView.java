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

    private int thermometerColorOut;
    private Paint thermometerPaintOut;
    
    private Paint mercuryBottomPaint;
    private Paint mercuryTopPaint;
    
    private int width;
    private int height;
    private int temperature = 50; //по умолчанию температура 50 градусов
    private final static int round = 40;


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

        thermometerColorOut = typedArray.getColor(R.styleable.ThermometerView_thermometer_color_out, Color.WHITE);
        typedArray.recycle();
    }

    private void init() {
        thermometerPaintOut = new Paint(); //внешняя верхняя часть термометра
        thermometerPaintOut.setColor(thermometerColorOut);
        thermometerPaintOut.setStyle(Paint.Style.FILL);

        mercuryBottomPaint = new Paint(); //внутренняя круглая часть термометра
        mercuryBottomPaint.setColor(Color.parseColor("#ff472b"));
        mercuryBottomPaint.setStyle(Paint.Style.FILL);
    }

    public Shader createShader() {
        LinearGradient shader = new LinearGradient(0, 0, width, height * 5 / 6, //для градиента
                new int[]{
                        Color.parseColor("#ff472b"),
                        Color.parseColor("#ffca58"),
                        Color.parseColor("#a8ff51"),
                        Color.parseColor("#ffca58"),
                        Color.parseColor("#ff472b")}
                , null, Shader.TileMode.CLAMP);
        return shader;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = h / 6; // чтобы сохранить пропорции градусника
        if (width >= w) {
            width = w;
            height = w * 6;
        } else {
            height = h;
        }
        
        mercuryTopPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Shader shader = createShader();
        mercuryTopPaint.setShader(shader);
        mercuryTopPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(width / 3, height * 5 / 6, width - (width / 3), 0,
                round, round, thermometerPaintOut); //рисует верхнюю часть термометра

        canvas.drawCircle(width / 2, height * 11 / 12 - height / 40, width / 2, thermometerPaintOut); //рисует нижнюю круглую часть термометра
        canvas.drawCircle(width / 2, height * 11 / 12 - height / 40, (width / 2) * 0.9f, mercuryBottomPaint); //рисует нижнюю круглую часть ртути

        canvas.drawRect(width / 3 * 1.2f, height * 5 / 6, width - (width / 3) * 1.2f,
                calculationOfMercuryLevel(), mercuryTopPaint); //рисует верхнюю часть ртути
    }

    private float calculationOfMercuryLevel() {
        float mercuryBottom;

        float heightMercury = (height * 4 / 6);
        float heightOneDegree = heightMercury / 100;


        if (temperature >= 50) {
            mercuryBottom = height * 1 / 18; //максимальная температура, минимальный отсуп сверху
        } else if (temperature <= -50) {
            mercuryBottom = height * 1 / 18 + heightMercury; //опустить ртутный столбик на минимум
        } else if (temperature >= 0) {
            mercuryBottom = height * 1 / 18 + (heightOneDegree * (50 - temperature));
            //минимальный отсуп сверху + опустить на величину (50 - temperature)
        } else {
            mercuryBottom = height * 1 / 18 + (heightOneDegree * (50 + (-1 * temperature)));
            //минимальный отсуп сверху + опустить на величину (50 + (-1 * temperature))
        }
        return mercuryBottom;
    }

    public void changeTemperature(int temperature) {
        this.temperature = temperature;
        requestLayout();
    }
}
