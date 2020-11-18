package com.paimon.katahack.invoiceAPI

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer c623293a-4109-4969-8dbb-c2e87dec12d")
            .build()

        return chain.proceed(newRequest)
    }

}