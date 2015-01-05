package com.zhejiangnews.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BaseSampleActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Add the Sample Fragment if there is one
		Fragment sampleFragment = getSampleFragment();
		if (sampleFragment != null) {
			getSupportFragmentManager().beginTransaction().replace(android.R.id.content, sampleFragment).commit();
		}
	}

	protected Fragment getSampleFragment() {
		return null;
	}

}
