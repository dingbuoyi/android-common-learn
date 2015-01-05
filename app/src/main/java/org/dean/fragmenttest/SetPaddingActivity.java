package org.dean.fragmenttest;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Dean on 14-12-26.
 */
public class SetPaddingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void btn1click() {
        LinearLayout.LayoutParams btnLp = (LinearLayout.LayoutParams)btn1.getLayoutParams();
        //btnLp.setMargins(200, 200, 0, 0);
        //btn1.requestLayout();
        btnLp.leftMargin = 200;
        btnLp.topMargin = 200;
        btn1.setLayoutParams(btnLp);//setLayoutParams内部会调用requestLayout方法的
    }

    @Override
    protected void btn2click() {
        btn2.scrollTo(0,10);
    }

    @Override
    protected void btn3click() {
    }
}
