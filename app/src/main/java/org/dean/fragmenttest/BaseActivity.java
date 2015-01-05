package org.dean.fragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

/**
 * Created by Dean on 14-12-26.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    protected Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                btn1click();
                break;
            case R.id.btn2:
                btn2click();
                break;
            case R.id.btn3:
                btn3click();
                break;
        }
    }

    protected abstract void btn1click();
    protected abstract void btn2click();
    protected abstract void btn3click();


}
