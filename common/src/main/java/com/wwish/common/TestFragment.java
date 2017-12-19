package com.wwish.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by wwish on 2017/12/13.
 */
@Route(path = "/test/fragment")
public class TestFragment extends Fragment {
    @Autowired
    String name;

//    @Autowired(required = true)
//    TestObj obj;

    public TestFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TextView tv = new TextView(getContext());
        tv.setText("test");

        return tv;
    }
}
