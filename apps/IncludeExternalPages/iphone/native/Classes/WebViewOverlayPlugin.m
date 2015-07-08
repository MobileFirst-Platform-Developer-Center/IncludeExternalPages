/**
 * COPYRIGHT LICENSE: This information contains sample code provided in source code form. You may copy, modify, and distribute
 * these sample programs in any form without payment to IBM® for the purposes of developing, using, marketing or distributing
 * application programs conforming to the application programming interface for the operating platform for which the sample code is written.
 * Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY,
 * FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF THE SAMPLE SOURCE CODE.
 * IBM HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE SOURCE CODE.
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
