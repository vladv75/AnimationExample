package ru.allfound.animationexample.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyWidget extends View {

    Paint paint;

    public MyWidget(Context context, final AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int w = this.getWidth();
        final int h = this.getHeight();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4f);
        canvas.drawRect(0, 0, w, h, paint);
        canvas.drawPath(makeArrowHorizontal(0, h / 2, w), paint);
        canvas.drawPath(makeArrowVertical(w / 2, h, h), paint);

        paint.setTextSize(24f);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(2f);
        canvas.drawText("X", w / 2 + 30, 50, paint);
        canvas.drawText("Y", w - 30, h / 2 - 30, paint);

        paint.setColor(Color.RED);
        makeTriangleLine(canvas, w / 2, h / 2, 150);

        paint.setColor(Color.BLUE);
        makeCircleLine(canvas, w / 2, h / 2, 150);
    }

    private Path makeCircleLine(Canvas canvas, float startX, float startY, int size) {
        Path path = new Path();
        path.reset();
        int step = size / 5;
        for (int i = 0; i < size; i++) {
            canvas.drawCircle(startX + i, startY - i, 5, paint);
            i += step;
        }
        path.close();
        return path;
    }

    private Path makeTriangleLine(Canvas canvas, float startX, float startY, int size) {
        Path path = new Path();
        path.reset();
        int step = size / 5;
        for (int i = 0; i < size; i++) {
            canvas.drawPath(makeTriangle(startX + i, startY + i, 5), paint);
            i += step;
        }
        path.close();
        return path;
    }

    private Path makeTriangle(float startX, float startY, float size) {
        Path path = new Path();
        path.reset();
        path.moveTo(startX, startY);
        path.moveTo(startX, startY - size);
        path.lineTo(startX + size, startY + size);
        path.lineTo(startX - size, startY + size);
        path.close();
        return path;
    }

    private Path makeArrowHorizontal(float x, float y, float length) {
        Path path = new Path();
        path.reset();
        path.moveTo(x, y);
        path.lineTo(length * 0.97f, y);
        path.lineTo(length * 0.95f, y * 0.93f);
        path.lineTo(length,  y);
        path.lineTo(length * 0.95f, y * 1.07f);
        path.lineTo(length * 0.97f, y);
        path.close();
        return path;
    }

    private Path makeArrowVertical(float x, float y, float length) {
        Path path = new Path();
        path.reset();
        path.moveTo(x, y);
        path.lineTo(x, y - length * 0.97f);
        path.lineTo(x * 0.93f, y - length * 0.95f);
        path.lineTo(x,  y - length);
        path.lineTo(x * 1.07f, y - length * 0.95f);
        path.lineTo(x, y - length * 0.97f);
        path.close();
        return path;
    }
}
