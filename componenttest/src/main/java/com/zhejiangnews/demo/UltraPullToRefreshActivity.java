package com.zhejiangnews.demo;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UltraPullToRefreshActivity extends Activity {
	private ImageView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ultra_pull_to_refresh);
		view = (ImageView) findViewById(R.id.store_house_ptr_image);
		final PtrFrameLayout frame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
		// header
		final StoreHouseHeader header = new StoreHouseHeader(UltraPullToRefreshActivity.this);
		header.setPadding(0, 15, 0, 0);

		/**
		 * using a string, support: A-Z 0-9 - . you can add more letters by
		 * {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
		 */
		header.initWithStringArray(R.array.storehouse);

		frame.setDurationToCloseHeader(3000);
		frame.setHeaderView(header);
		frame.addPtrUIHandler(header);
		frame.postDelayed(new Runnable() {
			@Override
			public void run() {
				frame.autoRefresh(false);
			}
		}, 100);

		frame.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				return true;
			}

			@Override
			public void onRefreshBegin(final PtrFrameLayout frame) {
				frame.postDelayed(new Runnable() {
					@Override
					public void run() {
						frame.refreshComplete();
					}
				}, 2000);
			}
		});
	}
}
