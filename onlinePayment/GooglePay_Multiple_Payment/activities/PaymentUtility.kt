package com.codexdroid.easyupi_payment

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Sounds like I am sending request to server from this file
 *
 * i.e. what type of request i want that request i need to send to server
 * Ex. apiVersion, paymentToken, which card I should allow to user to make payment
 *
 * **/



object PaymentUtility {

    fun getBaseRequest() : JSONObject {
        return JSONObject().put("apiVersion",2).put("apiVersionMinor",0)

    }

    fun getPaymentTokenFromProvider() : JSONObject {

        lateinit var json : JSONObject
        try
        {
             json = JSONObject().put("type","PAYMENT_GATEWAY")
                .put("parameters",JSONObject()
                    .put("gateway","razorpay")
                    .put("gatewayMerchantId","FCIWGQTs4TAPrr"))

        }catch (ex : JSONException){}

        return json
    }


    fun allowedCards() : JSONArray{
        return JSONArray()
            .put("AMEX")
            .put("DISCOVER")
            .put("INTERAC")
            .put("JCB")
            .put("MASTERCARD")
            .put("VISA")
    }

    fun getAllowedCardAuthMethod() : JSONArray{
        return JSONArray()
            .put("PAN_ONLY")
            .put("CRYPTOGRAM_3DS")
    }

    fun baseCardPaymentMethod() : JSONObject{

        lateinit var cardPM : JSONObject
        try {
            cardPM = JSONObject()
            cardPM.put("type","CARD")

            val cardParam = JSONObject()
            cardParam.put("allowedAuthMethods", getAllowedCardAuthMethod())
            cardParam.put("allowedCard", allowedCards())
            cardParam.put("isBillingAddressRequired",true)

            val addressParam = JSONObject()
            addressParam.put("format","FULL")

            cardParam.put("addressParam",addressParam)
            cardPM.put("cardParam",cardParam)

        }catch (ex : JSONException){}

        return cardPM

    }

    fun cardPaymentMethod() : JSONObject{
        lateinit var paymentMethod : JSONObject
         try {
             paymentMethod = baseCardPaymentMethod()
             paymentMethod.put("tokenizationSpecification", getPaymentTokenFromProvider())

        }catch (ex : JSONException){}

        return paymentMethod

    }

    fun createPaymentClient(activity : Activity) : PaymentsClient{
        val wallOption = Wallet.WalletOptions.Builder().setEnvironment(WalletConstants.ENVIRONMENT_TEST).build()
        return Wallet.getPaymentsClient(activity,wallOption)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun readyToPayRequest() : Optional<JSONObject>{

        return try{
            val isReadyToPay = getBaseRequest()
            isReadyToPay.put("allowedPaymentMethods",JSONArray().put(baseCardPaymentMethod()))
            Optional.of(isReadyToPay)
        }catch (ex : JSONException){
            Optional.empty()
        }
    }
}
