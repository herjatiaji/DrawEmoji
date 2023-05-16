package com.example.drawemoji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas canvas;
    private Paint paint = new Paint();
    private Paint paintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);

    private Paint paintCircle = new Paint();
    private Bitmap bitmap;
    private ImageView imageView;

    private Rect rect = new Rect();
    private Rect bounds = new Rect();
    private static final int OFFSET = 120;
    private int offset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int colorBlack,colorYellow,colorBg;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorBlack = ResourcesCompat.getColor(getResources(), R.color.black, null);
        colorYellow = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        colorBg = ResourcesCompat.getColor(getResources(), R.color.background, null);


        paint.setColor(colorBlack);


        imageView = findViewById(R.id.image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSomething(v, count);
                count += 1;
            }
        });
    }

    public void drawSomething(View v, int count){
        int width = v.getWidth();
        int height = v.getHeight();
        int halfWidth = v.getWidth()/2;
        int halfHeight = v.getHeight()/2;
        float radius = (float) (Math.min(width/6, height/6));

        if(count == 0) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            imageView.setImageBitmap(bitmap);
            canvas = new Canvas(bitmap);
        }

        else if(count == 1){
            //background sky
            paint.setColor(colorBg);
            Point k = new Point(halfWidth-1000, halfHeight-1000);
            Point l = new Point(halfWidth+1000, halfHeight-1000);
            Point m = new Point(halfWidth+1000, halfHeight+1000);
            Point n = new Point(halfWidth-1000, halfHeight+1000);

            Path bg = new Path();
            bg.setFillType(Path.FillType.EVEN_ODD);
            bg.lineTo(k.x, k.y);
            bg.lineTo(l.x, l.y);
            bg.lineTo(m.x, m.y);
            bg.lineTo(n.x, n.y);
            bg.lineTo(k.x, k.y);
            bg.close();

            canvas.drawPath(bg, paint);
        }

        else if(count == 2) {
            //background ground
            paint.setColor(colorYellow);
            canvas.drawCircle(halfWidth, halfHeight, 400, paint);
        }

        else if(count == 3) {
            //left rear foot
            paint.setColor(colorBlack);
            canvas.drawCircle(halfWidth, 980, 300, paint);
        }

        else if (count == 4) {
            //right rear foot
            paint.setColor(colorYellow);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(halfWidth, 960, 300, paint);
        }

        else if(count == 5) {
            //body
            paint.setColor(Color.BLACK);
            canvas.drawCircle(width/3, 800, 30, paint);
            canvas.drawCircle(700, 800, 30, paint);
        }



        else {

        }

        v.invalidate();
    }
}