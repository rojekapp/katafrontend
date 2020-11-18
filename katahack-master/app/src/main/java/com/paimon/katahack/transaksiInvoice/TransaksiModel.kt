package com.paimon.katahack.transaksiInvoice

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "transaksi")
//Parcelable annotation to make parcelable object
@Parcelize
data class TransaksiModel(
    //PrimaryKey annotation to declare primary key
    //ColumnInfo annotation to specify the column's name
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "nama") var nama: String = "",
    @ColumnInfo(name = "lokasi") var lokasi: String = "",
    @ColumnInfo(name = "phone") var phone: String = "",
    @ColumnInfo(name = "status") var status: String = "Belum Membayar",
    @ColumnInfo(name = "harga") var harga: Int = 0,
    @ColumnInfo(name = "info_bank") var info_bank: String = ""
) : Parcelable {
}