package com.aaa.battleplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion {  // crashing to Plane

     Bitmap explosion[] = new Bitmap[9];


    public Explosion(Context context) {
        explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion00);
        explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion11);
        explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion22);
        explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion33);
        explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion44);
        explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion55);
        explosion[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion66);
        explosion[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion77);
        explosion[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_explosion88);
    }

    public Bitmap getExplosion(int explosionFrame) {
        return explosion[explosionFrame];
    }

    public int getExplosionWidth() {
        return explosion[0].getWidth();
    }

    public int getExplosionHeight() {
        return explosion[0].getHeight();
    }
}
