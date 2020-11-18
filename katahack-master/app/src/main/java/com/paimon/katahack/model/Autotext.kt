package com.paimon.katahack.model

import io.realm.RealmObject

open class Autotext(var title: String = "", var content: String = "") : RealmObject()
