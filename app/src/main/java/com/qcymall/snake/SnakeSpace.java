package com.qcymall.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by lanmi on 2018/6/1.
 */

public class SnakeSpace extends View {
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;
    public static final int DIRECTION_RIGHT = 4;
    private int direction;
    private int nextDir;
    private int snakeFood;
    private LinkedList<Integer> snakeArray;
    public SnakeSpace(Context context) {
        super(context);
        initView();
    }

    public SnakeSpace(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SnakeSpace(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SnakeSpace(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        direction = DIRECTION_UP;
        snakeArray = new LinkedList<Integer>();
        snakeArray.add(8*100+16);
        snakeArray.add(8*100+17);
        snakeArray.add(8*100+18);
        showFood();
    }

    public void move(){
        int firstIndex = snakeArray.getFirst();
        switch (direction){
            case DIRECTION_LEFT:
                if (firstIndex >= 100){
                    firstIndex -= 100;
                }else{
                    return;
                }
                break;
            case DIRECTION_UP:
                if (firstIndex%100 > 0){
                    firstIndex -= 1;
                }else{
                    return;
                }
                break;
            case DIRECTION_RIGHT:
                if (firstIndex < 1500){
                    firstIndex += 100;
                }else{
                    return;
                }
                break;
            case DIRECTION_DOWN:
                if (firstIndex%100 < 31){
                    firstIndex += 1;
                }else{
                    return;
                }
                break;
        }
        snakeArray.addFirst(firstIndex);
        if (firstIndex == snakeFood){
            showFood();
        }else{
            snakeArray.removeLast();
        }


        postInvalidate();
    }
    public void move(int dir){
        if ((dir <= 2 && direction <= 2) || (dir > 2 && direction > 2)){
            return;
        }
        direction = dir;
        move();
    }

    private void showFood(){
        int x = (int) Math.round(Math.random() * 16);
        int y = (int) Math.round(Math.random() * 32);

        while (snakeArray.contains(x*100 + y)){
            x = (int) Math.round(Math.random() * 16);
            y = (int) Math.round(Math.random() * 32);
        }
        Log.e("SnakeSpace", "food pos = " + snakeFood);
        snakeFood = x*100 + y;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newHeight = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) * 2, MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, newHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int dx = width / 16;
        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        canvas.drawColor(0xffFCBD00);
        canvas.drawLine(0, 0, width, 0, paint);
        canvas.drawLine(width, 0, width, height, paint);
        canvas.drawLine(0, height, width, height, paint);
        canvas.drawLine(0, 0, 0, height, paint);
        Paint paint2 = new Paint();
        paint2.setColor(0x50000000);

        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 32; j++) {
                if (snakeArray.contains(i*100+j) || snakeFood == (i*100+j)){
                    canvas.drawRect((dx * i) + 1, (dx * j) + 1, (dx * i) + dx - 1, (dx * j) + dx - 1, paint);
                }else{
                    canvas.drawRect((dx * i) + 1, (dx * j) + 1, (dx * i) + dx - 1, (dx * j) + dx - 1, paint2);
                }
            }
        }

        super.onDraw(canvas);
    }

}
