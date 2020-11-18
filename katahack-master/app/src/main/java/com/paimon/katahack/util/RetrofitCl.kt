package com.paimon.katahack.util

import com.paimon.katahack.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitCl {
    private var mInstance: Retrofit? = null

    val instance: Retrofit
        get() {
            if (mInstance == null) {
                mInstance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return mInstance!!
        }
}