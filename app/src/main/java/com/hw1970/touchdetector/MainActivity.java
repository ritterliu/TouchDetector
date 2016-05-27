package com.hw1970.touchdetector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DragView(this));
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

    private class DragView extends SurfaceView implements View.OnTouchListener{
        private Paint mPaint;
        private Canvas mCanvas;
        private SurfaceHolder mSfh;

        public DragView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(100);
            mSfh = getHolder();
            mCanvas = new Canvas();
            this.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mCanvas = mSfh.lockCanvas();
            mCanvas.drawColor(Color.BLACK);
            int action = event.getAction();
            switch (action){
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    int touchCount = event.getPointerCount();
                    mCanvas.drawText("Touch count:" + touchCount, 0 , 180, mPaint);
                    for (int i = 0; i < touchCount; i++) {
                        int x = (int) event.getX(i);
                        int y = (int) event.getY(i);
                        mCanvas.drawText(1 + i + ":(" + x + ":" +y + ")", x - 100 , y - 180, mPaint);
                    }
                    break;
            }
            mSfh.unlockCanvasAndPost(mCanvas);
            return true;
        }
    }
}
