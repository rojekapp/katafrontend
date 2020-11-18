package com.paimon.katahack.util

import com.google.gson.JsonObject
import com.paimon.katahack.model.CreateInvoice
import com.paimon.katahack.model.Invoice
import com.paimon.katahack.model.Mutasi
import com.paimon.katahack.model.Update
import com.paimon.katahack.model.ongkir.Ongkir
import io.reactivex.Observable
import retrofit2.http.*

interface Service {
    @GET(API.INFO)
    fun getMutasi(): Observable<Mutasi>

    @GET(API.INVOCISE)
    fun getInvoice(): Observable<Invoice>

    @Headers("Content-Type: application/json")
    @POST(API.CREATE_INVOICE)
    fun setInvoice(@Body body: JsonObject) : Observable<CreateInvoice>

    @Headers("Content-Type: application/json")
    @POST(API.ONGKIR)
    fun setOngkir(@Body body: JsonObject): Observable<Ongkir>

    @GET
    fun getUpdate(@Url url : String): Observable<Update>
}