package com.paimon.katahack.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "mutasi")
//Parcelable annotation to make parcelable object
@Parcelize
data class MutasiModel(
    //PrimaryKey annotation to declare primary key
    //ColumnInfo annotation to specify the column's name
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "nama") var tanggalTransaksi: String = "",
    @ColumnInfo(name = "lokasi") var JumlahTransaksi: String = ""
) : Parcelable {
}