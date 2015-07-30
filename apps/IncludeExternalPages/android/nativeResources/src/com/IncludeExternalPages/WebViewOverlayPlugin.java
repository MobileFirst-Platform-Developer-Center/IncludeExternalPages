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
