package com.aaa.battleplane;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import android.os.Handler;



public class PlaneAnim extends View {

    Bitmap background, tank;
    Bitmap plane[] = new Bitmap[15];
    int planeX, planeY;
    int planeVelocity = 20;
    Rect dest;
    int dWidth;
    int dHeight;
    static int canvasHeight;
    static int canvasWidth;
    int planeFrame = 0;
    Random random;
    int planeWidth, planeHeight;
    static int tankWidth;
    static int tankHeight;
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS = 30;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    Context context;
    int count = 0;
    SoundPool soundPool;
    int fire = 0;
    boolean explosionState = false;
    int explosionFrame = 0;
    Explosion explosion;
    int explosionX, explosionY;


    public PlaneAnim(Context context) {
        super(context);
        this.context = context;
        handler = new Handler();
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        plane[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_1);
        plane[1] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_2);
        plane[2] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_3);
        plane[3] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_4);
        plane[4] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_5);
        plane[5] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_6);
        plane[6] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_7);
        plane[7] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_8);
        plane[8] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_9);
        plane[9] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_10);
        plane[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_11);
        plane[11] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_12);
        plane[12] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_13);
        plane[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_14);
        plane[14] = BitmapFactory.decodeResource(getResources(), R.drawable.plane_15);
        tank = BitmapFactory.decodeResource(getResources(), R.drawable.tank);

        random = new Random();

//get this screen width and height
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        planeX = dWidth + random.nextInt(200);
        planeY = random.nextInt(100);
        dest = new Rect(0, 0, dWidth, dHeight);

        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        fire = soundPool.load(context, R.raw.fire, 1);

        explosion = new Explosion(context);

        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasHeight = getHeight();
        canvasWidth = getWidth();
        canvas.drawBitmap(background, null, dest, null);
        planeFrame++;
        if (planeFrame == 15)
            planeFrame = 0;
        planeX -= planeVelocity;
        planeWidth = plane[0].getWidth();
        planeHeight = plane[0].getHeight();
        tankWidth = tank.getWidth();
        tankHeight = tank.getHeight();
        if (planeX < -planeWidth) {
            planeX = dWidth + random.nextInt(200);
            planeY = random.nextInt(100);
            planeVelocity = 5 + random.nextInt(16);
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).bulletY > -Bullet.getBulletHeight()) {
                bullets.get(i).bulletY -= bullets.get(i).bulletVelocity;
                canvas.drawBitmap(bullets.get(i).bullet, bullets.get(i).bulletX, bullets.get(i).bulletY, null);
                if ((bullets.get(i).bulletX >= planeX) && (bullets.get(i).bulletX <= planeX + planeWidth) &&
                        (bullets.get(i).bulletY >=planeY) && (bullets.get(i).bulletY < planeY + planeHeight)) {

                    count++;
                    explosionState = true;
                    explosionX = planeX;
                    explosionY = planeY;

                    planeX = dWidth + random.nextInt(200);
                    planeY = random.nextInt(100);
                    planeVelocity = 5 + random.nextInt(16);
                    bullets.remove(i);


                }
            }else{
                bullets.remove(i);
            }
        }
        if (explosionState){
            if (explosionFrame < 9) {
                canvas.drawBitmap(explosion.getExplosion(explosionFrame), explosionX, explosionY, null);
                explosionFrame++;
            } else {
                explosionFrame = 0;
                explosionState = false;
            }

        }
        canvas.drawBitmap(plane[planeFrame], planeX, planeY, null);
        canvas.drawBitmap(tank, (dWidth / 2 - tankWidth / 2), (canvasHeight - tankHeight), null);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            //check we are touching on the tank
            if (touchX >= (dWidth / 2 - tankWidth / 2)
                    && touchX <= (dWidth / 2 + tankWidth / 2)
                    && touchY >= (canvasHeight - tankHeight)) {
                //fire the bullet
                if (bullets.size() < 3) {
                    Bullet bullet = new Bullet(context);
                    bullets.add(bullet);
                    if (fire != 0) {
                        soundPool.play(fire, 1, 1, 0, 0, 1);
                    }
                }
            }
        }
        return true;
    }
}