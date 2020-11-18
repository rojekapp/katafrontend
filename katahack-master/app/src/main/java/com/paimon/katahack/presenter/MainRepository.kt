package com.paimon.katahack.presenter

import com.google.gson.JsonObject
import com.paimon.katahack.model.CreateInvoice
import com.paimon.katahack.model.Invoice
import com.paimon.katahack.model.Mutasi
import com.paimon.katahack.model.Update
import com.paimon.katahack.model.ongkir.Ongkir
import com.paimon.katahack.util.RetrofitCl
import com.paimon.katahack.util.Service
import com.paimon.katahack.view.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainRepository {

    private lateinit var retrofit: Retrofit
    private lateinit var jsonApi: Service
    private lateinit var compositeDisposable: CompositeDisposable

    private fun setup() {
        retrofit = RetrofitCl.instance
        jsonApi = retrofit.create(Service::class.java)
        compositeDisposable = CompositeDisposable()
    }

    fun getMutasi(
        callback: MutasiCallback<Mutasi>
    ) {
        setup()
        compositeDisposable.add(
            jsonApi.getMutasi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    data.let {
                        callback.onGetMutasi(it)
                    }
                }, {
                    callback.onGetMutasiError(it.message!!)
                })
        )
    }

    fun getInvoice(
        callback: InvoiceCallback<Invoice>
    ) {
        setup()
        compositeDisposable.add(
            jsonApi.getInvoice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    data.let {
                        callback.onGetInvoice(it)
                    }
                }, {
                    callback.onGetInvoiceError(it.message!!)
                })
        )
    }

    fun setInvoice(
        body: JsonObject,
        callback: CreateInvoiceCallback<CreateInvoice>
    ) {
        setup()
        compositeDisposable.add(
            jsonApi.setInvoice(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    data.let {
                        callback.onSetInvoice(it)
                    }
                }, {
                    callback.onSetInvoiceError(it.message!!)
                })
        )
    }

    fun setOngkir(
        body: JsonObject,
        callback: OngkirCallback<Ongkir>
    ) {
        setup()
        compositeDisposable.add(
            jsonApi.setOngkir(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    data.let {
                        callback.onSetOngkir(it)
                    }
                }, {
                    callback.onSetOngkirError(it.message!!)
                })
        )
    }

    fun getUpdate(
        url: String,
        callback: UpdateCallback<Update>
    ) {
        setup()
        compositeDisposable.add(
            jsonApi.getUpdate(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    data.let {
                        callback.onGetUpdate(it)
                    }
                }, {
                    callback.onGetUpdateError(it.message!!)
                })
        )
    }
}