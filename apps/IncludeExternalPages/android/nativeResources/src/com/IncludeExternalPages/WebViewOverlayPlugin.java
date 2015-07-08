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

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import android.view.View;

public class WebViewOverlayPlugin extends CordovaPlugin {

	private final String ACTION_OPEN_URL = "open";
	private final String ACTION_CLOSE_WEBVIEWOVERLAY = "close";

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		if (action.equals(ACTION_OPEN_URL)) {
			IncludeExternalPages.thisapp.runOnUiThread(new Runnable() {
				public void run() {
					IncludeExternalPages.clearWebViewOverlayHistory();
					IncludeExternalPages.loadWebViewOverlay("http://m.ibm.com/");
					IncludeExternalPages.setWebViewOverlayVisibility(View.VISIBLE);
					IncludeExternalPages.requestWebViewOverlayFocus();
					IncludeExternalPages.clearWebViewOverlayHistory();
				}
			});
			return true;
		} else if (action.equals(ACTION_CLOSE_WEBVIEWOVERLAY)) {
			IncludeExternalPages.thisapp.runOnUiThread(new Runnable() {
				public void run() {
					IncludeExternalPages.loadWebViewOverlayContent("");
					IncludeExternalPages.setWebViewOverlayVisibility(View.INVISIBLE);
					IncludeExternalPages.clearWebViewOverlayHistory();
				}
			});
			return true;
		} else
			return false;
	}
}
