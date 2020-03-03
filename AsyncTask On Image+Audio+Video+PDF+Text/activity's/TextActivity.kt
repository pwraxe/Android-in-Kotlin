package com.example.asynctaskdemo

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class TextActivity : AppCompatActivity() {

    private var loadTextButton : Button? = null
    private var simpleText : TextView? = null

    lateinit var progress : ProgressDialog
    lateinit var context : Context

    var hasInternet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        context = this
        loadTextButton = findViewById(R.id.loadTextButton)
        simpleText = findViewById(R.id.textView_id)

        loadTextButton?.setOnClickListener {
            LoadText().execute()

        }
    }
    inner class LoadText : AsyncTask<Void,Void,String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setMessage("Downloading")
            progress.setTitle("Fetching Text From Internet")
            progress.setCancelable(false)
            progress.show()
        }
        override fun doInBackground(vararg params: Void?): String? {

            if(isNetworkAailable())
            {
                hasInternet = true
                var httpClient = OkHttpClient()
                var url = "https://script.googleusercontent.com/macros/echo?user_content_key=rhWs14DNPYMtrG7O5vEi_Bmlt2o6g-WNOIL1QHPR-nPSlwPRdRNugF9fYp5YC-jWOm-CAgZsbddnUukwXh-Dim17LmARU5w6m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBg4Wj9So2Q_mI0_S0Bm21-AGmcRnplmVaRcxvVzvCi9cnQQJegsnVb9TgJzPufw35cdv3aNHr6K&lib=MKMzvVvSFmMa3ZLOyg67WCThf1WVRYg6Z"
                val request = Request.Builder().url(url).build()
                val responce = httpClient.newCall(request).execute()
                return responce.body()?.string().toString()
            }
            else
                return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progress.dismiss()
            if(hasInternet)
                simpleText?.text = result
            else
                showNoInternetMessage()
        }

    }

    private fun isNetworkAailable() : Boolean {
        val connectivityManager  = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo  = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected

    }
    fun showNoInternetMessage()
    {
        simpleText?.text = "No Internet Available"
    }
}
