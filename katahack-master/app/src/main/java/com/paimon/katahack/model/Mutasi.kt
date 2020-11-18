package com.paimon.katahack.model

import com.google.gson.annotations.SerializedName

data class Mutasi(
    val mutation: Mutation
)

data class Mutation(
    val `data`: List<MutasiData>,
    val status: Boolean
)

data class MutasiData(
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("jenis_tran")
    val jenisTran: String,
    @SerializedName("ket_tran")
    val ketTran: String,
    @SerializedName("kode_tran")
    val kodeTran: String,
    @SerializedName("mata_uang")
    val mataUang: String,
    @SerializedName("mutasi_debet")
    val mutasiDebet: String,
    @SerializedName("mutasi_kredit")
    val mutasiKredit: String,
    @SerializedName("nomor_reff")
    val nomorReff: String,
    @SerializedName("nomor_rekening")
    val nomorRekening: String,
    @SerializedName("posisi_neraca")
    val posisiNeraca: String,
    @SerializedName("saldo_akhir_mutasi")
    val saldoAkhirMutasi: String,
    @SerializedName("saldo_awal_mutasi")
    val saldoAwalMutasi: String,
    @SerializedName("tanggal_tran")
    val tanggalTran: String
)