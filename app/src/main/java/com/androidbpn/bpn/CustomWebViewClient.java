package com.androidbpn.bpn;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;

import static android.content.ContentValues.TAG;

public class CustomWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("intent://")) {
            try {
                Context context = view.getContext();
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                if (intent != null) {
                    view.stopLoading();
                    PackageManager packageManager = context.getPackageManager();
                    ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if (info != null) {
                        context.startActivity(intent);
                    } else {
                        String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                        view.loadUrl(fallbackUrl);

//                    or call external broswer
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fallbackUrl));
//                    context.startActivity(browserIntent);
                    }

                    return true;
                }
            } catch (URISyntaxException e) {
                    Log.e(TAG, "Can't resolve intent://", e);
            }
        }

        return false;
    }

}
