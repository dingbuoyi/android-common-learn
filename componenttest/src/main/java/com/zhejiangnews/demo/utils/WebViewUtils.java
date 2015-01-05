package com.zhejiangnews.demo.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;

public final class WebViewUtils {
	private WebViewUtils() {
	}

	public static final void initWithUrl(WebView webview, String url) {
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setDomStorageEnabled(true);// 必须开启，不然有的页面JS会报错
		webview.loadUrl(url);
	}
}
