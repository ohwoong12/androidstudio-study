package com.webbrowser.ch07_webbroswer

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.webbrowser.ch07_webbroswer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var editUrl: EditText
    lateinit var moveBtn: Button
    lateinit var previousBtn: Button
    lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editUrl = findViewById(R.id.editUrl)
        moveBtn = findViewById(R.id.MoveBtn)
        previousBtn = findViewById(R.id.previousBtn)
        web = findViewById(R.id.urlWebView)

        web.webViewClient = CookWebViewClient()

        var webSet = web.settings
        webSet.javaScriptEnabled = true
        webSet.builtInZoomControls = true

        moveBtn.setOnClickListener {
            web.loadUrl(editUrl.text.toString())
        }

        previousBtn.setOnClickListener {
            web.goBack()
        }


    }

    class CookWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}