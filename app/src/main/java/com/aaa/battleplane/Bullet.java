package com.aaa.battleplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet {
    int bulletX;
    int bulletY;
    int bulletVelocity;
    static Bitmap bullet;

    public Bullet(Context context) {
        bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bulet);
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
