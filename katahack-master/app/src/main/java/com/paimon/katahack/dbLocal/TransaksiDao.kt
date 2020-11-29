package com.paimon.katahack.dbLocal

import androidx.room.*
import com.paimon.katahack.transaksiInvoice.TransaksiModel

@Dao
interface TransaksiDao {

    @Insert
    fun insert(note: TransaksiModel)

    @Update
    fun update(note: TransaksiModel)

    @Delete
    fun delete(note: TransaksiModel)

    @Query("SELECT * FROM transaksi")
    fun getAll() : List<TransaksiModel>

    @Query("SELECT * FROM transaksi WHERE id = :id")
    fun getById(id: Int) : List<TransaksiModel>

    @Query("SELECT * FROM transaksi WHERE status = :status")
    fun getByStatus(status: String) : List<TransaksiModel>
}