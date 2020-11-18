package com.paimon.katahack.ui.fragment.autotext

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paimon.katahack.R
import com.paimon.katahack.ui.adapter.AutotextAdapter
import com.paimon.katahack.model.Autotext
import io.realm.Realm
import kotlinx.android.synthetic.main.autotext_fragment.view.*
import kotlinx.android.synthetic.main.dialog_add_autotext.*


class AutotextFragment : Fragment() {
    private lateinit var root: View
    private lateinit var realm: Realm
    private lateinit var autotext: Autotext
    private var autotextList = mutableListOf<Autotext>()

    companion object {
        fun newInstance() = AutotextFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.autotext_fragment, container, false)
        realm = Realm.getDefaultInstance()
//        subscribeAutotextResult()
        root.btn_add_text.setOnClickListener {
            addAutotext(autotextList)
        }
        val list = realm.where(Autotext::class.java).findAll()
        val adapter = AutotextAdapter()
        adapter.submitList(list)
        root.rv_autotext.adapter = adapter

        return root
    }


    private fun addAutotext(autotextList: List<Autotext>) {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_add_autotext)
        dialog.show()
        dialog.setCancelable(true)
        dialog.btn_submit_question.setOnClickListener {
            realm.executeTransaction {
                (autotextList as ArrayList).add(
                    Autotext(
                        dialog.edt_title.text.toString()
                        , dialog.edt_content.text.toString()
                    )
                )
                realm.insertOrUpdate(autotextList)
            }
            dialog.dismiss()
        }
    }
}
