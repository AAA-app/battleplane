package com.aaa.battleplane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    PlaneAnim planeAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        planeAnim = new PlaneAnim(this);
        setContentView(planeAnim);


    }
}
