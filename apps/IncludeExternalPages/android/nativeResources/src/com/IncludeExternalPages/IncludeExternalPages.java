/**
* Copyright 2015 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
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
