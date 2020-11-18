package com.paimon.katahack.view

import com.paimon.katahack.model.ongkir.Ongkir

interface OngkirView : OngkirCallback<Ongkir> {
    fun onShowLoading()
}