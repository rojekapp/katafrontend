package com.paimon.katahack.view

import com.paimon.katahack.model.Update

interface UpdateView : UpdateCallback<Update> {
    fun onShowLoading()
}