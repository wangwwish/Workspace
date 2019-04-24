package com.example.viewlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by wangwei-ds10 on 2019/4/17.
 */

public class ViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ViewFlipper vf = (ViewFlipper) findViewById(R.id.view_flipper);

        vf.addView(View.inflate(this, R.layout.view_advertisement01, null));
        vf.addView(View.inflate(this, R.layout.view_advertisement02, null));
//        vf.addView(View.inflate(this, R.layout.view_advertisement03, null));
        ViewFlipper vf1 = (ViewFlipper) findViewById(R.id.view_flipper1);
        vf1.addView(View.inflate(this, R.layout.view_advertisement01, null));
        vf1.addView(View.inflate(this, R.layout.view_advertisement02, null));
    }
}
