package com.aaa.battleplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet {
    int bulletX;
    int bulletY;
    int bulletVelocity;
    static Bitmap bullet;
//    static Bitmap bullet[] = new Bitmap[6];

    public Bullet(Context context) {
        bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet00);
//        bullet[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet1);
//        bullet[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet2);
//        bullet[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet3);
//        bullet[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet4);
//        bullet[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet5);





        bulletX = PlaneAnim.canvasWidth / 2 - bullet.getWidth() /2;
        bulletY = PlaneAnim.canvasHeight - PlaneAnim.tankHeight;
        bulletVelocity = 40;
    }

    public static int getBulletHeight() {
        return bullet.getHeight();
    }

    public static int getBulletWidth() {
        return bullet.getWidth();
    }

}
