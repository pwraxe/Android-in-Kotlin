package com.codexdroid.razorpaypracticedemo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

/***
 * Key ID : rzp_test_*******rM
 * Key Secret : F6LS***...***zOxqTvY
 *
 *
 * -------------------------------------------------
 *
 * Follow this step :
 *      https://razorpay.com/docs/payment-gateway/android-integration/standard/
 *
 *
 *
 * Step 1 : Download Razorpay SDK
 *      1. Goto https://razorpay.com/integrations/
 *      2. on Android > Click on Download
 *      3. select letest version
 *      4. you will see  Files : aar(sizeIn_KB)
 *      5. click on it and download
 *      6. copy that downloaded aar file and paste in app>libs as it is with extract
 *
 *
 *
 * Step 2 : Do it after download SDK (Important)
 *
 *      open build.gradle(Module: app)  file
 *      above || outside the android {}
 *          type following as it is
 *--------------------------------------------------------
 *          repositories {
 *              mavenCentral()
 *                  flatDir {
 *                      dirs 'libs'
 *                  }
 *          }
 *
 *          // Note : flatDir{} is extra here
 *--------------------------------------------------------
 *
 *
 * Step 3 : add following dependency  (get letest dependency from document)
 *      implementation 'com.razorpay:checkout:1.5.16'   +  Sync
 *
 *
 * Step 4 : if Gradle Scripts Contains 'proguard-rules.pro' file
 *          then add proguard rule in that file
 *
 *
 *
 *XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX WAIT ============>
 *
 * Problem :
 *  When you try to make payment via Upi like PhonePay OR GooglePay
 *
 *  then default razorpay upi id appear there
 *
 *  If you find solution to alter upi then do following code
 *
 * */

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Checkout.preload(applicationContext)

    }

    fun payWithRazorpay(view: View) {

        try {

            val checkout = Checkout()
            val jsonObj = JSONObject()
            checkout.setKeyID("rzp_live_SuITUgzoQZhVrM")

            jsonObj.put("name","Akshay Pawar")
            jsonObj.put("description","This is test payment from live mode")
            jsonObj.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            jsonObj.put("currency","INR")
            jsonObj.put("amount","100")  // 1Rs. == 100
            val metaUserData = JSONObject()
            metaUserData.put("email","pawarakshay13@gmail.com")
            metaUserData.put("contact","+919657513437")

            jsonObj.put("userMetaData",metaUserData)
            checkout.open(this,jsonObj)

        }catch (ex : JSONException){
            Log.e("AXE","Exception : ",ex)
        }

    }

    override fun onPaymentSuccess(razorpayID : String?) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()
        Log.e("AXE","Payment Success : $razorpayID")
    }

    override fun onPaymentError(i : Int, p1: String?) {
        Toast.makeText(this, "Payment Fail", Toast.LENGTH_SHORT).show()
        Log.e("AXE","Payment Fail : $i \n ============= \n  $p1")
    }
}