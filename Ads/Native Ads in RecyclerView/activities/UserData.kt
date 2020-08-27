package com.codexdroid.nativeadsdemo

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.parcel.RawValue
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class SingleLargeObject (
    var total : Int = 0,
    var totalHits : Int = 0,
    var hits : @RawValue
    ArrayList<UserData>)



class UserData(

    var largeImageURL : String? = null,
    var downloads : Int = 0,
    var likes : Int = 0,
    var user : String? = null,
    var userImageURL : String? = null

)


enum class NetworkState {
    LOADED, ERROR, LOADING
}


private const val BASE_URL = "https://pixabay.com/api/"


private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UserDataApi {

    @GET("?key=15745555-d342d00f85f2ab3998a613c5e")
    fun getUserDetailsAsync() : Deferred<SingleLargeObject>

}

object UserDataApiService {
    val userApiService : UserDataApi by lazy {
        retrofit.create(UserDataApi::class.java)
    }
}