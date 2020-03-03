package com.example.webviewdemo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {


    private var searchbox : EditText? = null
    private var searchButton : Button? = null
    private var githubButton : Button? = null
    private var googleButton : Button? = null
    private var youtubeButton : Button? = null
    private var webView : WebView? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchbox = findViewById(R.id.search_editText_id)
        searchButton = findViewById(R.id.searchButton_id)
        githubButton = findViewById(R.id.githubButton_id)
        googleButton = findViewById(R.id.googleButton_id)
        youtubeButton = findViewById(R.id.youtubeButton_id)
        webView = findViewById(R.id.webView_id)

        searchButton?.setOnClickListener {
            val usrTxt = searchbox?.text.toString()
            if(webView!!.canGoBack()){
                webView!!.goBack()      // TO Previous Page
            }
            if(webView!!.canGoForward()){
                webView!!.goForward()       // Next page of current page
            }


        }
        githubButton?.setOnClickListener {
            webView?.loadUrl("https://github.com/pwraxe")
        }
        googleButton?.setOnClickListener {
            webView?.loadUrl("https://www.google.co.in")
        }
        youtubeButton?.setOnClickListener {
            webView?.loadUrl("https://www.youtube.com/")
        }

    }
}

