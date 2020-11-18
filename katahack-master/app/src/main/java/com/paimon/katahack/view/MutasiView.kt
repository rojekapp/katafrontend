package com.paimon.katahack.view

import com.paimon.katahack.model.Mutasi

interface MutasiView : MutasiCallback<Mutasi> {
    fun onShowLoading()
}