/*
 *
    COPYRIGHT LICENSE: This information contains sample code provided in source code form. You may copy, modify, and distribute
    these sample programs in any form without payment to IBM® for the purposes of developing, using, marketing or distributing
    application programs conforming to the application programming interface for the operating platform for which the sample code is written.
    Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES,
    EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY,
    FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF THE SAMPLE SOURCE CODE.
    IBM HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE SOURCE CODE.

 */

package com.IncludeExternalPages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import org.apache.cordova.CordovaActivity;
import com.worklight.androidgap.api.WL;
import com.worklight.androidgap.api.WLInitWebFrameworkResult;
import com.worklight.androidgap.api.WLInitWebFrameworkListener;

public class IncludeExternalPages extends CordovaActivity implements WLInitWebFrameworkListener {
	private static WebView webViewOverlay;
	public static Activity thisapp;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

	
		WL.createInstance(this);

		WL.getInstance().showSplashScreen(this);

		WL.getInstance().initializeWebFramework(getApplicationContext(), this);
		
	}
	
	public static void loadWebViewOverlay(String url) {
		webViewOverlay.loadUrl(url);
	}

	public static void setWebViewOverlayVisibility(int visibility) {
		webViewOverlay.setVisibility(visibility);
	}

	public static void loadWebViewOverlayContent(String content) {
		webViewOverlay.loadData(content, "text/html", "UTF-8");
	}

	public static void requestWebViewOverlayFocus() {
		webViewOverlay.requestFocus();
	}

	public static void clearWebViewOverlayHistory() {
		webViewOverlay.clearHistory();
	}

	/**
	 * The IBM Worklight framework calls the onInitWebFrameworkComplete() API after its initialization is complete and web resources are ready to be used.
	 */
 	public void onInitWebFrameworkComplete(WLInitWebFrameworkResult result){
		if (result.getStatusCode() == WLInitWebFrameworkResult.SUCCESS) {
			super.loadUrl(WL.getInstance().getMainHtmlFilePath());
			
			thisapp = this;	
			WebViewClient webViewClient = new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					Log.d("IncludeExternalPages", "webViewOverlay is openning :: " + url);
					view.loadUrl(url);
					return true;
				}
			};
			
			webViewOverlay = new WebView(this);
			webViewOverlay.setVisibility(View.INVISIBLE);
			webViewOverlay.setWebViewClient(webViewClient);

			RelativeLayout.LayoutParams webViewOverlayLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			webViewOverlayLayoutParams.setMargins(0, 120, 0, 196);
			webViewOverlay.setLayoutParams(webViewOverlayLayoutParams);
			webViewOverlay.getSettings().setJavaScriptEnabled(true);

			RelativeLayout rootRelativeLayout = new RelativeLayout(this);
			
			((FrameLayout)root.getParent()).removeAllViews();
			rootRelativeLayout.addView(root);
			rootRelativeLayout.addView(webViewOverlay);
			setContentView(rootRelativeLayout);
			
		} else {
			handleWebFrameworkInitFailure(result);
		}
	}

	private void handleWebFrameworkInitFailure(WLInitWebFrameworkResult result){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setNegativeButton(R.string.close, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which){
				finish();
			}
		});

		alertDialogBuilder.setTitle(R.string.error);
		alertDialogBuilder.setMessage(result.getMessage());
		alertDialogBuilder.setCancelable(false).create().show();
	}
	
	
}
