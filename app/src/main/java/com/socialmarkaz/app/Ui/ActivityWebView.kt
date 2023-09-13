package com.socialmarkaz.app.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.socialmarkaz.app.R

class ActivityWebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val webView: WebView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.termsfeed.com/legal/privacy-policy/")
        webView.webViewClient = WebViewClient()
    }
}