package com.hw1970.touchdetector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DragImage dragView = new DragImage(this);
        setContentView(dragView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DragImage extends SurfaceView implements SurfaceHolder.Callback,View.OnTouchListener {
        private Paint paint;
        private Canvas canvas;
        private SurfaceHolder sfh;

        public DragImage(Context context) {
            super(context);
            sfh=this.getHolder();
            sfh.addCallback(this);
            this.setOnTouchListener(this);
            canvas = new Canvas();
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            paint=new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setAntiAlias(true);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }


        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.BLACK);
            int touchCount = event.getPointerCount();
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    Log.i("Ritter", "ACTION_UP touchCount:" + touchCount);
                    break;
                default:
                    Log.i("Ritter", "DEFAULT touchCount:" + touchCount + ", motion:" + event.getAction());
                    canvas.drawText("Total count:" + touchCount, 0, 60, this.paint);
                    for (int i = 0; i < touchCount; i++) {
                        int touchX = (int) event.getX(i);
                        int touchY = (int) event.getY(i);
                        canvas.drawText(1 + i + ":(" + touchX + "," + touchY + ")", touchX - 50, touchY - 100, this.paint);
                    }
                    break;
            }
            sfh.unlockCanvasAndPost(canvas);
            return true;
        }

    }
}
