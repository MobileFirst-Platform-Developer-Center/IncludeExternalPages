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

//  WebViewOverlayPlugin.m

#import "WebViewOverlayPlugin.h"

@implementation WebViewOverlayPlugin

- (void)open:(CDVInvokedUrlCommand*)command{
    NSLog(@"WebViewOverlayPlugin :: open");
    
    CGRect rect = self.webView.bounds;
    
    // Display the overlayed content between the header and the tabbar.
    // If iOS 7 - take into account the new status bar behavior and ajust the height accordingly.
    if([[[UIDevice currentDevice] systemVersion] doubleValue] >= 7.0) {
        rect.origin.y += 48;
        rect.size.height -= 48;
    } else {
        rect.origin.y += 39;
        rect.size.height -= 39;
    }
    
    UIWebView *webView = [[UIWebView alloc] initWithFrame:rect];
    webView.tag = 12345;
    [self.webView addSubview:webView];
    
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:[NSURL URLWithString:@"http://m.ibm.com"]];
    [webView loadRequest:urlRequest];
    
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)close:(CDVInvokedUrlCommand*)command{
    NSLog(@"WebViewOverlayPlugin :: close");
    
    for (UIView *view in [self.webView subviews]){
        if (view.tag==12345) {
            [view removeFromSuperview];
        }
    }

    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
