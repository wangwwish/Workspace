package com.wwish.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.viewlibrary.ViewActivity;

/**
 * Created by wwish on 2017/12/12.
 */

public class LauchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Fragment fragment = (Fragment) ARouter.getInstance().build("/test/fragment").navigation();
        Toast.makeText(LauchActivity.this, "找到Fragment:" + fragment.toString(), Toast.LENGTH_SHORT).show();
    }

    public void enterWaterImage(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void enterTabLayout(View view) {
        startActivity(new Intent(this, TabLayoutActivity.class));
    }

    public void enterViewLayout(View view) {
        startActivity(new Intent(this, ViewActivity.class));
    }
}
