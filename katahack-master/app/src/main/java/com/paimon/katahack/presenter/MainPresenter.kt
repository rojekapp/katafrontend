package com.paimon.katahack.presenter

import com.google.gson.JsonObject
import com.paimon.katahack.model.CreateInvoice
import com.paimon.katahack.model.Invoice
import com.paimon.katahack.model.Mutasi
import com.paimon.katahack.model.Update
import com.paimon.katahack.model.ongkir.Ongkir
import com.paimon.katahack.view.*


class MainPresenter<T>(private val view: T, private val repository: MainRepository) {
    fun getMutasi() {
        if (view is MutasiView) {
            view.onShowLoading()
            repository.getMutasi(object :
                MutasiCallback<Mutasi> {
                override fun onGetMutasi(data: Mutasi) {
                    view.onGetMutasi(data)
                }

                override fun onGetMutasiError(message: String) {
                    view.onGetMutasiError(message)
                }
            })
        }
    }

    fun getInvoice() {
        if (view is InvoiceView) {
            view.onShowLoading()
            repository.getInvoice(object :
                InvoiceCallback<Invoice> {
                override fun onGetInvoice(data: Invoice) {
                    view.onGetInvoice(data)
                }

                override fun onGetInvoiceError(message: String) {
                    view.onGetInvoiceError(message)
                }
            })
        }
    }

    fun setInvoice(body: JsonObject) {
        if (view is CreateInvoiceView) {
            view.onShowLoading()
            repository.setInvoice(body, object :
                CreateInvoiceCallback<CreateInvoice> {
                override fun onSetInvoice(data: CreateInvoice) {
                    view.onSetInvoice(data)
                }

                override fun onSetInvoiceError(message: String) {
                    view.onSetInvoiceError(message)
                }
            })
        }
    }

    fun setOngkir(body: JsonObject) {
        if (view is OngkirView) {
            view.onShowLoading()
            repository.setOngkir(body, object :
                OngkirCallback<Ongkir> {
                override fun onSetOngkir(data: Ongkir) {
                    view.onSetOngkir(data)
                }

                override fun onSetOngkirError(message: String) {
                    view.onSetOngkirError(message)
                }

            })
        }
    }

    fun getUpdate(url: String) {
        if (view is UpdateView) {
            view.onShowLoading()
            repository.getUpdate(url, object :
                UpdateCallback<Update> {
                override fun onGetUpdate(data: Update) {
                    view.onGetUpdate(data)
                }

                override fun onGetUpdateError(message: String) {
                    view.onGetUpdateError(message)
                }

            })
        }
    }
}