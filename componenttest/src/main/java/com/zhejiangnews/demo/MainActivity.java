package com.zhejiangnews.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.haarman.listviewanimations.appearance.AppearanceExamplesActivity;
import com.haarman.listviewanimations.googlecards.GoogleCardsActivity;

public class MainActivity extends SherlockFragmentActivity implements OnClickListener {
	Button btn1, btn2, btn3,btn4,btn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btn1:
			Intent btn1Intent = new Intent(MainActivity.this, ActionbarPullToRefreshListActivity.class);
			startActivity(btn1Intent);
			break;
		case R.id.btn2:
			Intent btn2Intent = new Intent(MainActivity.this, UltraPullToRefreshActivity.class);
			startActivity(btn2Intent);
			break;
		case R.id.btn3:
			Intent btn3Intent = new Intent(MainActivity.this, GoogleCardsActivity.class);
			startActivity(btn3Intent);
            break;
		case R.id.btn4:
			Intent btn4Intent = new Intent(MainActivity.this, AppearanceExamplesActivity.class);
			startActivity(btn4Intent);
            break;
		case R.id.btn5:
			Intent btn5Intent = new Intent(MainActivity.this, AnimatedActivity.class);
			startActivity(btn5Intent);
            break;
		default:
			break;
		}
	}

}
