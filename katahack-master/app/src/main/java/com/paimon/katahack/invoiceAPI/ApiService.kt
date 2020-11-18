package com.paimon.katahack.invoiceAPI

import com.paimon.katahack.modelOngkir.OngkirRequest
import com.paimon.katahack.modelOngkir.OngkirResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.COBA_INVOICE_URL)
    fun invoice(@Header("Authorization") token: String, @Body request: InvoiceRequest): Call<InvoiceResponse>


    @POST(Constants.COBA_ONGKIR_URL)
    fun ongkir(@Body request: OngkirRequest): Call<OngkirResponse>
}