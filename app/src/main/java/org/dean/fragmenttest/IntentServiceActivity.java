package org.dean.fragmenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.dean.fragmenttest.service.MyIntentService;

/**
 * Created by Dean on 14-12-25.
 */
public class IntentServiceActivity extends Activity implements View.OnClickListener {
    private Button btn1, btn2, btn3;

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
                Intent serviceIntent = new Intent(IntentServiceActivity.this,MyIntentService.class);
                startService(serviceIntent);
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
        }
    }


}
